package com.example.ymoroz.squareapp;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

public class MainActivity extends MvpAppCompatActivity implements EquationView {

    @InjectPresenter
    public EquationPresenter equationPresenter;

    EditText fieldA;
    EditText fieldB;
    EditText fieldC;
    TextView fieldX1Value;
    TextView fieldX2Value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fieldA = (EditText) findViewById(R.id.fieldA);
        fieldB = (EditText) findViewById(R.id.fieldB);
        fieldC = (EditText) findViewById(R.id.fieldC);
        fieldX1Value = (TextView) findViewById(R.id.labelX1Value);
        fieldX2Value = (TextView) findViewById(R.id.labelX2Value);

        Button buttonSolve = (Button) findViewById(R.id.buttonSolve);
        buttonSolve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String aValue = fieldA.getText().toString();
                String bValue = fieldB.getText().toString();
                String cValue = fieldC.getText().toString();

                if (aValue.isEmpty() || bValue.isEmpty() || cValue.isEmpty()) {
                    Toast.makeText(MainActivity.this,
                            "Все поля должны быть заполнены",
                            Toast.LENGTH_LONG).show();
                    return;
                }

                equationPresenter.solve(
                        Double.valueOf(fieldA.getText().toString()),
                        Double.valueOf(fieldB.getText().toString()),
                        Double.valueOf(fieldC.getText().toString())
                );

                fieldA.clearFocus();
                fieldB.clearFocus();
                fieldC.clearFocus();

                hideKeyboard();
            }
        });

        Button buttonClear = (Button) findViewById(R.id.buttonClear);
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                equationPresenter.clear();
                hideKeyboard();
            }
        });
    }

    @Override
    public void updateRoots(EquationRoots roots) {
        fieldX1Value.setText(String.valueOf(roots.X1));
        fieldX2Value.setText(String.valueOf(roots.X2));
    }

    @Override
    public void clear() {
        fieldA.setText("");
        fieldB.setText("");
        fieldC.setText("");
        fieldX1Value.setText("");
        fieldX2Value.setText("");
        fieldA.requestFocus();
    }

    private void hideKeyboard() {
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }
}
