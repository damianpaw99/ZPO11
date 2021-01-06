package edu.ib.zpo11lista;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Main Activity class
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Method onCreate
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /**
     * Method changing Activity to ValueActivity
     * @param view
     */
    public void onValueClick(View view) {
        TextView equText= (TextView) findViewById(R.id.etxtInput);
        String equation = equText.getText().toString();
        if(!check(equation)) {
            Polynomial p = new Polynomial(equation);
            Intent intent = new Intent(this, ValueActivity.class);
            intent.putExtra(ValueActivity.EQUATION_EXTRA, p);
            startActivity(intent);
        }
    }

    /**
     * Method changing Activity to DerivativeActivity
     * @param view
     */
    public void onDerivativeClick(View view) {
        TextView equText= (TextView) findViewById(R.id.etxtInput);
        String equation = equText.getText().toString();
        if(!check(equation)) {
            Polynomial p = new Polynomial(equation);
            Intent intent = new Intent(this, DerivativeActivity.class);
            intent.putExtra(ValueActivity.EQUATION_EXTRA, p);
            startActivity(intent);
        }
    }

    /**
     * Method checking if String contains letter
     * @param s String
     * @return Boolean value
     */
    private boolean check(String s) {
      for (int i = 0; i < s.length(); i++) {
            if (Character.isLetter(s.charAt(i))) {
                return true;
            }
        }
      return false;
    }
}