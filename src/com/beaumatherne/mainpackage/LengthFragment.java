package com.beaumatherne.mainpackage;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
 
public class LengthFragment extends Fragment {
	private EditText mMillimeterField; // will reference millimeter_text_field
	private EditText mCentimeterField; // will reference centimeter_text_field
	private EditText mInchField; // will reference inch_text_field
	private EditText mDecimeterField; // will reference decimeter_text_field
	private EditText mFeetField; // will reference feet_text_field
	private EditText mYardField; // will reference yard_text_field
	private EditText mMeterField; // will reference meter_text_field
	private EditText mKilometerField; // will reference kilometer_text_field
	private EditText mMileField; // will reference mile_text_field
	private Button mResetButton; // will reference length_reset_button
	private LengthModel length; // model object to hold length data
	private Boolean suppressFieldChanges; // use this boolean to prevent onTextChanged infinite loop
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	// inflate the fragment
        View rootView = inflater.inflate(R.layout.fragment_length, container, false);
        
        // initialize length
        length = new LengthModel();
        
        // initialize boolean variable
        suppressFieldChanges = false;
        
        // create decimal formatter with decimal notation
        final DecimalFormat weightDecimalFormatter = new DecimalFormat("0.0#####");
        weightDecimalFormatter.setRoundingMode(RoundingMode.HALF_UP);
        
        // create decimal formatter with scientific notation
        final DecimalFormat weightScientificFormatter = new DecimalFormat("0.0000E0");
        
        // set references to the text fields
        mMillimeterField = (EditText)rootView.findViewById(R.id.millimeter_text_field);
        mCentimeterField = (EditText)rootView.findViewById(R.id.centimeter_text_field);
        mInchField = (EditText)rootView.findViewById(R.id.inch_text_field);
        mDecimeterField = (EditText)rootView.findViewById(R.id.decimeter_text_field);
        mFeetField = (EditText)rootView.findViewById(R.id.feet_text_field);
        mYardField = (EditText)rootView.findViewById(R.id.yard_text_field);
        mMeterField = (EditText)rootView.findViewById(R.id.meter_text_field);
        mKilometerField = (EditText)rootView.findViewById(R.id.kilometer_text_field);
        mMileField = (EditText)rootView.findViewById(R.id.mile_text_field);
        mResetButton = (Button)rootView.findViewById(R.id.length_reset_button);
        
        // Set addTextChangedListener on mMillimeterField
        mMillimeterField.addTextChangedListener(new TextWatcher() {
        	// update the other text fields with the converted values
        	public void onTextChanged(CharSequence s, int start, int before, int count) {
        		if(!suppressFieldChanges) {
            		if(!isNumeric(mMillimeterField.getText().toString())) {
                 		// mMillimeterField's text can't be parsed to a double,
                 		// set the other text fields to empty strings
            			suppressFieldChanges = true;
            				
            			mCentimeterField.setText("");
            			mInchField.setText("");
            			mDecimeterField.setText("");
            			mFeetField.setText("");
            			mYardField.setText("");
            			mMeterField.setText("");
            			mKilometerField.setText("");
            			mMileField.setText("");
                     	
                 		suppressFieldChanges = false;
                 	} else if (Double.parseDouble(mMillimeterField.getText().toString()) == 0) {
                 		// conversions don't need to be performed on a millimeter value of 0
                 		// set the other text fields to empty strings
                 		suppressFieldChanges = true;
        				
                 		mCentimeterField.setText("");
            			mInchField.setText("");
            			mDecimeterField.setText("");
            			mFeetField.setText("");
            			mYardField.setText("");
            			mMeterField.setText("");
            			mKilometerField.setText("");
            			mMileField.setText("");
                     	
                 		suppressFieldChanges = false;
                 	} else {
                 		length.calculateFromMillimeter(Double.parseDouble(mMillimeterField.getText().toString()));

                 		// update text fields with converted values
                 		suppressFieldChanges = true;
                 		
                 		// these if statements determine if the values should be displayed
                 		// with scientific notation or decimal notation
                 		if(Math.abs(length.getCentimeter()) < 0.000001 || Math.abs(length.getCentimeter()) > 999999999.0) {
                 			mCentimeterField.setText(weightScientificFormatter.format(length.getCentimeter()));
                 		} else {
                 			mCentimeterField.setText(weightDecimalFormatter.format(length.getCentimeter()));
                 		}
             			
                 		if(Math.abs(length.getInch()) < 0.000001 || Math.abs(length.getInch()) > 999999999.0) {
                 			mInchField.setText(weightScientificFormatter.format(length.getInch()));
                 		} else {
                 			mInchField.setText(weightDecimalFormatter.format(length.getInch()));
                 		}
                 		
                 		if(Math.abs(length.getDecimeter()) < 0.000001 || Math.abs(length.getDecimeter()) > 999999999.0) {
                 			mDecimeterField.setText(weightScientificFormatter.format(length.getDecimeter()));
                 		} else {
                 			mDecimeterField.setText(weightDecimalFormatter.format(length.getDecimeter()));
                 		}
                 		
                 		if(Math.abs(length.getFeet()) < 0.000001 || Math.abs(length.getFeet()) > 999999999.0) {
                 			mFeetField.setText(weightScientificFormatter.format(length.getFeet()));
                 		} else {
                 			mFeetField.setText(weightDecimalFormatter.format(length.getFeet()));
                 		}
                 		
                 		if(Math.abs(length.getYard()) < 0.000001 || Math.abs(length.getYard()) > 999999999.0) {
                 			mYardField.setText(weightScientificFormatter.format(length.getYard()));
                 		} else {
                 			mYardField.setText(weightDecimalFormatter.format(length.getYard()));
                 		}
                 		
                 		if(Math.abs(length.getMeter()) < 0.000001 || Math.abs(length.getMeter()) > 999999999.0) {
                 			mMeterField.setText(weightScientificFormatter.format(length.getMeter()));
                 		} else {
                 			mMeterField.setText(weightDecimalFormatter.format(length.getMeter()));
                 		}
                 		
                 		if(Math.abs(length.getKilometer()) < 0.000001 || Math.abs(length.getKilometer()) > 999999999.0) {
                 			mKilometerField.setText(weightScientificFormatter.format(length.getKilometer()));
                 		} else {
                 			mKilometerField.setText(weightDecimalFormatter.format(length.getKilometer()));
                 		}
                 		
                 		if(Math.abs(length.getMile()) < 0.000001 || Math.abs(length.getMile()) > 999999999.0) {
                 			mMileField.setText(weightScientificFormatter.format(length.getMile()));
                 		} else {
                 			mMileField.setText(weightDecimalFormatter.format(length.getMile()));
                 		}
                     	
    					suppressFieldChanges = false;
                 	}
            	}
        	}
        	
        	public void afterTextChanged(Editable s) {
        		// method is not used for now
        	}
        	public void beforeTextChanged(CharSequence s, int start, int count, int after){
        		// method is not used for now
        	}
        }); // end addTextChangedListener on mMillimeterField
        
