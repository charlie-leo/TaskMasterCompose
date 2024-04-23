package com.task.master.presentation.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.task.master.utils.extensions.ItimFont
import com.task.master.ui.theme.PrimaryColor
import com.task.master.ui.theme.PrimaryLightColor
import com.task.master.ui.theme.ProgressChecked

/**
 * Created by Charles Raj I on 21/04/24
 * @project TaskMaster Compose
 * @author Charles Raj
 */


@Composable
fun ThemeText(text: String, modifier: Modifier) {
    Text(text = text,
        fontFamily = ItimFont,
        fontWeight = FontWeight.Bold,
        color = ProgressChecked,
        fontSize = 14.sp,
        )
}

@Composable
fun ThemePrimaryButton(
    text: String,
    onClick : () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(10.dp),
    colors: ButtonColors = ButtonDefaults.buttonColors(
        containerColor = PrimaryColor,
        contentColor = PrimaryLightColor
    ),
    elevation: ButtonElevation? = ButtonDefaults.buttonElevation(
        defaultElevation = 5.dp,
        pressedElevation = 1.dp
    ),
    border: BorderStroke? = null,
){
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = shape,
        colors = colors,
        elevation = elevation
    ) {
        Text(text = text, fontFamily = ItimFont)
    }
}
@Composable
fun ThemeSecondaryButton(
    text: String,
    onClick : () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(10.dp),
    colors: ButtonColors = ButtonDefaults.buttonColors(
        containerColor = PrimaryColor,
        contentColor = PrimaryLightColor
    ),
    elevation: ButtonElevation? = ButtonDefaults.buttonElevation(
        defaultElevation = 5.dp,
        pressedElevation = 1.dp
    ),
    border: BorderStroke? = null,
){
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = shape,
        colors = colors,
        elevation = elevation
    ) {
        Text(text = text, fontFamily = ItimFont)
    }
}

@Composable
fun ThemeImageView(url : String, modifier: Modifier){
    val painter: Painter = rememberImagePainter(
        data = url,
        builder = {

        }
    )
    Image(
        painter = painter,
        contentDescription = "Image from URL: $url",
        contentScale = ContentScale.FillBounds,
        modifier = modifier
    )
}

@Composable
fun ThemeOutlineTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    singleLine: Boolean = false,
    enable: Boolean = true,
    maxLine: Int = 1,
    modifier: Modifier) {

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(text = label,
                fontFamily = ItimFont
            )
        },
        placeholder = {
            Text(text = placeholder,
                fontFamily = ItimFont
            )
        },
        singleLine = singleLine,
        maxLines = maxLine,
        shape = RoundedCornerShape(16.dp),
        enabled = enable,
        modifier = modifier,
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = Color.Black,
            disabledBorderColor = Color.Black,
            disabledTextColor = Color.Black,
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black,
            disabledPlaceholderColor = Color.Black,
            errorPlaceholderColor = Color.Black,
            focusedPlaceholderColor = Color.Black,
            unfocusedPlaceholderColor = Color.Black,
            disabledLabelColor = Color.Black,
            errorLabelColor = Color.Black,
            focusedLabelColor = Color.Black,
            unfocusedLabelColor = Color.Black,
            selectionColors = TextSelectionColors(
                handleColor = Color.Transparent,
                backgroundColor = Color.Transparent
            )
        )
    )
}