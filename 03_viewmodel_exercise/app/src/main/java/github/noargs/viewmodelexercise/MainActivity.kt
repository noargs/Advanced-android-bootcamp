package github.noargs.viewmodelexercise

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import github.noargs.viewmodelexercise.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMainBinding
  private lateinit var viewModel: MainActivityViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
//    setContentView(R.layout.activity_main)

    binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

    binding.resultTextView.text = viewModel.getTotal().toString()

    binding.insertButton.setOnClickListener {
      if (binding.inputEditText.text.toString() == "") {
        Toast.makeText(this, "Please enter a number", Toast.LENGTH_LONG).show()
      } else {
        viewModel.setTotal(binding.inputEditText.text.toString().toInt())
        binding.resultTextView.text = viewModel.getTotal().toString()
        binding.inputEditText.text.clear()
      }
      hideKeyboard()
    }
  }

  private fun hideKeyboard() {
    if (currentFocus != null) {
      val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
      imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)

    }
  }
}