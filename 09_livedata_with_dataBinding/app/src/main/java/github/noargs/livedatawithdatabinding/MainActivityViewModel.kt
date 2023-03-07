package github.noargs.livedatawithdatabinding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel: ViewModel() {

  // private var count = 0
  private var count = MutableLiveData<Int>()
  val countData: LiveData<Int>
    get() = count

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