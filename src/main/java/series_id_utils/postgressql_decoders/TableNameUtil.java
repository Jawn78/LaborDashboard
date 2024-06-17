package series_id_utils.postgressql_decoders;

public class TableNameUtil {

    public static String getTableName(String seriesId) {
        if (seriesId == null || seriesId.length() < 2) {
            throw new IllegalArgumentException("Invalid series ID");
        }
        String prefix = seriesId.substring(0, 2).toLowerCase();
        return prefix + "_labor_data";
    }
}
