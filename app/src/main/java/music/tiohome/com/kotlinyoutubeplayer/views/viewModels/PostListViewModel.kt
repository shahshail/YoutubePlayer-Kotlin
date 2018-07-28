package music.tiohome.com.kotlinyoutubeplayer.views.viewModels

import android.arch.lifecycle.MutableLiveData
import android.view.View
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import music.tiohome.com.kotlinyoutubeplayer.BaseViewModel
import music.tiohome.com.kotlinyoutubeplayer.R
import music.tiohome.com.kotlinyoutubeplayer.model.Post
import music.tiohome.com.kotlinyoutubeplayer.network.PostApi
import music.tiohome.com.kotlinyoutubeplayer.views.adapters.PostListAdapter
import javax.inject.Inject

class PostListViewModel : BaseViewModel(){

    @Inject
    lateinit var postApi: PostApi

    private lateinit var disposable: Disposable
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage:MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadPost() }
    val postListAdapter: PostListAdapter = PostListAdapter()


    init {
    loadPost()
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose() //Dispose subscription is viewModel is no longer available

    }

    private fun loadPost(){
        disposable = postApi.getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetrievePostListStart() }
                .doOnTerminate { onRetrievePostListFinish() }
                .subscribe(
                        // Add result
                        { result -> onRetrievePostListSuccess(result) },
                        { onRetrievePostListError() }
                )
    }


    private fun onRetrievePostListStart(){
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrievePostListFinish(){
        loadingVisibility.value = View.GONE
    }

    private fun onRetrievePostListSuccess(post: List<Post>){
        postListAdapter.updatePostList(post)
    }

    private fun onRetrievePostListError(){
        errorMessage.value = R.string.post_error
    }

}