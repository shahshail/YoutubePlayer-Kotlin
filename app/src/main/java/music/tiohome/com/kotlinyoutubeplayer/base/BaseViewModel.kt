package music.tiohome.com.kotlinyoutubeplayer

import android.arch.lifecycle.ViewModel
import music.tiohome.com.kotlinyoutubeplayer.Di.component.ViewModelInjector
import music.tiohome.com.kotlinyoutubeplayer.Di.module.NetworkModule
import music.tiohome.com.kotlinyoutubeplayer.views.viewModels.PostListViewModel
import net.gahfy.mvvmposts.injection.component.DaggerViewModelInjector
import net.gahfy.mvvmposts.injection.component.ViewModelInjector
import net.gahfy.mvvmposts.injection.module.NetworkModule
import net.gahfy.mvvmposts.ui.post.PostListViewModel

abstract class BaseViewModel:ViewModel(){
    private val injector: ViewModelInjector = DaggerViewModelInjector
            .builder()
            .networkModule(NetworkModule)
            .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is PostListViewModel -> injector.inject(this)
        }
    }
}