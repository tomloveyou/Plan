package com.example.hp.testapplication.api;

import com.example.hp.testapplication.BuildConfig;
import com.example.hp.testapplication.R;
import com.example.hp.testapplication.api.Exception.RetryWhenNetworkException;
import com.google.gson.JsonParseException;

import com.standards.library.app.AppContext;
import com.standards.library.app.ReturnCode;
import com.standards.library.model.BaseInfo;
import com.standards.library.model.Response;
import com.standards.library.rx.ErrorThrowable;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * <请描述这个类是干什么的>
 *
 * @result: 2016/7/7 13:39
 * @version: V1.0
 */
public class ResponseHandle {

    public static <T> Func1<Throwable, Observable<? extends T>> errorResumeFunc() {
        return throwable -> {
            if (throwable instanceof UnknownHostException || throwable instanceof JsonParseException) {
                if (!AppContext.isNetworkAvailable()) {
                    return Observable.error(new ErrorThrowable(ReturnCode.LOCAL_NO_NETWORK, AppContext.getContext().getString(R.string.load_no_network)));
                }
                return Observable.error(new ErrorThrowable(ReturnCode.LOCAL_ERROR_TYPE_ERROR, BuildConfig.DEBUG_LOG ? throwable.toString() : AppContext.getString(R.string.load_system_busy)));
            } else if (throwable instanceof SocketTimeoutException || throwable instanceof ConnectException) {
                return Observable.error(new ErrorThrowable(ReturnCode.LOCAL_TIMEOUT_ERROR, BuildConfig.DEBUG_LOG ? throwable.toString() : AppContext.getString(R.string.load_time_out)));
            } else if (throwable instanceof ErrorThrowable) {
                Observable.error(throwable);
            }
            return Observable.error(new ErrorThrowable(ReturnCode.LOCAL_UNKNOWN_ERROR, BuildConfig.DEBUG_LOG ? throwable.toString() : AppContext.getString(R.string.load_system_busy)));
        };
    }

    // 读取实体数据
    private static class ReadresultFunc<E> implements Func1<Response<E>, Observable<E>> {
        @Override
        public Observable<E> call(Response<E> x) {
            if (x.code.equals(ReturnCode.CODE_SUCCESS+"")) {
                if (x.result != null && x.result instanceof BaseInfo) {
                    ((BaseInfo) x.result).setSussceHintMsg(x.msg==null?"":x.msg);
                }
                return Observable.just(x.result);
            } else {
                return Observable.error(new ErrorThrowable(Integer.parseInt(x.code), (x.code.equals(ReturnCode.CODE_TOKEN_INVALID+"")) ? "" : x.msg));//用户失效不显示提示语
            }
        }
    }

    //获取response数据
    public static class ReadResponse implements Func1<Response, Observable<Response>> {

        @Override
        public Observable<Response> call(Response x) {
            if (x.code.equals( ReturnCode.CODE_SUCCESS+"")) {
                return Observable.just(x);
            } else {
                return Observable.error(new ErrorThrowable(Integer.parseInt(x.code), x.code.equals( ReturnCode.CODE_TOKEN_INVALID+"") ? "" : x.msg));
            }
        }
    }

    //新建处理实体类型数据
    public static ReadresultFunc newEntityresult() {
        return new ReadresultFunc();
    }

    public static ReadResponse newResponseresult() {
        return new ReadResponse();
    }


    public static <T> Observable.Transformer<T, T> applySchedulersWithToken() {
        return tObservable -> tObservable
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .onErrorResumeNext(errorResumeFunc())
                .retryWhen(new RetryWhenNetworkException())
                .observeOn(AndroidSchedulers.mainThread());
    }


}
