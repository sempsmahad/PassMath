package com.kh69.passmath.common.data.di

import com.kh69.passmath.common.data.api.ApiConstants
import com.kh69.passmath.common.data.api.QuestionLoaderApi
import com.kh69.passmath.common.data.api.interceptors.AuthenticationInterceptor
import com.kh69.passmath.common.data.api.interceptors.LoggingInterceptor
import com.kh69.passmath.common.data.api.interceptors.NetworkStatusInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

  @Provides
  @Singleton
  fun provideApi(builder: Retrofit.Builder): QuestionLoaderApi {
    return builder
        .build()
        .create(QuestionLoaderApi::class.java)
  }

  @Provides
  fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit.Builder {
    return Retrofit.Builder()
        .baseUrl(ApiConstants.BASE_ENDPOINT)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create())
  }

  @Provides
  fun provideOkHttpClient(
      httpLoggingInterceptor: HttpLoggingInterceptor,
      networkStatusInterceptor: NetworkStatusInterceptor,
      authenticationInterceptor: AuthenticationInterceptor
  ): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(networkStatusInterceptor)
        .addInterceptor(authenticationInterceptor)
        .addInterceptor(httpLoggingInterceptor)
        .build()
  }

  @Provides
  fun provideHttpLoggingInterceptor(loggingInterceptor: LoggingInterceptor): HttpLoggingInterceptor {
    val interceptor = HttpLoggingInterceptor(loggingInterceptor)

    interceptor.level = HttpLoggingInterceptor.Level.BODY

    return interceptor
  }
}