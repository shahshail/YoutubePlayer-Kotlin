package music.tiohome.com.kotlinyoutubeplayer.app

import android.app.Application
import android.content.Context
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import music.tiohome.com.kotlinyoutubeplayer.network.ApiFactory
import music.tiohome.com.kotlinyoutubeplayer.network.UsersServices

/**
 * Created by Shail Shah on 07/22/18.
 */
class AppController : Application() {

    var userService: UsersServices? = null
        get() {
            if (field == null) {
                userService = ApiFactory.create()
            }

            return field
        }
    private var scheduler: Scheduler? = null

    fun subscribeScheduler(): Scheduler {
        if (scheduler == null) {
            scheduler = Schedulers.io()
        }

        return scheduler!!
    }

    fun setScheduler(scheduler: Scheduler) {
        this.scheduler = scheduler
    }

    companion object {

        private operator fun get(context: Context): AppController {
            return context.applicationContext as AppController
        }

        fun create(context: Context): AppController {
            return AppController[context]
        }
    }

}
