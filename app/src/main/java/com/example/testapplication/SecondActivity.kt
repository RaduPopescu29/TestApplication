package com.example.testapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import com.example.testapplication.databinding.ActivityMainBinding
import com.example.testapplication.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivitySecondBinding

    companion object {
        const val TEXT_UPDATED = "text_updated"
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Toast.makeText(this, "On new Intent called from second activity", Toast.LENGTH_LONG).show()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener(this)
        binding.startThirdActivity.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.button -> {
                val intent = Intent()
                val bundle = Bundle()
                bundle.putString(TEXT_UPDATED, binding.editTextTextPersonName2.text.toString())
                intent.putExtras(bundle)
                setResult(RESULT_OK, intent)
                finish()
            }
            R.id.startThirdActivity -> {
                val intent = Intent(this, ThirdActivity::class.java)
                intent.putExtra("DIFFERENT_LAYOUT",false)
                startActivity(intent)
            }
        }
    }
}