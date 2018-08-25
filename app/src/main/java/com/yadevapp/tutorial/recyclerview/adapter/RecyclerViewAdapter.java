package com.yadevapp.tutorial.recyclerview.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.yadevapp.tutorial.recyclerview.R;
import com.yadevapp.tutorial.recyclerview.entity.Movie;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MovieViewHolder> {
  private final String TAG = getClass().getSimpleName();
  private WeakReference<Context> mContext;
  private ArrayList<Movie> mMovieList = new ArrayList<>();

  public RecyclerViewAdapter(Context context, ArrayList<Movie> movieList) {
    Log.d(TAG, "RecyclerViewAdapter: ");
    mContext = new WeakReference<>(context);
    mMovieList = movieList;
  }

  @NonNull
  @Override
  public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    Log.d(TAG, "onCreateViewHolder: ");
    LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
    View itemRowView = layoutInflater.inflate(R.layout.item_recyclerview_movie, parent, false);
    return new MovieViewHolder(itemRowView);
  }

  @Override
  public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
    Log.d(TAG, "onBindViewHolder: ");
    Movie movie = mMovieList.get(position);
    holder.getTitleTextView().setText(movie.getmName());
    holder.getYearTextView().setText(String.valueOf(movie.getmYear()));//we must convert the  year (int) to string
    holder.getTypetextView().setText(movie.getmType());
  }

  @Override
  public int getItemCount() {
    Log.d(TAG, "getItemCount: ");
    return mMovieList.size();
  }

  public class MovieViewHolder extends RecyclerView.ViewHolder {
    private View listViewRow;
    private TextView titleTextView;
    private TextView yearTextView;
    private TextView typetextView;

    public MovieViewHolder(View itemView) {
      super(itemView);
      listViewRow = itemView;
      titleTextView = (TextView) itemView.findViewById(R.id.item_recyclerview_book_title);
      yearTextView = (TextView) itemView.findViewById(R.id.item_recyclerview_book_year);
      typetextView = (TextView) itemView.findViewById(R.id.item_recyclerview_book_type);
    }

    public TextView getTitleTextView() {
      return titleTextView;
    }

    public TextView getYearTextView() {
      return yearTextView;
    }

    public TextView getTypetextView() {
      return typetextView;
    }
  }
}
