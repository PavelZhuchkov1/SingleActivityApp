package com.example.singleactivityapp.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.commit
import com.example.singleactivityapp.R
import com.example.singleactivityapp.databinding.FragmentNavBinding

class NavFragment : ViewBindingFragment() {

    private val binding by viewBinding(FragmentNavBinding::inflate)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        parentFragmentManager.commit {
            setPrimaryNavigationFragment(this@NavFragment)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.title = NavFragment::class.simpleName

        childFragmentManager.commit {
            setReorderingAllowed(true)
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
                addToBackStack(null)
            }

            true
        }
    }
}