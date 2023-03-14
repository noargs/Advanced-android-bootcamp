package github.noargs.structuredconcurrency

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// All the problem arose during the previous lesson can be solved
// with `coroutineScope` function note: small `c` instead of capital C in CoroutineScope

// `coroutineScope` is a suspending function allow us to create child scope within a
// given CoroutineScope (Parent scope in our case MainActivity's Main UI Thread).
// this `coroutineScope` guarantees the completion of task when the suspending function returns

// UserDataManager class demonstrate `Unstructured Concurrency`
// UserDataManager2 class demonstrate `Structured Concurrency`

// [[ Structure Concurrency ]]
// Structured concurrency guarantees to complete all the works started by coroutines within the
//  child scope before the return of the suspending function
// Structured concurrency helps us to keep track of tasks we started and to cancel them when needed

class MainActivity : AppCompatActivity() {

  private var count = 0

  private lateinit var btnDownloadUserData : Button
  private lateinit var btnCount : Button
  private lateinit var tvCount : TextView
  private lateinit var tvUserMessage: TextView


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    btnDownloadUserData = findViewById(R.id.btnDownloadUserData)
    btnCount = findViewById(R.id.btnCount)
    tvCount = findViewById(R.id.tvCount)
    tvUserMessage = findViewById(R.id.tvUserMessage)

    btnCount.setOnClickListener {
      tvCount.text = count++.toString()
    }
    btnDownloadUserData.setOnClickListener {

      // [[ Unstructured Concurrency ]] - demo
//      CoroutineScope(Dispatchers.Main).launch {

        // we got 0 as result whereas we have 50 as a value in UserDataManager() class we created
        // Reason is in UserDataManager() coroutine scope create new coroutine through CoroutineScope(IO)
        //  which behaves separately from this parent coroutine in the MainActivity
        // So the function getTotalUserCount() reaches to the end (from count = 0 ...... return count)
        // without the completion of CoroutineScope (denoted by ..... in above line) (
        // Therefore unable to change count value from 0 to 50 inside CoroutineScope in UserDataManager class
//        tvUserMessage.text = UserDataManager().getTotalUserCount().toString()
//      }

      // [[ Structured Concurrency ]] - demo
      CoroutineScope(Dispatchers.Main).launch {
        tvUserMessage.text = UserDataManager2().getTotalUserCount().toString()
      }
    }
  }

  private suspend fun downloadUserData() {
    for (i in 1..200000) {
      Log.i("MyTag", "Downloading user $i in ${Thread.currentThread().name}")

      delay(100)
    }
  }
}