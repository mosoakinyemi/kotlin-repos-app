package com.example.kotlin_app.ui.native_tab

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin_app.R
import com.example.kotlin_app.databinding.FragmentNativeTabBinding
import com.example.kotlin_app.utils.Resource
import com.example.kotlin_app.utils.onQueryTextChanged
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class NativeFragment : Fragment(R.layout.fragment_native_tab) {

    var dialog: ProgressDialog? = null
    private lateinit var nativeViewModel: NativeViewModel
    private val viewModel: NativeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentNativeTabBinding.bind(view)

        val reposAdapter = RepoItemAdapter()
        dialog = ProgressDialog(requireContext())
        binding.apply {
            recyclerView.apply {
                adapter = reposAdapter
                layoutManager = LinearLayoutManager(requireContext())
            }

        viewModel.repos.observe(viewLifecycleOwner){
             result ->
            Log.d("FETCHED REPOS FRAG", result.data.toString())
            reposAdapter.submitList(result.data)

            if (result is Resource.Loading && result.data.isNullOrEmpty()){
                dialog?.setTitle("Loading...")
                dialog?.show()
            }
            if (result is Resource.Error && result.data.isNullOrEmpty()){
                Log.d("FETCH Display Error", result.error?.localizedMessage.toString())

                dialog?.setTitle(result.error?.localizedMessage)
                dialog?.show()
            }
            dialog?.dismiss()
        }
        }
    }
    
}