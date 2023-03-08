package github.noargs.databindinglivedataviewmodeltwoway

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel: ViewModel() {
//  private var total = 0
  private var total = MutableLiveData<Int>()
  val totalData: LiveData<Int>
    get() = total

  val inputText = MutableLiveData<String>()

  init {
    total.value = 0
  }

//  fun getTotal(): Int {
//    return total
//  }

//  fun setTotal(input: Int) {
//    total.value = (total.value)?.plus(input)
//  }

  fun setTotal() {
    val intInput: Int = inputText.value!!.toInt()
    total.value = (total.value)?.plus(intInput)
  }

}