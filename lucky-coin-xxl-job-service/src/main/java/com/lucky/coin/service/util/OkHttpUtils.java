package com.lucky.coin.service.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
public class OkHttpUtils {

    private static final String sslPassKey = "javax.net.ssl.trustStorePassword";
    private static final String sslStoreKey = "javax.net.ssl.trustStore";

    private static final String HTTP_JSON = "application/json; charset=utf-8";
    private static final String HTTP_FORM = "application/x-www-form-urlencoded; charset=utf-8";
    private static final OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .build();
    private static OkHttpClient clientAuthentication = null;

    public static <T> T httpGetObject(String url, TypeReference<T> type) {
        String result = httpGet(url, null);
        if (StringUtils.isNotBlank(result)) {
            log.info("httpGet url:{}", url);
            return JSON.parseObject(result, type);
        }
        return null;
    }

    public static <T> T httpGetObject(String url, Map<String, String> headers, TypeReference<T> type) {
        String result = httpGet(url, headers);
        if (StringUtils.isNotBlank(result)) {
            log.info(" httpGetObject url:{} result:{} ", url, result);
            return JSON.parseObject(result, type);
        }
        return null;
    }
    /**
     * get请求
     * 对于小文档，响应体上的string()方法非常方便和高效。
     * 但是，如果响应主体很大(大于1 MB)，则应避免string()，
     * 因为它会将整个文档加载到内存中。在这种情况下，将主体处理为流。
     *
     * @param url
     * @return
     */
    /**
     * get请求
     * 对于小文档，响应体上的string()方法非常方便和高效。
     * 但是，如果响应主体很大(大于1 MB)，则应避免string()，
     * 因为它会将整个文档加载到内存中。在这种情况下，将主体处理为流。
     *
     * @param url
     * @return
     */
    public static String httpGet(String url, Map<String, String> headers) {
        StringBuilder stringBuilder = new StringBuilder();
        Long start = System.currentTimeMillis();
        stringBuilder.append(" httpGet    ：");
        if (url.contains("blockchair") && url.contains("key")) {
            log.info("blockchair httpGet");
        } else {
            stringBuilder.append(" url " + url);
        }
        if (url == null || "".equals(url)) {
            log.error(stringBuilder.toString());
            return "";
        }
        Request.Builder builder = new Request.Builder();
        if (headers != null && headers.size() > 0) {
            stringBuilder.append("headers:" + JSONObject.toJSONString(headers));
            headers.forEach((k, v) -> builder.addHeader(k, v));
        }
        Request request = builder.get().url(url).build();
        try {
            Response response = okHttpClient.newCall(request).execute();

            Integer code = response.code();
            stringBuilder.append(" 请求状态code:" + code);

            String result = response.body().string();
            Long end = System.currentTimeMillis();
            stringBuilder.append(" 时间:" + (end - start));
            if (result.length() < 1024 * 10) {
                stringBuilder.append(" 结果： " + result);
            } else {
                stringBuilder.append(" 结果length： " + result.length());
            }

            log.info(stringBuilder.toString());
            return result;
        } catch (Exception e) {
            Long end = System.currentTimeMillis();
            stringBuilder.append(" 时间:" + (end - start));
            if (e instanceof UnknownHostException) {
                if (StringUtils.contains(e.getMessage(), "api.satomoto.com")) {
                    log.error(stringBuilder.toString() + "error:{}", e.getMessage());
                    throw new RuntimeException("同步http GET 请求失败,url:" + url, e);
                }
            }
            log.error(stringBuilder.toString(), e);
            throw new RuntimeException("同步http GET 请求失败,url:" + url, e);
        }
    }

    /**
     * 同步 POST调用 有Header
     *
     * @param url
     * @param headers
     * @param json
     * @return
     */
    public static String httpPostJson(String url, Map<String, String> headers, String json) {
        StringBuilder stringBuilder = new StringBuilder();
        long start = System.currentTimeMillis();

        stringBuilder.append(" httpPostJson    ：");
        stringBuilder.append(" url " + url);
        stringBuilder.append(" json " + json);
        MediaType JSON = MediaType.parse(HTTP_JSON);
        RequestBody body = RequestBody.create(JSON, json);
        Request.Builder requestBuilder = new Request.Builder().url(url);
        if (headers != null && headers.size() > 0) {
            stringBuilder.append("headers:" + JSONObject.toJSONString(headers));
            headers.forEach((k, v) -> requestBuilder.addHeader(k, v));
        }
        Request request = requestBuilder.post(body).build();
        try {
            Response response = okHttpClient.newCall(request).execute();

            Integer code = response.code();
            stringBuilder.append(" 请求状态code:" + code);

            String result = response.body().string();
            stringBuilder.append(" 结果： " + result);
            stringBuilder.append(" time： " + (System.currentTimeMillis() - start));

            log.info(stringBuilder.toString());
            return result;
        } catch (IOException e) {
            log.error(stringBuilder.toString(), e);
            throw new RuntimeException(stringBuilder.toString() + "同步http请求失败:", e);
        }
    }

    /**
     * 带密码的批量切换
     *
     * @param url
     * @param json
     * @param admin
     * @param password
     * @return
     */
    public static String httpPostJsonAuthentication(String url, String json, String admin, String password) {
        if (clientAuthentication == null) {
            clientAuthentication = new OkHttpClient.Builder()
                    .addInterceptor(new BasicAuthInterceptor(admin, password))
                    .build();
        }
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("   httpPostJsonAuthentication ：");
        if (url == null || "".equals(url)) {
            log.error("url为null!");
            return "";
        }
        stringBuilder.append(" url " + url);
        stringBuilder.append(" json " + json);
        MediaType JSON = MediaType.parse(HTTP_JSON);
        RequestBody body = RequestBody.create(JSON, json);
        Request.Builder requestBuilder = new Request.Builder().url(url);
        Request request = requestBuilder.post(body).build();
        try {
            Response response = clientAuthentication.newCall(request).execute();
            stringBuilder.append(" code 是: " + response.code());
            String result = response.body().string();
            stringBuilder.append(" 结果： " + result);
            if (response.code() == 200) {

                stringBuilder.append(" 结果： " + result);
            } else {
                stringBuilder.append("Http post 请求失败;");
            }
            log.info(stringBuilder.toString());
            return result;
        } catch (IOException e) {
            log.error(stringBuilder.toString(), e);
            throw new RuntimeException(stringBuilder.toString() + "同步http请求失败:", e);
        }
    }

    /**
     * 提交表单
     *
     * @param url
     * @param content
     * @param headers
     * @return
     */
    public static String postDataByForm(String url, String content, Map<String, String> headers) {
        MediaType JSON = MediaType.parse(HTTP_FORM);
        RequestBody body = RequestBody.create(JSON, content);

        Request.Builder requestBuilder = new Request.Builder().url(url);
        if (headers != null && headers.size() > 0) {
            headers.forEach((k, v) -> requestBuilder.addHeader(k, v));
        }
        Request request = requestBuilder
                .post(body)
                .build();

        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
            if (response.code() == 200) {
                log.info("postDataByForm; [postUrl={}, requestContent={}, responseCode={}]", url, content, response.code());
                return response.body().string();
            } else {
                log.error("Http Post Form请求失败,[url={}, param={}]", url, content);
            }
        } catch (IOException e) {
            log.error("Http Post Form请求失败,[url={}, param={}]", url, content, e);
            throw new RuntimeException("Http Post Form请求失败,url:" + url);
        }
        return null;
    }


}
