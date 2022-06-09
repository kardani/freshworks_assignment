package com.kardani.masgiphy.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kardani.masgiphy.R
import com.kardani.masgiphy.databinding.FavoritesFragmentBinding
import com.kardani.masgiphy.ui.GiphsAdapter
import com.kardani.masgiphy.ui.model.GiphUI
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritesFragment : Fragment(), GiphsAdapter.ClickListener {

    private lateinit var binding: FavoritesFragmentBinding

    private val viewModel: FavoritesViewModel by viewModel()

    private val adapter = GiphsAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FavoritesFragmentBinding.inflate(layoutInflater)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.title = getString(R.string.favorites)

        setupAdapter()

        viewModel.favorites.observe(viewLifecycleOwner){

            if(it.isNotEmpty()){
                binding.noDataTv.visibility = View.GONE
            }else{
                binding.noDataTv.visibility = View.VISIBLE
            }

            adapter.submitList(it)

        }

    }

    private fun setupAdapter(){
        binding.favoritesRv.adapter = adapter
    }

    override fun onFavoriteClicked(item: GiphUI) {
        viewModel.removeFromFavorite(item)
    }

}