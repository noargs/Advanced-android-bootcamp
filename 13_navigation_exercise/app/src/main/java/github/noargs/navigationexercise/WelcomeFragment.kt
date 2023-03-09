package github.noargs.navigationexercise

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import github.noargs.navigationexercise.databinding.FragmentWelcomeBinding


class WelcomeFragment : Fragment() {

  private lateinit var binding: FragmentWelcomeBinding

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    // Inflate the layout for this fragment
//    return inflater.inflate(R.layout.fragment_welcome, container, false)

    binding = DataBindingUtil.inflate(inflater, R.layout.fragment_welcome, container, false)

    val name = requireArguments().getString("name_input")
    val email = requireArguments().getString("email_input_text")

    binding.nameTextView.text = name
    binding.emailTextView.text = email

    binding.viewTermsButton.setOnClickListener {
      it.findNavController().navigate(R.id.action_welcomeFragment_to_termsFragment)
    }


    return binding.root
  }

}