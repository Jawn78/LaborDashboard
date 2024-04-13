package bls;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;

public class requestAPISurvey {
    public static void main(String[] args) throws IOException {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        String jsonPayload = "{"+
//                "\"seriesid\":[\"LAUCN040010000000005\", \"LAUCN040010000000006\"], " +
//                "\"startyear\":\"2022\", \"endyear\":\"2023\", " +
//                "\"catalog\":false, \"calculations\":false, " +
                "\"annualaverage\":false,\"aspects\":false, " +
                "\"survey_abbreviation\": \"AP\",";
//                "\"registrationkey\":\"ef90111696de4045bf7c647d41cee652\"}";
        RequestBody body = RequestBody.create(mediaType, jsonPayload);

        Request request = new Request.Builder()
                .url("https://api.bls.gov/publicAPI/v2/surveys?survey_abbreviation=AP&registrationkey=ef90111696de4045bf7c647d41cee652")
                .post(body)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }
}
