package by.htp.kirova.logsanalysistool.view.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.function.Function;

/**
 * Read user's data from console.
 *
 * @author Kseniya Kirava
 * @since April 2, 2019
 */
public class Reader {

    /**
     * The message of validation with error constant.
     */
    private static final String ERROR_VALIDATION_MSG = "Validation error";

    /**
     * Instance of Reader.
     */
    private static final Reader instance = new Reader();

    /**
     * Default private constructor as this class entirely utility.
     */
    private Reader() {
    }

    public static Reader getInstance() {
        return instance;
    }

    /**
     * Read application settings from console.
     *
     * @param header filter header
     * @param stringValidator validator of String data
     * @param parser parser of String data
     * @param <T> output datatype
     * @return T data
     */
    public <T> T read(String header,
                      Function<String, Boolean> stringValidator,
                      Function<String, T> parser){
        return read(header, stringValidator, parser, null);
    }

    /**
     * Read application settings from console.
     *
     * @param header filter header
     * @param parser parser of String data
     * @param <T> output datatype
     * @return T data
     */
    public <T> T read(String header,
                      Function<String, T> parser){
        return read(header, null, parser, null);
    }

    /**
     * Read application settings from console.
     *
     * @param header filter header
     * @param stringValidator validator of String data
     * @param parser parser of String data
     * @param dataValidator validator of data
     * @param <T> output datatype
     * @return T data
     */
    public <T> T read(String header,
                      Function<String, Boolean> stringValidator,
                      Function<String, T> parser,
                      Function<T, Boolean> dataValidator) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            Printer.getInstance().printMessage(header);

            //read user input
            String stringValue = null;
            try {
                stringValue = reader.readLine();
            } catch (IOException e) {
                Printer.getInstance().printError(e);
            }

            //string validation
            if (stringValidator == null || stringValidator.apply(stringValue)) {

                //parsing:
                T result;
                try {
                    result = parser.apply(stringValue);
                } catch (Exception e) {
                    Printer.getInstance().printError(e);
                    continue;
                }

                //data validation
                if(dataValidator == null || dataValidator.apply(result)){
                    return result;
                }
                else{
                    Printer.getInstance().printError(ERROR_VALIDATION_MSG);
                }

            } else {
                Printer.getInstance().printError(ERROR_VALIDATION_MSG);
            }

        }
    }


}
