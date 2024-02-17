package edu.csuglobal.csc475.gpstracker

import android.annotation.SuppressLint
import android.location.Location
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.Toast.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import java.util.concurrent.TimeUnit


@SuppressLint("MissingPermission")
class MainActivity : AppCompatActivity() {
    private val locations = mutableListOf<Location>()
    private val adapter = LocationAdapter(locations)

    // FusedLocationProviderClient - Main class for receiving location updates.
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    // LocationRequest - Requirements for the location updates, i.e., how often you
    // should receive updates, the priority, etc.
    private lateinit var locationRequest: LocationRequest

    // LocationCallback - Called when FusedLocationProviderClient has a new Location.
    private lateinit var locationCallback: LocationCallback
    // Called when the activity is first created.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val btnNav = findViewById<Button>(R.id.btnNav)
        val layRecycle = findViewById<RecyclerView>(R.id.layRecycle)
        layRecycle.adapter = adapter
        btnNav.setOnClickListener {
            //clear layRecycle
            locations.clear()
        }//end btnNav setOnClickListener function

        setContentView(R.layout.activity_main)
    }//end onCreate function

    init {
        try {

            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        locationRequest = LocationRequest.create().apply {
            interval = TimeUnit.SECONDS.toMillis(60)
            fastestInterval = TimeUnit.SECONDS.toMillis(30)
            maxWaitTime = TimeUnit.MINUTES.toMillis(2)
            priority = Priority.PRIORITY_HIGH_ACCURACY
        }//end locationRequest apply function

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                super.onLocationResult(locationResult)

                // Normally, you want to save a new location to a database. We are simplifying
                // things a bit and just saving it as a local variable, as we only need it again
                // if a Notification is created (when the user navigates away from app).
                locationResult.lastLocation?.let { locations.add(it) }

                //update the layRecycle
                adapter.notifyDataSetChanged()
            }//end onLocationResult function
        }//end locationCallback object
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper())
        }//end try
        catch (e: Exception) {
            e.message?.let { Log.e("GPSTracker", it) }//end e.message?.let function
        }//end catch
    }//end init function

}//end MainActivity class

