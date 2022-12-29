package com.thirtysixone.morti.cleanmvvmnewsapp.presentation.savedNews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.thirtysixone.morti.cleanmvvmnewsapp.R
import com.thirtysixone.morti.cleanmvvmnewsapp.databinding.FragmentSavedNewsBinding
import com.thirtysixone.morti.cleanmvvmnewsapp.domain.model.Article
import com.thirtysixone.morti.cleanmvvmnewsapp.presentation.common.NewsAdapter
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author Morteza Arifi on 12/28/22.
 */
@AndroidEntryPoint
class SavedNewsFragment : Fragment() {

    private var _binding: FragmentSavedNewsBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var newsAdapter: NewsAdapter
    private val savedNewsViewModel: SavedNewsViewModel by viewModels()
    private var deletedArticle: Article? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSavedNewsBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecycleView()

        setupNewsStateObserver()

        ItemTouchHelper(getItemTouchHelper()).apply {
            attachToRecyclerView(binding.savedNewsRecyclerView)
        }

        setOnItemClickNavigationToArticle()
    }

    private fun setupNewsStateObserver() {
        savedNewsViewModel.savedNewsState.observe(viewLifecycleOwner) {
            when (it) {
                is SavedNewsPageState.SavedNewsActionState -> {
                    if (it.articleDeleted) {
                        Snackbar.make(
                            binding.root,
                            "Article deleted successfully",
                            Snackbar.LENGTH_LONG
                        )
                            .setAction("Undo") {
                                deletedArticle?.let { article ->
                                    savedNewsViewModel.onEvent(
                                        SavedNewsEvent.UndoDeleteArticle(
                                            article
                                        )
                                    )
                                }
                            }.show()
                    } else if (it.articleDeletedUndo) {
                        Snackbar.make(binding.root, "Article delete undo", Snackbar.LENGTH_LONG)
                            .show()
                    }
                }
                is SavedNewsPageState.SavedNewsListState -> {
                    if (it.isLoading) {
                        binding.contentProgressBar.show()
                    } else if (it.articles.isNotEmpty()) {
                        binding.contentProgressBar.hide()
                        newsAdapter.submitList(it.articles)
                    }
                }
            }

        }
    }

    private fun getItemTouchHelper() = object : ItemTouchHelper.SimpleCallback(
        ItemTouchHelper.UP or ItemTouchHelper.DOWN,
        ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
    ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val article = newsAdapter.currentList[position]
                deleteArticle(article)
        }
    }

    private fun setOnItemClickNavigationToArticle() {
        newsAdapter.setOnItemClickListener {
            val navController = findNavController()
            if (navController.currentDestination?.id == R.id.savedNewsFragment) {
                val bundle = Bundle()
                bundle.putParcelable("article", it)
                navController.navigate(
                    R.id.action_savedNewsFragment_to_articleFragment,
                    bundle
                )
            }
        }
    }

    private fun deleteArticle(article: Article) {
        savedNewsViewModel.onEvent(SavedNewsEvent.DeleteArticle(article))
        deletedArticle = article
    }

    private fun setupRecycleView() {
        newsAdapter = NewsAdapter()
        binding.savedNewsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = newsAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}