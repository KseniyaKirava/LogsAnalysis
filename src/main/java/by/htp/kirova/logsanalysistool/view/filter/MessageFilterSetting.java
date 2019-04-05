package by.htp.kirova.logsanalysistool.view.filter;

import by.htp.kirova.logsanalysistool.view.io.Reader;

import java.util.regex.Pattern;

/**
 *
 * @author Kseniya Kirava
 * @since April 2, 2019
 */
public class MessageFilterSetting extends BaseFilterSetting{

    /**
     * The message about message pattern constant.
     */
    private static final String MESSAGE_PATTERN_MSG = "Message regex: ";

    /**
     * The header of message filter.
     */
    private static final String MESSSAGE_FILTER_HEADER = "Message filter: ";

    /**
     * Pattern of message filter.
     */
    private Pattern pattern;

    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }

    public Pattern getPattern() {
        return pattern;
    }

    @Override
    protected String getHeader() {
        return MESSSAGE_FILTER_HEADER;
    }

    @Override
    protected void initImpl() {
        pattern = Reader.getInstance().read(MESSAGE_PATTERN_MSG, Pattern::compile);
    }
}
