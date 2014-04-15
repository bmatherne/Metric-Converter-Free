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
 
public class AreaFragment extends Fragment {
	private EditText mSquareMillimeterField; // will reference square_millimeter_text_field
	private EditText mSquareCentimeterField; // will reference square_centimeter_text_field
	private EditText mSquareInchField; // will reference square_inch_text_field
	private EditText mSquareFeetField; // will reference square_feet_text_field
	private EditText mSquareYardField; // will reference square_yard_text_field
	private EditText mSquareMeterField; // will reference square_meter_text_field
	private EditText mAcreField; // will reference acre_text_field
	private EditText mSquareKilometerField; // will reference square_kilometer_text_field
	private EditText mSquareMileField; // will reference square_mile_text_field
	private Button mResetButton; // will reference area_reset_button
	private AreaModel area; // model object to hold area data
	private Boolean suppressFieldChanges; // use this boolean to prevent onTextChanged infinite loop
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	// inflate the fragment
        View rootView = inflater.inflate(R.layout.fragment_area, container, false);
        
        // initialize area
        area = new AreaModel();
        
        // initialize boolean variable
        suppressFieldChanges = false;
        
        // create decimal formatter with decimal notation
        final DecimalFormat weightDecimalFormatter = new DecimalFormat("0.0#####");
        weightDecimalFormatter.setRoundingMode(RoundingMode.HALF_UP);
        
        // create decimal formatter with scientific notation
        final DecimalFormat weightScientificFormatter = new DecimalFormat("0.0000E0");
        
        // set references to the text fields
        mSquareMillimeterField = (EditText)rootView.findViewById(R.id.square_millimeter_text_field);
        mSquareCentimeterField = (EditText)rootView.findViewById(R.id.square_centimeter_text_field);
        mSquareInchField = (EditText)rootView.findViewById(R.id.square_inch_text_field);
        mSquareFeetField = (EditText)rootView.findViewById(R.id.square_feet_text_field);
        mSquareYardField = (EditText)rootView.findViewById(R.id.square_yard_text_field);
        mSquareMeterField = (EditText)rootView.findViewById(R.id.square_meter_text_field);
        mAcreField = (EditText)rootView.findViewById(R.id.acre_text_field);
        mSquareKilometerField = (EditText)rootView.findViewById(R.id.square_kilometer_text_field);
        mSquareMileField = (EditText)rootView.findViewById(R.id.square_mile_text_field);
        mResetButton = (Button)rootView.findViewById(R.id.area_reset_button);
        
