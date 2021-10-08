package com.fenger.wanandroid.constants

import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.fenger.wanandroid.R

/**
 * @author fengerzhang
 * @date 2021/10/8 19:53
 */
@Composable
fun TextFieldDefaults.inputEditTextType(): TextFieldColors = textFieldColors(
    backgroundColor = Color.Transparent,
    disabledIndicatorColor = Color.Transparent,
    errorIndicatorColor = Color.Transparent,
    focusedIndicatorColor = colorResource(id = R.color.blue),
    focusedLabelColor = Color.Transparent,
    unfocusedLabelColor = Color.LightGray
)