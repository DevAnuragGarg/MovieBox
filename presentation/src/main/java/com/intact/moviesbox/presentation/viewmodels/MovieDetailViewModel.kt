package com.intact.moviesbox.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import com.intact.moviesbox.domain.usecases.GetMovieDetailUseCase
import com.intact.moviesbox.presentation.mapper.MovieDomainPresentationMapper
import com.intact.moviesbox.presentation.model.ErrorDTO
import com.intact.moviesbox.presentation.model.MovieDTO
import com.intact.moviesbox.presentation.viewmodels.base.BaseViewModel
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val movieDomainPresentationMapper: MovieDomainPresentationMapper
) : BaseViewModel() {

    private val errorLiveData = MutableLiveData<ErrorDTO>()
    private val movieDetailLiveData = MutableLiveData<MovieDTO>()

    fun getMovieDetail(movieId: Long) {
        getCompositeDisposable().add(getMovieDetailUseCase
            .buildUseCase(GetMovieDetailUseCase.Param(movieId = movieId))
            .map { movieDomainPresentationMapper.to(it) }
            .subscribe({
                movieDetailLiveData.value = it
            }, {
                errorLiveData.value = ErrorDTO(400, it.localizedMessage)
            }
            ))
    }

    fun getErrorLiveData() = errorLiveData
    fun getMovieDetailLiveData() = movieDetailLiveData
}