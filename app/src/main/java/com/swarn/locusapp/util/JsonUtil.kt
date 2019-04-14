package com.swarn.locusapp.util

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.swarn.locusapp.R
import com.swarn.locusapp.data.CustomView
import java.io.InputStreamReader


/**
 * @author Swarn Singh.
 */
class JsonUtil {

    companion object {
        fun getCustomViews(context: Context): List<CustomView>? {
            val inputStream = context.resources.openRawResource(R.raw.customview)
            val reader = InputStreamReader(inputStream, "UTF-8")
            val collectionType = object : TypeToken<List<CustomView>>() {}.type
            return Gson().fromJson<List<CustomView>>(reader, collectionType)
        }
    }
}