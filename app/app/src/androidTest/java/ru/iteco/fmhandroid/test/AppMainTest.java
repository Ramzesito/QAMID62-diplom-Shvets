package ru.iteco.fmhandroid.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
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
    @Test
    public void shouldNavigateFromMainToNews(){
        dashboard.mainMenuClick();
        dashboard.selectItemClick(dashboard.getNewsCaption());
        newsScreen.screenIsDisplayed();
    }
    @Test
    public void shouldNavigateFromMainToAbout(){
        dashboard.mainMenuClick();
        dashboard.selectItemClick(dashboard.getAboutCaption());
        aboutScreen.screenIsDisplayed();
    }
    @Test
    public void shouldNavigateFromNewsToMain() {
        dashboard.mainMenuClick();
        dashboard.selectItemClick(dashboard.getNewsCaption());
        dashboard.mainMenuClick();
        dashboard.selectItemClick(dashboard.getMainCaption());
        mainScreen.screenIsDisplayed();
    }
    @Test
    public void shouldNavigateFromNewsToAbout() {
        dashboard.mainMenuClick();
        dashboard.selectItemClick(dashboard.getNewsCaption());
        dashboard.mainMenuClick();
        dashboard.selectItemClick(dashboard.getAboutCaption());
        aboutScreen.screenIsDisplayed();
    }
    @Test
    public void shouldReturnFromAboutToMain() {
        dashboard.mainMenuClick();
        dashboard.selectItemClick(dashboard.getAboutCaption());
        aboutScreen.clickReturnButton();
        mainScreen.screenIsDisplayed();
    }
    @Test
    public void shouldReturnFromAboutToNews() {
        dashboard.mainMenuClick();
        dashboard.selectItemClick(dashboard.getNewsCaption());
        dashboard.mainMenuClick();
        dashboard.selectItemClick(dashboard.getAboutCaption());
        aboutScreen.clickReturnButton();
        newsScreen.screenIsDisplayed();
    }
}