package github.noargs.coroutineexercise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

  private var count = 0
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val btnDownloadUserData = findViewById<Button>(R.id.btnDownloadUserData)
    val btnCount = findViewById<Button>(R.id.btnCount)
    val counter = findViewById<TextView>(R.id.tvCount)

    btnCount.setOnClickListener {
      counter.text = count++.toString()
      Log.i("MyTag", "Button count from ${Thread.currentThread().name} thread")
    }

    btnDownloadUserData.setOnClickListener {
      CoroutineScope(Dispatchers.IO).launch {
        downloadUserData()
      }
    }
  }

  private fun downloadUserData() {
    for (i in 1..1000000) {
      Log.i("MyTag", "Downloading user $i in ${Thread.currentThread().name}")
    }
  }
}