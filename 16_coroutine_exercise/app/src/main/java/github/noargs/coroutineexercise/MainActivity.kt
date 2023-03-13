package github.noargs.coroutineexercise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

  private var count = 0
  private lateinit var userMessage: TextView
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

  private suspend fun downloadUserData() {
    for (i in 1..1000000) {
      // Log.i("MyTag", "Downloading user $i in ${Thread.currentThread().name}")

      // we cannot touch this (IO) Thread as view was initially created in Main Thread
      // however Coroutine give us opportunity to switch the thread `withContext()`
//      userMessage.text = "Downloading user $i, ${Thread.currentThread().name}"
      withContext(Dispatchers.Main) {
        userMessage.text = "Downloading user $i, in ${Thread.currentThread().name}"
        delay(1000)
      }
    }
  }
}