package com.example.calculator;

import android.annotation.SuppressLint;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import static java.lang.Math.sqrt;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    TextView textView2;

    char[] operations = {'/', '*', '+', '-', '='};
    char[] operationsWithDot = {'/', '*', '+', '-', '.'};
    char[] numbersWithoutZero = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};

    @SuppressLint("SetTextI18n")
    public void onNumberClick(View view) {
        String text = textView.getText().toString();
        if (text.charAt(0) == '=') {
            textView2.setText(text);
            textView.setText(view.getTag().toString());
            return;
        }
        char op = 'N';
        if (containsChar(operations, text.charAt(0))) {
            op = text.charAt(0);
            text = text.substring(2);
        }
        if (text.equals("0")) {
            text = view.getTag().toString();
        } else if (text.length() < 12) {
            text = text + view.getTag().toString();
        }
        text = (op != 'N' ? op + " " : "") + text;
        textView.setText(text);

    }

    public void onOperationClick(View view) {
        String text = textView.getText().toString();
        text = text.charAt(text.length() - 1) == '.' ? text.substring(0, text.length() - 1) : text;

        String text2 = textView2.getText().toString();
        if (text.charAt(0) == '=')
            text2 = text;
        else if (text2.length() == 0)
            text2 = "0";
        else if (text2.charAt(0) == '=')
            text2 = text2.substring(2);

        char op = 'N';
        if (containsChar(operations, text.charAt(0))) {
            op = text.charAt(0);
            text = text.substring(2);
        }

        String number1 = text2;
        boolean type1 = false;
        if (number1.contains("."))
            type1 = true;

        String number2 = text;
        boolean type2 = false;
        if (number2.contains("."))
            type2 = true;

        switch (op) {
            case 'N':
                text2 = number2;
                break;
            case '+':
                text2 = String.valueOf((type1 ? Double.parseDouble(number1) : Integer.parseInt(number1))
                        + (type2 ? Double.parseDouble(number2) : Integer.parseInt(number2)));
                break;
            case '-':
                text2 = String.valueOf((type1 ? Double.parseDouble(number1) : Integer.parseInt(number1))
                        - (type2 ? Double.parseDouble(number2) : Integer.parseInt(number2)));
                break;
            case '*':
                text2 = String.valueOf((type1 ? Double.parseDouble(number1) : Integer.parseInt(number1))
                        * (type2 ? Double.parseDouble(number2) : Integer.parseInt(number2)));
                break;
            case '/':
                text2 = String.valueOf((type1 ? Double.parseDouble(number1) : Integer.parseInt(number1))
                        / (type2 ? Double.parseDouble(number2) : Integer.parseInt(number2)));
                break;
        }
        text = view.getTag().toString() + " 0";
        if (text2.contains(".")) {
            boolean isInt = true;
            for (int i = 1; i < text2.substring(text2.indexOf('.')).length(); i++) {
                if (containsChar(numbersWithoutZero, text2.substring(text2.indexOf('.')).charAt(i)))
                    isInt = false;
            }
            if (isInt) {
                text2 = text2.substring(0, text2.indexOf('.'));
            }
        }
        text2 = text2.substring(0, Math.min(text2.length(), 12));
        if (view.getTag().toString().equals("=")) {
            text = "= " + text2;
            text2 = "";
        }
        textView.setText(text);
        textView2.setText(text2);
    }

    public void onDotClick(View view) {
        String text = textView.getText().toString();
        if (text.charAt(0) == '=') {
            textView2.setText(text);
            text = text.substring(2);
        }
        if (text.contains(".")) {
            textView.setText(text);
            return;
        }
        char op = 'N';
        if (containsChar(operations, text.charAt(0))) {
            op = text.charAt(0);
            text = text.substring(2);
        }
        if (text.length() < 12) {
            text = text + '.';
        }
        text = (op != 'N' ? op + " " : "") + text;
        textView.setText(text);
    }

    public static boolean containsChar(char[] charArray, char ch) {
        for (char c : charArray) {
            if (c == ch)
                return true;
        }
        return false;
    }

    public void onFunctionClick(View view) {
        String text = textView.getText().toString();
        switch (view.getTag().toString()) {
            case "c":
                textView.setText("0");
                textView2.setText("");
                break;
            case "bs":
                if (text.charAt(0) == '=')
                    break;
                if (text.length() == 1) {
                    textView.setText("0");
                } else if (containsChar(operations, text.charAt(0)) && text.length() == 2) {
                    textView.setText(textView2.getText().toString());
                    textView2.setText("");
                } else
                    textView.setText(text.subSequence(0, text.length() - 1));
                break;
            case "%":
                char op = 'N';
                if (containsChar(operations, text.charAt(0))) {
                    op = text.charAt(0);
                    text = text.substring(2);
                }

                float res = Float.parseFloat(text) / 100;
                if (res - (int) res > 0) {
                    text = String.valueOf(res);
                } else
                    text = String.valueOf((int) res);

                text = (op != 'N' ? op + " " : "") + text;
                textView.setText(text);
                break;
            case "=":
                if (text.charAt(0) != '=')
                    onOperationClick(view);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);

        Button num1 = findViewById(R.id.num1);
        Button num2 = findViewById(R.id.num2);
        Button num3 = findViewById(R.id.num3);
        Button num4 = findViewById(R.id.num4);
        Button num5 = findViewById(R.id.num5);
        Button num6 = findViewById(R.id.num6);
        Button num7 = findViewById(R.id.num7);
        Button num8 = findViewById(R.id.num8);
        Button num9 = findViewById(R.id.num9);
        Button num0 = findViewById(R.id.num0);
        Button clear = findViewById(R.id.clearButton);
        Button backspace = findViewById(R.id.backspaceButton);
        Button percent = findViewById(R.id.percentButton);
        Button divide = findViewById(R.id.divideButton);
        Button multiply = findViewById(R.id.multiplyButton);
        Button minus = findViewById(R.id.minusButton);
        Button plus = findViewById(R.id.plusButton);
        Button dot = findViewById(R.id.dotButton);
        Button result = findViewById(R.id.resultButton);

        ArrayList<Button> buttons = new ArrayList<>();
        buttons.add(num0);
        buttons.add(num1);
        buttons.add(num2);
        buttons.add(num3);
        buttons.add(num4);
        buttons.add(num5);
        buttons.add(num6);
        buttons.add(num7);
        buttons.add(num8);
        buttons.add(num9);
        buttons.add(clear);
        buttons.add(backspace);
        buttons.add(percent);
        buttons.add(divide);
        buttons.add(multiply);
        buttons.add(minus);
        buttons.add(plus);
        buttons.add(dot);
        buttons.add(result);

        backspace.setText("<-");

        Display display = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        int columns = 3;
        int lines = 3;

        for (Button button : buttons) {
            button.setHeight((int) (width / sqrt(3) / columns));
        }

        textView.setText("0");
        textView2.setText("");

    }

    String operation = "";
    boolean q;

    @SuppressLint("SetTextI18n")

    public void onOperationClick(Button button) {
        if (operation.equals("")) {
            operation = button.getTag().toString();
            textView2.setText(textView.getText().toString());
            textView.setText(operation + " ");
        } else {
            textView.setText(textView.getText().toString().substring(2));
            operation = button.getTag().toString();
            textView.setText(operation + " " + textView.getText().toString());
        }
    }


    public void resultCalculation() {
        String number1 = textView2.getText().toString();
        boolean type1 = false;
        if (number1.contains("."))
            type1 = true;

        String number2 = textView.getText().toString();
        boolean type2 = false;
        if (number2.contains("."))
            type2 = true;

        switch (operation) {
            case "":
                number1 = number2;
                break;
            case "+":
                number1 = String.valueOf((type1 ? Double.parseDouble(number1) : Integer.parseInt(number1))
                        + (type2 ? Double.parseDouble(number2) : Integer.parseInt(number2)));
                break;
            case "-":
                number1 = String.valueOf((type1 ? Double.parseDouble(number1) : Integer.parseInt(number1))
                        - (type2 ? Double.parseDouble(number2) : Integer.parseInt(number2)));
                break;
            case "*":
                number1 = String.valueOf((type1 ? Double.parseDouble(number1) : Integer.parseInt(number1))
                        * (type2 ? Double.parseDouble(number2) : Integer.parseInt(number2)));
                break;
            case "/":
                number1 = String.valueOf((type1 ? Double.parseDouble(number1) : Integer.parseInt(number1))
                        / (type2 ? Double.parseDouble(number2) : Integer.parseInt(number2)));
                break;
        }
        q = true;
        operation = "";
        textView2.setText(number1);
    }

    public void onClearClick(Button button) {
        textView.setText("0");
        textView2.setText("");
    }

    public void onBackspaceClick(Button button) {
        if (textView.getText().toString().charAt(0) == '=')
            return;
        if (textView.getText().toString().length() == 1) {
            textView.setText("0");
        } else if (containsChar(operations, textView.getText().toString().charAt(0))
                && textView.getText().toString().length() == 2) {
            textView.setText(textView2.getText().toString());
            textView2.setText("");
        }

    }

    @SuppressLint("SetTextI18n")

    public void onDotClick(Button button) {
        if (textView.getText().toString().contains(".")) {
            textView.setText(textView.getText().toString());
            return;
        }
        if (textView.getText().toString().length() < 12) {
            textView.setText(textView.getText().toString() + '.');
        }
    }
    public void onPercentClick(Button button) {
        float res = Float.parseFloat(textView.getText().toString()) / 100;
        if (res - (int) res > 0) {
            textView.setText(String.valueOf(res));
        } else
            textView.setText(String.valueOf((int) res));
    }
}
