package excel_writers;

import bls.Datum;
import bls.Series;
import data_models.SMSeriesIDData;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.HashMap;
import java.util.Map;

import static decoder_files.sm_series_id_decoder.decodeSeriesID;

public class sm_excel_writer {

    public static void writeToExcel(Sheet sheet, Series series) {
        try {


            // Use a map to organize data by Series ID and Year
            Map<String, Map<String, String>> seriesData = new HashMap<>();

                for (Datum dataPoint : series.getData()) {
                    String key = series.getSeriesID() + "-" + dataPoint.getYear();
                    if (!seriesData.containsKey(key)) {
                        seriesData.put(key, new HashMap<>());
                    }
                    seriesData.get(key).put(dataPoint.getPeriod(), dataPoint.getValue());
                }

            // Verify the data size
            System.out.println("Total number of series-year SM combinations: " + seriesData.size());
            System.out.println("SM KeySet: " + seriesData.keySet());
            // Writing aggregated data to Excel
            int rowNum = sheet.getLastRowNum() + 1; // Ensure we're writing to a new row
            for (String key : seriesData.keySet()) {
                Row row = sheet.createRow(rowNum++);
                String[] parts = key.split("-");
                String seriesID = parts[0];
                String year = parts[1];

                SMSeriesIDData seriesDataObj = decodeSeriesID(seriesID);

                // Static data
                row.createCell(0).setCellValue(seriesDataObj.getSeriesID());
                row.createCell(1).setCellValue(seriesDataObj.getSeasonalAdjustmentCode());
                row.createCell(2).setCellValue(seriesDataObj.getSeasonalAdjustmentText());
                row.createCell(3).setCellValue(seriesDataObj.getStateCode());
                row.createCell(4).setCellValue(seriesDataObj.getStateName());
                row.createCell(5).setCellValue(seriesDataObj.getAreaCode());
                row.createCell(6).setCellValue(seriesDataObj.getAreaName());
                row.createCell(7).setCellValue(seriesDataObj.getSupersectorCode());
                row.createCell(8).setCellValue(seriesDataObj.getSupersectorName());
                row.createCell(9).setCellValue(seriesDataObj.getIndustryCode());
                row.createCell(10).setCellValue(seriesDataObj.getIndustryName());
                row.createCell(11).setCellValue(seriesDataObj.getDataTypeCode());
                row.createCell(12).setCellValue(seriesDataObj.getDataTypeText());
                row.createCell(13).setCellValue(year);

                // Dynamic monthly data
                Map<String, String> periodValues = seriesData.get(key);
                for (int i = 1; i <= 12; i++) {

                    if (seriesData.get(key).toString().contains("Q")) {
                        String quarterKey = "Q" + String.format("%02d", i);
                        row.createCell(14).setCellValue("Quarterly");
                        String value = periodValues.getOrDefault(quarterKey, "");  // Use "N/A" for missing values
                        row.createCell(14 + i).setCellValue(value);
                    } else if (seriesData.get(key).toString().contains("M")) {
                        row.createCell(14).setCellValue("Monthly");
                        String monthKey = "M" + String.format("%02d", i);
                        String value = periodValues.getOrDefault(monthKey, "");  // Use "N/A" for missing values
                        row.createCell(14 + i).setCellValue(value);
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
        header.createCell(0).setCellValue("Series ID");
        header.createCell(1).setCellValue("Seasonally Adjusted Code");
        header.createCell(2).setCellValue("Seasonally Adjusted Text");
        header.createCell(3).setCellValue("State Code");
        header.createCell(4).setCellValue("State");
        header.createCell(5).setCellValue("Area Code");
        header.createCell(6).setCellValue("Area");
        header.createCell(7).setCellValue("Supersector Code");
        header.createCell(8).setCellValue("Supersector");
        header.createCell(9).setCellValue("Industry Code");
        header.createCell(10).setCellValue("Industry");
        header.createCell(11).setCellValue("Data Type Code");
        header.createCell(12).setCellValue("Data Type");
        header.createCell(13).setCellValue("Year");
        header.createCell(14).setCellValue("Period");
        header.createCell(15).setCellValue("1");
        header.createCell(16).setCellValue("2");
        header.createCell(17).setCellValue("3");
        header.createCell(18).setCellValue("4");
        header.createCell(19).setCellValue("5");
        header.createCell(20).setCellValue("6");
        header.createCell(21).setCellValue("7");
        header.createCell(22).setCellValue("8");
        header.createCell(23).setCellValue("9");
        header.createCell(24).setCellValue("10");
        header.createCell(25).setCellValue("11");
        header.createCell(26).setCellValue("12");

    }


}
