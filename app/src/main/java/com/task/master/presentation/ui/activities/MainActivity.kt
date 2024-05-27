package com.task.master.presentation.ui.activities

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.animation.AnticipateInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.animation.doOnEnd
import androidx.core.content.pm.ShortcutInfoCompat
import androidx.core.content.pm.ShortcutManagerCompat
import androidx.core.graphics.drawable.IconCompat
import com.task.master.R
import com.task.master.presentation.ui.screens.Onboarding
import com.task.master.presentation.ui.viewmodel.MainActivityViewModel
import com.task.master.ui.theme.TaskMasterComposeTheme
import dagger.hilt.android.AndroidEntryPoint

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
                    var type = ""
                    intent?.let{intent ->
                        intent.getStringExtra("Type")?.let {
                            type = it
                        }
                    }

                    Onboarding(mainViewModel,type)
                }
            }
        }
        val context = this.baseContext
        val shortcut = ShortcutInfoCompat.Builder(context, "dynamic_Id")
            .setShortLabel("Dynamic Item")
            .setLongLabel("Dynamic Item")
            .setIcon(IconCompat.createWithResource(context, R.drawable.profile_icon))
            .setIntent(
                Intent(context,MainActivity::class.java).apply {
                    action = Intent.ACTION_VIEW
                    putExtra("Type", "")
                }
            )
            .build()
        ShortcutManagerCompat.pushDynamicShortcut(context, shortcut)


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

//@Composable
//fun SplashScreen(navController: NavHostController, viewModel: MainActivityViewModel) {
//    Column(
//        modifier =
//        Modifier
//            .fillMaxSize()
//            .background(PrimaryLightColor),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    )
//    {
//
//
//        Image(
//            painter = painterResource(id = R.drawable.tm_logo), contentDescription = "Logo Image",
//            modifier = Modifier
//                .size(width = 200.dp, height = 200.dp)
//                .shadow(50.dp, shape = CircleShape)
//        )
//    }
//
//    android.os.Handler().postDelayed({
//        navController.navigate(Navigations.HOME_SCREEN)
//    }, 3000)
//}




//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun GreetingPreview() {
//    TaskMasterComposeTheme {
//        Onboarding()
//    }
//}