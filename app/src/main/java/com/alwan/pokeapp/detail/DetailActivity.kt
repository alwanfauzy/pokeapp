package com.alwan.pokeapp.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import com.alwan.pokeapp.R
import com.alwan.pokeapp.core.domain.model.Pokemon
import com.alwan.pokeapp.core.util.loadImage
import com.alwan.pokeapp.databinding.ActivityDetailBinding
import com.google.android.material.tabs.TabLayoutMediator

class DetailActivity : AppCompatActivity(), View.OnClickListener {
    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extraData = intent.getParcelableExtra<Pokemon>(EXTRA_DATA)
        extraData?.let { populateDetail(it) }
        binding.imgBackDetail.setOnClickListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun populateDetail(pokemon: Pokemon) {
        with(binding) {
            imgPokemonDetail.loadImage(pokemon.frontSprite)
            tvPokemonDetail.text = pokemon.name
        }
        setupViewPager()
    }

    private fun setupViewPager() {
        val pagerAdapter = DetailPagerAdapter(this)
        with(binding) {
            viewPagerDetail.adapter = pagerAdapter
            TabLayoutMediator(tabsDetail, viewPagerDetail) { tab, position ->
                tab.text = getString(TAB_TITLES[position])
            }.attach()
        }
    }

    companion object {
        const val EXTRA_DATA = "extra_data"

        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.stats,
            R.string.detail
        )
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.imgBackDetail -> finish()
        }
    }
}