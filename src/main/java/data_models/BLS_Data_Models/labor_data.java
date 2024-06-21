package data_models.BLS_Data_Models;


import data_models.BLS_API_Models.Datum;
import data_models.BLS_API_Models.Series;
import jakarta.persistence.*;
import org.apache.poi.ss.usermodel.Row;
import series_id_utils.exel_decoders.LN_Decoder;
import series_id_utils.postgressql_decoders.LN_SeriesID_Decoder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static series_id_utils.exel_decoders.LN_Decoder.loadSeriesData;

@Entity
@Table(name = "labor_data")
public class labor_data {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // This will auto-generate the ID if you use a database that supports auto-increment

    @Column(name = "series_id")
    private String seriesId;

    @Column(name = "year")
    private Long year;

    @Column(name = "period")
    private String period;

    @Column(name = "period_1")
    private String period1;

    @Column(name = "period_2")
    private String period2;

    @Column(name = "period_3")
    private String period3;

    @Column(name = "period_4")
    private String period4;

    @Column(name = "period_5")
    private String period5;

    @Column(name = "period_6")
    private String period6;

    @Column(name = "period_7")
    private String period7;

    @Column(name = "period_8")
    private String period8;

    @Column(name = "period_9")
    private String period9;

    @Column(name = "period_10")
    private String period10;

    @Column(name = "period_11")
    private String period11;

    @Column(name = "period_12")
    private String period12;

    public labor_data() {

    }

    public String getSeriesId() {
        return this.seriesId;
    }

    public void setSeriesId(String seriesId) {
        this.seriesId = seriesId;
    }

    public Long getYear() {
        return this.year;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    public String getPeriod() {
        return this.period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getPeriod1() {
        return this.period1;
    }

    public void setPeriod1(String period1) {
        this.period1 = period1;
    }

    public String getPeriod2() {
        return this.period2;
    }

    public void setPeriod2(String period2) {
        this.period2 = period2;
    }

    public String getPeriod3() {
        return this.period3;
    }

    public void setPeriod3(String period3) {
        this.period3 = period3;
    }

    public String getPeriod4() {
        return this.period4;
    }

    public void setPeriod4(String period4) {
        this.period4 = period4;
    }

    public String getPeriod5() {
        return this.period5;
    }

    public void setPeriod5(String period5) {
        this.period5 = period5;
    }

    public String getPeriod6() {
        return this.period6;
    }

    public void setPeriod6(String period6) {
        this.period6 = period6;
    }

    public String getPeriod7() {
        return this.period7;
    }

    public void setPeriod7(String period7) {
        this.period7 = period7;
    }

    public String getPeriod8() {
        return this.period8;
    }

    public void setPeriod8(String period8) {
        this.period8 = period8;
    }

    public String getPeriod9() {
        return this.period9;
    }

    public void setPeriod9(String period9) {
        this.period9 = period9;
    }

    public String getPeriod10() {
        return this.period10;
    }

    public void setPeriod10(String period10) {
        this.period10 = period10;
    }

    public String getPeriod11() {
        return this.period11;
    }

    public void setPeriod11(String period11) {
        this.period11 = period11;
    }

    public String getPeriod12() {
        return this.period12;
    }

    public void setPeriod12(String period12) {
        this.period12 = period12;
    }

    public labor_data(Series series) {
        Map<String, Map<String, String>> seriesData = new HashMap<>();
        for (Datum dataPoint : series.getData()) {
            String key = series.getSeriesID() + "-" + dataPoint.getYear();
            if (!seriesData.containsKey(key)) {
                seriesData.put(key, new HashMap<>());
            }
            seriesData.get(key).put(dataPoint.getPeriod(), dataPoint.getValue());
        }
        for (String key : seriesData.keySet()) {

            String[] parts = key.split("-");
            this.seriesId = parts[0];
            this.year = Long.valueOf(parts[1]);

            Map<String, String> periodValues = seriesData.get(key);
            for (int i = 1; i <= 12; i++) {
                String periodKey = "";
                if (seriesData.get(key).toString().contains("Q")) {
                    this.period = "Quarterly";
                    periodKey = "Q" + String.format("%02d", i);
                } else if (seriesData.get(key).toString().contains("M")) {
                    this.period = ("Monthly");
                    periodKey = "M" + String.format("%02d", i);
                }
                switch (i) {
                    case 1:
                        this.period1 = periodValues.getOrDefault(periodKey, "");
                        break;
                    case 2:
                        this.period2 = periodValues.getOrDefault(periodKey, "");
                        break;
                    case 3:
                        this.period3 = periodValues.getOrDefault(periodKey, "");
                        break;
                    case 4:
                        this.period4 = periodValues.getOrDefault(periodKey, "");
                        break;
                    case 5:
                        this.period5 = periodValues.getOrDefault(periodKey, "");
                        break;
                    case 6:
                        this.period6 = periodValues.getOrDefault(periodKey, "");
                        break;
                    case 7:
                        this.period7 = periodValues.getOrDefault(periodKey, "");
                        break;
                    case 8:
                        this.period8 = periodValues.getOrDefault(periodKey, "");
                        break;
                    case 9:
                        this.period9 = periodValues.getOrDefault(periodKey, "");
                        break;
                    case 10:
                        this.period10 = periodValues.getOrDefault(periodKey, "");
                        break;
                    case 11:
                        this.period11 = periodValues.getOrDefault(periodKey, "");
                        break;
                    case 12:
                        this.period12 = periodValues.getOrDefault(periodKey, "");
                        break;
                    default:
                        break;
                }
            }
        }
        }
    public labor_data(ResultSet resultSet) throws SQLException {
        this.seriesId = resultSet.getString("series_id");
        this.year = resultSet.getLong("year");
        this.period = resultSet.getString("period");
        this.period1 = resultSet.getString("period_1");
        this.period2 = resultSet.getString("period_2");
        this.period3 = resultSet.getString("period_3");
        this.period4 = resultSet.getString("period_4");
        this.period5 = resultSet.getString("period_5");
        this.period6 = resultSet.getString("period_6");
        this.period7 = resultSet.getString("period_7");
        this.period8 = resultSet.getString("period_8");
        this.period9 = resultSet.getString("period_9");
        this.period10 = resultSet.getString("period_10");
        this.period11 = resultSet.getString("period_11");
        this.period12 = resultSet.getString("period_12");
    }

    }