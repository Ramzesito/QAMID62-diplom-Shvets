package ru.iteco.fmhandroid.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.Epic;
import io.qameta.allure.kotlin.Feature;
import ru.iteco.fmhandroid.data.DataHelper;
import ru.iteco.fmhandroid.pageobjects.AboutScreen;
import ru.iteco.fmhandroid.pageobjects.AppMain;
import ru.iteco.fmhandroid.pageobjects.Dashboard;
import ru.iteco.fmhandroid.pageobjects.MainScreen;
import ru.iteco.fmhandroid.pageobjects.NewsScreen;

@RunWith(AllureAndroidJUnit4.class)
public class AppMainTest extends BaseForTest {
    private AppMain appMain = new AppMain();
    private Dashboard dashboard = new Dashboard();
    private NewsScreen newsScreen = new NewsScreen();
    private AboutScreen aboutScreen = new AboutScreen();
    private MainScreen mainScreen = new MainScreen();
    @Before
    public void initSetup() {
        if (!appMain.isLogined()) {
            appMain.performLogin(DataHelper.getValidAuthInfo());
        }
    }
    @Epic(value = "Основные функциональные тесты")
    @Feature(value = "Открытие экранов и переходы между ними")
    @Test
    @Description(value = "Проверка перехода с главного экрана на экран Новости")
    public void shouldNavigateFromMainToNews(){
        dashboard.mainMenuClick();
        dashboard.selectItemClick(dashboard.getNewsCaption());
        newsScreen.screenIsDisplayed();
    }
    @Epic(value = "Основные функциональные тесты")
    @Feature(value = "Открытие экранов и переходы между ними")
    @Test
    @Description(value = "Проверка перехода с главного экрана на экран About")
    public void shouldNavigateFromMainToAbout(){
        dashboard.mainMenuClick();
        dashboard.selectItemClick(dashboard.getAboutCaption());
        aboutScreen.screenIsDisplayed();
    }
    @Epic(value = "Основные функциональные тесты")
    @Feature(value = "Открытие экранов и переходы между ними")
    @Test
    @Description(value = "Проверка перехода с экрана Новости на главный экран")
    public void shouldNavigateFromNewsToMain() {
        dashboard.mainMenuClick();
        dashboard.selectItemClick(dashboard.getNewsCaption());
        dashboard.mainMenuClick();
        dashboard.selectItemClick(dashboard.getMainCaption());
        mainScreen.screenIsDisplayed();
    }
    @Epic(value = "Основные функциональные тесты")
    @Feature(value = "Открытие экранов и переходы между ними")
    @Test
    @Description(value = "Проверка перехода с экрана Новости на экран About")
    public void shouldNavigateFromNewsToAbout() {
        dashboard.mainMenuClick();
        dashboard.selectItemClick(dashboard.getNewsCaption());
        dashboard.mainMenuClick();
        dashboard.selectItemClick(dashboard.getAboutCaption());
        aboutScreen.screenIsDisplayed();
    }
    @Epic(value = "Основные функциональные тесты")
    @Feature(value = "Открытие экранов и переходы между ними")
    @Test
    @Description(value = "Проверка возврата с экрана About на главный экран")
    public void shouldReturnFromAboutToMain() {
        dashboard.mainMenuClick();
        dashboard.selectItemClick(dashboard.getAboutCaption());
        aboutScreen.clickReturnButton();
        mainScreen.screenIsDisplayed();
    }
    @Epic(value = "Основные функциональные тесты")
    @Feature(value = "Открытие экранов и переходы между ними")
    @Test
    @Description(value = "Проверка возврата с экрана About на экран Новости")
    public void shouldReturnFromAboutToNews() {
        dashboard.mainMenuClick();
        dashboard.selectItemClick(dashboard.getNewsCaption());
        dashboard.mainMenuClick();
        dashboard.selectItemClick(dashboard.getAboutCaption());
        aboutScreen.clickReturnButton();
        newsScreen.screenIsDisplayed();
    }
}