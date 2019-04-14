package com.swarn.locusapp.viewmodel

import android.app.Application
import android.os.Handler
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.swarn.locusapp.data.CustomView
import com.swarn.locusapp.util.JsonUtil

/**
 * @author Swarn Singh.
 */
class CustomViewModel(application: Application) : AndroidViewModel(application) {

    private var mCustomViewData: MutableLiveData<MutableList<CustomView>>? = null
    private var mApplication: Application = application

    init {
        getCustomViewFromJson()
    }

    fun getCustomViewFromJson(): MutableLiveData<MutableList<CustomView>>? {
        if (mCustomViewData == null) {
            mCustomViewData = MutableLiveData()
            loadCustomViewFromJson()
        }
        return mCustomViewData
    }

    private fun loadCustomViewFromJson() {
        val handler = Handler()
        handler.postDelayed({
            val data = JsonUtil.getCustomViews(mApplication)
            mCustomViewData?.value = data as MutableList<CustomView>?
        }, 100)
    }
}