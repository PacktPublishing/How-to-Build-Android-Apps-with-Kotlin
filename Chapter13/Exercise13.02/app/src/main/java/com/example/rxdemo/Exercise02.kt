package com.example.rxdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class Exercise02 : AppCompatActivity() {
    lateinit var customerDisposable: Disposable
    private val customers = arrayListOf<Customer>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        customers.add(Customer("John", 200.00, 120, "Prime"))
        customers.add(Customer("Daniel", 189.00, 38, "Default"))
        customers.add(Customer("Mark", 7399.00, 12, "Prime"))
        customers.add(Customer("Don", 2929.00, 157, "Default"))

        val observer = object : Observer<Customer> {

            override fun onComplete() {
                Log.d("RxJava logs", "onComplete")
            }

            override fun onNext(customer: Customer) {
                customer.creditsBalance += 100
                Log.d("RxJava logs", "Benefited customer $customer")
            }

            override fun onSubscribe(d: Disposable) {
                customerDisposable = d
                Log.d("RxJava logs", "onSubscribe")
            }

            override fun onError(e: Throwable) {
                Log.d("RxJava logs", "onError ${e.localizedMessage}")
            }
        }

        Observable.fromIterable(customers)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .filter { customer ->
                customer.numberOfOrder >= 100 && customer.subscription == "Prime"
            }
            .subscribe(observer)
    }

    override fun onDestroy() {
        super.onDestroy()
        customerDisposable.dispose()
    }
}