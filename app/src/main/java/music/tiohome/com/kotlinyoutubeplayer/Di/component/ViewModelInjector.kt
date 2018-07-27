package music.tiohome.com.kotlinyoutubeplayer.Di.component

import dagger.Component
import music.tiohome.com.kotlinyoutubeplayer.Di.module.NetworkModule
import music.tiohome.com.kotlinyoutubeplayer.views.viewModels.PostListViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector{

    fun inject(postListViewModel: PostListViewModel)

    @Component.Builder
    interface Builder{
        fun build(): ViewModelInjector

        fun networkModule(networkModule :NetworkModule): Builder
    }
}