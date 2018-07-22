package music.tiohome.com.kotlinyoutubeplayer.network

import io.reactivex.Observable
import music.tiohome.com.kotlinyoutubeplayer.model.Model
import retrofit2.http.GET
import retrofit2.http.Url

interface UsersServices{
    @GET
    fun fetchUser(@Url url :String): Observable<Model.Result>
}