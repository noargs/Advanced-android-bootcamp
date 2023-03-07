package github.noargs.livedataexercise

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

// [LiveData Vs MutableLiveData]
// - Data in a LiveData object are only readable, not editable
// - MutableLiveData is a subclass of LiveData
// - A MutableLiveData object allows us to change/update its data

class MainActivityViewModel: ViewModel() {

  // private var count = 0
  var count = MutableLiveData<Int>()

  init {
    count.value = 0
  }

//  fun getCurrentCount(): Int {
//    return count
//  }

  // we don't need to name function as `getUpdatedCount()` and return the `:Int`
  // or anything in this matter as in `LiveData` we can directly access the variable
  // from our mainActivity rather returning a value from function
  fun updateCount(){
    count.value = (count.value)?.plus(1)
  }


}