package music.tiohome.com.kotlinyoutubeplayer.views.viewModels

import android.arch.lifecycle.MutableLiveData
import android.view.View
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import music.tiohome.com.kotlinyoutubeplayer.BaseViewModel
import music.tiohome.com.kotlinyoutubeplayer.network.PostApi
import javax.inject.Inject

class PostListViewModel : BaseViewModel(){

    @Inject
    lateinit var postApi: PostApi

    private lateinit var disposable: Disposable
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()



    init {
    loadPost()
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose() //Dispose subscription is viewModel is no longer available

    }

    private fun loadPost(){
        disposable =
                postApi.getPosts()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe{onRetrievePostListStart()}
                        .doOnTerminate {onRetrievePostListFinish()}
                        .subscribe(
                                {onRetrievePostListSuccess()},
                                {onRetrievePostListError()}
                        )
    }

    private fun onRetrievePostListStart(){
        loadingVisibility.value = View.VISIBLE
    }

    private fun onRetrievePostListFinish(){
        loadingVisibility.value = View.GONE
    }

    private fun onRetrievePostListSuccess(){

    }

    private fun onRetrievePostListError(){

    }

}