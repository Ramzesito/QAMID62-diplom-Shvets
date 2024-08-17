package ru.iteco.fmhandroid.pageobjects;

import ru.iteco.fmhandroid.data.DataHelper;

public class AppMain {
    private LoginScreen loginScreen = new LoginScreen();
    private Dashboard dashboard = new Dashboard();


    public Boolean isLogined() {
        if (loginScreen.screenIsDisplayed()) {
            return false;
        }
        return true;
    }

    public void performLogin(DataHelper.AuthInfo authInfo) {
        loginScreen.fillLogin(authInfo.getLogin());
        loginScreen.fillPassword(authInfo.getPassword());
        loginScreen.clickEnterButton();
    }

    public void performLogout() {
        dashboard.authButtonClick();
        dashboard.selectItemClick(dashboard.getLogoutItemText());
    }
}
