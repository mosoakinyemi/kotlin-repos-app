package com.example.kotlin_app.ui.rn_tab

import android.app.Application
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.kotlin_app.BuildConfig
import com.example.kotlin_app.data.models.RepoItem
import com.example.kotlin_app.ui.native_tab.NativeViewModel
import com.facebook.react.*
import com.facebook.react.common.LifecycleState
import com.facebook.soloader.SoLoader
import com.example.kotlin_app.databinding.FragmentRnTabBinding


class RNFragment : Fragment() {
    private var mReactRootView: ReactRootView? = null
    private var mReactInstanceManager: ReactInstanceManager? = null
    private val sharedViewModel: NativeViewModel by activityViewModels()
    var initialProps = Bundle()
    private var topRepo: RepoItem? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application: Application = requireActivity().application
        SoLoader.init(requireContext(), false)

        val sharedViewModel = ViewModelProvider(requireActivity()).get(NativeViewModel::class.java)
        sharedViewModel.repos.observe(viewLifecycleOwner){
                result ->
            topRepo = result.data?.first()
            initialProps.putString("user_avatar",topRepo?.owner?.avatar_url);
            initialProps.putString("repo_title",topRepo?.name);
            initialProps.putString("stars",topRepo?.stargazers_count?.toString());
        }


        mReactRootView = ReactRootView(requireContext())
        val packages: List<ReactPackage> = PackageList(application).packages

        mReactInstanceManager = ReactInstanceManager.builder()
            .setApplication(application)
            .setCurrentActivity(requireActivity())
            .setBundleAssetName("index.android.bundle")
            .setJSMainModulePath("index")
            .addPackages(packages)
            .setUseDeveloperSupport(BuildConfig.DEBUG)
            .setInitialLifecycleState(LifecycleState.RESUMED)
            .build();
        mReactRootView!!.startReactApplication(mReactInstanceManager, "ReactNativeApp", initialProps);
        return mReactRootView
    }

    override fun onPause() {
        super.onPause()
        if (mReactInstanceManager != null) {
            mReactInstanceManager!!.onHostPause(requireActivity())
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        if (mReactInstanceManager != null) {
            mReactInstanceManager!!.onHostDestroy(requireActivity())
        }
        if (mReactRootView != null) {
            mReactRootView!!.unmountReactApplication()
        }
    }
    }