package com.alex.sixt.dataSource;

import com.alex.sixt.model.Car;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface CarsApi {
    @Headers("Cache-Control: max-age=640000")
    @GET("codingtask/cars")
    Single<List<Car>> listCars();
}
