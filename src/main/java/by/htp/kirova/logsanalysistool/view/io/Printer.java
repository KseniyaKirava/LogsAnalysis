package by.htp.kirova.logsanalysistool.view.io;

/**
 * Utility class for printing data.
 *
 * @author Kseniya Kirava
 * @since April 2, 2019
 */
public class Printer {

    /**
     * Instance of Printer.
     */
    private static volatile Printer instance;

    public static Printer getInstance() {
        Printer localInstance = instance;
        if (localInstance == null) {
            synchronized (Printer.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new Printer() {
                    };
                }
            }
        }
        return localInstance;
    }

    /**
     * Default private constructor as this class entirely utility.
     */
    private Printer() {
    }

    /**
     * Printing message.
     *
     * @param message message fot printing
     */
    public void printMessage(String message) {
        System.out.println(message);
    }

    /**
     * Printing custom error message.
     *
     * @param message  message fot printing
     */
    public void printError(String message) {
        System.err.println(message);
    }

    /**
     * Printing error.
     *
     * @param e name of error for printing
     */
    public void printError(Exception e) {
        System.err.println(e.toString());
    }
}
