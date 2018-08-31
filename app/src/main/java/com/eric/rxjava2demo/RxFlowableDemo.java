package com.eric.rxjava2demo;

import android.util.Log;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * flowable的示例
 * http://www.jianshu.com/p/9b1304435564
 * @author Eric
 * @date 2017/12/12
 */

public class RxFlowableDemo extends RxDemo {

    public static void flowableDemo() {

        Flowable<Integer> upstream = Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onComplete();
            }
        }, BackpressureStrategy.LATEST);

        Subscriber<Integer> downstream = new Subscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription s) {
                Log.d(RxDemo.TAG, "onSubscribe");
                //注意这句代码
                s.request(2);
            }

            @Override
            public void onNext(Integer integer) {
                Log.d(RxDemo.TAG, "onNext: " + integer);

            }

            @Override
            public void onError(Throwable t) {
                Log.w(RxDemo.TAG, "onError: ", t);
            }

            @Override
            public void onComplete() {
                Log.d(RxDemo.TAG, "onComplete");
            }
        };

        upstream.subscribe(downstream);

    }

    private static Subscription mSubscription;

    public static void request(long n) {
        //在外部调用request请求上游
        mSubscription.request(n);
    }

    public static void flowableDemo2() {
        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
                Log.d(TAG, "emit 1");
                emitter.onNext(1);
                Log.d(TAG, "emit 2");
                emitter.onNext(2);
                Log.d(TAG, "emit 3");
                emitter.onNext(3);
                Log.d(TAG, "emit complete");
                emitter.onComplete();

            }
        }, BackpressureStrategy.ERROR).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        Log.d(TAG, "onSubscribe");
                        //把Subscription保存起来
                        mSubscription = s;
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "onNext: " + integer);
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.w(TAG, "onError: ", t);
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete");
                    }
                });

    }

}
