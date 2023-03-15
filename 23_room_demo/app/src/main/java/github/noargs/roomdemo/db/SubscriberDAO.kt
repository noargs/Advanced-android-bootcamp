package github.noargs.roomdemo.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao // for room to recognise it as Data Access Object
interface SubscriberDAO {

  // Room library recognise this function with @Insert however you are free to name your function
  // Room will generate code to do the task by looking at parameters type, annotation and return type if any
  // `suspend` function is used as Room doesn't support database access on MAIN Thread rather on background thread
  // `suspend`ing function is a function which can be stopped and resumed at later time i.e. Kotlin coroutines
  // you can omit `suspend`ing function and use normal function if you are using another way of background processing in the calling class
  // also we can write different versions of this functions, only requirement is to annotate with @Insert
  @Insert //(onConflict=OnConflictStrategy.REPLACE ) // will try to insert the entity, if row has same Id value it will delete and replace in this strategy
  suspend fun insertSubscriber(subscriber: Subscriber): Long // use Long, Array or List<Long> values in the occasion of returning of inserted ID

  @Update
  suspend fun updateSubscriber(subscriber: Subscriber) // can also use `Int` return type

  @Delete
  suspend fun deleteSubscriber(subscriber: Subscriber)

  // this query will run when the function is called. query will be verified at compile time for errors
  // there will be now query related runtime errors
  @Query("DELETE FROM subscriber_data_table")
  suspend fun deleteAll()

  // function to get all the data from `subscriber_data_table`
  // Room facilitates us to get the data from database as liveData of list of entities,
  // these queries are called asynchronous queries
  // because these queries has livaData as return value, Room always run them on a background thread by itself
  // since its function returns LiveData, therefore Room library do its work from a background thread that why we don't need suspend function
  @Query("SELECT * FROM subscriber_data_table")
  fun getAllSubscribers(): LiveData<List<Subscriber>>

}