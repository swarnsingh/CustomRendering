package com.swarn.locusapp.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * @author Swarn Singh.
 */
data class CustomView(
    @SerializedName("type")
    @Expose
    var type: String?,

    @SerializedName("id")
    @Expose
    var id: String?,

    @SerializedName("title")
    @Expose
    var title: String?,

    @SerializedName("dataMap")
    @Expose
    var dataMap: DataMap?,

    var value: String?

) {
    data class DataMap(
        var options: List<String>
    )
}