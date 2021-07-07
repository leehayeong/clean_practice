package com.example.cleanexample.domain.usecase

import com.example.cleanexample.domain.DataResult
import com.example.cleanexample.domain.UserRepository
import com.example.cleanexample.domain.entity.Users
import io.reactivex.Observable

class GetUserListUseCaseImpl(
    private val userRepository: UserRepository
) : GetUserListUseCase {
    override fun invoke(size: Int): Observable<DataResult<Users>> {
        // userRepositoryImpl 의 getUserList 가 수행될 것.
        return userRepository.getUserList(size)
    }
}