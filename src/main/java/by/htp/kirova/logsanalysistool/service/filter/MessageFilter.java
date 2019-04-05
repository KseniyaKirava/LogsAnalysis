package by.htp.kirova.logsanalysistool.service.filter;

import by.htp.kirova.logsanalysistool.data.DataRow;
import by.htp.kirova.logsanalysistool.view.filter.MessageFilterSetting;

/**
 * DataRow filter by Message.
 *
 * @author Kseniya Kirava
 * @since April 2, 2019
 */
public class MessageFilter extends SettingsBasedFilter<MessageFilterSetting> {

    MessageFilter(MessageFilterSetting settings) {
        super(settings);
    }

    @Override
    public boolean filter(DataRow dataRow) {
        String message = dataRow.getMessage();
        return settings.getPattern().matcher(message).matches();
    }
}
