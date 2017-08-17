package com.xzz.draw.j_recycler.socket;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/8/16 0016.
 * 网络请求基类
 */

public abstract class HttpSocket<T,R> {
    private Retrofit mRetrofit = new Retrofit.Builder()
            .baseUrl(Api.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    private R request = mRetrofit.create(getRequestClass());

    private Params mParams = new Params();

    public static HttpSocket mHttp;

    public void request(HttpRequest<T> observer){
        setParams(mParams);
        getObservable(request,mParams)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public abstract void setParams(Params params);

    public abstract Class<R> getRequestClass();

    public abstract Observable<T> getObservable(R r,Params params);

}
