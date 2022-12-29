package com.thirtysixone.morti.cleanmvvmnewsapp.presentation.searchNews

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.thirtysixone.morti.cleanmvvmnewsapp.R
import com.thirtysixone.morti.cleanmvvmnewsapp.databinding.FragmentSearchNewsBinding
import com.thirtysixone.morti.cleanmvvmnewsapp.presentation.common.NewsAdapter
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author Morteza Arifi on 12/26/22.
 */
@AndroidEntryPoint
class SearchNewsFragment : Fragment() {

    private var _binding: FragmentSearchNewsBinding? = null
    private val binding get() = _binding!!

    lateinit var newsAdapter: NewsAdapter
    private val searchNewsViewModel: SearchNewsViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupOnItemClickListener()
        setupSearchStateObserver()
        setupTextStateObserver()
        setupTextChangeListener()


    }

    private fun setupTextChangeListener() {
        binding.searchSrcText.text = null
        binding.searchSrcText.hint = "type to search news"
        binding.searchSrcText.addTextChangedListener(
            object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    if (p0.isNullOrEmpty()) {
                        searchNewsViewModel.onEvent(SearchNewsEvent.TextFieldContentEmpty)
                    }
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    if (p0.isNullOrEmpty()) {
                        searchNewsViewModel.onEvent(SearchNewsEvent.TextFieldContentEmpty)
                    } else {
                        searchNewsViewModel.onEvent(SearchNewsEvent.TextFieldContentChange(p0.toString()))
                    }
                }

                override fun afterTextChanged(p0: Editable?) {
                }

            }
        )
    }


    private fun setupOnItemClickListener() {
        newsAdapter.setOnItemClickListener {
            val navController = findNavController()
            if (navController.currentDestination?.id == R.id.searchNewsFragment) {
                val bundle = Bundle()
                bundle.putParcelable("article", it)
                navController.navigate(
                    R.id.action_searchNewsFragment_to_articleFragment,
                    bundle
                )
            }
        }
    }


    private fun setupSearchStateObserver() {
        searchNewsViewModel.searchNewsState.observe(viewLifecycleOwner) {
            if (it.isLoading){
                binding.contentProgressBar.show()
            } else if (it.errorMessage.isNotEmpty()){
                binding.contentProgressBar.hide()
                Snackbar.make(binding.root, it.errorMessage, Snackbar.LENGTH_LONG).show()
            } else {
                binding.contentProgressBar.hide()
                newsAdapter.submitList(it.articles)
            }
        }
    }

    private fun setupTextStateObserver() {
        searchNewsViewModel.textState.observe(viewLifecycleOwner){
            binding.searchSrcText.hint = if(it.showHint) "type to search news" else null
        }
    }


    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter()
        binding.searchRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = newsAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}