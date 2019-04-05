package by.htp.kirova.logsanalysistool.view;

import by.htp.kirova.logsanalysistool.service.util.Parser;
import by.htp.kirova.logsanalysistool.view.io.Printer;
import by.htp.kirova.logsanalysistool.view.io.Reader;

/**
 * Read user's data from console.
 *
 * @author Kseniya Kirava
 * @since April 2, 2019
 */
public class GroupingSettings {

    /**
     * Grouping setting header constant.
     */
    private static final String GROUPING_SETTINGS_HEADER = "Grouping settings: ";

    /**
     * Grouping by user header constant.
     */
    private static final String GROUP_BY_USER_HEADER = String.format("Grouping by user? [%s/%s]",
            Parser.YES_ANSWER, Parser.NO_ANSWER);

    /**
     * Grouping by date header constant.
     */
    private static final String GROUP_BY_DATE_HEADER = "Grouping by datetime [NO/HOUR/DAY/MONTH]: ";

    /**
     * Group by username.
     */
    private boolean groupByUsername = false;

    /**
     * Group by date.
     */
    private DateTimeRoundingType groupByDate = DateTimeRoundingType.NO;

    public boolean isGroupByUsername() {
        return groupByUsername;
    }

    public void setGroupByUsername(boolean groupByUsername) {
        this.groupByUsername = groupByUsername;
    }

    public DateTimeRoundingType getGroupByDate() {
        return groupByDate;
    }

    public void setGroupByDate(DateTimeRoundingType groupByDate) {
        this.groupByDate = groupByDate;
    }

    /**
     * Initialization grouping setting.
     */
    void init() {
        Printer.getInstance().printMessage(GROUPING_SETTINGS_HEADER);

        groupByUsername = Reader.getInstance().read(GROUP_BY_USER_HEADER,
                s -> Parser.getInstance().parseBoolean(s));

        groupByDate = Reader.getInstance().read(GROUP_BY_DATE_HEADER,
                s -> Parser.getInstance().parseDateTimeRoundingType(s));

    }
}


