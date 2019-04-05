package by.htp.kirova.logsanalysistool.service.filter;

import by.htp.kirova.logsanalysistool.view.filter.BaseFilterSetting;
import by.htp.kirova.logsanalysistool.view.filter.MessageFilterSetting;
import by.htp.kirova.logsanalysistool.view.filter.TimePeriodFilterSetting;
import by.htp.kirova.logsanalysistool.view.filter.UsernameFilterSetting;

import java.util.List;
import java.util.stream.Collectors;

/**
 * {@link DataRowFilter} factory
 *
 * @author Kseniya Kirava
 * @since April 2, 2019
 */
public class DataRowFiltersFactory {

    /**
     * The message for failure of data settings constant.
     */
    private static final String NO_DATA_SETTINGS_MSG = "No data in filter settings";

    /**
     * The message for unsupported type of filter settings constant.
     */
    private static final String UNSUPPORTED_SETTINGS_TYPE_MSG = "Unsupported type of filter settings";

    /**
     * Instance of DataRowFiltersFactory is used for DataRowFiltersFactory access.
     */
    private static final DataRowFiltersFactory instance = new DataRowFiltersFactory();

    /**
     * Default private constructor as this class entirely utility.
     */
    private DataRowFiltersFactory() {
    }

    public static DataRowFiltersFactory getInstance() {
        return instance;
    }


    /**
     * Create joint dataRowFilter from several filters.
     *
     * @param settings settings of the corresponding type.
     * @return joint filter for DataRow.
     */
    private DataRowFilter create(BaseFilterSetting settings) {
        if (settings == null) {
            throw new IllegalArgumentException(NO_DATA_SETTINGS_MSG);
        }
        if (settings instanceof UsernameFilterSetting) {
            return new UsernameFilter((UsernameFilterSetting) settings);
        } else if (settings instanceof TimePeriodFilterSetting) {
            return new TimePeriodFilter((TimePeriodFilterSetting) settings);
        } else if (settings instanceof MessageFilterSetting) {
            return new MessageFilter((MessageFilterSetting) settings);
        } else {
            throw new UnsupportedOperationException(UNSUPPORTED_SETTINGS_TYPE_MSG);
        }

    }

    /**
     * Create joint dataRowFilter from several filters.
     *
     * @param items settings of the corresponding type.
     * @return joint filter for DataRow.
     */
    public List<DataRowFilter> create(List<BaseFilterSetting> items){
        return items.stream()
                .map(this::create)
                .collect(Collectors.toList());
    }
}
