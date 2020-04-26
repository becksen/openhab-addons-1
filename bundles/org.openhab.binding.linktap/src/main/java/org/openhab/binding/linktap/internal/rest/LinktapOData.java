package org.openhab.binding.linktap.internal.rest;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class LinktapOData {
    // private final Logger logger = LoggerFactory.getLogger(OData.class);

    public static final void main(String[] args) throws ClientProtocolException, IOException {
        Logger logger = Logger.getLogger(LinktapOData.class);
        LinktapOData runOdata = new LinktapOData();
        // runOdata.resolveRedirectUrl();
        runOdata.connectionTest();

    }

    private String resolveRedirectUrl() throws ClientProtocolException, IOException {
        // String jsonString =
        // "{\"username\":\"becksen\",\"apiKey\":\"ab4a9eae49a3ed8ea52d2443dd6bc9b9125efac354a7c44f8afc014a623dbc87\"}";

        String postUrl = "https://www.link-tap.com/api/getAllDevices"; // put in your url

        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(postUrl);
        List<NameValuePair> arguments = new ArrayList<>();

        arguments.add(new BasicNameValuePair("username", "becksen"));
        arguments.add(
                new BasicNameValuePair("apiKey", "ab4a9eae49a3ed8ea52d2443dd6bc9b9125efac354a7c44f8afc014a623dbc87"));

        try {
            post.setHeader(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded");
            post.setEntity(new UrlEncodedFormEntity(arguments));
            HttpResponse response = client.execute(post);
            // HTTP Header Response
            System.out.println("Header Response" + response);

            // Get HTTP Status Line
            StatusLine statusResult = response.getStatusLine();
            System.out.println("HTTPStatus: " + statusResult);

            // Get Get response Body
            // https://stackoverflow.com/questions/5769717/how-can-i-get-an-http-response-body-as-a-string-in-java
            String responseBody = new BasicResponseHandler().handleResponse(response);
            System.out.println("Response Body" + responseBody);

            // handle JSON response https://code.google.com/archive/p/json-simple/downloads
            // https://dzone.com/articles/how-to-parse-json-data-from-a-rest-api-using-simpl
            JSONParser parse = new JSONParser();
            JSONObject jobj = (JSONObject) parse.parse(responseBody);
            JSONArray jsonarr_1 = (JSONArray) jobj.get("devices");

            for (int i = 0; i < jsonarr_1.size(); i++) {

                // Store the JSON objects in an array

                // Get the index of the JSON object and print the values as per the index

                JSONObject jsonobj_1 = (JSONObject) jsonarr_1.get(i);

                System.out.println("Elements under results array");

                System.out.println("\nName: " + jsonobj_1.get("name"));

                System.out.println("location: " + jsonobj_1.get("location"));

                // Get First hierarchy element
                System.out.println("Status: " + jobj.get("result"));
            }

        } catch (Exception e) {
            System.out.println(e);

        }
        return postUrl;

        /*
         * Gson gson = new Gson();
         * HttpClient httpClient = HttpClientBuilder.create().build();
         * HttpPost post = new HttpPost(postUrl);
         * StringEntity postingString = null;
         * try {
         * postingString = new StringEntity(gson.toJson(jsonString));
         * } catch (UnsupportedEncodingException e) {
         * // TODO Auto-generated catch block
         * e.printStackTrace();
         * } // gson.tojson() converts your pojo to
         * // json
         * post.setEntity(postingString);
         * post.setHeader("Content-type", "application/json");
         *
         * HttpResponse response = httpClient.execute(post);
         *
         * System.out.println(response);
         *
         * HttpEntity httpEntity = response.getEntity();
         * System.out.println(response.getStatusLine().getStatusCode());
         *
         * System.out.println(httpEntity);
         *
         * return postUrl;
         */
    }

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

}
