package com.example.gymnastlink.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.gymnastlink.R

class NewPostActivity : AppCompatActivity() {
    private lateinit var uploadImageButton: Button
    private lateinit var removeImageButton: Button
    private lateinit var imageView: ImageView
    private lateinit var pickImageLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_post)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.new_post_text)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        imageView = findViewById(R.id.imgView)
        uploadImageButton = findViewById<Button>(R.id.upload_image_button).apply {
            setOnClickListener { openImagePicker() }
        }
        removeImageButton = findViewById<Button>(R.id.remove_image_button).apply {
            setOnClickListener { removeImage() }
        }

        pickImageLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    result.data?.data?.let { uri ->
                        handleImageSelection(uri)
                    }
                }
            }
    }

    private fun openImagePicker() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "image/*"
        }
        pickImageLauncher.launch(intent)
    }

    private fun handleImageSelection(uri: Uri) {
        imageView.setImageURI(uri)
        imageView.visibility = ImageView.VISIBLE
        uploadImageButton.visibility = Button.GONE
        removeImageButton.visibility = Button.VISIBLE
    }

    private fun removeImage() {
        imageView.setImageURI(null)
        imageView.visibility = ImageView.GONE
        removeImageButton.visibility = Button.GONE
        uploadImageButton.visibility = Button.VISIBLE
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}