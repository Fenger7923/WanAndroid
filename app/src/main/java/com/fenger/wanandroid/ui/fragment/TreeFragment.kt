package com.fenger.wanandroid.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.fenger.wanandroid.R
import com.fenger.wanandroid.adapter.TreeListAdapter
import com.fenger.wanandroid.base.BaseFragment
import com.fenger.wanandroid.bean.TreeListData
import com.fenger.wanandroid.network.RetrofitHelper
import kotlinx.android.synthetic.main.fragment_tree.tree_list
import kotlinx.android.synthetic.main.fragment_tree.trees_flow
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


/**
 * @author fengerzhang
 * @date 3/5/21 4:10 PM
 */
class TreeFragment : BaseFragment() {

    private lateinit var firstLvData: List<TreeListData.Data>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tree, container, false)
    }

    override fun initView() {
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
            tree_list.layoutManager = LinearLayoutManager(context).apply {
                stackFromEnd = true
            }

            tree_list.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            val adapter = TreeListAdapter(firstLvData)

            adapter.setOnItemClickListener(object : TreeListAdapter.OnItemClickListener {
                override fun onItemClick(view: View, position: Int) {
                    refreshSecondLvList(firstLvData[position].children)
                }
            })
            tree_list.adapter = adapter
            refreshSecondLvList(firstLvData[0].children)
        }
    }

    fun refreshSecondLvList(datas: List<TreeListData.Data.Children>?) {
        trees_flow.removeAllViews()
        if (!datas.isNullOrEmpty()) {
            for (data in datas) {
                val tv = LayoutInflater.from(context).inflate(R.layout.item_flow, trees_flow, false) as TextView
                tv.text = data.name
                tv.setOnClickListener {

                }
                trees_flow.addView(tv)
            }
        }
    }
}