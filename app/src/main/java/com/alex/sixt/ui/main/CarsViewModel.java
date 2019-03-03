package com.alex.sixt.ui.main;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.alex.sixt.dataSource.CarsRepository;
import com.alex.sixt.dataSource.CarsRepositoryImpl;
import com.alex.sixt.model.Car;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class CarsViewModel extends AndroidViewModel {

    private CarsListAdapter adapter;
    private List<Car> cars = new ArrayList<>();
    private final MutableLiveData<List<Car>> updateCars = new MutableLiveData<>();
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    private final CarsRepository carsRepository = new CarsRepositoryImpl();

    public CarsViewModel(@NonNull Application application) {
        super(application);
    }

    public CarsListAdapter getAdapter() {
        if (adapter == null) {
            adapter = new CarsListAdapter(cars);
        }
        return adapter;
    }

    public void fetchCars() {
        compositeDisposable.add(carsRepository.getCars()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(cars -> {
                    this.cars = cars;
                    adapter.setCars(cars);
                    updateCars.setValue(cars);
                }));
    }

    public MutableLiveData<List<Car>> getUpdateCars() {
        return updateCars;
    }

    public List<Car> getCars() {
        return cars;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}