        // Set addTextChangedListener on mCentimeterField
        mCentimeterField.addTextChangedListener(new TextWatcher() {
        	// update the other text fields with the converted values
        	public void onTextChanged(CharSequence s, int start, int before, int count) {
        		if(!suppressFieldChanges) {
            		if(!isNumeric(mCentimeterField.getText().toString())) {
                 		// mCentimeterField's text can't be parsed to a double,
                 		// set the other text fields to empty strings
            			suppressFieldChanges = true;
            				
            			mMillimeterField.setText("");
            			mInchField.setText("");
            			mDecimeterField.setText("");
            			mFeetField.setText("");
            			mYardField.setText("");
            			mMeterField.setText("");
            			mKilometerField.setText("");
            			mMileField.setText("");
                     	
                 		suppressFieldChanges = false;
                 	} else if (Double.parseDouble(mCentimeterField.getText().toString()) == 0) {
                 		// conversions don't need to be performed on a centimeter value of 0
                 		// set the other text fields to empty strings
                 		suppressFieldChanges = true;
        				
                 		mMillimeterField.setText("");
            			mInchField.setText("");
            			mDecimeterField.setText("");
            			mFeetField.setText("");
            			mYardField.setText("");
            			mMeterField.setText("");
            			mKilometerField.setText("");
            			mMileField.setText("");
                     	
                 		suppressFieldChanges = false;
                 	} else {
                 		length.calculateFromCentimeter(Double.parseDouble(mCentimeterField.getText().toString()));

                 		// update text fields with converted values
                 		suppressFieldChanges = true;
                 		
                 		// these if statements determine if the values should be displayed
                 		// with scientific notation or decimal notation
                 		if(Math.abs(length.getMillimeter()) < 0.000001 || Math.abs(length.getMillimeter()) > 999999999.0) {
                 			mMillimeterField.setText(weightScientificFormatter.format(length.getMillimeter()));
                 		} else {
                 			mMillimeterField.setText(weightDecimalFormatter.format(length.getMillimeter()));
                 		}
             			
                 		if(Math.abs(length.getInch()) < 0.000001 || Math.abs(length.getInch()) > 999999999.0) {
                 			mInchField.setText(weightScientificFormatter.format(length.getInch()));
                 		} else {
                 			mInchField.setText(weightDecimalFormatter.format(length.getInch()));
                 		}
                 		
                 		if(Math.abs(length.getDecimeter()) < 0.000001 || Math.abs(length.getDecimeter()) > 999999999.0) {
                 			mDecimeterField.setText(weightScientificFormatter.format(length.getDecimeter()));
                 		} else {
                 			mDecimeterField.setText(weightDecimalFormatter.format(length.getDecimeter()));
                 		}
                 		
                 		if(Math.abs(length.getFeet()) < 0.000001 || Math.abs(length.getFeet()) > 999999999.0) {
                 			mFeetField.setText(weightScientificFormatter.format(length.getFeet()));
                 		} else {
                 			mFeetField.setText(weightDecimalFormatter.format(length.getFeet()));
                 		}
                 		
                 		if(Math.abs(length.getYard()) < 0.000001 || Math.abs(length.getYard()) > 999999999.0) {
                 			mYardField.setText(weightScientificFormatter.format(length.getYard()));
                 		} else {
                 			mYardField.setText(weightDecimalFormatter.format(length.getYard()));
                 		}
                 		
                 		if(Math.abs(length.getMeter()) < 0.000001 || Math.abs(length.getMeter()) > 999999999.0) {
                 			mMeterField.setText(weightScientificFormatter.format(length.getMeter()));
                 		} else {
                 			mMeterField.setText(weightDecimalFormatter.format(length.getMeter()));
                 		}
                 		
                 		if(Math.abs(length.getKilometer()) < 0.000001 || Math.abs(length.getKilometer()) > 999999999.0) {
                 			mKilometerField.setText(weightScientificFormatter.format(length.getKilometer()));
                 		} else {
                 			mKilometerField.setText(weightDecimalFormatter.format(length.getKilometer()));
                 		}
                 		
                 		if(Math.abs(length.getMile()) < 0.000001 || Math.abs(length.getMile()) > 999999999.0) {
                 			mMileField.setText(weightScientificFormatter.format(length.getMile()));
                 		} else {
                 			mMileField.setText(weightDecimalFormatter.format(length.getMile()));
                 		}
                     	
    					suppressFieldChanges = false;
                 	}
            	}
        	}
        	
        	public void afterTextChanged(Editable s) {
        		// method is not used for now
        	}
        	public void beforeTextChanged(CharSequence s, int start, int count, int after){
        		// method is not used for now
        	}
        }); // end addTextChangedListener on mCentimeterField
        
