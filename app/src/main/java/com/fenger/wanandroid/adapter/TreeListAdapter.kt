package com.fenger.wanandroid.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fenger.wanandroid.R
import com.fenger.wanandroid.bean.TreeListData

/**
 * @author fengerzhang
 * @date 3/10/21 3:34 PM
 */
class TreeListAdapter(private val datas: List<TreeListData.Data>) : RecyclerView.Adapter<TreeListAdapter.ViewHolder>() {
    private var itemClickListener: OnItemClickListener? = null

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var id: Int = 0
        val name: TextView = view.findViewById(R.id.tree_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tree, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = datas[position].name
        holder.id = datas[position].id
        holder.itemView.setOnClickListener { view ->
            itemClickListener?.onItemClick(view, position)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
    }

    fun setOnItemClickListener(mOnItemClickListener: OnItemClickListener) {
        itemClickListener = mOnItemClickListener
    }
}