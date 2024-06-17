package util.psql;
import java.io.IOException;
import data_models.BLS_Data_Models.labor_data;
import data_models.Config_Models.ConfigPOJO;
import util.config.LaborDataRepository;
import util.config.Manager;
import util.config.PersistenceXmlUpdater;


import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class BLSDataChecker {

    public static void main(String[] args) throws IOException {
        String configFilePath = "src/main/java/config.json";
        String persistenceXmlFilePath = "src/main/resources/META-INF/persistence.xml";

        // Load configuration
        Manager.loadConfig(configFilePath);
        ConfigPOJO appConfig = Manager.getConfig();

        // Update persistence.xml
        PersistenceXmlUpdater.updatePersistenceXml(appConfig, persistenceXmlFilePath);

        // Use the repository
        String seriesId = "LNU00000062";
        int startYear = 2020;
        int endYear = 2024;

        LaborDataRepository repository = new LaborDataRepository();
        List<labor_data> sdl = repository.findDataBySeriesIdAndYearRange(seriesId, startYear, endYear);
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
        boolean result = DatabaseHelper.checkForNullPeriods(sdl);
        System.out.println("Are there null periods before the current period? " + result);

        repository.close();
    }

    public static class DatabaseHelper {

        public static boolean checkForNullPeriods(List<labor_data> sdl) {
            LocalDate currentDate = LocalDate.now();
            long currentYear = currentDate.getYear();
            int currentMonth = currentDate.getMonthValue();
            int currentQuarter = (currentMonth - 1) / 3 + 1;
            System.out.println("Current year: " + currentYear);
            System.out.println("Current Month: " + currentMonth);
            System.out.println("Current Quarter: " + currentQuarter);
            for (labor_data sd : sdl) {
//                if (sd.getYear() == currentYear) {
                    String periodType = sd.getPeriod();
                    System.out.println("Checking series: " + sd.getSeriesId() + " for year: " + sd.getYear() + " with period type: " + periodType);
                    if (periodType.equalsIgnoreCase("Monthly")) {
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
//                }
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
}