        // Set addTextChangedListener on mInchField
        mInchField.addTextChangedListener(new TextWatcher() {
        	// update the other text fields with the converted values
        	public void onTextChanged(CharSequence s, int start, int before, int count) {
        		if(!suppressFieldChanges) {
            		if(!isNumeric(mInchField.getText().toString())) {
                 		// mInchField's text can't be parsed to a double,
                 		// set the other text fields to empty strings
            			suppressFieldChanges = true;
            				
            			mMillimeterField.setText("");
            			mCentimeterField.setText("");
            			mDecimeterField.setText("");
            			mFeetField.setText("");
            			mYardField.setText("");
            			mMeterField.setText("");
            			mKilometerField.setText("");
            			mMileField.setText("");
                     	
                 		suppressFieldChanges = false;
                 	} else if (Double.parseDouble(mInchField.getText().toString()) == 0) {
                 		// conversions don't need to be performed on an inch value of 0
                 		// set the other text fields to empty strings
                 		suppressFieldChanges = true;
        				
                 		mMillimeterField.setText("");
                 		mCentimeterField.setText("");
            			mDecimeterField.setText("");
            			mFeetField.setText("");
            			mYardField.setText("");
            			mMeterField.setText("");
            			mKilometerField.setText("");
            			mMileField.setText("");
                     	
                 		suppressFieldChanges = false;
                 	} else {
                 		length.calculateFromInch(Double.parseDouble(mInchField.getText().toString()));

                 		// update text fields with converted values
                 		suppressFieldChanges = true;
                 		
                 		// these if statements determine if the values should be displayed
                 		// with scientific notation or decimal notation
                 		if(Math.abs(length.getMillimeter()) < 0.000001 || Math.abs(length.getMillimeter()) > 999999999.0) {
                 			mMillimeterField.setText(weightScientificFormatter.format(length.getMillimeter()));
                 		} else {
                 			mMillimeterField.setText(weightDecimalFormatter.format(length.getMillimeter()));
                 		}
             			
                 		if(Math.abs(length.getCentimeter()) < 0.000001 || Math.abs(length.getCentimeter()) > 999999999.0) {
                 			mCentimeterField.setText(weightScientificFormatter.format(length.getCentimeter()));
                 		} else {
                 			mCentimeterField.setText(weightDecimalFormatter.format(length.getCentimeter()));
                 		}
                 		
                 		if(Math.abs(length.getDecimeter()) < 0.000001 || Math.abs(length.getDecimeter()) > 999999999.0) {
                 			mDecimeterField.setText(weightScientificFormatter.format(length.getDecimeter()));
                 		} else {
                 			mDecimeterField.setText(weightDecimalFormatter.format(length.getDecimeter()));
                 		}
                 		
                 		if(Math.abs(length.getFeet()) < 0.000001 || Math.abs(length.getFeet()) > 999999999.0) {
                 			mFeetField.setText(weightScientificFormatter.format(length.getFeet()));
                 		} else {
                 			mFeetField.setText(weightDecimalFormatter.format(length.getFeet()));
                 		}
                 		
                 		if(Math.abs(length.getYard()) < 0.000001 || Math.abs(length.getYard()) > 999999999.0) {
                 			mYardField.setText(weightScientificFormatter.format(length.getYard()));
                 		} else {
                 			mYardField.setText(weightDecimalFormatter.format(length.getYard()));
                 		}
                 		
                 		if(Math.abs(length.getMeter()) < 0.000001 || Math.abs(length.getMeter()) > 999999999.0) {
                 			mMeterField.setText(weightScientificFormatter.format(length.getMeter()));
                 		} else {
                 			mMeterField.setText(weightDecimalFormatter.format(length.getMeter()));
                 		}
                 		
                 		if(Math.abs(length.getKilometer()) < 0.000001 || Math.abs(length.getKilometer()) > 999999999.0) {
                 			mKilometerField.setText(weightScientificFormatter.format(length.getKilometer()));
                 		} else {
                 			mKilometerField.setText(weightDecimalFormatter.format(length.getKilometer()));
                 		}
                 		
                 		if(Math.abs(length.getMile()) < 0.000001 || Math.abs(length.getMile()) > 999999999.0) {
                 			mMileField.setText(weightScientificFormatter.format(length.getMile()));
                 		} else {
                 			mMileField.setText(weightDecimalFormatter.format(length.getMile()));
                 		}
                     	
    					suppressFieldChanges = false;
                 	}
            	}
        	}
        	
        	public void afterTextChanged(Editable s) {
        		// method is not used for now
        	}
        	public void beforeTextChanged(CharSequence s, int start, int count, int after){
        		// method is not used for now
        	}
        }); // end addTextChangedListener on mInchField
        