        // Set addTextChangedListener on mSquareMillimeterField
        mSquareMillimeterField.addTextChangedListener(new TextWatcher() {
        	// update the other text fields with the converted values
        	public void onTextChanged(CharSequence s, int start, int before, int count) {
        		if(!suppressFieldChanges) {
            		if(!isNumeric(mSquareMillimeterField.getText().toString())) {
                 		// mSquareMillimeterField's text can't be parsed to a double,
                 		// set the other text fields to empty strings
            			suppressFieldChanges = true;
            				
            			mSquareCentimeterField.setText("");
            			mSquareInchField.setText("");
            			mSquareFeetField.setText("");
            			mSquareYardField.setText("");
            			mSquareMeterField.setText("");
            			mAcreField.setText("");
            			mSquareKilometerField.setText("");
            			mSquareMileField.setText("");
                     	
                 		suppressFieldChanges = false;
                 	} else if (Double.parseDouble(mSquareMillimeterField.getText().toString()) == 0) {
                 		// conversions don't need to be performed on a mSquareMillimeterField value of 0
                 		// set the other text fields to empty strings
                 		suppressFieldChanges = true;
        				
                 		mSquareCentimeterField.setText("");
            			mSquareInchField.setText("");
            			mSquareFeetField.setText("");
            			mSquareYardField.setText("");
            			mSquareMeterField.setText("");
            			mAcreField.setText("");
            			mSquareKilometerField.setText("");
            			mSquareMileField.setText("");
                     	
                 		suppressFieldChanges = false;
                 	} else {
                 		area.calculateFromSquareMillimeter(Double.parseDouble(mSquareMillimeterField.getText().toString()));

                 		// update text fields with converted values
                 		suppressFieldChanges = true;
                 		
                 		// these if statements determine if the values should be displayed
                 		// with scientific notation or decimal notation
                 		if(Math.abs(area.getSquareCentimeter()) < 0.000001 || Math.abs(area.getSquareCentimeter()) > 999999999.0) {
                 			mSquareCentimeterField.setText(weightScientificFormatter.format(area.getSquareCentimeter()));
                 		} else {
                 			mSquareCentimeterField.setText(weightDecimalFormatter.format(area.getSquareCentimeter()));
                 		}
             			
                 		if(Math.abs(area.getSquareInch()) < 0.000001 || Math.abs(area.getSquareInch()) > 999999999.0) {
                 			mSquareInchField.setText(weightScientificFormatter.format(area.getSquareInch()));
                 		} else {
                 			mSquareInchField.setText(weightDecimalFormatter.format(area.getSquareInch()));
                 		}
                 		
                 		if(Math.abs(area.getSquareFeet()) < 0.000001 || Math.abs(area.getSquareFeet()) > 999999999.0) {
                 			mSquareFeetField.setText(weightScientificFormatter.format(area.getSquareFeet()));
                 		} else {
                 			mSquareFeetField.setText(weightDecimalFormatter.format(area.getSquareFeet()));
                 		}
                 		
                 		if(Math.abs(area.getSquareYard()) < 0.000001 || Math.abs(area.getSquareYard()) > 999999999.0) {
                 			mSquareYardField.setText(weightScientificFormatter.format(area.getSquareYard()));
                 		} else {
                 			mSquareYardField.setText(weightDecimalFormatter.format(area.getSquareYard()));
                 		}
                 		
                 		if(Math.abs(area.getSquareMeter()) < 0.000001 || Math.abs(area.getSquareMeter()) > 999999999.0) {
                 			mSquareMeterField.setText(weightScientificFormatter.format(area.getSquareMeter()));
                 		} else {
                 			mSquareMeterField.setText(weightDecimalFormatter.format(area.getSquareMeter()));
                 		}
                 		
                 		if(Math.abs(area.getAcre()) < 0.000001 || Math.abs(area.getAcre()) > 999999999.0) {
                 			mAcreField.setText(weightScientificFormatter.format(area.getAcre()));
                 		} else {
                 			mAcreField.setText(weightDecimalFormatter.format(area.getAcre()));
                 		}
                 		
                 		if(Math.abs(area.getSquareKilometer()) < 0.000001 || Math.abs(area.getSquareKilometer()) > 999999999.0) {
                 			mSquareKilometerField.setText(weightScientificFormatter.format(area.getSquareKilometer()));
                 		} else {
                 			mSquareKilometerField.setText(weightDecimalFormatter.format(area.getSquareKilometer()));
                 		}
                 		
                 		if(Math.abs(area.getSquareMile()) < 0.000001 || Math.abs(area.getSquareMile()) > 999999999.0) {
                 			mSquareMileField.setText(weightScientificFormatter.format(area.getSquareMile()));
                 		} else {
                 			mSquareMileField.setText(weightDecimalFormatter.format(area.getSquareMile()));
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
        }); // end addTextChangedListener on mSquareMillimeterField
        
        // Set addTextChangedListener on mSquareCentimeterField
        mSquareCentimeterField.addTextChangedListener(new TextWatcher() {
        	// update the other text fields with the converted values
        	public void onTextChanged(CharSequence s, int start, int before, int count) {
        		if(!suppressFieldChanges) {
            		if(!isNumeric(mSquareCentimeterField.getText().toString())) {
                 		// mSquareCentimeterField's text can't be parsed to a double,
                 		// set the other text fields to empty strings
            			suppressFieldChanges = true;
            				
            			mSquareMillimeterField.setText("");
            			mSquareInchField.setText("");
            			mSquareFeetField.setText("");
            			mSquareYardField.setText("");
            			mSquareMeterField.setText("");
            			mAcreField.setText("");
            			mSquareKilometerField.setText("");
            			mSquareMileField.setText("");
                     	
                 		suppressFieldChanges = false;
                 	} else if (Double.parseDouble(mSquareCentimeterField.getText().toString()) == 0) {
                 		// conversions don't need to be performed on a mSquareCentimeterField value of 0
                 		// set the other text fields to empty strings
                 		suppressFieldChanges = true;
        				
                 		mSquareMillimeterField.setText("");
            			mSquareInchField.setText("");
            			mSquareFeetField.setText("");
            			mSquareYardField.setText("");
            			mSquareMeterField.setText("");
            			mAcreField.setText("");
            			mSquareKilometerField.setText("");
            			mSquareMileField.setText("");
                     	
                 		suppressFieldChanges = false;
                 	} else {
                 		area.calculateFromSquareCentimeter(Double.parseDouble(mSquareCentimeterField.getText().toString()));

                 		// update text fields with converted values
                 		suppressFieldChanges = true;
                 		
                 		// these if statements determine if the values should be displayed
                 		// with scientific notation or decimal notation
                 		if(Math.abs(area.getSquareMillimeter()) < 0.000001 || Math.abs(area.getSquareMillimeter()) > 999999999.0) {
                 			mSquareMillimeterField.setText(weightScientificFormatter.format(area.getSquareMillimeter()));
                 		} else {
                 			mSquareMillimeterField.setText(weightDecimalFormatter.format(area.getSquareMillimeter()));
                 		}
             			
                 		if(Math.abs(area.getSquareInch()) < 0.000001 || Math.abs(area.getSquareInch()) > 999999999.0) {
                 			mSquareInchField.setText(weightScientificFormatter.format(area.getSquareInch()));
                 		} else {
                 			mSquareInchField.setText(weightDecimalFormatter.format(area.getSquareInch()));
                 		}
                 		
                 		if(Math.abs(area.getSquareFeet()) < 0.000001 || Math.abs(area.getSquareFeet()) > 999999999.0) {
                 			mSquareFeetField.setText(weightScientificFormatter.format(area.getSquareFeet()));
                 		} else {
                 			mSquareFeetField.setText(weightDecimalFormatter.format(area.getSquareFeet()));
                 		}
                 		
                 		if(Math.abs(area.getSquareYard()) < 0.000001 || Math.abs(area.getSquareYard()) > 999999999.0) {
                 			mSquareYardField.setText(weightScientificFormatter.format(area.getSquareYard()));
                 		} else {
                 			mSquareYardField.setText(weightDecimalFormatter.format(area.getSquareYard()));
                 		}
                 		
                 		if(Math.abs(area.getSquareMeter()) < 0.000001 || Math.abs(area.getSquareMeter()) > 999999999.0) {
                 			mSquareMeterField.setText(weightScientificFormatter.format(area.getSquareMeter()));
                 		} else {
                 			mSquareMeterField.setText(weightDecimalFormatter.format(area.getSquareMeter()));
                 		}
                 		
                 		if(Math.abs(area.getAcre()) < 0.000001 || Math.abs(area.getAcre()) > 999999999.0) {
                 			mAcreField.setText(weightScientificFormatter.format(area.getAcre()));
                 		} else {
                 			mAcreField.setText(weightDecimalFormatter.format(area.getAcre()));
                 		}
                 		
                 		if(Math.abs(area.getSquareKilometer()) < 0.000001 || Math.abs(area.getSquareKilometer()) > 999999999.0) {
                 			mSquareKilometerField.setText(weightScientificFormatter.format(area.getSquareKilometer()));
                 		} else {
                 			mSquareKilometerField.setText(weightDecimalFormatter.format(area.getSquareKilometer()));
                 		}
                 		
                 		if(Math.abs(area.getSquareMile()) < 0.000001 || Math.abs(area.getSquareMile()) > 999999999.0) {
                 			mSquareMileField.setText(weightScientificFormatter.format(area.getSquareMile()));
                 		} else {
                 			mSquareMileField.setText(weightDecimalFormatter.format(area.getSquareMile()));
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
        }); // end addTextChangedListener on mSquareCentimeterField
        
        // Set addTextChangedListener on mSquareInchField
        mSquareInchField.addTextChangedListener(new TextWatcher() {
        	// update the other text fields with the converted values
        	public void onTextChanged(CharSequence s, int start, int before, int count) {
        		if(!suppressFieldChanges) {
            		if(!isNumeric(mSquareInchField.getText().toString())) {
                 		// mSquareInchField's text can't be parsed to a double,
                 		// set the other text fields to empty strings
            			suppressFieldChanges = true;
            				
            			mSquareMillimeterField.setText("");
            			mSquareCentimeterField.setText("");
            			mSquareFeetField.setText("");
            			mSquareYardField.setText("");
            			mSquareMeterField.setText("");
            			mAcreField.setText("");
            			mSquareKilometerField.setText("");
            			mSquareMileField.setText("");
                     	
                 		suppressFieldChanges = false;
                 	} else if (Double.parseDouble(mSquareInchField.getText().toString()) == 0) {
                 		// conversions don't need to be performed on a mSquareInchField value of 0
                 		// set the other text fields to empty strings
                 		suppressFieldChanges = true;
        				
                 		mSquareMillimeterField.setText("");
                 		mSquareCentimeterField.setText("");
            			mSquareFeetField.setText("");
            			mSquareYardField.setText("");
            			mSquareMeterField.setText("");
            			mAcreField.setText("");
            			mSquareKilometerField.setText("");
            			mSquareMileField.setText("");
                     	
                 		suppressFieldChanges = false;
                 	} else {
                 		area.calculateFromSquareInch(Double.parseDouble(mSquareInchField.getText().toString()));

                 		// update text fields with converted values
                 		suppressFieldChanges = true;
                 		
                 		// these if statements determine if the values should be displayed
                 		// with scientific notation or decimal notation
                 		if(Math.abs(area.getSquareMillimeter()) < 0.000001 || Math.abs(area.getSquareMillimeter()) > 999999999.0) {
                 			mSquareMillimeterField.setText(weightScientificFormatter.format(area.getSquareMillimeter()));
                 		} else {
                 			mSquareMillimeterField.setText(weightDecimalFormatter.format(area.getSquareMillimeter()));
                 		}
             			
                 		if(Math.abs(area.getSquareCentimeter()) < 0.000001 || Math.abs(area.getSquareCentimeter()) > 999999999.0) {
                 			mSquareCentimeterField.setText(weightScientificFormatter.format(area.getSquareCentimeter()));
                 		} else {
                 			mSquareCentimeterField.setText(weightDecimalFormatter.format(area.getSquareCentimeter()));
                 		}
                 		
                 		if(Math.abs(area.getSquareFeet()) < 0.000001 || Math.abs(area.getSquareFeet()) > 999999999.0) {
                 			mSquareFeetField.setText(weightScientificFormatter.format(area.getSquareFeet()));
                 		} else {
                 			mSquareFeetField.setText(weightDecimalFormatter.format(area.getSquareFeet()));
                 		}
                 		
                 		if(Math.abs(area.getSquareYard()) < 0.000001 || Math.abs(area.getSquareYard()) > 999999999.0) {
                 			mSquareYardField.setText(weightScientificFormatter.format(area.getSquareYard()));
                 		} else {
                 			mSquareYardField.setText(weightDecimalFormatter.format(area.getSquareYard()));
                 		}
                 		
                 		if(Math.abs(area.getSquareMeter()) < 0.000001 || Math.abs(area.getSquareMeter()) > 999999999.0) {
                 			mSquareMeterField.setText(weightScientificFormatter.format(area.getSquareMeter()));
                 		} else {
                 			mSquareMeterField.setText(weightDecimalFormatter.format(area.getSquareMeter()));
                 		}
                 		
                 		if(Math.abs(area.getAcre()) < 0.000001 || Math.abs(area.getAcre()) > 999999999.0) {
                 			mAcreField.setText(weightScientificFormatter.format(area.getAcre()));
                 		} else {
                 			mAcreField.setText(weightDecimalFormatter.format(area.getAcre()));
                 		}
                 		
                 		if(Math.abs(area.getSquareKilometer()) < 0.000001 || Math.abs(area.getSquareKilometer()) > 999999999.0) {
                 			mSquareKilometerField.setText(weightScientificFormatter.format(area.getSquareKilometer()));
                 		} else {
                 			mSquareKilometerField.setText(weightDecimalFormatter.format(area.getSquareKilometer()));
                 		}
                 		
                 		if(Math.abs(area.getSquareMile()) < 0.000001 || Math.abs(area.getSquareMile()) > 999999999.0) {
                 			mSquareMileField.setText(weightScientificFormatter.format(area.getSquareMile()));
                 		} else {
                 			mSquareMileField.setText(weightDecimalFormatter.format(area.getSquareMile()));
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
        }); // end addTextChangedListener on mSquareInchField
        
        // Set addTextChangedListener on mSquareFeetField
        mSquareFeetField.addTextChangedListener(new TextWatcher() {
        	// update the other text fields with the converted values
        	public void onTextChanged(CharSequence s, int start, int before, int count) {
        		if(!suppressFieldChanges) {
            		if(!isNumeric(mSquareFeetField.getText().toString())) {
                 		// mSquareFeetField's text can't be parsed to a double,
                 		// set the other text fields to empty strings
            			suppressFieldChanges = true;
            				
            			mSquareMillimeterField.setText("");
            			mSquareCentimeterField.setText("");
            			mSquareInchField.setText("");
            			mSquareYardField.setText("");
            			mSquareMeterField.setText("");
            			mAcreField.setText("");
            			mSquareKilometerField.setText("");
            			mSquareMileField.setText("");
                     	
                 		suppressFieldChanges = false;
                 	} else if (Double.parseDouble(mSquareFeetField.getText().toString()) == 0) {
                 		// conversions don't need to be performed on a mSquareFeetField value of 0
                 		// set the other text fields to empty strings
                 		suppressFieldChanges = true;
        				
                 		mSquareMillimeterField.setText("");
                 		mSquareCentimeterField.setText("");
                 		mSquareInchField.setText("");
            			mSquareYardField.setText("");
            			mSquareMeterField.setText("");
            			mAcreField.setText("");
            			mSquareKilometerField.setText("");
            			mSquareMileField.setText("");
                     	
                 		suppressFieldChanges = false;
                 	} else {
                 		area.calculateFromSquareFeet(Double.parseDouble(mSquareFeetField.getText().toString()));

                 		// update text fields with converted values
                 		suppressFieldChanges = true;
                 		
                 		// these if statements determine if the values should be displayed
                 		// with scientific notation or decimal notation
                 		if(Math.abs(area.getSquareMillimeter()) < 0.000001 || Math.abs(area.getSquareMillimeter()) > 999999999.0) {
                 			mSquareMillimeterField.setText(weightScientificFormatter.format(area.getSquareMillimeter()));
                 		} else {
                 			mSquareMillimeterField.setText(weightDecimalFormatter.format(area.getSquareMillimeter()));
                 		}
             			
                 		if(Math.abs(area.getSquareCentimeter()) < 0.000001 || Math.abs(area.getSquareCentimeter()) > 999999999.0) {
                 			mSquareCentimeterField.setText(weightScientificFormatter.format(area.getSquareCentimeter()));
                 		} else {
                 			mSquareCentimeterField.setText(weightDecimalFormatter.format(area.getSquareCentimeter()));
                 		}
                 		
                 		if(Math.abs(area.getSquareInch()) < 0.000001 || Math.abs(area.getSquareInch()) > 999999999.0) {
                 			mSquareInchField.setText(weightScientificFormatter.format(area.getSquareInch()));
                 		} else {
                 			mSquareInchField.setText(weightDecimalFormatter.format(area.getSquareInch()));
                 		}
                 		
                 		if(Math.abs(area.getSquareYard()) < 0.000001 || Math.abs(area.getSquareYard()) > 999999999.0) {
                 			mSquareYardField.setText(weightScientificFormatter.format(area.getSquareYard()));
                 		} else {
                 			mSquareYardField.setText(weightDecimalFormatter.format(area.getSquareYard()));
                 		}
                 		
                 		if(Math.abs(area.getSquareMeter()) < 0.000001 || Math.abs(area.getSquareMeter()) > 999999999.0) {
                 			mSquareMeterField.setText(weightScientificFormatter.format(area.getSquareMeter()));
                 		} else {
                 			mSquareMeterField.setText(weightDecimalFormatter.format(area.getSquareMeter()));
                 		}
                 		
                 		if(Math.abs(area.getAcre()) < 0.000001 || Math.abs(area.getAcre()) > 999999999.0) {
                 			mAcreField.setText(weightScientificFormatter.format(area.getAcre()));
                 		} else {
                 			mAcreField.setText(weightDecimalFormatter.format(area.getAcre()));
                 		}
                 		
                 		if(Math.abs(area.getSquareKilometer()) < 0.000001 || Math.abs(area.getSquareKilometer()) > 999999999.0) {
                 			mSquareKilometerField.setText(weightScientificFormatter.format(area.getSquareKilometer()));
                 		} else {
                 			mSquareKilometerField.setText(weightDecimalFormatter.format(area.getSquareKilometer()));
                 		}
                 		
                 		if(Math.abs(area.getSquareMile()) < 0.000001 || Math.abs(area.getSquareMile()) > 999999999.0) {
                 			mSquareMileField.setText(weightScientificFormatter.format(area.getSquareMile()));
                 		} else {
                 			mSquareMileField.setText(weightDecimalFormatter.format(area.getSquareMile()));
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
        }); // end addTextChangedListener on mSquareFeetField
        
        // Set addTextChangedListener on mSquareYardField
        mSquareYardField.addTextChangedListener(new TextWatcher() {
        	// update the other text fields with the converted values
        	public void onTextChanged(CharSequence s, int start, int before, int count) {
        		if(!suppressFieldChanges) {
            		if(!isNumeric(mSquareYardField.getText().toString())) {
                 		// mSquareYardField's text can't be parsed to a double,
                 		// set the other text fields to empty strings
            			suppressFieldChanges = true;
            				
            			mSquareMillimeterField.setText("");
            			mSquareCentimeterField.setText("");
            			mSquareInchField.setText("");
            			mSquareFeetField.setText("");
            			mSquareMeterField.setText("");
            			mAcreField.setText("");
            			mSquareKilometerField.setText("");
            			mSquareMileField.setText("");
                     	
                 		suppressFieldChanges = false;
                 	} else if (Double.parseDouble(mSquareYardField.getText().toString()) == 0) {
                 		// conversions don't need to be performed on a mSquareYardField value of 0
                 		// set the other text fields to empty strings
                 		suppressFieldChanges = true;
        				
                 		mSquareMillimeterField.setText("");
                 		mSquareCentimeterField.setText("");
                 		mSquareInchField.setText("");
                 		mSquareFeetField.setText("");
            			mSquareMeterField.setText("");
            			mAcreField.setText("");
            			mSquareKilometerField.setText("");
            			mSquareMileField.setText("");
                     	
                 		suppressFieldChanges = false;
                 	} else {
                 		area.calculateFromSquareYard(Double.parseDouble(mSquareYardField.getText().toString()));

                 		// update text fields with converted values
                 		suppressFieldChanges = true;
                 		
                 		// these if statements determine if the values should be displayed
                 		// with scientific notation or decimal notation
                 		if(Math.abs(area.getSquareMillimeter()) < 0.000001 || Math.abs(area.getSquareMillimeter()) > 999999999.0) {
                 			mSquareMillimeterField.setText(weightScientificFormatter.format(area.getSquareMillimeter()));
                 		} else {
                 			mSquareMillimeterField.setText(weightDecimalFormatter.format(area.getSquareMillimeter()));
                 		}
             			
                 		if(Math.abs(area.getSquareCentimeter()) < 0.000001 || Math.abs(area.getSquareCentimeter()) > 999999999.0) {
                 			mSquareCentimeterField.setText(weightScientificFormatter.format(area.getSquareCentimeter()));
                 		} else {
                 			mSquareCentimeterField.setText(weightDecimalFormatter.format(area.getSquareCentimeter()));
                 		}
                 		
                 		if(Math.abs(area.getSquareInch()) < 0.000001 || Math.abs(area.getSquareInch()) > 999999999.0) {
                 			mSquareInchField.setText(weightScientificFormatter.format(area.getSquareInch()));
                 		} else {
                 			mSquareInchField.setText(weightDecimalFormatter.format(area.getSquareInch()));
                 		}
                 		
                 		if(Math.abs(area.getSquareFeet()) < 0.000001 || Math.abs(area.getSquareFeet()) > 999999999.0) {
                 			mSquareFeetField.setText(weightScientificFormatter.format(area.getSquareFeet()));
                 		} else {
                 			mSquareFeetField.setText(weightDecimalFormatter.format(area.getSquareFeet()));
                 		}
                 		
                 		if(Math.abs(area.getSquareMeter()) < 0.000001 || Math.abs(area.getSquareMeter()) > 999999999.0) {
                 			mSquareMeterField.setText(weightScientificFormatter.format(area.getSquareMeter()));
                 		} else {
                 			mSquareMeterField.setText(weightDecimalFormatter.format(area.getSquareMeter()));
                 		}
                 		
                 		if(Math.abs(area.getAcre()) < 0.000001 || Math.abs(area.getAcre()) > 999999999.0) {
                 			mAcreField.setText(weightScientificFormatter.format(area.getAcre()));
                 		} else {
                 			mAcreField.setText(weightDecimalFormatter.format(area.getAcre()));
                 		}
                 		
                 		if(Math.abs(area.getSquareKilometer()) < 0.000001 || Math.abs(area.getSquareKilometer()) > 999999999.0) {
                 			mSquareKilometerField.setText(weightScientificFormatter.format(area.getSquareKilometer()));
                 		} else {
                 			mSquareKilometerField.setText(weightDecimalFormatter.format(area.getSquareKilometer()));
                 		}
                 		
                 		if(Math.abs(area.getSquareMile()) < 0.000001 || Math.abs(area.getSquareMile()) > 999999999.0) {
                 			mSquareMileField.setText(weightScientificFormatter.format(area.getSquareMile()));
                 		} else {
                 			mSquareMileField.setText(weightDecimalFormatter.format(area.getSquareMile()));
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
        }); // end addTextChangedListener on mSquareYardField
        
        // Set addTextChangedListener on mSquareMeterField
        mSquareMeterField.addTextChangedListener(new TextWatcher() {
        	// update the other text fields with the converted values
        	public void onTextChanged(CharSequence s, int start, int before, int count) {
        		if(!suppressFieldChanges) {
            		if(!isNumeric(mSquareMeterField.getText().toString())) {
                 		// mSquareMeterField's text can't be parsed to a double,
                 		// set the other text fields to empty strings
            			suppressFieldChanges = true;
            				
            			mSquareMillimeterField.setText("");
            			mSquareCentimeterField.setText("");
            			mSquareInchField.setText("");
            			mSquareFeetField.setText("");
            			mSquareYardField.setText("");
            			mAcreField.setText("");
            			mSquareKilometerField.setText("");
            			mSquareMileField.setText("");
                     	
                 		suppressFieldChanges = false;
                 	} else if (Double.parseDouble(mSquareMeterField.getText().toString()) == 0) {
                 		// conversions don't need to be performed on a mSquareMeterField value of 0
                 		// set the other text fields to empty strings
                 		suppressFieldChanges = true;
        				
                 		mSquareMillimeterField.setText("");
                 		mSquareCentimeterField.setText("");
                 		mSquareInchField.setText("");
                 		mSquareFeetField.setText("");
                 		mSquareYardField.setText("");
            			mAcreField.setText("");
            			mSquareKilometerField.setText("");
            			mSquareMileField.setText("");
                     	
                 		suppressFieldChanges = false;
                 	} else {
                 		area.calculateFromSquareMeter(Double.parseDouble(mSquareMeterField.getText().toString()));

                 		// update text fields with converted values
                 		suppressFieldChanges = true;
                 		
                 		// these if statements determine if the values should be displayed
                 		// with scientific notation or decimal notation
                 		if(Math.abs(area.getSquareMillimeter()) < 0.000001 || Math.abs(area.getSquareMillimeter()) > 999999999.0) {
                 			mSquareMillimeterField.setText(weightScientificFormatter.format(area.getSquareMillimeter()));
                 		} else {
                 			mSquareMillimeterField.setText(weightDecimalFormatter.format(area.getSquareMillimeter()));
                 		}
             			
                 		if(Math.abs(area.getSquareCentimeter()) < 0.000001 || Math.abs(area.getSquareCentimeter()) > 999999999.0) {
                 			mSquareCentimeterField.setText(weightScientificFormatter.format(area.getSquareCentimeter()));
                 		} else {
                 			mSquareCentimeterField.setText(weightDecimalFormatter.format(area.getSquareCentimeter()));
                 		}
                 		
                 		if(Math.abs(area.getSquareInch()) < 0.000001 || Math.abs(area.getSquareInch()) > 999999999.0) {
                 			mSquareInchField.setText(weightScientificFormatter.format(area.getSquareInch()));
                 		} else {
                 			mSquareInchField.setText(weightDecimalFormatter.format(area.getSquareInch()));
                 		}
                 		
                 		if(Math.abs(area.getSquareFeet()) < 0.000001 || Math.abs(area.getSquareFeet()) > 999999999.0) {
                 			mSquareFeetField.setText(weightScientificFormatter.format(area.getSquareFeet()));
                 		} else {
                 			mSquareFeetField.setText(weightDecimalFormatter.format(area.getSquareFeet()));
                 		}
                 		
                 		if(Math.abs(area.getSquareYard()) < 0.000001 || Math.abs(area.getSquareYard()) > 999999999.0) {
                 			mSquareYardField.setText(weightScientificFormatter.format(area.getSquareYard()));
                 		} else {
                 			mSquareYardField.setText(weightDecimalFormatter.format(area.getSquareYard()));
                 		}
                 		
                 		if(Math.abs(area.getAcre()) < 0.000001 || Math.abs(area.getAcre()) > 999999999.0) {
                 			mAcreField.setText(weightScientificFormatter.format(area.getAcre()));
                 		} else {
                 			mAcreField.setText(weightDecimalFormatter.format(area.getAcre()));
                 		}
                 		
                 		if(Math.abs(area.getSquareKilometer()) < 0.000001 || Math.abs(area.getSquareKilometer()) > 999999999.0) {
                 			mSquareKilometerField.setText(weightScientificFormatter.format(area.getSquareKilometer()));
                 		} else {
                 			mSquareKilometerField.setText(weightDecimalFormatter.format(area.getSquareKilometer()));
                 		}
                 		
                 		if(Math.abs(area.getSquareMile()) < 0.000001 || Math.abs(area.getSquareMile()) > 999999999.0) {
                 			mSquareMileField.setText(weightScientificFormatter.format(area.getSquareMile()));
                 		} else {
                 			mSquareMileField.setText(weightDecimalFormatter.format(area.getSquareMile()));
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
        }); // end addTextChangedListener on mSquareMeterField
        
        // Set addTextChangedListener on mAcreField
        mAcreField.addTextChangedListener(new TextWatcher() {
        	// update the other text fields with the converted values
        	public void onTextChanged(CharSequence s, int start, int before, int count) {
        		if(!suppressFieldChanges) {
            		if(!isNumeric(mAcreField.getText().toString())) {
                 		// mAcreField's text can't be parsed to a double,
                 		// set the other text fields to empty strings
            			suppressFieldChanges = true;
            				
            			mSquareMillimeterField.setText("");
            			mSquareCentimeterField.setText("");
            			mSquareInchField.setText("");
            			mSquareFeetField.setText("");
            			mSquareYardField.setText("");
            			mSquareMeterField.setText("");
            			mSquareKilometerField.setText("");
            			mSquareMileField.setText("");
                     	
                 		suppressFieldChanges = false;
                 	} else if (Double.parseDouble(mAcreField.getText().toString()) == 0) {
                 		// conversions don't need to be performed on a mAcreField value of 0
                 		// set the other text fields to empty strings
                 		suppressFieldChanges = true;
        				
                 		mSquareMillimeterField.setText("");
                 		mSquareCentimeterField.setText("");
                 		mSquareInchField.setText("");
                 		mSquareFeetField.setText("");
                 		mSquareYardField.setText("");
                 		mSquareMeterField.setText("");
            			mSquareKilometerField.setText("");
            			mSquareMileField.setText("");
                     	
                 		suppressFieldChanges = false;
                 	} else {
                 		area.calculateFromAcre(Double.parseDouble(mAcreField.getText().toString()));

                 		// update text fields with converted values
                 		suppressFieldChanges = true;
                 		
                 		// these if statements determine if the values should be displayed
                 		// with scientific notation or decimal notation
                 		if(Math.abs(area.getSquareMillimeter()) < 0.000001 || Math.abs(area.getSquareMillimeter()) > 999999999.0) {
                 			mSquareMillimeterField.setText(weightScientificFormatter.format(area.getSquareMillimeter()));
                 		} else {
                 			mSquareMillimeterField.setText(weightDecimalFormatter.format(area.getSquareMillimeter()));
                 		}
             			
                 		if(Math.abs(area.getSquareCentimeter()) < 0.000001 || Math.abs(area.getSquareCentimeter()) > 999999999.0) {
                 			mSquareCentimeterField.setText(weightScientificFormatter.format(area.getSquareCentimeter()));
                 		} else {
                 			mSquareCentimeterField.setText(weightDecimalFormatter.format(area.getSquareCentimeter()));
                 		}
                 		
                 		if(Math.abs(area.getSquareInch()) < 0.000001 || Math.abs(area.getSquareInch()) > 999999999.0) {
                 			mSquareInchField.setText(weightScientificFormatter.format(area.getSquareInch()));
                 		} else {
                 			mSquareInchField.setText(weightDecimalFormatter.format(area.getSquareInch()));
                 		}
                 		
                 		if(Math.abs(area.getSquareFeet()) < 0.000001 || Math.abs(area.getSquareFeet()) > 999999999.0) {
                 			mSquareFeetField.setText(weightScientificFormatter.format(area.getSquareFeet()));
                 		} else {
                 			mSquareFeetField.setText(weightDecimalFormatter.format(area.getSquareFeet()));
                 		}
                 		
                 		if(Math.abs(area.getSquareYard()) < 0.000001 || Math.abs(area.getSquareYard()) > 999999999.0) {
                 			mSquareYardField.setText(weightScientificFormatter.format(area.getSquareYard()));
                 		} else {
                 			mSquareYardField.setText(weightDecimalFormatter.format(area.getSquareYard()));
                 		}
                 		
                 		if(Math.abs(area.getSquareMeter()) < 0.000001 || Math.abs(area.getSquareMeter()) > 999999999.0) {
                 			mSquareMeterField.setText(weightScientificFormatter.format(area.getSquareMeter()));
                 		} else {
                 			mSquareMeterField.setText(weightDecimalFormatter.format(area.getSquareMeter()));
                 		}
                 		
                 		if(Math.abs(area.getSquareKilometer()) < 0.000001 || Math.abs(area.getSquareKilometer()) > 999999999.0) {
                 			mSquareKilometerField.setText(weightScientificFormatter.format(area.getSquareKilometer()));
                 		} else {
                 			mSquareKilometerField.setText(weightDecimalFormatter.format(area.getSquareKilometer()));
                 		}
                 		
                 		if(Math.abs(area.getSquareMile()) < 0.000001 || Math.abs(area.getSquareMile()) > 999999999.0) {
                 			mSquareMileField.setText(weightScientificFormatter.format(area.getSquareMile()));
                 		} else {
                 			mSquareMileField.setText(weightDecimalFormatter.format(area.getSquareMile()));
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
        }); // end addTextChangedListener on mAcreField
        
        // Set addTextChangedListener on mSquareKilometerField
        mSquareKilometerField.addTextChangedListener(new TextWatcher() {
        	// update the other text fields with the converted values
        	public void onTextChanged(CharSequence s, int start, int before, int count) {
        		if(!suppressFieldChanges) {
            		if(!isNumeric(mSquareKilometerField.getText().toString())) {
                 		// mSquareKilometerField's text can't be parsed to a double,
                 		// set the other text fields to empty strings
            			suppressFieldChanges = true;
            				
            			mSquareMillimeterField.setText("");
            			mSquareCentimeterField.setText("");
            			mSquareInchField.setText("");
            			mSquareFeetField.setText("");
            			mSquareYardField.setText("");
            			mSquareMeterField.setText("");
            			mAcreField.setText("");
            			mSquareMileField.setText("");
                     	
                 		suppressFieldChanges = false;
                 	} else if (Double.parseDouble(mSquareKilometerField.getText().toString()) == 0) {
                 		// conversions don't need to be performed on a mSquareKilometerField value of 0
                 		// set the other text fields to empty strings
                 		suppressFieldChanges = true;
        				
                 		mSquareMillimeterField.setText("");
                 		mSquareCentimeterField.setText("");
                 		mSquareInchField.setText("");
                 		mSquareFeetField.setText("");
                 		mSquareYardField.setText("");
                 		mSquareMeterField.setText("");
                 		mAcreField.setText("");
            			mSquareMileField.setText("");
                     	
                 		suppressFieldChanges = false;
                 	} else {
                 		area.calculateFromSquareKilometer(Double.parseDouble(mSquareKilometerField.getText().toString()));

                 		// update text fields with converted values
                 		suppressFieldChanges = true;
                 		
                 		// these if statements determine if the values should be displayed
                 		// with scientific notation or decimal notation
                 		if(Math.abs(area.getSquareMillimeter()) < 0.000001 || Math.abs(area.getSquareMillimeter()) > 999999999.0) {
                 			mSquareMillimeterField.setText(weightScientificFormatter.format(area.getSquareMillimeter()));
                 		} else {
                 			mSquareMillimeterField.setText(weightDecimalFormatter.format(area.getSquareMillimeter()));
                 		}
             			
                 		if(Math.abs(area.getSquareCentimeter()) < 0.000001 || Math.abs(area.getSquareCentimeter()) > 999999999.0) {
                 			mSquareCentimeterField.setText(weightScientificFormatter.format(area.getSquareCentimeter()));
                 		} else {
                 			mSquareCentimeterField.setText(weightDecimalFormatter.format(area.getSquareCentimeter()));
                 		}
                 		
                 		if(Math.abs(area.getSquareInch()) < 0.000001 || Math.abs(area.getSquareInch()) > 999999999.0) {
                 			mSquareInchField.setText(weightScientificFormatter.format(area.getSquareInch()));
                 		} else {
                 			mSquareInchField.setText(weightDecimalFormatter.format(area.getSquareInch()));
                 		}
                 		
                 		if(Math.abs(area.getSquareFeet()) < 0.000001 || Math.abs(area.getSquareFeet()) > 999999999.0) {
                 			mSquareFeetField.setText(weightScientificFormatter.format(area.getSquareFeet()));
                 		} else {
                 			mSquareFeetField.setText(weightDecimalFormatter.format(area.getSquareFeet()));
                 		}
                 		
                 		if(Math.abs(area.getSquareYard()) < 0.000001 || Math.abs(area.getSquareYard()) > 999999999.0) {
                 			mSquareYardField.setText(weightScientificFormatter.format(area.getSquareYard()));
                 		} else {
                 			mSquareYardField.setText(weightDecimalFormatter.format(area.getSquareYard()));
                 		}
                 		
                 		if(Math.abs(area.getSquareMeter()) < 0.000001 || Math.abs(area.getSquareMeter()) > 999999999.0) {
                 			mSquareMeterField.setText(weightScientificFormatter.format(area.getSquareMeter()));
                 		} else {
                 			mSquareMeterField.setText(weightDecimalFormatter.format(area.getSquareMeter()));
                 		}
                 		
                 		if(Math.abs(area.getAcre()) < 0.000001 || Math.abs(area.getAcre()) > 999999999.0) {
                 			mAcreField.setText(weightScientificFormatter.format(area.getAcre()));
                 		} else {
                 			mAcreField.setText(weightDecimalFormatter.format(area.getAcre()));
                 		}
                 		
                 		if(Math.abs(area.getSquareMile()) < 0.000001 || Math.abs(area.getSquareMile()) > 999999999.0) {
                 			mSquareMileField.setText(weightScientificFormatter.format(area.getSquareMile()));
                 		} else {
                 			mSquareMileField.setText(weightDecimalFormatter.format(area.getSquareMile()));
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
        }); // end addTextChangedListener on mSquareKilometerField
        
        // Set addTextChangedListener on mSquareMileField
        mSquareMileField.addTextChangedListener(new TextWatcher() {
        	// update the other text fields with the converted values
        	public void onTextChanged(CharSequence s, int start, int before, int count) {
        		if(!suppressFieldChanges) {
            		if(!isNumeric(mSquareMileField.getText().toString())) {
                 		// mSquareMileField's text can't be parsed to a double,
                 		// set the other text fields to empty strings
            			suppressFieldChanges = true;
            				
            			mSquareMillimeterField.setText("");
            			mSquareCentimeterField.setText("");
            			mSquareInchField.setText("");
            			mSquareFeetField.setText("");
            			mSquareYardField.setText("");
            			mSquareMeterField.setText("");
            			mAcreField.setText("");
            			mSquareKilometerField.setText("");
                     	
                 		suppressFieldChanges = false;
                 	} else if (Double.parseDouble(mSquareMileField.getText().toString()) == 0) {
                 		// conversions don't need to be performed on a mSquareMileField value of 0
                 		// set the other text fields to empty strings
                 		suppressFieldChanges = true;
        				
                 		mSquareMillimeterField.setText("");
                 		mSquareCentimeterField.setText("");
                 		mSquareInchField.setText("");
                 		mSquareFeetField.setText("");
                 		mSquareYardField.setText("");
                 		mSquareMeterField.setText("");
                 		mAcreField.setText("");
                 		mSquareKilometerField.setText("");
                     	
                 		suppressFieldChanges = false;
                 	} else {
                 		area.calculateFromSquareMile(Double.parseDouble(mSquareMileField.getText().toString()));

                 		// update text fields with converted values
                 		suppressFieldChanges = true;
                 		
                 		// these if statements determine if the values should be displayed
                 		// with scientific notation or decimal notation
                 		if(Math.abs(area.getSquareMillimeter()) < 0.000001 || Math.abs(area.getSquareMillimeter()) > 999999999.0) {
                 			mSquareMillimeterField.setText(weightScientificFormatter.format(area.getSquareMillimeter()));
                 		} else {
                 			mSquareMillimeterField.setText(weightDecimalFormatter.format(area.getSquareMillimeter()));
                 		}
             			
                 		if(Math.abs(area.getSquareCentimeter()) < 0.000001 || Math.abs(area.getSquareCentimeter()) > 999999999.0) {
                 			mSquareCentimeterField.setText(weightScientificFormatter.format(area.getSquareCentimeter()));
                 		} else {
                 			mSquareCentimeterField.setText(weightDecimalFormatter.format(area.getSquareCentimeter()));
                 		}
                 		
                 		if(Math.abs(area.getSquareInch()) < 0.000001 || Math.abs(area.getSquareInch()) > 999999999.0) {
                 			mSquareInchField.setText(weightScientificFormatter.format(area.getSquareInch()));
                 		} else {
                 			mSquareInchField.setText(weightDecimalFormatter.format(area.getSquareInch()));
                 		}
                 		
                 		if(Math.abs(area.getSquareFeet()) < 0.000001 || Math.abs(area.getSquareFeet()) > 999999999.0) {
                 			mSquareFeetField.setText(weightScientificFormatter.format(area.getSquareFeet()));
                 		} else {
                 			mSquareFeetField.setText(weightDecimalFormatter.format(area.getSquareFeet()));
                 		}
                 		
                 		if(Math.abs(area.getSquareYard()) < 0.000001 || Math.abs(area.getSquareYard()) > 999999999.0) {
                 			mSquareYardField.setText(weightScientificFormatter.format(area.getSquareYard()));
                 		} else {
                 			mSquareYardField.setText(weightDecimalFormatter.format(area.getSquareYard()));
                 		}
                 		
                 		if(Math.abs(area.getSquareMeter()) < 0.000001 || Math.abs(area.getSquareMeter()) > 999999999.0) {
                 			mSquareMeterField.setText(weightScientificFormatter.format(area.getSquareMeter()));
                 		} else {
                 			mSquareMeterField.setText(weightDecimalFormatter.format(area.getSquareMeter()));
                 		}
                 		
                 		if(Math.abs(area.getAcre()) < 0.000001 || Math.abs(area.getAcre()) > 999999999.0) {
                 			mAcreField.setText(weightScientificFormatter.format(area.getAcre()));
                 		} else {
                 			mAcreField.setText(weightDecimalFormatter.format(area.getAcre()));
                 		}
                 		
                 		if(Math.abs(area.getSquareKilometer()) < 0.000001 || Math.abs(area.getSquareKilometer()) > 999999999.0) {
                 			mSquareKilometerField.setText(weightScientificFormatter.format(area.getSquareKilometer()));
                 		} else {
                 			mSquareKilometerField.setText(weightDecimalFormatter.format(area.getSquareKilometer()));
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
        }); // end addTextChangedListener on mSquareMileField
        
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
    	mSquareMillimeterField.setText("");
    	mSquareCentimeterField.setText("");
    	mSquareInchField.setText("");
    	mSquareFeetField.setText("");
    	mSquareYardField.setText("");
    	mSquareMeterField.setText("");
    	mAcreField.setText("");
    	mSquareKilometerField.setText("");
    	mSquareMileField.setText("");
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
} // end class AreaFragment