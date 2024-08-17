package ru.iteco.fmhandroid.runner;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import ru.iteco.fmhandroid.test.AppMainTest;
import ru.iteco.fmhandroid.test.AuthorizationTest;
import ru.iteco.fmhandroid.test.NewsControlPanelTest;
import ru.iteco.fmhandroid.test.DashboardTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AuthorizationTest.class,
        AppMainTest.class,
        DashboardTest.class,
        NewsControlPanelTest.class
})
public class UITestSuite {

}
