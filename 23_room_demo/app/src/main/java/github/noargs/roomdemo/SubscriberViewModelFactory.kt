package github.noargs.roomdemo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import github.noargs.roomdemo.db.SubscriberRepo

class SubscriberViewModelFactory(private val repo: SubscriberRepo): ViewModelProvider.Factory {

  override fun <T : ViewModel> create(modelClass: Class<T>): T {

    if (modelClass.isAssignableFrom(SubscriberViewModel::class.java))
      return SubscriberViewModel(repo) as T

    throw java.lang.IllegalArgumentException("Uknown viewModel class")
  }



}