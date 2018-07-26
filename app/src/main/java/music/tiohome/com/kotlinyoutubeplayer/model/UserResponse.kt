package music.tiohome.com.kotlinyoutubeplayer.model

import com.google.gson.annotations.SerializedName

class UserResponse {

    @SerializedName("results")
    var courseList: List<Model.Result>? = null

}