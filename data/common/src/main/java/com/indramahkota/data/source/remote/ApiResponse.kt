package com.indramahkota.data.source.remote

import com.indramahkota.data.utils.MessageConstant.UNKNOWN_ERROR
import com.indramahkota.data.utils.NumberConstant.EMPTY_ERROR_CODE
import retrofit2.Response

sealed class ApiResponse<T> {
    companion object {
        fun <T> create(errorCode: Int, error: Throwable): ApiErrorResponse<T> {
            return ApiErrorResponse(errorCode, error.message ?: UNKNOWN_ERROR)
        }

        fun <T> create(response: Response<T>): ApiResponse<T> {
            return if (response.isSuccessful) {
                val body = response.body()
                if (body == null || response.code() == EMPTY_ERROR_CODE) ApiEmptyResponse()
                else ApiSuccessResponse(body)
            } else {
                val msg = response.errorBody()?.string()
                val errorMessage = if (msg.isNullOrEmpty()) response.message()
                else UNKNOWN_ERROR
                ApiErrorResponse(response.code(), errorMessage)
            }
        }
    }
}

class ApiEmptyResponse<T> : ApiResponse<T>()
data class ApiSuccessResponse<T>(val body: T) : ApiResponse<T>()
data class ApiErrorResponse<T>(val code: Int?, val message: String?) : ApiResponse<T>()
