import bls.Datum;
import bls.Series;
import com.google.gson.Gson;
import okhttp3.*;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

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
                    "\"LNS11300000\"," +
                    "\"CES0000000001\", " + // Placeholder for CES State and Metro Area
                    " \"LNS14000000\", " +
                    "\"LNS13000000\"], " +
                    "\"startyear\":\"2023\", \"endyear\":\"2024\", " +  // Updated years to 2023-2024
                    "\"catalog\":false, \"calculations\":false, " +
                    "\"annualaverage\":false, \"aspects\":false, " +
                    "\"registrationkey\":\"" + apiKey +"\"}";

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

            Gson gson = new Gson();
            bls.Response responseJSON = gson.fromJson(responseString, bls.Response.class);

            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Labor Data");

            // Create headers
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("Series ID");
            header.createCell(1).setCellValue("Year");
            header.createCell(2).setCellValue("Period");
            header.createCell(3).setCellValue("Value");

            int rowNum = 1;
            for (Series series : responseJSON.getResults().getSeries()) {
                for (Datum dataPoint : series.getData()) {
                    Row row = sheet.createRow(rowNum++);
                    row.createCell(0).setCellValue(series.getSeriesID());
                    row.createCell(1).setCellValue(dataPoint.getYear());
                    row.createCell(2).setCellValue(dataPoint.getPeriod());
                    row.createCell(3).setCellValue(dataPoint.getValue());
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

//    public static void main(String[] args) throws IOException {
//        OkHttpClient client = new OkHttpClient();
//
//        MediaType mediaType = MediaType.parse("application/json");
//        String jsonPayload = "{\"seriesid\": [\"LNS11300000\", \"LNS14000000\", \"LNS13000000\"], " +
//                "\"startyear\":\"2022\", \"endyear\":\"2024\", " +
//                "\"catalog\":false, \"calculations\":false, " +
//                "\"annualaverage\":false, \"aspects\":false, " +
//                "\"registrationkey\":\"ef90111696de4045bf7c647d41cee652\"}";
//
//        RequestBody body = RequestBody.create(mediaType, jsonPayload);
//
//        Request request = new Request.Builder()
//                .url("https://api.bls.gov/publicAPI/v2/timeseries/data?")
//                .post(body)
//                .addHeader("Content-Type", "application/json")
//                .build();
//
//        Gson gson = new Gson();
//        Response response = client.newCall(request).execute();
//        String responseString = response.body().string();
//        bls.Response responseJSON = gson.fromJson(responseString, bls.Response.class);
//        response.body().close();
//
//        // Apache POI logic to create Excel
//        Workbook workbook = new XSSFWorkbook();
//        Sheet sheet = workbook.createSheet("Labor Data");
//
//        // Create headers
//        Row header = sheet.createRow(0);
//        header.createCell(0).setCellValue("Series ID");
//        header.createCell(1).setCellValue("Year");
//        header.createCell(2).setCellValue("Period");
//        header.createCell(3).setCellValue("Value");
//
//        int rowNum = 1;
//        for (Series series : responseJSON.getResults().getSeries()) {
//            for (Datum dataPoint : series.getData()) {
//                Row row = sheet.createRow(rowNum++);
//                row.createCell(0).setCellValue(series.getSeriesID());
//                row.createCell(1).setCellValue(dataPoint.getYear());
//                row.createCell(2).setCellValue(dataPoint.getPeriod());
//                row.createCell(3).setCellValue(dataPoint.getValue());
//            }
//        }
//
//        // Write the output to a file
//        try (FileOutputStream fileOut = new FileOutputStream("LaborData.xlsx")) {
//            workbook.write(fileOut);
//        }
//        workbook.close();
//
//        System.out.println("Excel file has been created successfully.");
//    }
//}



//    public static void main(String[] args) throws IOException {
//        OkHttpClient client = new OkHttpClient();
//
//        MediaType mediaType = MediaType.parse("application/json");
//        String jsonPayload = "{\"seriesid\": [\"LNS11300000\", \"LNS14000000\", \"LNS13000000\"], " +
//                "\"startyear\":\"2022\", \"endyear\":\"2024\", " +
//                "\"catalog\":false, \"calculations\":false, " +
//                "\"annualaverage\":false, \"aspects\":false, " +
//                "\"registrationkey\":\"ef90111696de4045bf7c647d41cee652\"}";
//
//        RequestBody body = RequestBody.create(mediaType, jsonPayload);
//
//        Request request = new Request.Builder()
//                .url("https://api.bls.gov/publicAPI/v2/timeseries/data?")
//                .post(body)
//                .addHeader("Content-Type", "application/json")
//                .build();
//
//        Gson gson = new Gson();
//        Response response = client.newCall(request).execute();
//        String responseString = response.body().string();
//        bls.Response responseJSON = gson.fromJson(responseString, bls.Response.class);
//
//        System.out.println(responseString);
//        List<Series> seriesList = responseJSON.getResults().getSeries();
//        for (Series series : seriesList) {
//            System.out.println("Series ID: " + series.getSeriesID());
//            System.out.println("First data point: " + series.getData().get(0));
//            System.out.println("Second data point: " + series.getData().get(1));
//        }
//
//        response.body().close(); // Ensure to close the response body after use
//    }
//}


    // This works!!
//    public static void main(String[] args) throws IOException {
//        OkHttpClient client = new OkHttpClient();
//
//        MediaType mediaType = MediaType.parse("application/json");
//        String jsonPayload = "{\"seriesid\":" + "\"LNS11300000\",\n" +
//
////                "\"LNS11000000\",\n" +
////                "    \"LNS12000000\",\n" +
////                "    \"LNS13000000\",\n" +
////                "    \"LNS14000000\",\n" +
////                "    \"CES0000000001\",\n" +
////                "    \"CES0500000002\",\n" +
////                "    \"CES0500000007\",\n" +
////                "    \"CES0500000003\",\n" +
////                "    \"CES0500000008\"], " +
//                "\"startyear\":\"2022\", \"endyear\":\"2024\", " +
//                "\"catalog\":false, \"calculations\":false, " +
//                "\"annualaverage\":false,\"aspects\":false, " +
//                "\"registrationkey\":\"ef90111696de4045bf7c647d41cee652\"}";
//        RequestBody body = RequestBody.create(mediaType, jsonPayload);
//
//        Request request = new Request.Builder()
//                .url("https://api.bls.gov/publicAPI/v2/timeseries/data?")
//                .post(body)
//                .addHeader("Content-Type", "application/json")
//                .build();
//        Gson gson = new Gson();
//        Response response = client.newCall(request).execute();
////        System.out.println(response.body().string());
//        String responseString = response.body().string();
//        bls.Response responseJSON = gson.fromJson(responseString, bls.Response.class);
////        responseJSON.getData();
//        System.out.println(responseString);
//        List<Series> hello = responseJSON.getResults().getSeries();
//        System.out.println(hello.get(0).getData().get(1));
//        System.out.println(hello.get(0).getData().get(0));
//        // Ensure to close the response body after use
//
//    }

//    public static void requestPOJO(Response response){
//        try (ResponseBody responseBody = response.body()) {
//
//            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
//
//            // Safely get the response body as a String if non-null
//            //String variableFoo = responseBody != null ? responseBody.string() : null;
//            Gson gson = new Gson();
//            bls.Response responseJSON = gson.fromJson(responseBody.string(), bls.Response.class);
//            responseJSON.getData();
//            System.out.println(responseJSON);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

