package decoder_files;

import data_models.SMSeriesIDData;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class sm_series_id_decoder {
    private static final Map<String, String> seasonallyAdjustedLookup = new HashMap<>();
    private static final Map<String, String> stateCodeLookup = new HashMap<>();
    private static final Map<String, String> areaCodeLookup = new HashMap<>();
    private static final Map<String, String> supersectorCodeLookup = new HashMap<>();
    private static final Map<String, String> industryCodeLookup = new HashMap<>();
    private static final Map<String, String> dataTypeCodeLookup = new HashMap<>();
    private static Sheet sheet;

    private static void loadLookupTables(String filePath) throws IOException {
        FileInputStream file = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(file);
        sheet = workbook.getSheetAt(0);
        for (Row row : sheet) {
            if (row.getRowNum() == 0) continue; // Skip header row
            seasonallyAdjustedLookup.put(getCellValueAsString(row.getCell(0)), getCellValueAsString(row.getCell(1)));
            stateCodeLookup.put(getCellValueAsString(row.getCell(2)), getCellValueAsString(row.getCell(3)));
            areaCodeLookup.put(getCellValueAsString(row.getCell(4)), getCellValueAsString(row.getCell(5)));
            supersectorCodeLookup.put(getCellValueAsString(row.getCell(6)), getCellValueAsString(row.getCell(7)));
            industryCodeLookup.put(getCellValueAsString(row.getCell(8)), getCellValueAsString(row.getCell(9)));
            dataTypeCodeLookup.put(getCellValueAsString(row.getCell(10)), getCellValueAsString(row.getCell(11)));
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

    public static SMSeriesIDData decodeSeriesID(String seriesId) throws IOException {
        loadLookupTables("src/main/java/decoder_files/smu_decoder_file.xlsx");
        String seasonalAdjustedCode = safeSubstring(seriesId, 2, 3);
        String stateCode = safeSubstring(seriesId, 3, 5); // Extracting State Code from positions 4-5
        String areaCode = safeSubstring(seriesId, 5, 10); // Extracting Area Code from positions 6-10
        String supersectorCode = safeSubstring(seriesId, 10, 12); // Corrected to extract just the supersector part (positions 11-12)
        String industryCode = safeSubstring(seriesId, 10, 18); // Extracting combined SuperSector and Industry Code from positions 11-18
        String dataTypeCode = safeSubstring(seriesId, 18, 20); // Extracting Data Type Code from positions 19-20

//        System.out.println(stateCode);
//        System.out.println(stateCodeLookup);
        stateCode = stateCode.trim();
        areaCode = areaCode.trim();
        supersectorCode = supersectorCode.trim();
        industryCode = industryCode.trim();
        dataTypeCode = dataTypeCode.trim();

        SMSeriesIDData SMSeriesIDData = new SMSeriesIDData();
        SMSeriesIDData.setSeriesID(seriesId);
        SMSeriesIDData.setStateCode(stateCode);
        SMSeriesIDData.setSeasonalAdjustmentCode(seasonalAdjustedCode);
        SMSeriesIDData.setAreaCode(areaCode);
        SMSeriesIDData.setSupersectorCode(supersectorCode);
        SMSeriesIDData.setIndustryCode(removeLeadingZero(industryCode));
        SMSeriesIDData.setDataTypeCode(dataTypeCode);
        SMSeriesIDData.setSeasonalAdjustmentText(seasonallyAdjustedLookup.getOrDefault(seasonalAdjustedCode, "Unknown"));
        SMSeriesIDData.setStateName(stateCodeLookup.getOrDefault(removeLeadingZero(stateCode), "Unknown"));
        SMSeriesIDData.setAreaName(areaCodeLookup.getOrDefault(removeLeadingZero(areaCode), "Unknown"));
        SMSeriesIDData.setSupersectorName(supersectorCodeLookup.getOrDefault(removeLeadingZero(supersectorCode), "Unknown"));
        SMSeriesIDData.setIndustryName(industryCodeLookup.getOrDefault(removeLeadingZero(industryCode), "Unknown"));
        SMSeriesIDData.setDataTypeText(dataTypeCodeLookup.getOrDefault(removeLeadingZero(dataTypeCode), "Unknown"));
        return SMSeriesIDData;
    }


    private static String removeLeadingZero(String code) {
        code = code.replaceFirst("^0+(?!$)", ""); // Trim leading zeros
//        System.out.println(code);
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
