package by.htp.kirova.logsanalysistool.view.filter;


import by.htp.kirova.logsanalysistool.service.validation.Validator;
import by.htp.kirova.logsanalysistool.view.io.Reader;

/**
 *
 * @author Kseniya Kirava
 * @since April 2, 2019
 */
public class UsernameFilterSetting extends BaseFilterSetting {

    /**
     * The message about username pattern constant.
     */
    private static final String USERNAME_PATTERN_MSG = "Exact username: ";

    /**
     * The header of username filter.
     */
    private static final String USERNAME_FILTER_HEADER = "Username filter ";

    /**
     * Username filter.
     */
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    protected String getHeader() {
        return USERNAME_FILTER_HEADER;
    }

    @Override
    protected void initImpl() {
        userName = Reader.getInstance().read(USERNAME_PATTERN_MSG,
                s -> Validator.getInstance().checkUsername(s),
                s -> s);
    }



}
