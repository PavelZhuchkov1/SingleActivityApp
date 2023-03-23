package com.example.singleactivityapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.singleactivityapp.databinding.FragmentNavBinding

class NavFragment : Fragment() {

    private var _binding: FragmentNavBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNavBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        childFragmentManager.commit {
            add(R.id.fragment_container, TextFragment.newInstance(getString(R.string.showcase)))
        }
        binding.bottomNavView.selectedItemId = R.id.showcase

        binding.bottomNavView.setOnItemSelectedListener { item ->
            val selectedFragment = when (item.itemId) {
                R.id.my_books -> TextFragment.newInstance(getString(R.string.my_books))
                R.id.showcase -> TextFragment.newInstance(getString(R.string.showcase))
                R.id.profile -> TextFragment.newInstance(getString(R.string.profile))
                else -> return@setOnItemSelectedListener false
            }

            childFragmentManager.commit {
                replace(R.id.fragment_container, selectedFragment)
            }

            true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}