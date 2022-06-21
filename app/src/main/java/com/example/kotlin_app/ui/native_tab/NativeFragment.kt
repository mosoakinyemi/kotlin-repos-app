package com.example.kotlin_app.ui.native_tab

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.kotlin_app.R
import com.example.kotlin_app.data.models.SearchResponse
import com.example.kotlin_app.data.services.RequestManager
import com.example.kotlin_app.data.services.SearchResponseListener
import kotlinx.android.synthetic.main.fragment_native_tab.*

class NativeFragment : Fragment() {

    var dialog: ProgressDialog? = null
    private lateinit var nativeViewModel: NativeViewModel
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        nativeViewModel =
                ViewModelProvider(this).get(NativeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_native_tab, container, false)

        RequestManager(requireContext()).getSearchResults(listener)
        dialog = ProgressDialog(requireContext())
        dialog?.setTitle("Loading...")
        dialog?.show()
        return root
    }

    private val listener: SearchResponseListener = object: SearchResponseListener {
        override fun didFetch(response: SearchResponse, message: String) {
            dialog?.dismiss()
            recycler_home.setHasFixedSize(true)
            recycler_home.layoutManager = StaggeredGridLayoutManager(1,LinearLayoutManager.VERTICAL)
            val adapter = ReposListAdapter(requireContext(),response)
            recycler_home.adapter = adapter
        }

        override fun didError(message: String) {
            dialog?.dismiss()
            Toast.makeText(requireContext(),message,Toast.LENGTH_LONG).show()
        }

    }

}