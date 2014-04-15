package com.beaumatherne.mainpackage;

import java.text.DecimalFormat;
import java.math.RoundingMode;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
 
public class TemperatureFragment extends Fragment {
	private EditText mCelsiusField; // will reference celsius_text_field
	private EditText mFahrenheitField; // will reference fahrenheit_text_field
	private EditText mKelvinField; // will reference kelvin_text_field
	private Button mResetButton; // will reference temperature_reset_button
	private TemperatureModel temperature; // model object to hold temperature data
	private Boolean suppressFieldChanges; // use this boolean to prevent onTextChanged infinite loop
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {    	
    	// Inflate the fragment
        View rootView = inflater.inflate(R.layout.fragment_temperature, container, false);
        
        // initialize temperature
        temperature = new TemperatureModel();
        
        // initialize boolean variable
        suppressFieldChanges = false;
        
        // create decimal formatter
        final DecimalFormat temperatureFormatter = new DecimalFormat("0.0##");
        temperatureFormatter.setRoundingMode(RoundingMode.HALF_UP);
        
        // Set references to the text fields
        mCelsiusField = (EditText)rootView.findViewById(R.id.celsius_text_field);
        mFahrenheitField = (EditText)rootView.findViewById(R.id.fahrenheit_text_field);
        mKelvinField = (EditText)rootView.findViewById(R.id.kelvin_text_field);
        mResetButton = (Button)rootView.findViewById(R.id.temperature_reset_button);
        
        // Set addTextChangedListener on mCelsiusField
        mCelsiusField.addTextChangedListener(new TextWatcher() {
        	// update the other text fields with the converted values
            public void onTextChanged(CharSequence s, int start, int before, int count){
            	
            	if(!suppressFieldChanges) {
            		if(!isNumeric(mCelsiusField.getText().toString())) {
                 		// mCelsiusField's text can't be parsed to a double,
                 		// set the other text fields to empty strings
            			suppressFieldChanges = true;
            				
                 		mFahrenheitField.setText("");
                 		mKelvinField.setText("");
                     	
                 		suppressFieldChanges = false;
                 	} else {
                 		temperature.calculateFromCelsius(Double.parseDouble(mCelsiusField.getText().toString()));
                     	
                 		suppressFieldChanges = true;

    					mFahrenheitField.setText(temperatureFormatter.format(temperature.getFahrenheit()));
    					mKelvinField.setText(temperatureFormatter.format(temperature.getKelvin()));
                     	
    					suppressFieldChanges = false;
                 	}
            	}	
            };
            
            public void afterTextChanged(Editable s) {};
        	public void beforeTextChanged(CharSequence s, int start, int count, int after){};
        });
        
        // Set addTextChangedListener on mFahrenheitField
        mFahrenheitField.addTextChangedListener(new TextWatcher() {
        	// update the other text fields with the converted values
            public void onTextChanged(CharSequence s, int start, int before, int count){
            	
            	if(!suppressFieldChanges) {
            		if(!isNumeric(mFahrenheitField.getText().toString())) {
                 		// mFahrenheit's text can't be parsed to a double,
                 		// set the other text fields to empty strings
            			suppressFieldChanges = true;
            			
                 		mCelsiusField.setText("");
                 		mKelvinField.setText("");
                 		
                 		suppressFieldChanges = false;
                 	} else {
                 		temperature.calculateFromFahrenheit(Double.parseDouble(mFahrenheitField.getText().toString()));
                 		
                 		suppressFieldChanges = true;
            			
                 		mCelsiusField.setText(temperatureFormatter.format(temperature.getCelsius()));
                 		mKelvinField.setText(temperatureFormatter.format(temperature.getKelvin()));
                 		
                 		suppressFieldChanges = false;
                 	}
            	}	
            };
            
            public void afterTextChanged(Editable s) {};
        	public void beforeTextChanged(CharSequence s, int start, int count, int after){};
        });
        
     // Set addTextChangedListener on mKelvinField
        mKelvinField.addTextChangedListener(new TextWatcher() {
        	// update the other text fields with the converted values
            public void onTextChanged(CharSequence s, int start, int before, int count){
            	
            	if(!suppressFieldChanges) {
            		if(!isNumeric(mKelvinField.getText().toString())) {
                 		// mKelvinField's text can't be parsed to a double,
                 		// set the other text fields to empty strings
            			suppressFieldChanges = true;
            			
                 		mCelsiusField.setText("");
                 		mFahrenheitField.setText("");
                 		
                 		suppressFieldChanges = false;
                 	} else {
                 		temperature.calculateFromKelvin(Double.parseDouble(mKelvinField.getText().toString()));
                 		
                 		suppressFieldChanges = true;
            			
                 		mCelsiusField.setText(temperatureFormatter.format(temperature.getCelsius()));
                 		mFahrenheitField.setText(temperatureFormatter.format(temperature.getFahrenheit()));
                 		
                 		suppressFieldChanges = false;
                 	}
            	}	
            };
            
            public void afterTextChanged(Editable s) {};
        	public void beforeTextChanged(CharSequence s, int start, int count, int after){};
        });
        
        // Set onClick listener on mResetButton
        mResetButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// Call resetTextFields() to clear the text fields
				resetTextFields();	
			}
		});     

        return rootView;
    } // end method onCreateView
       
    // resetTextFields() clears the fragment's text fields
    public void resetTextFields() {
    	mCelsiusField.setText("");
    	mFahrenheitField.setText("");
    	mKelvinField.setText("");
    } // end method resetTextFields
    
    // checks to determine if a String contains a valid
    // numeric value
    public boolean isNumeric(String input) {
    	  try {
    	    Double.parseDouble(input);
    	    return true;
    	  }
    	  catch (NumberFormatException e) {
    	    // String is not numeric
    	    return false;
    	  }
    } // end method isNumeric
} // end class TemperatureFragment