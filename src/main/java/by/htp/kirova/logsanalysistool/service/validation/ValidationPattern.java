package by.htp.kirova.logsanalysistool.service.validation;

import java.util.regex.Pattern;

/**
 * Contains precompiled patterns for validating settings.
 *
 * @author Kseniya Kirava
 * @since April 2, 2019
 */
enum ValidationPattern {

    /**
     * Username pattern.
     */
    USERNAME("[A-Za-z._-]{2,15}"),

    THREADS_COUNT("[1-9]{1}[0-9]{0,1}"),

    /**
     * Pattern for date formats: dd/MM/yyyy, dd-MM-yyyy, dd.MM.yyyy
     */
    DATE("^(?:(?:31" +
            "(\\/|-|\\.)" +
            "(?:0?[13578]|1[02]))\\1|(?:(?:29|30)" +
            "(\\/|-|\\.)" +
            "(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29" +
            "(\\/|-|\\.)" +
            "0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|" +
            "[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])" +
            "(\\/|-|\\.)" +
            "(?:(?:0?[1-9])|" +
            "(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$");

    /**
     * Pattern field.
     */
    private Pattern pattern;

    ValidationPattern(String regEx){
        this.pattern = Pattern.compile(regEx);
    }

    public Pattern getPattern() {
        return pattern;
    }
}


