package ru.iteco.fmhandroid.pageobjects;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static ru.iteco.fmhandroid.test.BaseForTest.waitDisplayed;
import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class Dashboard {
    private String mainMenuItemCaptionMain = "Main";
    private String mainMenuItemCaptionNews = "News";
    private String mainMenuItemCaptionAbout = "About";
    private String logoutItemText = "Log out";
    private int mainMenuBtn = R.id.main_menu_image_button;
    private int missionBtn = R.id.our_mission_image_button;
    private int authBtn = R.id.authorization_image_button;
    private int listItems = android.R.id.title;

    public String getMainCaption() {
        return this.mainMenuItemCaptionMain;
    }
    public String getNewsCaption() {
        return this.mainMenuItemCaptionNews;
    }
    public String getAboutCaption() {
        return this.mainMenuItemCaptionAbout;
    }
    public String getLogoutItemText() {
        return this.logoutItemText;
    }

    public void mainMenuClick() {
        onView(isRoot()).perform(waitDisplayed(mainMenuBtn, 5000));
        Allure.step("Клик на кнопку с ID = " + mainMenuBtn + ("mainMenuClick()"));
        onView(withId(mainMenuBtn)).check(matches(isDisplayed())).perform(click());
    }

    public void missionButtonClick() {
        onView(isRoot()).perform(waitDisplayed(missionBtn, 5000));
        Allure.step("Клик на кнопку с ID = " + missionBtn + "(missionButtonClick())");
        onView(withId(missionBtn)).check(matches(isDisplayed())).perform(click());

    }
    public void authButtonClick() {
        onView(isRoot()).perform(waitDisplayed(authBtn, 5000));
        Allure.step("Клик на кнопку с ID = " + authBtn + "(authButtonClick())");
        onView(withId(authBtn)).check(matches(isDisplayed())).perform(click());
    }

    public void selectItemClick(String itemText) {
        Allure.step("Выбор элемента меню с TEXT = " + itemText);
        onView(allOf(withId(listItems), withText(itemText))).check(matches(isDisplayed())).perform(click());
    }
}
