package com.intact.moviesbox.ui.movieDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProviders
import com.intact.moviesbox.data.model.ErrorDTO
import com.intact.moviesbox.databinding.FragmentMovieDetailBinding
import com.intact.moviesbox.extension.observeLiveData
import com.intact.moviesbox.presentation.viewmodels.MovieDetailViewModel
import com.intact.moviesbox.ui.base.BaseFragment
import com.intact.moviesbox.ui.base.CustomViewModelFactory
import com.intact.moviesbox.util.IMAGE_BASE_URL_ORIGINAL
import com.intact.moviesbox.util.MOVIE_ID
import com.squareup.picasso.Picasso
import timber.log.Timber
import javax.inject.Inject


/**
 * Created by Anurag Garg on 2019-04-24.
 */
class MovieDetailFragment : BaseFragment() {
//
//    @Inject
//    lateinit var picasso: Picasso
//
//    @Inject
//    lateinit var viewModelFactory: CustomViewModelFactory
//
//    private var movieId: Long = 0
//    private lateinit var binding: FragmentMovieDetailBinding
//    private lateinit var movieDetailViewModel: MovieDetailViewModel
//
//    // creating new instance using static function
//    companion object {
//
//        fun newInstance(movieId: Long) = MovieDetailFragment().apply {
//            arguments = bundleOf(
//                MOVIE_ID to movieId
//            )
//        }
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        movieId = arguments?.getLong(MOVIE_ID)!!
//    }
//
//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//
//        // get the view model
//        movieDetailViewModel =
//            ViewModelProviders.of(this@MovieDetailFragment, viewModelFactory)
//                .get(MovieDetailViewModel::class.java)
//        setObservers(movieDetailViewModel)
//
//        // get the movie details
//        movieDetailViewModel.getMovieDetails(movieId)
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
//        val view = binding.root
//        initializeVariables(view)
//        return view
//    }
//
//    private fun initializeVariables(view: View) {
//        Timber.d("Initialize variables")
//    }
//
//    // setting the observers
//    private fun setObservers(viewModel: MovieDetailViewModel) {
//        observeLiveData(viewModel.getMovieDetailLiveData()) {
//            Timber.d("Updating Movie detail data $it")
//            updateUI(it)
//        }
//        observeLiveData(viewModel.getErrorLiveData()) {
//            onError(it)
//        }
//    }
//
//    // updating the UI
//    private fun updateUI(movieDetailDTO: MovieDetailDTO) {
//
//        movieDetailDTO.apply {
//            picasso.load(IMAGE_BASE_URL_ORIGINAL + backdrop_path).into(binding.movieBanner)
//        }
//    }
//
//    // on error received
//    private fun onError(dto: ErrorDTO) {
//        Timber.d("onError: $dto")
//    }
}