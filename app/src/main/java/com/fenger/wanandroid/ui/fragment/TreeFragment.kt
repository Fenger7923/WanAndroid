package com.fenger.wanandroid.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fenger.wanandroid.R
import com.fenger.wanandroid.adapter.RecyclerViewAdapter
import com.fenger.wanandroid.adapter.TreeListAdapter
import com.fenger.wanandroid.base.BaseFragment
import com.fenger.wanandroid.bean.ArticleListData
import com.fenger.wanandroid.bean.TreeListData
import com.fenger.wanandroid.network.RetrofitHelper
import kotlinx.android.synthetic.main.fragment_tree.tree_article_list
import kotlinx.android.synthetic.main.fragment_tree.tree_flow
import kotlinx.android.synthetic.main.fragment_tree.tree_list
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


/**
 * @author fengerzhang
 * @date 3/5/21 4:10 PM
 */
class TreeFragment : BaseFragment() {

    private lateinit var firstLvData: List<TreeListData.Data>

    private var articleListData: MutableList<ArticleListData.Data.Datas> = mutableListOf()
    private var curPage = 0
    private var totalPage = 0
    private var isLoading = false
    private var treeCid = 0

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

        tree_article_list.layoutManager = LinearLayoutManager(context)
        tree_article_list.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        tree_article_list.adapter = RecyclerViewAdapter(articleListData)

        tree_article_list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val total = (tree_article_list.layoutManager as LinearLayoutManager).itemCount
                val last = (tree_article_list.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()

                if (last == total -1 && !isLoading) {
                    isLoading = true
                    if (curPage <= totalPage) {
                        curPage ++
                        loadArticleData(curPage)
                    }
                }
            }
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
                    tree_article_list.visibility = View.GONE
                    tree_flow.visibility = View.VISIBLE
                    refreshSecondLvList(firstLvData[position].children)
                }
            })
            tree_list.adapter = adapter
            refreshSecondLvList(firstLvData[0].children)
        }
    }

    fun refreshSecondLvList(datas: List<TreeListData.Data.Children>?) {
        tree_flow.removeAllViews()
        if (!datas.isNullOrEmpty()) {
            for (data in datas) {
                val tv = LayoutInflater.from(context).inflate(R.layout.item_flow, tree_flow, false) as TextView
                tv.text = data.name
                tv.setOnClickListener {
                    showArticleList()
                    treeCid = data.id
                    loadArticleData(0)
                }
                tree_flow.addView(tv)
            }
        }
    }

    private fun showArticleList() {
        articleListData.clear()
        tree_article_list.removeAllViews()
        tree_article_list.visibility = View.VISIBLE
        tree_flow.visibility = View.GONE
    }

    private fun loadArticleData(page: Int) {
        RetrofitHelper.retrofitService.getArticleList(page, treeCid)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                isLoading = false
                initArticleList(it)
            }, {
                it.toString()
            })
    }

    private fun initArticleList(result: ArticleListData) {
        if (result.errorCode != 0) {
            return
        }
        curPage = result.data.curPage - 1

        if (curPage == 0) {
            totalPage = result.data.pageCount - 1
        }

        result.data.datas?.forEach {
            articleListData.add(it)
        }

        tree_article_list.adapter?.notifyDataSetChanged()
    }
}