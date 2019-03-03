package com.alex.sixt.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.alex.sixt.R;
import com.alex.sixt.databinding.FragmentListBinding;

/**
 * A placeholder fragment containing a simple view.
 */
public class CarsListFragment extends Fragment {

    private CarsViewModel carsViewModel;

    public static CarsListFragment newInstance() {
        return new CarsListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        carsViewModel = ViewModelProviders.of(getActivity()).get(CarsViewModel.class);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        FragmentListBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list,
                container, false);
        binding.setViewModel(carsViewModel);
        return binding.getRoot();
    }
}