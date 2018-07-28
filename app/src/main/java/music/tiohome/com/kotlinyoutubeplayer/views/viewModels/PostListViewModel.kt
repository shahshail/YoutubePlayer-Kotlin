package music.tiohome.com.kotlinyoutubeplayer.views.viewModels

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import android.view.View
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import music.tiohome.com.kotlinyoutubeplayer.BaseViewModel
import music.tiohome.com.kotlinyoutubeplayer.R
import music.tiohome.com.kotlinyoutubeplayer.model.Result
import music.tiohome.com.kotlinyoutubeplayer.network.CourseApi
import music.tiohome.com.kotlinyoutubeplayer.views.adapters.CourseListAdapter
import javax.inject.Inject

class PostListViewModel : BaseViewModel(){

    @Inject
    lateinit var postApi: CourseApi

    private lateinit var disposable: Disposable
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage:MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadPost() }
    val courseListAdapter: CourseListAdapter = CourseListAdapter()


    init {
    loadPost()
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose() //Dispose subscription is viewModel is no longer available

    }

    private fun loadPost(){
        disposable = postApi.getCourses()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetrieveCourseListStart() }
                .doOnTerminate { onRetrieveCourseListFinish() }
                .subscribe(
                        // Add result
                        { result -> onRetrieveCourseListSuccess(result) },
                        { error -> onRetrieveCourseListError(error) }
                )
    }


    private fun onRetrieveCourseListStart(){
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrieveCourseListFinish(){
        loadingVisibility.value = View.GONE
    }

    private fun onRetrieveCourseListSuccess(post: Result){
        courseListAdapter.updatePostList(post.videos)
        Log.w("class",post.toString())
    }

    private fun onRetrieveCourseListError(error: Throwable){
        errorMessage.value = R.string.post_error
        Log.w("class",error.message)
    }

}