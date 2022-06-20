package com.example.kotlin_app.ui.rn_tab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.kotlin_app.R

class RNFragment : Fragment() {

    private lateinit var rnViewModel: RNViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        rnViewModel =
                ViewModelProvider(this).get(RNViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_native_tab, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        rnViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }


}