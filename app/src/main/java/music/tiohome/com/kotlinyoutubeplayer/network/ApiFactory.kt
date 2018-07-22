package music.tiohome.com.kotlinyoutubeplayer.network

import music.tiohome.com.kotlinyoutubeplayer.utils.Constant.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {

    fun create(): UsersServices {
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        return retrofit.create(UsersServices::class.java!!)
    }

}
