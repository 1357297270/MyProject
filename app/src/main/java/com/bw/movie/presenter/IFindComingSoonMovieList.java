package com.bw.movie.presenter;

/**
 * Created by Administrator on 2018/8/4.
 */

public interface IFindComingSoonMovieList {
    //即将上映
    void findComingSoonMovieList(String userId, String sessionId, int page, int count);

}
