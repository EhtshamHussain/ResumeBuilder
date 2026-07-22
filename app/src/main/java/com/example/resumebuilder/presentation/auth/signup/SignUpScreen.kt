package com.example.resumebuilder.presentation.auth.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.resumebuilder.R
import com.example.resumebuilder.presentation.shared.navigation.NavigationAction
import com.example.resumebuilder.presentation.shared.presentation.base.BaseScreen
import com.example.resumebuilder.presentation.shared.presentation.base.BaseViewModel
import com.example.resumebuilder.presentation.shared.presentation.component.labels.LabelText
import com.example.resumebuilder.presentation.shared.presentation.component.divider.OrDivider
import com.example.resumebuilder.presentation.shared.presentation.component.buttons.CustomButton
import com.example.resumebuilder.presentation.shared.presentation.component.textfield.CustomTextField
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun SignUpScreen(
    state: SignUpState = SignUpState(),
    baseUIEvent: SharedFlow<BaseViewModel.BaseViewModelEvents> = MutableSharedFlow(),
    actionEvent: (SignUpEvent) -> Unit = {},
    navigation: (NavigationAction) -> Unit = {}
) {
    BaseScreen(
        baseUIEvents = baseUIEvent,
        navigation = navigation
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(vertical = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(R.drawable.auth_screen_logo),
                contentDescription = null,
                modifier = Modifier.size(80.dp)
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = "CareerSync AI",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF005EA4)
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "Accelerate your professional journey",
                fontSize = 16.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "Create Account",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            LabelText(
                text = "Full Name",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp)
            )
            CustomTextField(
                value = state.name,
                placeholder = "Enter your full name ",
                onValueChange = { actionEvent(SignUpEvent.NameChanged(it)) },
                trailingIcon = Icons.Default.Person,
                modifier = Modifier.fillMaxWidth(.9f)
            )

            Spacer(modifier = Modifier.height(20.dp))

            LabelText(
                text = "Email Address",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp)
            )
            CustomTextField(
                value = state.email,
                placeholder = "Enter your email ",
                onValueChange = { actionEvent(SignUpEvent.EmailChanged(it)) },
                trailingIcon = Icons.Default.Email,
                modifier = Modifier.fillMaxWidth(.9f)
            )

            Spacer(modifier = Modifier.height(20.dp))

            LabelText(
                text = "Password",

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp)
            )
            CustomTextField(
                value = state.password,
                placeholder = "Set up strong password",
                onValueChange = { actionEvent(SignUpEvent.PasswordChanged(it)) },
                isPassword = true,
                modifier = Modifier.fillMaxWidth(.9f)
            )

            state.error?.let {
                Text(it, color = Color.Red, fontSize = 14.sp)
            }
            Spacer(modifier = Modifier.height(15.dp))
            var checked by remember { mutableStateOf(false) }
            Row(
                modifier = Modifier
                    .fillMaxWidth(.9f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = checked,
                    onCheckedChange = { checked = it }
                )
                val annotatedString = buildAnnotatedString {
                    append("By signing up, I agree to the ")
                    withStyle(
                        style = SpanStyle(
                            color = Color(0xFF005EA4),
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append("Terms of Service")
                    }
                    append(" and ")
                    withStyle(
                        style = SpanStyle(
                            color = Color(0xFF005EA4),
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append("Privacy Policy")
                    }
                }
                Text(
                    text = annotatedString,
                    fontSize = 12.sp
                )
            }

            Spacer(modifier = Modifier.height(25.dp))

            CustomButton(
                onClick = {
                    actionEvent(SignUpEvent.SignUpClicked)
                },
                modifier = Modifier.fillMaxWidth(.9f),
                isWithIcon = true,
                text = "Create Account",
                contentColor = Color.White,
                containerColor = Color(0xFF005EA4)
            )

            Spacer(modifier = Modifier.height(25.dp))
            OrDivider()
            Spacer(modifier = Modifier.height(25.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Already have an account? ", fontSize = 14.sp)
                Text(
                    text = "Login",
                    fontSize = 14.sp,
                    color = Color(0xFF005EA4),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable {
                        actionEvent(SignUpEvent.NavigateToLogin)
                    }
                )
            }
        }
    }
}