package ru.iteco.fmhandroid.pageobjects;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static ru.iteco.fmhandroid.test.BaseForTest.waitDisplayed;
import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class MissionScreen {
    private int missionTitle = R.id.our_mission_title_text_view;
    private String screenTitle = "Love is all";

    public void screenIsDisplayed() {
        Allure.step("Проверка отображения экрана Mission.");
        onView(withId(missionTitle)).check(matches(isDisplayed())).check(matches(withText(screenTitle)));
    }
}
