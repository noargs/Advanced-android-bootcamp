package github.noargs.databindinglivedataviewmodeltwoway

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import github.noargs.databindinglivedataviewmodeltwoway.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMainBinding
  private lateinit var viewModel: MainActivityViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

    binding.myViewModel = viewModel

    binding.lifecycleOwner = this

    // binding.textViewResult.text = viewModel.getTotal().toString()
//    viewModel.totalData.observe(this, Observer {
//      binding.textViewResult.text = it.toString()
//    })

//    binding.buttonAdd.setOnClickListener {
//      viewModel.setTotal(binding.editTextInput.text.toString().toInt())
//        //binding.textViewResult.text = viewModel.getTotal().toString()
//
//      hideKeyboard()
//      clearEditTextInputField()
//    }
  }

  private fun hideKeyboard() {
    if (currentFocus != null) {
      val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
      imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }
  }

  private fun clearEditTextInputField() {
    binding.editTextInput.text.clear()
  }
}