package ru.iteco.fmhandroid.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
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
    @Test
    public void shouldNewsScreenBeOpened() {
        dashboard.mainMenuClick();
        dashboard.selectItemClick(dashboard.getNewsCaption());
        newsScreen.screenIsDisplayed();
    }
    @Test
    public void shouldAboutScreenBeOpened() {
        dashboard.mainMenuClick();
        dashboard.selectItemClick(dashboard.getAboutCaption());
        aboutScreen.screenIsDisplayed();
    }

    @Test
    public void shouldMissionScreenBeOpened() {
        dashboard.missionButtonClick();
        missionScreen.screenIsDisplayed();
    }
    @Test
    public void shouldLogout() {
        appMain.performLogout();
        loginScreen.screenIsDisplayed();
    }
}
