package github.noargs.navigationexercise

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import github.noargs.navigationexercise.databinding.FragmentTermsBinding


class TermsFragment : Fragment() {

  private lateinit var binding: FragmentTermsBinding

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    // Inflate the layout for this fragment
//    return inflater.inflate(R.layout.fragment_terms, container, false)

    binding = DataBindingUtil.inflate(inflater, R.layout.fragment_terms, container, false)

    return binding.root
  }

}