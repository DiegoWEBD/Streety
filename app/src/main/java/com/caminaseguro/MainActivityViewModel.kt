package com.caminaseguro

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng

class MainActivityViewModel: ViewModel() {

    var locationPermissionGranted = MutableLiveData(false)
    var currentUserLocation = MutableLiveData(LatLng(0.0, 0.0))
}