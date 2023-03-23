package com.example.singleactivityapp.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.commit
import com.example.singleactivityapp.R
import com.example.singleactivityapp.databinding.FragmentTextBinding

class TextFragment : ViewBindingFragment() {

    private val binding by viewBinding(FragmentTextBinding::inflate)

    companion object {
        private const val ARG_TEXT_KEY = "text"

        fun newInstance(text: String): TextFragment {
            val args = bundleOf(ARG_TEXT_KEY to text)
            val fragment = TextFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.myText.text = arguments?.getString(ARG_TEXT_KEY)
        binding.backStack.text = parentFragmentManager.backStackEntryCount.toString()
        binding.myText.setOnClickListener {
            parentFragmentManager.commit {
                setReorderingAllowed(true)
                add(R.id.fragment_container, TextFragment.newInstance("book screen"))
                addToBackStack(null)
            }
        }
    }
}