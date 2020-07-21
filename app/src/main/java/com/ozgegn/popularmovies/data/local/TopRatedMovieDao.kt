package com.ozgegn.popularmovies.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ozgegn.popularmovies.model.TopRatedMovieResult

@Dao
interface TopRatedMovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieList(movieList: List<TopRatedMovieResult>)

    @Query("SELECT * FROM topRatedMovies WHERE page=:page_ ORDER BY popularity DESC")
    fun getMovieList(page_: Int): List<TopRatedMovieResult>

    @Query("SELECT * FROM topRatedMovies WHERE title LIKE '%' || :query || '%'")
    fun searchMovie(query: String): List<TopRatedMovieResult>
}