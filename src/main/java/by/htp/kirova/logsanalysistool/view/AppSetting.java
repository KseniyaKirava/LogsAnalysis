package by.htp.kirova.logsanalysistool.view;

import by.htp.kirova.logsanalysistool.service.validation.Validator;
import by.htp.kirova.logsanalysistool.view.filter.BaseFilterSetting;
import by.htp.kirova.logsanalysistool.view.filter.MessageFilterSetting;
import by.htp.kirova.logsanalysistool.view.filter.TimePeriodFilterSetting;
import by.htp.kirova.logsanalysistool.view.filter.UsernameFilterSetting;
import by.htp.kirova.logsanalysistool.view.io.Printer;
import by.htp.kirova.logsanalysistool.view.io.Reader;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Settings of application.
 *
 * @author Kseniya Kirava
 * @since April 2, 2019
 */
public class AppSetting {

    /**
     * Input directory header constant.
     */
    private static final String INPUT_DIRECTORY_HEADER = "Input directory name: ";

    /**
     * Output directory header constant.
     */
    private static final String OUTPUT_DIRECTORY_HEADER = "Output directory name: ";

    /**
     * threads count header constant.
     */
    private static final String THREADS_COUNT_HEADER = "Threads count: ";

    /**
     * GroupingSettings
     */
    private GroupingSettings groupingSettings = new GroupingSettings();

    /**
     * Input path.
     */
    private Path inputPath;

    /**
     * Output path.
     */
    private Path outputPath;

    /**
     * Threads count.
     */
    private int threadsCount;

    /**
     * BaseFilterSetting parameters
     */
    private BaseFilterSetting[] filterSetting = new BaseFilterSetting[]{
            new UsernameFilterSetting(),
            new MessageFilterSetting(),
            new TimePeriodFilterSetting()};

    /**
     * Total steps count.
     */
    private int totalStepsCount = 4 + filterSetting.length;

    public Path getInputPath() {
        return inputPath;
    }

    public Path getOutputPath() {
        return outputPath;
    }

    public int getThreadsCount() {
        return threadsCount;
    }

    public GroupingSettings getGroupingSettings() {
        return groupingSettings;
    }

    public void setGroupingSettings(GroupingSettings groupingSettings) {
        this.groupingSettings = groupingSettings;
    }

    public void setInputPath(Path inputPath) {
        this.inputPath = inputPath;
    }

    public void setOutputPath(Path outputPath) {
        this.outputPath = outputPath;
    }

    public void setThreadsCount(int threadsCount) {
        this.threadsCount = threadsCount;
    }

    public void setFilterSetting(BaseFilterSetting[] filterSetting) {
        this.filterSetting = filterSetting;
    }

    /**
     * Initialization of settings.
     */
    public void init() {
        int step = 1;

        printStep(step++);
        initInputPath();

        printStep(step++);
        initOutputPath();

        printStep(step++);
        initThreadCount();


        for (BaseFilterSetting filter : filterSetting) {
            printStep(step++);
            filter.init();
        }

        printStep(step);
        groupingSettings.init();
    }

    /**
     * Initialization of input path settings.
     */
    private void initInputPath() {
        inputPath = Reader.getInstance().read(INPUT_DIRECTORY_HEADER,
                null, s -> Paths.get(s),
                path -> Validator.getInstance().checkPathReadable(path));
    }

    /**
     * Initialization of output path settings.
     */
    private void initOutputPath() {
        outputPath = Reader.getInstance().read(OUTPUT_DIRECTORY_HEADER,
                null, s -> Paths.get(s));
    }

    /**
     * Initialization of thread count settings.
     */
    private void initThreadCount() {
        threadsCount = Reader.getInstance().read(THREADS_COUNT_HEADER,
                s -> Validator.getInstance().checkThreadsCount(s), Integer::parseInt);
    }

    /**
     * Step printer.
     * @param step count
     */
    private void printStep(int step) {
        Printer.getInstance().printMessage(String.format("Step %d of %d", step, totalStepsCount));
    }

    /**
     * Getting of used filter settings.
     *
     * @return list of BaseFilterSetting
     */
    public List<BaseFilterSetting> getUsedFilterSettings() {
        List<BaseFilterSetting> result = new ArrayList<>();
        for (BaseFilterSetting setting : filterSetting) {
            if (setting.getIsUsed()) {
                result.add(setting);
            }
        }
        return result;
    }
}
