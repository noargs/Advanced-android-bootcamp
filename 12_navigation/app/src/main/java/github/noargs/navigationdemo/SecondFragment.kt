package github.noargs.navigationdemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import github.noargs.navigationdemo.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {

  private lateinit var binding: FragmentSecondBinding
  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    // Inflate the layout for this fragment
//    return inflater.inflate(R.layout.fragment_second, container, false)
    binding = DataBindingUtil.inflate(inflater, R.layout.fragment_second, container, false)

    var input = requireArguments().getString("user_input")
    binding.textView.text = input.toString()

    return binding.root
  }

}