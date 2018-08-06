package com.bw.movie.net;

/**
 * Created by Administrator on 2018/8/2.
 */

public interface OnNetListener {
    void onSuccess(String result);

    void onFailed(Throwable e);
}
