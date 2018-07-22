package music.tiohome.com.kotlinyoutubeplayer.network

import retrofit2.http.GET
import retrofit2.http.Url
import java.util.*

interface UsersServices{
    @GET
    fun fetchUser(@Url url :String): Observable<UserResponse>
}