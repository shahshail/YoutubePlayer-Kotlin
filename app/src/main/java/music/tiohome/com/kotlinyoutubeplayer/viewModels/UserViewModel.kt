package music.tiohome.com.kotlinyoutubeplayer.viewModels

import android.content.Context
import android.databinding.ObservableField
import android.databinding.ObservableInt
import android.view.View
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import music.tiohome.com.kotlinyoutubeplayer.R
import music.tiohome.com.kotlinyoutubeplayer.app.AppController
import music.tiohome.com.kotlinyoutubeplayer.model.Model
import music.tiohome.com.kotlinyoutubeplayer.utils.Constant.Companion.RANDOM_USER_URL
import java.util.*

class UserViewModel(private var context: Context?) : Observable() {

    var progressBar: ObservableInt
    var userRecycler: ObservableInt
    var userLabel: ObservableInt
    var messageLabel: ObservableField<String>

    private val courseList: MutableList<Model.Result>
    private var compositeDisposable: CompositeDisposable? = CompositeDisposable()

    init {
        this.courseList = ArrayList<Model.Result>()
        progressBar = ObservableInt(View.GONE)
        userRecycler = ObservableInt(View.GONE)
        userLabel = ObservableInt(View.VISIBLE)
        messageLabel = ObservableField(context!!.getString(R.string.default_message_loading_users))
    }

    fun onClickFabToLoad(view: View) {
        initializeViews()
        fetchUsersList()
    }

    //It is "public" to show an example of test
    fun initializeViews() {
        userLabel.set(View.GONE)
        userRecycler.set(View.GONE)
        progressBar.set(View.VISIBLE)
    }

    private fun fetchUsersList() {

        val appController = AppController.create(context!!)
        val usersService = appController.userService

        val disposable = usersService!!.fetchUser(RANDOM_USER_URL)
                .subscribeOn(appController.subscribeScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ userResponse ->
                    updateUserDataList(userResponse.courseList!!)
                    progressBar.set(View.GONE)
                    userLabel.set(View.GONE)
                    userRecycler.set(View.VISIBLE)
                }, {
                    messageLabel.set(context!!.getString(R.string.error_message_loading_users))
                    progressBar.set(View.GONE)
                    userLabel.set(View.VISIBLE)
                    userRecycler.set(View.GONE)
                })

        compositeDisposable!!.add(disposable)
    }

    private fun updateUserDataList(courses: List<Model.Result>) {
        courseList.addAll(courses)
        setChanged()
        notifyObservers()
    }

    fun getCourseList(): List<Model.Result> {
        return courseList
    }

    private fun unSubscribeFromObservable() {
        if (compositeDisposable != null && !compositeDisposable!!.isDisposed) {
            compositeDisposable!!.dispose()
        }
    }

    fun reset() {
        unSubscribeFromObservable()
        compositeDisposable = null

    }
}