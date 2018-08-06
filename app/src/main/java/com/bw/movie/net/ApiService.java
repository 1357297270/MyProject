package com.bw.movie.net;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by Administrator on 2018/8/2.
 */

public interface ApiService {
    /**
     * get请求
     * HeaderParam入参
     */
    @GET
    Observable<String> get(@HeaderMap Map<String, String> headers, @Url String url);

    /**
     * get请求
     * HeaderParam入参
     * FormParam入参
     */
    @GET
    Observable<String> get(@HeaderMap Map<String, String> headers, @Url String url, @QueryMap Map<String, Object> map);

    /**
     * get请求
     * FormParam入参
     */
    @GET
    Observable<String> get(@Url String url, @QueryMap Map<String, Object> map);

    /**
     * post请求
     * FormParam入参
     */
    @FormUrlEncoded
    @POST
    Observable<String> post(@Url String url, @FieldMap Map<String, Object> map);

    /**
     * post请求
     * HeaderParam入参
     * FormParam入参
     */
    @FormUrlEncoded
    @POST
    Observable<String> post(@HeaderMap Map<String, String> headers, @Url String url, @FieldMap Map<String, Object> map);

    /**
     * post请求
     * HeaderParam入参
     * 上传文件
     */
    @Multipart
    @POST
    Observable<String> post(@HeaderMap Map<String, String> headers, @Url String url, @Part MultipartBody.Part file);
}
