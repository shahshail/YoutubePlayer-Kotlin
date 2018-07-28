package music.tiohome.com.kotlinyoutubeplayer.model.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import music.tiohome.com.kotlinyoutubeplayer.model.CourseDao
import music.tiohome.com.kotlinyoutubeplayer.model.Videos

@Database(entities = arrayOf(Videos::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun courseDao(): CourseDao
}