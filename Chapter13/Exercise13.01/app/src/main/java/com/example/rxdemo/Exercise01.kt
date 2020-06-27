package com.example.rxdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class Exercise01 : AppCompatActivity() {
    lateinit var helloWordDisposable: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val observer = object : Observer<String> {
            override fun onComplete() {
                Log.d("RxJava logs", "onComplete")
            }
            override fun onSubscribe(d: Disposable) {
                helloWordDisposable = d
                Log.d("RxJava logs", "onSubscribe")
            }
            override fun onNext(t: String) {
                Log.d("RxJava logs", "onNext Data $t")
            }
            override fun onError(e: Throwable) {
                Log.d("RxJava logs", "onError")
            }
        }
        val helloWorldObservable = Observable.just("Hello world, from Packt publications")
        helloWorldObservable.subscribeOn(Schedulers.newThread())
        helloWorldObservable.observeOn(AndroidSchedulers.mainThread())
        helloWorldObservable.subscribe(observer)
    }

    override fun onDestroy() {
        super.onDestroy()
        helloWordDisposable.dispose()
    }
}