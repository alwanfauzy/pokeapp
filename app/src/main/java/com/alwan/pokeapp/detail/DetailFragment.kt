package com.alwan.pokeapp.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alwan.pokeapp.R
import com.alwan.pokeapp.core.domain.model.Pokemon
import com.alwan.pokeapp.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val extraData =
            requireActivity().intent.getParcelableExtra<Pokemon>(DetailActivity.EXTRA_DATA)
        extraData?.let { populateDetail(it) }
    }

    private fun populateDetail(pokemon: Pokemon) {
        with(binding){
            tvHeightDetail.text = getString(R.string.height, pokemon.height)
            tvWeightDetail.text = getString(R.string.weight, pokemon.weight)
            tvAbilitiesDetail.text = getString(R.string.abilities, pokemon.abilities)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}