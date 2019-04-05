package by.htp.kirova.logsanalysistool.service;

import by.htp.kirova.logsanalysistool.data.DataRow;
import by.htp.kirova.logsanalysistool.service.filter.DataRowFilter;
import by.htp.kirova.logsanalysistool.view.io.Printer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * log file filter
 *
 * @author Kseniya Kirava
 * @since April 2, 2019
 */
public class LogFilter {

    /**
     * List of filtered DataRows.
     */
    private List<DataRow> filteredDataRows;

    public List<DataRow> getFilteredDataRows() {
        return filteredDataRows;
    }

    /**
     * Single file filter
     *
     * @param filters List of DataRow filter
     * @param inputFile path
     */
    public void filter(List<DataRowFilter> filters, Path inputFile) {
        try (Stream<String> lineStream = Files.lines(inputFile)) {
            filteredDataRows = lineStream
                    .map(DataRow::parse)
                    .filter(dataRow -> filters.stream().allMatch(f -> f.filter(dataRow)))
                    .collect(Collectors.toList());

        } catch (IOException e) {
            Printer.getInstance().printError(e);
        }
    }

    /**
     * Multi-threads log file filter
     *
     * @param filters List of DataRow filter
     * @param directory path
     * @param threadsCount count of threads
     * @return list of LogFilter
     * @throws ExecutionException exception
     * @throws InterruptedException exception
     * @throws IOException exception
     */
    public static List<LogFilter> filter(List<DataRowFilter> filters, Path directory, int threadsCount)
            throws ExecutionException, InterruptedException, IOException {
        List<Path> filePaths = Files.list(directory)
                .filter(Files::isRegularFile)
                .collect(Collectors.toList());

        ForkJoinPool customThreadPool = new ForkJoinPool(threadsCount);
        return customThreadPool.submit(() ->
                filePaths.parallelStream()
                        .map(filePath -> {
                            LogFilter processor = new LogFilter();
                            processor.filter(filters, filePath);
                            return processor;
                        }).collect(Collectors.toList())
        ).get();
    }
}
