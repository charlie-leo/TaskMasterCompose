package com.task.master.presentation.dialog

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerColors
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.task.master.R
import com.task.master.utils.extensions.ItimFont
import com.task.master.presentation.common.ThemeImageView
import com.task.master.presentation.common.ThemeOutlineTextField
import com.task.master.presentation.common.ThemePrimaryButton
import com.task.master.presentation.ui.screens.Tasks
import com.task.master.ui.theme.ButtonGreen
import com.task.master.ui.theme.PrimaryColor
import com.task.master.ui.theme.PrimaryLightColor
import com.task.master.ui.theme.ProgressGreen
import com.task.master.utils.DateUtils

/**
 * Created by Charles Raj I on 21/04/24
 * @project TaskMaster Compose
 * @author Charles Raj
 */


@OptIn(ExperimentalMaterial3Api::class, ExperimentalCoilApi::class)
@Composable
fun AddTaskDialog(
    onDismissRequest: () -> Unit,
    onAddRequest: (task: Tasks) -> Unit,
//    galleryLauncher: ManagedActivityResultLauncher<String, Uri?>
) {
    val TAG = "AddTaskDialog"


    val task = remember {
        mutableStateOf(Tasks(0, "", "", "", mutableListOf()))
    }
    var showDialog by remember { mutableStateOf(false) }
    val dateState = rememberDatePickerState()
    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent())
        { uri ->
            var imageList = task.value.taskFiles.toMutableList()
            if (imageList.size < 3) {
                imageList.add(uri.toString())
                task.value = task.value.copy(taskFiles = imageList)
                Log.d(TAG, "HomeScreen: $uri")
            }
        }

    Dialog(
        onDismissRequest = {
            onDismissRequest()
        },
        properties = DialogProperties(
            dismissOnBackPress = true,
        ),
    ) {


//        val dialogWindow = getDialogWindow()
//        dialogWindow.let { window ->
//            window?.setDimAmount(0.5f)
//            window?.setWindowAnimations(-1)
//        }

        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp)
                .background(color = Color.White, shape = RoundedCornerShape(16.dp))
                .padding(16.dp)


        ) {
            val (titleText, formColumn,
                txtTasKDetail, txtEndTime,
                addImgLay, AddBtn) = createRefs()

            Text(text = "Add Task",
                fontFamily = ItimFont,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()

                    .constrainAs(titleText) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        height = Dimension.fillToConstraints
                    }
            )

            Column(
                modifier = Modifier
                    .constrainAs(formColumn) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(titleText.bottom, margin = 10.dp)
                        bottom.linkTo(parent.bottom)
                        width = Dimension.fillToConstraints
                        height = Dimension.wrapContent
                    }
            ) {
                ThemeOutlineTextField(
                    value = task.value.taskName,
                    onValueChange = {
                        task.value = task.value.copy(taskName = it)
                    },
                    label = "Task Name",
                    placeholder = "Task Name",
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()

                )
                ThemeOutlineTextField(
                    value = task.value.taskDetail,
                    onValueChange = {
                        task.value = task.value.copy(taskDetail = it)
                    },
                    label = "Task Details",
                    placeholder = "Task Details",
                    maxLine = 4,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                ThemeOutlineTextField(
                    value = task.value.taskEndDate,
                    onValueChange = {
                        task.value = task.value.copy(taskEndDate = it)
                    },
                    label = "End Time",
                    placeholder = "21/04/2024, 4:00 PM",
                    enable = false,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            showDialog = !showDialog
                        }

                )

                if (showDialog) {
                    ThemeDatePickerDialog(
                        onDismissRequest = {
                            showDialog = false
                        },
                        positiveClick = {
                            dateState.selectedDateMillis?.let {
                                task.value = task.value.copy(
                                    taskEndDate = DateUtils.formatDate(
                                        it,
                                        DateUtils.DATE_TIME_FORMAT
                                    )
                                )
                            }
                            showDialog = false
                        },
                        negativeClick = {
                            showDialog = false
                        },
                        dateState = dateState
                    )
                }


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding()
                        .background(color = Color.Transparent)
                        .padding(vertical = 10.dp)
                ) {
                    if (task.value.taskFiles.size < 3) {
                        Card(
                            modifier = Modifier
                                .size(50.dp)
                                .padding(5.dp)
                                .background(color = Color.Transparent)
                                .padding(end = 0.dp)
                                .clickable {
                                    launcher.launch("image/*")
                                },
                            border = BorderStroke(1.dp, Color.Black),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.Transparent
                            )
                        ) {
                            Icon(
                                Icons.Filled.Add,
                                "Floating action button.",
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(10.dp),
                            )
                        }
                    }
                    LazyRow(

                    ) {
                        items(task.value.taskFiles.size) { index ->
                            ImageCard(task.value.taskFiles[index]) {
                                removeImage(task, index)
                            }
                        }
                    }

                }

                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.CenterEnd
                ) {

                    ThemePrimaryButton(
                        text = "ADD",
                        onClick = {
                            onAddRequest(task.value)
                        }
                    )
                }

            }


        }
    }
}


