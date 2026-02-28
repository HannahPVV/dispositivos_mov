package hannah.valencia.clima.scenes.explore.view

import android.app.AlertDialog
import android.os.Bundle
import android.text.InputType
import android.text.method.TextKeyListener
import android.view.View
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import hannah.valencia.clima.R
import hannah.valencia.clima.databinding.FragmentExploreBinding
import hannah.valencia.clima.scenes.explore.adapter.ExploreAdapter
import hannah.valencia.clima.scenes.explore.viewModel.ExploreViewModel

class ExploreFragment : Fragment(R.layout.fragment_explore) {

    private lateinit var binding: FragmentExploreBinding
    private val viewModel: ExploreViewModel by viewModels()
    private lateinit var adapter: ExploreAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentExploreBinding.bind(view)

        // Adapter simple (solo mostrar)
        adapter = ExploreAdapter(emptyList())

        // RecyclerView
        binding.recyclerLocations.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@ExploreFragment.adapter
        }

        // Botón +
        binding.btnAddLocation.setOnClickListener {
            showAddLocationDialog()
        }

        // Observer
        viewModel.locations.observe(viewLifecycleOwner) { locations ->
            adapter.updateData(locations)
        }
    }

    private fun showAddLocationDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_location, null)
        val etCity = dialogView.findViewById<EditText>(R.id.etCity)

        // Permitir acentos y ñ
        etCity.inputType = InputType.TYPE_CLASS_TEXT
        etCity.keyListener =
            TextKeyListener.getInstance(true, TextKeyListener.Capitalize.WORDS)

        AlertDialog.Builder(requireContext())
            .setTitle("Agregar ubicación")
            .setView(dialogView)
            .setPositiveButton("Agregar") { _, _ ->
                val cityName = etCity.text.toString().trim()
                if (cityName.isNotEmpty()) {
                    viewModel.addLocation(cityName)
                }
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }
}