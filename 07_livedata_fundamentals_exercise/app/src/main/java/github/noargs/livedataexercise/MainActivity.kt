package github.noargs.livedataexercise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import github.noargs.livedataexercise.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMainBinding
  private lateinit var viewModel: MainActivityViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    //setContentView(R.layout.activity_main)

    binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

    binding.textViewCounter.text = viewModel.getCurrentCount().toString()

    binding.buttonClick.setOnClickListener {
      binding.textViewCounter.text = viewModel.getUpdatedCount().toString()
    }
  }
}