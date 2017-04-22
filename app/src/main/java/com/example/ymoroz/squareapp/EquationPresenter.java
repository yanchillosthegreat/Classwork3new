package com.example.ymoroz.squareapp;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

@InjectViewState
public class EquationPresenter extends MvpPresenter<EquationView> implements IEquationPresenter {
    @Override
    public void solve(double a, double b, double c) {
        EquationRoots roots = new EquationRoots();
        double d = b*b - 4*a*c;
        if (d == 0)
        {
            roots.X1 = roots.X2 = -b / (2 * a);
        }
        else if (d > 0)
        {
            roots.X1 = (-b + Math.sqrt(d)) / (2 * a);
            roots.X2 = (-b - Math.sqrt(d)) / (2 * a);
        }
        getViewState().updateRoots(roots);
    }

    @Override
    public void clear() {
        getViewState().clear();
    }
}
