package github.noargs.twowaydatabinding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel: ViewModel() {

  var userName = MutableLiveData<String>()

  init {
    userName.value = "Ibn"
  }

}