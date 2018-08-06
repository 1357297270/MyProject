package com.bw.movie.presenter;

import com.bw.movie.bean.Coming_soon_Bean;
import com.bw.movie.model.FindComingSoonMovieListModel;
import com.bw.movie.model.FindHotMovieListModel;
import com.bw.movie.net.OnNetListener;
import com.bw.movie.view.FindComingSoonMovieListView;
import com.bw.movie.view.FindHotMovieListView;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by Administrator on 2018/8/4.
 */

public class FindComingSoonMovieListImp implements IFindComingSoonMovieList{
    private FindComingSoonMovieListView view;
    private FindComingSoonMovieListModel soonMovieListModel;

    public FindComingSoonMovieListImp(FindComingSoonMovieListView view) {
        this.view = view;
        soonMovieListModel= new FindComingSoonMovieListModel();
    }

    @Override
    public void findComingSoonMovieList(String userId, String sessionId, int page, int count) {
        soonMovieListModel.findComingSoonMovieList(userId, sessionId, page, count, new OnNetListener() {
            @Override
            public void onSuccess(String result) {
                Coming_soon_Bean coming_soon_bean = new Gson().fromJson(result, Coming_soon_Bean.class);
                List<Coming_soon_Bean.ResultBean> result1 = coming_soon_bean.getResult();
                view.showComingSoonMovieList(result1);
            }

            @Override
            public void onFailed(Throwable e) {

            }
        });
    }
}