        // Set addTextChangedListener on mDecimeterField
        mDecimeterField.addTextChangedListener(new TextWatcher() {
        	// update the other text fields with the converted values
        	public void onTextChanged(CharSequence s, int start, int before, int count) {
        		if(!suppressFieldChanges) {
            		if(!isNumeric(mDecimeterField.getText().toString())) {
                 		// mDecimeterField's text can't be parsed to a double,
                 		// set the other text fields to empty strings
            			suppressFieldChanges = true;
            				
            			mMillimeterField.setText("");
            			mCentimeterField.setText("");
            			mInchField.setText("");
            			mFeetField.setText("");
            			mYardField.setText("");
            			mMeterField.setText("");
            			mKilometerField.setText("");
            			mMileField.setText("");
                     	
                 		suppressFieldChanges = false;
                 	} else if (Double.parseDouble(mDecimeterField.getText().toString()) == 0) {
                 		// conversions don't need to be performed on an inch value of 0
                 		// set the other text fields to empty strings
                 		suppressFieldChanges = true;
        				
                 		mMillimeterField.setText("");
                 		mCentimeterField.setText("");
                 		mInchField.setText("");
            			mFeetField.setText("");
            			mYardField.setText("");
            			mMeterField.setText("");
            			mKilometerField.setText("");
            			mMileField.setText("");
                     	
                 		suppressFieldChanges = false;
                 	} else {
                 		length.calculateFromDecimeter(Double.parseDouble(mDecimeterField.getText().toString()));

                 		// update text fields with converted values
                 		suppressFieldChanges = true;
                 		
                 		// these if statements determine if the values should be displayed
                 		// with scientific notation or decimal notation
                 		if(Math.abs(length.getMillimeter()) < 0.000001 || Math.abs(length.getMillimeter()) > 999999999.0) {
                 			mMillimeterField.setText(weightScientificFormatter.format(length.getMillimeter()));
                 		} else {
                 			mMillimeterField.setText(weightDecimalFormatter.format(length.getMillimeter()));
                 		}
             			
                 		if(Math.abs(length.getCentimeter()) < 0.000001 || Math.abs(length.getCentimeter()) > 999999999.0) {
                 			mCentimeterField.setText(weightScientificFormatter.format(length.getCentimeter()));
                 		} else {
                 			mCentimeterField.setText(weightDecimalFormatter.format(length.getCentimeter()));
                 		}
                 		
                 		if(Math.abs(length.getInch()) < 0.000001 || Math.abs(length.getInch()) > 999999999.0) {
                 			mInchField.setText(weightScientificFormatter.format(length.getInch()));
                 		} else {
                 			mInchField.setText(weightDecimalFormatter.format(length.getInch()));
                 		}
                 		
                 		if(Math.abs(length.getFeet()) < 0.000001 || Math.abs(length.getFeet()) > 999999999.0) {
                 			mFeetField.setText(weightScientificFormatter.format(length.getFeet()));
                 		} else {
                 			mFeetField.setText(weightDecimalFormatter.format(length.getFeet()));
                 		}
                 		
                 		if(Math.abs(length.getYard()) < 0.000001 || Math.abs(length.getYard()) > 999999999.0) {
                 			mYardField.setText(weightScientificFormatter.format(length.getYard()));
                 		} else {
                 			mYardField.setText(weightDecimalFormatter.format(length.getYard()));
                 		}
                 		
                 		if(Math.abs(length.getMeter()) < 0.000001 || Math.abs(length.getMeter()) > 999999999.0) {
                 			mMeterField.setText(weightScientificFormatter.format(length.getMeter()));
                 		} else {
                 			mMeterField.setText(weightDecimalFormatter.format(length.getMeter()));
                 		}
                 		
                 		if(Math.abs(length.getKilometer()) < 0.000001 || Math.abs(length.getKilometer()) > 999999999.0) {
                 			mKilometerField.setText(weightScientificFormatter.format(length.getKilometer()));
                 		} else {
                 			mKilometerField.setText(weightDecimalFormatter.format(length.getKilometer()));
                 		}
                 		
                 		if(Math.abs(length.getMile()) < 0.000001 || Math.abs(length.getMile()) > 999999999.0) {
                 			mMileField.setText(weightScientificFormatter.format(length.getMile()));
                 		} else {
                 			mMileField.setText(weightDecimalFormatter.format(length.getMile()));
                 		}
                     	
    					suppressFieldChanges = false;
                 	}
            	}
        	}
        	
        	public void afterTextChanged(Editable s) {
        		// method is not used for now
        	}
        	public void beforeTextChanged(CharSequence s, int start, int count, int after){
        		// method is not used for now
        	}
        }); // end addTextChangedListener on mDecimeterField
        
