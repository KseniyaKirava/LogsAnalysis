package by.htp.kirova.logsanalysistool.service.validation;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;

/**
 * Validates settings data by checking it against predefined patterns.
 * Utility class, therefore final as it's not designed for instantiation and extension.
 *
 * @author Kseniya Kirava
 * @since April 2, 2019
 */
public final class Validator {

    /**
     * Instance of Validator is used for data validation.
     */
    private static final Validator instance = new Validator();

    /**
     * Default private constructor as this class entirely utility.
     */
    private Validator() {
    }

    public static Validator getInstance() {
        return instance;
    }


    /**
     * Checks the username against username pattern.
     *
     * @param username username.
     * @return {@code true} in case of success and false otherwise.
     */
    public boolean checkUsername(String username) {
        return username != null && ValidationPattern.USERNAME.getPattern().matcher(username).matches();
    }


    /**
     * Checks the date against date pattern.
     *
     * @param date date in String format
     * @return {@code true} in case of success and false otherwise.
     */
    public boolean checkDate(String date) {
        return date != null && ValidationPattern.DATE.getPattern().matcher(date).matches();
    }

    /**
     * Checks the time period.
     * Start date can not precede end date.
     *
     * @param startDate start date of time period
     * @param endDate   end date of time period
     * @return {@code true} in case of success and false otherwise.
     */
    public boolean checkTimePeriod(LocalDateTime startDate, LocalDateTime endDate) {
        return startDate.isBefore(endDate) || startDate.equals(endDate);
    }

    /**
     * Check that path is readable directory.
     *
     * @param path to directory
     * @return {@code true} in case of success and false otherwise.
     */
    public boolean checkPathReadable(Path path) {
        return Files.isDirectory(path) && Files.isReadable(path);
    }

    /**
     * Check that threads count validate.
     *
     * @param count of threads
     * @return {@code true} in case of success and false otherwise.
     */
    public boolean checkThreadsCount(String count) {
        return count != null && ValidationPattern.THREADS_COUNT.getPattern().matcher(count).matches();
    }


}



