package com.indramahkota.data.interceptor

import android.content.Context
import com.indramahkota.data.utils.ConnectionHelper
import com.indramahkota.data.utils.MessageConstant.NO_INTERNET_CONNECTION_ERROR
import java.io.IOException
import okhttp3.Interceptor
import okhttp3.Response

class ConnectivityInterceptor(private val context: Context) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!ConnectionHelper(context).isOnline) throw IOException(NO_INTERNET_CONNECTION_ERROR)
        return chain.proceed(chain.request())
    }
}