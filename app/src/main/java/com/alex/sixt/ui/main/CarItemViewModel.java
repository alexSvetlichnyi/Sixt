package com.alex.sixt.ui.main;

import android.content.res.Resources;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.alex.sixt.R;
import com.alex.sixt.model.Car;

import java.util.Observable;

public class CarItemViewModel {
    public final ObservableField<String> modelName = new ObservableField<>();
    public final ObservableField<String> fuelType = new ObservableField<>();
    public final ObservableField<String> color = new ObservableField<>();
    public final ObservableField<String> transmission = new ObservableField<>();
    public final ObservableField<String> fuelLevel = new ObservableField<>();
    public final ObservableField<String> imageUrl = new ObservableField<>();

    public CarItemViewModel(@NonNull Car car, Resources resources) {
        modelName.set(resources.getString(R.string.model, car.getModelName()));
        fuelType.set(resources.getString(R.string.fuel_type, car.getFuelType()));
        fuelLevel.set(resources.getString(R.string.fuel_level, car.getFuelLevel()));
        transmission.set(resources.getString(R.string.transmission, car.getTransmission()));
        color.set(resources.getString(R.string.color, car.getColor()));
        imageUrl.set(car.getCarImageUrl());
    }
}