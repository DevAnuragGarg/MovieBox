package com.intact.moviesbox.ui.movieDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProviders
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.Unbinder
import com.intact.moviesbox.R
import com.intact.moviesbox.extension.observeLiveData
import com.intact.moviesbox.ui.BaseFragment
import com.intact.moviesbox.ui.CustomViewModelFactory
import com.intact.moviesbox.data.model.ErrorDTO
import com.intact.moviesbox.data.model.MovieDetailDTO
import com.intact.moviesbox.util.IMAGE_BASE_URL_ORIGINAL
import com.intact.moviesbox.util.MOVIE_ID
import com.squareup.picasso.Picasso
import timber.log.Timber
import javax.inject.Inject


/**
 * Created by Anurag Garg on 2019-04-24.
 */
class MovieDetailFragment : BaseFragment() {

    @BindView(R.id.movieBanner)
    lateinit var movieBannerImage: ImageView

    @Inject
    lateinit var picasso: Picasso

    @Inject
    lateinit var viewModelFactory: CustomViewModelFactory

    private lateinit var movieId: String
    private lateinit var unBinder: Unbinder
    private lateinit var movieDetailViewModel: MovieDetailViewModel

    // creating new instance using static function
    companion object {

        fun newInstance(movieId: String) = MovieDetailFragment().apply {
            arguments = bundleOf(
                MOVIE_ID to movieId
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movieId = arguments?.getString(MOVIE_ID)!!
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // get the view model
        movieDetailViewModel =
            ViewModelProviders.of(this@MovieDetailFragment, viewModelFactory).get(MovieDetailViewModel::class.java)
        setObservers(movieDetailViewModel)

        // get the movie details
        movieDetailViewModel.getMovieDetails(movieId)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_movie_detail, container, false)
        initializeVariables(view)
        return view
    }

    private fun initializeVariables(view: View) {
        Timber.d("Initialize variables")

        // initialize butterknife
        unBinder = ButterKnife.bind(this@MovieDetailFragment, view)
    }

    // setting the observers
    private fun setObservers(viewModel: MovieDetailViewModel) {
        observeLiveData(viewModel.getMovieDetailLiveData()) {
            Timber.d("Updating Movie detail data $it")
            updateUI(it)
        }
        observeLiveData(viewModel.getErrorLiveData()) {
            onError(it)
        }
    }

    // updating the UI
    private fun updateUI(movieDetailDTO: MovieDetailDTO) {

        movieDetailDTO.apply {
            picasso.load(IMAGE_BASE_URL_ORIGINAL + backdrop_path).into(movieBannerImage)
        }
    }

    // on error received
    private fun onError(dto: ErrorDTO) {
        Timber.d("onError: $dto")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        unBinder.unbind()
    }
}