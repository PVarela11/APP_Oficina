package com.example.safecar

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.safecar.model.Marcadores
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.util.*
import kotlin.collections.ArrayList


private const val ARG_PARAM1 = "list"

private lateinit var listaMarcadores: ArrayList<Marcadores>
private lateinit var listaLat: MutableList<Double>
private lateinit var listaLng: MutableList<Double>
private lateinit var listaTitle: MutableList<String>
private lateinit var googleMap: GoogleMap
private lateinit var local: LatLng
var count: Int = 0

class MapsFragment : Fragment() {

    private var list: ArrayList<out Parcelable>? = null
    private var coordenadas = DoubleArray(2)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       // putParcelableArrayList(ARG_PARAM1, param1)
       // putDoubleArray(teste,param2)
        arguments?.let {
            list = it.getParcelableArrayList(ARG_PARAM1)
            listaMarcadores = list as ArrayList<Marcadores>
            coordenadas = it.getDoubleArray("meu_teste")!!
            local = LatLng(coordenadas[0],coordenadas[1])
            //var coor = it.getDoubleArray(ARG_PARAM1)
        }
        println("fksdhgfjkgsdjylfglsdbf")
        println(local)
        //println("AKJGFSKHDGFSDGFIJGSKUFGJKSHDFGJHSDFGHSDJF")
        //println(coor)
        var listaLat: MutableList<Double> = ArrayList()
        var listaLng: MutableList<Double> = ArrayList()
        var listaTitle: MutableList<String> = ArrayList()
        //println("Aqui")

        listaMarcadores.forEach {
            listaLat.add(it.lat.toDouble())
            listaLng.add(it.long.toDouble())
            listaTitle.add(it.title)
        }
        //println("Saiu")
        //println(listaLat)
        //println(listaLng)
        //println(listaTitle)

        // Construct a PlaceDetectionClient.
        //mPlaceDetectionClient = Places.getPlaceDetectionClient(this, null);
        //setUpMapIfNeeded()
        }

    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */

        //googleMap.setMyLocationEnabled(true);
        println("oi")
        println(listaMarcadores)
        listaMarcadores.forEach {
            //listaLat.add(it.lat.toDouble())
            //listaLng.add(it.long.toDouble())
            //listaTitle.add(it.title)
            val loc = LatLng(it.lat.toDouble(), it.long.toDouble())
            googleMap.addMarker(MarkerOptions().position(loc).title(it.title))
            //googleMap.moveCamera(CameraUpdateFactory.newLatLng(loc))
        }
        googleMap.addMarker(
                MarkerOptions()
                        .position(local)
                        .title("Localização atual")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
        )
        googleMap.setMaxZoomPreference(25.0f)
        googleMap.setMinZoomPreference(5.0f)
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(local))
        //val sydney = LatLng(-34.0, 151.0)
        //val latLngs: List<LatLng> = placeList.map { LatLng(-34.0, 151.0)}
        //googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))

        //googleMap.moveCamera(CameraUpdateFactory.newLatLng(loc))
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MapsFragment.
         */
        // TODO: Rename and change types and number of parameters

        private var teste = "meu_teste"

        @JvmStatic
        fun newInstance(param1: ArrayList<out Parcelable>, param2: DoubleArray) =
            MapsFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(ARG_PARAM1, param1)
                    putDoubleArray(teste,param2)
                }
                println(arguments)
            }
    }

}