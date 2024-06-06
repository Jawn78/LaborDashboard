package excel_writers;

import data_models.BLS_API_Models.Datum;
import data_models.BLS_API_Models.Series;
import data_models.BLS_Data_Models.lnSeriesIDData;
import data_models.Config_Models.ConfigPOJO;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import series_id_utils.exel_decoders.LN_Decoder;
import util.config.Manager;
import series_id_utils.postgressql_decoders.LN_SeriesID_Decoder;

import java.util.HashMap;
import java.util.Map;

import static series_id_utils.exel_decoders.LN_Decoder.loadSeriesData;

public class ln_excel_writer {
    static ConfigPOJO config = Manager.getConfig();
    public static void writeToExcel(Sheet sheet, Series series) {
        try {
            lnSeriesIDData seriesDataObj = null;
            // Use a map to organize data by Series ID and Year
            Map<String, Map<String, String>> seriesData = new HashMap<>();


                for (Datum dataPoint : series.getData()) {
                    String key = series.getSeriesID() + "-" + dataPoint.getYear();
                    if (!seriesData.containsKey(key)) {
                        seriesData.put(key, new HashMap<>());
                    }
                    seriesData.get(key).put(dataPoint.getPeriod(), dataPoint.getValue());
                }


            // Verify the data size & Keyset
            System.out.println("Total number of LN series-year combinations: " + seriesData.size());
            System.out.println("LN KeySet: " + seriesData.keySet());
            // Writing aggregated data to Excel
            int rowNum = sheet.getLastRowNum() + 1; // Ensure writing to a new row
            for (String key : seriesData.keySet()) {
                Row row = sheet.createRow(rowNum++);
                String[] parts = key.split("-");
                String seriesID = parts[0];
                String year = parts[1];

                if(config.getUsePostgreSQL()){
                    seriesDataObj = LN_SeriesID_Decoder.decodeSeriesID(seriesID);
                } else if(!config.getUsePostgreSQL()){
                    seriesDataObj = LN_Decoder.getSeriesData(seriesID,loadSeriesData("src/main/java/excel_decoder_files/ln_decoder_file.xlsx"));
                }
                        // Static data
                row.createCell(0).setCellValue(seriesDataObj.getSeriesId());
                row.createCell(1).setCellValue(seriesDataObj.getLfstCode());
                row.createCell(2).setCellValue(seriesDataObj.getPeriodicityCode());
                row.createCell(3).setCellValue(seriesDataObj.getPeriodicityText());
                row.createCell(4).setCellValue(seriesDataObj.getSeriesTitle());
                row.createCell(5).setCellValue(seriesDataObj.getAbsnCode());
                row.createCell(6).setCellValue(seriesDataObj.getAbsnText());
                row.createCell(7).setCellValue(seriesDataObj.getActivityCode());
                row.createCell(8).setCellValue(seriesDataObj.getActivityText());
                row.createCell(9).setCellValue(seriesDataObj.getAgesCode());
                row.createCell(10).setCellValue(seriesDataObj.getAgesText());
                row.createCell(11).setCellValue(seriesDataObj.getCertCode());
                row.createCell(12).setCellValue(seriesDataObj.getCertText());
                row.createCell(13).setCellValue(seriesDataObj.getClassCode());
                row.createCell(14).setCellValue(seriesDataObj.getClassText());
                row.createCell(15).setCellValue(seriesDataObj.getDurationCode());
                row.createCell(16).setCellValue(seriesDataObj.getDurationText());
                row.createCell(17).setCellValue(seriesDataObj.getEducationCode());
                row.createCell(18).setCellValue(seriesDataObj.getEducationText());
                row.createCell(19).setCellValue(seriesDataObj.getEntrCode());
                row.createCell(20).setCellValue(seriesDataObj.getEntrText());
                row.createCell(21).setCellValue(seriesDataObj.getExprCode());
                row.createCell(22).setCellValue(seriesDataObj.getExprText());
                row.createCell(23).setCellValue(seriesDataObj.getHheaderCode());
                row.createCell(24).setCellValue(seriesDataObj.getHheaderText());
                row.createCell(25).setCellValue(seriesDataObj.getHourCode());
                row.createCell(26).setCellValue(seriesDataObj.getHourText());
                row.createCell(27).setCellValue(seriesDataObj.getIndyCode());
                row.createCell(28).setCellValue(seriesDataObj.getIndyText());
                row.createCell(29).setCellValue(seriesDataObj.getJdesCode());
                row.createCell(30).setCellValue(seriesDataObj.getJdesText());
                row.createCell(31).setCellValue(seriesDataObj.getLookCode());
                row.createCell(32).setCellValue(seriesDataObj.getLookText());
                row.createCell(33).setCellValue(seriesDataObj.getMariCode());
                row.createCell(34).setCellValue(seriesDataObj.getMariText());
                row.createCell(35).setCellValue(seriesDataObj.getMjhsCode());
                row.createCell(36).setCellValue(seriesDataObj.getMjhsText());
                row.createCell(37).setCellValue(seriesDataObj.getOccupationCode());
                row.createCell(38).setCellValue(seriesDataObj.getOccupationText());
                row.createCell(39).setCellValue(seriesDataObj.getOrigCode());
                row.createCell(40).setCellValue(seriesDataObj.getOrigText());
                row.createCell(41).setCellValue(seriesDataObj.getPctsCode());
                row.createCell(42).setCellValue(seriesDataObj.getPctsText());
                row.createCell(43).setCellValue(seriesDataObj.getRaceCode());
                row.createCell(44).setCellValue(seriesDataObj.getRaceText());
                row.createCell(45).setCellValue(seriesDataObj.getRjnwCode());
                row.createCell(46).setCellValue(seriesDataObj.getRjnwText());
                row.createCell(47).setCellValue(seriesDataObj.getRnlfCode());
                row.createCell(48).setCellValue(seriesDataObj.getRnlfText());
                row.createCell(49).setCellValue(seriesDataObj.getRwnsCode());
                row.createCell(50).setCellValue(seriesDataObj.getRwnsText());
                row.createCell(51).setCellValue(seriesDataObj.getSeekCode());
                row.createCell(52).setCellValue(seriesDataObj.getSeekText());
                row.createCell(53).setCellValue(seriesDataObj.getSexsCode());
                row.createCell(54).setCellValue(seriesDataObj.getSexsText());
                row.createCell(55).setCellValue(seriesDataObj.getTdatCode());
                row.createCell(56).setCellValue(seriesDataObj.getTdatText());
                row.createCell(57).setCellValue(seriesDataObj.getVetsCode());
                row.createCell(58).setCellValue(seriesDataObj.getVetsText());
                row.createCell(59).setCellValue(seriesDataObj.getWkstCode());
                row.createCell(60).setCellValue(seriesDataObj.getWkstText());
                row.createCell(61).setCellValue(seriesDataObj.getBornCode());
                row.createCell(62).setCellValue(seriesDataObj.getBornText());
                row.createCell(63).setCellValue(seriesDataObj.getChldCode());
                row.createCell(64).setCellValue(seriesDataObj.getChldText());
                row.createCell(65).setCellValue(seriesDataObj.getDisaCode());
                row.createCell(66).setCellValue(seriesDataObj.getDisaText());
                row.createCell(67).setCellValue(seriesDataObj.getSeasonal());
                row.createCell(68).setCellValue(seriesDataObj.getSeasonalText());
                row.createCell(69).setCellValue(seriesDataObj.getFootnoteCodes());
                row.createCell(70).setCellValue(seriesDataObj.getBeginYear());
                row.createCell(71).setCellValue(seriesDataObj.getBeginPeriod());
                row.createCell(72).setCellValue(seriesDataObj.getEndYear());
                row.createCell(73).setCellValue(seriesDataObj.getEndPeriod());
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
        header.createCell(76).setCellValue("period_1");
        header.createCell(77).setCellValue("period_2");
        header.createCell(79).setCellValue("period_3");
        header.createCell(79).setCellValue("period_4");
        header.createCell(80).setCellValue("period_5");
        header.createCell(81).setCellValue("period_6");
        header.createCell(82).setCellValue("period_7");
        header.createCell(83).setCellValue("period_8");
        header.createCell(84).setCellValue("period_9");
        header.createCell(85).setCellValue("period_10");
        header.createCell(86).setCellValue("period_11");
        header.createCell(87).setCellValue("period_12");

    }


}
