package com.example.singleactivityapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.singleactivityapp.databinding.FragmentTextBinding

class TextFragment : Fragment() {

    private var _binding: FragmentTextBinding? = null
    private val binding get() = _binding!!

    companion object {
        private const val ARG_TEXT_KEY = "text"

        fun newInstance(text: String): TextFragment {
            val args = bundleOf(ARG_TEXT_KEY to text)
            val fragment = TextFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTextBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.myText.text = arguments?.getString(ARG_TEXT_KEY)
        binding.myText.setOnClickListener {
            requireActivity().supportFragmentManager.commit {
                add(R.id.fragment_container, TextFragment.newInstance("book screen"))
                addToBackStack(null)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}