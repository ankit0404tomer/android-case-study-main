package com.target.targetcasestudy.di

import com.target.targetcasestudy.common.Constants
import com.target.targetcasestudy.data.remote.DealApiKtx
import com.target.targetcasestudy.data.repository.DealRepositoryImpl
import com.target.targetcasestudy.domain.repository.DealRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private var okHttp: OkHttpClient? = null

    @Provides
    @Singleton
    fun provideRetrofitApi(): DealApiKtx {
        return Retrofit.Builder()
            .client(getOkHttpClient())
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(
                RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())
            )
            .build()
            .create(DealApiKtx::class.java)
    }

    @Provides
    @Singleton
    fun provideDealRepository(api: DealApiKtx): DealRepository {
        return DealRepositoryImpl(api)
    }

    private fun getOkHttpClient(): OkHttpClient {
        if (okHttp == null)
            okHttp = getOkHttpBuilder().build()
        return okHttp!!
    }

    private fun getOkHttpBuilder(): OkHttpClient.Builder {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .pingInterval(10, TimeUnit.SECONDS)
            .addInterceptor(logging)
    }
}