package com.example.parkedcarfinder

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.location.Location
import android.os.Bundle
import android.view.View
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

private const val PERMISSION_CODE_REQUEST_LOCATION = 1

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {
    private val markLocationButton: View
        by lazy { findViewById(R.id.maps_mark_location_button) }

    private val fusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(this)
    }

    private lateinit var mMap: GoogleMap

    private var userMarker: Marker? = null
    private var carMarker: Marker? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        markLocationButton.setOnClickListener {
            if (getHasLocationPermission()) {
                markParkedCar()
            }
        }
    }

    override fun onResume() {
        super.onResume()

        val hasLocationPermissions = getHasLocationPermission()
        if (hasLocationPermissions) {
            getLastLocation { location ->
                val userLocation = LatLng(location.latitude, location.longitude)
                updateMapLocation(userLocation)
                userMarker = addMarkerAtLocation(userLocation, "You")
            }
        }
    }

    private fun getHasLocationPermission() = if (
        ContextCompat.checkSelfPermission(
            this, Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    ) {
        true
    } else {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this, Manifest.permission.ACCESS_FINE_LOCATION
            )
        ) {
            showPermissionRationale { requestLocationPermission() }
        } else {
            requestLocationPermission()
        }
        false
    }

    private fun showPermissionRationale(positiveAction: () -> Unit) {
        AlertDialog.Builder(this)
            .setTitle("Location permission")
            .setMessage("We need your permission to find your current location")
            .setPositiveButton(
                "OK"
            ) { _, _ -> positiveAction() }
            .create()
            .show()
    }

    private fun requestLocationPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            PERMISSION_CODE_REQUEST_LOCATION
        )
    }

    @SuppressLint("MissingPermission")
    private fun getLastLocation(onLocation: (location: Location) -> Unit) {
        fusedLocationProviderClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                location?.let {
                    onLocation(it)
                }
            }
    }

    private fun updateMapLocation(location: LatLng) {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 7f))
    }

    private fun addMarkerAtLocation(
        location: LatLng,
        title: String,
        markerIcon: BitmapDescriptor? = null
    ) = mMap.addMarker(
        MarkerOptions()
            .title(title)
            .position(location)
            .apply {
                markerIcon?.let { icon(markerIcon) }
            }
    )

    private fun markParkedCar() {
        getLastLocation { location ->
            val userLocation = LatLng(location.latitude, location.longitude)
            userMarker?.remove()
            carMarker?.remove()
            updateMapLocation(userLocation)
            carMarker = addMarkerAtLocation(
                userLocation,
                "Your Car",
                getBitmapDescriptorFromVector(R.drawable.ic_baseline_directions_car_24)
            )
            userMarker = addMarkerAtLocation(userLocation, "You")
            saveLocation(userLocation)
        }
    }

    private fun getBitmapDescriptorFromVector(@DrawableRes vectorDrawableResourceId: Int): BitmapDescriptor? {
        val bitmap =
            ContextCompat.getDrawable(this, vectorDrawableResourceId)?.let { vectorDrawable ->
                vectorDrawable
                    .setBounds(0, 0, vectorDrawable.intrinsicWidth, vectorDrawable.intrinsicHeight)

                val drawableWithTint = DrawableCompat.wrap(vectorDrawable)
                DrawableCompat.setTint(drawableWithTint, Color.RED)

                val bitmap = Bitmap.createBitmap(
                    vectorDrawable.intrinsicWidth,
                    vectorDrawable.intrinsicHeight,
                    Bitmap.Config.ARGB_8888
                )
                val canvas = Canvas(bitmap)
                drawableWithTint.draw(canvas)
                bitmap
            } ?: return null
        return BitmapDescriptorFactory.fromBitmap(bitmap).also {
            bitmap.recycle()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            PERMISSION_CODE_REQUEST_LOCATION -> getLastLocation { location ->
                val userLocation = LatLng(location.latitude, location.longitude)
                updateMapLocation(userLocation)
                userMarker = addMarkerAtLocation(userLocation, "You")
            }
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))

        restoreLocation()?.let { userLocation ->
            carMarker = addMarkerAtLocation(
                userLocation,
                "Your Car",
                getBitmapDescriptorFromVector(R.drawable.ic_baseline_directions_car_24)
            )
            userMarker = addMarkerAtLocation(userLocation, "You")
        }
    }

    private fun saveLocation(latLng: LatLng) =
        getPreferences(MODE_PRIVATE)?.edit()?.apply {
            putString("latitude", latLng.latitude.toString())
            putString("longitude", latLng.longitude.toString())
            apply()
        }

    private fun restoreLocation() =
        getPreferences(Context.MODE_PRIVATE)?.let { sharedPreferences ->
            val latitude =
                sharedPreferences.getString("latitude", null)?.toDoubleOrNull() ?: return null
            val longitude =
                sharedPreferences.getString("longitude", null)?.toDoubleOrNull() ?: return null
            LatLng(latitude, longitude)
        }
}
