package com.example.cleanexample.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cleanexample.data.datasource.UserDataSourceImpl
import com.example.cleanexample.data.spec.UserRepositoryImpl
import com.example.cleanexample.domain.usecase.GetUserListUseCase
import com.example.cleanexample.domain.usecase.GetUserListUseCaseImpl
import com.example.cleanexample.presentation.main.MainViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    private val getUserListUseCase: GetUserListUseCase
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        when (modelClass::class.java) {
            MainViewModel::class.java ->
                MainViewModel(getUserListUseCase)
            else ->
                MainViewModel(getUserListUseCase)
        } as T

    companion object {
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance() =
            INSTANCE
                ?: synchronized(ViewModelFactory::class.java) {
                    INSTANCE
                        ?: ViewModelFactory(
                            getUserListUseCase = GetUserListUseCaseImpl(
                                UserRepositoryImpl(
                                    UserDataSourceImpl()
                                )
                            )
                        )
                }
    }
}