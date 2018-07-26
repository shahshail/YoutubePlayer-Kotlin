package music.tiohome.com.kotlinyoutubeplayer.viewModels

import android.content.Context
import android.databinding.BaseObservable
import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide
import music.tiohome.com.kotlinyoutubeplayer.model.Model

class ItemUserViewModel(private var result: Model.Result?, private val context: Context) : BaseObservable() {

    val profileBanner: String
        get() = result!!.videos.imageUrl

    val profileThumb: String
        get() = result!!.videos.channel.profileImageUrl

    val courseTitle: String
        get() = result!!.videos.name

    val courseSubtitle: Int
        get() = result!!.videos.numberOfViews


//    fun onItemClick(v: View) {
//        context.startActivity(UserDetailActivity.fillDetail(v.context, user))
//    }

    fun setCourse(course: Model.Result) {
        this.result = course
        notifyChange()
    }

    companion object {

        // Loading Image using Glide Library.
        @BindingAdapter("imageUrl")
        fun setImageUrl(imageView: ImageView, url: String) {
            Glide.with(imageView.context).load(url).into(imageView)
        }
    }
}
