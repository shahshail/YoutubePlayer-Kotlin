package music.tiohome.com.kotlinyoutubeplayer.views.adapters

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import music.tiohome.com.kotlinyoutubeplayer.R
import music.tiohome.com.kotlinyoutubeplayer.databinding.ItemCoursesBinding
import music.tiohome.com.kotlinyoutubeplayer.model.Videos
import music.tiohome.com.kotlinyoutubeplayer.views.viewModels.PostViewModel

class CourseListAdapter: RecyclerView.Adapter<CourseListAdapter.ViewHolder>() {
    private lateinit var courseList:List<Videos>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseListAdapter.ViewHolder {
        val binding: ItemCoursesBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_courses, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CourseListAdapter.ViewHolder, position: Int) {
        holder.bind(courseList[position])
    }

    override fun getItemCount(): Int {
        return if(::courseList.isInitialized) courseList.size else 0
    }

    fun updatePostList(postList:List<Videos>){
        this.courseList = postList
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemCoursesBinding):RecyclerView.ViewHolder(binding.root){
        private val viewModel = PostViewModel()

        fun bind(post:Videos){
            viewModel.bind(post)
            binding.viewModel = viewModel
        }
    }
}