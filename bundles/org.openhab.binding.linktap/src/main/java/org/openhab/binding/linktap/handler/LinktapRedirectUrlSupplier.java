package org.openhab.binding.linktap.handler;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.httpclient.HttpException;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.smarthome.io.net.http.HttpUtil;
import org.openhab.binding.linktap.internal.linktapBindingConstants;
import org.openhab.binding.linktap.internal.exceptions.FailedResolvingLinktapUrlException;
//import org.openhab.binding.nest.internal.NestBindingConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

@NonNullByDefault
public class LinktapRedirectUrlSupplier {
    private final Logger logger = LoggerFactory.getLogger(LinktapRedirectUrlSupplier.class);
    // private LinktapBridgeConfiguration config;

    protected String cachedUrl = "";

    protected Properties httpHeaders;

    public LinktapRedirectUrlSupplier(final Properties httpHeaders) {
        this.httpHeaders = httpHeaders;
    }

    public String getRedirectUrl() throws FailedResolvingLinktapUrlException {
        if (cachedUrl.isEmpty()) {
            cachedUrl = resolveRedirectUrl();
        }
        return cachedUrl;
    }

    public void resetCache() {
        cachedUrl = "";
    }

    /**
     * Resolves the redirect URL for calls using the {@link NestBindingConstants#REST_URL}.
     *
     * The Jetty client used by {@link HttpUtil} will not pass the Authorization header after a redirect resulting in
     * "401 Unauthorized error" issues.
     *
     * Note that this workaround currently does not use any configured proxy like {@link HttpUtil} does.
     *
     * @throws IOException
     * @throws HttpException
     *
     * @see https://developers.nest.com/documentation/cloud/how-to-handle-redirects
     */

    private String resolveRedirectUrl() throws FailedResolvingLinktapUrlException {
        // byte[] content = null;
        // this.config = config;

        // TODO User new Rest capabilities
        String jsonString = "{\"username\":\"becksen\",\"apiKey\":\"ab4a9eae49a3ed8ea52d2443dd6bc9b9125efac354a7c44f8afc014a623dbc87\"}";

        for (String httpHeaderKey : httpHeaders.stringPropertyNames()) {

            String httpNotrelevant = httpHeaders.getProperty(httpHeaderKey);
        }

        String postUrl = linktapBindingConstants.REST_URL + linktapBindingConstants.REST_GET_DEVICES;// put in your url
        Gson gson = new Gson();

        /*
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
         * try {
         * HttpResponse response = httpClient.execute(post);
         * } catch (ClientProtocolException e) {
         * // TODO Auto-generated catch block
         * e.printStackTrace();
         * } catch (IOException e) {
         * // TODO Auto-generated catch block
         * e.printStackTrace();
         * }
         */
        return postUrl;

        /*
         * //HttpClient client = new HttpClient();
         * PostMethod method = new PostMethod(linktapBindingConstants.REST_URL +
         * linktapBindingConstants.REST_GET_DEVICES);
         *
         * //String jsonString =
         * "{\"username\":\"becksen\",\"apiKey\":\"ab4a9eae49a3ed8ea52d2443dd6bc9b9125efac354a7c44f8afc014a623dbc87\"}";
         * // JSON_STRING.put("username", becksen);
         *
         * StringRequestEntity requestEntity = new StringRequestEntity(jsonString, "application/json", "UFT-8");
         *
         * // method.setRequestEntity(new ByteArrayRequestEntity(content));
         * // method.setRequestHeader("Content-type", linktapBindingConstants.REST_CONTENT_TYPE_PARAM);
         * method.setRequestEntity(requestEntity);
         * // client.getParams().setSoTimeout(1000 * linktapBindingConstants.REST_TIMEOUT);
         * // client.getParams().setConnectionManagerTimeout(1000 * linktapBindingConstants.REST_TIMEOUT);
         *
         * int httpResponse = client.executeMethod(method);
         * return (linktapBindingConstants.REST_URL + linktapBindingConstants.REST_GET_DEVICES);
         *
         * HttpClient httpClient = HttpClientBuilder.create().build(); // Use this instead
         *
         * try {
         *
         * HttpPost request = new HttpPost("http://yoururl");
         * StringEntity params = new StringEntity("details={\"name\":\"myname\",\"age\":\"20\"} ");
         * request.addHeader("content-type", "application/x-www-form-urlencoded");
         * request.setEntity(params);
         * HttpResponse response = httpClient.execute(request);
         *
         * // handle response here...
         *
         * } catch (Exception ex) {
         *
         * // handle exception here
         *
         * } finally {
         * // Deprecated
         * // httpClient.getConnectionManager().shutdown();
         * }
         */

    }
    /*
     * private String resolveRedirectUrl() throws FailedResolvingLinktapUrlException {
     * HttpClient httpClient = new HttpClient(new SslContextFactory());
     * httpClient.setFollowRedirects(false);
     *
     * // Request request = httpClient.newRequest(linktapBindingConstants.REST_URL).method(HttpMethod.GET).timeout(30,
     * Request request = httpClient
     * .newRequest(linktapBindingConstants.REST_URL + linktapBindingConstants.REST_GET_DEVICES)
     * .method(HttpMethod.POST).timeout(30, TimeUnit.SECONDS);
     * for (String httpHeaderKey : httpHeaders.stringPropertyNames()) {
     * request.header(httpHeaderKey, httpHeaders.getProperty(httpHeaderKey));
     * }
     *
     * ContentResponse response;
     * try {
     * httpClient.start();
     * response = request.send();
     * httpClient.stop();
     * } catch (Exception e) {
     * throw new FailedResolvingLinktapUrlException("Failed to resolve redirect URL: " + e.getMessage(), e);
     * }
     *
     * int status = response.getStatus();
     * String redirectUrl = response.getHeaders().get(HttpHeader.LOCATION);
     *
     * if (status != HttpStatus.TEMPORARY_REDIRECT_307) {
     * logger.debug("Redirect status: {}", status);
     * logger.debug("Redirect response: {}", response.getContentAsString());
     * throw new FailedResolvingLinktapUrlException("Failed to get redirect URL, expected status "
     * + HttpStatus.TEMPORARY_REDIRECT_307 + " but was " + status);
     * } else if (StringUtils.isEmpty(redirectUrl)) {
     * throw new FailedResolvingLinktapUrlException("Redirect URL is empty");
     * }
     *
     * redirectUrl = redirectUrl.endsWith("/") ? redirectUrl.substring(0, redirectUrl.length() - 1) : redirectUrl;
     * logger.debug("Redirect URL: {}", redirectUrl);
     * return redirectUrl;
     * }
     */
}
