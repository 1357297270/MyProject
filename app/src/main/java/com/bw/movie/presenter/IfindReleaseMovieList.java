package com.bw.movie.presenter;

/**
 * Created by Administrator on 2018/8/4.
 */

public interface IfindReleaseMovieList {
    //正在上映
    void findReleaseMovieList(String userId, String sessionId, int page, int count);

}
