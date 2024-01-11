package com.buiducha.teamtracker.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.buiducha.teamtracker.repository.FirebaseRepository
import com.buiducha.teamtracker.repository.StreamRepository
import com.buiducha.teamtracker.ui.navigation.AuthGraph
import com.buiducha.teamtracker.ui.navigation.MainGraph
import com.buiducha.teamtracker.ui.navigation.SplashScreenGraph
import com.buiducha.teamtracker.ui.screens.root_screen.RootScreen
import com.buiducha.teamtracker.ui.theme.TeamTrackerTheme
import com.buiducha.teamtracker.viewmodel.shared_viewmodel.CurrentUserInfoViewModel
import io.getstream.chat.android.models.User
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val firebaseRepository = FirebaseRepository.get()
    private val streamRepository = StreamRepository.get()
    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val currentUserInfoViewModel: CurrentUserInfoViewModel by viewModels()
        lifecycleScope.launch {
            currentUserInfoViewModel.currentUserInfo.collect {
                val name = currentUserInfoViewModel.currentUserInfo.value.fullName
                if (name.isNotEmpty() ) {
                    val user = User(
                        id = firebaseRepository.getCurrentUser()?.uid!!,
                        name = name
                    )
                    streamRepository.initUser(user)
                }
            }
        }
        setContent {
            TeamTrackerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RootScreen(
                        currentUserInfoViewModel = currentUserInfoViewModel
                    )
                }
            }
        }
    }
}