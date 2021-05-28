package com.wuyson.todokotlin.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.wuyson.common.base.BaseFragment
import com.wuyson.todokotlin.databinding.FragmentHomeBinding
import com.wuyson.todokotlin.entity.local.PageEntity
import com.wuyson.todokotlin.ui.adapter.ItemDecoration
import com.wuyson.todokotlin.ui.adapter.MainRecyclerAdapter
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * ViewBinding
 */
class HomeFragment : BaseFragment() {
    private var _binding:FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var mDatas:ArrayList<PageEntity> = arrayListOf();

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initView()
    }

    private fun initView(){
        initRecyclerView()
    }

    private fun initRecyclerView(){
        mDatas.add(PageEntity(1,"Markdown"))
        mDatas.add(PageEntity(2,"ViewPager2"))
        mDatas.add(PageEntity(3,"打开扫码1"))
        mDatas.add(PageEntity(4,"打开扫码2"))
        mDatas.add(PageEntity(5,"气泡"))
        mDatas.add(PageEntity(6,"发送一条气泡"))
        mDatas.add(PageEntity(7,"发送一条延迟3S的Handler消息"))

        rv_content.apply {
            layoutManager = StaggeredGridLayoutManager(3, RecyclerView.VERTICAL)
            adapter = MainRecyclerAdapter(mDatas)
            addItemDecoration(ItemDecoration(context))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}