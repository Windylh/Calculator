package com.example.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.util.Log;

public class MainActivity extends AppCompatActivity
{

    private TextView in = null;
    private TextView out = null;
    private boolean signal = false;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        in = findViewById(R.id.in);
        out = findViewById(R.id.out);
    }

    private void del()
    {
        int length = in.getText().length();
        if(length>0)
        {
            int end = length - 1;
            in.setText(in.getText().subSequence(0, end));
        }
    }

    private boolean isOperator(String chr)
    {
        // Log.v("test",chr);
        return ((chr.equals("+") || chr.equals("-") || chr.equals("*") ||chr.equals("/")));
    }
    public void onClick(View btn)
    {
        String expr = ((Button) btn).getText().toString();
        switch(expr)
        {
            default:
                int length = in.getText().length();
                if(isOperator(expr))
                {
                     if(signal)
                     {
                         if(!out.getText().toString().equals("NaN"))
                             in.setText(out.getText());
                         else
                             in.setText(null);
                         out.setText(null);
                         signal = false;
                     }
                     else  if(length > 0 && isOperator(in.getText().toString().substring(length-1)))
                         del();
                }
                else
                {
                    if(signal)
                    {
                        in.setText(null);
                        out.setText(null);
                        signal = false;
                    }

                }
                in.setText(in.getText()+expr);break;
            case "Del":
                del();break;
            case "c":
                in.setText("");out.setText("");break;
            case "=":
                Double result = Calculator.conversion(in.getText().toString());
                out.setText(result.toString());signal = true;break;
        }
    }
}
