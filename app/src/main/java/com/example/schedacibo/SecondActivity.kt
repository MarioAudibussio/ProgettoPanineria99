package com.example.schedacibo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.schedacibo.databinding.ActivitySecondBinding
import com.example.schedacibo.topMenu.BibiteFragment
import com.example.schedacibo.topMenu.FrittoFragment
import com.example.schedacibo.topMenu.HamburgerSpecialiFragment
import com.example.schedacibo.topMenu.PaniniFragment
import com.example.schedacibo.topMenu.VaschetteFragment
import com.google.android.material.tabs.TabLayout

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.firebase.ui.database.FirebaseRecyclerOptions
import android.text.Editable
import android.text.TextWatcher
import com.example.schedacibo.Adapter.BibiteAdapter
import com.example.schedacibo.Adapter.FrittoAdapter
import com.example.schedacibo.Adapter.PaniniAdapter
import com.example.schedacibo.Adapter.VaschetteAdapter
import com.example.schedacibo.DataClass.Bibite
import com.example.schedacibo.DataClass.Fritti
import com.example.schedacibo.DataClass.Panini
import com.example.schedacibo.DataClass.Vaschette
import com.example.schedacibo.DetailActivity.BibiteDetailActivity
import com.example.schedacibo.DetailActivity.FrittiDetailActivity
import com.example.schedacibo.DetailActivity.ProductDetailActivity
import com.example.schedacibo.DetailActivity.VaschetteDetailActivity

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding
    private var lastSelectedTabPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("SecondActivity", "Binding creato con successo")
        val selectedTab = intent.getStringExtra("SELECTED_TAB")

        // Seleziona il tab corretto in base all'intent
        val tabPosition = when (selectedTab) {
            "panini" -> 0
            "fritti" -> 1
            "bibite" -> 2
            "hamburgerSpeciali" -> 4
            "vaschette" -> 3
            else -> lastSelectedTabPosition
        }
        binding.tablayout.getTabAt(tabPosition)?.select()
        lastSelectedTabPosition = tabPosition

        // Carica il contenuto iniziale per il tab selezionato
        handleTabSelection(tabPosition)

        // Configura il pulsante indietro
        binding.backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
        }

        // Quando premi il bottone lente apre un input text
        binding.search.setOnClickListener {
            EditTextVisibility()
        }

        // Configura i listener del TabLayout
        binding.tablayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                Log.d("SecondActivity", "Tab selezionato: ${tab.position}")
                lastSelectedTabPosition = tab.position
                handleTabSelection(tab.position)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    private fun EditTextVisibility() {
        if (binding.editText.visibility == View.VISIBLE) {
            // Nascondi l'EditText e rendilo non editabile
            binding.editText.visibility = View.GONE
            binding.editText.isEnabled = false
            binding.editText.setText("") // Clear the search text
            hideKeyboard()
            // Reload the original list
            handleTabSelection(lastSelectedTabPosition)
        } else {
            // Mostra l'EditText e rendilo editabile
            binding.editText.visibility = View.VISIBLE
            binding.editText.isEnabled = true
            binding.editText.isFocusableInTouchMode = true
            binding.editText.requestFocus()
            showKeyboard()

            // Setup search functionality
            setupSearchFunctionality()
        }
    }

    private fun showKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(binding.editText, InputMethodManager.SHOW_IMPLICIT)
    }

    private fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.editText.windowToken, 0)
    }

    private fun handleTabSelection(tabPosition: Int) {
        when (tabPosition) {
            0 -> {
                binding.appendableContentContainer.visibility = View.VISIBLE
                replaceAppendableFragment(PaniniFragment())
            }

            1 -> {
                binding.appendableContentContainer.visibility = View.VISIBLE
                replaceAppendableFragment(FrittoFragment())
            }

            2 -> {
                binding.appendableContentContainer.visibility = View.VISIBLE
                replaceAppendableFragment(BibiteFragment())
            }

            3 -> {
                binding.appendableContentContainer.visibility = View.VISIBLE
                replaceAppendableFragment(HamburgerSpecialiFragment())
            }

            4 -> {
                binding.appendableContentContainer.visibility = View.VISIBLE
                replaceAppendableFragment(VaschetteFragment())
            }

        }
    }

    private fun replaceAppendableFragment(fragment: Fragment) {
        // Rimuovi frammento precedente dal contenitore appendibile
        val currentFragment =
            supportFragmentManager.findFragmentById(binding.appendableContentContainer.id)
        currentFragment?.let {
            supportFragmentManager.beginTransaction().remove(it).commit()
        }

        // Aggiungi nuovo frammento al contenitore appendibile
        supportFragmentManager.beginTransaction()
            .replace(binding.appendableContentContainer.id, fragment)
            .commit()
        Log.d(
            "SecondActivity",
            "Appendable fragment sostituito: ${fragment::class.java.simpleName}"
        )
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.frameLayout.id, fragment)
            .commit()
        Log.d("SecondActivity", "Fragment transaction committed")
    }


    //parte per il sorting dei prodotti -SETUP
    private fun setupSearchFunctionality() {
        binding.editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                searchProducts(s.toString().trim())
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun searchProducts(searchText: String) {

        val currentFragment = supportFragmentManager.findFragmentById(binding.appendableContentContainer.id)

        when (currentFragment) {
            is PaniniFragment -> searchPanini(searchText)
            is FrittoFragment -> searchFritti(searchText)
            is BibiteFragment -> searchBibite(searchText)
            is VaschetteFragment -> searchVaschette(searchText)
        }
    }
    //search specifico
    private fun searchPanini(searchText: String) {
        val currentFragment = supportFragmentManager.findFragmentById(binding.appendableContentContainer.id) as? PaniniFragment

        if (currentFragment == null) {
            Log.e("SecondActivity", "Current fragment is not PaniniFragment")
            return
        }

        val reference = FirebaseDatabase.getInstance().reference.child("panini")

        reference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val paniniList = snapshot.children
                    .mapNotNull { it.getValue(Panini::class.java) }
                    .filter { panini ->
                        panini.nome?.contains(searchText, ignoreCase = true) == true
                    }

                // If search text is empty, show all items
                val filteredList = if (searchText.isEmpty()) {
                    snapshot.children.mapNotNull { it.getValue(Panini::class.java) }
                } else {
                    paniniList
                }


                val adapter = PaniniAdapter(paniniList) { selectedPanini ->
                    // Handle item click if needed
                    ProductDetailActivity.startActivity(currentFragment.requireActivity() as AppCompatActivity, selectedPanini)
                }

                currentFragment.updateAdapter(adapter)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("SecondActivity", "Error searching panini", error.toException())
            }
        })
    }

    private fun searchFritti(searchText: String) {
        val currentFragment = supportFragmentManager.findFragmentById(binding.appendableContentContainer.id) as? FrittoFragment

        if (currentFragment == null) {
            Log.e("SecondActivity", "Current fragment is not FrittiFragment")
            return
        }

        val reference = FirebaseDatabase.getInstance().reference.child("fritti")

        reference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val frittiList = snapshot.children
                    .mapNotNull { it.getValue(Fritti::class.java) }
                    .filter { fritti ->
                        fritti.nome?.contains(searchText, ignoreCase = true) == true
                    }

                // If search text is empty, show all items
                val filteredList = if (searchText.isEmpty()) {
                    snapshot.children.mapNotNull { it.getValue(Fritti::class.java) }
                } else {
                    frittiList
                }

                val adapter = FrittoAdapter(frittiList) { selectedFritti ->
                    // Handle item click if needed
                    FrittiDetailActivity.startActivity(currentFragment.requireActivity() as AppCompatActivity, selectedFritti)
                }

                currentFragment.updateAdapter(adapter)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("SecondActivity", "Error searching fritti", error.toException())
            }
        })
    }

    private fun searchBibite(searchText: String) {
        val currentFragment = supportFragmentManager.findFragmentById(binding.appendableContentContainer.id) as? BibiteFragment

        if (currentFragment == null) {
            Log.e("SecondActivity", "Current fragment is not BibiteFragment")
            return
        }

        val reference = FirebaseDatabase.getInstance().reference.child("bibite")

        reference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val bibiteList = snapshot.children
                    .mapNotNull { it.getValue(Bibite::class.java) }
                    .filter { bibita ->
                        bibita.nome?.contains(searchText, ignoreCase = true) == true
                    }

                // If search text is empty, show all items
                val filteredList = if (searchText.isEmpty()) {
                    snapshot.children.mapNotNull { it.getValue(Bibite::class.java) }
                } else {
                    bibiteList
                }

                val adapter = BibiteAdapter(bibiteList) { selectedBibita ->
                    // Handle item click if needed
                    BibiteDetailActivity.startActivity(currentFragment.requireActivity() as AppCompatActivity, selectedBibita)
                }

                currentFragment.updateAdapter(adapter)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("SecondActivity", "Error searching bibite", error.toException())
            }
        })
    }

    private fun searchVaschette(searchText: String) {
        val currentFragment = supportFragmentManager.findFragmentById(binding.appendableContentContainer.id) as? VaschetteFragment

        if (currentFragment == null) {
            Log.e("SecondActivity", "Current fragment is not VaschetteFragment")
            return
        }

        val reference = FirebaseDatabase.getInstance().reference.child("vaschette")

        reference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val vaschetteList = snapshot.children
                    .mapNotNull { it.getValue(Vaschette::class.java) }
                    .filter { vaschetta ->
                        vaschetta.nome?.contains(searchText, ignoreCase = true) == true
                    }

                // If search text is empty, show all items
                val filteredList = if (searchText.isEmpty()) {
                    snapshot.children.mapNotNull { it.getValue(Vaschette::class.java) }
                } else {
                    vaschetteList
                }

                val adapter = VaschetteAdapter(vaschetteList) { selectedVaschetta ->
                    // Handle item click if needed
                    VaschetteDetailActivity.startActivity(currentFragment.requireActivity() as AppCompatActivity, selectedVaschetta)
                }

                currentFragment.updateAdapter(adapter)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("SecondActivity", "Error searching vaschette", error.toException())
            }
        })
    }


}
