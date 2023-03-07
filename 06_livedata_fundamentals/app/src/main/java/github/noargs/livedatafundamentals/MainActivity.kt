package github.noargs.livedatafundamentals

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import github.noargs.livedatafundamentals.databinding.ActivityMainBinding

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
  private lateinit var viewModelFactory: MainActivityViewModelFactory

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
//    setContentView(R.layout.activity_main)

    binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    viewModelFactory = MainActivityViewModelFactory(125)
    viewModel = ViewModelProvider(this, viewModelFactory)[MainActivityViewModel::class.java]

    viewModel.totalData.observe(this, Observer {
      binding.resultTextView.text = it.toString()
    })
    // replaced by above code `observer from liveData
    // binding.resultTextView.text = viewModel.getTotal().toString()

    binding.insertButton.setOnClickListener {
      binding.apply {
        if (inputEditText.text.toString() == "") {
          Toast.makeText(this@MainActivity,
            getString(R.string.edit_text_empty_field_error),
            Toast.LENGTH_LONG
          ).show()
        } else {
          viewModel.setTotal(inputEditText.text.toString().toInt())
          // don't need to set `resultTextView` again on click as LiveData automatically update
          // upon the change in data
          // resultTextView.text = viewModel.getTotal().toString()
          inputEditText.text.clear()
        }

        hideKeyboard()
      }
    }

  }

  private fun hideKeyboard() {
    if (currentFocus != null) {
      val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
      imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }
  }
}