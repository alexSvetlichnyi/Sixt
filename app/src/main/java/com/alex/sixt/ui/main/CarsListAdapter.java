package com.alex.sixt.ui.main;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.alex.sixt.R;
import com.alex.sixt.databinding.CarItemBinding;
import com.alex.sixt.model.Car;

import java.util.List;

public class CarsListAdapter extends RecyclerView.Adapter<CarsListAdapter.CarItemViewHolder> {
    private List<Car> cars;

    public CarsListAdapter(@NonNull List<Car> cars) {
        this.cars = cars;
    }

    @NonNull
    @Override
    public CarItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        CarItemBinding carItemBinding = DataBindingUtil.inflate(inflater, R.layout.car_item, viewGroup, false);
        return new CarItemViewHolder(carItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CarItemViewHolder holder, int position) {
        CarItemViewModel carItemViewModel = new CarItemViewModel(cars.get(position), holder.carItemBinding.getRoot().getResources());
        holder.carItemBinding.setViewModel(carItemViewModel);
        holder.carItemBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return cars.size();
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
        notifyDataSetChanged();
    }

    class CarItemViewHolder extends RecyclerView.ViewHolder {
        final CarItemBinding carItemBinding;

        CarItemViewHolder(CarItemBinding carItemBinding) {
            super(carItemBinding.getRoot());
            this.carItemBinding = carItemBinding;
        }
    }
}