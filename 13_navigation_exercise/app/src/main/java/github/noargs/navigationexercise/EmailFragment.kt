package github.noargs.navigationexercise

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import github.noargs.navigationexercise.databinding.FragmentEmailBinding

class EmailFragment : Fragment() {

  private lateinit var binding: FragmentEmailBinding

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    // Inflate the layout for this fragment
//    return inflater.inflate(R.layout.fragment_email, container, false)

    binding = DataBindingUtil.inflate(inflater, R.layout.fragment_email, container, false)

    val name = requireArguments().getString("name_input_text")

    binding.submitButton.setOnClickListener {
      if (!TextUtils.isEmpty(binding.emailEditText.text.toString())) {
        val bundle = bundleOf(
          "email_input_text" to binding.emailEditText.text.toString(),
          "name_input" to name
        )

        it.findNavController().navigate(R.id.action_emailFragment_to_welcomeFragment, bundle)

      } else {
        Toast.makeText(
          activity,
          getString(R.string.please_enter_your_email),
          Toast.LENGTH_LONG
        ).show()
      }
      hideKeyboard()
    }

    return binding.root
  }

  private fun hideKeyboard() {
    if (activity?.currentFocus != null) {
      val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
      imm.hideSoftInputFromWindow(activity?.currentFocus?.windowToken, 0)
    }
  }

}