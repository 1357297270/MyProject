package com.bw.movie.view;

import com.bw.movie.bean.Coming_soon_Bean;
import com.bw.movie.bean.Popular_Bean;

import java.util.List;

/**
 * Created by Administrator on 2018/8/4.
 */

public interface FindComingSoonMovieListView {
    //正在
    void showComingSoonMovieList(List<Coming_soon_Bean.ResultBean> list);
}
