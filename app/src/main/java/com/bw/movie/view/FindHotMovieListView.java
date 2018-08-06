package com.bw.movie.view;

import com.bw.movie.bean.Popular_Bean;

import java.util.List;

/**
 * Created by Administrator on 2018/8/3.
 */

public interface FindHotMovieListView {
    //热门
    void showHotMovieList(List<Popular_Bean.ResultBean> list);
}
