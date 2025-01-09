package com.example.gymnastlink.ui.fragments

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.gymnastlink.R
import com.example.gymnastlink.ui.MainActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class NewPostFragment : Fragment() {

    private lateinit var uploadImageButton: Button
    private lateinit var removeImageButton: Button
    private lateinit var imageView: ImageView
    private lateinit var pickImageLauncher: ActivityResultLauncher<Intent>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_new_post, container, false)
        (activity as? MainActivity)?.setFragmentTitle(getString(R.string.new_post_text))

        view.findViewById<FloatingActionButton>(R.id.cancel_fab).apply {
            setOnClickListener {
                findNavController().navigateUp()
            }
        }
        imageView = view.findViewById(R.id.imgView)
        uploadImageButton = view.findViewById<Button>(R.id.upload_image_button).apply {
            setOnClickListener { openImagePicker() }
        }
        removeImageButton = view.findViewById<Button>(R.id.remove_image_button).apply {
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

        return view
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
        imageView.setImageResource(0)
        imageView.visibility = ImageView.GONE
        removeImageButton.visibility = Button.GONE
        uploadImageButton.visibility = Button.VISIBLE
    }
}