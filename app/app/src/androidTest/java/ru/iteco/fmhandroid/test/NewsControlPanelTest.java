package ru.iteco.fmhandroid.test;

import android.view.View;

import androidx.test.core.app.ActivityScenario;

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
import ru.iteco.fmhandroid.pageobjects.AppMain;
import ru.iteco.fmhandroid.pageobjects.NewsControlPanel;
import ru.iteco.fmhandroid.pageobjects.Dashboard;
import ru.iteco.fmhandroid.pageobjects.NewsScreen;
import ru.iteco.fmhandroid.ui.AppActivity;

@RunWith(AllureAndroidJUnit4.class)
public class NewsControlPanelTest extends BaseForTest {
    private AppMain appMain = new AppMain();
    private Dashboard dashboard = new Dashboard();
    private NewsControlPanel newsControlPanel = new NewsControlPanel();
    private NewsScreen newsScreen = new NewsScreen();
    private String toastMessage = "Fill empty fields";
    private View decorView;

    @Before
    public void initSetup() {

        mActivityScenarioRule.getScenario().onActivity(new ActivityScenario.ActivityAction<AppActivity>() {
            @Override
            public void perform(AppActivity activity) {
                decorView = activity.getWindow().getDecorView();
            }
        });

        if (!appMain.isLogined()) {
            appMain.performLogin(DataHelper.getValidAuthInfo());
        }
        dashboard.mainMenuClick();
        dashboard.selectItemClick(dashboard.getNewsCaption());
        newsScreen.controlPanelEnter();
    }

    @Epic(value = "Основные функциональные тесты")
    @Feature(value = "Панель управления новостями")
    @Test
    @Description(value = "Проверка открытия панели управления новостями")
    @Severity(value = SeverityLevel.BLOCKER)
    public void shouldNewsControlPanelBeOpened() {
        newsControlPanel.screenIsDisplayed();
    }

    @Epic(value = "Основные функциональные тесты")
    @Feature(value = "Панель управления новостями")
    @Test
    @Description(value = "Проверка открытия формы создания новости")
    @Severity(value = SeverityLevel.BLOCKER)
    public void shouldCreatingNewsFormBeOpened() {
        newsControlPanel.addNewsButtonClick();
        newsControlPanel.creatingNewsFormIsDisplayed();
    }

    @Epic(value = "Основные функциональные тесты")
    @Feature(value = "Панель управления новостями")
    @Test
    @Description(value = "Проверка добавления валидной новости")
    @Severity(value = SeverityLevel.BLOCKER)
    public void shouldAddValidNews() {
        DataHelper.NewsInfo creatingNews = DataHelper.getValidNews(0);
        newsControlPanel.addNewsButtonClick();
        newsControlPanel.fillCreatingNewsForm(creatingNews);
        newsControlPanel.clickSaveButton();
        // проверка, что новость появилась в разделе Новости
        dashboard.mainMenuClick();
        dashboard.selectItemClick(dashboard.getNewsCaption());
        newsScreen.isNewsPublished(creatingNews);
    }

    @Epic(value = "Основные функциональные тесты")
    @Feature(value = "Панель управления новостями")
    @Test
    @Description(value = "Проверка сообщения при отмене добавления новости")
    @Severity(value = SeverityLevel.MINOR)
    public void shouldNotifyWhenCancelAddingNews() {
        newsControlPanel.addNewsButtonClick();
        newsControlPanel.clickCancelButton();
        newsControlPanel.isCancelNotifyMessageDisplayed();
    }

    @Epic(value = "Основные функциональные тесты")
    @Feature(value = "Панель управления новостями")
    @Test
    @Description(value = "Проверка отсутствия сохранения новости при отмене создания")
    @Severity(value = SeverityLevel.NORMAL)
    public void shouldCancelAddingNewsWithoutSaving() {
        DataHelper.NewsInfo cancelingNews = DataHelper.getValidNews(1);
        newsControlPanel.addNewsButtonClick();
        newsControlPanel.fillCreatingNewsForm(cancelingNews);
        newsControlPanel.clickCancelButton();
        //нажать OK в окошке и проверить, что новость не появилась на экране Новости
        newsControlPanel.clickModalOK();
        dashboard.mainMenuClick();
        dashboard.selectItemClick(dashboard.getNewsCaption());
        newsScreen.isNewsNotPublished(cancelingNews);
    }

    @Epic(value = "Основные функциональные тесты")
    @Feature(value = "Панель управления новостями")
    @Test
    @Description(value = "Проверка всплывающего сообщения при сохранении новости при незаполненных полях")
    @Severity(value = SeverityLevel.MINOR)
    public void shouldNotifyIfEmptyFields() {
        newsControlPanel.addNewsButtonClick();
        newsControlPanel.creatingNewsFormIsDisplayed();
        newsControlPanel.clickSaveButton();
        newsControlPanel.isToastMessageDisplayed(decorView, toastMessage);
    }

}