package github.noargs.roomdemo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import github.noargs.roomdemo.db.Subscriber
import github.noargs.roomdemo.db.SubscriberRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SubscriberViewModel(private val repo: SubscriberRepo ): ViewModel() {

  val subscribers = repo.subscribers

  private var isUpdateOrDelete = false
  private lateinit var subscriberToUpdateOrDelete: Subscriber

  val inputName = MutableLiveData<String>()
  val inputEmail = MutableLiveData<String>()

  val saveOrUpdateButtonText = MutableLiveData<String>()
  val clearOrUpdateButtonText = MutableLiveData<String>()

  init {
    saveOrUpdateButtonText.value = "Save"
    clearOrUpdateButtonText.value = "Clear All"
  }

  fun saveOrUpdate() {
    if (isUpdateOrDelete) {
      subscriberToUpdateOrDelete.name = inputName.value!!
      update(subscriberToUpdateOrDelete)
    } else {
      val name = inputName.value!!
      val email = inputEmail.value!!

      insert(Subscriber(0, name, email)) // Room will replace 0 to auto increment Id
    }

    inputName.value = ""
    inputEmail.value = ""
  }

  fun clearAllOrDelete() {
    if ( isUpdateOrDelete ) {
      delete(subscriberToUpdateOrDelete)
    } else {
      clearAll()
    }
  }

  fun insert(subscriber: Subscriber) = viewModelScope.launch(Dispatchers.IO) { repo.insert(subscriber) }

  fun update(subscriber: Subscriber) = viewModelScope.launch(Dispatchers.IO) {
    repo.update(subscriber)

    withContext(Dispatchers.Main) {

      inputName.value = ""
      inputEmail.value = ""

      isUpdateOrDelete = false
      saveOrUpdateButtonText.value = "Save"
      clearOrUpdateButtonText.value = "Clear All"
    }
  }

  fun delete(subscriber: Subscriber) = viewModelScope.launch(Dispatchers.IO) {
    repo.delete(subscriber)
    withContext(Dispatchers.Main) {

      inputName.value = ""
      inputEmail.value = ""

      isUpdateOrDelete = false
      saveOrUpdateButtonText.value = "Save"
      clearOrUpdateButtonText.value = "Clear All"
    }
  }

  fun clearAll() = viewModelScope.launch(Dispatchers.IO) { repo.deleteAll() }

  fun initUpdateAndDelete(subscriber: Subscriber) {
    inputName.value = subscriber.name
    inputEmail.value = subscriber.email

    isUpdateOrDelete = true
    subscriberToUpdateOrDelete = subscriber
    saveOrUpdateButtonText.value = "Update"
    clearOrUpdateButtonText.value = "Delete"
  }


}