package music.tiohome.com.kotlinyoutubeplayer.model

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface CourseDao {
    @get:Query("SELECT * FROM course")
    val all : List<Videos>

    @Insert
    fun insertAll(vararg videos: Videos)
}