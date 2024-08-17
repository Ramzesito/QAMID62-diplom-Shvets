package ru.iteco.fmhandroid.pageobjects;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static ru.iteco.fmhandroid.test.BaseForTest.waitDisplayed;
import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class LoginScreen {

    private final int loginFld = R.id.login_text_input_layout;
    private final int pswFld = R.id.password_text_input_layout;
    private final int enterBtn = R.id.enter_button;
    private final String loginHint = "Login";
    private final String pswHint = "Password";

    public Boolean screenIsDisplayed() {
        Allure.step("Проверка, отображается ли экран логина...");
        try {
            onView(isRoot()).perform(waitDisplayed(enterBtn, 5000));
            onView(withId(enterBtn)).check(matches(isDisplayed()));
            Allure.step("Экран логина отображается.");
            return true;
        } catch (Exception e) {
            Allure.step("Экран логина не отображается.");
            return false; //(NoMatchingViewException)
        }
    }

    public void fillLogin(String login) {
        onView(isRoot()).perform(waitDisplayed(loginFld, 5000));
        Allure.step("Заполнение поля Login = " + login);
        onView(withHint(loginHint)).perform(typeText(login), closeSoftKeyboard());
    }

    public void fillPassword(String password) {
        onView(isRoot()).perform(waitDisplayed(pswFld, 5000));
        Allure.step("Заполнение поля Password = " + password);
        onView(withHint(pswHint)).perform(typeText(password), closeSoftKeyboard());
    }

    public void clickEnterButton() {
        Allure.step("Клик по кнопке с ID = " + enterBtn + "(clickEnterButton())");
        onView(withId(enterBtn)).check(matches(isDisplayed())).perform(click());
    }

}
