package com.yadevapp.tutorial.recyclerview.entity;

import java.util.ArrayList;

/**
 * Created by abdoulaye on 7/7/16.
 * represente the movies.json file in java form
 */
public class MoviesJsonFile {
    private final String TAG = getClass().getSimpleName();

    private ArrayList<Movie> movieArray;

    public ArrayList<Movie> getMovieArray() {
        return movieArray;
    }

    public void setMovieArray(ArrayList<Movie> movieArray) {
        this.movieArray = movieArray;
    }
}
