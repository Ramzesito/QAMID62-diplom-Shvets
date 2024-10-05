package ru.iteco.fmhandroid.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.Epic;
import io.qameta.allure.kotlin.Feature;
import io.qameta.allure.kotlin.Severity;
import io.qameta.allure.kotlin.SeverityLevel;
import ru.iteco.fmhandroid.data.DataHelper;
import ru.iteco.fmhandroid.pageobjects.AboutScreen;
import ru.iteco.fmhandroid.pageobjects.AppMain;
import ru.iteco.fmhandroid.pageobjects.Dashboard;
import ru.iteco.fmhandroid.pageobjects.LoginScreen;
import ru.iteco.fmhandroid.pageobjects.MissionScreen;
import ru.iteco.fmhandroid.pageobjects.NewsScreen;

@RunWith(AllureAndroidJUnit4.class)
public class DashboardTest extends BaseForTest {
    private NewsScreen newsScreen = new NewsScreen();
    private AboutScreen aboutScreen = new AboutScreen();
    private LoginScreen loginScreen = new LoginScreen();
    private Dashboard dashboard = new Dashboard();
    private MissionScreen missionScreen = new MissionScreen();
    private AppMain appMain = new AppMain();
    @Before
    public void initSetup() {
        if (!appMain.isLogined()) {
            appMain.performLogin(DataHelper.getValidAuthInfo());
        }
    }
    @Epic(value = "Основные функциональные тесты")
    @Feature(value = "Открытие экранов и переходы между ними")
    @Test
    @Description(value = "Проверка открытия экрана Новости")
    public void shouldNewsScreenBeOpened() {
        dashboard.mainMenuClick();
        dashboard.selectItemClick(dashboard.getNewsCaption());
        newsScreen.screenIsDisplayed();
    }
    @Epic(value = "Основные функциональные тесты")
    @Feature(value = "Открытие экранов и переходы между ними")
    @Test
    @Description(value = "Проверка открытия экрана About")
    @Severity(value = SeverityLevel.MINOR)
    public void shouldAboutScreenBeOpened() {
        dashboard.mainMenuClick();
        dashboard.selectItemClick(dashboard.getAboutCaption());
        aboutScreen.screenIsDisplayed();
    }
    @Epic(value = "Основные функциональные тесты")
    @Feature(value = "Открытие экранов и переходы между ними")
    @Test
    @Description(value = "Проверка открытия экрана Mission")
    @Severity(value = SeverityLevel.MINOR)
    public void shouldMissionScreenBeOpened() {
        dashboard.missionButtonClick();
        missionScreen.screenIsDisplayed();
    }
    @Epic(value = "Основные функциональные тесты")
    @Feature(value = "Авторизация")
    @Test
    @Description(value = "Проверка разлогинивания")
    @Severity(value = SeverityLevel.CRITICAL)
    public void shouldLogout() {
        appMain.performLogout();
        loginScreen.screenIsDisplayed();
    }
}
