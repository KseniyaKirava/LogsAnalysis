package by.htp.kirova.logsanalysistool;

import by.htp.kirova.logsanalysistool.service.util.Parser;
import by.htp.kirova.logsanalysistool.view.AppSetting;
import by.htp.kirova.logsanalysistool.view.DateTimeRoundingType;
import by.htp.kirova.logsanalysistool.view.GroupingSettings;
import by.htp.kirova.logsanalysistool.view.filter.BaseFilterSetting;
import by.htp.kirova.logsanalysistool.view.filter.MessageFilterSetting;
import by.htp.kirova.logsanalysistool.view.filter.TimePeriodFilterSetting;
import by.htp.kirova.logsanalysistool.view.filter.UsernameFilterSetting;
import org.junit.Test;

import java.nio.file.Paths;
import java.util.regex.Pattern;

public class ApplicationTest {

    @Test(timeout = 1500)
    public void test1_1() throws Exception{
        test1Impl(1, "/out/filtered-test1_1.csv");
    }

    @Test(timeout = 1500)
    public void test1_4() throws Exception{
        test1Impl(2,"/out/filtered-test1_4.csv");
    }


    @Test(timeout = 1500)
    public void test2() throws Exception{
        AppSetting settings = new AppSetting();
        String resourcesDir = getResourcesDir();
        settings.setInputPath(Paths.get(resourcesDir));
        settings.setOutputPath(Paths.get(resourcesDir + "/out/filtered-test2.csv"));

        TimePeriodFilterSetting ts = new TimePeriodFilterSetting();
        ts.setStartDate(Parser.getInstance().parseDate("01.11.2001").atStartOfDay());
        ts.setEndDate(Parser.getInstance().parseDate("01.12.2001").atStartOfDay());
        ts.setUsed(true);
        settings.setFilterSetting(new BaseFilterSetting[] { ts });

        settings.setThreadsCount(4);

        GroupingSettings groupingSettings = new GroupingSettings();
        groupingSettings.setGroupByUsername(true);
        groupingSettings.setGroupByDate(DateTimeRoundingType.DAY);
        settings.setGroupingSettings(groupingSettings);

        Application application = new Application();
        application.invoke(settings);
    }

    @Test(timeout = 1500)
    public void test3() throws Exception{
        AppSetting settings = new AppSetting();
        String resourcesDir = getResourcesDir();
        settings.setInputPath(Paths.get(resourcesDir));
        settings.setOutputPath(Paths.get(resourcesDir + "/out/filtered-test3.csv"));

        MessageFilterSetting ms = new MessageFilterSetting();
        ms.setPattern(Pattern.compile("message [0-9]{2}"));
        ms.setUsed(true);
        settings.setFilterSetting(new BaseFilterSetting[] { ms });

        settings.setThreadsCount(2);

        GroupingSettings groupingSettings = new GroupingSettings();
        groupingSettings.setGroupByUsername(true);
        groupingSettings.setGroupByDate(DateTimeRoundingType.HOUR);
        settings.setGroupingSettings(groupingSettings);

        Application application = new Application();
        application.invoke(settings);
    }

    void test1Impl(int threadsCount, String outName) throws Exception{
        AppSetting settings = new AppSetting();
        String resourcesDir = getResourcesDir();
        settings.setInputPath(Paths.get(resourcesDir));
        settings.setOutputPath(Paths.get(resourcesDir + outName));

        UsernameFilterSetting us = new UsernameFilterSetting();
        us.setUserName("user1");
        us.setUsed(true);
        settings.setFilterSetting(new BaseFilterSetting[] { us });

        settings.setThreadsCount(threadsCount);

        GroupingSettings groupingSettings = new GroupingSettings();
        groupingSettings.setGroupByDate(DateTimeRoundingType.MONTH);
        settings.setGroupingSettings(groupingSettings);

        Application application = new Application();
        application.invoke(settings);
    }


    private static String getResourcesDir() {
        return System.getProperty("user.dir") + "/src/main/resources";
    }
}
