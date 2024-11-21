package com.example.schedacibo.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.schedacibo.R
import com.example.schedacibo.databinding.FragmentInfoBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class InfoFragment : Fragment(), OnMapReadyCallback {

    private var _binding: FragmentInfoBinding? = null
    private val binding get() = _binding!!

    private lateinit var mMap: GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inizializza il binding
        _binding = FragmentInfoBinding.inflate(inflater, container, false)

        // Inizializza il SupportMapFragment
        val mapFragment =
            childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        return binding.root
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Aggiungi un marker e sposta la telecamera
        val location = LatLng(-34.0, 151.0) // Sostituisci con la tua posizione desiderata
        mMap.addMarker(MarkerOptions().position(location).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 10f))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Libera il binding
    }
}