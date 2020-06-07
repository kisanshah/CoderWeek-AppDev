package com.example.mapandfirebase

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = FirebaseAuth.getInstance()

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        getUpdate.setOnClickListener {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                getLocation()
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 44
                )
            }
        }
        signOut.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this, MainLoginPage::class.java))
        }
    }

    @SuppressLint("SetTextI18n")
    private fun getLocation() {
        fusedLocationProviderClient.lastLocation.addOnCompleteListener { task ->
            val location = task.result
            if (location != null) {
                val geocode = Geocoder(this, Locale.getDefault())
                var address: List<Address> =
                    geocode.getFromLocation(location.latitude, location.longitude, 1)

                currentLocation.text =
                    "Latitude : ${address[0].latitude}" +
                            "\nLongitude : ${address[0].longitude}" +
                            "\nCountry : ${address[0].countryName}" +
                            "\nLocale : ${address[0].locale}" +
                            "\nCountryCode : ${address[0].countryCode}" +
                            "\nPostalCode : ${address[0].postalCode}"
            }

        }
    }
}
