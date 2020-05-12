package org.openhab.binding.linktap.internal.rest;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LinktapOData {
    // private final Logger logger = LoggerFactory.getLogger(OData.class);

    /*
     * public static final void main(String[] args) throws ClientProtocolException, IOException {
     * Logger logger = Logger.getLogger(LinktapOData.class);
     * LinktapOData runOdata = new LinktapOData();
     * // runOdata.resolveRedirectUrl();
     * runOdata.connectionTest();
     *
     * }
     */

    /**
     * This methods checks if URL is valid and reachable
     *
     * @return URL
     * @throws IOException
     */
    private URL connectionTest() throws IOException {
        Boolean result = false;

        URL url = new URL("https://www.link-tap.com/api/");
        HttpURLConnection huc = (HttpURLConnection) url.openConnection();

        int responseCode = huc.getResponseCode();

        if (HttpURLConnection.HTTP_OK == responseCode) {
            result = true;
            System.out.println(result);
        } else {
            result = false;
            System.out.println(result);
            // throw new IOException("HTTP Response not 200");

        }
        System.out.println(url);
        return url;
    }

    private String testOKHTTP() throws Exception {

        OkHttpClient clientok = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType,
                "username=becksen&apiKey=ab4a9eae49a3ed8ea52d2443dd6bc9b9125efac354a7c44f8afc014a623dbc87");
        Request request = new Request.Builder().url("https://www.link-tap.com/api/getAllDevices ").method("POST", body)
                .addHeader("Content-Type", "application/x-www-form-urlencoded").build();
        Response response = clientok.newCall(request).execute();
        int httpCode = response.code(); // http response code
        String responseBody = response.body().string();
        System.out.println("HTTP Code:" + httpCode);
        System.out.println("TestOKHTTP: " + response.toString());
        System.out.println("ResBody: " + responseBody);

        // handle json response output
        JSONParser parse = new JSONParser();

        JSONObject jobj = (JSONObject) parse.parse(responseBody);
        JSONArray jsonarr_1 = (JSONArray) jobj.get("devices");
        try {
            for (int i = 0; i < jsonarr_1.size(); i++) {

                // Store the JSON objects in an array

                // Get the index of the JSON object and print the values as per the index

                JSONObject jsonobj_1 = (JSONObject) jsonarr_1.get(i);

                System.out.println("\nElements under results array");

                System.out.println("\nName: " + jsonobj_1.get("name"));

                System.out.println("\nlocation: " + jsonobj_1.get("location"));

                // Get First hierarchy element
                System.out.println("\nResult: " + jobj.get("result"));
                System.out.println("\nStatus: " + jsonobj_1.get("status"));
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }

        return response.toString();
    }

}
