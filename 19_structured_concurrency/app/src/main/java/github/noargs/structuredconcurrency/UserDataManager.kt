package github.noargs.structuredconcurrency

import kotlinx.coroutines.*

// This class demonstrate `Unstructured Concurrency`
class UserDataManager {

  suspend fun getTotalUserCount(): Int {
    var count = 0
    CoroutineScope(Dispatchers.IO).launch {
      delay(1000) // to simulate wrong running task we delay for 1s
      count = 50
    }

    val deferred = CoroutineScope(Dispatchers.IO).async {
      delay(3000)
      return@async 70
    }

    // Code will jump straight to this line wait for 3s hence 70 value will be received instread of 50
    // This is unstructured concurrency it might work as expected with async{} and await
    // But in android if error occurs in a function then function will throw an exception
    //  and you can catch the exception in the caller function and handle the situation
    // However in this unstructured concurrency scenario whether we use launch{} or async{} builders
    //  there's no way to handle the exceptions properly.
    return count + deferred.await()

  }
}