        // Set addTextChangedListener on mFeetField
        mFeetField.addTextChangedListener(new TextWatcher() {
        	// update the other text fields with the converted values
        	public void onTextChanged(CharSequence s, int start, int before, int count) {
        		if(!suppressFieldChanges) {
            		if(!isNumeric(mFeetField.getText().toString())) {
                 		// mFeetField's text can't be parsed to a double,
                 		// set the other text fields to empty strings
            			suppressFieldChanges = true;
            				
            			mMillimeterField.setText("");
            			mCentimeterField.setText("");
            			mInchField.setText("");
            			mDecimeterField.setText("");
            			mYardField.setText("");
            			mMeterField.setText("");
            			mKilometerField.setText("");
            			mMileField.setText("");
                     	
                 		suppressFieldChanges = false;
                 	} else if (Double.parseDouble(mFeetField.getText().toString()) == 0) {
                 		// conversions don't need to be performed on an inch value of 0
                 		// set the other text fields to empty strings
                 		suppressFieldChanges = true;
        				
                 		mMillimeterField.setText("");
                 		mCentimeterField.setText("");
                 		mInchField.setText("");
                 		mDecimeterField.setText("");
            			mYardField.setText("");
            			mMeterField.setText("");
            			mKilometerField.setText("");
            			mMileField.setText("");
                     	
                 		suppressFieldChanges = false;
                 	} else {
                 		length.calculateFromFeet(Double.parseDouble(mFeetField.getText().toString()));

                 		// update text fields with converted values
                 		suppressFieldChanges = true;
                 		
                 		// these if statements determine if the values should be displayed
                 		// with scientific notation or decimal notation
                 		if(Math.abs(length.getMillimeter()) < 0.000001 || Math.abs(length.getMillimeter()) > 999999999.0) {
                 			mMillimeterField.setText(weightScientificFormatter.format(length.getMillimeter()));
                 		} else {
                 			mMillimeterField.setText(weightDecimalFormatter.format(length.getMillimeter()));
                 		}
             			
                 		if(Math.abs(length.getCentimeter()) < 0.000001 || Math.abs(length.getCentimeter()) > 999999999.0) {
                 			mCentimeterField.setText(weightScientificFormatter.format(length.getCentimeter()));
                 		} else {
                 			mCentimeterField.setText(weightDecimalFormatter.format(length.getCentimeter()));
                 		}
                 		
                 		if(Math.abs(length.getInch()) < 0.000001 || Math.abs(length.getInch()) > 999999999.0) {
                 			mInchField.setText(weightScientificFormatter.format(length.getInch()));
                 		} else {
                 			mInchField.setText(weightDecimalFormatter.format(length.getInch()));
                 		}
                 		
                 		if(Math.abs(length.getDecimeter()) < 0.000001 || Math.abs(length.getDecimeter()) > 999999999.0) {
                 			mDecimeterField.setText(weightScientificFormatter.format(length.getDecimeter()));
                 		} else {
                 			mDecimeterField.setText(weightDecimalFormatter.format(length.getDecimeter()));
                 		}
                 		
                 		if(Math.abs(length.getYard()) < 0.000001 || Math.abs(length.getYard()) > 999999999.0) {
                 			mYardField.setText(weightScientificFormatter.format(length.getYard()));
                 		} else {
                 			mYardField.setText(weightDecimalFormatter.format(length.getYard()));
                 		}
                 		
                 		if(Math.abs(length.getMeter()) < 0.000001 || Math.abs(length.getMeter()) > 999999999.0) {
                 			mMeterField.setText(weightScientificFormatter.format(length.getMeter()));
                 		} else {
                 			mMeterField.setText(weightDecimalFormatter.format(length.getMeter()));
                 		}
                 		
                 		if(Math.abs(length.getKilometer()) < 0.000001 || Math.abs(length.getKilometer()) > 999999999.0) {
                 			mKilometerField.setText(weightScientificFormatter.format(length.getKilometer()));
                 		} else {
                 			mKilometerField.setText(weightDecimalFormatter.format(length.getKilometer()));
                 		}
                 		
                 		if(Math.abs(length.getMile()) < 0.000001 || Math.abs(length.getMile()) > 999999999.0) {
                 			mMileField.setText(weightScientificFormatter.format(length.getMile()));
                 		} else {
                 			mMileField.setText(weightDecimalFormatter.format(length.getMile()));
                 		}
                     	
    					suppressFieldChanges = false;
                 	}
            	}
        	}
        	
        	public void afterTextChanged(Editable s) {
        		// method is not used for now
        	}
        	public void beforeTextChanged(CharSequence s, int start, int count, int after){
        		// method is not used for now
        	}
        }); // end addTextChangedListener on mFeetField
        
