package music.tiohome.com.kotlinyoutubeplayer.model

import android.arch.persistence.room.Entity

/**
 * Class which provides a model for post
 * @constructor Sets all properties of the post
 * @property userId the unique identifier of the author of the post
 * @property id the unique identifier of the post
 * @property title the title of the post
 * @property body the content of the post
 */

data class Result(
        val id : Int,
        val user: User,
        val videos: List<Videos>
)
data class User(val id : Int, val name: String, val username: String)

@Entity

data class Videos(val id : Int, val name: String, val link : String, val imageUrl : String, val numberOfViews:Int, val channel: Channel)

data class Channel(val name: String, val profileImageUrl : String, val numberOfSubscribers : Int)
