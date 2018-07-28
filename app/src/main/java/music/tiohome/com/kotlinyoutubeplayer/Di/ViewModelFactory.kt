package music.tiohome.com.kotlinyoutubeplayer.Di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.persistence.room.Room
import android.support.v7.app.AppCompatActivity
import music.tiohome.com.kotlinyoutubeplayer.model.database.AppDatabase
import music.tiohome.com.kotlinyoutubeplayer.views.viewModels.CourseListViewModel

class ViewModelFactory(private val activity: AppCompatActivity): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CourseListViewModel::class.java)) {
            val db = Room.databaseBuilder(activity.applicationContext, AppDatabase::class.java, "posts").build()
            @Suppress("UNCHECKED_CAST")
            return CourseListViewModel(db.courseDao()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}