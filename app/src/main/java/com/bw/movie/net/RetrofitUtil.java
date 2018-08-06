package com.bw.movie.net;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Administrator on 2018/8/2.
 */

public class RetrofitUtil {
    private static RetrofitUtil instener;
    private OkHttpClient okHttpClient;
    private ApiService apiService;

    private RetrofitUtil() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(logging)//日志拦截器
                .build();

        apiService = new Retrofit.Builder()
                .baseUrl("http://172.17.8.100/movieApi/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()
                .create(ApiService.class);
    }

    public static synchronized RetrofitUtil getInstener() {
        if (instener == null) {
            synchronized (RetrofitUtil.class) {
                if (instener == null) {
                    instener = new RetrofitUtil();
                }
            }
        }
        return instener;
    }

    /**
     * get请求
     * HeaderParam入参
     */
    public Observable<String> get(Map<String, String> headers, String url) {
        return apiService.get(headers, url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * get请求
     * HeaderParam入参
     * FormParam入参
     */
    public Observable<String> get(Map<String, String> headers, String url, Map<String, Object> map) {
        return apiService.get(headers, url, map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * get请求
     * FormParam入参
     */
    public Observable<String> get(String url, Map<String, Object> map) {
        return apiService.get(url, map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * post请求
     * FormParam入参
     */
    public Observable<String> post(String url, Map<String, Object> map) {
        return apiService.post(url, map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * post请求
     * HeaderParam入参
     * FormParam入参
     */
    public Observable<String> post(Map<String, String> headers, String url, Map<String, Object> map) {
        return apiService.post(headers, url, map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * post请求
     * HeaderParam入参
     * 上传文件
     */
    public Observable<String> post(Map<String, String> headers, String url, MultipartBody.Part file) {
        return apiService.post(headers, url, file).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
