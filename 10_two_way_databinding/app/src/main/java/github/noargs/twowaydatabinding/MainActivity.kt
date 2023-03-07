package github.noargs.twowaydatabinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import github.noargs.twowaydatabinding.databinding.ActivityMainBinding

// Two Way DataBinding
// - Changes in Object value will change the value of UI
// - Changes in the value of UI will change the Object value
class MainActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMainBinding
  private lateinit var viewModel: MainActivityViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

    viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
    binding.viewModel = viewModel
    binding.lifecycleOwner = this
  }
}