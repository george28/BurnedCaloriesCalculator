package hargis.burnedcaloriescalculator;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;


public class MainActivity extends Activity implements SeekBar.OnSeekBarChangeListener, OnEditorActionListener
        ,OnItemSelectedListener , OnKeyListener {

    private EditText weightEditText;
    private EditText milesRanEditText;
    private SeekBar seekBar;
    private TextView caloriesBurnedOutputTextView;
    private Spinner feetSpinner;
    private Spinner inchSpinner;
    private TextView bmiOutputTextView;

    private SharedPreferences savedValues;
    private String weightEditTextString = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weightEditText = (EditText) findViewById(R.id.wieghtEditText);
        milesRanEditText = (EditText)findViewById(R.id.milesRanEditText);
        seekBar = (SeekBar)findViewById(R.id.seekBar);
        caloriesBurnedOutputTextView = (TextView)findViewById(R.id.caloriesBurnedOutputTextView);
        feetSpinner = (Spinner)findViewById(R.id.feetSpinner);
        inchSpinner = (Spinner)findViewById(R.id.inchSpinner);
        bmiOutputTextView = (TextView)findViewById(R.id.bmiOutputTextView);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(
                this, R.array.split_array, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        feetSpinner.setAdapter(adapter1);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(
                this, R.array.split_array, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        feetSpinner.setAdapter(adapter2);

        weightEditText.setOnEditorActionListener(this);
        weightEditText.setOnKeyListener(this);
        seekBar.setOnSeekBarChangeListener(this);
        seekBar.setOnKeyListener(this);

        feetSpinner.setOnItemSelectedListener(this);
        inchSpinner.setOnItemSelectedListener(this);


        savedValues = getSharedPreferences("SavedValues", MODE_PRIVATE);

    }

    public void onPause(){

        int progress = Math.round(milesRanEditText * 100);
        seekBar.setProgress(progress);


        super.onPause();
    }
    public void calculateAndDisplay(){
        // weight entered
        weightEditTextString = weightEditText.getText().toString();
        float weightAmount;
        if(weightEditTextString.equals("")){
            weightAmount = 0;
        }
        else{
            weightAmount = Float.parseFloat(weightEditTextString);
        }

        int progress = seekBar.getProgress();
        milesRanTextView = (float) progress/ 10;

         caloriesBurnedOutputTextView = 0.75 * seekBar * weightEditText;

        bmiOutputTextView = (weightEditText * 703)/((12 * feetSpinner + inchSpinner )*(12 * feetSpinner = inchSpinner));
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        milesRanEditText.setText( );
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        calculateAndDisplay();
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        calculateAndDisplay();
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_DONE ||
            actionId == EditorInfo.IME_ACTION_UNSPECIFIED    )
        {
            calculateAndDisplay();
        }
        return false;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        return false;
    }
}
