package com.swarn.locusapp.ui

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.ClipDrawable.HORIZONTAL
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.swarn.locusapp.R
import com.swarn.locusapp.adapter.CustomRecyclerViewAdapter
import com.swarn.locusapp.holder.PhotoViewHolder
import com.swarn.locusapp.util.PhotoUtil
import com.swarn.locusapp.viewmodel.CustomViewModel
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity(), CustomRecyclerViewAdapter.CallbackInterface {

    private lateinit var mHolder: RecyclerView.ViewHolder
    private var mCurrentPosition: Int = 0

    private lateinit var currentPhotoPath: String

    private val TAG = MainActivity::class.java.canonicalName

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mCustomViewModel: CustomViewModel
    private lateinit var mCustomRecyclerViewAdapter: CustomRecyclerViewAdapter

    private val REQUEST_TAKE_PHOTO = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.swarn.locusapp.R.layout.activity_main)

        mRecyclerView = findViewById(com.swarn.locusapp.R.id.custom_recycler_view)

        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mRecyclerView.setHasFixedSize(true)

        val itemDecor = DividerItemDecoration(applicationContext, HORIZONTAL)
        mRecyclerView.addItemDecoration(itemDecor)

        mCustomViewModel = ViewModelProviders.of(this).get(CustomViewModel::class.java)

        mCustomViewModel.getCustomViewFromJson()?.observe(this, Observer {
            mCustomRecyclerViewAdapter = CustomRecyclerViewAdapter(this, it)
            mRecyclerView.adapter = mCustomRecyclerViewAdapter
            mCustomRecyclerViewAdapter.notifyDataSetChanged()
        })
    }

    override fun onResume() {
        super.onResume()
        if (::mCustomRecyclerViewAdapter.isInitialized) {
            mCustomRecyclerViewAdapter.notifyDataSetChanged()
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (::mCustomRecyclerViewAdapter.isInitialized) {
            mCustomRecyclerViewAdapter.notifyDataSetChanged()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    private fun logFormValues() {
        if (mCustomRecyclerViewAdapter != null) {
            for (customView in mCustomRecyclerViewAdapter.getData()!!) {
                Log.d(TAG, "${customView.id} :  ${customView.value}")
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.submit_form_btn -> {
                logFormValues()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        // Create an image file name
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${timeStamp}_", /* prefix */
            ".jpg", /* suffix */
            storageDir /* directory */
        ).apply {
            // Save a file: path for use with ACTION_VIEW intents
            currentPhotoPath = absolutePath
        }
    }


    override fun onHandlePhotoSelection(position: Int, holder: RecyclerView.ViewHolder) {
        mCurrentPosition = position
        mHolder = holder

        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            // Ensure that there's a camera activity to handle the intent
            takePictureIntent.resolveActivity(packageManager)?.also {
                //startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO)
                // Create the File where the photo should go
                val photoFile: File? = try {
                    createImageFile()
                } catch (ex: IOException) {
                    null
                }
                // Continue only if the File was successfully created
                photoFile?.also {
                    val photoURI: Uri = FileProvider.getUriForFile(
                        this,
                        "com.swarn.locusapp.fileprovider",
                        it
                    )
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO)
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode === REQUEST_TAKE_PHOTO && resultCode === Activity.RESULT_OK) {
            val imgFile = File(currentPhotoPath)
            if (imgFile.exists()) {
                val customView = mCustomRecyclerViewAdapter.getData()!![mCurrentPosition]
                customView.value = currentPhotoPath

                PhotoUtil.setPic((mHolder as PhotoViewHolder).photoImgView, currentPhotoPath)
                (mHolder as PhotoViewHolder).photoRemoveBadge.visibility = View.VISIBLE

                mCustomRecyclerViewAdapter.setData(customView, mCurrentPosition)
                mCustomRecyclerViewAdapter.notifyDataSetChanged()
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}
