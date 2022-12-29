package com.thirtysixone.morti.cleanmvvmnewsapp.presentation.article

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.thirtysixone.morti.cleanmvvmnewsapp.databinding.FragmentNewsArticleBinding
import com.thirtysixone.morti.cleanmvvmnewsapp.domain.model.Article
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author Morteza Arifi on 12/27/22.
 */
@AndroidEntryPoint
class ArticleFragment : Fragment() {

    private var _binding: FragmentNewsArticleBinding? = null
    val binding
        get() = _binding!!

    private val args: ArticleFragmentArgs by navArgs()
    private val articleViewModel: ArticleViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewsArticleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val article = args.article
        loadWebView(article)

        binding.pullRefresh.setOnRefreshListener {
            loadWebView(article)
            binding.pullRefresh.isRefreshing = false
        }

        binding.saveFabButton.setOnClickListener {
            articleViewModel.onEvent(ArticleEvent.SaveArticle(article))
        }

        articleViewModel.articleState.observe(viewLifecycleOwner){
            if(it.articleSaved){
                Snackbar.make(binding.root, "Article saved successfully ", Snackbar.LENGTH_LONG).show()
            }
        }






    }

    private fun loadWebView(article: Article) {
        binding.articleWebView.apply {
            webViewClient = WebViewClient()
            loadUrl(article.url)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}