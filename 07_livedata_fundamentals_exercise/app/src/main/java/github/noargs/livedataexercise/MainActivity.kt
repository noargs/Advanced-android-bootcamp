package github.noargs.livedataexercise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import github.noargs.livedataexercise.databinding.ActivityMainBinding

// - LiveData: A lifecycle aware observable data holder class
// in android three app components have lifecycle attached to them
// i.e. activity, fragment, and services
// Therefore activity, fragment and services can be used as observers
// of liveData object

// - LiveData only updates observers in an active lifecycle state
// - Automatically update the UI when app data changes
// - No need to write codes to handle lifecycle manually
// - Can also be used to share Services between components

class MainActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMainBinding
  private lateinit var viewModel: MainActivityViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    //setContentView(R.layout.activity_main)

    binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

    viewModel.count.observe(this, Observer {
      binding.textViewCounter.text = it.toString()
    })

    // - Replaced by above code `observer` from liveData
    // binding.textViewCounter.text = viewModel.getCurrentCount().toString()

    binding.buttonClick.setOnClickListener {

      // binding.textViewCounter.text = viewModel.getUpdatedCount().toString()

      // - above code commented as we only tell the viewModel to increment
      // - and result i.e. updated `count` automatically gets changed in `textViewCounter`
      // - as it's been observed by LiveData
      viewModel.updateCount()
    }
  }
}