@Composable
fun ImageCard(uri: String, onRemoveImage: () -> Unit) {
    Card(
        modifier = Modifier
            .size(50.dp)
            .padding(5.dp)
            .background(color = Color.Transparent)
            .padding(end = 0.dp),
        border = BorderStroke(1.dp, Color.Black),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )
    ) {

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopEnd
        ) {
            ThemeImageView(url = uri,
                modifier = Modifier.fillMaxWidth()
            )
            Icon(
                Icons.Filled.Close,
                "Floating action button.",
                tint = Color.White,
                modifier = Modifier
                    .size(20.dp)
                    .padding(end = 4.dp, top = 4.dp)
                    .background(
                        color = Color.Black.copy(alpha = 0.6f),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(2.dp)
                    .clickable {
                        onRemoveImage()
                    },
            )
        }

    }
}

fun removeImage(task: MutableState<Tasks>, index: Int) {
    var item = task.value.taskFiles.toMutableList()
    item.removeAt(index)
    task.value = task.value.copy(taskFiles = item)
}


@Composable
fun ViewTaskDialog(
    onDismissRequest: () -> Unit,
    task: Tasks,
    onCompleteTask: () -> Unit
) {
    Dialog(
        onDismissRequest = {
            onDismissRequest()
        },
        properties = DialogProperties(
            dismissOnBackPress = true,
        ),
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(16.dp)
        ) {
            Text(
                text = task.taskName,
                fontFamily = ItimFont,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Left
            )
            Text(
                text = task.taskDetail,
                fontFamily = ItimFont,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Left
            )
            LazyColumn {
                items (task.taskFiles.size) {index ->
                    AttachmentItem(task.taskFiles[index])
                }
            }

            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                ,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            )
            {
                IconButton(onClick = onDismissRequest ,
                    modifier = Modifier.fillMaxWidth()
                        .padding(20.dp)
                        .border(3.dp,
                            color = Color.Red,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .weight(1f),
                ) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "Add icon",
                        tint = Color.Red,
                        modifier = Modifier.fillMaxSize()
                        )
                }
                IconButton(onClick = onCompleteTask
                ,
                    modifier = Modifier.fillMaxWidth()
                        .padding(20.dp)
                        .border(3.dp,
                            color = ButtonGreen,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .weight(1f)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = "Add icon",
                        tint = ButtonGreen,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }



        }

    }
}

@Composable
fun AttachmentItem(url: String) {

    val expand = remember {
        mutableStateOf(false)
    }

    Box (
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        contentAlignment = Alignment.TopEnd
    ) {

        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = 13.dp)
        ) {
            drawLine(
                color = Color.Black,
                start = Offset(3f, size.height),
                end = Offset(size.width, size.height),
                strokeWidth = 1.dp.toPx(),
                cap = StrokeCap.Round
            )
        }
            if (expand.value) {
                ThemeImageView(
                    url = url,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp)
                        .background(
                            color = Color.Transparent,
                            shape = RoundedCornerShape(
                                bottomStartPercent = 16,
                                bottomEndPercent = 16
                            )
                        )
                )
            }
        var rowBg = Color.Transparent
        var iconTint = Color.Black
        if (expand.value) {
            rowBg = Color.Black.copy(alpha = 0.3f)
            iconTint = Color.White
        }

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(vertical = 0.dp)
                .clickable {
                    expand.value = !expand.value
                }
                .background(color = rowBg)
                .padding(10.dp)
            ,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){

            Icon(
                painter = painterResource(id = R.drawable.file_icon),
                "Floating action button.",
                tint = iconTint,
                modifier = Modifier
                    .size(25.dp)
            )
            Icon(
                Icons.Filled.KeyboardArrowDown,
                "Floating action button.",
                tint = iconTint,
                modifier = Modifier
                    .size(25.dp)
            )


        }

    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThemeDatePickerDialog(
    onDismissRequest: () -> Unit,
    positiveText: String = "Ok",
    positiveClick: () -> Unit,
    negativeText: String = "Cancel",
    negativeClick: () -> Unit,
    colors: DatePickerColors = DatePickerDefaults.colors(
        containerColor = PrimaryLightColor,
        titleContentColor = PrimaryColor,
        headlineContentColor = PrimaryColor,
        todayContentColor = PrimaryColor,
        selectedDayContainerColor = PrimaryColor,
        selectedDayContentColor = PrimaryLightColor,
        todayDateBorderColor = PrimaryColor,
        dayContentColor = PrimaryColor,
        currentYearContentColor = PrimaryLightColor,
        dayInSelectionRangeContainerColor = PrimaryLightColor,
        selectedYearContainerColor = PrimaryLightColor,
        selectedYearContentColor = PrimaryLightColor,
        weekdayContentColor = PrimaryLightColor,
    ),
    dateState: DatePickerState
) {
    DatePickerDialog(
        onDismissRequest = onDismissRequest,
        confirmButton = {
            ThemePrimaryButton(
                text = positiveText,
                onClick = positiveClick
            )
        },
        dismissButton = {
            ThemePrimaryButton(
                text = negativeText,
                onClick = negativeClick
            )
        },
        colors = colors
    ) {
        DatePicker(
            state = dateState,
            showModeToggle = true
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DialogPreview() {
//    AddTaskDialog()
    AttachmentItem("")
}