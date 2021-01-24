package com.logic.demo.logic

import android.util.Log
import androidx.lifecycle.liveData
import com.logic.demo.logic.local.CountDataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

object Repository {

    fun getCount()= fire(Dispatchers.IO) {
        try {
            val count = CountDataStore.get()
            Log.d("Repository getCount", "$count")
            Result.success(count)
        } catch (e: Exception) {
            Result.failure<Exception>(RuntimeException(""))
        }
    }

    fun updateCount(count: Int) = fire(Dispatchers.IO) {
        CountDataStore.update(count)
        Result.success(Unit)
    }

    private fun <T> fire(context: CoroutineContext, block: suspend () -> Result<T>) =
        liveData<Result<T>>(context) {
            val result = try {
                block()
            } catch (e: Exception) {
                Result.failure<T>(e)
            }
            emit(result)
        }
}