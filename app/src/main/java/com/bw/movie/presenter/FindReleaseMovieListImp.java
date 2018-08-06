package com.bw.movie.presenter;

import com.bw.movie.bean.Now_showing_Bean;
import com.bw.movie.bean.Popular_Bean;
import com.bw.movie.model.FindHotMovieListModel;
import com.bw.movie.model.FindReleaseMovieListModel;
import com.bw.movie.net.OnNetListener;
import com.bw.movie.view.FindHotMovieListView;
import com.bw.movie.view.FindReleaseMovieListView;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by Administrator on 2018/8/4.
 * 正在
 */

public class FindReleaseMovieListImp implements IfindReleaseMovieList {
    private FindReleaseMovieListView view;
    private FindReleaseMovieListModel movieListModel;

    public FindReleaseMovieListImp(FindReleaseMovieListView view) {
        this.view = view;
        movieListModel= new FindReleaseMovieListModel();
    }

    @Override
    public void findReleaseMovieList(String userId, String sessionId, int page, int count) {
      movieListModel.findReleaseMovieList(userId, sessionId, page, count, new OnNetListener() {
          @Override
          public void onSuccess(String result) {
              Now_showing_Bean now_showing_bean = new Gson().fromJson(result, Now_showing_Bean.class);
              List<Now_showing_Bean.ResultBean> result1 = now_showing_bean.getResult();
              view.showReleaseMovieList(result1);

          }

          @Override
          public void onFailed(Throwable e) {

          }
      });
    }
}
