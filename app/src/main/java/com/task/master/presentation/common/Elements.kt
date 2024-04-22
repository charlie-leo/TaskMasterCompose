package com.task.master.presentation.common

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.task.master.extensions.ItimFont
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