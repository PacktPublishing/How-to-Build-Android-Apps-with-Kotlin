package com.example.rxdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class Activity01 : AppCompatActivity() {
    lateinit var customerDisposable: Disposable

    private val customers = arrayListOf<PropertyRecommendation>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        customers.add(PropertyRecommendation("John", "South river, Sitka, Alaska", 1200.00, 2.0, true))
        customers.add(PropertyRecommendation("Daniel", "Mount town, Kenai, Alaska", 2200.00, 2.0, false))
        customers.add(PropertyRecommendation("Mark", "Down town, Kenai, Alaska", 4500.00, 3.0, true))
        customers.add(PropertyRecommendation("Don", "Farm town, Wasilla, Alaska", 4500.00, 0.7, true))

        val observer = object : Observer<PropertyRecommendation> {

            override fun onComplete() {
                Log.d("RxJava logs", "onComplete")
            } override fun onNext(propertyRecommendation: PropertyRecommendation) {
                Log.d("RxJava logs", "Benefited customer $propertyRecommendation")
            } override fun onSubscribe(d: Disposable) {
                customerDisposable = d
                Log.d("RxJava logs", "onSubscribe")
            } override fun onError(e: Throwable) {
                Log.d("RxJava logs", "onError ${e.localizedMessage}")

            } }

        Observable.fromIterable(customers)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .filter { propertyRecommendation ->
                propertyRecommendation.propertyArea >= 3000 &&
                    propertyRecommendation.distanceToNextOutLet >= 1 &&
                    propertyRecommendation.isCarParkingAvailable
            } .subscribe(observer) }

    override fun onDestroy() {
        super.onDestroy()
        customerDisposable.dispose()
    }
}