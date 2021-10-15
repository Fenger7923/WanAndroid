package com.fenger.wanandroid.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fenger.wanandroid.R
import com.fenger.wanandroid.utils.User

/**
 * @author fengerzhang
 * @date 2021/10/15 19:19
 */

@Composable
fun UserPage() {
    Column {
        SetUserInfo(isLogin = User.isLogin)
        SetSettingList()
    }
}

@Composable
private fun SetUserInfo(isLogin: Boolean) {
    val context = LocalContext.current
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .height(260.dp)
            .background(color = colorResource(R.color.blue))
            .clickable {
                if (!isLogin) {
                    User.doIfLogin(context)
                }
            }
    ) {
        Image(
            bitmap = ImageBitmap.imageResource(id = R.drawable.pager_image1),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(80.dp)
                .height(80.dp)
                .clip(shape = RoundedCornerShape(50)),
            contentDescription = "用户头像"
        )
        Text(
            text = if (isLogin) User.username else stringResource(id = R.string.to_login),
            modifier = Modifier.padding(top = 10.dp, bottom = 5.dp),
            color = Color.White,
            fontSize = 22.sp
        )
        if (isLogin) {
            Text(
                text = "ID: ${User.id}",
                modifier = Modifier.padding(bottom = 5.dp),
                color = Color.White,
                fontSize = 12.sp
            )
            Text(
                text = "等级: ${User.icon}   排名: ${User.username}",
                modifier = Modifier.padding(bottom = 5.dp),
                color = Color.White,
                fontSize = 12.sp
            )
        }
    }
}

@Composable
private fun SetSettingList() {
    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = colorResource(R.color.white))
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(start = 16.dp, end = 16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_action_like),
                contentDescription = null
            )
            Text(
                text = stringResource(id = R.string.my_points),
                fontSize = 15.sp,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 10.dp, end = 10.dp)
            )
            Text(
                text = User.coinCount.toString(),
                fontSize = 13.sp,
                color = colorResource(id = R.color.red),
                modifier = Modifier.padding(end = 5.dp)
            )
            Image(
                bitmap = ImageBitmap.imageResource(id = R.drawable.ic_next),
                contentDescription = null,
                modifier = Modifier
                    .size(13.dp)
                    .rotate(180f)
            )
        }
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(start = 16.dp, end = 16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_action_like),
                contentDescription = null
            )
            Text(
                text = stringResource(id = R.string.my_collect),
                fontSize = 15.sp,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 10.dp, end = 10.dp)
            )
            Image(
                bitmap = ImageBitmap.imageResource(id = R.drawable.ic_next),
                contentDescription = null,
                modifier = Modifier
                    .size(13.dp)
                    .rotate(180f)
            )
        }
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(start = 16.dp, end = 16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_action_like),
                contentDescription = null
            )
            Text(
                text = stringResource(id = R.string.about_me),
                fontSize = 15.sp,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 10.dp, end = 10.dp)
            )
            Image(
                bitmap = ImageBitmap.imageResource(id = R.drawable.ic_next),
                contentDescription = null,
                modifier = Modifier
                    .size(13.dp)
                    .rotate(180f)
            )
        }
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(start = 16.dp, end = 16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_action_like),
                contentDescription = null
            )
            Text(
                text = stringResource(id = R.string.system_settings),
                fontSize = 15.sp,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 10.dp, end = 10.dp)
            )
            Image(
                bitmap = ImageBitmap.imageResource(id = R.drawable.ic_next),
                contentDescription = null,
                modifier = Modifier
                    .size(13.dp)
                    .rotate(180f)
            )
        }
    }
}