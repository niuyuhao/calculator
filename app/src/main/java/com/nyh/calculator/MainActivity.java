package com.nyh.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9,
            btnClear, btnPlus, btnSubtract, btnMultiply, btnDivide, btnSum, btnPoint;
    TextView text;
    String str = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn0 = (Button) findViewById(R.id.btn0);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);
        btn7 = (Button) findViewById(R.id.btn7);
        btn8 = (Button) findViewById(R.id.btn8);
        btn9 = (Button) findViewById(R.id.btn9);
        btnClear = (Button) findViewById(R.id.btnClear);
        btnPlus = (Button) findViewById(R.id.btnPlus);
        btnSubtract = (Button) findViewById(R.id.btnSubtract);
        btnMultiply = (Button) findViewById(R.id.btnMultiply);
        btnDivide = (Button) findViewById(R.id.btnDivide);
        btnPoint = (Button) findViewById(R.id.btnPoint);
        btnSum = (Button) findViewById(R.id.btnSum);
        text = (TextView) findViewById(R.id.text);

        Button btnRm = findViewById(R.id.btnRm);

        //注册点击事件
        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btnClear.setOnClickListener(this);
        btnPlus.setOnClickListener(this);
        btnSubtract.setOnClickListener(this);
        btnMultiply.setOnClickListener(this);
        btnDivide.setOnClickListener(this);
        btnPoint.setOnClickListener(this);
        btnSum.setOnClickListener(new click());

        btnRm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String input = text.getText().toString();
        switch (v.getId()) {
            //如果点的是0 - 9 和 .  就把按钮的内容设置到textView
            case R.id.btn0:
            case R.id.btn1:
            case R.id.btn2:
            case R.id.btn3:
            case R.id.btn4:
            case R.id.btn5:
            case R.id.btn6:
            case R.id.btn7:
            case R.id.btn8:
            case R.id.btn9:
            case R.id.btnPoint:

                text.setText(input + ((Button) v).getText());
                break;

            case R.id.btnPlus:
            case R.id.btnSubtract:
            case R.id.btnMultiply:
            case R.id.btnDivide:

                text.setText(input + " " + ((Button) v).getText() + " "); //给运算符前后加空格，好判断
                break;

            case R.id.btnClear:
                text.setText("");
                break;
            case R.id.btnRm:  //删除
                if (input == null||input.equals("")){
                    Toast.makeText(MainActivity.this,"没啥删的了",Toast.LENGTH_SHORT).show();
                }else {
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i = 0; i < input.length() - 1; i++) {
                        stringBuilder.append(input.charAt(i));
                    }
                    text.setText(stringBuilder.toString());
                }
                break;
        }
    }

    class click implements View.OnClickListener {
        public void onClick(View v) {
            getResult();
        }
    }

    private void getResult() {
        String str1 = text.getText().toString();
        if (str1 == null || str1.equals("")) {  //啥也没输
            Toast.makeText(MainActivity.this,"不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        if (!str1.contains(" ")) {
            Toast.makeText(MainActivity.this,"没有输入运算符",Toast.LENGTH_SHORT).show();
            return;
        }
        if (str1.contains("=")) {  //解决得出结果之后再点击=会退出的问题  但是还存在问题
            Toast.makeText(MainActivity.this,"结果未清空",Toast.LENGTH_SHORT).show();
            return;
        }

        double result = 0;
        // 第一个数的字符串
        String s1 = str1.substring(0, str1.indexOf(" "));
        // 运算符
        String op = str1.substring(str1.indexOf(" ") + 1, str1.indexOf(" ") + 2);
        // 第二个数的字符串
        String s2 = str1.substring(str1.indexOf(" ") + 3);

        double d1 = Double.parseDouble(s1);//将数字字符串转为double类型
        double d2 = Double.parseDouble(s2);
        if (op.equals("+")) { //加法运算
            result = d1 + d2;
        } else if (op.equals("-")) { //减法运算
            result = d1 - d2;
        } else if (op.equals("*")) { //乘法运算
            result = d1 * d2;
        } else if (op.equals("/")) { //除法运算
            if (d2 == 0) { //如果被除数是0
                result = 0; //则结果是0
            } else {
                result = d1 / d2;
            }
        }

        text.setText(str1 + " = " + result); //显示计算结果

        if (!s1.contains(".") && !s2.contains(".") && !op.equals("/")) {//如果两个整数且不是除法运算
            int r = (int) result; //则结果转为整数
            text.setText(str1 + " = " + r);
        }
    }
}