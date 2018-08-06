package com.bw.movie.model;

import com.bw.movie.net.BaseObserver;
import com.bw.movie.net.OnNetListener;
import com.bw.movie.net.RetrofitUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/8/3.
 * 热门
 */

public class FindHotMovieListModel {
    public void findHotMovieList(String userId, String sessionId, int page, int count, final OnNetListener onNetListener){
        Map<String,String> headers = new HashMap<>();
        headers.put("userId",userId);
        headers.put("sessionId",sessionId);
        Map<String,Object> map = new HashMap<>();
        map.put("page",page);
        map.put("count",count);

        RetrofitUtil.getInstener().get(headers,"movie/v1/findHotMovieList",map)
                .subscribe(new BaseObserver() {
                    @Override
                    public void onSuccess(String result) {
                        onNetListener.onSuccess(result);
                    }

                    @Override
                    public void onFailed(Throwable e) {

                    }
                });


    }
}
