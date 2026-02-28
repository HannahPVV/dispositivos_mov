package hannah.valencia.clima.scenes.explore.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hannah.valencia.clima.R
import hannah.valencia.clima.databinding.ItemLocationBinding
import hannah.valencia.clima.scenes.explore.model.LocationModel
import hannah.valencia.clima.scenes.explore.model.WeatherType

class ExploreAdapter(
    private var locations: List<LocationModel>
) : RecyclerView.Adapter<ExploreAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemLocationBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemLocationBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val location = locations[position]

        holder.binding.tvCity.text = location.city
        holder.binding.tvTemp.text = "${location.temperature}°C"

        when (location.weather) {
            WeatherType.SUNNY -> {
                holder.binding.tvWeather.text = "Soleado"
                holder.binding.ivWeather.setImageResource(R.drawable.ic_sunny)
            }
            WeatherType.CLOUDY -> {
                holder.binding.tvWeather.text = "Nublado"
                holder.binding.ivWeather.setImageResource(R.drawable.ic_cloudy)
            }
            WeatherType.RAINY -> {
                holder.binding.tvWeather.text = "Lluvia"
                holder.binding.ivWeather.setImageResource(R.drawable.ic_rain)
            }
            WeatherType.SNOWY -> {
                holder.binding.tvWeather.text = "Nevado"
                holder.binding.ivWeather.setImageResource(R.drawable.ic_snow)
            }
        }
    }

    override fun getItemCount() = locations.size

    fun updateData(newList: List<LocationModel>) {
        locations = newList
        notifyDataSetChanged()
    }
}