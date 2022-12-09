package com.example.testapplication

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.testapplication.databinding.ActivitySecondBinding
import com.example.testapplication.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityThirdBinding
    private lateinit var secondBinding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val differentLayout = intent.extras?.getBoolean("DIFFERENT_LAYOUT", false)
        if (differentLayout == true) {
            secondBinding = ActivitySecondBinding.inflate(layoutInflater)
            setContentView(secondBinding.root)
        } else {
            binding = ActivityThirdBinding.inflate(layoutInflater)
            setContentView(binding.root)
            binding.openCamera.setOnClickListener(this)
            binding.startSecondActivity.setOnClickListener(this)
            binding.openEmail.setOnClickListener(this)
        }

    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.openCamera -> {
                val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                try {
                    startActivityForResult(takePictureIntent, 2000)
                } catch (e: ActivityNotFoundException) {
                    // display error state to the user
                }
            }
            R.id.startSecondActivity -> {
                val intent = Intent(this, SecondActivity::class.java)
                startActivity(intent)
            }
            R.id.openEmail -> {

                val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "0721858821"))
                startActivity(intent)
            }
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Toast.makeText(this, "Third activity onNewIntent", Toast.LENGTH_LONG).show()
    }
}