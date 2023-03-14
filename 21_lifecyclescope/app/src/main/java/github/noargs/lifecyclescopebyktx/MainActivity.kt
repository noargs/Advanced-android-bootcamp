package github.noargs.lifecyclescopebyktx

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import github.noargs.lifecyclescopebyktx.ui.main.MainFragment

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

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    if (savedInstanceState == null) {
      supportFragmentManager.beginTransaction()
        .replace(R.id.container, MainFragment.newInstance())
        .commitNow()
    }
  }
}