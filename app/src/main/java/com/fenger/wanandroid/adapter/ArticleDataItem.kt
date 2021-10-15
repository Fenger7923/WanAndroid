package com.fenger.wanandroid.adapter

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.library_base.bean.ArticleData
import com.fenger.wanandroid.R
import com.fenger.wanandroid.ui.web.WebViewActivity
import com.fenger.wanandroid.utils.startActivityExt

/**
 * @author fengerzhang
 * @date 2021/10/13 15:11
 */
@Composable
fun ArticleDataItem(data: ArticleData) {
    val current = LocalContext.current

    Column(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()
        .clickable {
            current.startActivityExt<WebViewActivity>().apply {
                Intent().apply {
                    putExtra(WebViewActivity.JUMP_URL, data.link)
                }
            }
        }) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.pager_image1),
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
                    .padding(end = 4.dp),
            )

            Text(
                text = data.author,
                textAlign = TextAlign.Left,
                maxLines = 1,
                modifier = Modifier
                    .wrapContentSize()
                    .weight(1f)
            )

            Text(
                text = data.niceDate,
                modifier = Modifier
                    .padding(end = 10.dp)
                    .wrapContentSize(),
                color = colorResource(
                    id = R.color.gray
                ),
                textAlign = TextAlign.Right
            )
        }

        Text(
            text = data.title, modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 4.dp), fontSize = 19.sp
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = data.chapterName.toString(),
                modifier = Modifier.weight(1f),
                fontSize = 16.sp
            )

            Image(
                painter = painterResource(id = R.drawable.ic_action_no_like),
                contentDescription = null,
                modifier = Modifier
                    .wrapContentSize()
                    .padding(10.dp)
            )
        }
    }
}


@Composable
@Preview(showBackground = false)
fun TestArticleDataItem() {
    ArticleDataItem(
        data = ArticleData(
            0,
            0,
            "标题",
            0,
            "类型",
            "",
            "",
            "作者",
            "来源",
            0L,
            "",
            "",
            0,
            "日期",
            0,
            false,
            ""
        )
    )
}