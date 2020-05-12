package com.intact.moviesbox.ui.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.ViewModelProviders
import com.intact.moviesbox.R
import com.intact.moviesbox.databinding.ActivityMovieDetailBinding
import com.intact.moviesbox.extension.observeLiveData
import com.intact.moviesbox.presentation.model.ErrorDTO
import com.intact.moviesbox.presentation.model.MovieDTO
import com.intact.moviesbox.presentation.viewmodels.MovieDetailViewModel
import com.intact.moviesbox.ui.base.BaseActivity
import com.intact.moviesbox.ui.base.CustomViewModelFactory
import com.intact.moviesbox.util.IMAGE_BASE_URL_500
import com.intact.moviesbox.util.INTENT_KEY_MOVIE_ID
import com.squareup.picasso.Picasso
import dagger.android.DispatchingAndroidInjector
import timber.log.Timber
import javax.inject.Inject


/**
 * Activity to show the movie details
 *
 * Created by Anurag Garg on 2019-04-24.
 *
 * Using kotlinx.android.synthetic is no longer a recommended practice.
 *
 * Data binding: A binding class is generated for each layout file. By default,
 * the name of the class is based on the name of the layout file, converting it to Pascal case
 * and adding the Binding suffix to it. The above layout filename is activity_main.xml so the
 * corresponding generated class is ActivityMainBinding.
 *
 * Binding adapters: For every layout expression, there is a binding adapter that makes the
 * framework calls required to set the corresponding properties or listeners. For example,
 * the binding adapter can take care of calling the setText() method to set the text property
 * or call the setOnClickListener() method to add a listener to the click event.
 *
 * @BindingAdapter("imageUrl", "error")
 * fun loadImage(view: ImageView, url: String, error: Drawable) {
 *      Picasso.get().load(url).error(error).into(view)
 * }
 *
 * <ImageView app:imageUrl="@{venue.imageUrl}" app:error="@{@drawable/venueError}" />
 *
 * We are not using binding adapter as in above example the code has to be written in the user class
 * which is a pojo and we are not injecting picasso over there
 *
 * https://developer.android.com/topic/libraries/data-binding/expressions#kotlin
 */
class MovieDetailActivity : BaseActivity() {

    @Inject
    internal lateinit var androidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var viewModelFactory: CustomViewModelFactory

    @Inject
    lateinit var picasso: Picasso

    private var movieId: Long = 0
    private lateinit var movieDTO: MovieDTO
    private lateinit var binding: ActivityMovieDetailBinding
    private lateinit var movieDetailViewModel: MovieDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeVariables()
    }

    private fun initializeVariables() {
        // set up action bar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.run {
            this.setDisplayHomeAsUpEnabled(true)
            this.setDisplayShowHomeEnabled(true)
        }

        // get the view model
        movieDetailViewModel =
            ViewModelProviders.of(this@MovieDetailActivity, viewModelFactory)
                .get(MovieDetailViewModel::class.java)
        setObservers(movieDetailViewModel)
        getMovieId()

        // get the movie details
        movieDetailViewModel.getMovieDetail(movieId)
    }

    private fun getMovieId() {
        movieId = intent.extras!!.getLong(INTENT_KEY_MOVIE_ID);
    }

    // setting the observers
    private fun setObservers(viewModel: MovieDetailViewModel) {
        observeLiveData(viewModel.getMovieDetailLiveData()) {
            it?.let {
                Timber.d("Received the movie detail data, $it")
                movieDTO = it
                updateUI()
            }
        }
        observeLiveData(viewModel.getErrorLiveData()) {
            it?.let {
                Timber.d("Error for the movie detail data ${it.message}")
                onError(it)
            }
        }
    }

    // on error received
    private fun onError(dto: ErrorDTO) {
        Timber.d("onError: $dto")
    }

    // update UI
    private fun updateUI() {
        // data binding
        binding.movieDetailDTO = movieDTO
        // it speeds up the binding which are pending
        //binding.executePendingBindings()

        // view binding
        with(movieDTO) {
            binding.collapsingToolbar.title = title
            picasso.load(IMAGE_BASE_URL_500 + backdropPath)
                .placeholder(R.drawable.ic_video_camera).into(binding.posterIV)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}