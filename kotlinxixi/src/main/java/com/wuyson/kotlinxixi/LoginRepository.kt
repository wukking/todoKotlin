package com.wuyson.kotlinxixi

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}
//Dispatchers.Main - 使用此调度程序可在 Android 主线程上运行协程。此调度程序只能用于与界面交互和执行快速工作。示例包括调用 suspend 函数，运行 Android 界面框架操作，以及更新 LiveData 对象。
//Dispatchers.IO - 此调度程序经过了专门优化，适合在主线程之外执行磁盘或网络 I/O。示例包括使用 Room 组件、从文件中读取数据或向文件中写入数据，以及运行任何网络操作。
//Dispatchers.Default - 此调度程序经过了专门优化，适合在主线程之外执行占用大量 CPU 资源的工作。用例示例包括对列表排序和解析 JSON。
class LoginRepository(private val responseParser: LoginResponseParser) {
    private val loginUrl = "https://example.com/login"
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO

    // Function that makes the network request, blocking the current thread
    suspend fun makeLoginRequest(
        jsonBody: String
    ): Result<LoginResponse> {
        val url = URL(loginUrl)
        (url.openConnection() as? HttpURLConnection)?.run {
            requestMethod = "POST"
            setRequestProperty("Content-Type", "application/json; utf-8")
            setRequestProperty("Accept", "application/json")
            doOutput = true
            outputStream.write(jsonBody.toByteArray())
            return Result.Success(responseParser.parse(inputStream))
        }
        return withContext(defaultDispatcher){
            Result.Error(Exception("Cannot open HttpURLConnection"))
        }
    }
}

class LoginResponseParser{
    fun parse(inputStream:InputStream):LoginResponse{

        return LoginResponse()
    }
}
class LoginResponse
