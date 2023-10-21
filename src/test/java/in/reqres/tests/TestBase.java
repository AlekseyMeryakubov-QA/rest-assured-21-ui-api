package in.reqres.tests;

import com.codeborne.selenide.Configuration;
import in.reqres.api.AuthorizationApi;
import in.reqres.api.BooksApi;
import in.reqres.helpers.CustomAllureListener;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {

    AuthorizationApi authorizationApi = new AuthorizationApi();
    BooksApi booksApi = new BooksApi();

    @BeforeAll
    static void settingURI() {
        RestAssured.baseURI = "https://demoqa.com";
        String browser = System.getProperty("browser", "chrome");
        String browserSize = System.getProperty("browserSize", "1920x1080");
        String remote = System.getProperty("remote","https://user1:1234@selenoid.autotests.cloud/wd/hub");
        String browserVersion = System.getProperty("browserVersion","100.0");
        Configuration.browser = browser;
        Configuration.browserSize = browserSize;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.remote = remote;
        Configuration.browserVersion = browserVersion;

    }
}


//                Configuration.baseUrl = "https://demoqa.com";
//                RestAssured.baseURI = "https://demoqa.com";
//                Configuration.browserSize = "1920x1080";
//                Configuration.pageLoadStrategy = "eager";