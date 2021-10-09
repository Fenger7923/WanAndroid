package com.fenger.wanandroid.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.library_base.http.login
import com.fenger.wanandroid.R
import com.fenger.wanandroid.base.BaseFragment
import com.fenger.wanandroid.constants.inputEditTextType
import com.fenger.wanandroid.ui.activity.LoginActivity
import com.fenger.wanandroid.utils.User
import com.google.android.material.composethemeadapter.MdcTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginFragment : BaseFragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            MdcTheme {
                SetLoginInfo()
            }
        }
    }

    @Composable
    fun SetLoginInfo() {

        var userName by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .padding(top = 40.dp, bottom = 60.dp)
                    .clickable {
                        (activity as LoginActivity).changeToRegister()
                    }
            ) {
                Text(
                    text = stringResource(R.string.to_register),
                    modifier = Modifier.padding(start = 10.dp, end = 10.dp),
                    color = colorResource(id = R.color.blue),
                    fontSize = 12.sp
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_next),
                    contentDescription = null,
                    modifier = Modifier.size(15.dp).rotate(180f)
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

            Text(
                text = "登录",
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
                    .offset(y = 10.dp)
                    .clickable {
                        CoroutineScope(Dispatchers.Default).launch {
                            User.setUser(login(userName, password))
                            activity?.finish()
                        }
                    },
                color = colorResource(id = R.color.white),
            )
        }
    }

    override fun initView() {
    }

}
