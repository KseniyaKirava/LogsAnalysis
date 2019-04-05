package by.htp.kirova.logsanalysistool.service.filter;

import by.htp.kirova.logsanalysistool.data.DataRow;
import by.htp.kirova.logsanalysistool.view.filter.UsernameFilterSetting;

/**
 * DataRow filter by Username.
 *
 * @author Kseniya Kirava
 * @since April 2, 2019
 */
public class UsernameFilter extends SettingsBasedFilter<UsernameFilterSetting> {

    UsernameFilter(UsernameFilterSetting settings) {
        super(settings);
    }

    @Override
    public boolean filter(DataRow dataRow) {
        return dataRow.getUsername().equals(settings.getUserName());
    }
}
