package excel_writers;

import bls.Datum;
import bls.Series;
import data_models.LNSeriesIDData;
import data_models.SMSeriesIDData;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import series_id_decoder.LN_Decoder;

import java.util.HashMap;
import java.util.Map;

import static decoder_files.sm_series_id_decoder.decodeSeriesID;
import static series_id_decoder.LN_Decoder.loadSeriesData;

public class ln_excel_writer {

    public static void writeToExcel(Sheet sheet, bls.Response responseJSON) {
        try {


            // Use a map to organize data by Series ID and Year
            Map<String, Map<String, String>> seriesData = new HashMap<>();

            for (Series series : responseJSON.getResults().getSeries()) {
                for (Datum dataPoint : series.getData()) {
                    String key = series.getSeriesID() + "-" + dataPoint.getYear();
                    if (!seriesData.containsKey(key)) {
                        seriesData.put(key, new HashMap<>());
                    }
                    seriesData.get(key).put(dataPoint.getPeriod(), dataPoint.getValue());
                }
            }

            // Verify the data size
            System.out.println("Total number of series-year combinations: " + seriesData.size());

            // Writing aggregated data to Excel
            int rowNum = sheet.getLastRowNum() + 1; // Ensure we're writing to a new row
            for (String key : seriesData.keySet()) {
                Row row = sheet.createRow(rowNum++);
                String[] parts = key.split("-");
                String seriesID = parts[0];
                String year = parts[1];

                LNSeriesIDData seriesDataObj = LN_Decoder.getSeriesData(seriesID,loadSeriesData("src/main/java/decoder_files/ln_decoder_file.xlsx"));
                // Static data
                row.createCell(0).setCellValue(seriesDataObj.getSeries_id());
                row.createCell(1).setCellValue(seriesDataObj.getLfst_code());
                row.createCell(2).setCellValue(seriesDataObj.getPeriodicity_code());
                row.createCell(3).setCellValue(seriesDataObj.getPeriodicity_text());
                row.createCell(4).setCellValue(seriesDataObj.getSeries_title());
                row.createCell(5).setCellValue(seriesDataObj.getAbsn_code());
                row.createCell(6).setCellValue(seriesDataObj.getAbsn_text());
                row.createCell(7).setCellValue(seriesDataObj.getActivity_code());
                row.createCell(8).setCellValue(seriesDataObj.getActivity_text());
                row.createCell(9).setCellValue(seriesDataObj.getAges_code());
                row.createCell(10).setCellValue(seriesDataObj.getAges_text());
                row.createCell(11).setCellValue(seriesDataObj.getCert_code());
                row.createCell(12).setCellValue(seriesDataObj.getCert_text());
                row.createCell(13).setCellValue(seriesDataObj.getClass_code());
                row.createCell(14).setCellValue(seriesDataObj.getClass_text());
                row.createCell(15).setCellValue(seriesDataObj.getDuration_code());
                row.createCell(16).setCellValue(seriesDataObj.getDuration_text());
                row.createCell(17).setCellValue(seriesDataObj.getEducation_code());
                row.createCell(18).setCellValue(seriesDataObj.getEducation_text());
                row.createCell(19).setCellValue(seriesDataObj.getEntr_code());
                row.createCell(20).setCellValue(seriesDataObj.getEntr_text());
                row.createCell(21).setCellValue(seriesDataObj.getExpr_code());
                row.createCell(22).setCellValue(seriesDataObj.getExpr_text());
                row.createCell(23).setCellValue(seriesDataObj.getHheader_code());
                row.createCell(24).setCellValue(seriesDataObj.getHheader_text());
                row.createCell(25).setCellValue(seriesDataObj.getHour_code());
                row.createCell(26).setCellValue(seriesDataObj.getHour_text());
                row.createCell(27).setCellValue(seriesDataObj.getIndy_code());
                row.createCell(28).setCellValue(seriesDataObj.getIndy_text());
                row.createCell(29).setCellValue(seriesDataObj.getJdes_code());
                row.createCell(30).setCellValue(seriesDataObj.getJdes_text());
                row.createCell(31).setCellValue(seriesDataObj.getLook_code());
                row.createCell(32).setCellValue(seriesDataObj.getLook_text());
                row.createCell(33).setCellValue(seriesDataObj.getMari_code());
                row.createCell(34).setCellValue(seriesDataObj.getMari_text());
                row.createCell(35).setCellValue(seriesDataObj.getMjhs_code());
                row.createCell(36).setCellValue(seriesDataObj.getMjhs_text());
                row.createCell(37).setCellValue(seriesDataObj.getOccupation_code());
                row.createCell(38).setCellValue(seriesDataObj.getOccupation_text());
                row.createCell(39).setCellValue(seriesDataObj.getOrig_code());
                row.createCell(40).setCellValue(seriesDataObj.getOrig_text());
                row.createCell(41).setCellValue(seriesDataObj.getPcts_code());
                row.createCell(42).setCellValue(seriesDataObj.getPcts_text());
                row.createCell(43).setCellValue(seriesDataObj.getRace_code());
                row.createCell(44).setCellValue(seriesDataObj.getRace_text());
                row.createCell(45).setCellValue(seriesDataObj.getRjnw_code());
                row.createCell(46).setCellValue(seriesDataObj.getRjnw_text());
                row.createCell(47).setCellValue(seriesDataObj.getRnlf_code());
                row.createCell(48).setCellValue(seriesDataObj.getRnlf_text());
                row.createCell(49).setCellValue(seriesDataObj.getRwns_code());
                row.createCell(50).setCellValue(seriesDataObj.getRwns_text());
                row.createCell(51).setCellValue(seriesDataObj.getSeek_code());
                row.createCell(52).setCellValue(seriesDataObj.getSeek_text());
                row.createCell(53).setCellValue(seriesDataObj.getSexs_code());
                row.createCell(54).setCellValue(seriesDataObj.getSexs_text());
                row.createCell(55).setCellValue(seriesDataObj.getTdat_code());
                row.createCell(56).setCellValue(seriesDataObj.getTdat_text());
                row.createCell(57).setCellValue(seriesDataObj.getVets_code());
                row.createCell(58).setCellValue(seriesDataObj.getVets_text());
                row.createCell(59).setCellValue(seriesDataObj.getWkst_code());
                row.createCell(60).setCellValue(seriesDataObj.getWkst_text());
                row.createCell(61).setCellValue(seriesDataObj.getBorn_code());
                row.createCell(62).setCellValue(seriesDataObj.getBorn_text());
                row.createCell(63).setCellValue(seriesDataObj.getChld_code());
                row.createCell(64).setCellValue(seriesDataObj.getChld_text());
                row.createCell(65).setCellValue(seriesDataObj.getDisa_code());
                row.createCell(66).setCellValue(seriesDataObj.getDisa_text());
                row.createCell(67).setCellValue(seriesDataObj.getSeasonal());
                row.createCell(68).setCellValue(seriesDataObj.getSeasonal_text());
                row.createCell(69).setCellValue(seriesDataObj.getFootnote_codes());
                row.createCell(70).setCellValue(seriesDataObj.getBegin_year());
                row.createCell(71).setCellValue(seriesDataObj.getBegin_period());
                row.createCell(72).setCellValue(seriesDataObj.getEnd_year());
                row.createCell(73).setCellValue(seriesDataObj.getEnd_period());
                row.createCell(74).setCellValue(year);

                // Dynamic monthly data
                Map<String, String> periodValues = seriesData.get(key);
                for (int i = 1; i <= 12; i++) {

                    if (seriesData.get(key).toString().contains("Q")) {
                        String quarterKey = "Q" + String.format("%02d", i);
                        row.createCell(75).setCellValue("Quarterly");
                        String value = periodValues.getOrDefault(quarterKey, "");  // Use "N/A" for missing values
                        row.createCell(75 + i).setCellValue(value);
                    } else if (seriesData.get(key).toString().contains("M")) {
                        row.createCell(75).setCellValue("Monthly");
                        String monthKey = "M" + String.format("%02d", i);
                        String value = periodValues.getOrDefault(monthKey, "");  // Use "N/A" for missing values
                        row.createCell(75 + i).setCellValue(value);
                    }



                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void createHeaders(Sheet sheet) {
        // Create headers
        Row header = sheet.createRow(0);

        header.createCell(0).setCellValue("Series id");
        header.createCell(1).setCellValue("Lfst code");
        header.createCell(2).setCellValue("Periodicity code");
        header.createCell(3).setCellValue("Periodicity text");
        header.createCell(4).setCellValue("Series title");
        header.createCell(5).setCellValue("Absn code");
        header.createCell(6).setCellValue("Absn text");
        header.createCell(7).setCellValue("Activity code");
        header.createCell(8).setCellValue("Activity text");
        header.createCell(9).setCellValue("Ages code");
        header.createCell(10).setCellValue("Ages text");
        header.createCell(11).setCellValue("Cert code");
        header.createCell(12).setCellValue("Cert text");
        header.createCell(13).setCellValue("Class code");
        header.createCell(14).setCellValue("Class text");
        header.createCell(15).setCellValue("Duration code");
        header.createCell(16).setCellValue("Duration text");
        header.createCell(17).setCellValue("Education code");
        header.createCell(18).setCellValue("Education text");
        header.createCell(19).setCellValue("Entr code");
        header.createCell(20).setCellValue("Entr text");
        header.createCell(21).setCellValue("Expr code");
        header.createCell(22).setCellValue("Expr text");
        header.createCell(23).setCellValue("Hheader code");
        header.createCell(24).setCellValue("Hheader text");
        header.createCell(25).setCellValue("Hour code");
        header.createCell(26).setCellValue("Hour text");
        header.createCell(27).setCellValue("Indy code");
        header.createCell(28).setCellValue("Indy text");
        header.createCell(29).setCellValue("Jdes code");
        header.createCell(30).setCellValue("Jdes text");
        header.createCell(31).setCellValue("Look code");
        header.createCell(32).setCellValue("Look text");
        header.createCell(33).setCellValue("Mari code");
        header.createCell(34).setCellValue("Mari text");
        header.createCell(35).setCellValue("Mjhs code");
        header.createCell(36).setCellValue("Mjhs text");
        header.createCell(37).setCellValue("Occupation code");
        header.createCell(38).setCellValue("Occupation text");
        header.createCell(39).setCellValue("Orig code");
        header.createCell(40).setCellValue("Orig text");
        header.createCell(41).setCellValue("Pcts code");
        header.createCell(42).setCellValue("Pcts text");
        header.createCell(43).setCellValue("Race code");
        header.createCell(44).setCellValue("Race text");
        header.createCell(45).setCellValue("Rjnw code");
        header.createCell(46).setCellValue("Rjnw text");
        header.createCell(47).setCellValue("Rnlf code");
        header.createCell(48).setCellValue("Rnlf text");
        header.createCell(49).setCellValue("Rwns code");
        header.createCell(50).setCellValue("Rwns text");
        header.createCell(51).setCellValue("Seek code");
        header.createCell(52).setCellValue("Seek text");
        header.createCell(53).setCellValue("Sexs code");
        header.createCell(54).setCellValue("Sexs text");
        header.createCell(55).setCellValue("Tdat code");
        header.createCell(56).setCellValue("Tdat text");
        header.createCell(57).setCellValue("Vets code");
        header.createCell(58).setCellValue("Vets text");
        header.createCell(59).setCellValue("Wkst code");
        header.createCell(60).setCellValue("Wkst text");
        header.createCell(61).setCellValue("Born code");
        header.createCell(62).setCellValue("Born text");
        header.createCell(63).setCellValue("Chld code");
        header.createCell(64).setCellValue("Chld text");
        header.createCell(65).setCellValue("Disa code");
        header.createCell(66).setCellValue("Disa text");
        header.createCell(67).setCellValue("Seasonal");
        header.createCell(68).setCellValue("Seasonal text");
        header.createCell(69).setCellValue("Footnote codes");
        header.createCell(70).setCellValue("Begin year");
        header.createCell(71).setCellValue("Begin period");
        header.createCell(72).setCellValue("End year");
        header.createCell(73).setCellValue("End period");
        header.createCell(74).setCellValue("Year");
        header.createCell(75).setCellValue("Period");
        header.createCell(76).setCellValue("1");
        header.createCell(77).setCellValue("2");
        header.createCell(79).setCellValue("3");
        header.createCell(79).setCellValue("4");
        header.createCell(80).setCellValue("5");
        header.createCell(81).setCellValue("6");
        header.createCell(82).setCellValue("7");
        header.createCell(83).setCellValue("8");
        header.createCell(84).setCellValue("9");
        header.createCell(85).setCellValue("10");
        header.createCell(86).setCellValue("11");
        header.createCell(87).setCellValue("12");

    }


}
