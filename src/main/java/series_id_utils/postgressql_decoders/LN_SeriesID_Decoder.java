package series_id_utils.postgressql_decoders;
import data_models.BLS_Data_Models.lnSeriesIDData;
import data_models.Config_Models.ConfigPOJO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static util.config.Manager.getConfig;


public class LN_SeriesID_Decoder {

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static lnSeriesIDData decodeSeriesID(String seriesIdToFind) {
        lnSeriesIDData data = null;
        ConfigPOJO appConfig = getConfig();

        try (Connection conn = getConnection(appConfig);
             PreparedStatement stmt = createPreparedStatement(conn, seriesIdToFind);
             ResultSet resultSet = stmt.executeQuery()) {

            if (resultSet.next()) {
                data = mapResultSetToData(resultSet);
                System.out.println("Retrieved data for Series ID: " + data.getSeriesId());
            } else {
                System.out.println("No records found matching the series ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
    }

    private static Connection getConnection(ConfigPOJO appConfig) throws SQLException {
        return DriverManager.getConnection(
                appConfig.getPostgreSQLConfig().getUrl(),
                appConfig.getPostgreSQLConfig().getUser(),
                appConfig.getPostgreSQLConfig().getPassword()
        );
    }

    private static PreparedStatement createPreparedStatement(Connection conn, String seriesIdToFind) throws SQLException {
        String query = "SELECT * FROM ln_decoder_file WHERE TRIM(series_id) = TRIM(?)";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, seriesIdToFind);
        return stmt;
    }

    private static lnSeriesIDData mapResultSetToData(ResultSet resultSet) throws SQLException {
        lnSeriesIDData data = new lnSeriesIDData();
        data.setSeriesId(resultSet.getString("series_id"));
        data.setLfstCode(resultSet.getString("lfstCode"));
        data.setPeriodicityCode(resultSet.getString("periodicityCode"));
        data.setPeriodicityText(resultSet.getString("periodicityText"));
        data.setSeriesTitle(resultSet.getString("seriesTitle"));
        data.setAbsnCode(resultSet.getString("absnCode"));
        data.setAbsnText(resultSet.getString("absnText"));
        data.setActivityCode(resultSet.getString("activityCode"));
        data.setActivityText(resultSet.getString("activityText"));
        data.setAgesCode(resultSet.getString("agesCode"));
        data.setAgesText(resultSet.getString("agesText"));
        data.setCertCode(resultSet.getString("certCode"));
        data.setCertText(resultSet.getString("certText"));
        data.setClassCode(resultSet.getString("classCode"));
        data.setClassText(resultSet.getString("classText"));
        data.setDurationCode(resultSet.getString("durationCode"));
        data.setDurationText(resultSet.getString("durationText"));
        data.setEducationCode(resultSet.getString("educationCode"));
        data.setEducationText(resultSet.getString("educationText"));
        data.setEntrCode(resultSet.getString("entrCode"));
        data.setEntrText(resultSet.getString("entrText"));
        data.setExprCode(resultSet.getString("exprCode"));
        data.setExprText(resultSet.getString("exprText"));
        data.setHheaderCode(resultSet.getString("hheaderCode"));
        data.setHheaderText(resultSet.getString("hheaderText"));
        data.setHourCode(resultSet.getString("hourCode"));
        data.setHourText(resultSet.getString("hourText"));
        data.setIndyCode(resultSet.getString("indyCode"));
        data.setIndyText(resultSet.getString("indyText"));
        data.setJdesCode(resultSet.getString("jdesCode"));
        data.setJdesText(resultSet.getString("jdesText"));
        data.setLookCode(resultSet.getString("lookCode"));
        data.setLookText(resultSet.getString("lookText"));
        data.setMariCode(resultSet.getString("mariCode"));
        data.setMariText(resultSet.getString("mariText"));
        data.setMjhsCode(resultSet.getString("mjhsCode"));
        data.setMjhsText(resultSet.getString("mjhsText"));
        data.setOccupationCode(resultSet.getString("occupationCode"));
        data.setOccupationText(resultSet.getString("occupationText"));
        data.setOrigCode(resultSet.getString("origCode"));
        data.setOrigText(resultSet.getString("origText"));
        data.setPctsCode(resultSet.getString("pctsCode"));
        data.setPctsText(resultSet.getString("pctsText"));
        data.setRaceCode(resultSet.getString("raceCode"));
        data.setRaceText(resultSet.getString("raceText"));
        data.setRjnwCode(resultSet.getString("rjnwCode"));
        data.setRjnwText(resultSet.getString("rjnwText"));
        data.setRnlfCode(resultSet.getString("rnlfCode"));
        data.setRnlfText(resultSet.getString("rnlfText"));
        data.setRwnsCode(resultSet.getString("rwnsCode"));
        data.setRwnsText(resultSet.getString("rwnsText"));
        data.setSeekCode(resultSet.getString("seekCode"));
        data.setSeekText(resultSet.getString("seekText"));
        data.setSexsCode(resultSet.getString("sexsCode"));
        data.setSexsText(resultSet.getString("sexsText"));
        data.setTdatCode(resultSet.getString("tdatCode"));
        data.setTdatText(resultSet.getString("tdatText"));
        data.setVetsCode(resultSet.getString("vetsCode"));
        data.setVetsText(resultSet.getString("vetsText"));
        data.setWkstCode(resultSet.getString("wkstCode"));
        data.setWkstText(resultSet.getString("wkstText"));
        data.setBornCode(resultSet.getString("bornCode"));
        data.setBornText(resultSet.getString("bornText"));
        data.setChldCode(resultSet.getString("chldCode"));
        data.setChldText(resultSet.getString("chldText"));
        data.setDisaCode(resultSet.getString("disaCode"));
        data.setDisaText(resultSet.getString("disaText"));
        data.setSeasonal(resultSet.getString("seasonal"));
        data.setSeasonalText(resultSet.getString("seasonalText"));
        data.setFootnoteCodes(resultSet.getString("footnoteCodes"));
        data.setBeginYear(resultSet.getString("begin_year"));
        data.setBeginPeriod(resultSet.getString("begin_period"));
        data.setEndYear(resultSet.getString("end_year"));
        data.setEndPeriod(resultSet.getString("end_period"));
        return data;
    }
}
//    public static lnSeriesIDData main(String seriesIdToFind) {
//        lnSeriesIDData data = null;
//        try {
//            Class.forName("org.postgresql.Driver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//            System.exit(1);
//        }
//
//        ConfigPOJO appConfig = getConfig();
//
//        try (Connection conn = DriverManager.getConnection(appConfig.getPostgreSQLConfig().getUrl(), appConfig.getPostgreSQLConfig().getUser(), appConfig.getPostgreSQLConfig().getPassword());
//             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM ln_decoder_file WHERE TRIM(series_id) = TRIM(?)")) {
//            stmt.setString(1, seriesIdToFind); // Set the series_id parameter in the SQL query
//            ResultSet resultSet = stmt.executeQuery();
//
//            // Process the ResultSet
//            if (resultSet.next()) {  // Check if there is at least one result
//
//                data = new lnSeriesIDData();
//
//                data.setSeries_id(resultSet.getString("series_id"));
//                data.setLfstCode(resultSet.getString("lfstCode"));
//                data.setPeriodicityCode(resultSet.getString("periodicityCode"));
//                data.setPeriodicityText(resultSet.getString("periodicityText"));
//                data.setSeriesTitle(resultSet.getString("seriesTitle"));
//                data.setAbsnCode(resultSet.getString("absnCode"));
//                data.setAbsnText(resultSet.getString("absnText"));
//                data.setActivityCode(resultSet.getString("activityCode"));
//                data.setActivityText(resultSet.getString("activityText"));
//                data.setAgesCode(resultSet.getString("agesCode"));
//                data.setAgesText(resultSet.getString("agesText"));
//                data.setCertCode(resultSet.getString("certCode"));
//                data.setCertText(resultSet.getString("certText"));
//                data.setClassCode(resultSet.getString("classCode"));
//                data.setClassText(resultSet.getString("classText"));
//                data.setDurationCode(resultSet.getString("durationCode"));
//                data.setDurationText(resultSet.getString("durationText"));
//                data.setEducationCode(resultSet.getString("educationCode"));
//                data.setEducationText(resultSet.getString("educationText"));
//                data.setEntrCode(resultSet.getString("entrCode"));
//                data.setEntrText(resultSet.getString("entrText"));
//                data.setExprCode(resultSet.getString("exprCode"));
//                data.setExprText(resultSet.getString("exprText"));
//                data.setHheaderCode(resultSet.getString("hheaderCode"));
//                data.setHheaderText(resultSet.getString("hheaderText"));
//                data.setHourCode(resultSet.getString("hourCode"));
//                data.setHourText(resultSet.getString("hourText"));
//                data.setIndyCode(resultSet.getString("indyCode"));
//                data.setIndyText(resultSet.getString("indyText"));
//                data.setJdesCode(resultSet.getString("jdesCode"));
//                data.setJdesText(resultSet.getString("jdesText"));
//                data.setLookCode(resultSet.getString("lookCode"));
//                data.setLookText(resultSet.getString("lookText"));
//                data.setMariCode(resultSet.getString("mariCode"));
//                data.setMariText(resultSet.getString("mariText"));
//                data.setMjhsCode(resultSet.getString("mjhsCode"));
//                data.setMjhsText(resultSet.getString("mjhsText"));
//                data.setOccupationCode(resultSet.getString("occupationCode"));
//                data.setOccupationText(resultSet.getString("occupationText"));
//                data.setOrigCode(resultSet.getString("origCode"));
//                data.setOrigText(resultSet.getString("origText"));
//                data.setPctsCode(resultSet.getString("pctsCode"));
//                data.setPctsText(resultSet.getString("pctsText"));
//                data.setRaceCode(resultSet.getString("raceCode"));
//                data.setRaceText(resultSet.getString("raceText"));
//                data.setRjnwCode(resultSet.getString("rjnwCode"));
//                data.setRjnwText(resultSet.getString("rjnwText"));
//                data.setRnlfCode(resultSet.getString("rnlfCode"));
//                data.setRnlfText(resultSet.getString("rnlfText"));
//                data.setRwnsCode(resultSet.getString("rwnsCode"));
//                data.setRwnsText(resultSet.getString("rwnsText"));
//                data.setSeekCode(resultSet.getString("seekCode"));
//                data.setSeekText(resultSet.getString("seekText"));
//                data.setSexsCode(resultSet.getString("sexsCode"));
//                data.setSexsText(resultSet.getString("sexsText"));
//                data.setTdatCode(resultSet.getString("tdatCode"));
//                data.setTdatText(resultSet.getString("tdatText"));
//                data.setVetsCode(resultSet.getString("vetsCode"));
//                data.setVetsText(resultSet.getString("vetsText"));
//                data.setWkstCode(resultSet.getString("wkstCode"));
//                data.setWkstText(resultSet.getString("wkstText"));
//                data.setBornCode(resultSet.getString("bornCode"));
//                data.setBornText(resultSet.getString("bornText"));
//                data.setChldCode(resultSet.getString("chldCode"));
//                data.setChldText(resultSet.getString("chldText"));
//                data.setDisaCode(resultSet.getString("disaCode"));
//                data.setDisaText(resultSet.getString("disaText"));
//                data.setSeasonal(resultSet.getString("seasonal"));
//                data.setSeasonalText(resultSet.getString("seasonalText"));
//                data.setFootnoteCodes(resultSet.getString("footnoteCodes"));
//                data.setBegin_year(resultSet.getString("begin_year"));
//                data.setBegin_period(resultSet.getString("begin_period"));
//                data.setEnd_year(resultSet.getString("end_year"));
//                data.setEnd_period(resultSet.getString("end_period"));
//
//                // Print out each column or use it as needed
//                System.out.println("Retrieved data for Series ID: " +  data.getSeries_id());
//
//
//            } else {
//                System.out.println("No records found matching the series ID.");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return data;
//    }
//}
