package com.example.rxjavaandroid.network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetroInstance {

	companion object {
		val baseURL = "https://www.googleapis.com/books/v1/"

		fun getRetroInstance() : Retrofit {

			return Retrofit.Builder()
				.baseUrl(baseURL)
				.addConverterFactory(GsonConverterFactory.create())
				// mandatory step for RxJava
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				.build()
		}
	}
}