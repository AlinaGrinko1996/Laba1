package ua.nure.hrunko.android.laba1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Calculator extends AppCompatActivity {
    String buf = "";
    TextView result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        result = (TextView)findViewById(R.id.result);
        result.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                buf = result.getText().toString();
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });
    }

    public void onNul(View view) {
        buf += "0";
        result.setText(buf.subSequence(0, buf.length()));
    }

    public void onOne(View view) {
        buf += "1";
        result.setText(buf.subSequence(0, buf.length()));
    }

    public void onTwo(View view) {
        buf += "2";
        result.setText(buf.subSequence(0, buf.length()));
    }

    public void onThree(View view) {
        buf += "3";
        result.setText(buf.subSequence(0, buf.length()));
    }

    public void onFour(View view) {
        buf += "4";
        result.setText(buf.subSequence(0, buf.length()));
    }

    public void onFive(View view) {
        buf += "5";
        result.setText(buf.subSequence(0, buf.length()));
    }

    public void onSix(View view) {
        buf += "6";
        result.setText(buf.subSequence(0, buf.length()));
    }

    public void onSeven(View view) {
        buf += "7";
        result.setText(buf.subSequence(0, buf.length()));
    }

    public void onEight(View view) {
        buf += "8";
        result.setText(buf.subSequence(0, buf.length()));
    }

    public void onNine(View view) {
        buf += "9";
        result.setText(buf.subSequence(0, buf.length()));
    }

    public void onPoint(View view) {
        buf += ".";
        result.setText(buf.subSequence(0, buf.length()));
    }

    public void onEql(View view) {
        String expressionResult = new ExpressionParser(buf).getResult();
        result.setText(expressionResult);
        buf = expressionResult;
    }

    public void onPlus(View view) {
        buf += "+";
        result.setText(buf.subSequence(0, buf.length()));
    }

    public void onMinus(View view) {
        buf += "-";
        result.setText(buf.subSequence(0, buf.length()));
    }

    public void onMult(View view) {
        buf += "*";
        result.setText(buf.subSequence(0, buf.length()));
    }

    public void onDiv(View view) {
        buf += "/";
        result.setText(buf.subSequence(0, buf.length()));
    }

    public void onDel(View view) {
        String newBuf = buf;
        if (newBuf.length() > 0) {
            buf = newBuf.substring(0, newBuf.length() - 1);
            result.setText(newBuf.subSequence(0, newBuf.length() - 1));
        }
    }

    public void onClean(View view) {
        buf = "";
        result.setText("");
    }
}
