package github.noargs.roomdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import github.noargs.roomdemo.databinding.ActivityMainBinding
import github.noargs.roomdemo.db.SubscriberDatabase
import github.noargs.roomdemo.db.SubscriberRepo

class MainActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMainBinding
  private lateinit var subscriberViewModel: SubscriberViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    //setContentView(R.layout.activity_main)

    binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

    val dao = SubscriberDatabase.getInstance(application).subscriberDAO
    val repo = SubscriberRepo(dao)
    val factory = SubscriberViewModelFactory(repo)
    subscriberViewModel = ViewModelProvider(this, factory)[SubscriberViewModel::class.java]
    binding.myViewModel = subscriberViewModel
    binding.lifecycleOwner = this // as we are using liveData with dataBinding hence we need to provide lifecycleOwner

    initRecyclerView()

  }

  private fun initRecyclerView() {
    binding.subscriberRecyclerView.layoutManager = LinearLayoutManager(this)
    displaySubscribersList()
  }

  private fun displaySubscribersList() {

    // SubscriberViewModel (->) SubscribersRepo (->) SubscriberDAO.getAllSubscribers(): LiveData<List<Subscriber>>
    subscriberViewModel.subscribers.observe(this, Observer {
      Log.i("MyTag", it.toString())
      binding.subscriberRecyclerView.adapter = MyRecyclerViewAdapter(it)
    })
  }



}  // end of