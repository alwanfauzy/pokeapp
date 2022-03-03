package com.alwan.pokeapp.main

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.alwan.pokeapp.core.data.Resource
import com.alwan.pokeapp.core.domain.model.Pokemon
import com.alwan.pokeapp.core.ui.PokemonAdapter
import com.alwan.pokeapp.core.util.GridMarginItemDecoration
import com.alwan.pokeapp.databinding.ActivityMainBinding
import com.alwan.pokeapp.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint
import es.dmoral.toasty.Toasty

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), PokemonAdapter.PokemonCallback {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private val mainViewModel: MainViewModel by viewModels()
    private val pokemonAdapter = PokemonAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        setupViewModel()
        setupRecyclerView()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setupViewModel() {
        mainViewModel.listPokemon.observe(this) {
            when (it) {
                is Resource.Loading -> {
                    showLoading(true)
                    showError(false)
                }
                is Resource.Success -> {
                    showLoading(false)
                    showError(false)
                    it.data?.let { data -> pokemonAdapter.setData(ArrayList(data)) }
                }
                is Resource.Error -> {
                    it.message?.let { message ->
                        showLoading(false)
                        showError(true)
                        Toasty.warning(this, message, Toasty.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun setupRecyclerView() {
        val spanCount = if (isPortrait()) 2 else 3
        with(binding.rvMain) {
            layoutManager = GridLayoutManager(this@MainActivity, spanCount)
            setHasFixedSize(true)
            addItemDecoration(GridMarginItemDecoration(spanCount, 16, true))
            adapter = pokemonAdapter
        }
    }

    private fun showLoading(state: Boolean) {
        binding.spinMain.visibility = if (state) View.VISIBLE else View.GONE
    }

    private fun showError(state: Boolean) {
        with(binding) {
            imgErrorMain.visibility = if (state) View.VISIBLE else View.GONE
            tvErrorMain.visibility = if (state) View.VISIBLE else View.GONE
        }
    }

    private fun isPortrait() =
        resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT

    override fun onPokemonClick(pokemon: Pokemon) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_DATA, pokemon)
        startActivity(intent)
    }

}