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
import com.yadevapp.tutorial.recyclerview.entity.Car;
import com.yadevapp.tutorial.recyclerview.entity.Movie;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
  private final String TAG = getClass().getSimpleName();
  private WeakReference<Context> mContext;
  private ArrayList<Movie> mMovieList = new ArrayList<>();
  private ArrayList<Car> mCarList = new ArrayList<>();
  private CarItemClickListener mCarItemClickListener;
  private MovieItemClickListener mMovieItemClickListener;

  public RecyclerViewAdapter(Context context, ArrayList<Movie> movieList, ArrayList<Car> carList) {
    Log.d(TAG, "RecyclerViewAdapter: ");
    mContext = new WeakReference<>(context);
    mMovieList = movieList;
    mCarList = carList;
  }

  @Override
  public int getItemViewType(int position) {
      //put the cars list first
    if (position < mCarList.size()) {
      return ViewType.CAR.getValue();
    }
    return ViewType.MOVIE.getValue();
  }

  @NonNull
  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    Log.d(TAG, "onCreateViewHolder: ");
    LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
    View itemRowView;
    ViewType viewTypeEnum = ViewType.createViewType(viewType);
    switch (viewTypeEnum) {
      case CAR:
        itemRowView = layoutInflater.inflate(R.layout.item_recyclerview_car, parent, false);
        return new CarViewHolder(itemRowView);
      case MOVIE:
        itemRowView = layoutInflater.inflate(R.layout.item_recyclerview_movie, parent, false);
        return new MovieViewHolder(itemRowView);
          default:
            throw new RuntimeException("wrong view type");
    }
  }

  @Override
  public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    Log.d(TAG, "onBindViewHolder: ");

    ViewType viewType = ViewType.createViewType(getItemViewType(position));
    switch (viewType) {
      case CAR:
        Car car = mCarList.get(position);
        CarViewHolder carViewHolder = (CarViewHolder)holder;
        carViewHolder.getNameTextView().setText(car.getmName());
        carViewHolder.getBrandTextView().setText(car.getmBrand());
        carViewHolder.getYearTextView().setText(String.valueOf(car.getmYear()));
        break;
      case MOVIE:
        Movie movie = mMovieList.get(position - mCarList.size());
        MovieViewHolder movieViewHolder = (MovieViewHolder)holder;
        movieViewHolder.getTitleTextView().setText(movie.getmName());
        movieViewHolder.getYearTextView().setText(String.valueOf(movie.getmYear()));//we must convert the  year (int) to string
        movieViewHolder.getTypetextView().setText(movie.getmType());
        break;
        default:
          throw new RuntimeException("unknow view type");
    }
  }

  @Override
  public int getItemCount() {
    Log.d(TAG, "getItemCount: ");
    return mMovieList.size() + mCarList.size();
  }

  public Object getItem(int position) {
    if (position < mCarList.size()) {
      return mCarList.get(position);
    }
    return mMovieList.get(position - mCarList.size());
  }

  public void setmCarItemClickListener(CarItemClickListener mCarItemClickListener) {
    this.mCarItemClickListener = mCarItemClickListener;
  }

  public void setmMovieItemClickListener(MovieItemClickListener mMovieItemClickListener) {
    this.mMovieItemClickListener = mMovieItemClickListener;
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

      itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          if (mMovieItemClickListener != null) {
            mMovieItemClickListener.onMovieItemClick(listViewRow, (Movie)getItem(getAdapterPosition()), getAdapterPosition());
          }
        }
      });

      itemView.setOnLongClickListener(new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
          if (mMovieItemClickListener != null) {
            mMovieItemClickListener.onMovieItemLongClick(listViewRow, (Movie)getItem(getAdapterPosition()), getAdapterPosition());
          }
          return false;
        }
      });
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

  public class CarViewHolder extends RecyclerView.ViewHolder {
    private View listViewRow;
    private TextView nameTextView;
    private TextView yearTextView;
    private TextView brandTextView;

    public CarViewHolder(final View itemView) {
      super(itemView);
      listViewRow = itemView;
      nameTextView = (TextView) itemView.findViewById(R.id.item_recyclerview_car_name);
      yearTextView = (TextView) itemView.findViewById(R.id.item_recyclerview_car_year);
      brandTextView = (TextView) itemView.findViewById(R.id.item_recyclerview_car_brand);

      itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          if (mCarItemClickListener != null) {
            mCarItemClickListener.onCarItemClick(listViewRow, (Car)getItem(getAdapterPosition()), getAdapterPosition());
          }
        }
      });

      itemView.setOnLongClickListener(new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
          if (mCarItemClickListener != null) {
            mCarItemClickListener.onCarItemLongClick(listViewRow, (Car)getItem(getAdapterPosition()), getAdapterPosition());
          }
          return false;
        }
      });
    }

    public TextView getNameTextView() {
      return nameTextView;
    }

    public TextView getYearTextView() {
      return yearTextView;
    }

    public TextView getBrandTextView() {
      return brandTextView;
    }
  }

  private enum ViewType {
    MOVIE,
    CAR;

    public static ViewType createViewType(int value) {
      switch (value) {
        case 0:
          return MOVIE;
        case 1:
          return CAR;
        default:
          throw new RuntimeException("unknown view type");
      }
    }

    public int getValue() {
     switch (this) {
       case MOVIE:
         return 0;
       case CAR:
         return 1;
         default:
           //should not pass here
           throw new RuntimeException("unknown ViewType");
     }
    }
  }

  public interface MovieItemClickListener {
    void onMovieItemClick(View view, Movie movie, int position);
    void onMovieItemLongClick(View view, Movie movie, int position);
  }

  public interface CarItemClickListener {
    void onCarItemClick(View view, Car car, int position);
    void onCarItemLongClick(View view, Car car, int position);
  }
}
