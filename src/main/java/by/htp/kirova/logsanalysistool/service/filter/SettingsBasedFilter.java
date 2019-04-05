package by.htp.kirova.logsanalysistool.service.filter;

import by.htp.kirova.logsanalysistool.data.DataRow;
import by.htp.kirova.logsanalysistool.view.filter.BaseFilterSetting;

/**
 * Base implementation of {@link DataRowFilter} based on {@link BaseFilterSetting}
 *
 * @author Kseniya Kirava
 * @since April 2, 2019
 */
public abstract class SettingsBasedFilter<T extends BaseFilterSetting> implements DataRowFilter {

    /**
     * Settings field with type T.
     */
    protected T settings;

    SettingsBasedFilter(T settings) {
        this.settings = settings;
    }

    /**
     * Filter DataRow according to settings.
     *
     * @param dataRow line
     * @return {@code true} in case of success and false otherwise.
     */
    public abstract boolean filter(DataRow dataRow);

}
