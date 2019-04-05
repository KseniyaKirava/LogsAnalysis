package by.htp.kirova.logsanalysistool.view.filter;

import by.htp.kirova.logsanalysistool.service.util.Parser;
import by.htp.kirova.logsanalysistool.service.validation.Validator;
import by.htp.kirova.logsanalysistool.view.io.Printer;
import by.htp.kirova.logsanalysistool.view.io.Reader;

import java.time.LocalDateTime;

/**
 *
 * @author Kseniya Kirava
 * @since April 2, 2019
 */
public class TimePeriodFilterSetting extends BaseFilterSetting {

    /**
     * The message about start date constant.
     */
    private static final String START_DATE_MSG = String.format("Start date (%s):", Parser.DATE_FORMAT);

    /**
     * The message about end date constant.
     */
    private static final String END_DATE_MSG = String.format("End date (%s):", Parser.DATE_FORMAT);

    /**
     * The message about invalid datetime period.
     */
    private static final String INVALID_DATETIME_PERIOD_MSG = "WARNING! Invalid DateTime period. Start date can not precede end date. " +
            "Please, try again.";

    /**
     * The header of time period filter.
     */
    private static final String TIME_PERIOD_FILTER_HEADER = "Time period filter ";

    /**
     * Start date of period filter.
     */
    private LocalDateTime startDate;

    /**
     * End date of period filter.
     */
    private LocalDateTime endDate;

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    @Override
    protected void initImpl() {
        while (true) {
            startDate = Reader.getInstance().read(START_DATE_MSG,
                    s -> Validator.getInstance().checkDate(s),
                    //we use parseDate(s).atStartOfDay() instead of parseDateTime(s) to simplify input
                    s -> Parser.getInstance().parseDate(s).atStartOfDay());

            endDate = Reader.getInstance().read(END_DATE_MSG,
                    s -> Validator.getInstance().checkDate(s),
                    //we use parseDate(s).atStartOfDay() instead of parseDateTime(s) to simplify input
                    s -> Parser.getInstance().parseDate(s).atStartOfDay());

            if (Validator.getInstance().checkTimePeriod(startDate, endDate)) {
                return;
            } else {
                Printer.getInstance().printError(INVALID_DATETIME_PERIOD_MSG);
            }
        }
    }

    @Override
    protected String getHeader() {
        return TIME_PERIOD_FILTER_HEADER;
    }
}