        // Set addTextChangedListener on mYardField
        mYardField.addTextChangedListener(new TextWatcher() {
        	// update the other text fields with the converted values
        	public void onTextChanged(CharSequence s, int start, int before, int count) {
        		if(!suppressFieldChanges) {
            		if(!isNumeric(mYardField.getText().toString())) {
                 		// mYardField's text can't be parsed to a double,
                 		// set the other text fields to empty strings
            			suppressFieldChanges = true;
            				
            			mMillimeterField.setText("");
            			mCentimeterField.setText("");
            			mInchField.setText("");
            			mDecimeterField.setText("");
            			mFeetField.setText("");
            			mMeterField.setText("");
            			mKilometerField.setText("");
            			mMileField.setText("");
                     	
                 		suppressFieldChanges = false;
                 	} else if (Double.parseDouble(mYardField.getText().toString()) == 0) {
                 		// conversions don't need to be performed on a yard value of 0
                 		// set the other text fields to empty strings
                 		suppressFieldChanges = true;
        				
                 		mMillimeterField.setText("");
                 		mCentimeterField.setText("");
                 		mInchField.setText("");
                 		mDecimeterField.setText("");
                 		mFeetField.setText("");
            			mMeterField.setText("");
            			mKilometerField.setText("");
            			mMileField.setText("");
                     	
                 		suppressFieldChanges = false;
                 	} else {
                 		length.calculateFromYard(Double.parseDouble(mYardField.getText().toString()));

                 		// update text fields with converted values
                 		suppressFieldChanges = true;
                 		
                 		// these if statements determine if the values should be displayed
                 		// with scientific notation or decimal notation
                 		if(Math.abs(length.getMillimeter()) < 0.000001 || Math.abs(length.getMillimeter()) > 999999999.0) {
                 			mMillimeterField.setText(weightScientificFormatter.format(length.getMillimeter()));
                 		} else {
                 			mMillimeterField.setText(weightDecimalFormatter.format(length.getMillimeter()));
                 		}
             			
                 		if(Math.abs(length.getCentimeter()) < 0.000001 || Math.abs(length.getCentimeter()) > 999999999.0) {
                 			mCentimeterField.setText(weightScientificFormatter.format(length.getCentimeter()));
                 		} else {
                 			mCentimeterField.setText(weightDecimalFormatter.format(length.getCentimeter()));
                 		}
                 		
                 		if(Math.abs(length.getInch()) < 0.000001 || Math.abs(length.getInch()) > 999999999.0) {
                 			mInchField.setText(weightScientificFormatter.format(length.getInch()));
                 		} else {
                 			mInchField.setText(weightDecimalFormatter.format(length.getInch()));
                 		}
                 		
                 		if(Math.abs(length.getDecimeter()) < 0.000001 || Math.abs(length.getDecimeter()) > 999999999.0) {
                 			mDecimeterField.setText(weightScientificFormatter.format(length.getDecimeter()));
                 		} else {
                 			mDecimeterField.setText(weightDecimalFormatter.format(length.getDecimeter()));
                 		}
                 		
                 		if(Math.abs(length.getFeet()) < 0.000001 || Math.abs(length.getFeet()) > 999999999.0) {
                 			mFeetField.setText(weightScientificFormatter.format(length.getFeet()));
                 		} else {
                 			mFeetField.setText(weightDecimalFormatter.format(length.getFeet()));
                 		}
                 		
                 		if(Math.abs(length.getMeter()) < 0.000001 || Math.abs(length.getMeter()) > 999999999.0) {
                 			mMeterField.setText(weightScientificFormatter.format(length.getMeter()));
                 		} else {
                 			mMeterField.setText(weightDecimalFormatter.format(length.getMeter()));
                 		}
                 		
                 		if(Math.abs(length.getKilometer()) < 0.000001 || Math.abs(length.getKilometer()) > 999999999.0) {
                 			mKilometerField.setText(weightScientificFormatter.format(length.getKilometer()));
                 		} else {
                 			mKilometerField.setText(weightDecimalFormatter.format(length.getKilometer()));
                 		}
                 		
                 		if(Math.abs(length.getMile()) < 0.000001 || Math.abs(length.getMile()) > 999999999.0) {
                 			mMileField.setText(weightScientificFormatter.format(length.getMile()));
                 		} else {
                 			mMileField.setText(weightDecimalFormatter.format(length.getMile()));
                 		}
                     	
    					suppressFieldChanges = false;
                 	}
            	}
        	}
        	
        	public void afterTextChanged(Editable s) {
        		// method is not used for now
        	}
        	public void beforeTextChanged(CharSequence s, int start, int count, int after){
        		// method is not used for now
        	}
        }); // end addTextChangedListener on mYardField
        
        // Set addTextChangedListener on mMeterField
        mMeterField.addTextChangedListener(new TextWatcher() {
        	// update the other text fields with the converted values
        	public void onTextChanged(CharSequence s, int start, int before, int count) {
        		if(!suppressFieldChanges) {
            		if(!isNumeric(mMeterField.getText().toString())) {
                 		// mMeterField's text can't be parsed to a double,
                 		// set the other text fields to empty strings
            			suppressFieldChanges = true;
            				
            			mMillimeterField.setText("");
            			mCentimeterField.setText("");
            			mInchField.setText("");
            			mDecimeterField.setText("");
            			mFeetField.setText("");
            			mYardField.setText("");
            			mKilometerField.setText("");
            			mMileField.setText("");
                     	
                 		suppressFieldChanges = false;
                 	} else if (Double.parseDouble(mMeterField.getText().toString()) == 0) {
                 		// conversions don't need to be performed on a meter value of 0
                 		// set the other text fields to empty strings
                 		suppressFieldChanges = true;
        				
                 		mMillimeterField.setText("");
                 		mCentimeterField.setText("");
                 		mInchField.setText("");
                 		mDecimeterField.setText("");
                 		mFeetField.setText("");
                 		mYardField.setText("");
            			mKilometerField.setText("");
            			mMileField.setText("");
                     	
                 		suppressFieldChanges = false;
                 	} else {
                 		length.calculateFromMeter(Double.parseDouble(mMeterField.getText().toString()));

                 		// update text fields with converted values
                 		suppressFieldChanges = true;
                 		
                 		// these if statements determine if the values should be displayed
                 		// with scientific notation or decimal notation
                 		if(Math.abs(length.getMillimeter()) < 0.000001 || Math.abs(length.getMillimeter()) > 999999999.0) {
                 			mMillimeterField.setText(weightScientificFormatter.format(length.getMillimeter()));
                 		} else {
                 			mMillimeterField.setText(weightDecimalFormatter.format(length.getMillimeter()));
                 		}
             			
                 		if(Math.abs(length.getCentimeter()) < 0.000001 || Math.abs(length.getCentimeter()) > 999999999.0) {
                 			mCentimeterField.setText(weightScientificFormatter.format(length.getCentimeter()));
                 		} else {
                 			mCentimeterField.setText(weightDecimalFormatter.format(length.getCentimeter()));
                 		}
                 		
                 		if(Math.abs(length.getInch()) < 0.000001 || Math.abs(length.getInch()) > 999999999.0) {
                 			mInchField.setText(weightScientificFormatter.format(length.getInch()));
                 		} else {
                 			mInchField.setText(weightDecimalFormatter.format(length.getInch()));
                 		}
                 		
                 		if(Math.abs(length.getDecimeter()) < 0.000001 || Math.abs(length.getDecimeter()) > 999999999.0) {
                 			mDecimeterField.setText(weightScientificFormatter.format(length.getDecimeter()));
                 		} else {
                 			mDecimeterField.setText(weightDecimalFormatter.format(length.getDecimeter()));
                 		}
                 		
                 		if(Math.abs(length.getFeet()) < 0.000001 || Math.abs(length.getFeet()) > 999999999.0) {
                 			mFeetField.setText(weightScientificFormatter.format(length.getFeet()));
                 		} else {
                 			mFeetField.setText(weightDecimalFormatter.format(length.getFeet()));
                 		}
                 		
                 		if(Math.abs(length.getYard()) < 0.000001 || Math.abs(length.getYard()) > 999999999.0) {
                 			mYardField.setText(weightScientificFormatter.format(length.getYard()));
                 		} else {
                 			mYardField.setText(weightDecimalFormatter.format(length.getYard()));
                 		}
                 		
                 		if(Math.abs(length.getKilometer()) < 0.000001 || Math.abs(length.getKilometer()) > 999999999.0) {
                 			mKilometerField.setText(weightScientificFormatter.format(length.getKilometer()));
                 		} else {
                 			mKilometerField.setText(weightDecimalFormatter.format(length.getKilometer()));
                 		}
                 		
                 		if(Math.abs(length.getMile()) < 0.000001 || Math.abs(length.getMile()) > 999999999.0) {
                 			mMileField.setText(weightScientificFormatter.format(length.getMile()));
                 		} else {
                 			mMileField.setText(weightDecimalFormatter.format(length.getMile()));
                 		}
                     	
    					suppressFieldChanges = false;
                 	}
            	}
        	}
        	
        	public void afterTextChanged(Editable s) {
        		// method is not used for now
        	}
        	public void beforeTextChanged(CharSequence s, int start, int count, int after){
        		// method is not used for now
        	}
        }); // end addTextChangedListener on mMeterField
        
