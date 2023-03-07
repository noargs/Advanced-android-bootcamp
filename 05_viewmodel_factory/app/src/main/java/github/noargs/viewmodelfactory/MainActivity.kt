package github.noargs.viewmodelfactory

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import github.noargs.viewmodelfactory.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMainBinding
  private lateinit var viewModel: MainActivityViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
//    setContentView(R.layout.activity_main)

    binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]

    binding.resultTextView.text = viewModel.getTotal().toString()

    binding.insertButton.setOnClickListener {
      binding.apply {
        if (inputEditText.text.toString() == "") {
          Toast.makeText(this@MainActivity,
            getString(R.string.edit_text_empty_field_error),
            Toast.LENGTH_LONG
          ).show()
        } else {
          viewModel.setTotal(inputEditText.text.toString().toInt())
          resultTextView.text = viewModel.getTotal().toString()
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