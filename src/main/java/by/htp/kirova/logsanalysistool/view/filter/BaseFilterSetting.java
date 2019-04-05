package by.htp.kirova.logsanalysistool.view.filter;

import by.htp.kirova.logsanalysistool.service.util.Parser;
import by.htp.kirova.logsanalysistool.view.io.Printer;
import by.htp.kirova.logsanalysistool.view.io.Reader;

/**
 * Base class for initialization filter settings.
 *
 * @author Kseniya Kirava
 * @since April 2, 2019
 */
public abstract class BaseFilterSetting {

    /**
     * The message for using filter.
     */
    private static final String IS_USED_HEADER = String.format("Do you want to use this filter? [%s/%s]",
            Parser.YES_ANSWER, Parser.NO_ANSWER);


    /**
     * Field is used for saving flag, that filter should be used
     */
    private boolean isUsed;

    public boolean getIsUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }



    /**
     * Filter settings initialisation.
     */
    protected abstract void initImpl();

    /**
     * Receiving header's name.
     *
     * @return header name
     */
    protected abstract String getHeader();





    /**
     * Base method to communicate with user.
     * Asks user if he want to use this filter.
     */
    public void init() {
        Printer.getInstance().printMessage(getHeader());

        isUsed = Reader.getInstance().read(IS_USED_HEADER,
                s -> Parser.getInstance().parseBoolean(s));

        //if we use this filter we should init its parameters
        if (isUsed) {
            initImpl();
        }
    }

}
