package com.buiducha.teamtracker.ui.screens.settings.settings_screen

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.buiducha.teamtracker.ui.theme.DarkGreen
import com.buiducha.teamtracker.viewmodel.SettingsViewModel
import com.buiducha.teamtracker.viewmodel.shared_viewmodel.CurrentUserInfoViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProfileSetting(
    modifier: Modifier = Modifier,
    currentUserInfoViewModel: CurrentUserInfoViewModel
) {
    val currentUserState by currentUserInfoViewModel.currentUserInfo.collectAsState()

    val settingsViewModel: SettingsViewModel = viewModel()
    var imgUrl = remember{ mutableStateOf("") }
    var uri by remember{
        mutableStateOf<Uri?>(null)
    }
    val singlePhotoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {
            uri = it
        }
    )

    val context = LocalContext.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .padding(
                all = 40.dp
            )
    ) {
        if(currentUserState.avatarUri == null){
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .background(
                        color = DarkGreen,
                        shape = CircleShape
                    )
                    .padding(32.dp)
            ) {
                Text(
                    text = if(currentUserState.fullName.length > 3) currentUserState.fullName.substring(0, 2).uppercase() else "",
                    fontSize = 36.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
        else{
            GlideImage(
                model = currentUserState.avatarUri,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(CircleShape)
                    .width(44.dp)
                    .aspectRatio(1f)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Edit image",
            color = Color.Blue,
            fontSize = 18.sp,
            modifier = Modifier
                .clickable {
                    singlePhotoPicker.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                }
        )

        var hasUploadedImage by remember { mutableStateOf(false) }
        if (uri != null && !hasUploadedImage){
            uri?.let {
                settingsViewModel.uploadImage(
                    uri = it,
                    context = context,
                    imgUrl = imgUrl,
                    oldImageUrl = currentUserInfoViewModel
                        .currentUserInfo.value.avatarUri
                        .toString()
                )
            }
            hasUploadedImage = true
        }

        if (hasUploadedImage && uri != null){
            settingsViewModel.updateUserAvatar(imgUrl)
        }
    }
}