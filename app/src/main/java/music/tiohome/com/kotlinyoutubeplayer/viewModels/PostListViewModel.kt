package music.tiohome.com.kotlinyoutubeplayer.viewModels

import music.tiohome.com.kotlinyoutubeplayer.BaseViewModel
import music.tiohome.com.kotlinyoutubeplayer.network.PostApi
import javax.inject.Inject

class PostListViewModel : BaseViewModel(){

    @Inject
    lateinit var postApi: PostApi

}