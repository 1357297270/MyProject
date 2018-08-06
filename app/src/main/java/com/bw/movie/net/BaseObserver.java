package com.bw.movie.net;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2018/8/2.
 */

public abstract class BaseObserver implements Observer<String> {
    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(String s) {
        onSuccess(s);
    }

    @Override
    public void onError(Throwable e) {
        onFailed(e);
    }

    @Override
    public void onComplete() {

    }

    public abstract void onSuccess(String result);

    public abstract void onFailed(Throwable e);
}
