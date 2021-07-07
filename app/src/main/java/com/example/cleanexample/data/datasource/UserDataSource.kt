package com.example.cleanexample.data.datasource

import com.example.cleanexample.data.spec.R_Users
import io.reactivex.Single

interface UserDataSource {
    // user list 받아오는 data 레이어 데이터소스
    fun getUserList(size: Int) : Single<R_Users>
}