package in.reqres.tests;

import in.reqres.models.CredentialsModel;

public class TestData {

    private static final String LOGIN = "Alex777";
    private static final String PASSWORD = "Alex777!";
    private static final String ISBN = "9781449325862";

    public static CredentialsModel credentials = new CredentialsModel(LOGIN,PASSWORD);

    public static String getISBN() {
        return ISBN;
    }
}
