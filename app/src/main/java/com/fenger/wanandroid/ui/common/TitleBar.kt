package com.fenger.wanandroid.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * @author fengerzhang
 * @date 2021/11/30 10:55
 *
 * 标题
 * @param text 标题文本
 * @param showBack 是否展示返回按钮
 * @param iconImageVector 返回按钮的样式
 * @param backClick 返回按钮事件
 */
@Composable
fun TitleBar(
    text: String,
    showBack: Boolean = false,
    iconImageVector: ImageVector = Icons.Default.ArrowBack,
    backClick: (() -> Unit)? = null
) {
    TopAppBar(contentPadding = PaddingValues.Absolute()) {
        if (showBack) {
            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier
                    .width(50.dp)
                    .fillMaxHeight()
                    .clickable {
                        backClick?.invoke()
                    }

            ) {
                Icon(
                    imageVector = iconImageVector,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.padding(15.dp, 0.dp)
                )
            }
        }
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            Text(
                text = text,
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}