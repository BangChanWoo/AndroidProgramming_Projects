package com.raon.wcombine.ui.myvote;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

public class MyVoteFragment {
    private MyVoteViewModel myVoteViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        myVoteViewModel = new ViewModelProvider(this).get(MyVoteViewModel.class);
    }
}
