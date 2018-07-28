package music.tiohome.com.kotlinyoutubeplayer.views.viewModels

import android.arch.lifecycle.MutableLiveData
import music.tiohome.com.kotlinyoutubeplayer.BaseViewModel
import music.tiohome.com.kotlinyoutubeplayer.model.Post

class PostViewModel: BaseViewModel(){
    private var postTitle =  MutableLiveData<String>()
    private val postBody = MutableLiveData<String>()

    fun bind(post: Post){
        postTitle.value = post.title
        postBody.value = post.body
    }

    fun getPostTitle():MutableLiveData<String>{
        return postTitle
    }

    fun getPostBody():MutableLiveData<String>{
        return postBody
    }
}