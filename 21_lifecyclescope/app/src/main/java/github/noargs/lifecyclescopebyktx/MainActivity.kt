package github.noargs.lifecyclescopebyktx

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.lifecycleScope
import github.noargs.lifecyclescopebyktx.ui.main.MainFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// Another scope introduced by google is LifeCycleScope
// LifeCycleScope is defined by each lifeCycle Object
// Any coroutine launch in this scope is cancelled when lifeCycle of this scope is
//   destroyed
// You can access the coroutine scope of the lifeCycle either by lifecycle.coroutineScope
//   OR lifeCycleOwner.lifecycleScope

// Sometimes we need to create coroutines in objects with a lifecycle, such as activities and
//   fragments
// This new scope has specially created for those scenarios
// All the coroutines in this new scope will be canceled when the Lifecycle is destroyed
// So if it is an Activity, all the coroutines in that scope will be canceled when the `onDestroy()`
// method of the Activity invoked, same will happen if it is Fragment

class MainActivity : AppCompatActivity() {

  private lateinit var progressBar: ProgressBar

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    if (savedInstanceState == null) {
      supportFragmentManager.beginTransaction()
        .replace(R.id.container, MainFragment.newInstance())
        .commitNow()
    }

    progressBar = findViewById(R.id.progressBar)

//    lifecycleScope.launch {// if you want it to run in separate thread the use `lifecycleScope.launch(Dispatchers.IO)`
//      delay(5000)
//      progressBar.visibility = View.VISIBLE
//      delay(3000)
//      progressBar.visibility = View.GONE
//      delay(4000)
//      progressBar.visibility = View.VISIBLE
//      delay(3000)
//      progressBar.visibility = View.GONE
//    }


    lifecycleScope.launch(Dispatchers.IO) {
      Log.i("MyTag", " thread is ${Thread.currentThread().name}")
    }

  // Sometimes we might need to suspend the execution of a code block, considering
  //    the current state of a lifecycle
  // For that we have three additional builders - NOW Deprecated
    lifecycleScope.launchWhenCreated {} // when activity or fragment created
    lifecycleScope.launchWhenStarted {} // when activity or fragment started
    lifecycleScope.launchWhenResumed { } // when activity or fragment resumed

  }

} // end of