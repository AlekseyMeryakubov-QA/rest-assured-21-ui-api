package in.reqres.tests;

import in.reqres.models.AddBooksListModel;
import in.reqres.models.DeleteBookModel;
import in.reqres.models.IsbnModel;
import in.reqres.models.LoginResponseModel;
import in.reqres.pages.UserProfilePage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static in.reqres.tests.TestData.*;


public class DeleteBookFromProfileTest extends  TestBase {
    @Test
    @DisplayName("Удаление выбранной книги из профиля")
    void deleteBookFromProfileTest() {
        LoginResponseModel loginResponse = authorizationApi.login(credentials);

        IsbnModel isbnModel = booksApi.createIsbnModel(getISBN());
        AddBooksListModel bookList = booksApi.createAddBooksListModel(loginResponse, isbnModel);
        DeleteBookModel deleteBookModel = booksApi.createDeleteBookModel(loginResponse,isbnModel);

        booksApi.deleteAllBooks (loginResponse);
        booksApi.addBook (loginResponse, bookList);
        booksApi.deleteOneBook(loginResponse, deleteBookModel);

        UserProfilePage.openUserProfileWithCookies(loginResponse.getUserId(),loginResponse.getToken(), loginResponse.getExpires());
        UserProfilePage.checkDisappearBook("Git Pocket Guide");

    }
}
