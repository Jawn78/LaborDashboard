import com.google.gson.Gson;
import data_models.Config_Models.ConfigPOJO;
import excel_writers.ln_excel_writer;
import excel_writers.sm_excel_writer;
import okhttp3.*;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static util.Config.Manager.getConfig;
import static util.Config.Manager.loadConfig;

public class RequestAPI {

    private static final OkHttpClient client = new OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS) // Increase read timeout
            .connectTimeout(60, TimeUnit.SECONDS) // Increase connection timeout
            .build();
    private static final Gson gson = new Gson();

    private static String readApiKey() throws IOException {
        return getConfig().getBLSApiKey().trim();
    }

    private static List<List<String>> partitionList(List<String> list) {
        return IntStream.range(0, list.size())
                .boxed()
                .collect(Collectors.groupingBy(index -> index / 50))
                .values()
                .stream()
                .map(indices -> indices.stream().map(list::get).collect(Collectors.toList()))
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        loadConfig("src/main/java/config.json");
        Workbook workbook = new XSSFWorkbook();
        Sheet sm_sheet = workbook.createSheet("SM Labor Data");
        Sheet ln_sheet = workbook.createSheet("LN Labor Data");
        sm_excel_writer.createHeaders(sm_sheet);
        ln_excel_writer.createHeaders(ln_sheet);

        String fileName = "LaborData.xlsx";
        String apiKey;
        try {
            apiKey = readApiKey();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        MediaType mediaType = MediaType.parse("application/json");

                List<String> seriesIDs = List.of("LNS12300001", "LNS12300002", "LNS12500001",
//                        , "LNS12500002", "LNS12600001", "LNS12600002", "LNS13023569", "LNS13023622", "LNS13023654", "LNS13023706", "LNS13026511", "LNS14000000", "LNS14000001", "LNS14000002", "LNS14100000", "LNU00000001", "LNU00000002", "LNU00000012", "LNU00000013", "LNU00000014", "LNU00000024", "LNU00000025", "LNU00000026", "LNU00000036", "LNU00000037", "LNU00000038", "LNU00000048", "LNU00000049", "LNU00000050", "LNU00000060", "LNU00000061", "LNU00000062", "LNU00000086", "LNU00000088", "LNU00000089", "LNU00000091", "LNU00000092", "LNU00000093", "LNU00000094", "LNU00000095", "LNU00000096", "LNU00000097", "LNU00000152", "LNU00000154", "LNU00000163", "LNU00000164", "LNU00000171", "LNU00000172", "LNU00000173", "LNU00000179", "LNU00000180", "LNU00000181", "LNU00000182", "LNU00000188", "LNU00000189", "LNU00000190", "LNU00000196", "LNU00000197", "LNU00000198", "LNU00000199", "LNU00000203", "LNU00000204", "LNU00000317", "LNU00000319", "LNU00000326", "LNU00000327", "LNU00000332", "LNU00000333", "LNU00000334", "LNU00000338", "LNU00000339", "LNU00000340", "LNU00000341", "LNU00000345", "LNU00000346", "LNU00000347", "LNU00000351", "LNU00000352", "LNU00000353", "LNU00000354", "LNU00000358", "LNU00015346", "LNU00015349", "LNU00024230", "LNU00024231", "LNU00024232", "LNU00024885", "LNU00024886", "LNU00024887", "LNU00024932", "LNU00024933", "LNU00024934", "LNU00024935", "LNU00024936", "LNU00024937", "LNU00024938", "LNU00024939", "LNU00024940", "LNU00024941", "LNU00024942", "LNU00076975", "LNU00076980", "LNU00076985", "LNU00091113", "LNU00091114", "LNU00091115", "LNU01000001", "LNU01000002", "LNU01000024", "LNU01000036", "LNU01000060", "LNU01000088", "LNU01000089", "LNU01000091", "LNU01000092", "LNU01000093", "LNU01000094", "LNU01000095", "LNU01000096", "LNU01000097", "LNU01022996", "LNU01023002", "LNU01023008", "LNU01024230", "LNU01024231", "LNU01024232", "LNU01024887", "LNU01024932", "LNU01024933", "LNU01024934", "LNU01024935", "LNU01024936", "LNU01024937", "LNU01024938", "LNU01024941", "LNU01024942", "LNU01300000", "LNU01300001", "LNU01300002", "LNU01300012", "LNU01300013", "LNU01300014", "LNU01300024", "LNU01300025", "LNU01300026", "LNU01300036", "LNU01300037", "LNU01300038", "LNU01300048", "LNU01300049", "LNU01300050", "LNU01300060", "LNU01300061", "LNU01300062", "LNU01300086", "LNU01300088", "LNU01300089", "LNU01300091", "LNU01300092", "LNU01300093", "LNU01300094", "LNU01300095", "LNU01300096", "LNU01300097", "LNU01300152", "LNU01300154", "LNU01300163", "LNU01300164", "LNU01300171", "LNU01300172", "LNU01300173", "LNU01300179", "LNU01300180", "LNU01300181", "LNU01300182", "LNU01300188", "LNU01300189", "LNU01300190", "LNU01300196", "LNU01300197", "LNU01300198", "LNU01300199", "LNU01300203", "LNU01300204", "LNU01300317", "LNU01300319", "LNU01300326", "LNU01300327", "LNU01300332", "LNU01300333", "LNU01300334", "LNU01300338", "LNU01300339", "LNU01300340", "LNU01300341", "LNU01300345", "LNU01300346", "LNU01300347", "LNU01300351", "LNU01300352", "LNU01300353", "LNU01300354", "LNU01300358", "LNU01300359", "LNU01315346", "LNU01315349", "LNU01324230", "LNU01324231", "LNU01324232", "LNU01324885", "LNU01324886", "LNU01324887", "LNU01324932", "LNU01324933", "LNU01324934", "LNU01324935", "LNU01324936", "LNU01324937", "LNU01324938", "LNU01324939", "LNU01324940", "LNU01324941", "LNU01324942", "LNU01327683", "LNU01376975", "LNU01376980", "LNU01376985", "LNU01391113", "LNU01392221", "LNU02000012", "LNU02000013", "LNU02000014", "LNU02000024", "LNU02000025", "LNU02000026", "LNU02000036", "LNU02000037", "LNU02000038", "LNU02000048", "LNU02000049", "LNU02000050", "LNU02000060", "LNU02000061", "LNU02000062", "LNU02000086", "LNU02000088", "LNU02000089", "LNU02000091", "LNU02000092", "LNU02000093", "LNU02000094", "LNU02000095", "LNU02000096", "LNU02000097", "LNU02000152", "LNU02000154", "LNU02000163", "LNU02000164", "LNU02000171", "LNU02000172", "LNU02000173", "LNU02000179", "LNU02000180", "LNU02000181", "LNU02000182", "LNU02000188", "LNU02000189", "LNU02000190", "LNU02000196", "LNU02000197", "LNU02000198", "LNU02000199", "LNU02000203", "LNU02000317", "LNU02000319", "LNU02000326", "LNU02000327", "LNU02000332", "LNU02000333", "LNU02000334", "LNU02000338", "LNU02000339", "LNU02000340", "LNU02000341", "LNU02000345", "LNU02000346", "LNU02000347", "LNU02000351", "LNU02000352", "LNU02000353", "LNU02000354", "LNU02000358", "LNU02005054", "LNU02005118", "LNU02005150", "LNU02005646", "LNU02005678", "LNU02005812", "LNU02005844", "LNU02005978", "LNU02006010", "LNU02015346", "LNU02015349", "LNU02024230", "LNU02024231", "LNU02024232", "LNU02024687", "LNU02024885", "LNU02024886", "LNU02024887", "LNU02024932", "LNU02024933", "LNU02024934", "LNU02024935", "LNU02024936", "LNU02024937", "LNU02024938", "LNU02024939", "LNU02024940", "LNU02024941", "LNU02024942", "LNU02026619", "LNU02026620", "LNU02026621", "LNU02026622", "LNU02026623", "LNU02026624", "LNU02026625", "LNU02026626", "LNU02026627", "LNU02026628", "LNU02026629", "LNU02026630", "LNU02026631", "LNU02026632", "LNU02026633", "LNU02026813", "LNU02026815", "LNU02026817", "LNU02026819", "LNU02026821", "LNU02026823", "LNU02026825", "LNU02026827", "LNU02026841", "LNU02026843", "LNU02026845", "LNU02026847", "LNU02026849", "LNU02026851", "LNU02026853", "LNU02026855", "LNU02026869", "LNU02026871", "LNU02026873", "LNU02026875", "LNU02026877", "LNU02026879", "LNU02026881", "LNU02026883", "LNU02026938", "LNU02026939", "LNU02026940", "LNU02026941", "LNU02026942", "LNU02026943", "LNU02026944", "LNU02026945", "LNU02026946", "LNU02026947", "LNU02026948", "LNU02026949", "LNU02026951", "LNU02026952", "LNU02026967", "LNU02026968", "LNU02026969", "LNU02026970", "LNU02026972", "LNU02026973", "LNU02033120", "LNU02033447", "LNU02033503", "LNU02033510", "LNU02033566", "LNS12026619", "LNS12032194", "LNS12300000", "LNS12500000", "LNS12600000", "LNS13008275", "LNS11000001", "LNS11000000", "LNS11000002",
                "SMS01000000000000001"
//                        , "SMS01000000000000026", "SMS02000000000000001", "SMS02000000000000026", "SMS04000000000000001", "SMS04000000000000026", "SMS05000000000000001", "SMS05`000000000000026", "SMS06000000000000001", "SMS06000000000000026", "SMS08000000000000001", "SMS08000000000000026", "SMS09000000000000001", "SMS09000000000000026", "SMS10000000000000001", "SMS10000000000000026", "SMS11000000000000001", "SMS11000000000000026", "SMS12000000000000001", "SMS12000000000000026", "SMS13000000000000001", "SMS13000000000000026", "SMS15000000000000001", "SMS15000000000000026", "SMS16000000000000001", "SMS16000000000000026", "SMS17000000000000001", "SMS17000000000000026", "SMS18000000000000001", "SMS18000000000000026", "SMS19000000000000001", "SMS19000000000000026", "SMS20000000000000001", "SMS20000000000000026", "SMS21000000000000001", "SMS21000000000000026", "SMS22000000000000001", "SMS22000000000000026", "SMS23000000000000001", "SMS23000000000000026", "SMS24000000000000001", "SMS24000000000000026", "SMS25000000000000001", "SMS25000000000000026", "SMS26000000000000001", "SMS26000000000000026", "SMS27000000000000001", "SMS27000000000000026", "SMS28000000000000001", "SMS28000000000000026", "SMS29000000000000001", "SMS29000000000000026", "SMS30000000000000001", "SMS30000000000000026", "SMS31000000000000001", "SMS31000000000000026", "SMS32000000000000001", "SMS32000000000000026", "SMS33000000000000001", "SMS33000000000000026", "SMS34000000000000001", "SMS34000000000000026", "SMS35000000000000001", "SMS35000000000000026", "SMS36000000000000001", "SMS36000000000000026", "SMS37000000000000001", "SMS37000000000000026", "SMS38000000000000001", "SMS38000000000000026", "SMS39000000000000001", "SMS39000000000000026", "SMS40000000000000001", "SMS40000000000000026", "SMS41000000000000001", "SMS41000000000000026", "SMS42000000000000001", "SMS42000000000000026", "SMS72000000000000001", "SMS72000000000000026", "SMS44000000000000001", "SMS44000000000000026", "SMS45000000000000001", "SMS45000000000000026", "SMS46000000000000001", "SMS46000000000000026", "SMS47000000000000001", "SMS47000000000000026", "SMS48000000000000001", "SMS48000000000000026", "SMS48000000000000001", "SMS48000000000000026", "SMS49000000000000001", "SMS49000000000000026", "SMS50000000000000001", "SMS50000000000000026", "SMS51000000000000001", "SMS51000000000000026", "SMS78000000000000001", "SMS78000000000000026", "SMS53000000000000001", "SMS53000000000000026", "SMS54000000000000001", "SMS54000000000000026", "SMS55000000000000001", "SMS55000000000000026", "SMS56000000000000001", "SMS56000000000000026"
//
                );

        List<List<String>> partitions = partitionList(seriesIDs);

        String finalApiKey = apiKey;
        CompletableFuture<?>[] futures = partitions.stream().map(partition ->
                CompletableFuture.runAsync(() -> {
                    try {
                        processPartition(partition, finalApiKey, mediaType, sm_sheet, ln_sheet);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                })
        ).toArray(CompletableFuture[]::new);

        CompletableFuture.allOf(futures).join();

        // Write and close the workbook after all data has been processed
        try (FileOutputStream fileOut = new FileOutputStream(fileName)) {
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processPartition(List<String> partition, String apiKey, MediaType mediaType, Sheet sm_sheet, Sheet ln_sheet) throws IOException {
        int maxAttempts = 5;
        int attempts = 0;
        boolean success = false;
        String seriesIdJsonArray = partition.stream().map(id -> "\"" + id + "\"").collect(Collectors.joining(", ", "[", "]"));
        String jsonPayload = String.format("{\"seriesid\": %s, \"startyear\":\"2019\", \"endyear\":\"2024\", " +
                "\"catalog\":false, \"calculations\":false, \"annualaverage\":false, \"aspects\":false, " +
                "\"registrationkey\":\"%s\"}", seriesIdJsonArray, apiKey);

        RequestBody body = RequestBody.create(jsonPayload, mediaType);
        Request request = new Request.Builder()
                .url("https://api.bls.gov/publicAPI/v2/timeseries/data")
                .post(body)
                .addHeader("Content-Type", "application/json")
                .build();
        while (attempts < maxAttempts && !success) {
            try (Response response = client.newCall(request).execute()) {
                if (!response.isSuccessful()) {
                    System.out.println("Failed to fetch data for partition: " + response);
                    return;
                }
                String responseString = Objects.requireNonNull(response.body()).string();
                data_models.BLS_API_Models.Response responseJSON = gson.fromJson(responseString, data_models.BLS_API_Models.Response.class);
                synchronized (sm_sheet) {
                    for (data_models.BLS_API_Models.Series series : responseJSON.getResults().getSeries()) {
                        if (series.getSeriesID().contains("LN")) {
                            ln_excel_writer.writeToExcel(ln_sheet, series);
                        } else if (series.getSeriesID().contains("SM")) {
                            sm_excel_writer.writeToExcel(sm_sheet, series);
                        }
                    }
                }
                success = true;
            } catch (IOException e) {
                attempts++;
                if (attempts >= maxAttempts) throw e;
                System.out.println("Retrying... Attempt " + attempts);
            }
        }
    }
}



