package com.example.gebruiker.tomverburg_pset5;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    private EditText textmsg;
    static final int READ_BLOCK_SIZE = 100;
    public static String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textmsg = (EditText) findViewById(R.id.text);

    }


    // write text to file
    public void WriteBtn(View v) {
        // add-write text into file
        try {
            String story = read();
            FileOutputStream fileout = openFileOutput("mytextfile.txt", MODE_PRIVATE);
            OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
            outputWriter.write(textmsg.getText().toString() + "," + story);
            outputWriter.close();

            //display file saved message
            Toast.makeText(getBaseContext(), "File saved successfully!",
                    Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Read text from file
    public String read() {
        //reading text from file
        //http://stackoverflow.com/questions/5283444/convert-array-of-strings-into-a-string-in-java
        try {
            FileInputStream fileIn = openFileInput("mytextfile.txt");
            InputStreamReader InputRead = new InputStreamReader(fileIn);

            //change reading
            char[] inputBuffer = new char[READ_BLOCK_SIZE];
            s = "";
            int charRead;

            while ((charRead = InputRead.read(inputBuffer)) > 0) {
                // char to string conversion
                String readstring = String.copyValueOf(inputBuffer, 0, charRead);
                s += readstring;
            }
            InputRead.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }


    public void ReadBtn(View v) {
        //reading text from file
        try {
            FileInputStream fileIn = openFileInput("mytextfile.txt");
            InputStreamReader InputRead = new InputStreamReader(fileIn);

            char[] inputBuffer = new char[READ_BLOCK_SIZE];
            String s = "";
            int charRead;

            while ((charRead = InputRead.read(inputBuffer)) > 0) {
                // char to string conversion
                String readstring = String.copyValueOf(inputBuffer, 0, charRead);
                s += readstring;
            }
            InputRead.close();
            textmsg.setText(s);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

