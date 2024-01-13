package com.buiducha.teamtracker.ui.screens.scan_qr_code

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.buiducha.speedyfood.ui.screens.shareds.SimpleTopBar
import com.buiducha.teamtracker.viewmodel.shared_viewmodel.SelectedWorkspaceViewModel

@Preview
@Composable
fun QRScreenPreview() {
//    QRScreen()
}

@Composable
fun QRScreen(
    navController: NavController,
    selectedWorkspaceViewModel: SelectedWorkspaceViewModel
) {
    val data = selectedWorkspaceViewModel.workspace.collectAsState()
    Scaffold(
        topBar = {
            SimpleTopBar(onBackListener = { navController.popBackStack() })
        }
    ) {paddingValues ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            Image(
                painter = rememberQrBitmapPainter(data.value.id),
                contentDescription = "workspace_qr",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.size(240.dp),
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = data.value.name,
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = "Scan QR code to join the group",
                fontSize = 18.sp
            )
        }
    }

}