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


// There are situations where we need to run more than 1 coroutine concurrently
//  in a suspended functions and get some results returned from the function
// There are two ways to do this, we call them `structured concurrency` and
//  `unstructured concurrency`

// in this app we will demonstrate `unstructured concurrency` which is the wrong
//  way to do it

// [[ Unstructured concurrency ]] does not guarantee to complete all the tasks
//  of the suspending function, before it returns
// Actually the child's coroutine can be still running even after the completion of
//  of parent coroutine, as a result we have unpredictable results
//  especially if we use `launch { }` coroutine builder
// If we use `async` builder and `await` function call for the return value, you might
//  be able to get the expected result of the async coroutine

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


      CoroutineScope(Dispatchers.Main).launch {

        // we got 0 as result whereas we have 50 as a value in UserDataManager() class we created
        // Reason is in UserDataManager() coroutine scope create new coroutine through CoroutineScope(IO)
        //  which behaves separately from this parent coroutine in the MainActivity
        // So the function getTotalUserCount() reaches to the end (from count = 0 ...... return count)
        // without the completion of CoroutineScope (denoted by ..... in above line) (
        // Therefore unable to change count value from 0 to 50 inside CoroutineScope in UserDataManager class
        tvUserMessage.text = UserDataManager().getTotalUserCount().toString()
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