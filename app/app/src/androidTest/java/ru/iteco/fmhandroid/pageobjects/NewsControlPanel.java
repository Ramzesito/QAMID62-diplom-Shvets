package ru.iteco.fmhandroid.pageobjects;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.longClick;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static ru.iteco.fmhandroid.test.BaseForTest.childAtPosition;
import android.view.View;
import androidx.test.espresso.ViewInteraction;
import org.hamcrest.Matchers;
import org.hamcrest.core.IsInstanceOf;
import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.data.DataHelper;

public class NewsControlPanel {
    private String panelTitle = "Control panel";
    private int addNewsBtn = R.id.add_news_image_view;
    private int creatingNewsForm = R.id.container_custom_app_bar_include_on_fragment_create_edit_news;
    private String addNewsBtnContent = "Add news button";
    private int dropDownNewsTypesBtn = R.id.text_input_end_icon;
    private int dropDownNewsTypesText = R.id.news_item_category_text_auto_complete_text_view;
    private int newsTitleText = R.id.news_item_title_text_input_edit_text;
    private int newsDescText = R.id.news_item_description_text_input_edit_text;
    private int publishDateID = R.id.news_item_publish_date_text_input_edit_text;
    private int publishTimeID = R.id.news_item_publish_time_text_input_edit_text;
    private int saveBtn = R.id.save_button;
    private int cancelBtn = R.id.cancel_button;
    private int modalOkBtn = android.R.id.button1;
    private int messageID = android.R.id.message;
    private String cancelNotifyText = "The changes won't be saved, do you really want to log out?";

    public void screenIsDisplayed() {
        Allure.step("Проверка отображения Control Panel.");
        ViewInteraction textView = onView(
                allOf(withText(panelTitle),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class))),
                        isDisplayed()));
        textView.check(matches(withText(panelTitle)));
    }

    public void addNewsButtonClick() {
        Allure.step("Клик на кнопку с ID = " + addNewsBtn + "(addNewsButtonClick())");
        ViewInteraction materialButton = onView(
                allOf(withId(addNewsBtn), withContentDescription(addNewsBtnContent),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                3),
                        isDisplayed()));
        materialButton.perform(click());
    }

    public void creatingNewsFormIsDisplayed() {
        Allure.step("Проверка отображения формы Creating News.");
        onView(withId(creatingNewsForm)).check(matches(isDisplayed()));
    }

    public void fillCreatingNewsForm(DataHelper.NewsInfo newsInfo) {
        selectNewsCategory(newsInfo.getCategory());
        fillTextField(newsTitleText, newsInfo.getTitle());
        fillTextField(newsDescText, newsInfo.getDescription());
        selectPublicationDate(newsInfo.getPublicationDate());
        selectPublicationTime(newsInfo.getPublicationTime());
    }
    public void fillTextField(int fieldID, String text) {
        Allure.step("Заполнение текстового поля с ID = " + fieldID);
        onView(withId(fieldID)).perform(replaceText(text), closeSoftKeyboard());
    }

    public void selectPublicationDate(String date) {
        Allure.step("Выбор даты = " + date + " (если пустая, то берется текущая)");
        if (date.isEmpty()) {
            // если дата не указана, то берем текущую
            onView(withId(publishDateID)).perform(click());
            onView(withId(modalOkBtn)).perform(click());
        } else {
            onView(withId(publishDateID))
                    .perform(longClick()).perform(replaceText(date), closeSoftKeyboard());
        }
    }

    public void selectPublicationTime(String time) {
        Allure.step("Выбор времени = " + time + " (если пустое, то берется текущее)");
        if (time.isEmpty()) {
            // если время не указано, то берем текущее
            onView(withId(publishTimeID)).perform(click());
            onView(withId(modalOkBtn)).perform(click());
        } else {
            onView(withId(publishTimeID))
                    .perform(longClick()).perform(replaceText(time), closeSoftKeyboard());
        }
    }

    public void selectNewsCategory(String category) {
        Allure.step("Выбор категории создаваемой новости: " + category);
        Allure.step("Клик на дропдаун с категориями создаваемых новостей с ID = " + dropDownNewsTypesBtn);

        ViewInteraction checkableImageButton = onView(
                allOf(withId(com.google.android.material.R.id.text_input_end_icon), withContentDescription("Show dropdown menu"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                0),
                        isDisplayed()));
        checkableImageButton.perform(click());

        Allure.step("Ввод категории в поле с ID = " + dropDownNewsTypesText);
        ViewInteraction categoryInputText = onView(withId(dropDownNewsTypesText));
        categoryInputText.perform(replaceText(DataHelper.newsCategory[1]), closeSoftKeyboard());

    }
    public void clickSaveButton() {
        Allure.step("Клик по кнопке с ID = " + saveBtn + "(clickSaveButton())");
        onView(withId(saveBtn)).check(matches(isDisplayed())).perform(click());
    }
    public void clickCancelButton() {
        Allure.step("Клик по кнопке с ID = " + cancelBtn + "(clickCancelButton())");
        onView(withId(cancelBtn)).check(matches(isDisplayed())).perform(click());
    }

    public void clickModalOK() {
        Allure.step("Клик по кнопке с ID = " + modalOkBtn + "(clickModalOK())");
        onView(withId(modalOkBtn)).check(matches(isDisplayed())).perform(click());
    }

    public void isCancelNotifyMessageDisplayed() {
        Allure.step("Проверка наличия предупреждающего сообщения об отмене сохранения новости.");
        onView(withId(messageID)).
                check(matches(isDisplayed())).
                check(matches(withText(cancelNotifyText)));
    }

    public void isToastMessageDisplayed(View decorView, String message) {
        Allure.step("Проверка наличия всплывающего сообщения: " + message);
        onView(withText(message)).
                inRoot(withDecorView(Matchers.not(decorView)))
                .check(matches(isDisplayed()));
    }
}
