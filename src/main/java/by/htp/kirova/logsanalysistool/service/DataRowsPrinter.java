package by.htp.kirova.logsanalysistool.service;

import by.htp.kirova.logsanalysistool.data.DataRow;
import by.htp.kirova.logsanalysistool.view.io.Printer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

/**
 * Utility class for print DataRows to file.
 *
 * @author Kseniya Kirava
 * @since April 4, 2019
 */
public class DataRowsPrinter {

    /**
     * Printing DataRow to file.
     *
     * @param dataRows list of DataRow
     * @param outputPath path for output
     */
    public void print(List<DataRow> dataRows, Path outputPath){
        try {
            Stream<String> localStream = dataRows.stream().map(DataRow::toString);
            Path parentDir = outputPath.getParent();
            if (!Files.exists(parentDir))
                Files.createDirectories(parentDir);
            Files.write(outputPath, (Iterable<String>) localStream::iterator);
        } catch (IOException e) {
            Printer.getInstance().printError(e);
        }

    }
}
