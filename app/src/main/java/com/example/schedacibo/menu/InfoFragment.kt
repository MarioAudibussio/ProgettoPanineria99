package com.example.schedacibo.menu

import android.content.Intent
import android.net.Uri
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
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Bottone per aprire Instagram
        binding.btnInstagram.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://www.instagram.com") // URL di Instagram
            startActivity(intent)
        }

        // Bottone per aprire Facebook
        binding.btnFacebook.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://www.facebook.com") // URL di Facebook
            startActivity(intent)
        }

        // Bottone per effettuare una chiamata
        binding.btnCall.setOnClickListener {
            val phoneNumber = "3356625912" // Numero di telefono
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:$phoneNumber")
            startActivity(intent)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inizializza il binding
        _binding = FragmentInfoBinding.inflate(inflater, container, false)

        // Inizializza il SupportMapFragment
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        return binding.root
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Aggiungi un marker e sposta la telecamera
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