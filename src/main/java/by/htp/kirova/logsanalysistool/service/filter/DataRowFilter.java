package by.htp.kirova.logsanalysistool.service.filter;

import by.htp.kirova.logsanalysistool.data.DataRow;

/**
 * Interface for DataRowFilters.
 *
 * @author Kseniya Kirava
 * @since April 2, 2019
 */
public interface DataRowFilter {

    /**
     * Filter DataRow.
     *
     * @param dataRow line
     * @return {@code true} in case of success and false otherwise.
     */
    boolean filter(DataRow dataRow);
}
