package com.example.kotlin_app.ui.rn_tab

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kotlin_app.BuildConfig
import com.facebook.react.*
import com.facebook.react.common.LifecycleState
import com.facebook.soloader.SoLoader


class RNFragment : Fragment() {
    private val mReactDelegate: ReactDelegate? = null
    private var mReactRootView: ReactRootView? = null
    private var mReactInstanceManager: ReactInstanceManager? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application: Application = requireActivity().application
        SoLoader.init(requireContext(), false)

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
        mReactRootView!!.startReactApplication(mReactInstanceManager, "ReactNativeApp", null);
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