package com.example.safecar

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentTransaction
import com.example.safecar.model.Marcadores
import com.google.android.gms.location.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.type.LatLng
import java.util.*
import java.util.jar.Manifest
import kotlin.collections.ArrayList
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {

    lateinit var homeFragment: HomeFragment
    //lateinit var mapsFragment: MapsFragment
    lateinit var userFragment: UserFragment
    lateinit var carroFragment: CarroFragment
    lateinit var marcadores: Marcadores
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    lateinit var locationRequest: LocationRequest
    var firebaseUser = FirebaseAuth.getInstance().currentUser
    //private lateinit var auth: FirebaseAuth
    //lateinit var coordenadas: DoubleArray

    //the permission id is just an int that must be unique, any number can be used
    private var PERMISSION_ID = 1000
    private var coordenadas = DoubleArray(2)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Iniciando o fused Provider Client
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        //coordenadas[0] = 37.4219983
        //coordenadas[1] = -122.084
        getLastLocation()
        //FirebaseAuth.getInstance().signOut()
        println("sdfkjhfkjsdghfgoisdgfuigsdiuf")
        println(firebaseUser?.displayName)

        val marcadoresLista =loadMarkers()

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bot_nav)
        //setSupportActionBar(findViewById(R.id.toolbar))
        //supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //if (savedInstanceState != null) {
            homeFragment = HomeFragment()
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.frame_layout, homeFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit()
        //}

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->

            when (item.itemId) {

                R.id.home -> {
                homeFragment = HomeFragment()
                        supportFragmentManager
                        . beginTransaction ()
                    .replace(R.id.frame_layout, homeFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }

                R.id.gps -> {
                    //var args =  Bundle()
                    //args.putDoubleArray("coordenadas",coordenadas)
                    println(Arrays.toString(coordenadas))
                    println("E MESMO ISTO")
                    val fragment = MapsFragment.newInstance(ArrayList(marcadoresLista),coordenadas)
                    println("Vai entrar no fragmento gps")

                    supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.frame_layout, fragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .commit()
                }

                R.id.user -> {

                    userFragment = UserFragment()
                    supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.frame_layout, userFragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .commit()
                }

                R.id.carros -> {
                    carroFragment = CarroFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout, carroFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
            }
            true
        }
    }

    //Verificação de permissões
    private fun CheckPermission():Boolean{
        println("Check permission")
        if(
            ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
                    ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
        ){
            println("True")
            return true
        }
        println("Check false")
        return false
    }
    //Obter permissões
    private fun RequestPermission(){
        println("Request permission")
        ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION),PERMISSION_ID
        )
    }

    //Agora usa-se a função para checkar se o serviço de localização está enabled
    private fun isLocationEnabled():Boolean{

        var locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }

    //esta função dá check nos resultados das permissões
    //usamos isto em debug
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        //this is a build in a function thar check permission result
        if (requestCode == PERMISSION_ID)
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                Log.d("Debug", "You have the permission")
    }

    //Agora criamos a função que permite obter a últioma localização
    private fun getLastLocation(){
        //chek das poermissoes
        if (CheckPermission()){
            print(CheckPermission())
            if (isLocationEnabled()){
                //Get Location
                fusedLocationProviderClient.lastLocation.addOnCompleteListener { task ->
                    var location = task.result
                    if (location == null){
                        getNewLocation()

                }else{
                    //Show loacation
                        println("AQUIIII")
                        println("Coordenadas atuais são:\n" + location.latitude + "," + location.longitude)
                        Toast.makeText(this, "Coordenadas atuais são:\n" + location.latitude + "," + location.longitude, Toast.LENGTH_SHORT).show()
                        coordenadas[0] = location.latitude
                        coordenadas[1] = location.longitude
                        println(coordenadas[0])
                    }
                }
            }else{
                println("UIIIIIII")
                Toast.makeText(this, "Please enable location", Toast.LENGTH_SHORT).show()
            }
        }else{
            RequestPermission()
        }
    }


    @SuppressLint("MissingPermission")
    private fun getNewLocation(){
        locationRequest = LocationRequest()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 0
        locationRequest.fastestInterval = 0
        locationRequest.numUpdates = 2
        fusedLocationProviderClient!!.requestLocationUpdates(
            locationRequest,locationCallback, Looper.myLooper()
         //Criar o locartionCallback
        )
    }

    private val locationCallback = object : LocationCallback(){
        override fun onLocationResult(p0: LocationResult?) {
            var lastLocation : Location = p0!!.lastLocation
            coordenadas[0] = lastLocation.latitude
            coordenadas[1] = lastLocation.longitude
            //println(coordenadas[0])
            println("Location Calback, coordenadas sao :\n" + lastLocation.latitude + "," + lastLocation.longitude)
        //Toast.makeText(applicationContext, "Coordenadas atuais são:\n" + lastLocation.latitude + "," + lastLocation.longitude, Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadMarkers(): MutableList<Marcadores> {
        val docs = Firebase.firestore.collection("Oficinas")
        val marcadores = mutableListOf<Marcadores>()
        docs.addSnapshotListener { snapshot, e ->
            for (document in snapshot!!.documents){
                val marcador = Marcadores(
                    "${document.data?.get("nome")}",
                    "${document.data?.get("Latitude")}",
                    "${document.data?.get("Longitude")}"
                )
                marcadores += marcador
            }
        }
        return marcadores
    }

}
