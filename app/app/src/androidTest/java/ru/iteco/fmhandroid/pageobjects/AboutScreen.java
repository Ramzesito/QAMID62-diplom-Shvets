package ru.iteco.fmhandroid.pageobjects;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import android.view.View;
import androidx.test.espresso.ViewInteraction;
import org.hamcrest.core.IsInstanceOf;
import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class AboutScreen {
    private int privacyLabel = R.id.about_privacy_policy_label_text_view;
    private int backBtn = R.id.about_back_image_button;
    private String screenText = "Privacy Policy:";

    public void screenIsDisplayed() {
        Allure.step("Проверка отображения экрана About.");
        ViewInteraction textView = onView(
                allOf(withId(privacyLabel), withText(screenText),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class))),
                        isDisplayed()));
        textView.check(matches(withText(screenText)));
    }

    public void clickReturnButton() {
        Allure.step("Клик на кнопку с ID = " + backBtn + " (clickReturnButton())");
        onView(withId(backBtn)).check(matches(isDisplayed())).perform(click());
    }
}
