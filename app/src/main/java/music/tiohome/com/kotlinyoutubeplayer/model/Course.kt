package music.tiohome.com.kotlinyoutubeplayer.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey

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

@Entity(tableName = "course")
data class Videos(@field:PrimaryKey var id : Int,
                  var name: String,
                  var link : String,
                  var imageUrl : String,
                  var numberOfViews:Int,
                  @Ignore
                  val channel: Channel){ constructor() : this(0, "", "", "", 0, Channel("","",0)) }

data class Channel(val name: String,
                   val profileImageUrl : String,
                   val numberOfSubscribers : Int)

