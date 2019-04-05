package by.htp.kirova.logsanalysistool.data;

import by.htp.kirova.logsanalysistool.service.util.Parser;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

/**
 * Data model for Log line.
 *
 * @author Kseniya Kirava
 * @since April 3, 2019
 */
public class DataRow {

    /**
     * Delimiter for CSV file format.
     */
    private static final Pattern commaPattern = Pattern.compile(";");

    /**
     * Username.
     */
    private String username;

    /**
     * Date of creation of log message.
     */
    private LocalDateTime date;

    /**
     * Log message.
     */
    private String message;

    public String getUsername() {
        return username;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }

    /**
     * Parsing CSV line to DataRow.
     *
     * @param string CSV line
     * @return DataRow
     */
    public static DataRow parse(String string){
        String[] splitResult = commaPattern.split(string, 3);
        DataRow dataRow = new DataRow();
        dataRow.username = splitResult[0];
        dataRow.date = Parser.getInstance().parseDateTime(splitResult[1]);
        dataRow.message = splitResult[2];
        return dataRow;
    }

    @Override
    public String toString() {
        String dateString = date.format(Parser.getInstance().getDateTimeFormatter());
        return username + ";" + dateString + ";" + message;
    }
}
