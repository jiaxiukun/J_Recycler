package com.xzz.draw.j_recycler.socket;

import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2017/8/16 0016.
 */
//实现观察者
public abstract class HttpRequest<T> implements Observer<T> {

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T value) {
        onSuccedd(value);
    }

    @Override
    public void onError(Throwable e) {
        Log.e("TAG", "onError: " + e.toString());
    }

    @Override
    public void onComplete() {

    }

    public abstract void onSuccedd(T t);
}
