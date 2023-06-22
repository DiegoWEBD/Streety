package com.caminaseguro

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.graphics.BlurMaskFilter
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.caminaseguro.location.LocationService
import com.caminaseguro.presentation.map.MapScreen
import com.caminaseguro.presentation.nav_bar.NavBar
import com.caminaseguro.presentation.permission_request.RequestPermissions
import com.caminaseguro.ui.theme.CaminaSeguroTheme
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng


class MainActivity : ComponentActivity() {

    private var requiredPermissions = listOf(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION
    )
    private val rationaleMessage = "Necesitamos que nos otorgues permisos para conocer tu ubicación y para enviarte notificaciones, " +
            "así podremos informarte en tiempo real sobre lo que está ocurriendo en tu entorno"

    private val viewModel by viewModels<MainActivityViewModel>()
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        if(Build.VERSION.SDK_INT >= 28) {
            requiredPermissions = requiredPermissions.plus(Manifest.permission.FOREGROUND_SERVICE)

            if(Build.VERSION.SDK_INT >= 33){
                requiredPermissions = requiredPermissions.plus(Manifest.permission.POST_NOTIFICATIONS)
            }
        }

        /*window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )*/

        setContent {
            CaminaSeguroTheme {
                val currentUserLocation = viewModel.currentUserLocation.observeAsState(LatLng(-29.939931, -71.253549))
                val isLocationPermissionGranted = viewModel.locationPermissionGranted.observeAsState(false)

                RequestPermissions(
                    permissions = requiredPermissions,
                    onRequestSucceeded = ::handlePermissionsGranted,
                    rationaleMessage = rationaleMessage,
                    goToAppSettings = ::goToAppSettings
                )

                Scaffold(
                    bottomBar = { NavBar() }
                ) {

                    MapScreen(
                        getDeviceLocation = ::getDeviceLocation,
                        currentLocation = currentUserLocation.value as LatLng,
                        padding = it,
                        launchToast = ::launchToast,
                        isLocationPermissionGranted = isLocationPermissionGranted.value
                    )
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        stopLocationService()
    }

    private fun launchToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun handlePermissionsGranted() {
        viewModel.locationPermissionGranted.value = true
        startLocationService()
        getDeviceLocation()
    }

    private fun startLocationService() {
        Intent(applicationContext, LocationService::class.java).apply {
            action = LocationService.ACTION_START
            startService(this)
        }
    }

    private fun stopLocationService() {
        Intent(applicationContext, LocationService::class.java).apply {
            action = LocationService.ACTION_STOP
            startService(this)
        }
    }

    private fun getDeviceLocation() {
        if(viewModel.locationPermissionGranted.value == false) return

        try {
            val locationResult = fusedLocationProviderClient.lastLocation

            locationResult.addOnCompleteListener { task ->
                if(task.isSuccessful) {
                    val lastKnownLocation = task.result
                    if(lastKnownLocation != null) {
                        //launchToast("Location: ${lastKnownLocation.latitude}, ${lastKnownLocation.longitude}")
                        viewModel.currentUserLocation.value = LatLng(lastKnownLocation.latitude, lastKnownLocation.longitude)
                    }
                }
            }
        } catch(e: SecurityException) {
            Log.d("Exception", e.message.toString())
        }
    }
}

fun Activity.goToAppSettings() {
    val intent = Intent(
        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
        Uri.fromParts("package", packageName, null)
    )
    startActivity(intent)
}

fun Modifier.shadow(
    color: Color = Color.Black,
    borderRadius: Dp = 0.dp,
    blurRadius: Dp = 0.dp,
    offsetY: Dp = 0.dp,
    offsetX: Dp = 0.dp,
    spread: Dp = 0f.dp,
    modifier: Modifier = Modifier
) = this.then(
    modifier.drawBehind {
        this.drawIntoCanvas {
            val paint = Paint()
            val frameworkPaint = paint.asFrameworkPaint()
            val spreadPixel = spread.toPx()
            val leftPixel = (0f - spreadPixel) + offsetX.toPx()
            val topPixel = (0f - spreadPixel) + offsetY.toPx()
            val rightPixel = (this.size.width + spreadPixel)
            val bottomPixel = (this.size.height + spreadPixel)

            if(blurRadius != 0.dp) {
                frameworkPaint.maskFilter = BlurMaskFilter(blurRadius.toPx(), BlurMaskFilter.Blur.NORMAL)
            }
            frameworkPaint.color = color.toArgb()

            it.drawRoundRect(
                left = leftPixel,
                top = topPixel,
                right = rightPixel,
                bottom = bottomPixel,
                radiusX = borderRadius.toPx(),
                radiusY = borderRadius.toPx(),
                paint = paint
            )
        }
    }
)