package com.ozgegn.popularmovies.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ozgegn.popularmovies.model.MovieDetailsResponse

@Dao
interface MovieDetailsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieDetail(movieDetail: MovieDetailsResponse)

    @Query("SELECT * FROM movieDetails WHERE id=:id_")
    fun getMovieDetail(id_: Int): MovieDetailsResponse?

}