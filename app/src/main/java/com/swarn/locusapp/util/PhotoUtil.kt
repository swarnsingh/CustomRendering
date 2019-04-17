package com.swarn.locusapp.util

import android.content.Context
import android.graphics.BitmapFactory
import android.widget.ImageView

/**
 * @author Swarn Singh.
 */
const val DEFAULT_SIZE = 250F

class PhotoUtil {
    companion object {
        fun convertDpToPx(context: Context, dp: Float): Float {
            return dp * context.resources.displayMetrics.density
        }
    }
}