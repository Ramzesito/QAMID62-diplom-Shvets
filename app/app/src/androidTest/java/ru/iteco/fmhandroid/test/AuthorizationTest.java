package ru.iteco.fmhandroid.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Attachment;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.Epic;
import io.qameta.allure.kotlin.Feature;
import io.qameta.allure.kotlin.Severity;
import io.qameta.allure.kotlin.SeverityLevel;
import ru.iteco.fmhandroid.data.DataHelper;
import ru.iteco.fmhandroid.pageobjects.AppMain;
import ru.iteco.fmhandroid.pageobjects.LoginScreen;
import ru.iteco.fmhandroid.pageobjects.MainScreen;

@RunWith(AllureAndroidJUnit4.class)
public class AuthorizationTest extends BaseForTest {

    private AppMain appMain = new AppMain();
    private LoginScreen loginScreen = new LoginScreen();
    private MainScreen mainScreen = new MainScreen();

    @Before
    public void checkIfLogined() {
        Allure.step("Проверка, залогинен ли пользователь...");
        if (appMain.isLogined()) {
            Allure.step("Залогинен, производится логаут перед тестом логина.");
            appMain.performLogout();
        } else {
            Allure.step("Не залогинен.");
        }
    }
    @Epic(value = "Основные функциональные тесты")
    @Feature(value = "Авторизация")
    @Test
    @Description(value = "Проверка авторизации при валидных логине и пароле")
    @Severity(value = SeverityLevel.BLOCKER)
    public void shouldValidLoginPassword() {
        appMain.performLogin(DataHelper.getValidAuthInfo());
        mainScreen.screenIsDisplayed();
    }
    @Epic(value = "Основные функциональные тесты")
    @Feature(value = "Авторизация")
    @Test
    @Description(value = "Проверка авторизации при НЕвалидных логине и пароле")
    @Severity(value = SeverityLevel.NORMAL)
    public void shouldNotInvalidLoginPassword() {
        appMain.performLogin(DataHelper.getInvalidAuthInfo());
        loginScreen.screenIsDisplayed();
    }
    @Epic(value = "Основные функциональные тесты")
    @Feature(value = "Авторизация")
    @Test
    @Description(value = "Проверка авторизации при валидном логине и НЕвалидном пароле")
    @Severity(value = SeverityLevel.NORMAL)
    public void shouldNotValidLoginInvalidPassword() {
        appMain.performLogin(DataHelper.getValidLoginInvalidPass());
        loginScreen.screenIsDisplayed();
    }
    @Epic(value = "Основные функциональные тесты")
    @Feature(value = "Авторизация")
    @Test
    @Description(value = "Проверка авторизации при незаполненных полях логин/пароль")
    @Severity(value = SeverityLevel.NORMAL)
    public void shouldNotEmptyLoginPassword() {
        appMain.performLogin(DataHelper.getEmptyAuthInfo());
        loginScreen.screenIsDisplayed();
    }
}
