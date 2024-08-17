package ru.iteco.fmhandroid.pageobjects;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayingAtLeast;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class MainScreen {
    private int mainScreenBoard = R.id.container_custom_app_bar_include_on_fragment_main;

    public void screenIsDisplayed() {
        Allure.step("Проверка отображения экрана Main.");
        onView(withId(mainScreenBoard)).check(matches(isDisplayingAtLeast(1)));
    }
}
