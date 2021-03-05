package com.fenger.wanandroid.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fenger.wanandroid.R
import com.fenger.wanandroid.bean.MainTabData
import com.fenger.wanandroid.ui.activity.WebViewActivity
import com.fenger.wanandroid.ui.activity.WebViewActivity.Companion.JUMP_URL

/**
 * @author fengerzhang
 * @date 3/1/21 4:14 PM
 */
class RecyclerViewAdapter(private val datas: List<MainTabData.Data.Datas>) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val author: TextView = view.findViewById(R.id.homeItemAuthor)
        val title: TextView = view.findViewById(R.id.homeItemTitle)
        val type: TextView = view.findViewById(R.id.homeItemType)
        val date: TextView = view.findViewById(R.id.homeItemDate)
        val isFav: ImageView = view.findViewById(R.id.homeItemLike)
        var url: String = ""
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_article, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = datas[position]
        val context = holder.itemView.context
        holder.author.text = if (data.author.isNotEmpty()) data.author else data.shareUser
        holder.title.text = data.title
        holder.date.text = data.niceDate
        holder.type.text = data.chapterName
        holder.isFav.setImageResource(if (data.collect) R.drawable.ic_action_like else R.drawable.ic_action_no_like)
        holder.url = data.link
        holder.itemView.setOnClickListener {
            val intent = Intent(context, WebViewActivity::class.java)
            intent.putExtra(JUMP_URL, holder.url)
            context.startActivity(intent)
        }
    }
}