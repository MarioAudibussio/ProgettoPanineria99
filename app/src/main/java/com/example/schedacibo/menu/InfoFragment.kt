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

        _binding = FragmentInfoBinding.inflate(inflater, container, false)


        val mapFragment =
            childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        return binding.root
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap


        val location = LatLng(36.786388, 14.907449)
        mMap.addMarker(MarkerOptions().position(location).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 10f))
        mMap.uiSettings.isZoomControlsEnabled = true;
        mMap.setMinZoomPreference(20.0f);
        mMap.uiSettings.isCompassEnabled = true;
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Libera il binding
    }
}