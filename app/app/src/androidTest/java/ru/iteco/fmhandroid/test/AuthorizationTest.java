package ru.iteco.fmhandroid.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Allure;
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
    @Test
    public void shouldValidLoginPassword() {
        appMain.performLogin(DataHelper.getValidAuthInfo());
        mainScreen.screenIsDisplayed();
    }
    @Test
    public void shouldNotInvalidLoginPassword() {
        appMain.performLogin(DataHelper.getInvalidAuthInfo());
        loginScreen.screenIsDisplayed();
    }
    @Test
    public void shouldNotValidLoginInvalidPassword() {
        appMain.performLogin(DataHelper.getValidLoginInvalidPass());
        loginScreen.screenIsDisplayed();
    }
    @Test
    public void shouldNotEmptyLoginPassword() {
        appMain.performLogin(DataHelper.getEmptyAuthInfo());
        loginScreen.screenIsDisplayed();
    }
}
