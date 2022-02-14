package com.soumikshah.futuramaquotes.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.soumikshah.futuramaquotes.model.Character
import com.soumikshah.futuramaquotes.model.CharacterService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class CharacterViewModel: ViewModel() {
    var character = MutableLiveData<List<Character>>()
    var errorMessage = MutableLiveData<Boolean>()
    var loading = MutableLiveData<Boolean>()
    private val disposable = CompositeDisposable()
    private val characterService= CharacterService()

    fun refresh(){
        fetchCharacters()
    }

    private fun fetchCharacters(){
        loading.value = true
        disposable.add(
            characterService.getCharacters()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<List<Character>>(){
                    override fun onSuccess(value: List<Character>?) {
                        character.value = value
                        errorMessage.value = false
                        loading.value = false
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