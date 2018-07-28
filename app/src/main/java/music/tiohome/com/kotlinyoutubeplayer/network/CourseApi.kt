package music.tiohome.com.kotlinyoutubeplayer.network

import io.reactivex.Observable
import music.tiohome.com.kotlinyoutubeplayer.model.Result
import retrofit2.http.GET

interface CourseApi {

    @GET("home_feed")
    fun getCourses() : Observable<Result>
}