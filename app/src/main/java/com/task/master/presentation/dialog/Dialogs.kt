package com.task.master.presentation.dialog

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.task.master.extensions.ItimFont
import com.task.master.presentation.ui.screens.Tasks
import com.task.master.presentation.ui.screens.ThemeOutlineTextField
import com.task.master.presentation.ui.screens.getDialogWindow
import com.task.master.ui.theme.PrimaryColor
import com.task.master.ui.theme.PrimaryLightColor

/**
 * Created by Charles Raj I on 21/04/24
 * @project TaskMaster Compose
 * @author Charles Raj
 */


@Composable
fun AddTaskDialog(
    onDismissRequest: () -> Unit,
    onAddRequest: (task : Tasks) -> Unit
) {

    val task = remember {
        mutableStateOf(Tasks(0,"","","", mutableListOf()))
    }

    Dialog(onDismissRequest = {
        onDismissRequest()
    },
        properties = DialogProperties(
            dismissOnBackPress = true,
        ),
    ) {


        val dialogWindow = getDialogWindow()
        dialogWindow.let { window ->
            window?.setDimAmount(0.5f)
            window?.setWindowAnimations(-1)
        }

        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .requiredHeight(400.dp)
//                .height(375.dp)
//                    .shadow(17.dp, shape = RoundedCornerShape(16.dp), false, Color.Black, PrimaryColor)
                .padding(16.dp)
                .background(color = Color.White, shape = RoundedCornerShape(16.dp))
                .padding(16.dp)
                .zIndex(10f)

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
                    .fillMaxWidth()
                    .constrainAs(formColumn) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(titleText.bottom, margin = 10.dp)
                        bottom.linkTo(parent.bottom)
                        width = Dimension.fillToConstraints
                        height = Dimension.fillToConstraints
                    }
            ) {
                ThemeOutlineTextField(
                    value = task.value.taskName,
                    onValueChange = {
                        task.value.taskName = it
                    },
                    label = "Task Name",
                    placeholder = "Task Name",
                    modifier = Modifier
                        .fillMaxWidth()
                )
                ThemeOutlineTextField(
                    value = task.value.taskDetail,
                    onValueChange = {
                        task.value.taskDetail = it
                    },
                    label = "Task Details",
                    placeholder = "Task Details",
                    modifier = Modifier
                        .fillMaxWidth()
                )
                ThemeOutlineTextField(
                    value = task.value.taskEndDate,
                    onValueChange = {
                        task.value.taskEndDate = it
                    },
                    label = "End Time",
                    placeholder = "21/04/2024, 4:00 PM",
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding()
                        .background(color = Color.Transparent)
                        .padding(vertical = 10.dp)
                ) {
                    Card(
                        modifier = Modifier
                            .size(50.dp)
                            .background(color = Color.Transparent),
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

                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.CenterEnd
                ) {

                    Button(
                        onClick = {
                            onAddRequest(task.value)
                        },
//                        modifier = Modifier
//                            .background(
//                                color = PrimaryColor,
//                                shape = RoundedCornerShape(10.dp)
//                            ),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = PrimaryColor,
                            contentColor = PrimaryLightColor
                        ),
                        elevation = ButtonDefaults.buttonElevation(
                            defaultElevation = 5.dp,
                            pressedElevation = 1.dp
                        )
                    ) {
                        Text(text = "ADD")
                    }
                }

            }


        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DialogPreview() {
//    AddTaskDialog()
}