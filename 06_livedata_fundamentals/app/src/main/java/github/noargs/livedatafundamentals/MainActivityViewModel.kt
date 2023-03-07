package github.noargs.livedatafundamentals

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


// [LiveData Vs MutableLiveData]
// - Data in a LiveData object are only readable, not editable
// - MutableLiveData is a subclass of LiveData
// - A MutableLiveData object allows us to change/update its data

class MainActivityViewModel(startingTotal: Int): ViewModel() {

//  private var total = 0
  private var total = MutableLiveData<Int>()
  val totalData: LiveData<Int> get() = total

  init {
    total.value = startingTotal
  }

//  fun getTotal(): Int {
//    return total
//  }

  fun setTotal(input: Int) {
    total.value = (total.value)?.plus(input)
  }
}