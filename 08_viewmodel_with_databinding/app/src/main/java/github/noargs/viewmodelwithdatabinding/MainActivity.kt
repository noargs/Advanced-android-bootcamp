package github.noargs.viewmodelwithdatabinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import github.noargs.viewmodelwithdatabinding.databinding.ActivityMainBinding

// Integrate DataBinding directly in View model object i.e. in Activity xml file
class MainActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMainBinding
  private lateinit var viewModel: MainActivityViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    //setContentView(R.layout.activity_main)

    binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
    binding.viewModel = viewModel

    viewModel.count.observe(this, Observer {
      binding.textViewCounter.text = it.toString()
    })

// - [[[ Function setOnClickListener is moved to activity_main.xml]]]
//    binding.buttonClick.setOnClickListener {
//      viewModel.updateCount()
//    }
  }
}