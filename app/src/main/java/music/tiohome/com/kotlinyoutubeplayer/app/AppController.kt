package music.tiohome.com.kotlinyoutubeplayer.app

import android.app.Application
import android.content.Context
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class AppController : Application() {
   // private userServices : UserServices;
    private lateinit var scheduler : Scheduler

    private operator fun get(context: Context): AppController {
        return context.applicationContext as AppController
    }

    fun create(context: Context): AppController {
        return get(context)
    }

    fun getUserService(): UsersService {
        if (usersService == null) {
            usersService = ApiFactory.create()
        }

        return usersService
    }

    fun subscribeScheduler(): Scheduler {
        if (scheduler == null) {
            scheduler = Schedulers.io()
        }

        return scheduler
    }

    fun setUserService(usersService: UsersService) {
        this.usersService = usersService
    }

    fun setScheduler(scheduler: Scheduler) {
        this.scheduler = scheduler
    }
}