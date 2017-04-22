package com.example.ymoroz.squareapp;

import com.arellomobile.mvp.MvpView;

public interface EquationView extends MvpView {
    void updateRoots(EquationRoots roots);
    void clear();
}
