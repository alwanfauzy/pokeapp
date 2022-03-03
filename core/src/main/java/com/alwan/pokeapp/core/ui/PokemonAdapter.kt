package com.alwan.pokeapp.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alwan.pokeapp.core.databinding.ItemPokemonBinding
import com.alwan.pokeapp.core.domain.model.Pokemon
import com.alwan.pokeapp.core.util.loadImage

class PokemonAdapter(private val callback: PokemonCallback) :
    RecyclerView.Adapter<PokemonAdapter.PokemonViewholder>() {
    private val mData = ArrayList<Pokemon>()

    fun setData(data: ArrayList<Pokemon>) {
        mData.clear()
        mData.addAll(data)
        notifyDataSetChanged()
    }

    interface PokemonCallback {
        fun onPokemonClick(pokemon: Pokemon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PokemonViewholder(
            ItemPokemonBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: PokemonViewholder, position: Int) =
        holder.bind(mData[position])

    override fun getItemCount(): Int = mData.count()

    inner class PokemonViewholder(private val binding: ItemPokemonBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(pokemon: Pokemon) {
            with(binding) {
                tvPokemonItem.text = pokemon.name
                imgPokemonItem.loadImage(pokemon.frontSprite)
                root.setOnClickListener { callback.onPokemonClick(pokemon) }
            }
        }
    }
}