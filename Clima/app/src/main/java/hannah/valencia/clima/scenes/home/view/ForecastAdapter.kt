package hannah.valencia.clima.scenes.home.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hannah.valencia.clima.databinding.ItemForecastBinding
import hannah.valencia.clima.scenes.home.model.ForecastModel

class ForecastAdapter(private val forecastList: List<ForecastModel>) :
    RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder>() {

    // Usamos el Binding del archivo item_forecast.xml
    class ForecastViewHolder(val binding: ItemForecastBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val binding = ItemForecastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ForecastViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        val item = forecastList[position]
        holder.binding.tvHourItem.text = item.hour
        holder.binding.tvTempItem.text = "${item.temp}°"
        holder.binding.ivWeatherIcon.setImageResource(item.iconRes)
    }

    override fun getItemCount(): Int = forecastList.size
}