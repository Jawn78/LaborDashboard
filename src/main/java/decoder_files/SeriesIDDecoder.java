package decoder_files;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SeriesIDDecoder {
    private static final Map<String, String> stateCodeLookup = new HashMap<>();
    private static final Map<String, String> areaCodeLookup = new HashMap<>();
    private static final Map<String, String> supersectorCodeLookup = new HashMap<>();
    private static final Map<String, String> industryCodeLookup = new HashMap<>();
    private static final Map<String, String> dataTypeCodeLookup = new HashMap<>();
    private static Sheet sheet;

    public static void main(String[] args) throws IOException {
        loadLookupTables("src/main/java/decoder_files/smu_decoder_file.xlsx");
//        decodeSeriesID("SMU0900000550000000202");
          decodeSeriesID("SMU09000005500000002");
    }

    private static void loadLookupTables(String filePath) throws IOException {
        FileInputStream file = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(file);
        sheet = workbook.getSheetAt(0);
        for (Row row : sheet) {
            if (row.getRowNum() == 0) continue; // Skip header row
            stateCodeLookup.put(getCellValueAsString(row.getCell(0)), getCellValueAsString(row.getCell(1)));
            areaCodeLookup.put(getCellValueAsString(row.getCell(2)), getCellValueAsString(row.getCell(3)));
            supersectorCodeLookup.put(getCellValueAsString(row.getCell(4)), getCellValueAsString(row.getCell(5)));
            industryCodeLookup.put(getCellValueAsString(row.getCell(6)), getCellValueAsString(row.getCell(7)));
            dataTypeCodeLookup.put(getCellValueAsString(row.getCell(8)), getCellValueAsString(row.getCell(9)));
        }

        workbook.close();
        file.close();
    }
    private static String getCellValueAsString(Cell cell) {
        if (cell == null) return "Unknown"; // Handle null cells
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    // Check if the numeric value is zero
                    if (cell.getNumericCellValue() == 0) {
                        return "0"; // Return "0" for numeric zero
                    } else {
                        return NumberToTextConverter.toText(cell.getNumericCellValue());
                    }
                }
            default:
                return "Unknown";
        }
    }

    private static void decodeSeriesID(String seriesId) {
        String stateCode = safeSubstring(seriesId, 3, 5); // Extracting State Code from positions 4-5
        String areaCode = safeSubstring(seriesId, 5, 10); // Extracting Area Code from positions 6-10
        String supersectorCode = safeSubstring(seriesId, 10, 12); // Corrected to extract just the supersector part (positions 11-12)
        String industryCode = safeSubstring(seriesId, 10, 18); // Extracting combined SuperSector and Industry Code from positions 11-18
        String dataTypeCode = safeSubstring(seriesId, 18, 20); // Extracting Data Type Code from positions 19-20

        stateCode = stateCode.trim();
        areaCode = areaCode.trim();
        supersectorCode = supersectorCode.trim();
        industryCode = industryCode.trim();
        dataTypeCode = dataTypeCode.trim();

        String stateName = stateCodeLookup.getOrDefault(removeLeadingZero(stateCode), "Unknown");
        String areaName = areaCodeLookup.getOrDefault(removeLeadingZero(areaCode), "Unknown");
        String supersectorName = supersectorCodeLookup.getOrDefault(supersectorCode, "Unknown");
        String industryName = industryCodeLookup.getOrDefault(industryCode, "Unknown");
        String dataTypeText = dataTypeCodeLookup.getOrDefault(removeLeadingZero(dataTypeCode), "Unknown");
        System.out.println(areaCodeLookup.size());
        System.out.println("State Code: " + stateCode + ", State Name: " + stateName);
        System.out.println("Area Code: " + areaCode + ", Area Name: " + areaName);
        System.out.println("Supersector Code: " + supersectorCode + ", Supersector Name: " + supersectorName);
        System.out.println("Industry Code: " + industryCode + ", Industry Name: " + industryName);
        System.out.println("Data Type Code: " + dataTypeCode + ", Data Type Text: " + dataTypeText);

    }


    private static String removeLeadingZero(String code) {
        code = code.replaceFirst("^0+(?!$)", ""); // Trim leading zeros
        System.out.println(code);
        return code;
    }

    private static String safeSubstring(String str, int beginIndex, int endIndex) {
        if (str.length() >= endIndex) {
            return str.substring(beginIndex, endIndex);
        } else if (str.length() > beginIndex) {
            return str.substring(beginIndex);
        } else {
            return "";  // Or "Unknown" if it makes more sense for your context
        }
    }


}
