package util;

import data_models.Decoder_Models.DecoderModel;
import data_models.Decoder_Models.DecoderModelParts;
import data_models.Decoder_Models.KeyPair;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SeriesIDDecoder {



        public SeriesIDDecoder(String filePath) {
            DecoderModel model = loadModels(filePath);
        }

        private static DecoderModel loadModels(String filePath) {
            DecoderModel model = new DecoderModel();
            try (FileInputStream fis = new FileInputStream(filePath);
                Workbook workbook = WorkbookFactory.create(fis)) {
                Sheet formatSheet = workbook.getSheetAt(0); // Assuming this is the correct sheet index
                Sheet compositeDecoderSheet = workbook.getSheetAt(1);
                Iterator<Row> rowIterator = formatSheet.iterator();

                if (rowIterator.hasNext()) rowIterator.next(); // Skip the header row
                while (rowIterator.hasNext()) {
                    Row row = rowIterator.next();
                    DecoderModelParts modelParts = new DecoderModelParts();

                    String partNumberStr = getCellValue(row.getCell(0));
                    if (isNumeric(partNumberStr)) {
                        modelParts.setPart_number(Integer.parseInt(partNumberStr));
                        modelParts.setIdentifier(getCellValue(row.getCell(1)));
                        modelParts.setStart_position(Integer.parseInt(getCellValue(row.getCell(2))));
                        modelParts.setEnd_position(Integer.parseInt(getCellValue(row.getCell(3))));
                        model.addDecoderModelParts(modelParts); // Append or set parts based on the design decision
                    }
                }
                model.setPrefix("sm");
//              model.setKeypairs(columns(compositeDecoderSheet));

            } catch (Exception e) {
                e.printStackTrace();
            }
            return model;
        }

        private static boolean isNumeric(String strNum) {
            if (strNum == null) {
                return false;
            }
            try {
                Double.parseDouble(strNum);
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
                    return "";
                default:
                    return "";
            }
        }
        public static List<List<KeyPair>> columns(Sheet sheet) {
            List<List<KeyPair>> columns = new ArrayList<>();

            Iterator<Row> rowIterator = sheet.iterator();

            if (rowIterator.hasNext()) {
                rowIterator.next(); // Skip the header row
            }

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

//    static class DecoderModel{
//        String prefix;
//        DecoderModelParts decoderModelParts;
//    }
//
//    static class DecoderModelParts {
//        public int part_number;
//        public String identifier;
//        public int start_position;
//        public int end_position;
//        public List<List<KeyPair>> keypairs;
//    }
//
//    static class KeyPair {
//        String key;
//        String value;
//
//        public KeyPair(String key, String value) {
//            this.key = key;
//            this.value = value;
//        }
//    }

    public static void main(String[] args) {
//        try (FileInputStream fis = new FileInputStream("src/main/java/excel_decoder_files/smu_decoder_file.xlsx");
//             Workbook workbook = WorkbookFactory.create(fis)) {
//            Sheet formatSheet = workbook.getSheetAt(1);
//
//           }catch(Exception e){
        DecoderModel model = loadModels("src/main/java/excel_decoder_files/smu_decoder_file.xlsx");
        System.out.println(model.getDecoderModelParts().size());
        System.out.println("Model Prefix: " + model.getPrefix());
//        System.out.println(model.getKeypairs().get(0).get(0).getKey());
//        System.out.println(model.getKeypairs().get(0).get(0).getValue());
        System.out.println(model.getDecoderModelParts().size());
        for(DecoderModelParts parts : model.getDecoderModelParts()){

//                System.out.println("Part Number: " + part.getPart_number() +
//                        ", Identifier: " + part.getIdentifier() +
//                        ", Start Position: " + part.getStart_position() +
//                        ", End Position: " + part.getEnd_position());


        }
        }
        }