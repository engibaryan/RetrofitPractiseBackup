package com.example.retrofitlesson

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
//import androidx.lifecycle.viewModelScope
import com.example.retrofitlesson.retrofit.Article
import com.example.retrofitlesson.retrofit.Datasource
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private val _posts = MutableLiveData<Result<Article>>()
    val posts: LiveData<Result<Article>> = _posts

    fun getPosts() {
        viewModelScope.launch {
            try{
                val response = Datasource().loadNews()
                _posts.postValue(Result.success(response))
            } catch (e: Exception) {
                _posts.postValue(Result.error(e))
            }
        }
    }
}

sealed class Result<out T : Any> {
    data class Loading(val message: String = "") : Result<Nothing>()
    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()

    companion object {
        fun <T : Any> loading(message: String = ""): Result<T> = Loading(message)
        fun <T : Any> success(data: T): Result<T> = Success(data)
        fun error(exception: Exception): Result<Nothing> = Error(exception)
    }
}