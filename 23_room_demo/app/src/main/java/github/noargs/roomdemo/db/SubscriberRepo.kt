package github.noargs.roomdemo.db


//                                  ------ [[ MVVM architecture ]] --------

/*
 *
 *                                            Activity / Fragment
 *                                                     |
 *                                           ViewModel  (LiveData)
 *                                                    |
 *                                    ---------  Repository  ----------
 *                                    |                               |
 *                                  Model                     Remote Data Source
 *                                    (Room)                              (Retrofit)
 *                                   |                                |
 *                               SQLite                          webservice
 *
 *
 * - MVVM is the recommended best architecture for Android Development by Google
 * - MVVM stands for Model, View and ViewModel
 * - Model means all data management related components
 * - Model has `Local database` related components, `Remote data sources` components and a `Repository`
 * - This small project we will omit using `Remote data sources`
 * - This purpose of this class (Repository class) is to provide clean API for viewModels to easily get and send data
 * - As Google documentation describes Repositories to be mediator between different data sources i.e. local databases, web services and caches
 * - https://developer.android.com/jetpack/docs/guide
 *
 */

class SubscriberRepo(private val dao: SubscriberDAO) {

  val subscribers = dao.getAllSubscribers() // No need to call this from background thread, because Room library automatically treat this as return type of LiveData

  suspend fun insert(subscriber: Subscriber) {
    dao.insertSubscriber(subscriber)
  }

  suspend fun update(subscriber: Subscriber) {
    dao.updateSubscriber(subscriber)
  }

  suspend fun delete(subscriber: Subscriber) {
    dao.deleteSubscriber(subscriber)
  }

  suspend fun deleteAll() {
    dao.deleteAll()
  }

}