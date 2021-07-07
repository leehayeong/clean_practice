package com.example.cleanexample.domain

import com.example.cleanexample.domain.entity.Users
import io.reactivex.Observable

interface UserRepository {
    fun getUserList(size: Int): Observable<DataResult<Users>>
}