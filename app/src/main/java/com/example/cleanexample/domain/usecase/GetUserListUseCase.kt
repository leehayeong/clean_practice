package com.example.cleanexample.domain.usecase

import com.example.cleanexample.domain.DataResult
import com.example.cleanexample.domain.entity.Users
import io.reactivex.Observable

interface GetUserListUseCase {
    operator fun invoke(size: Int): Observable<DataResult<Users>>
}