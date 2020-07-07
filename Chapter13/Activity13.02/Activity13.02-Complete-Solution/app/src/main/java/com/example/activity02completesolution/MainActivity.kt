package com.example.activity02completesolution

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.activity02completesolution.model.Movie
import com.example.activity02completesolution.model.MovieListResponse
import com.example.activity02completesolution.network.MovieApi
import com.example.activity02completesolution.network.RetrofitClient
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit

class MainActivity : AppCompatActivity(), MovieAdapter.MovieClickedListener {

    lateinit var retrofit: Retrofit
    val movies = arrayListOf<Movie>()
    lateinit var movieDisposable: Disposable
    lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        retrofit = RetrofitClient.getRetrofitClient()
        val service = getMovieService()
        val observer = getMovieListObserver()
        getMovieList(service, observer)
        initList()
    }

    private fun initList() {
        movieList.layoutManager = LinearLayoutManager(this)
        movieAdapter = MovieAdapter(this, movies)
        movieAdapter.setClickListener(this)
        movieList.adapter = movieAdapter
    }

    private fun getMovieService() = retrofit.create(MovieApi::class.java)

    private fun getMovieList(
        movieApi: MovieApi,
        observer: Observer<Movie>
    ) {
        movieApi.getPopularMovies(
            "1d9a4704d81b27085f142914119d38fe"
        ).subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap {
                Observable.fromIterable(it.results)
            }.filter {
                it.original_language == "en"
            }
            .subscribe(observer)
    }

    private fun getMovieListObserver(): Observer<Movie> {
        return object : Observer<Movie> {

            override fun onComplete() {
                Log.d("RxJava logs", "onComplete")
            }

            override fun onSubscribe(d: Disposable) {
                movieDisposable = d
                Log.d("RxJava logs", "onSubscribe")
            }

            override fun onNext(movie: Movie) {
                movies.add(movie)
                movieAdapter.notifyDataSetChanged()
            }

            override fun onError(e: Throwable) {
                Log.d("RxJava logs", "onError ${e.localizedMessage}")
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        movieDisposable.dispose()
    }

    override fun onMovieClick(view: View, position: Int) {
        val movieIntent = Intent(this, MovieDetailActivity::class.java)
        movieIntent.putExtra("movie_detail", movies[position])
        startActivity(movieIntent)
    }
}
