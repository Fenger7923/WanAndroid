package com.example.library_base.http

import com.example.library_base.bean.ArticleDataList
import com.example.library_base.bean.BannerDataList
import com.example.library_base.bean.LoginData
import com.example.library_base.bean.TreeListData

/**
 * 首页数据
 * http://www.wanandroid.com/article/list/0/json
 * @param page page
 * @return MainTabData
 */
suspend inline fun getMainTabList(page: Int): ArticleDataList {
    val request = HttpRequest(url = "/article/list/{page}/json").putPath("page", page.toString())
    return HttpController.get(request, ArticleDataList::class.java)
}

/**
 * 首页Banner
 * @return BannerData
 */
suspend inline fun getBanner(): BannerDataList {
    val request = HttpRequest(url = "/banner/json")
    return HttpController.get(request, BannerDataList::class.java)
}

/**
 * 知识体系
 * @return TreeListData
 */
suspend inline fun getTypeTreeList(): TreeListData {
    val request = HttpRequest(url = "/tree/json")
    return HttpController.get(request, TreeListData::class.java)
}

/**
 * 知识体系下的文章
 * @param page page
 * @param cid cid
 */
suspend inline fun getArticleList(page: Int, cid: Int): ArticleDataList {
    val request = HttpRequest(url = "/article/list/{page}/json")
        .putPath("page", page.toString())
        .putQuery("cid", cid.toString())
    return HttpController.get(request, ArticleDataList::class.java)
}

/**
 * 登陆
 * @param username username
 * @param password password
 */
suspend inline fun login(username: String, password: String): LoginData {
    val request = HttpRequest(url = "user/login")
        .putPath("username", username)
        .putQuery("password", password)
    return HttpController.post(request, LoginData::class.java)
}