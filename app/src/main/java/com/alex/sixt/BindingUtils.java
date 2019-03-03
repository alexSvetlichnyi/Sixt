package com.alex.sixt;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

public class BindingUtils {

    @BindingAdapter("url")
    public static void setImageSource(final ImageView view, String url) {
        Glide.with(view.getContext()).load(url).error(R.drawable.noimage).into(view);
    }

    @BindingAdapter("divider")
    public static void addDivider(RecyclerView recyclerView, boolean divider) {
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
    }
}
