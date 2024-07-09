package excel_writers;

import data_models.BLS_API_Models.Datum;
import data_models.BLS_API_Models.Series;
import data_models.BLS_Data_Models.SMSeriesIDData;
import data_models.File_Decoder_Models.FileDecoderModel;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.HashMap;
import java.util.Map;

import static excel_writers.sm_series_id_decoder.decodeSeriesID;

public class unknown_series_excel_writer {

    public static void writeToExcel(Sheet sheet, Series series, FileDecoderModel fileDecoderModel) {
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
            System.out.println("Total number of year combinations: " + seriesData.size());
            System.out.println("KeySet: " + seriesData.keySet());
            // Writing aggregated data to Excel
            int rowNum = sheet.getLastRowNum() + 1; // Ensure we're writing to a new row
            for (String key : seriesData.keySet()) {
                Row row = sheet.createRow(rowNum++);
                String[] parts = key.split("-");
                String seriesID = parts[0];
                String year = parts[1];
                //Create Headers
                createHeaders(sheet, fileDecoderModel);


                row.createCell(0).setCellValue(seriesID);
                int f = 1;
                int g = 1;
                int size = fileDecoderModel.getModelParts().size() * 2;
                for (int i = 1; i < size; i++) {
                    row.createCell(i).setCellValue(fileDecoderModel.ModelParts.get(g).getSubIDString());
                    i++;
                    f++;
                    row.createCell(i).setCellValue(fileDecoderModel.ModelParts.get(g).getIDPartvalue());
                    if (g < fileDecoderModel.getModelParts().size() - 1) {
                        g++;
                    }
                    f++;

                }
                f++;
                row.createCell(f++).setCellValue(year);
                // Static data

                // Dynamic monthly data
                Map<String, String> periodValues = seriesData.get(key);
                for (int i = 1; i <= 12; i++) {
                    if (seriesData.get(key).toString().contains("Q")) {
                        String quarterKey = "Q" + String.format("%02d", i);
                        row.createCell(f).setCellValue("Quarterly");
                        String value = periodValues.getOrDefault(quarterKey, "");  // Use "N/A" for missing values
                        row.createCell(f + i).setCellValue(value);
                    } else if (seriesData.get(key).toString().contains("M")) {
                        row.createCell(f).setCellValue("Monthly");
                        String monthKey = "M" + String.format("%02d", i);
                        String value = periodValues.getOrDefault(monthKey, "");  // Use "N/A" for missing values
                        row.createCell(f + i).setCellValue(value);
                    }


                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void createHeaders(Sheet sheet, FileDecoderModel fileDecoderModel) {
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Series ID");
        int f = 1;
        int g = 1;
        int i = 1;
        for (i = 1; i < fileDecoderModel.getModelParts().size() * 2; i++) {
            header.createCell(i).setCellValue(fileDecoderModel.ModelParts.get(g).getKeyHeader());
            i++;
            f++;
            header.createCell(i).setCellValue(fileDecoderModel.ModelParts.get(g).getValueHeader());
            if (g < fileDecoderModel.getModelParts().size() - 1) {
                g++;
            }
            f++;
        }

        header.createCell(f + 1).setCellValue("year");
        header.createCell(f + 2).setCellValue("period");
        header.createCell(f + 3).setCellValue("period_1");
        header.createCell(f + 4).setCellValue("period_2");
        header.createCell(f + 5).setCellValue("period_3");
        header.createCell(f + 6).setCellValue("period_4");
        header.createCell(f + 7).setCellValue("period_5");
        header.createCell(f + 8).setCellValue("period_6");
        header.createCell(f + 9).setCellValue("period_7");
        header.createCell(f + 10).setCellValue("period_8");
        header.createCell(f + 11).setCellValue("period_9");
        header.createCell(f + 12).setCellValue("period_10");
        header.createCell(f + 13).setCellValue("period_11");
        header.createCell(f + 14).setCellValue("period_12");
    }


}
