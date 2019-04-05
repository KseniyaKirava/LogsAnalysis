package by.htp.kirova.logsanalysistool.service;

import by.htp.kirova.logsanalysistool.data.DataRow;
import by.htp.kirova.logsanalysistool.service.grouping.GroupingKey;
import by.htp.kirova.logsanalysistool.view.GroupingSettings;
import by.htp.kirova.logsanalysistool.view.io.Printer;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Utility class for creation statistics.
 *
 * @author Kseniya Kirava
 * @since April 4, 2019
 */
public class Statistic {

    /**
     * Create statistics based on grouping settings
     *
     * @param dataRowList list of DataRow
     * @param groupingSettings settings for grouping
     */
    public void create(List<DataRow> dataRowList, GroupingSettings groupingSettings){
        //grouping:
        Map<GroupingKey, Long> grouped = dataRowList.stream()
                .collect(Collectors.groupingBy(
                        dataRow -> new GroupingKey(dataRow, groupingSettings),//key
                        Collectors.counting()));//value

        //sorting map by key:
        Stream<Map.Entry<GroupingKey, Long>> sorted = grouped.entrySet().stream()
                .sorted(Map.Entry.comparingByKey(GroupingKey::compareTo));

        //printing:
        sorted.forEach(entry -> Printer.getInstance().printMessage(
                String.format("%s\t%s", entry.getKey(), entry.getValue())));
    }
}
