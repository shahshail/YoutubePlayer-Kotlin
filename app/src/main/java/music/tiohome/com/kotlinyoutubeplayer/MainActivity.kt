package music.tiohome.com.kotlinyoutubeplayer

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val wikiApiServe by lazy {
        WikiApiServices.create()
    }
    var disposable: Disposable? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = MainAdapter()

        fetchjson()
        beginSearch("Trump")


    }

    fun fetchjson(){
        println("Attemting to Fetch JSon")
    }

    private fun beginSearch(srsearch: String) {
        disposable =
                wikiApiServe.hitCountCheck("query", "json", "search", srsearch)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { result -> showResult(result.query.searchinfo.totalhits)},
                                { error -> showError(error.message) }
                        )
    }

    private fun showResult(hits : Int){
        Log.w("MainActivity", "Me called")
        Log.w("MainActivity", "The result is $hits")

    }
    private fun showError(hits : String?){
        Log.w("MainActivity", "Error Occured $hits")

    }
}