        // Set addTextChangedListener on mKilometerField
        mKilometerField.addTextChangedListener(new TextWatcher() {
        	// update the other text fields with the converted values
        	public void onTextChanged(CharSequence s, int start, int before, int count) {
        		if(!suppressFieldChanges) {
            		if(!isNumeric(mKilometerField.getText().toString())) {
                 		// mKilometerField's text can't be parsed to a double,
                 		// set the other text fields to empty strings
            			suppressFieldChanges = true;
            				
            			mMillimeterField.setText("");
            			mCentimeterField.setText("");
            			mInchField.setText("");
            			mDecimeterField.setText("");
            			mFeetField.setText("");
            			mYardField.setText("");
            			mMeterField.setText("");
            			mMileField.setText("");
                     	
                 		suppressFieldChanges = false;
                 	} else if (Double.parseDouble(mKilometerField.getText().toString()) == 0) {
                 		// conversions don't need to be performed on a kilometer value of 0
                 		// set the other text fields to empty strings
                 		suppressFieldChanges = true;
        				
                 		mMillimeterField.setText("");
                 		mCentimeterField.setText("");
                 		mInchField.setText("");
                 		mDecimeterField.setText("");
                 		mFeetField.setText("");
                 		mYardField.setText("");
                 		mMeterField.setText("");
            			mMileField.setText("");
                     	
                 		suppressFieldChanges = false;
                 	} else {
                 		length.calculateFromKilometer(Double.parseDouble(mKilometerField.getText().toString()));

                 		// update text fields with converted values
                 		suppressFieldChanges = true;
                 		
                 		// these if statements determine if the values should be displayed
                 		// with scientific notation or decimal notation
                 		if(Math.abs(length.getMillimeter()) < 0.000001 || Math.abs(length.getMillimeter()) > 999999999.0) {
                 			mMillimeterField.setText(weightScientificFormatter.format(length.getMillimeter()));
                 		} else {
                 			mMillimeterField.setText(weightDecimalFormatter.format(length.getMillimeter()));
                 		}
             			
                 		if(Math.abs(length.getCentimeter()) < 0.000001 || Math.abs(length.getCentimeter()) > 999999999.0) {
                 			mCentimeterField.setText(weightScientificFormatter.format(length.getCentimeter()));
                 		} else {
                 			mCentimeterField.setText(weightDecimalFormatter.format(length.getCentimeter()));
                 		}
                 		
                 		if(Math.abs(length.getInch()) < 0.000001 || Math.abs(length.getInch()) > 999999999.0) {
                 			mInchField.setText(weightScientificFormatter.format(length.getInch()));
                 		} else {
                 			mInchField.setText(weightDecimalFormatter.format(length.getInch()));
                 		}
                 		
                 		if(Math.abs(length.getDecimeter()) < 0.000001 || Math.abs(length.getDecimeter()) > 999999999.0) {
                 			mDecimeterField.setText(weightScientificFormatter.format(length.getDecimeter()));
                 		} else {
                 			mDecimeterField.setText(weightDecimalFormatter.format(length.getDecimeter()));
                 		}
                 		
                 		if(Math.abs(length.getFeet()) < 0.000001 || Math.abs(length.getFeet()) > 999999999.0) {
                 			mFeetField.setText(weightScientificFormatter.format(length.getFeet()));
                 		} else {
                 			mFeetField.setText(weightDecimalFormatter.format(length.getFeet()));
                 		}
                 		
                 		if(Math.abs(length.getYard()) < 0.000001 || Math.abs(length.getYard()) > 999999999.0) {
                 			mYardField.setText(weightScientificFormatter.format(length.getYard()));
                 		} else {
                 			mYardField.setText(weightDecimalFormatter.format(length.getYard()));
                 		}
                 		
                 		if(Math.abs(length.getMeter()) < 0.000001 || Math.abs(length.getMeter()) > 999999999.0) {
                 			mMeterField.setText(weightScientificFormatter.format(length.getMeter()));
                 		} else {
                 			mMeterField.setText(weightDecimalFormatter.format(length.getMeter()));
                 		}
                 		
                 		if(Math.abs(length.getMile()) < 0.000001 || Math.abs(length.getMile()) > 999999999.0) {
                 			mMileField.setText(weightScientificFormatter.format(length.getMile()));
                 		} else {
                 			mMileField.setText(weightDecimalFormatter.format(length.getMile()));
                 		}
                     	
    					suppressFieldChanges = false;
                 	}
            	}
        	}
        	
        	public void afterTextChanged(Editable s) {
        		// method is not used for now
        	}
        	public void beforeTextChanged(CharSequence s, int start, int count, int after){
        		// method is not used for now
        	}
        }); // end addTextChangedListener on mKilometerField
        
