package me.dio.bankline.data

import android.util.Log
import me.dio.bankline.data.remote.BanklineApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import androidx.lifecycle.liveData as liveData1

object BanklineRepository {

    private val TAG = javaClass.simpleName

    private val restApi by lazy {
        Retrofit.Builder()
            .baseUrl("http://localhost:8090/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BanklineApi::class.java)
    }

        fun findBankStatement(accountHolderId: Int) = liveData1 {
            emit(State.Wait)
            try {
                emit(State.Success(data = restApi.findBankStatement(accountHolderId)))
            }catch(e: Exception){
                Log.e(TAG, e.message, e)
                emit(State.Error(e.message))
            }
        }

}
