import bls.Datum;
import bls.Series;
import com.google.gson.Gson;
import data_models.SeriesIDData;
import decoder_files.SeriesIDDecoder;
import okhttp3.*;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static decoder_files.SeriesIDDecoder.decodeSeriesID;

public class RequestAPI {
    private static String readApiKey(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath))).trim();
    }

    public static void main(String[] args) {
        try {
            OkHttpClient client = new OkHttpClient();
            String apiKey = readApiKey("src/main/java/apikey.txt");
            MediaType mediaType = MediaType.parse("application/json");
            String jsonPayload = "{\"seriesid\": [" +
                    "\"SMU11000000500000011\"," +
                    "\"SMU09000005500000002\"], " +
//                    "\"LNS11300000\"," +
//                    "\"CES0000000001\", " + // Placeholder for CES State and Metro Area
//                    " \"LNS14000000\", " +
//                    "\"LNS13000000\"], " +
                    "\"startyear\":\"2023\", \"endyear\":\"2024\", " +  // Updated years to 2023-2024
                    "\"catalog\":false, \"calculations\":false, " +
                    "\"annualaverage\":false, \"aspects\":false, " +
                    "\"registrationkey\":\"" + apiKey + "\"}";

            RequestBody body = RequestBody.create(jsonPayload, mediaType);

            Request request = new Request.Builder()
                    .url("https://api.bls.gov/publicAPI/v2/timeseries/data")
                    .post(body)
                    .addHeader("Content-Type", "application/json")
                    .build();

            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            String responseString = response.body().string();
            response.close();

            writeToExcel(responseString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeToExcel(String responseString) {
        try {
            Gson gson = new Gson();
            bls.Response responseJSON = gson.fromJson(responseString, bls.Response.class);

            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Labor Data");

            // Create headers
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("Series ID");
            header.createCell(1).setCellValue("State Code");
            header.createCell(2).setCellValue("State");
            header.createCell(3).setCellValue("Area Code");
            header.createCell(4).setCellValue("Area");
            header.createCell(5).setCellValue("Supersector Code");
            header.createCell(6).setCellValue("Supersector");
            header.createCell(7).setCellValue("Industry Code");
            header.createCell(8).setCellValue("Industry");
            header.createCell(9).setCellValue("Data Type Code");
            header.createCell(10).setCellValue("Data Type");
            header.createCell(11).setCellValue("Year");
            header.createCell(12).setCellValue("Period");
            header.createCell(13).setCellValue("Value");

            int rowNum = 1;

            for (Series series : responseJSON.getResults().getSeries()) {
                for (Datum dataPoint : series.getData()) {
                    Row row = sheet.createRow(rowNum++);
                    String seriesID = series.getSeriesID();
                    SeriesIDData seriesData = decodeSeriesID(seriesID);
                    row.createCell(0).setCellValue(seriesData.getSeriesID());
                    row.createCell(1).setCellValue(seriesData.getStateCode());
                    row.createCell(2).setCellValue(seriesData.getStateName());
                    row.createCell(3).setCellValue(seriesData.getAreaCode());
                    row.createCell(4).setCellValue(seriesData.getAreaName());
                    row.createCell(5).setCellValue(seriesData.getSupersectorCode());
                    row.createCell(6).setCellValue(seriesData.getSupersectorName());
                    row.createCell(7).setCellValue(seriesData.getIndustryCode());
                    row.createCell(8).setCellValue(seriesData.getIndustryName());
                    row.createCell(9).setCellValue(seriesData.getDataTypeCode());
                    row.createCell(10).setCellValue(seriesData.getDataTypeText());
                    row.createCell(11).setCellValue(dataPoint.getYear());
                    row.createCell(12).setCellValue(dataPoint.getPeriod());
                    row.createCell(13).setCellValue(dataPoint.getValue());
                }
            }

            // Write the output to a file
            try (FileOutputStream fileOut = new FileOutputStream("LaborData.xlsx")) {
                workbook.write(fileOut);
            }
            workbook.close();

            System.out.println("Excel file has been created successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}



