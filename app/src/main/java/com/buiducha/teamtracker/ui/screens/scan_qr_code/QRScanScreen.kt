package com.buiducha.teamtracker.ui.screens.scan_qr_code

import android.Manifest
import android.app.Activity
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.journeyapps.barcodescanner.CaptureManager
import com.journeyapps.barcodescanner.CompoundBarcodeView

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun QRScanScreen() {
    val context = LocalContext.current
    var scanFlag by remember {
        mutableStateOf(false)
    }

//    val permissionState = rememberPermissionState(Manifest.permission.CAMERA)
//
//    LaunchedEffect(permissionState) {
//        if (!permissionState.status.isGranted) {
//            when {
//                permissionState.shouldShowRationale -> {
//                    // Display a rationale for requesting the permission
//                }
//                else -> {
//                    // Request the permission
//                    val permissionRequested = permissionState.launchPermissionRequest()
//                    if (permissionRequested) {
//                        // Permission request has been launched
//                    } else {
//                        // Permission request has been denied, handle this case
//                    }
//                }
//            }
//        } else {
//            // Permission is already granted, start the action
//        }
//    }

    val compoundBarcodeView = remember {
        CompoundBarcodeView(context).apply {
            val capture = CaptureManager(context as Activity, this)
            capture.initializeFromIntent(context.intent, null)
            this.setStatusText("")
            capture.decode()
            this.decodeContinuous { result ->
                if(scanFlag){
                    return@decodeContinuous
                }
                scanFlag = true
                result.text?.let { barCodeOrQr->
                    //Do something and when you finish this something
                    //put scanFlag = false to scan another item
                    Log.d("This is a log", barCodeOrQr)
                    scanFlag = false
                }
                //If you don't put this scanFlag = false, it will never work again.
                //you can put a delay over 2 seconds and then scanFlag = false to prevent multiple scanning

            }
        }
    }

    AndroidView(
        modifier = Modifier,
        factory = { compoundBarcodeView },
    )
}