package github.noargs.asyncawait

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.delay


// Let's assume we have to get results from 4 different online data sources
// and combine them all to show the final results

// Following time each task will take
// Task 1 => 10s
// Task 2 => 15s
// Task 3 => 12s
// Task 4 => 13s
// Total: 10 + 15 + 12 + 13 = 50s

// 50s are too much waiting just to see the results.

// what if we can download all this data in parallel
// if we can do so, we will be able to show the results in just 15s

// Writing code to download this data in parallel and combining them
// at the end for results is called `Parallel Decomposition`

// with kotlin coroutine we can do Parallel Decomposition very easily
// as opposed to in past we have to write long, difficult to write and difficult
// to read code and difficult to maintain code

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
  }
}

// imitate getting the stock value from an API
private suspend fun getStock1(): Int {
  delay(10000) // 10 seconds
  Log.i("MyTag", " stock 1 returned")
  return 55000
}

private suspend fun getStock2(): Int {
  delay(80000) // 8 seconds
  Log.i("MyTag", " stock 2 returned")
  return 35000
}