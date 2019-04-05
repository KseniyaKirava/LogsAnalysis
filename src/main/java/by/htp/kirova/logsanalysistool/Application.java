package by.htp.kirova.logsanalysistool;

import by.htp.kirova.logsanalysistool.data.DataRow;
import by.htp.kirova.logsanalysistool.service.DataRowsPrinter;
import by.htp.kirova.logsanalysistool.service.LogFilter;
import by.htp.kirova.logsanalysistool.service.Statistic;
import by.htp.kirova.logsanalysistool.service.filter.DataRowFilter;
import by.htp.kirova.logsanalysistool.service.filter.DataRowFiltersFactory;
import by.htp.kirova.logsanalysistool.view.AppSetting;
import by.htp.kirova.logsanalysistool.view.io.Printer;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

/**
 * Read user's data from console.
 *
 * @author Kseniya Kirava
 * @since April 2, 2019
 */
class Application {

    /**
     * Invocation application settings.
     *
     * @param settings application settings
     */
    void invoke(AppSetting settings) {

        List<DataRowFilter> filters = DataRowFiltersFactory.getInstance()
                .create(settings.getUsedFilterSettings());

        List<LogFilter> processors;
        try {
            processors = LogFilter.filter(filters, settings.getInputPath(), settings.getThreadsCount());
        } catch (IOException | InterruptedException | ExecutionException e) {
            Printer.getInstance().printError(e);
            return;
        }

        List<DataRow> filteredDataRows = processors.stream()
                .map(LogFilter::getFilteredDataRows)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        new DataRowsPrinter().print(filteredDataRows, settings.getOutputPath());

        new Statistic().create(filteredDataRows, settings.getGroupingSettings());

    }
}
