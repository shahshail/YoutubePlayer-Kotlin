package music.tiohome.com.kotlinyoutubeplayer.network

import io.reactivex.Observable
import music.tiohome.com.kotlinyoutubeplayer.model.Post
import retrofit2.http.GET

interface PostApi {

    @GET("/posts")
    fun getPosts() : Observable<List<Post>>
}