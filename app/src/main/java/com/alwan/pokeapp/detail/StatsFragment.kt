package com.alwan.pokeapp.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alwan.pokeapp.core.domain.model.Pokemon
import com.alwan.pokeapp.core.util.animateTo
import com.alwan.pokeapp.core.util.setBigMax
import com.alwan.pokeapp.databinding.FragmentStatsBinding

class StatsFragment : Fragment() {
    private var _binding: FragmentStatsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStatsBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val extraData =
            requireActivity().intent.getParcelableExtra<Pokemon>(DetailActivity.EXTRA_DATA)
        extraData?.let { populateStats(it) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun populateStats(pokemon: Pokemon) {
        with(binding) {
            setProgressBarBigMax(progressBarHp.max)
            pokemon.statHp?.let {
                progressBarHp.progress = it
                progressBarHp.animateTo(it, PROGRESS_DELAY)
            }
            pokemon.statAttack?.let {
                progressBarAttack.progress = it
                progressBarAttack.animateTo(it, PROGRESS_DELAY)
            }
            pokemon.statDefense?.let {
                progressBarDefense.progress = it
                progressBarDefense.animateTo(it, PROGRESS_DELAY)
            }
            pokemon.statSpecialAttack?.let {
                progressBarSpecialAttack.progress = it
                progressBarSpecialAttack.animateTo(it, PROGRESS_DELAY)
            }
            pokemon.statSpecialDefense?.let {
                progressBarSpecialDefense.progress = it
                progressBarSpecialDefense.animateTo(it, PROGRESS_DELAY)
            }
            pokemon.statSpeed?.let {
                progressBarSpeed.progress = it
                progressBarSpeed.animateTo(it, PROGRESS_DELAY)
            }
        }
    }

    private fun setProgressBarBigMax(max : Int) {
        with(binding) {
            progressBarHp.setBigMax(max)
            progressBarAttack.setBigMax(max)
            progressBarDefense.setBigMax(max)
            progressBarSpecialAttack.setBigMax(max)
            progressBarSpecialDefense.setBigMax(max)
            progressBarSpeed.setBigMax(max)
        }
    }

    companion object{
        private const val PROGRESS_DELAY = 500L
    }
}