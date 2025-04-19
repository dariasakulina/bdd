package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private final SelenideElement loginField = $("[data-test-id=login] input");
    private final SelenideElement passwordField = $("[data-test-id=password] input");
    private final SelenideElement loginButton = $("[data-test-id=action-login]");
    private final SelenideElement errorNotification = $("[data-test-id=error-notification]");

    public VerificationPage validLogin(DataHelper.AuthInfo authInfo) {
        performLogin(authInfo);
        return new VerificationPage();
    }

    public LoginPage invalidLogin(DataHelper.AuthInfo authInfo) {
        performLogin(authInfo);
        return this;
    }

    private void performLogin(DataHelper.AuthInfo authInfo) {
        loginField.setValue(authInfo.getLogin());
        passwordField.setValue(authInfo.getPassword());
        loginButton.click();
    }

    public void shouldShowError(String expectedMessage) {
        errorNotification.shouldBe(visible).shouldHave(text(expectedMessage));
    }
}