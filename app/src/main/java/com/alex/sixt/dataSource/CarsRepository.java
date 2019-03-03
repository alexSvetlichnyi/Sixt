package com.alex.sixt.dataSource;

import com.alex.sixt.model.Car;

import java.util.List;

import io.reactivex.Single;

public interface CarsRepository {
    Single<List<Car>> getCars();
}
