package com.yadevapp.tutorial.recyclerview.activity;

import android.service.carrier.CarrierService;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yadevapp.tutorial.recyclerview.R;
import com.yadevapp.tutorial.recyclerview.adapter.RecyclerViewAdapter;
import com.yadevapp.tutorial.recyclerview.entity.Car;
import com.yadevapp.tutorial.recyclerview.entity.CarsJsonFile;
import com.yadevapp.tutorial.recyclerview.entity.Movie;
import com.yadevapp.tutorial.recyclerview.entity.MoviesJsonFile;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private final String TAG = getClass().getSimpleName();
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        setContentView(R.layout.activity_main);
        //get views from conventview
        mRecyclerView = findViewById(R.id.activity_main_recyclerview);
        //layout uncomment the one you want to use
        //linear layout
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //grid layout (2 columns)
        //mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        //add separator
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
        //parse asset file movies.json to get the movielist
        ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
        ArrayList<Movie> movieList = new ArrayList<>();
        try {
            //parse the assets file movies.json
            MoviesJsonFile moviesJsonFile = mapper.readValue(getAssets().open("movies.json"), MoviesJsonFile.class);
            movieList = moviesJsonFile.getMovieArray();
        } catch (IOException e) {
            Log.e(TAG, "IOException", e);
        }

        ArrayList<Car> carList = new ArrayList<>();
        try {
            //parse the assets file cars.json
            CarsJsonFile carsJsonFile = mapper.readValue(getAssets().open("cars.json"), CarsJsonFile.class);
            carList = carsJsonFile.getCarArray();
        } catch (IOException e) {
            Log.e(TAG, "IOException", e);
        }

        //instanciate the adapter
        mRecyclerViewAdapter = new RecyclerViewAdapter(this, movieList, carList);
        //bind the adapter to the listview
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
    }
}
