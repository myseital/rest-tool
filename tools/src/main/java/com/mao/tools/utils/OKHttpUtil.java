package com.mao.tools.utils;

import com.alibaba.fastjson.JSON;
import com.mao.tools.common.SdkConstant;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.apache.commons.lang3.time.StopWatch;

import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * OKHttp请求工具类
 *
 * @author myseital
 **/
@Slf4j
public class OKHttpUtil {

    private static final MediaType MEDIA_TYPE_JSON = MediaType.get(SdkConstant.CONTENT_TYPE_JSON);
    private static final MediaType MEDIA_TYPE_FORM = MediaType.get(SdkConstant.CONTENT_TYPE_FORM);
    private static final MediaType MEDIA_TYPE_XML = MediaType.get(SdkConstant.CONTENT_TYPE_XML);
    private static final String ENCODING = SdkConstant.ENCODING;
    private static final String CONTENT_TYPE = SdkConstant.CONTENT_TYPE;
    private static final String SOAP_ACTION = SdkConstant.SOAP_ACTION;

    private static final OkHttpClient okHttpClient;

    private static final int CONNECT_TIMEOUT = 120;
    private static final int WRITE_TIMEOUT = 120;
    private static final int READ_TIMEOUT = 200;
    private static final int MAX_IDLE_CONNECTION = 5;
    private static final long KEEP_ALIVE_DURATION = 5;

    static {
        // 支持https请求，绕过验证
        X509TrustManager manager = SSLSocketClientUtil.getX509TrustManager();
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                // 忽略校验
                .sslSocketFactory(SSLSocketClientUtil.getSocketFactory(manager), manager)
                // 忽略校验
                .hostnameVerifier(SSLSocketClientUtil.getHostnameVerifier())
                .connectionPool(new ConnectionPool(MAX_IDLE_CONNECTION, KEEP_ALIVE_DURATION, TimeUnit.MINUTES))
                .build();
    }

    /**
     * webservice 请求
     *
     * @param url
     * @param soap
     * @return
     * @throws IOException
     */
    public static String webservice(String url, String soap) throws IOException {
        RequestBody body = RequestBody.create(MEDIA_TYPE_XML, soap);
        Request request = new Request.Builder().url(url).post(body)
                .addHeader(SOAP_ACTION, SdkConstant.EMPTY_STRING)
                .addHeader(CONTENT_TYPE, SdkConstant.CONTENT_TYPE_XML)
                .build();

        return doHttpRequest(request);
    }

    /**
     * 携带json数据体，发送post请求，并返回结果字符串
     *
     * @param url           请求url
     * @param jsonParams    json数据体
     * @param headersParams 请求头
     * @return 请求响应字符串
     * @throws IOException
     */
    public static String postWithJson(String url, Map<String, Object> jsonParams, Map<String, String> headersParams) throws IOException {
        return postWithJson(url, null, JsonUtil.obj2String(jsonParams), headersParams);
    }

    /**
     * 携带json数据体，发送post请求，并返回结果字符串
     *
     * @param url
     * @param pathParams
     * @param jsonStr
     * @return
     * @throws IOException
     */
    public static String postWithJson(String url, Map<String, String> pathParams, String jsonStr) throws IOException {
        return postWithJson(url, pathParams, jsonStr, null);
    }

    /**
     * 携带json数据体，发送put请求，并返回结果字符串
     *
     * @param url           请求url
     * @param pathParams    uri参数
     * @param jsonStr       json数据体
     * @param headersParams 请求头
     * @return 请求响应字符串
     * @throws IOException
     */
    public static String postWithJson(String url, Map<String, String> pathParams, String jsonStr, Map<String, String> headersParams) throws IOException {
        RequestBody requestBody = RequestBody.create(MEDIA_TYPE_JSON, jsonStr);

        Request request = new Request.Builder()
                .url(getHttpUrl(url, pathParams))
                .post(requestBody)
                .headers(setHeaders(headersParams))
                .build();

        return doHttpRequest(request);
    }


    /**
     * 携带json数据体，发送post请求，并返回结果字符串
     *
     * @param url           请求url
     * @param params        请求url
     * @param headersParams 请求头
     * @return 请求响应字符串
     * @throws IOException
     */
    public static String postWithForm(String url, Map<String, String> params, Map<String, String> headersParams) throws IOException {
        FormBody formBody = getFormBody(params);
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .headers(setHeaders(headersParams))
                .build();
        //发起请求
        return doHttpRequest(request);
    }

