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
        private fun convertDpToPx(context: Context, dp: Float): Float {
            return dp * context.resources.displayMetrics.density
        }

        fun setPic(imageView: ImageView, photoPath: String?) {
            // Get the dimensions of the View
            var targetW: Int = imageView.width
            var targetH: Int = imageView.height

            if (targetW == 0 || targetH == 0) {
                targetW = convertDpToPx(imageView.context, DEFAULT_SIZE).toInt()
                targetH = convertDpToPx(imageView.context, DEFAULT_SIZE).toInt()
            }

            if (photoPath != null) {
                val bmOptions = BitmapFactory.Options().apply {
                    // Get the dimensions of the bitmap
                    inJustDecodeBounds = true
                    BitmapFactory.decodeFile(photoPath, this)
                    val photoW: Int = outWidth
                    val photoH: Int = outHeight

                    // Determine how much to scale down the image
                    val scaleFactor: Int = Math.min(photoW / targetW, photoH / targetH)

                    // Decode the image file into a Bitmap sized to fill the View
                    inJustDecodeBounds = false
                    inSampleSize = scaleFactor
                    inPurgeable = true
                }
                BitmapFactory.decodeFile(photoPath, bmOptions)?.also { bitmap ->
                    imageView.setImageBitmap(bitmap)
                }
            }

        }
    }
}