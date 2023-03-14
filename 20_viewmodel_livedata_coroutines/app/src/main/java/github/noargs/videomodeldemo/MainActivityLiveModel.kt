package github.noargs.videomodeldemo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

// In android everytime the ViewModel is cleared from the memory, just before the clearing
//   ViewModel invokes its `onCleared()` method, which is always there from beginning
// If we want to do something before clearing we can manually override the `onClear()` method

// Some of the coroutines we launch in a viewmodel has a potential to run even after the viewmodel
//   is cleared from the memory. it might run until our app is terminated. in some scenarios
//   that would the intention.
// If that's not what we intended app will be end up leaking memory.
// To avoid that we need to cancel the coroutine within this `onCleared()` function.
// In order to cancel coroutines started in this scope. we need to pass a job instance
//   for the context of the coroutine scope
// By cancelling the job in the coroutine scope we can cancel all the coroutines within this scope

// But think, If we have 20 viewmodel classes in our app, doing this cancellation manually on 20
//   different placed inside viewmodel might be unnecessary and waste of time, to avoid this boilerplate
//   code we can use `viewModelScope`

// This new viewModelScope is bounded to ViewModel's lifecycle. It has created to automatically handle
//   cancellation
// We can easily use this scope from an extension function available on the viewmodel-ktx library.
//   implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version"

class MainActivityLiveModel: ViewModel() {

//  private val job = Job()
//  private val scope = CoroutineScope(Dispatchers.IO + job)

  fun getUserData() {

    // Our scope `scope.launch{ }` will be replaced by `viewModelScope.launch{ }` from ktx library
    // and we don't need `onCleared()` method from viewmodel, `CoroutineScope()` as well as `Job()`
//    scope.launch {
//
//    }
    viewModelScope.launch {

    }


  }

//  override fun onCleared() {
//    super.onCleared()
//    job.cancel()
//  }
}