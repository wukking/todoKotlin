package com.wuyson.todokotlin.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.wuyson.todokotlin.databinding.ActivityViewPager2Binding
import com.wuyson.todokotlin.ui.fragment.BlankFragment

/**
 * @author Wuyson
 * @date 2020/12/1
 */
private const val TAG = "MainActivity"

class ViewPager2Activity : AppCompatActivity() {
    lateinit var binding: ActivityViewPager2Binding
    lateinit var mAdapter: DemoVPAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewPager2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        mAdapter = DemoVPAdapter(this)
        binding.vpContent.adapter = mAdapter

        TabLayoutMediator(binding.tabSort, binding.vpContent) { tab, position ->
            run {
                tab.text = "Tab${position}"
            }
        }.attach()
    }


    class DemoVPAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
        override fun getItemCount(): Int = 100

        override fun createFragment(position: Int): Fragment = BlankFragment.newInstance("Tab${position}")

    }
}