package by.htp.kirova.logsanalysistool.service.filter;

import by.htp.kirova.logsanalysistool.data.DataRow;
import by.htp.kirova.logsanalysistool.view.filter.TimePeriodFilterSetting;

import java.time.LocalDateTime;

/**
 * DataRow filter by TimePeriod.
 *
 * @author Kseniya Kirava
 * @since April 2, 2019
 */
public class TimePeriodFilter extends SettingsBasedFilter<TimePeriodFilterSetting> {

    TimePeriodFilter(TimePeriodFilterSetting settings) {
        super(settings);
    }

    @Override
    public boolean filter(DataRow dataRow) {
        LocalDateTime date = dataRow.getDate();
        LocalDateTime startDate = settings.getStartDate();
        LocalDateTime endDate = settings.getEndDate();
        return (date.isAfter(startDate) || date.equals(startDate)) &&
                (date.isBefore(endDate) || date.equals(endDate));

    }
}
