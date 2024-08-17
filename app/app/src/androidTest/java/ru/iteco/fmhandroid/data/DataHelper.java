package ru.iteco.fmhandroid.data;

public class DataHelper {
    private DataHelper() {
    }

    // -------------  по пользователям  ----------------------
    public static class AuthInfo {
        private final String login;
        private final String password;

        private AuthInfo(String login, String password) {
            this.login = login;
            this.password = password;
        }

        public String getLogin() {
            return this.login;
        }

        public String getPassword() {
            return this.password;
        }
    }

    public static AuthInfo getValidAuthInfo() {
        return new AuthInfo("login2", "password2");
    }

    public static AuthInfo getInvalidAuthInfo() {
        return new AuthInfo("mr561aa456_0", "45lkdlv545");
    }
    public static AuthInfo getValidLoginInvalidPass() {
        return new AuthInfo("login2", "45ghgj78gfdf");
    }
    public static AuthInfo getEmptyAuthInfo() {
        return new AuthInfo("", "");
    }


    // -------------  по новостям  ----------------------

    public static final String[] newsCategory = {
            "Объявление",
            "День рождения",
            "Зарплата",
            "Профсоюз",
            "Праздник"
    };
    public static class NewsInfo {
        private final String category;
        private final String title;
        private final String description;
        private final String publicationDate;
        private final String publicationTime;

        private NewsInfo(String category, String title, String description, String publicationDate, String publicationTime) {
            this.category = category;
            this.title =  title;
            this.description = description;
            this.publicationDate = publicationDate;
            this.publicationTime = publicationTime;
        }

        public String getCategory() {
            return this.category;
        }
        public String getTitle() {
            return this.title;
        }
        public String getDescription() {
            return this.description;
        }
        public String getPublicationDate() {
            return this.publicationDate;
        }
        public String getPublicationTime() {
            return this.publicationTime;
        }
    }

    public static NewsInfo getValidNews(int newsIndex) {
        switch (newsIndex) {
            case 1:
                return new NewsInfo(newsCategory[1],
                        "News to cancel checking!",
                        "SHOULD BE CANCELED",
                        "",
                        "");
            case 2:
                return new NewsInfo(newsCategory[2],
                        "MyNewsTitle111!!!",
                        "MyDES111C!!!",
                        "31.12.2024",
                        "");
            default:
                return new NewsInfo(newsCategory[0],
                        "Default news",
                        "Description of default news",
                        "",
                        "");
        }
    }
}
