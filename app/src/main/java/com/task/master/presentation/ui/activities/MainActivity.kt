package com.task.master.presentation.ui.activities

import android.animation.ObjectAnimator
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import android.view.animation.AnticipateInterpolator
import android.window.SplashScreenView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.animation.doOnEnd
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import com.task.master.R
import com.task.master.presentation.ui.screens.Navigations
import com.task.master.presentation.ui.screens.Onboarding
import com.task.master.presentation.ui.viewmodel.MainActivityViewModel
import com.task.master.ui.theme.PrimaryLightColor
import com.task.master.ui.theme.TaskMasterComposeTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    private val mainViewModel by viewModels<MainActivityViewModel>()

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskMasterComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Onboarding(mainViewModel)
                }
            }
        }

        val isloaded = true

        val content : View = findViewById(android.R.id.content)
        content.viewTreeObserver.addOnPreDrawListener {
            if (isloaded) {
                true
            } else {
                false
            }
        }

        splashScreen.setOnExitAnimationListener {view ->
            val slideUp = ObjectAnimator.ofFloat(
                view,
                View.TRANSLATION_Y,
                0f,
                -view.height.toFloat()
            )
            slideUp.interpolator = AnticipateInterpolator()
            slideUp.duration = 200L

            slideUp.doOnEnd { view.remove() }
            slideUp.start()
        }

    }
}

@Composable
fun SplashScreen(navController: NavHostController, viewModel: MainActivityViewModel) {
    Column(
        modifier =
        Modifier
            .fillMaxSize()
            .background(PrimaryLightColor),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )
    {


        Image(
            painter = painterResource(id = R.drawable.tm_logo), contentDescription = "Logo Image",
            modifier = Modifier
                .size(width = 200.dp, height = 200.dp)
                .shadow(50.dp, shape = CircleShape)
        )
    }

    android.os.Handler().postDelayed({
        navController.navigate(Navigations.HOME_SCREEN)
    }, 3000)
}




//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun GreetingPreview() {
//    TaskMasterComposeTheme {
//        Onboarding()
//    }
//}