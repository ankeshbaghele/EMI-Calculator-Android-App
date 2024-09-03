package com.example.simplecalculator;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;
    private Button buttonAdd, buttonSubtract, buttonMul, buttonDiv, buttonClear, buttonEqual;
    private TextView calculationTextView, resultTextView;

    private StringBuilder expression = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initControl();
        initControlListener();
    }

    private void initControl() {
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);

        buttonAdd = findViewById(R.id.buttonAdd);
        buttonSubtract = findViewById(R.id.buttonSub);
        buttonMul = findViewById(R.id.buttonMul);
        buttonDiv = findViewById(R.id.buttonDiv);
        buttonClear = findViewById(R.id.buttonClear);
        buttonEqual = findViewById(R.id.buttonEqual);

        calculationTextView = findViewById(R.id.text_view_calculation);
        resultTextView = findViewById(R.id.text_view_result);
    }

    private void initControlListener() {
        button0.setOnClickListener(v -> onNumberButtonClicked("0"));
        button1.setOnClickListener(v -> onNumberButtonClicked("1"));
        button2.setOnClickListener(v -> onNumberButtonClicked("2"));
        button3.setOnClickListener(v -> onNumberButtonClicked("3"));
        button4.setOnClickListener(v -> onNumberButtonClicked("4"));
        button5.setOnClickListener(v -> onNumberButtonClicked("5"));
        button6.setOnClickListener(v -> onNumberButtonClicked("6"));
        button7.setOnClickListener(v -> onNumberButtonClicked("7"));
        button8.setOnClickListener(v -> onNumberButtonClicked("8"));
        button9.setOnClickListener(v -> onNumberButtonClicked("9"));

        buttonAdd.setOnClickListener(v -> onOperatorButtonClicked("+"));
        buttonSubtract.setOnClickListener(v -> onOperatorButtonClicked("-"));
        buttonMul.setOnClickListener(v -> onOperatorButtonClicked("*"));
        buttonDiv.setOnClickListener(v -> onOperatorButtonClicked("/"));

        buttonEqual.setOnClickListener(v -> onEqualButtonClicked());
        buttonClear.setOnClickListener(v -> onClearButtonClicked());
    }

    private void onNumberButtonClicked(String number) {
        expression.append(number);
        updateDisplay();
    }

    private void onOperatorButtonClicked(String operator) {
        if (expression.length() > 0 && !isLastCharOperator()) {
            expression.append(" ").append(operator).append(" ");
            updateDisplay();
        }
    }

    private void onEqualButtonClicked() {
        if (expression.length() > 0) {
            try {
                double result = evaluateExpression(expression.toString());
                resultTextView.setText(String.valueOf(result));
            } catch (Exception e) {
                resultTextView.setText("Error");
            }
        }
    }

    private void onClearButtonClicked() {
        expression.setLength(0);
        resultTextView.setText("");
        updateDisplay();
    }

    private void updateDisplay() {
        calculationTextView.setText(expression.toString());
    }

    private boolean isLastCharOperator() {
        if (expression.length() == 0) return false;
        char lastChar = expression.charAt(expression.length() - 1);
        return lastChar == '+' || lastChar == '-' || lastChar == '*' || lastChar == '/';
    }

    private double evaluateExpression(String expr) throws ArithmeticException {
        String[] tokens = expr.split(" ");
        if (tokens.length < 3) throw new ArithmeticException("Invalid expression");

        double num1 = Double.parseDouble(tokens[0]);
        double num2 = Double.parseDouble(tokens[2]);
        String operator = tokens[1];

        switch (operator) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                if (num2 == 0) throw new ArithmeticException("Division by zero");
                return num1 / num2;
            default:
                throw new ArithmeticException("Invalid operator");
        }
    }
}
