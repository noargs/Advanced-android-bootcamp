package github.noargs.unstructuredconcurency

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

  private var count = 0

  private lateinit var btnDownloadUserData : Button
  private lateinit var btnCount : Button
  private lateinit var tvCount : TextView


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    btnDownloadUserData = findViewById(R.id.btnDownloadUserData)
    btnCount = findViewById(R.id.btnCount)
    tvCount = findViewById(R.id.tvCount)

    btnCount.setOnClickListener {
      tvCount.text = count++.toString()
    }
    btnDownloadUserData.setOnClickListener {

      // `launch` is a builder which launches new coroutine without blocking
      // the current thread.
      // Returns an instance of Job, which can be used as a reference to the coroutine
      // we use `launch` builder for any coroutines that doesn't have any result
      // as the return value
      CoroutineScope(Dispatchers.IO).launch {
        downloadUserData()
      }
    }
  }

  private suspend fun downloadUserData() {
    for (i in 1..200000) {
      Log.i("MyTag", "Downloading user $i in ${Thread.currentThread().name}")

      // to prevent logcat: Unexpected EOF error causes by logcat unable to
      // catch up with all the log messages generating in high speed by a device
      // OR device shutdown OR logd crashes
      // you have to use `suspend` keyword with downloadUserData() function
      delay(100)
    }
  }
}