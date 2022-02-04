package com.example.calculator27;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.StringCharacterIterator;

public class MainActivity extends AppCompatActivity {
    private TextView screen;
    private Button AC, Power, Back, Division, Multiply, Minus, Plus, Point, One, Two, Three, Four, Five, Six, Seven, Eight, Nine, Zero, Ans;
    private String input, Answer;
    private Button send;
    private boolean t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        screen = findViewById(R.id.screen);
        AC = findViewById(R.id.ac);
        send = findViewById(R.id.btn_send);
        Power = findViewById(R.id.power);
        Back = findViewById(R.id.back);
        Division = findViewById(R.id.division);
        Seven = findViewById(R.id.seven);
        Eight = findViewById(R.id.eight);
        Nine = findViewById(R.id.nine);
        Multiply = findViewById(R.id.multiply);
        Four = findViewById(R.id.four);
        Five = findViewById(R.id.five);
        Six = findViewById(R.id.six);
        Minus = findViewById(R.id.minus);
        One = findViewById(R.id.one);
        Two = findViewById(R.id.two);
        Three = findViewById(R.id.three);
        Plus = findViewById(R.id.plus);
        Zero = findViewById(R.id.zero);
        Point = findViewById(R.id.point);
        Ans = findViewById(R.id.ans);
        send.setOnClickListener(view -> {
            sendData(screen.getText().toString());
        });
    }

    public void ButtonClick(View view) {
        if (t) {
            screen.setText("0");
            send.setVisibility(View.GONE);
            t = false;
        }
        Button button = (Button) view;
        String data = button.getText().toString();
        switch (data) {
            case "AC":
                input = "";
                break;
            case "Ans":
                input += Answer;
                break;
            case "*":
                Solve();
                input += "*";
                break;
            case "^":
                Solve();
                input += "^";
                break;
            case "=":
                Solve();
                send.setVisibility(View.VISIBLE);
                t = true;
                Answer = input;
                break;
            case "back":
                String newText = input.substring(0, input.length() - 1);
                input = newText;
                break;
            default:
                if (input == null) {
                    input = "";
                }
                if (data.equals("+") || data.equals("-") || data.equals("/")) {
                    Solve();
                }
                input += data;

        }
        screen.setText(input);
    }

    private void sendData(String text) {
        Intent intent = new Intent(this,SecondActivity.class);
        intent.putExtra("key",text);
        startActivity(intent);
        finish();
    }

    private void Solve() {
        if (input.split("\\*").length == 2) {
            String[] number = input.split("\\*");
            try {
                double multiply = Double.parseDouble(number[0]) * Double.parseDouble(number[1]);
                input = multiply + "";
            } catch (Exception ignored) {
            }

        } else if (input.split("/").length == 2) {
            String[] number = input.split("/");
            try {


                double division = Double.parseDouble(number[0]) / Double.parseDouble(number[1]);
                input = division + "";
            } catch (Exception ignored) {

            }

        } else if (input.split("\\^").length == 2) {
            String[] number = input.split("\\^");
            try {


                double pow = Math.pow(Double.parseDouble(number[1]), Double.parseDouble(number[1]));
                input = pow + "";
            } catch (Exception e) {

            }
        } else if (input.split("\\+").length == 2) {
            String number[] = input.split("\\+");
            try {


                double sum = Double.parseDouble(number[0]) + Double.parseDouble(number[1]);
                input = sum + "";
            } catch (Exception e) {

            }
        } else if (input.split("-").length > 1) {
            String number[] = input.split("-");
            if (number[0] == "" && number.length == 2)
                number[0] = 0 + "";
            try {


                double sub = 0;
                if (number.length > 1) {
                    sub = Double.parseDouble(number[0]) - Double.parseDouble(number[1]);
                } else if (number.length == 3) {
                    sub = Double.parseDouble(number[1]) - Double.parseDouble(number[2]);
                }
                input = sub + "";
            } catch (Exception e) {

            }
        }
        String n[] = input.split("\\.");
        if (n.length > 1) {
            if (n[1].equals("0")) {
                input = n[0];
            }

        }
        screen.setText(input);
    }
}
