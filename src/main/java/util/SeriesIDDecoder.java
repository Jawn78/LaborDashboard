package util;

import data_models.Decoder_Models.DecoderModel;
import data_models.Decoder_Models.DecoderModelParts;
import data_models.Decoder_Models.KeyPair;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class SeriesIDDecoder {

    public static DecoderModel loadModelsWithID(String SeriesID) {
        String filePath = getFilePathPrefix(SeriesID);
        DecoderModel model = loadModelsWithPath(filePath);
        model.setPrefix(SeriesID.substring(0, 2).toLowerCase(Locale.ROOT));
        return model;
    }

    public static DecoderModel loadModelsWithPath(String filePath) {
        DecoderModel model = new DecoderModel();
        int partNumber = 1;
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(fis)) {
            Sheet formatStructureSheet = workbook.getSheetAt(0); // Assuming this is the correct sheet index
            Sheet compositeDecodeSheet = workbook.getSheetAt(1);
            Iterator<Row> rowIterator = formatStructureSheet.iterator();
            if (rowIterator.hasNext()) {
                rowIterator.next(); // Skip the header row
            }
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();

                DecoderModelParts modelParts = new DecoderModelParts();
                modelParts.setKeypairs(new ArrayList<>()); // Initialize key pairs list

                String partNumberStr = getCellValue(row.getCell(0)); // Assuming part number is in the first cell
//                System.out.println(partNumberStr);
                modelParts.setIdentifier(partNumberStr); // Set the identifier as the key name from the table

                // Parse the start and end positions
                String startPositionStr = getCellValue(row.getCell(1));
                String endPositionStr = getCellValue(row.getCell(2));

                if (isNumeric(startPositionStr) && isNumeric(endPositionStr)) {
                    int startPosition = Integer.parseInt(startPositionStr);
                    int endPosition = Integer.parseInt(endPositionStr);
                    if (endPosition == 0) { // Special case handling if endPosition is 0
                        endPosition = startPosition; // or some other logic as needed
                    }
                    modelParts.setStart_position(startPosition);
                    modelParts.setEnd_position(endPosition);
                }
                modelParts.setPart_number(partNumber); // Increment identifier for each part
                // Add parts to the model
                model.addDecoderModelParts(modelParts);
                partNumber++;
            }
            model.setPrefix(extractSeriesID(filePath));
            loadKeyPairs(compositeDecodeSheet, model);

        } catch (FileNotFoundException e) {
            System.out.println("The file was not found at: " + filePath + " " + e);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return model;
    }

    // Check if the value is able to be parsed as an integer
    private static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            Integer.parseInt(strNum);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    private static String getCellValue(Cell cell) {
        if (cell == null) return "";
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
                    return sdf.format(cell.getDateCellValue());
                } else {
                    return new java.text.DecimalFormat("0").format(cell.getNumericCellValue());
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case BLANK:
            default:
                return "";
        }
    }

    // Get KeyPair columns
    public static List<List<KeyPair>> columns(Sheet sheet) {
        List<List<KeyPair>> columns = new ArrayList<>();
        Iterator<Row> rowIterator = sheet.iterator();

//        // Skip the header row
//        if (rowIterator.hasNext()) {
//            rowIterator.next();
//        }

        int numCols = sheet.getRow(0).getLastCellNum();
        for (int i = 0; i < numCols; i += 2) {
            columns.add(new ArrayList<>());
        }

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            for (int colIndex = 0; colIndex < numCols; colIndex += 2) {
                if (colIndex + 1 < numCols) {
                    Cell keyCell = row.getCell(colIndex, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    Cell valueCell = row.getCell(colIndex + 1, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    String key = getCellValue(keyCell);
                    String value = getCellValue(valueCell);
                    if (key != null && !key.isEmpty() && value != null && !value.isEmpty()) {
                        columns.get(colIndex / 2).add(new KeyPair(key, value));
                    }
                }
            }
        }
        return columns;
    }

    public static DecoderModel loadKeyPairs(Sheet sheet, DecoderModel model) {
        List<List<KeyPair>> columnsPair = columns(sheet);
        int modelPartIndex = 1; // Start from 1 to skip the "Prefix" part
        for (int i = 0; i < columnsPair.size(); i++) {
            List<KeyPair> keyPairs = columnsPair.get(i);
            if (modelPartIndex < model.getDecoderModelParts().size()) {
                model.getDecoderModelParts().get(modelPartIndex).setKeypairs(keyPairs);
                modelPartIndex++;
            } else {
                System.out.println("Warning: Extra key pair column found in sheet, but no corresponding model part.");
            }
        }

        return model;
    }

    public static String extractSeriesID(String filePath) {
        // Base directory prefix
        String basePrefix = "src/main/java/excel_decoder_files/";
        // Suffix for the files
        String suffix = "_decoder_file.xlsx";

        // Check if the file path starts with the base prefix and ends with the suffix
        if (filePath.startsWith(basePrefix) && filePath.endsWith(suffix)) {
            // Calculate the start index of the series ID substring
            int startIndex = basePrefix.length();
            // Calculate the end index of the series ID substring
            int endIndex = filePath.length() - suffix.length();
            // Extract the series ID substring
            return filePath.substring(startIndex, endIndex);
        } else {
            // If the file path format is incorrect, return an empty string or throw an exception
            return ""; // or throw new IllegalArgumentException("Invalid file path format.");
        }
    }

    public static String getFilePathPrefix(String SeriesID) {
        String filePath = "src/main/java/excel_decoder_files/" + SeriesID.substring(0, 2).toLowerCase(Locale.ROOT) + "_decoder_file.xlsx";
        return filePath;
    }

    public static void main(String[] args) {
        String seriesID = "SMS01000000000000001";
        DecoderModel model = loadModelsWithID(seriesID);
        for (DecoderModelParts part : model.getDecoderModelParts()) {
            int start = part.getStart_position() - 1; // Adjust for 0-based index
            int end = part.getEnd_position() == 0 ? seriesID.length() : part.getEnd_position();
            if (start < end && start < seriesID.length() && end <= seriesID.length()) {
                String extractedPart = seriesID.substring(start, end);
                System.out.println("For part " + part.getIdentifier() + " the size is " + part.getKeypairs().size());
                // String decodedValue = decodePart(part.getKeypairs(), extractedPart);
                // result.append(part.getIdentifier()).append(": ").append(decodedValue).append(", ");


            }
        }
    }
}