        // Set addTextChangedListener on mMileField
        mMileField.addTextChangedListener(new TextWatcher() {
        	// update the other text fields with the converted values
        	public void onTextChanged(CharSequence s, int start, int before, int count) {
        		if(!suppressFieldChanges) {
            		if(!isNumeric(mMileField.getText().toString())) {
                 		// mMileField's text can't be parsed to a double,
                 		// set the other text fields to empty strings
            			suppressFieldChanges = true;
            				
            			mMillimeterField.setText("");
            			mCentimeterField.setText("");
            			mInchField.setText("");
            			mDecimeterField.setText("");
            			mFeetField.setText("");
            			mYardField.setText("");
            			mMeterField.setText("");
            			mKilometerField.setText("");
                     	
                 		suppressFieldChanges = false;
                 	} else if (Double.parseDouble(mMileField.getText().toString()) == 0) {
                 		// conversions don't need to be performed on a mile value of 0
                 		// set the other text fields to empty strings
                 		suppressFieldChanges = true;
        				
                 		mMillimeterField.setText("");
                 		mCentimeterField.setText("");
                 		mInchField.setText("");
                 		mDecimeterField.setText("");
                 		mFeetField.setText("");
                 		mYardField.setText("");
                 		mMeterField.setText("");
                 		mKilometerField.setText("");
                     	
                 		suppressFieldChanges = false;
                 	} else {
                 		length.calculateFromMile(Double.parseDouble(mMileField.getText().toString()));

                 		// update text fields with converted values
                 		suppressFieldChanges = true;
                 		
                 		// these if statements determine if the values should be displayed
                 		// with scientific notation or decimal notation
                 		if(Math.abs(length.getMillimeter()) < 0.000001 || Math.abs(length.getMillimeter()) > 999999999.0) {
                 			mMillimeterField.setText(weightScientificFormatter.format(length.getMillimeter()));
                 		} else {
                 			mMillimeterField.setText(weightDecimalFormatter.format(length.getMillimeter()));
                 		}
             			
                 		if(Math.abs(length.getCentimeter()) < 0.000001 || Math.abs(length.getCentimeter()) > 999999999.0) {
                 			mCentimeterField.setText(weightScientificFormatter.format(length.getCentimeter()));
                 		} else {
                 			mCentimeterField.setText(weightDecimalFormatter.format(length.getCentimeter()));
                 		}
                 		
                 		if(Math.abs(length.getInch()) < 0.000001 || Math.abs(length.getInch()) > 999999999.0) {
                 			mInchField.setText(weightScientificFormatter.format(length.getInch()));
                 		} else {
                 			mInchField.setText(weightDecimalFormatter.format(length.getInch()));
                 		}
                 		
                 		if(Math.abs(length.getDecimeter()) < 0.000001 || Math.abs(length.getDecimeter()) > 999999999.0) {
                 			mDecimeterField.setText(weightScientificFormatter.format(length.getDecimeter()));
                 		} else {
                 			mDecimeterField.setText(weightDecimalFormatter.format(length.getDecimeter()));
                 		}
                 		
                 		if(Math.abs(length.getFeet()) < 0.000001 || Math.abs(length.getFeet()) > 999999999.0) {
                 			mFeetField.setText(weightScientificFormatter.format(length.getFeet()));
                 		} else {
                 			mFeetField.setText(weightDecimalFormatter.format(length.getFeet()));
                 		}
                 		
                 		if(Math.abs(length.getYard()) < 0.000001 || Math.abs(length.getYard()) > 999999999.0) {
                 			mYardField.setText(weightScientificFormatter.format(length.getYard()));
                 		} else {
                 			mYardField.setText(weightDecimalFormatter.format(length.getYard()));
                 		}
                 		
                 		if(Math.abs(length.getMeter()) < 0.000001 || Math.abs(length.getMeter()) > 999999999.0) {
                 			mMeterField.setText(weightScientificFormatter.format(length.getMeter()));
                 		} else {
                 			mMeterField.setText(weightDecimalFormatter.format(length.getMeter()));
                 		}
                 		
                 		if(Math.abs(length.getKilometer()) < 0.000001 || Math.abs(length.getKilometer()) > 999999999.0) {
                 			mKilometerField.setText(weightScientificFormatter.format(length.getKilometer()));
                 		} else {
                 			mKilometerField.setText(weightDecimalFormatter.format(length.getKilometer()));
                 		}
                     	
    					suppressFieldChanges = false;
                 	}
            	}
        	}
        	
        	public void afterTextChanged(Editable s) {
        		// method is not used for now
        	}
        	public void beforeTextChanged(CharSequence s, int start, int count, int after){
        		// method is not used for now
        	}
        }); // end addTextChangedListener on mMileField
        
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
    	mMillimeterField.setText("");
    	mCentimeterField.setText("");
    	mInchField.setText("");
    	mDecimeterField.setText("");
    	mFeetField.setText("");
    	mYardField.setText("");
    	mMeterField.setText("");
    	mKilometerField.setText("");
    	mMileField.setText("");
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
} // end class LengthFragment