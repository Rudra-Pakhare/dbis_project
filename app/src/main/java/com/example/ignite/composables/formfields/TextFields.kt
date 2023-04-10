package com.example.ignite.composables.formfields

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EmailField(
    value: String = "",
    onValueChange: (String) -> Unit,
    placeholder: String
){
    OutlinedTextField(
        value =  value,
        onValueChange = onValueChange,
        placeholder = { Text(placeholder) },
        shape = RoundedCornerShape(20),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .height(60.dp),
        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color(0x66E8E8E8), focusedIndicatorColor = Color.Gray, cursorColor = Color.Gray)
    )
}

@Composable
fun PasswordField(
    value: String = "",
    onValueChange: (String) -> Unit,
    placeholder: String,
    passwordVisibility : Boolean = false,
    onClk : () -> Unit
){
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange ,
        placeholder = { Text(placeholder) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            IconButton(onClick = onClk) {
                Icon(imageVector = if(passwordVisibility) Icons.Default.Visibility else Icons.Default.VisibilityOff,"")
            }
        },
        shape = RoundedCornerShape(20),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .height(60.dp),
        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color(0x66E8E8E8),focusedIndicatorColor = Color.Gray, cursorColor = Color.Gray )
    )
}

@Composable
fun MyButton(
    text: String = "",
    onClk: () -> Unit
){
    Button(
        onClick = onClk,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .height(60.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF5DB075)),
        shape = RoundedCornerShape(50)
    ) {
        Text(
            text = text,
            fontSize = 25.sp,
            color = Color.White
        )
    }
}

@Composable
fun TopRow(
    text1 : String ="",
    text2 : String ="",
    onClk: () -> Unit
){
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Text(
            text = text1,
            fontSize = 40.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(start = 125.dp)
        )
        Text(
            text = text2,
            Modifier
                .clickable(onClick = onClk)
                .padding(end = 10.dp),
            fontSize = 20.sp,
            color = Color(0xFF5DB075),
            fontWeight = FontWeight.SemiBold
        )
    }
}