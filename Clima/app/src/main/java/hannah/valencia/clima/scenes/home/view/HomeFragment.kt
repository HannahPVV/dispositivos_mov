package hannah.valencia.clima.scenes.home.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import hannah.valencia.clima.databinding.FragmentHomeBinding
import hannah.valencia.clima.scenes.base.BaseActivity
import hannah.valencia.clima.scenes.home.viewModel.HomeViewModel


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.binding = FragmentHomeBinding.inflate(inflater, container, false)
        this.viewModel = HomeViewModel(requireContext(), requireActivity() as BaseActivity)

        return this.binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //
        this.viewModel.name.observe(viewLifecycleOwner) { cityName ->
            this.binding.tvCityName.text = cityName
        }

        // 2. Observamos la lista del clima (RecyclerView)
        this.viewModel.forecastList.observe(viewLifecycleOwner) { lista ->
            this.binding.rvHourlyForecast.adapter = ForecastAdapter(lista)
        }

        // 3. Observamos los detalles (Humedad y Viento)
        this.viewModel.humidity.observe(viewLifecycleOwner) { valor ->
            this.binding.tvHumidityVal.text = valor
        }

        this.viewModel.wind.observe(viewLifecycleOwner) { valor ->
            this.binding.tvWindVal.text = valor
        }

        this.viewModel.uvIndex.observe(viewLifecycleOwner) { valor ->
            this.binding.tvUvVal.text = valor
        }

        this.viewModel.feelsLike.observe(viewLifecycleOwner) { valor ->
            this.binding.tvFeelsLikeVal.text = valor
        }

        this.viewModel.precipitation.observe(viewLifecycleOwner) { valor ->
            this.binding.tvPrecipitationVal.text = valor
        }


    }
}