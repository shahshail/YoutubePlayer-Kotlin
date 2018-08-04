package music.tiohome.com.kotlinyoutubeplayer.views.viewModels

import android.arch.lifecycle.MutableLiveData
import music.tiohome.com.kotlinyoutubeplayer.BaseViewModel
import music.tiohome.com.kotlinyoutubeplayer.model.Videos

class PostViewModel: BaseViewModel(){

    //Courses
    private var courseTitle =  MutableLiveData<String>()
    private var courseLink =  MutableLiveData<String>()
    private var courseImageURL =  MutableLiveData<String>()
    private val noOfViews = MutableLiveData<String>()

    //Channel
    private var channelName =  MutableLiveData<String>()
    private var channelImageURL =  MutableLiveData<String>()
    private var channelNoOfSubscriber =  MutableLiveData<String>()

    private var subViewInfo =  MutableLiveData<String>()

    fun bind(course: Videos){
        //Courses
        courseTitle.value = course.name
        noOfViews.value = course.numberOfViews.toString()
        courseLink.value = course.link
        courseImageURL.value = course.imageUrl

        //Channel
        channelName.value = course.channel.name
        channelImageURL.value = course.channel.profileImageUrl
        channelNoOfSubscriber.value = course.channel.numberOfSubscribers.toString()

        //utils
        subViewInfo.value = "${course.channel.name}  ${getNoOfViews()} views"
    }

    fun getSubViewInfo():MutableLiveData<String>{
       return subViewInfo
    }

    fun getCourseTitle():MutableLiveData<String>{
        return courseTitle
    }

    fun getCourseImageURL():MutableLiveData<String>{
        return courseImageURL
    }


    fun getChannelImageURL():MutableLiveData<String>{
        return channelImageURL
    }

    fun getNoOfViews(): String{
        return "${noOfViews.value!!.toInt()/1000}K"
    }

}