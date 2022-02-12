package com.soumikshah.futuramaquotes.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.soumikshah.futuramaquotes.model.Quote
import com.soumikshah.futuramaquotes.model.QuotesService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ListViewModel: ViewModel() {
    var quotes = MutableLiveData<List<Quote>>()
    var errorMessage = MutableLiveData<Boolean>()
    var loading = MutableLiveData<Boolean>()
    private val disposable= CompositeDisposable()
    private val quoteService = QuotesService()


    fun refresh(){
        fetchLoadingUrl()
    }

    private fun fetchLoadingUrl(){
        loading.value = true
        disposable.add(
            quoteService.getQuotes()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<List<Quote>>(){
                    override fun onSuccess(value: List<Quote>?) {
                        quotes.value = value
                        errorMessage.value = false
                        loading.value =false
                    }

                    override fun onError(e: Throwable?) {
                        errorMessage.value = true
                        loading.value = true
                    }
                })
        )

    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}