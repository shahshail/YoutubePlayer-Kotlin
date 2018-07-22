package music.tiohome.com.kotlinyoutubeplayer.model


object  Model{
    data class Result(val user: User, val videos: Videos)
    data class User(val id : Int, val name: String, val username :String)
    data class Videos(val id : Int, val name: String, val link : String, val imageUrl : String, val numberOfViews : Int, val channel : Channel)
    data class Channel(val name: String, val profileImageUrl: String, val numberOfSubscribers :String)
}