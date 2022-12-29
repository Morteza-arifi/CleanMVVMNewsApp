package com.thirtysixone.morti.cleanmvvmnewsapp.presentation.topHeadLineNews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.thirtysixone.morti.cleanmvvmnewsapp.R
import com.thirtysixone.morti.cleanmvvmnewsapp.databinding.FragmentTopHeadlinesNewsBinding
import com.thirtysixone.morti.cleanmvvmnewsapp.presentation.common.NewsAdapter
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author Morteza Arifi on 12/26/22.
 */
@AndroidEntryPoint
class TopHeadLinesFragment : Fragment() {

    private var _binding: FragmentTopHeadlinesNewsBinding? = null
    private val binding get() = _binding!!

    val topHeadLinesViewModel : TopHeadLinesViewModel by viewModels()
    lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTopHeadlinesNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        setupObserver()

        newsAdapter.setOnItemClickListener {
            val navController = findNavController()
            if (navController.currentDestination?.id == R.id.topHeadLinesFragment) {
                val bundle = Bundle()
                bundle.putParcelable("article", it)
                navController.navigate(
                    R.id.action_topHeadLinesFragment_to_articleFragment,
                    bundle
                )
            }
        }

    }

    private fun setupObserver() {
        topHeadLinesViewModel.state.observe(viewLifecycleOwner){
            if (it.isLoading){
                binding.contentProgressBar.show()
            }
            else if (it.errorMessage.isNotBlank()) {
                binding.contentProgressBar.hide()
                Snackbar.make(binding.root, it.errorMessage, Snackbar.LENGTH_LONG).show()
            } else if(it.data.isNotEmpty()) {
                binding.contentProgressBar.hide()
                newsAdapter.submitList(it.data)

            }
        }
    }


    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter()
        binding.topHeadLinesRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = newsAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}