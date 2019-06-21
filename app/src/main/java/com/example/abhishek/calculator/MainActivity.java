package com.example.abhishek.calculator;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tf,hi;
    String str="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int i;
        String s[]={"7","8","9","+","4","5","6","-","1","2","3","*","0",".","=","/","^","E","DEL","CLR"};
        Button[] b = initializeButtons(20);
        for (i=0;i<20;i++){
            b[i].setText(s[i]);
            b[i].setOnClickListener(this);
        }
        tf=(TextView)findViewById(R.id.textView2);
        hi=(TextView)findViewById(R.id.textView);

    }
    public Button[] initializeButtons(int x) {
        Resources res = getResources();
        Button[] buttons = new Button[x];
        for (int i = 0; i < x; i++) {
            String b = "button" + (i+1);
            buttons[i] = (Button) findViewById(res.getIdentifier(b, "id", getPackageName()));
        }
        return buttons;
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.button19){
            if(str.length()>0&&str!=null)
                str=str.substring(0,str.length()-1);

        }
        else if(v.getId()==R.id.button15){
            if(str!=null){
                hi.setText(str);
                str=calculate();
            }
        }
        else if(v.getId()==R.id.button20){
            str="";hi.setText("");
        }
        else{
            str+=((Button)findViewById(v.getId())).getText();
        }
        tf.setText(str);
    }
    public String calculate(){
        String tc=str,ans="";
        char ch;
        int l=tc.length(),c=1;
        double n1,n2,s=0;
        try {
            while (c < l && (tc.charAt(c) - 48 >= 0 && tc.charAt(c) - 48 <= 9 || tc.charAt(c) == '.' || tc.charAt(c) == 'E'))
                c++;
            if (c == l || c == l - 1)
                return tc;
            n1 = Double.parseDouble(tc.substring(0, c));
            n2 = Double.parseDouble(tc.substring(c + 1, l));
            ch = tc.charAt(c);
            switch (ch) {
                case '+':
                    s = (n1 + n2);
                    break;
                case '-':
                    s = (n1 - n2);
                    break;
                case '*':
                    s = (n1 * n2);
                    break;
                case '/':
                    s = (n1 / n2);
                    break;
                case '^':
                    s = Math.pow(n1, n2);
                    break;
            }
            ans = Double.toString(s);
            return ans;
        }
        catch (Exception e){
            return "Error";
        }
    }
}
