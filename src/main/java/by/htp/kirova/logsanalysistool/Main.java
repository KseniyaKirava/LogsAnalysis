package by.htp.kirova.logsanalysistool;

import by.htp.kirova.logsanalysistool.view.AppSetting;

/**
 * Main class of application.
 *
 * @author Kseniya Kirava
 * @since April 2, 2019
 */
public class Main {

    public static void main(String[] args) {

        AppSetting settings = new AppSetting();
        settings.init();
        Application application = new Application();
        application.invoke(settings);

    }
}
