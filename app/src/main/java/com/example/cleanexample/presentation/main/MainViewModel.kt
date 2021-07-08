package com.example.cleanexample.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleanexample.domain.DataResult
import com.example.cleanexample.domain.entity.User
import com.example.cleanexample.domain.usecase.GetUserListUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

class MainViewModel(
    private val getUserListUseCase: GetUserListUseCase
) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    private val _userList = MutableLiveData<ArrayList<User>>()
    val userList: LiveData<ArrayList<User>>
        get() = _userList

    fun getUserList() {
        // invoke 함수가 여기서 실행되네
        getUserListUseCase(10)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                when (it) {
                    is DataResult.Success ->
                        _userList.value = it.data.results
                    else ->
                        _userList.value = arrayListOf()
                }
            }.addTo(compositeDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}