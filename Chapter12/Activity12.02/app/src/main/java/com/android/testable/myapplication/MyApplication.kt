package com.android.testable.myapplication

import android.app.Application
import com.android.testable.myapplication.api.PostService
import com.android.testable.myapplication.repository.PostRepository
import com.android.testable.myapplication.repository.PostRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class MyApplication : Application() {

    private val networkModule = module {
        single {
            Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        single {
            providePostService(get())
        }
    }
    private val repositoryModule = module {
        single {
            providePostRepository(get())
        }
    }
    private val viewModelModule = module {
        viewModel {
            PostViewModel(get())
        }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(listOf(networkModule, repositoryModule, viewModelModule))
        }
    }

    private fun providePostService(retrofit: Retrofit): PostService {
        return retrofit.create(PostService::class.java)
    }

    open fun providePostRepository(postService: PostService): PostRepository {
        return PostRepositoryImpl(postService)
    }
}