package com.bw.movie.presenter;

import com.bw.movie.bean.Popular_Bean;
import com.bw.movie.model.FindHotMovieListModel;
import com.bw.movie.net.OnNetListener;
import com.bw.movie.view.FindHotMovieListView;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by Administrator on 2018/8/3.
 *
 * 热门
 */

public class FindHotMovieListImp implements IFindHotMovieList {
    private FindHotMovieListView view;
    private FindHotMovieListModel movieListModel;

    public FindHotMovieListImp(FindHotMovieListView view) {
        this.view = view;
        movieListModel = new FindHotMovieListModel();
    }

    @Override
    public void findHotMovieList(String userId, String sessionId, int page, int count) {
        movieListModel.findHotMovieList(userId, sessionId, page, count, new OnNetListener() {
            @Override
            public void onSuccess(String result) {
                Popular_Bean popular_bean = new Gson().fromJson(result, Popular_Bean.class);
                List<Popular_Bean.ResultBean> result1 = popular_bean.getResult();
                view.showHotMovieList(result1);
            }

            @Override
            public void onFailed(Throwable e) {

            }
        });
    }
}
