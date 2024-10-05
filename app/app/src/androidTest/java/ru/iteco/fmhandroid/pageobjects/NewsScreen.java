package ru.iteco.fmhandroid.pageobjects;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;
import static ru.iteco.fmhandroid.test.BaseForTest.childAtPosition;
import androidx.test.espresso.ViewInteraction;
import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.data.DataHelper;

public class NewsScreen {
    private int newsContainer = R.id.container_list_news_include;
    private int editNewsButton = R.id.edit_news_material_button;
    private int newsScreenBoard = R.id.container_custom_app_bar_include_on_fragment_news_list;
    private int newsRecyclerView = R.id.news_list_recycler_view;
    private int newsMaterialCardView = R.id.news_item_material_card_view;
    private int newsDescription = R.id.news_item_description_text_view;

    public void screenIsDisplayed() {
        Allure.step("Проверка отображения экрана News.");
        onView(withId(newsScreenBoard)).check(matches(isDisplayed()));
    }

    public void controlPanelEnter() {
        Allure.step("Клик на кнопку с ID = " + editNewsButton + "(controlPanelEnter())");
        ViewInteraction materialButton = onView(
                allOf(withId(editNewsButton),
                        childAtPosition(
                                childAtPosition(
                                        withId(newsContainer),
                                        0),
                                3),
                        isDisplayed()));
        materialButton.perform(click());
    }
    public void isNewsPublished(DataHelper.NewsInfo news) {
        Allure.step("Клик на первую новость в RecyclerView с ID = " + newsRecyclerView+ " (isNewsPublished())");
        onView(withId(newsRecyclerView)).perform(actionOnItemAtPosition(0, click()));

        Allure.step("Проверка наличия Description = " + news.getDescription());
        onView(allOf(withId(newsDescription),
                withParent(withParent(withId(newsMaterialCardView))),
                isDisplayed()))
                .check(matches(withText(news.getDescription())));
    }
    public void isNewsNotPublished(DataHelper.NewsInfo news) {
        Allure.step("Проверка списка новостей на пустоту.");

//        ViewInteraction textView = onView(
//                allOf(withId(R.id.empty_news_list_text_view), withText("There is nothing here yet..."),
//                        withParent(allOf(withId(R.id.all_news_cards_block_constraint_layout),
//                                withParent(withId(R.id.container_list_news_include)))),
//                        isDisplayed()));
//        textView.check(matches(withText("There is nothing here yet...")));


        Allure.step("Клик на первую новость в RecyclerView с ID = " + newsRecyclerView + " (isNewsNotPublished())");
        onView(withId(newsRecyclerView)).perform(actionOnItemAtPosition(0, click()));

        Allure.step("Проверка отсутствия Description = " + news.getDescription());
        onView(allOf(withId(newsDescription),
                withParent(withParent(withId(newsMaterialCardView))),
                isDisplayed()))
                .check(matches(not(withText(news.getDescription()))));
    }
}
