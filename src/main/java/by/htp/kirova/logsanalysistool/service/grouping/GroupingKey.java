package by.htp.kirova.logsanalysistool.service.grouping;

import by.htp.kirova.logsanalysistool.data.DataRow;
import by.htp.kirova.logsanalysistool.service.util.Parser;
import by.htp.kirova.logsanalysistool.view.GroupingSettings;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

/**
 * DataRow grouping key.
 *
 * @author Kseniya Kirava
 * @since April 4, 2019
 */
public class GroupingKey implements Comparable<GroupingKey>{
    private String username;
    private LocalDateTime dateTime;

    public GroupingKey(DataRow dataRow, GroupingSettings settings) {
        if (settings.isGroupByUsername()) {
           username = dataRow.getUsername();
        }
        switch (settings.getGroupByDate()) {
            case NO:
                dateTime = null;
                break;
            case HOUR:
                dateTime = dataRow.getDate().truncatedTo(ChronoUnit.HOURS);
                break;
            case DAY:
                dateTime = dataRow.getDate().truncatedTo(ChronoUnit.DAYS);
                break;
            case MONTH:
                dateTime = dataRow.getDate().truncatedTo(ChronoUnit.DAYS).withDayOfMonth(1);
                break;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupingKey that = (GroupingKey) o;
        return Objects.equals(username, that.username) &&
                Objects.equals(dateTime, that.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, dateTime);
    }

    @Override
    public String toString() {
        String result = username;
        if (dateTime != null) {
            String dateString = dateTime.format(Parser.getInstance().getDateFormatter());
            if (result != null)
                result = dateString + " " + result;
            else
                result = dateString;
        }
        return result;
    }


    @Override
    public int compareTo(GroupingKey o) {
        if(o == null){
            return 1;
        }
        if(this.dateTime == null){
            if(o.dateTime != null){
                return -1;
            }
        }
        else  {
            int c = this.dateTime.compareTo(o.dateTime);
            if(c != 0)
                return c;
        }

        if(this.username == null) {
            return o.username == null ? 0 : -1;
        }
        return this.username.compareTo(o.username);
    }
}
