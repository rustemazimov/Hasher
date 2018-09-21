package com.example.rustem.hasher;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import static java.security.AccessController.getContext;

/**
 * @author Rustem Azimov
 *
 */
public class MainActivity extends AppCompatActivity {
    /*
     * Function class instance which contains a method for hashing with
     * MD5, SHA-1, SHA-256, SHA-512, BCRYPT, PBKDF-2
     */
    private Function functions = new Function();

    //Text area for giving output (hashed value of plain text
    private EditText inputTxtView, outputTxtView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Options for different hashing methods
        final String[] options= new String[]
                {
                        "Select",
                        Function.MD5,
                        Function.SHA1,
                        Function.SHA256,
                        Function.SHA512/*,
                        Function.BCRYPT,
                        Function.PBKDF2*/
                };

        //Specifies a method option for hashing
        Spinner s = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, options);
        s.setAdapter(adapter);
        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                outputTxtView = (EditText)findViewById(R.id.resultView);
                inputTxtView = (EditText)findViewById(R.id.inputTxt);
                String inputTxt = inputTxtView.getText().toString();
                /*
                 * If "select" option was selected:
                 *               output = ""
                 */
                outputTxtView.setText(position == 0 ? "" : functions.hashText(inputTxt, options[position]));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                showMessage("Nothing was selected");
            }
        });
        //Handling copy button's action
        ((Button)findViewById(R.id.copyButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(outputTxtView != null && !outputTxtView.getText().toString().equals(""))
                {
                    /*
                     * if the selected option isn't "Select" and NullPointerException won' occur
                     * copy hashed text to clipboard
                     */
                    copy("hashedTxt", outputTxtView.getText().toString());
                }
            }
        });
    }
    private void showMessage(String txt){
        Toast.makeText(MainActivity.this, txt, Toast.LENGTH_LONG).show();
    }
    private void copy(String label, String txt){
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText(label, txt);
        clipboard.setPrimaryClip(clip);
        showMessage("Copied to clipboard");
    }
}
