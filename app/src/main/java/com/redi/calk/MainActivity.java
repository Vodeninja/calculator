package com.redi.calk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView migbox = findViewById(R.id.migbox);
        Animation blinkAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);
        migbox.startAnimation(blinkAnimation);
    }
    public void clear(View v) {
        TextView result = findViewById(R.id.result);
        result.setText("");
    }
    public void clearone(View v) {
        try {
            TextView result = findViewById(R.id.result);
            StringBuilder sb = new StringBuilder(result.getText().toString());
            if (result.length() >= 1) {
                sb.deleteCharAt(result.length() - 1);
                result.setText(sb.toString());}
        }
        catch (NumberFormatException e) {
            return;
        }
    }
    public void resh(View v) {
        TextView result = findViewById(R.id.result);
        result.setText(String.valueOf(evaluateExpression(result.getText().toString())));
    }
    public void action(View v) {
        try {
            TextView result = findViewById(R.id.result);
            result.setText((result.getText().toString() + ((Button) v).getText().toString()));
            //Toast.makeText(this, ((Button) v).getText().toString(), Toast.LENGTH_SHORT).show(); //
        }
        catch (NumberFormatException e) {
            return;
        }

    }
    public static double evaluateExpression(String expression) {
        String[] tokens = expression.split("(?<=\\d)(?=\\D)|(?<=\\D)(?=\\d)");

        double result = Double.parseDouble(tokens[0]);

            for (int i = 1; i < tokens.length; i += 2) {
                String operator = tokens[i];
                double operand = Double.parseDouble(tokens[i + 1]);

                switch (operator) {
                    case "+":
                        result += operand;
                        break;
                    case "-":
                        result -= operand;
                        break;
                    case "*":
                        result *= operand;
                        break;
                    case "/":
                        result /= operand;
                        break;
                    case "%":
                        try {
                            result %= operand;
                        }
                        catch (NumberFormatException e) {
                        result = 0;

                    }
                        break;
                }
            }

            return result;
        }

}