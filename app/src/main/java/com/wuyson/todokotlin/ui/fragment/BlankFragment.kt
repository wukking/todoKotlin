package com.wuyson.todokotlin.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wuyson.todokotlin.R
import kotlinx.android.synthetic.main.fragment_blank.*

private const val ARG_PARAM1 = "param1"
private const val TAG = "Blank_Fragment"

class BlankFragment : Fragment() {
    private var param1: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }

        Log.e(TAG, "onCreate1: "+param1 )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_blank, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        tv_text.text = param1
        Log.e(TAG, "onActivityCreated: "+param1 )

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
                BlankFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                    }
                }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "onDestroy: "+param1 )
    }
}