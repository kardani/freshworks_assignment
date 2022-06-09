package com.kardani.masgiphy.ui.giphs

import android.os.Bundle
import android.view.*
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.kardani.masgiphy.R
import com.kardani.masgiphy.core.DataState
import com.kardani.masgiphy.databinding.IndexFragmentBinding
import com.kardani.masgiphy.ui.model.GiphUI
import com.kardani.masgiphy.ui.GiphsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class IndexFragment : Fragment(), GiphsAdapter.ClickListener {

    private lateinit var binding: IndexFragmentBinding

    private val viewModel: IndexViewModel by viewModel()

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

        binding = IndexFragmentBinding.inflate(layoutInflater)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.title = getString(R.string.giphs)

        setupAdapter()

        binding.retryBtn.setOnClickListener {

        }

        binding.searchEt.addTextChangedListener {

            viewModel.searchGiphs(it.toString())

        }

        viewModel.giphs.observe(viewLifecycleOwner){

            binding.loading.visibility = if(it is DataState.Loading) View.VISIBLE else View.GONE


            if(it is DataState.Error){
                binding.errorLayout.visibility = View.VISIBLE
                binding.errorMessageTV.text = it.error
            }else{
                binding.errorLayout.visibility = View.GONE
            }

            if(it is DataState.Success) {
                adapter.submitList(it.value)
            }

            binding.giphsRV.visibility = if(it is DataState.Success) View.VISIBLE else View.GONE

        }

    }

    private fun setupAdapter(){
        binding.giphsRV.adapter = adapter
    }

    override fun onFavoriteClicked(item: GiphUI) {
        viewModel.toggleFavorite(item)
    }

}