package com.bw.movie.view;

import com.bw.movie.bean.Now_showing_Bean;
import com.bw.movie.bean.Popular_Bean;

import java.util.List;

/**
 * Created by Administrator on 2018/8/4.
 */

public interface FindReleaseMovieListView {
    //正在
    void showReleaseMovieList( List<Now_showing_Bean.ResultBean> list);
}
