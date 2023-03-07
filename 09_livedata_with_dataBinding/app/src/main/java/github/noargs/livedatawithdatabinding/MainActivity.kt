package github.noargs.livedatawithdatabinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import github.noargs.livedatawithdatabinding.databinding.ActivityMainBinding

// How to Integrate LiveData with DataBinding
class MainActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMainBinding
  private lateinit var viewModel: MainActivityViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    //setContentView(R.layout.activity_main)

    binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

    // LiveData always associate with itself lifecycle of an activity, fragment or service
    // therefore we have to provide the actual lifecycle owner to the view model object
    // i.e. as LiveData already incorporated in view model object ( in activity_main.xml)
    binding.lifecycleOwner = this

    binding.viewModel = viewModel

// -[[[ LiveData `Observer` is moved to activity_main.xml with the help of DataBinding]]]
//    viewModel.count.observe(this, Observer {
//      binding.textViewCounter.text = it.toString()
//    })

// - [[[ Function setOnClickListener (contains `ViewModel`) is moved to activity_main.xml with the help of DataBinding]]]
//    binding.buttonClick.setOnClickListener {
//      viewModel.updateCount()
//    }
  }
}