package music.tiohome.com.kotlinyoutubeplayer

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.list_item.view.*

class MainAdapter : RecyclerView.Adapter<AdapterViewHolder>() {




    //Returnds View Holder object for each and every views ..
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        //inflate our list layout to the adapter for caching
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellRows = layoutInflater.inflate(R.layout.list_item,parent,false)

        return AdapterViewHolder(cellRows)
    }


    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        holder.itemView?.tv_main_title?.text = "Hello there"

    }

    override fun getItemCount(): Int {
       return 5
    }



}

class AdapterViewHolder(v:View) : RecyclerView.ViewHolder(v){

}