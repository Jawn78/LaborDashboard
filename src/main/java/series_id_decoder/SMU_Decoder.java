package series_id_decoder;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class SMU_Decoder {
    public static void main(String[] args) throws Exception {
        // Load the Excel File
        FileInputStream file = new FileInputStream("src/main/java/decoder_files/smu_decoder_file.xlsx");
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0);

        // Prepare a map to hold the data
        Map<String, String[]> dataMap = new HashMap<>();
        boolean header = true;

        for (Row row : sheet) {
            if (header) {
                header = false; // skip the header row
                continue;
            }
            String stateCode = getStringCellValue(row.getCell(0));
            String stateName = getStringCellValue(row.getCell(1));
            String areaCode = getStringCellValue(row.getCell(2));
            String areaName = getStringCellValue(row.getCell(3));
            String supersectorCode = getStringCellValue(row.getCell(4));
            String supersectorName = getStringCellValue(row.getCell(5));
            String industryCode = getStringCellValue(row.getCell(6));
            String industryName = getStringCellValue(row.getCell(7));
            String dataTypeCode = getStringCellValue(row.getCell(8));
            String dataTypeText = getStringCellValue(row.getCell(9));

            String key = stateCode + areaCode + supersectorCode + industryCode + dataTypeCode;
            dataMap.put(key, new String[]{stateName, areaName, supersectorName, industryName, dataTypeText});
        }

        // Example Series ID
        String seriesId = "SMU09000005500000002";
        decodeSeriesID(seriesId, dataMap);

        workbook.close();
        file.close();
    }
    // Method to handle different cell types
    private static String getStringCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    // Format the date into a string if necessary
                    return new SimpleDateFormat("yyyy-MM-dd").format(cell.getDateCellValue());
                } else {
                    // Convert numeric value to string
                    return Double.toString(cell.getNumericCellValue());
                }
            case BOOLEAN:
                return Boolean.toString(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }

    private static void decodeSeriesID(String seriesId, Map<String, String[]> dataMap) {
        // Validate the series ID length
        if (seriesId.length() < 20) {
            System.out.println("Invalid series ID length: " + seriesId);
            return;
        }

        // Extract parts of the Series ID safely using the Excel MID logic converted to Java substring
        String prefix = seriesId.substring(0, 2);  // SM - Similar to Excel MID($A3, 1, 2)
        String seasonalAdjustmentCode = seriesId.substring(2, 3); // U - Similar to Excel MID($A3, 3, 1)
        String stateCode = seriesId.length() >= 5 ? seriesId.substring(3, 5) : ""; // Similar to Excel MID($A3, 4, 2)
        String areaCode = seriesId.length() >= 10 ? seriesId.substring(5, 10) : ""; // Similar to Excel MID($A3, 6, 5)
        String supersectorCode = seriesId.length() >= 12 ? seriesId.substring(10, 12) : ""; // Similar to Excel MID($A3, 11, 2)
        String industryCode = seriesId.length() >= 18 ? seriesId.substring(10, 18) : ""; // Similar to Excel MID($A3, 11, 8)
        String dataTypeCode = seriesId.length() >= 20 ? seriesId.substring(18, 20) : ""; // Similar to Excel MID($A3, 19, 2)

        // Construct a key for map lookup. In your Excel, these would be used in separate VLOOKUPs
        // Here we concatenate them into a single key, which should be adjusted if used individually
        String key = stateCode + areaCode + supersectorCode + industryCode + dataTypeCode;

        // Look up the data
        if (dataMap.containsKey(key)) {
            String[] details = dataMap.get(key);
            System.out.println("Prefix: " + prefix);
            System.out.println("Seasonal Adjustment Code: " + seasonalAdjustmentCode);
            System.out.println("State Name: " + details[0]);  // Assumes state name is the first element
            System.out.println("Area Name: " + details[1]);   // Assumes area name is the second element
            System.out.println("Supersector Name: " + details[2]);  // Assumes supersector name is the third element
            System.out.println("Industry Name: " + details[3]);  // Assumes industry name is the fourth element
            System.out.println("Data Type Text: " + details[4]);  // Assumes data type text is the fifth element
        } else {
            System.out.println("No data found for this series ID: " + key);
        }
    }


}
//    public static void main(String[] args) {
//        String seriesID = "SMU09000005500000002";
//        String filePath = "src/main/java/decoder_files/smu_decoder_file.xlsx";
//
//        // Load the series data from Excel
//        Map<String, String[]> seriesDataMap = loadSeriesData(filePath);
//
//        // Decode the series ID
//        if (seriesDataMap.containsKey(seriesID)) {
//            String[] details = seriesDataMap.get(seriesID);
//            System.out.println("State Code: " + details[0]);
//            System.out.println("State Name: " + details[1]);
//            System.out.println("Area Code: " + details[2]);
//            System.out.println("Area Name: " + details[3]);
//            System.out.println("Supersector Code: " + details[4]);
//            System.out.println("Supersector Name: " + details[5]);
//            System.out.println("Industry Code: " + details[6]);
//            System.out.println("Industry Name: " + details[7]);
//            System.out.println("Data Type Code: " + details[8]);
//            System.out.println("Data Type Text: " + details[9]);
//        } else {
//            System.out.println("Series ID not found in the Excel file.");
//        }
//    }
//
//    private static Map<String, String[]> loadSeriesData(String filePath) {
//        Map<String, String[]> seriesData = new HashMap<>();
//        try (FileInputStream fis = new FileInputStream(new File(filePath));
//             Workbook workbook = new XSSFWorkbook(fis)) {
//
//            Sheet sheet = workbook.getSheetAt(0);
//            boolean firstRow = true;
//            for (Row row : sheet) {
//                if (firstRow) {
//                    firstRow = false;
//                    continue;
//                }
//                Cell seriesCell = row.getCell(0);
//                if (seriesCell != null) {
//                    String seriesID = seriesCell.getStringCellValue();
//                    String[] details = new String[10];
//                    for (int i = 0; i < 10; i++) {
//                        Cell cell = row.getCell(i);
//                        details[i] = cell.getStringCellValue();
//                    }
//                    seriesData.put(seriesID, details);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return seriesData;
//    }
//}
