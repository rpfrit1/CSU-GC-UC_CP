@file:Suppress("DEPRECATION")

package edu.csuglobal.csc475.gpsTracking


import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.IntentSender.SendIntentException
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.LocationSettingsStatusCodes
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MainActivity : AppCompatActivity() {

    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback
    private lateinit var txtLong:TextView
    private lateinit var txtLat:TextView
    private lateinit var btnStart:Button
    private lateinit var mapShow:SupportMapFragment
    private val places = ArrayList<Place>(25)

    private fun isGPSEnabled(): Boolean {
        val locationManager = getSystemService(LOCATION_SERVICE) as android.location.LocationManager
        return locationManager.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER)
    }//end isGPSEnabled()

    private fun turnOnGPS() {
        val builder = LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest)
        builder.setAlwaysShow(true)

        val result = LocationServices.getSettingsClient(
            applicationContext
        )
            .checkLocationSettings(builder.build())

        result.addOnCompleteListener { task ->
            try {
                task.getResult(ApiException::class.java)
                Toast.makeText(this@MainActivity, "GPS is already turned on", Toast.LENGTH_SHORT)
                    .show()
            }//end try
            catch (e: ApiException) {
                when (e.statusCode) {
                    LocationSettingsStatusCodes.RESOLUTION_REQUIRED -> try {
                        val resolvableApiException = e as ResolvableApiException
                        resolvableApiException.startResolutionForResult(this@MainActivity, 2)
                    }//end try
                    catch (ex: SendIntentException) {
                        ex.printStackTrace()
                    }//end catch

                    LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE -> {}
                }//end when
            }//end catch
        }//end addOnCompleteListener()

    }//end turnOnGPS()
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtLong = findViewById(R.id.txtLong)
        txtLat = findViewById(R.id.txtLat)
        btnStart =  findViewById(R.id.btnStart)
        mapShow = (supportFragmentManager.findFragmentById(R.id.mapShow) as SupportMapFragment)
        mapShow.getMapAsync { googleMap ->
            addMarkers(googleMap)
        }//end getMapAsync function

        locationRequest = LocationRequest.create().setPriority(Priority.PRIORITY_HIGH_ACCURACY)
            .setInterval(10000).setFastestInterval(5000)
        btnStart.setOnClickListener {
            getCurrentLocation()
            }//end setOnClickListener()

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                super.onLocationResult(locationResult)
                val index = locationResult.locations.size - 1
                txtLat.text = locationResult.locations[index].latitude.toString()
                txtLong.text = locationResult.locations[index].longitude.toString()

                //add locationResult to places
                val location = LatLng(
                    locationResult.locations[index].latitude,
                    locationResult.locations[index].longitude
                )
                //add to mapShow
                mapShow.getMapAsync { googleMap ->
                    googleMap.addMarker(
                        MarkerOptions()
                            .position(location)
                    )
                    places.add(Place(location, location))
                    addMarkers(googleMap)
                    googleMap.moveCamera(CameraUpdateFactory.newLatLng(location))
                    googleMap.animateCamera(CameraUpdateFactory.zoomIn())
                    googleMap.animateCamera(
                        CameraUpdateFactory.zoomTo(15f),
                        2000,
                        null
                    )
                    //enanble zoom controls
                    googleMap.uiSettings.isZoomControlsEnabled = true
                }//end getMapAsync function
            }//end onLocationResult()
        }//end locationCallback object

        //create a listener for the LocationCallback object
        LocationServices.getFusedLocationProviderClient(this@MainActivity)
            .requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper())

        //create a listener for the btnStart button
        btnStart.setOnClickListener {
            getCurrentLocation()
        }
    }//end onCreate()
    private fun addMarkers(googleMap: GoogleMap) {
        places.forEach { place ->
            googleMap.addMarker(
                MarkerOptions()
                    .position(place.latLng).title(place.name)
            )
        }//end forEach loop
    }//end addMarkers function
    @RequiresApi(Build.VERSION_CODES.M)
    private fun getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (isGPSEnabled()) {
                LocationServices.getFusedLocationProviderClient(this@MainActivity)
                    .requestLocationUpdates(locationRequest, object : LocationCallback() {
                        override fun onLocationResult(locationResult: LocationResult) {
                            super.onLocationResult(locationResult)
                            LocationServices.getFusedLocationProviderClient(this@MainActivity)
                                .removeLocationUpdates(this)
                            if (locationResult.locations.size > 0) {
                                val index = locationResult.locations.size - 1
                                txtLat.text = locationResult.locations[index].latitude.toString()
                                txtLong.text = locationResult.locations[index].longitude.toString()

                                //add locationResult to places
                                val location = LatLng(
                                    locationResult.locations[index].latitude,
                                    locationResult.locations[index].longitude
                                )
                                //add to mapShow
                                mapShow.getMapAsync { googleMap ->
                                    googleMap.addMarker(
                                        MarkerOptions()
                                            .position(location)
                                    )
                                    places.add(Place(location, location))
                                    addMarkers(googleMap)
                                    googleMap.moveCamera(CameraUpdateFactory.newLatLng(location))
                                    googleMap.animateCamera(CameraUpdateFactory.zoomIn())
                                    googleMap.animateCamera(
                                        CameraUpdateFactory.zoomTo(15f),
                                        2000,
                                        null
                                    )
                                }//end getMapAsync function
                            }//end if
                        }//end onLocationResult()
                    }/*end requestLocationUpdates*/, Looper.getMainLooper())
            }//end if
            else {
                turnOnGPS()
            }//end else
        }//end if
        else {
            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1)
        }//end else
        //end if
    }//end getLocation()


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if(isGPSEnabled()) {
                    getCurrentLocation()
                }//end if
                else {
                    turnOnGPS()
                }//end else
            }//end if
        }//end if
    }//end onRequestPermissionsResult()

    @RequiresApi(Build.VERSION_CODES.M)
    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 2) {
            if(resultCode == Activity.RESULT_OK) {
                getCurrentLocation()
            }//end if
        }//end if
    }//end onActivityResult()
    data class Place(
        val latLng: LatLng,
        val address: LatLng
    ) {
        val name: String

        init {
            //set name to the time stamp
            name = System.currentTimeMillis().toString()
        }//end init function
    }//end Place class
}//end MainActivity



