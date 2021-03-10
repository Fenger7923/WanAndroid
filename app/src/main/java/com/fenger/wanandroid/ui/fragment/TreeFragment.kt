package com.fenger.wanandroid.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.fenger.wanandroid.R
import com.fenger.wanandroid.adapter.TreeListAdapter
import com.fenger.wanandroid.base.BaseFragment
import com.fenger.wanandroid.bean.TreeListData
import com.fenger.wanandroid.network.RetrofitHelper
import kotlinx.android.synthetic.main.fragment_tree.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


/**
 * @author fengerzhang
 * @date 3/5/21 4:10 PM
 */
class TreeFragment : BaseFragment() {

    private lateinit var firstLvData: List<TreeListData.Data>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        initView()
        return inflater.inflate(R.layout.fragment_tree, container, false)
    }

    private fun initView() {
        RetrofitHelper.retrofitService.getTypeTreeList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                initRecycleView(it)
            }, {
                it.toString()
            })
    }

    private fun initRecycleView(result: TreeListData) {
        if (result.errorCode != 0) {
            return
        }

        firstLvData = result.data
        if (!firstLvData.isNullOrEmpty()) {
            tree_list.layoutManager = LinearLayoutManager(context)
            tree_list.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            val adapter = TreeListAdapter(firstLvData)
            adapter.setOnItemClickListener(object : TreeListAdapter.OnItemClickListener {
                override fun onItemClick(view: View, position: Int) {
                    refreshList()
                }
            })
            tree_list.adapter = adapter

        }
    }

    fun refreshList() {
        // TODO
    }
}