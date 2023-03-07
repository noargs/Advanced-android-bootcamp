package github.noargs.viewmodelwithdatabinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import github.noargs.viewmodelwithdatabinding.databinding.ActivityMainBinding

// Integrate DataBinding directly in View model object i.e. Activity xml file
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