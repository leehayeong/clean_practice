package com.example.cleanexample.data.spec

import com.example.cleanexample.data.datasource.UserDataSource
import com.example.cleanexample.data.spec.mapper.UserMapper
import com.example.cleanexample.domain.DataResult
import com.example.cleanexample.domain.UserRepository
import com.example.cleanexample.domain.entity.Users
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class UserRepositoryImpl(
    private val userDataSource: UserDataSource
) : UserRepository {
    override fun getUserList(size: Int): Observable<DataResult<Users>> {
        return Observable.create { emitter ->
            emitter.onNext(DataResult.Loading)  // 로딩 발행

            // 통신 결과를 entity 로 매핑
            userDataSource.getUserList(size).map {
                UserMapper.mapToEntity(it)
            }
                .subscribeOn(Schedulers.io())
                .doFinally { emitter.onComplete() }
                .subscribe(
                    { emitter.onNext(DataResult.Success(it)) },
                    { emitter.onNext(DataResult.Error(it as Exception)) }
                )

        }
    }
}