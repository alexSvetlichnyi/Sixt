package com.alex.sixt.dataSource;

import com.alex.sixt.model.Car;

import java.util.List;

import io.reactivex.Single;

public class CarsRepositoryImpl implements CarsRepository {
    private final CarsApi carsApi ;

    public CarsRepositoryImpl() {
        carsApi = RetrofitClient.getInstance().create(CarsApi.class);
    }

    @Override
    public Single<List<Car>> getCars() {
        return carsApi.listCars();
    }
}
