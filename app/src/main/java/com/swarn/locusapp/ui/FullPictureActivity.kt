package com.swarn.locusapp.ui

import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.swarn.locusapp.R
import com.swarn.locusapp.data.ViewType
import java.io.File


class FullPictureActivity : AppCompatActivity() {

    private lateinit var mFullPictureImgView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_picture)

        mFullPictureImgView = findViewById(R.id.full_picture_img_view)

        val file = File(intent.extras.get(ViewType.PHOTO.toString()) as String)
        val picUri = Uri.fromFile(file)

        mFullPictureImgView.setImageURI(picUri)
    }
}
