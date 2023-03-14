package github.noargs.structuredconcurrency

import kotlinx.coroutines.*

// This class demonstrate `Structured Concurrency`
class UserDataManager2 {

  var count = 0
  lateinit var deferred: Deferred<Int>

  suspend fun getTotalUserCount(): Int {

    coroutineScope {
      launch(Dispatchers.IO) {// if we dont use Dispatcher here it will run in parent scope (i.e. run in Main Thread which is MainActivity's UI Thread)
        delay(1000)
        count = 50
      }

      deferred = async(Dispatchers.IO) {
        delay(3000)
        return@async 70
      }
    }

    return count + deferred.await()
  }

} // end of