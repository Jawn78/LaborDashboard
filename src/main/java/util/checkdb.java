package util;
import data_models.LNSeriesIDData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class checkdb {

    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
//        bls_decoders.public.ln_decoder_file
        String url = "jdbc:postgresql://192.168.50.214/bls_decoders";
        String user = "blsuser";
        String password = "P@$$w0rd";

        String seriesIdToFind = "LNU00022968";  // Set the actual series ID you are looking for

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM ln_decoder_file WHERE TRIM(series_id) = TRIM(?)")) {
            stmt.setString(1, seriesIdToFind); // Set the series_id parameter in the SQL query
            ResultSet rs = stmt.executeQuery();

            // Process the ResultSet
            if (rs.next()) {  // Check if there is at least one result

                LNSeriesIDData data = new LNSeriesIDData();

                data.setSeries_id(rs.getString("series_id"));
                data.setLfst_code(rs.getString("lfst_code"));
                data.setPeriodicity_code(rs.getString("periodicity_code"));
                data.setPeriodicity_text(rs.getString("periodicity_text"));
                data.setSeries_title(rs.getString("series_title"));
                data.setAbsn_code(rs.getString("absn_code"));
                data.setAbsn_text(rs.getString("absn_text"));
                data.setActivity_code(rs.getString("activity_code"));
                data.setActivity_text(rs.getString("activity_text"));
                data.setAges_code(rs.getString("ages_code"));
                data.setAges_text(rs.getString("ages_text"));
                data.setCert_code(rs.getString("cert_code"));
                data.setCert_text(rs.getString("cert_text"));
                data.setClass_code(rs.getString("class_code"));
                data.setClass_text(rs.getString("class_text"));
                data.setDuration_code(rs.getString("duration_code"));
                data.setDuration_text(rs.getString("duration_text"));
                data.setEducation_code(rs.getString("education_code"));
                data.setEducation_text(rs.getString("education_text"));
                data.setEntr_code(rs.getString("entr_code"));
                data.setEntr_text(rs.getString("entr_text"));
                data.setExpr_code(rs.getString("expr_code"));
                data.setExpr_text(rs.getString("expr_text"));
                data.setHheader_code(rs.getString("hheader_code"));
                data.setHheader_text(rs.getString("hheader_text"));
                data.setHour_code(rs.getString("hour_code"));
                data.setHour_text(rs.getString("hour_text"));
                data.setIndy_code(rs.getString("indy_code"));
                data.setIndy_text(rs.getString("indy_text"));
                data.setJdes_code(rs.getString("jdes_code"));
                data.setJdes_text(rs.getString("jdes_text"));
                data.setLook_code(rs.getString("look_code"));
                data.setLook_text(rs.getString("look_text"));
                data.setMari_code(rs.getString("mari_code"));
                data.setMari_text(rs.getString("mari_text"));
                data.setMjhs_code(rs.getString("mjhs_code"));
                data.setMjhs_text(rs.getString("mjhs_text"));
                data.setOccupation_code(rs.getString("occupation_code"));
                data.setOccupation_text(rs.getString("occupation_text"));
                data.setOrig_code(rs.getString("orig_code"));
                data.setOrig_text(rs.getString("orig_text"));
                data.setPcts_code(rs.getString("pcts_code"));
                data.setPcts_text(rs.getString("pcts_text"));
                data.setRace_code(rs.getString("race_code"));
                data.setRace_text(rs.getString("race_text"));
                data.setRjnw_code(rs.getString("rjnw_code"));
                data.setRjnw_text(rs.getString("rjnw_text"));
                data.setRnlf_code(rs.getString("rnlf_code"));
                data.setRnlf_text(rs.getString("rnlf_text"));
                data.setRwns_code(rs.getString("rwns_code"));
                data.setRwns_text(rs.getString("rwns_text"));
                data.setSeek_code(rs.getString("seek_code"));
                data.setSeek_text(rs.getString("seek_text"));
                data.setSexs_code(rs.getString("sexs_code"));
                data.setSexs_text(rs.getString("sexs_text"));
                data.setTdat_code(rs.getString("tdat_code"));
                data.setTdat_text(rs.getString("tdat_text"));
                data.setVets_code(rs.getString("vets_code"));
                data.setVets_text(rs.getString("vets_text"));
                data.setWkst_code(rs.getString("wkst_code"));
                data.setWkst_text(rs.getString("wkst_text"));
                data.setBorn_code(rs.getString("born_code"));
                data.setBorn_text(rs.getString("born_text"));
                data.setChld_code(rs.getString("chld_code"));
                data.setChld_text(rs.getString("chld_text"));
                data.setDisa_code(rs.getString("disa_code"));
                data.setDisa_text(rs.getString("disa_text"));
                data.setSeasonal(rs.getString("seasonal"));
                data.setSeasonal_text(rs.getString("seasonal_text"));
                data.setFootnote_codes(rs.getString("footnote_codes"));
                data.setBegin_year(rs.getString("begin_year"));
                data.setBegin_period(rs.getString("begin_period"));
                data.setEnd_year(rs.getString("end_year"));
                data.setEnd_period(rs.getString("end_period"));

                // Print out each column or use it as needed
                System.out.println("Series ID: " +  data.getSeries_id());
                System.out.println("LFST Code: " + data.getLfst_code());
                System.out.println("Periodicity Text: " + data.getPeriodicity_text());
                // Continue printing other fields
            } else {
                System.out.println("No records found matching the series ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
