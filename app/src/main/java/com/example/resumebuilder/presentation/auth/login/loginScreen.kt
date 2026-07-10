package com.example.resumebuilder.presentation.auth.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.resumebuilder.R
import com.example.resumebuilder.presentation.shared.navigation.NavigationAction
import com.example.resumebuilder.presentation.shared.presentation.base.BaseScreen
import com.example.resumebuilder.presentation.shared.presentation.base.BaseViewModel
import com.example.resumebuilder.presentation.shared.presentation.component.labels.LabelText
import com.example.resumebuilder.presentation.shared.presentation.component.divider.OrDivider
import com.example.resumebuilder.screens.CustomButton
import com.example.resumebuilder.screens.CustomTextField
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import org.koin.androidx.compose.koinViewModel

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun LoginScreen(
    state: LoginState=LoginState(),
    baseUiEvent: SharedFlow<BaseViewModel.BaseViewModelEvents> = MutableSharedFlow(),
    navigation: (NavigationAction) -> Unit = {},
    actionEvent: (LoginEvent) -> Unit = {},
) {
    BaseScreen(
        baseUIEvents = baseUiEvent,
        navigation = navigation
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Image(painter = painterResource(R.drawable.auth_screen_logo), contentDescription = null)
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Welcome Back", fontSize = 32.sp)
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = "Sign in to continue your career journey ", fontSize = 16.sp)

            Spacer(modifier = Modifier.height(25.dp))

            LabelText(
                text = "Email Address", modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp)
            )
            CustomTextField(
                value = state.email,
                onValueChange = { actionEvent(LoginEvent.EmailChanged(it)) },
                trailingIcon = Icons.Default.Email,
                modifier = Modifier.fillMaxWidth(.9f)
            )


            Spacer(modifier = Modifier.height(25.dp))

            LabelText(
                text = "Password",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp)
            )
            CustomTextField(
                value = state.password,
                onValueChange = {actionEvent(LoginEvent.PasswordChanged(it)) },
                isPassword = true,
                modifier = Modifier.fillMaxWidth(.9f)
            )


            Spacer(modifier = Modifier.height(35.dp))

            state.error?.let { error ->
                Text(text = error, color = Color.Red, fontSize = 14.sp)
            }
            Spacer(modifier = Modifier.height(35.dp))

            CustomButton(
                onClick = {
                    actionEvent(LoginEvent.LoginClicked)
                },
                modifier = Modifier.fillMaxWidth(.9f),
                isLogin = true,
                text = "Login",
                contentColor = Color.White,
                containerColor = Color(0xFF005EA4)
            )

            Spacer(modifier = Modifier.height(35.dp))
            OrDivider()
            Spacer(modifier = Modifier.height(35.dp))
            CustomButton(
                onClick = { actionEvent(LoginEvent.NavigateToSignup) },
                text = "SignUp",
                modifier = Modifier.fillMaxWidth(.9f),
                contentColor = Color(0xFF005EA4)
            )

        }
    }
}



