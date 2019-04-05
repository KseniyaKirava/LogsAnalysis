package by.htp.kirova.logsanalysistool.service.util;

import by.htp.kirova.logsanalysistool.view.DateTimeRoundingType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Convert String to relevant data types.
 * Utility class, therefore final as it's not designed for instantiation and extension.
 *
 * @author Kseniya Kirava
 * @since April 2, 2019
 */
public final class Parser {

    /**
     * The date format constant.
     */
    public static final String DATE_FORMAT = "dd.MM.yyyy";

    /**
     * The datetime constant.
     */
    private static final String DATE_TIME_FORMAT = "dd.MM.yyyy HH:mm:ss";

    /**
     * The message for illegal value constant.
     */
    private static final String ILLEGAL_VALUE_MSG = "Impossible to get a boolean from ";

    /**
     * The yes answer constant.
     */
    public static final String YES_ANSWER = "y";

    /**
     * The no answer constant.
     */
    public static final String NO_ANSWER = "n";

    /**
     * Instance of Parser.
     */
    private static final Parser instance = new Parser();

    /**
     * Default private constructor as this class entirely utility.
     */
    private Parser() {
    }

    public static Parser getInstance() {
        return instance;
    }

    /**
     * Date Formatter.
     */
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT);

    /**
     * Date/Time Formatter.
     */
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);

    public DateTimeFormatter getDateFormatter() {
        return dateFormatter;
    }

    public DateTimeFormatter getDateTimeFormatter() {
        return dateTimeFormatter;
    }

    /**
     * Parse the date from String format use DateTimeFormatter.
     *
     * @param date date in String format.
     * @return date in LocalDate format.
     */
    public LocalDate parseDate(String date){
        return LocalDate.parse(date, dateFormatter);
    }

    /**
     * Parse the date from String format use DateTimeFormatter.
     *
     * @param date date in String format.
     * @return date in LocalDateTime format.
     */
    public LocalDateTime parseDateTime(String date){
        return LocalDateTime.parse(date, dateTimeFormatter);
    }

    /**
     * Parse the user's answer from String format to boolean.
     *
     * @param string user's answer in String format.
     * @return user's answer in boolean format.
     */
    public boolean parseBoolean(String string){
        switch (string) {
            case YES_ANSWER:
                return true;
            case NO_ANSWER:
                return false;
            default:
                throw new IllegalArgumentException(ILLEGAL_VALUE_MSG + string);
        }
    }

    /**
     * Parse DateTImeRoundingType from String.
     *
     * @param string String
     * @return DateTimeRoundingType
     */
    public DateTimeRoundingType parseDateTimeRoundingType(String string){
        return DateTimeRoundingType.valueOf(string);
    }


}
