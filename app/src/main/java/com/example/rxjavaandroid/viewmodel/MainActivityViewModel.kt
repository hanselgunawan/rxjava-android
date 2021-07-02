package com.example.rxjavaandroid.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rxjavaandroid.network.BookListModel
import com.example.rxjavaandroid.network.RetroInstance
import com.example.rxjavaandroid.network.RetroService
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainActivityViewModel : ViewModel() {

	var bookList: MutableLiveData<BookListModel?> = MutableLiveData()

	fun getBookListObserver(): MutableLiveData<BookListModel?> {
		return bookList
	}

	fun makeApiCall(query: String) {

		val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)

		retroInstance.getBookListFromApi(query)
			// RxJava part
			.subscribeOn(Schedulers.io())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe(getBookListObserverRx())
	}

	private fun getBookListObserverRx(): Observer<BookListModel> {
		return object :Observer<BookListModel> {
			override fun onSubscribe(d: Disposable?) {
				// showing progress bar
			}

			override fun onNext(t: BookListModel?) {
				bookList.postValue(t)
			}

			override fun onError(e: Throwable?) {
				bookList.postValue(null)
			}

			override fun onComplete() {
				// hide progress bar
			}

		}
	}
}