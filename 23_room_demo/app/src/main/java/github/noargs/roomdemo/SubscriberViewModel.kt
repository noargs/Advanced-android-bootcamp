package github.noargs.roomdemo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import github.noargs.roomdemo.db.Subscriber
import github.noargs.roomdemo.db.SubscriberRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SubscriberViewModel(private val repo: SubscriberRepo ): ViewModel() {

  val subscribers = repo.subscribers

  val inputName = MutableLiveData<String>()
  val inputEmail = MutableLiveData<String>()

  val saveOrUpdateButtonText = MutableLiveData<String>()
  val clearOrUpdateButtonText = MutableLiveData<String>()

  init {
    saveOrUpdateButtonText.value = "Save"
    clearOrUpdateButtonText.value = "Clear All"
  }

  fun saveOrUpdate() {
    val name = inputName.value!!
    val email = inputEmail.value!!

    insert(Subscriber(0, name, email)) // Room will replace 0 to auto increment Id

    inputName.value = ""
    inputEmail.value = ""
  }

  fun clearAllOrDelete() {
    clearAll()
  }

  fun insert(subscriber: Subscriber) = viewModelScope.launch(Dispatchers.IO) { repo.insert(subscriber) }

  fun update(subscriber: Subscriber) = viewModelScope.launch(Dispatchers.IO) { repo.update(subscriber) }

  fun delete(subscriber: Subscriber) = viewModelScope.launch(Dispatchers.IO) { repo.delete(subscriber) }

  fun clearAll() = viewModelScope.launch(Dispatchers.IO) { repo.deleteAll() }


}