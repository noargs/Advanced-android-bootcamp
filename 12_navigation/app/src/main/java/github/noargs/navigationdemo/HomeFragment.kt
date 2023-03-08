package github.noargs.navigationdemo

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import github.noargs.navigationdemo.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

  private lateinit var binding: FragmentHomeBinding
  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    // Inflate the layout for this fragment
    //return inflater.inflate(R.layout.fragment_home, container, false)
    binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)




    binding.button.setOnClickListener {

      if (!TextUtils.isEmpty(binding.editText.text.toString())) {
        // [NOT RECOMMENDED] to pass the data between destinations But only through ViewModel
        val bundle: Bundle = bundleOf("user_input" to binding.editText.text.toString())
        binding.editText.text.clear()
        it.findNavController().navigate(R.id.action_homeFragment_to_secondFragment, bundle)
      } else {
        Toast.makeText(
          activity,
          getString(R.string.empty_edit_text_field_warning),
          Toast.LENGTH_LONG
        ).show()
      }
    }

    return binding.root
  }
}