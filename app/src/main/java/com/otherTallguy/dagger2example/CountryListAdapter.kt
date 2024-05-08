package com.otherTallguy.dagger2example


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.otherTallguy.dagger2example.databinding.ItemCountryBinding
import com.otherTallguy.dagger2example.model.Country
import com.otherTallguy.dagger2example.model.Universities
import com.otherTallguy.dagger2example.utils.getProgressDrawable
import com.otherTallguy.dagger2example.utils.loadImage


@SuppressLint("NotifyDataSetChanged")
class CountryListAdapter(
   // private var countries: ArrayList<Country>,
    private var universities: ArrayList<Universities>,
    private val onClickListener: OnClickListener
) :
    RecyclerView.Adapter<CountryListAdapter.CountryViewHolder>() {


    fun updateCountries(newCountries: List<Universities>) {
        /*countries.clear()
        countries.addAll(newCountries)*/
        universities.clear()
        universities.addAll(newCountries)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCountryBinding.inflate(inflater, parent, false)
        return CountryViewHolder(binding)
    }


    override fun getItemCount() = universities.size

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(universities[position])
        holder.itemView.setOnClickListener { onClickListener.onClick(universities[position]) }
    }

  /*  fun filterList(filterList: ArrayList<Country>) {
        countries = filterList
        notifyDataSetChanged()
    }*/

    class CountryViewHolder(private val binding: ItemCountryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val progressDrawable = getProgressDrawable(binding.root.context)

        fun bind(country: Universities) {
            binding.item = country
          //  binding.imageView.loadImage(country.flag, progressDrawable)
        }
    }
}

class OnClickListener(val clickListener: (country: Universities) -> Unit) {
    fun onClick(country: Universities) = clickListener(country)
}