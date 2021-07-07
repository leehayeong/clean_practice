package com.example.cleanexample.data.datasource

import com.example.cleanexample.data.retrofit.UserApi
import com.example.cleanexample.data.retrofit.UserEndpoints
import com.example.cleanexample.data.spec.R_Users
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserDataSourceImpl() : UserDataSource {
    override fun getUserList(size: Int): Single<R_Users> {
        val request = UserApi.buildService(UserEndpoints::class.java)
        val call = request.getUsers(size)

        return Single.create { emitter ->
            call.enqueue(object : Callback<R_Users> {
                override fun onResponse(call: Call<R_Users>, response: Response<R_Users>) {
                    if (response.isSuccessful) {
                        emitter.onSuccess(response.body()!!)
                        return
                    }
                }

                override fun onFailure(call: Call<R_Users>, t: Throwable) {
                    emitter.onError(Exception("Exception in UserDataSourceImpl"))
                }
            })
        }
    }
}