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
import com.example.kotlin_app.data.models.RepoItem
import com.example.kotlin_app.databinding.FragmentNativeTabBinding
import com.example.kotlin_app.utils.Resource
import com.example.kotlin_app.utils.onQueryTextChanged
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NativeFragment : Fragment(R.layout.fragment_native_tab),OnItemClickListener {

    var dialog: ProgressDialog? = null
    private val viewModel: NativeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentNativeTabBinding.bind(view)

        val reposAdapter = RepoItemAdapter(this)

        dialog = ProgressDialog(requireContext())
        binding.apply {
            recyclerView.apply {
                adapter = reposAdapter
                layoutManager = LinearLayoutManager(requireContext())
            }

        viewModel.repos.observe(viewLifecycleOwner){
             result ->
            reposAdapter.submitList(result.data)

            if (result is Resource.Loading && result.data.isNullOrEmpty()){
                dialog?.setTitle("Loading...")
                dialog?.show()
            }
            if (result is Resource.Error && result.data.isNullOrEmpty()){
                dialog?.setTitle(result.error?.localizedMessage)
                dialog?.show()
            }
            dialog?.dismiss()
        }
        }
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.top_menu,menu)
        var searchItem = menu.findItem(R.id.action_search)
        var searchView = searchItem.actionView as SearchView

        searchView.onQueryTextChanged {
            viewModel.searchQuery.value = it
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
       return when(item.itemId){
            R.id.action_sort_by_asc -> {
                viewModel.sortOrder.value = SortOrder.BY_ASC
                true
            }
           R.id.action_sort_by_desc -> {
               viewModel.sortOrder.value = SortOrder.BY_DESC
               true
           }
           else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onItemClick(repo: RepoItem) {
        viewModel.onRepoItemSelected(repo)
    }
}