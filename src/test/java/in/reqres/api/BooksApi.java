package in.reqres.api;

import in.reqres.models.*;
import io.qameta.allure.Step;

import java.util.ArrayList;
import java.util.List;

import static in.reqres.specs.BookSpec.*;
import static io.restassured.RestAssured.given;

public class BooksApi {

    @Step("Запрос на удаление всех книг")
    public void deleteAllBooks(LoginResponseModel loginResponse) {
        given(bookRequestSpec)
                .header("Authorization", "Bearer " + loginResponse.getToken())
                .queryParam("UserId", loginResponse.getUserId())
                .when()
                .delete("/BookStore/v1/Books")
                .then()
                .spec(successDeleteBookResponseSpec);
    }

    @Step("Запрос на добавление книги")
    public void addBook(LoginResponseModel loginResponse, AddBooksListModel booksList) {
        given(bookRequestSpec)
                .header("Authorization", "Bearer " + loginResponse.getToken())
                .body(booksList)
                .when()
                .post("/BookStore/v1/Books")
                .then()
                .spec(successAddBookResponseSpec)
                .extract().as(AddBooksResponseModel.class);
    }

    @Step("Запрос на удаление одной книги")
    public void deleteOneBook(LoginResponseModel loginResponse, DeleteBookModel deleteBookModel) {
        given(bookRequestSpec)
                .header("Authorization", "Bearer " + loginResponse.getToken())
                .queryParam("UserId", loginResponse.getUserId())
                .body(deleteBookModel)
                .when()
                .delete("/BookStore/v1/Book")
                .then()
                .spec(successDeleteBookResponseSpec);
    }

    public IsbnModel createIsbnModel(String isbn) {
        IsbnModel isbnModel = new IsbnModel();
        isbnModel.setIsbn(isbn);
        return isbnModel;
    }

    public AddBooksListModel createAddBooksListModel(LoginResponseModel loginResponse, IsbnModel isbnModel) {
        AddBooksListModel booksList = new AddBooksListModel();
        booksList.setUserId(loginResponse.getUserId());
        List<IsbnModel> isbnList = new ArrayList<>();
        isbnList.add(isbnModel);
        booksList.setCollectionOfIsbns(isbnList);
        return booksList;
    }

    public DeleteBookModel createDeleteBookModel(LoginResponseModel loginResponse, IsbnModel isbnModel) {
        DeleteBookModel deleteBookModel = new DeleteBookModel();
        deleteBookModel.setUserId(loginResponse.getUserId());
        deleteBookModel.setIsbn(isbnModel.getIsbn());
        return deleteBookModel;
    }
}