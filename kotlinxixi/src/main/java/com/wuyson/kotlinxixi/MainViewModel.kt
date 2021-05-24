package com.wuyson.kotlinxixi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(val loginRepository: LoginRepository) :ViewModel(){

    //应用从主线程上的 View 层调用 login() 函数。
    //launch 创建一个新的协程，以在主线程上发出网络请求，然后该协程开始执行。
    //在协程内，调用 loginRepository.makeLoginRequest() 现在会挂起协程的进一步执行操作，
    // 直至 makeLoginRequest() 中的 withContext 块结束运行。
    //withContext 块结束运行后，login() 中的协程在主线程上恢复执行操作，并返回网络请求的结果。
    fun login(username: String, token: String){
        //此协程应在为 I/O 操作预留的线程上执行
        viewModelScope.launch {
            val jsonBody = "{ username: \"$username\", token: \"$token\"}"
            val result = try {
                loginRepository.makeLoginRequest(jsonBody)
            } catch(e: Exception) {
                Result.Error(Exception("Network request failed"))
            }

            when (result) {
                is Result.Success<LoginResponse> -> "success"
                else -> "// Show error in UI"
            }
        }

    }
}