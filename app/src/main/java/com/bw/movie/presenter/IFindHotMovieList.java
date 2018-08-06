package com.bw.movie.presenter;

/**
 * Created by Administrator on 2018/8/3.
 */

public interface IFindHotMovieList {
    //热门影片
    void findHotMovieList(String userId, String sessionId, int page, int count);


}
