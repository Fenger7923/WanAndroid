package com.fenger.wanandroid.ui.tree

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.library_base.bean.TreeData
import com.fenger.wanandroid.R
import com.fenger.wanandroid.adapter.ArticleDataItem
import com.google.accompanist.flowlayout.FlowRow
import com.google.android.material.composethemeadapter.MdcTheme

/**
 * @author fengerzhang
 * @date 2021/10/13 21:06
 */

@Composable
fun TreePage(treeViewModel: TreeViewModel = TreeViewModel()) {

    val firstLvData by treeViewModel.firstLvData.observeAsState(listOf())
    val secondLvData by treeViewModel.secondLvData.observeAsState(listOf())
    val articleListData by treeViewModel.articleListData.observeAsState(listOf())
    val isFlowShow by treeViewModel.isInSecondLv.observeAsState(true)

    MdcTheme {
        Row {
            LazyColumn {
                itemsIndexed(firstLvData) { index, item ->
                    // TODO 使用box或stack垂直居中
                    Text(
                        text = item.name,
                        modifier = Modifier
                            .height(35.dp)
                            .width(95.dp)
                            .padding(bottom = 1.dp)
                            .background(color = colorResource(id = R.color.gray))
                            .clickable {
                                treeViewModel.showFirstLvView(index)
                            },
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                    )
                }
            }


            if (isFlowShow) {
                // TODO 太难看了～～
                FlowTreeData(secondLvData, treeViewModel)
            } else {
                LazyColumn {
                    itemsIndexed(articleListData) { _, item ->
                        ArticleDataItem(item)
                        Divider(color = Color.LightGray)
                    }
                }
            }
        }
    }
}

@Composable
private fun FlowTreeData(
    secondLvData: List<TreeData>,
    treeViewModel: TreeViewModel
) {
    FlowRow(modifier = Modifier.fillMaxSize()) {
        for (item in secondLvData) {
            Text(
                text = item.name,
                modifier = Modifier
                    .padding(5.dp)
                    .wrapContentSize()
                    .clickable {
                        treeViewModel.showSecondLvView(item.id)
                    }
                    .background(
                        color = colorResource(id = R.color.blue),
                        shape = RoundedCornerShape(50)
                    )
                    .padding(5.dp),
                fontSize = 12.sp
            )
        }
    }
}