    /**
     * 发送get请求，并返回结果字符串
     *
     * @param url
     * @param params
     * @param headersParams
     * @return
     * @throws IOException
     */
    public static String get(String url, Map<String, String> params, Map<String, String> headersParams) throws IOException {
        //构造请求
        Request request = new Request.Builder()
                .url(getHttpUrl(url, params))
                .headers(setHeaders(headersParams))
                .build();
        //发起请求
        return doHttpRequest(request);
    }

    /**
     * 携带json数据体，发送patch请求，并返回结果字符串
     *
     * @param url
     * @param params
     * @param body
     * @param headersParams
     * @return
     * @throws IOException
     */
    public static String patch(String url, Map<String, String> params, Map<String, Object> body, Map<String, String> headersParams) throws IOException {
        RequestBody requestBody = RequestBody.create(MEDIA_TYPE_JSON, JsonUtil.obj2String(body));
        //构造请求
        Request request = new Request.Builder()
                .url(getHttpUrl(url, params))
                .headers(setHeaders(headersParams))
                .patch(requestBody)
                .build();
        //发起请求
        return doHttpRequest(request);
    }

    /**
     * 携带json数据体，发送put请求，并返回结果字符串
     *
     * @param url           请求url
     * @param jsonParams    json数据体
     * @param headersParams 请求头
     * @return 请求响应字符串
     * @throws IOException
     */
    public static String putWithJson(String url, Map<String, Object> jsonParams, Map<String, String> headersParams) throws IOException {
        //将map转成json字符串
        String jsonStr = JSON.toJSONString(jsonParams);
        return putWithJson(url, jsonStr, headersParams);
    }

    /**
     * 携带json数据体，发送put请求，并返回结果字符串
     *
     * @param url           请求url
     * @param jsonStr       json数据体
     * @param headersParams 请求头
     * @return 请求响应字符串
     * @throws IOException
     */
    public static String putWithJson(String url, String jsonStr, Map<String, String> headersParams) throws IOException {
        RequestBody requestBody = RequestBody.create(MEDIA_TYPE_JSON, jsonStr);
        Request request = new Request.Builder()
                .url(url)
                .put(requestBody)
                .headers(setHeaders(headersParams))
                .build();

        return doHttpRequest(request);
    }

    private static String doHttpRequest(Request request) throws IOException {
        //发起请求
        StopWatch stopWatch = StopWatch.createStarted();
        try (Response response = okHttpClient.newCall(request).execute()) {
            stopWatch.stop();
            int code = response.code();
            String result = Objects.requireNonNull(response.body()).string();
            log.info("请求url:[{}]耗时[{}ms]code[{}]", request.url(), stopWatch.getTime(TimeUnit.MILLISECONDS), code);
            if (!response.isSuccessful()) {
                throw new IOException(result);
            }
            return result;
        }
    }

    /**
     * Set Headers
     *
     * @param headersParams
     * @return
     */
    private static Headers setHeaders(Map<String, String> headersParams) {
        Headers headers;
        Headers.Builder headersBuilder = new Headers.Builder();
        if (headersParams != null) {
            for (Map.Entry<String, String> entry : headersParams.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                headersBuilder.add(key, value);
            }
        }
        headers = headersBuilder.build();
        return headers;
    }

    /**
     * Set URI
     *
     * @param url    接口对应url
     * @param params 接口url需要的参数
     */
    private static HttpUrl getHttpUrl(String url, Map<String, String> params) {
        HttpUrl.Builder newBuilder = Objects.requireNonNull(HttpUrl.parse(url)).newBuilder();
        if (MapUtil.isNotEmpty(params)) {
            // Set params
            for (Map.Entry<String, String> param : params.entrySet()) {
                newBuilder.addQueryParameter(param.getKey(), param.getValue());
            }
        }

        return newBuilder.build();
    }

    /**
     * 通过参数返回FormBody对象
     *
     * @param params 用于构建FormBody的参数
     * @return FormBody
     */
    private static FormBody getFormBody(Map<String, String> params) {
        FormBody.Builder formBodyBuilder = new FormBody.Builder(Charset.forName(ENCODING));
        // 封装请求参数
        if (MapUtil.isNotEmpty(params)) {
            Set<Map.Entry<String, String>> entrySet = params.entrySet();
            for (Map.Entry<String, String> entry : entrySet) {
                formBodyBuilder.add(entry.getKey(), entry.getValue());
            }
        }

        return formBodyBuilder.build();
    }
}
