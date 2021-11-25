package com.dudarenkavitali.data.net

import com.dudarenkavitali.data.entity.ErrorResponse
import com.dudarenkavitali.domain.entity.AppErrorType
import com.dudarenkavitali.domain.entity.AppException
import com.google.gson.Gson
import io.reactivex.ObservableTransformer
import io.reactivex.SingleTransformer
import retrofit2.HttpException
import java.net.SocketTimeoutException

class RestErrorParser (val gson: Gson) {
    fun <T> parseError(): SingleTransformer<T, T> {
        return SingleTransformer { observable ->
            observable.onErrorReturn { throwable ->
                when (throwable) {
                    is HttpException -> {
                        val errorBody = throwable.response().errorBody()?.string()
                            ?: throw AppException(AppErrorType.UNKNOWN)
                        try {
                            val errorObject = gson.fromJson<ErrorResponse>(errorBody, ErrorResponse::class.java)
                            when (errorObject.errorCode) {
                                400 -> {
                                    throw AppException(AppErrorType.INCORRECT_ID)
                                }
                                200 -> {
                                    throw AppException(AppErrorType.BLOCKED)
                                }
                            }
                        } catch (e: Exception) {

                        }
                        throw AppException(AppErrorType.UNKNOWN)
                    }
                    is SocketTimeoutException -> {
                        throw AppException(AppErrorType.SERVER_IS_NOT_AVAILABLE)
                    }
                    else -> {
                        throw AppException(AppErrorType.UNKNOWN)
                    }
                }
            }
        }
    }
}