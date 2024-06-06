package util.psql;

import data_models.BLS_Data_Models.labor_data;
import data_models.Config_Models.ConfigPOJO;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static util.config.Manager.getConfig;
import static util.config.Manager.loadConfig;
import static util.psql.BLSDataChecker.DatabaseHelper.checkForNullPeriods;


public class BLSDataChecker {

    public static void main(String[] args) throws SQLException {
        String seriedID = "SMS56000000000000001";
        int startYear = 2020;
        int endYear = 2024;
         new DatabaseHelper();
        List<labor_data> sdl = getSeriesData(seriedID, startYear, endYear);
        System.out.println(String.format(
                "%-10s %-5s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s",
                "SeriesID", "Year", "Period1", "Period2", "Period3", "Period4", "Period5", "Period6", "Period7", "Period8", "Period9", "Period10", "Period11", "Period12"
        ));
        for (labor_data sd : sdl) {
            System.out.println(String.format(
                    "%s %d %s %s %s %s %s %s %s %s %s %s %s %s",
                    sd.getSeriesId(),
                    sd.getYear(),
                    sd.getPeriod(),
                    sd.getPeriod1(),
                    sd.getPeriod2(),
                    sd.getPeriod3(),
                    sd.getPeriod4(),
                    sd.getPeriod5(),
                    sd.getPeriod6(),
                    sd.getPeriod7(),
                    sd.getPeriod8(),
                    sd.getPeriod9(),
                    sd.getPeriod10(),
                    sd.getPeriod11(),
                    sd.getPeriod12()
            ));
        }
        // Check the current year's data
        boolean result = checkForNullPeriods(sdl);
        System.out.println("Are there null periods before the current period? " + result);

    }


    public static class DatabaseHelper {

        static {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }


        public static boolean checkForNullPeriods(List<labor_data> sdl) {
            LocalDate currentDate = LocalDate.now();
            long currentYear = currentDate.getYear();
            int currentMonth = currentDate.getMonthValue();
            int currentQuarter = (currentMonth - 1) / 3 + 1;
            System.out.println("Current year: " + currentYear);
            System.out.println("Current Month: " + currentMonth);
            System.out.println("Current Quarter: " + currentQuarter);
            for (labor_data sd : sdl) {
                if (sd.getYear() == currentYear) {
                    String periodType = sd.getPeriod();
                    System.out.println("Checking series: " + sd.getSeriesId() + " for year: " + sd.getYear() + " with period type: " + periodType);
                    if (periodType.equalsIgnoreCase("Monthly")) {
                        System.out.println("did you get here 1");
                        for (int i = 1; i <= currentMonth; i++) {
                            if (getPeriodValue(sd, i) == null) {
                                System.out.println("Found null in month: " + i);
                                return true;
                            }
                        }
                    } else if (periodType.equalsIgnoreCase("Quarterly")) {
                        for (int i = 1; i <= currentQuarter; i++) {
                            if (getPeriodValue(sd, i) == null) {
                                System.out.println("Found null in quarter: " + i);
                                return true;
                            }
                        }
                    }
                }
            }
            return false;
        }

        private static String getPeriodValue(labor_data sd, int period) {
            switch (period) {
                case 1: return sd.getPeriod1();
                case 2: return sd.getPeriod2();
                case 3: return sd.getPeriod3();
                case 4: return sd.getPeriod4();
                case 5: return sd.getPeriod5();
                case 6: return sd.getPeriod6();
                case 7: return sd.getPeriod7();
                case 8: return sd.getPeriod8();
                case 9: return sd.getPeriod9();
                case 10: return sd.getPeriod10();
                case 11: return sd.getPeriod11();
                case 12: return sd.getPeriod12();
                default: return null;
            }
        }
    }


        private static Connection getConnection() throws SQLException {
            loadConfig("src/main/java/config.json");
            ConfigPOJO appConfig = getConfig();
            return DriverManager.getConnection(
                    appConfig.getPostgreSQLConfig().getUrl(),
                    appConfig.getPostgreSQLConfig().getUser(),
                    appConfig.getPostgreSQLConfig().getPassword()
            );
        }

        public static List<labor_data> getSeriesData(String seriesId, int startYear, int endYear) throws SQLException {
            List<labor_data> dataList = new ArrayList<>();
            String query = "SELECT * FROM ln_labor_data WHERE TRIM(series_id) = ? AND year BETWEEN ? AND ?";
            try (Connection conn = getConnection();
                 PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, seriesId);
                stmt.setInt(2, startYear);
                stmt.setInt(3, endYear);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    labor_data lnLaborData = new labor_data(rs);
                    dataList.add(lnLaborData);
                }
            }
            return dataList;
        }

        private static void updateSeriesData(String seriesID, long year, Map<String, String> periodValues) throws SQLException {
//        String dbPrefix = "sm";
//            String query = "UPDATE "+ dbPrefix + "_labor_data SET period_1 = ?, period_2 = ?, period_3 = ?, period_4 = ?, period_5 = ?, " +
//                    "period_6 = ?, period_7 = ?, period_8 = ?, period_9 = ?, period_10 = ?, period_11 = ?, period_12 = ? WHERE series_id = ? AND year = ?";

            String query = "SELECT series_id, year, period, period_1, period_2, period_3, period_4, period_5, " +
                    "period_6, period_7, period_8, period_9, period_10, period_11, period_12 " +
                    "FROM sm_labor_data WHERE TRIM(series_id) = ? AND year BETWEEN ? AND ?";
            try (Connection conn = getConnection();
                 PreparedStatement stmt = conn.prepareStatement(query)) {
                for (int i = 1; i <= 12; i++) {
                    String monthKey = "M" + String.format("%02d", i);
                    String value = periodValues.getOrDefault(monthKey, "N/A");
                    stmt.setString(i, value);
                }
                stmt.setString(13, seriesID);
                stmt.setLong(14, year);

                stmt.executeUpdate();
            }
        }

    }