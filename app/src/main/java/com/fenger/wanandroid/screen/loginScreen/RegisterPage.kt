package com.fenger.wanandroid.ui.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fenger.wanandroid.R
import com.fenger.wanandroid.constants.inputEditTextType

/**
 * @author fengerzhang
 * @date 2021/10/9 17:00
 */

@Composable
fun RegisterPage(toNextPage: () -> Unit) {
    var userName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var assertPassword by remember { mutableStateOf("") }
    var isErrorInPassword by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize().padding(top = 40.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(bottom = 60.dp)
                .clickable {
                    toNextPage.invoke()
                }
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_next),
                contentDescription = null,
                modifier = Modifier.size(15.dp)
            )
            Text(
                text = stringResource(R.string.to_login),
                modifier = Modifier.padding(start = 10.dp, end = 10.dp),
                color = colorResource(id = R.color.blue),
                fontSize = 12.sp
            )
        }
        TextField(
            value = userName,
            onValueChange = { userName = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Ascii),
            label = { Text(text = stringResource(R.string.enter_username)) },
            singleLine = true,
            colors = TextFieldDefaults.inputEditTextType()
        )
        TextField(
            value = password,
            onValueChange = { password = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            label = { Text(text = stringResource(R.string.enter_password)) },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            colors = TextFieldDefaults.inputEditTextType()
        )
        TextField(
            value = assertPassword,
            onValueChange = {
                assertPassword = it
                if (assertPassword != password) {
                    isErrorInPassword = true
                }
            },
            isError = isErrorInPassword,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            label = { Text(text = stringResource(R.string.enter_password)) },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            colors = TextFieldDefaults.inputEditTextType()
        )

        Text(
            text = "注册",
            textAlign = TextAlign.Center,
            fontSize = 15.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp, start = 60.dp, end = 60.dp)
                .background(
                    color = colorResource(id = R.color.blue),
                    shape = RoundedCornerShape(100.dp),
                )
                .height(44.dp)
                .offset(y = 10.dp),
            color = colorResource(id = R.color.white),
        )
    }
}