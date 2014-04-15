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
 
public class WeightFragment extends Fragment {
	private EditText mMilligramField; // will reference milligram_text_field
	private EditText mGramField; // will reference gram_text_field
	private EditText mOunceField; // will reference ounce_text_field
	private EditText mPoundField; // will reference pound_text_field
	private EditText mKilogramField; // will reference kilogram_text_field
	private EditText mStoneField; // will reference stone_text_field
	private EditText mShortTonField; // will reference short_ton_text_field
	private EditText mMetricTonField; // will reference metric_ton_text_field
	private EditText mLongTonField; // will reference long_ton_text_field
	private Button mResetButton; // will reference weight_reset_button
	private WeightModel weight; // model object to hold weight data
	private Boolean suppressFieldChanges; // use this boolean to prevent onTextChanged infinite loop
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	// inflate the fragment
        View rootView = inflater.inflate(R.layout.fragment_weight, container, false);
        
        // initialize weight
        weight = new WeightModel();
        
        // initialize boolean variable
        suppressFieldChanges = false;
        
        // create decimal formatter with decimal notation
        final DecimalFormat weightDecimalFormatter = new DecimalFormat("0.0#####");
        weightDecimalFormatter.setRoundingMode(RoundingMode.HALF_UP);
        
        // create decimal formatter with scientific notation
        final DecimalFormat weightScientificFormatter = new DecimalFormat("0.0000E0");        
        
        // set references to the text fields
        mMilligramField = (EditText)rootView.findViewById(R.id.milligram_text_field);
        mGramField = (EditText)rootView.findViewById(R.id.gram_text_field);
        mOunceField = (EditText)rootView.findViewById(R.id.ounce_text_field);
        mPoundField = (EditText)rootView.findViewById(R.id.pound_text_field);
        mKilogramField = (EditText)rootView.findViewById(R.id.kilogram_text_field);
        mStoneField = (EditText)rootView.findViewById(R.id.stone_text_field);
        mShortTonField = (EditText)rootView.findViewById(R.id.short_ton_text_field);
        mMetricTonField = (EditText)rootView.findViewById(R.id.metric_ton_text_field);
        mLongTonField = (EditText)rootView.findViewById(R.id.long_ton_text_field);
        mResetButton = (Button)rootView.findViewById(R.id.weight_reset_button);
        
        //Set addTextChangedListener on mMilligramField
        mMilligramField.addTextChangedListener(new TextWatcher() {
        	// update the other text fields with the converted values
        	public void onTextChanged(CharSequence s, int start, int before, int count) {
        		if(!suppressFieldChanges) {
            		if(!isNumeric(mMilligramField.getText().toString())) {
                 		// mMilligramField's text can't be parsed to a double,
                 		// set the other text fields to empty strings
            			suppressFieldChanges = true;
            				
            			mGramField.setText("");
            			mOunceField.setText("");
            			mPoundField.setText("");
            			mKilogramField.setText("");
            			mStoneField.setText("");
            			mShortTonField.setText("");
            			mMetricTonField.setText("");
            			mLongTonField.setText("");
                     	
                 		suppressFieldChanges = false;
                 	} else if (Double.parseDouble(mMilligramField.getText().toString()) == 0) {
                 		// conversions don't need to be performed on a milligram value of 0
                 		// set the other text fields to empty strings
                 		suppressFieldChanges = true;
        				
            			mGramField.setText("");
            			mOunceField.setText("");
            			mPoundField.setText("");
            			mKilogramField.setText("");
            			mStoneField.setText("");
            			mShortTonField.setText("");
            			mMetricTonField.setText("");
            			mLongTonField.setText("");
                     	
                 		suppressFieldChanges = false;
                 	} else {
                 		weight.calculateFromMilligram(Double.parseDouble(mMilligramField.getText().toString()));

                 		// update text fields with converted values
                 		suppressFieldChanges = true;
                 		
                 		// these if statements determine if the values should be displayed
                 		// with scientific notation or decimal notation
                 		if(Math.abs(weight.getGram()) < 0.000001 || Math.abs(weight.getGram()) > 999999999.0) {
                 			mGramField.setText(weightScientificFormatter.format(weight.getGram()));
                 		} else {
                 			mGramField.setText(weightDecimalFormatter.format(weight.getGram()));
                 		}
             			
                 		if(Math.abs(weight.getOunce()) < 0.000001 || Math.abs(weight.getOunce()) > 999999999.0) {
                 			mOunceField.setText(weightScientificFormatter.format(weight.getOunce()));
                 		} else {
                 			mOunceField.setText(weightDecimalFormatter.format(weight.getOunce()));
                 		}
                 		
                 		if(Math.abs(weight.getPound()) < 0.000001 || Math.abs(weight.getPound()) > 999999999.0) {
                 			mPoundField.setText(weightScientificFormatter.format(weight.getPound()));
                 		} else {
                 			mPoundField.setText(weightDecimalFormatter.format(weight.getPound()));
                 		}
                 		
                 		if(Math.abs(weight.getKilogram()) < 0.000001 || Math.abs(weight.getKilogram()) > 999999999.0) {
                 			mKilogramField.setText(weightScientificFormatter.format(weight.getKilogram()));
                 		} else {
                 			mKilogramField.setText(weightDecimalFormatter.format(weight.getKilogram()));
                 		}
                 		
                 		if(Math.abs(weight.getStone()) < 0.000001 || Math.abs(weight.getStone()) > 999999999.0) {
                 			mStoneField.setText(weightScientificFormatter.format(weight.getStone()));
                 		} else {
                 			mStoneField.setText(weightDecimalFormatter.format(weight.getStone()));
                 		}
                 		
                 		if(Math.abs(weight.getShortTon()) < 0.000001 || Math.abs(weight.getShortTon()) > 999999999.0) {
                 			mShortTonField.setText(weightScientificFormatter.format(weight.getShortTon()));
                 		} else {
                 			mShortTonField.setText(weightDecimalFormatter.format(weight.getShortTon()));
                 		}
                 		
                 		if(Math.abs(weight.getMetricTon()) < 0.000001 || Math.abs(weight.getMetricTon()) > 999999999.0) {
                 			mMetricTonField.setText(weightScientificFormatter.format(weight.getMetricTon()));
                 		} else {
                 			mMetricTonField.setText(weightDecimalFormatter.format(weight.getMetricTon()));
                 		}
                 		
                 		if(Math.abs(weight.getLongTon()) < 0.000001 || Math.abs(weight.getLongTon()) > 999999999.0) {
                 			mLongTonField.setText(weightScientificFormatter.format(weight.getLongTon()));
                 		} else {
                 			mLongTonField.setText(weightDecimalFormatter.format(weight.getLongTon()));
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
        }); // end addTextChangedListener on mMilligramField
        
        //Set addTextChangedListener on mGramField
        mGramField.addTextChangedListener(new TextWatcher() {
        	// update the other text fields with the converted values
        	public void onTextChanged(CharSequence s, int start, int before, int count) {
        		if(!suppressFieldChanges) {
            		if(!isNumeric(mGramField.getText().toString())) {
                 		// mGramField's text can't be parsed to a double,
                 		// set the other text fields to empty strings
            			suppressFieldChanges = true;
            				
            			mMilligramField.setText("");
            			mOunceField.setText("");
            			mPoundField.setText("");
            			mKilogramField.setText("");
            			mStoneField.setText("");
            			mShortTonField.setText("");
            			mMetricTonField.setText("");
            			mLongTonField.setText("");
                     	
                 		suppressFieldChanges = false;
                 	} else if (Double.parseDouble(mGramField.getText().toString()) == 0) {
                 		// conversions don't need to be performed on a gram value of 0
                 		// set the other text fields to empty strings
                 		suppressFieldChanges = true;
        				
                 		mMilligramField.setText("");
            			mOunceField.setText("");
            			mPoundField.setText("");
            			mKilogramField.setText("");
            			mStoneField.setText("");
            			mShortTonField.setText("");
            			mMetricTonField.setText("");
            			mLongTonField.setText("");
                     	
                 		suppressFieldChanges = false;
                 	} else {
                 		weight.calculateFromGram(Double.parseDouble(mGramField.getText().toString()));
                     	
                 		// update text fields with converted values
                 		suppressFieldChanges = true;
                 		
                 		// these if statements determine if the values should be displayed
                 		// with scientific notation or decimal notation
                 		if(Math.abs(weight.getMilligram()) < 0.000001 || Math.abs(weight.getMilligram()) > 999999999.0) {
                 			mMilligramField.setText(weightScientificFormatter.format(weight.getMilligram()));
                 		} else {
                 			mMilligramField.setText(weightDecimalFormatter.format(weight.getMilligram()));
                 		}
             			
                 		if(Math.abs(weight.getOunce()) < 0.000001 || Math.abs(weight.getOunce()) > 999999999.0) {
                 			mOunceField.setText(weightScientificFormatter.format(weight.getOunce()));
                 		} else {
                 			mOunceField.setText(weightDecimalFormatter.format(weight.getOunce()));
                 		}
                 		
                 		if(Math.abs(weight.getPound()) < 0.000001 || Math.abs(weight.getPound()) > 999999999.0) {
                 			mPoundField.setText(weightScientificFormatter.format(weight.getPound()));
                 		} else {
                 			mPoundField.setText(weightDecimalFormatter.format(weight.getPound()));
                 		}
                 		
                 		if(Math.abs(weight.getKilogram()) < 0.000001 || Math.abs(weight.getKilogram()) > 999999999.0) {
                 			mKilogramField.setText(weightScientificFormatter.format(weight.getKilogram()));
                 		} else {
                 			mKilogramField.setText(weightDecimalFormatter.format(weight.getKilogram()));
                 		}
                 		
                 		if(Math.abs(weight.getStone()) < 0.000001 || Math.abs(weight.getStone()) > 999999999.0) {
                 			mStoneField.setText(weightScientificFormatter.format(weight.getStone()));
                 		} else {
                 			mStoneField.setText(weightDecimalFormatter.format(weight.getStone()));
                 		}
                 		
                 		if(Math.abs(weight.getShortTon()) < 0.000001 || Math.abs(weight.getShortTon()) > 999999999.0) {
                 			mShortTonField.setText(weightScientificFormatter.format(weight.getShortTon()));
                 		} else {
                 			mShortTonField.setText(weightDecimalFormatter.format(weight.getShortTon()));
                 		}
                 		
                 		if(Math.abs(weight.getMetricTon()) < 0.000001 || Math.abs(weight.getMetricTon()) > 999999999.0) {
                 			mMetricTonField.setText(weightScientificFormatter.format(weight.getMetricTon()));
                 		} else {
                 			mMetricTonField.setText(weightDecimalFormatter.format(weight.getMetricTon()));
                 		}
                 		
                 		if(Math.abs(weight.getLongTon()) < 0.000001 || Math.abs(weight.getLongTon()) > 999999999.0) {
                 			mLongTonField.setText(weightScientificFormatter.format(weight.getLongTon()));
                 		} else {
                 			mLongTonField.setText(weightDecimalFormatter.format(weight.getLongTon()));
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
        }); // end addTextChangedListener on mGramField
        
        //Set addTextChangedListener on mOunceField
        mOunceField.addTextChangedListener(new TextWatcher() {
        	// update the other text fields with the converted values
        	public void onTextChanged(CharSequence s, int start, int before, int count) {
        		if(!suppressFieldChanges) {
            		if(!isNumeric(mOunceField.getText().toString())) {
                 		// mOunceField's text can't be parsed to a double,
                 		// set the other text fields to empty strings
            			suppressFieldChanges = true;
            				
            			mMilligramField.setText("");
            			mGramField.setText("");
            			mPoundField.setText("");
            			mKilogramField.setText("");
            			mStoneField.setText("");
            			mShortTonField.setText("");
            			mMetricTonField.setText("");
            			mLongTonField.setText("");
                     	
                 		suppressFieldChanges = false;
                 	} else if (Double.parseDouble(mOunceField.getText().toString()) == 0) {
                 		// conversions don't need to be performed on an ounce value of 0
                 		// set the other text fields to empty strings
                 		suppressFieldChanges = true;
        				
                 		mMilligramField.setText("");
                 		mGramField.setText("");
            			mPoundField.setText("");
            			mKilogramField.setText("");
            			mStoneField.setText("");
            			mShortTonField.setText("");
            			mMetricTonField.setText("");
            			mLongTonField.setText("");
                     	
                 		suppressFieldChanges = false;
                 	} else {
                 		weight.calculateFromOunce(Double.parseDouble(mOunceField.getText().toString()));
                     	
                 		// update text fields with converted values
                 		suppressFieldChanges = true;
                 		
                 		// these if statements determine if the values should be displayed
                 		// with scientific notation or decimal notation
                 		if(Math.abs(weight.getMilligram()) < 0.000001 || Math.abs(weight.getMilligram()) > 999999999.0) {
                 			mMilligramField.setText(weightScientificFormatter.format(weight.getMilligram()));
                 		} else {
                 			mMilligramField.setText(weightDecimalFormatter.format(weight.getMilligram()));
                 		}
             			
                 		if(Math.abs(weight.getGram()) < 0.000001 || Math.abs(weight.getGram()) > 999999999.0) {
                 			mGramField.setText(weightScientificFormatter.format(weight.getGram()));
                 		} else {
                 			mGramField.setText(weightDecimalFormatter.format(weight.getGram()));
                 		}
                 		
                 		if(Math.abs(weight.getPound()) < 0.000001 || Math.abs(weight.getPound()) > 999999999.0) {
                 			mPoundField.setText(weightScientificFormatter.format(weight.getPound()));
                 		} else {
                 			mPoundField.setText(weightDecimalFormatter.format(weight.getPound()));
                 		}
                 		
                 		if(Math.abs(weight.getKilogram()) < 0.000001 || Math.abs(weight.getKilogram()) > 999999999.0) {
                 			mKilogramField.setText(weightScientificFormatter.format(weight.getKilogram()));
                 		} else {
                 			mKilogramField.setText(weightDecimalFormatter.format(weight.getKilogram()));
                 		}
                 		
                 		if(Math.abs(weight.getStone()) < 0.000001 || Math.abs(weight.getStone()) > 999999999.0) {
                 			mStoneField.setText(weightScientificFormatter.format(weight.getStone()));
                 		} else {
                 			mStoneField.setText(weightDecimalFormatter.format(weight.getStone()));
                 		}
                 		
                 		if(Math.abs(weight.getShortTon()) < 0.000001 || Math.abs(weight.getShortTon()) > 999999999.0) {
                 			mShortTonField.setText(weightScientificFormatter.format(weight.getShortTon()));
                 		} else {
                 			mShortTonField.setText(weightDecimalFormatter.format(weight.getShortTon()));
                 		}
                 		
                 		if(Math.abs(weight.getMetricTon()) < 0.000001 || Math.abs(weight.getMetricTon()) > 999999999.0) {
                 			mMetricTonField.setText(weightScientificFormatter.format(weight.getMetricTon()));
                 		} else {
                 			mMetricTonField.setText(weightDecimalFormatter.format(weight.getMetricTon()));
                 		}
                 		
                 		if(Math.abs(weight.getLongTon()) < 0.000001 || Math.abs(weight.getLongTon()) > 999999999.0) {
                 			mLongTonField.setText(weightScientificFormatter.format(weight.getLongTon()));
                 		} else {
                 			mLongTonField.setText(weightDecimalFormatter.format(weight.getLongTon()));
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
        }); // end addTextChangedListener on mOunceField
        
        //Set addTextChangedListener on mPoundField
        mPoundField.addTextChangedListener(new TextWatcher() {
        	// update the other text fields with the converted values
        	public void onTextChanged(CharSequence s, int start, int before, int count) {
        		if(!suppressFieldChanges) {
            		if(!isNumeric(mPoundField.getText().toString())) {
                 		// mPoundField's text can't be parsed to a double,
                 		// set the other text fields to empty strings
            			suppressFieldChanges = true;
            				
            			mMilligramField.setText("");
            			mGramField.setText("");
            			mOunceField.setText("");
            			mKilogramField.setText("");
            			mStoneField.setText("");
            			mShortTonField.setText("");
            			mMetricTonField.setText("");
            			mLongTonField.setText("");
                     	
                 		suppressFieldChanges = false;
                 	} else if (Double.parseDouble(mPoundField.getText().toString()) == 0) {
                 		// conversions don't need to be performed on a pound value of 0
                 		// set the other text fields to empty strings
                 		suppressFieldChanges = true;
        				
                 		mMilligramField.setText("");
                 		mGramField.setText("");
                 		mOunceField.setText("");
            			mKilogramField.setText("");
            			mStoneField.setText("");
            			mShortTonField.setText("");
            			mMetricTonField.setText("");
            			mLongTonField.setText("");
                     	
                 		suppressFieldChanges = false;
                 	} else {
                 		weight.calculateFromPound(Double.parseDouble(mPoundField.getText().toString()));
                     	
                 		// update text fields with converted values
                 		suppressFieldChanges = true;
                 		
                 		// these if statements determine if the values should be displayed
                 		// with scientific notation or decimal notation
                 		if(Math.abs(weight.getMilligram()) < 0.000001 || Math.abs(weight.getMilligram()) > 999999999.0) {
                 			mMilligramField.setText(weightScientificFormatter.format(weight.getMilligram()));
                 		} else {
                 			mMilligramField.setText(weightDecimalFormatter.format(weight.getMilligram()));
                 		}
             			
                 		if(Math.abs(weight.getGram()) < 0.000001 || Math.abs(weight.getGram()) > 999999999.0) {
                 			mGramField.setText(weightScientificFormatter.format(weight.getGram()));
                 		} else {
                 			mGramField.setText(weightDecimalFormatter.format(weight.getGram()));
                 		}
                 		
                 		if(Math.abs(weight.getOunce()) < 0.000001 || Math.abs(weight.getOunce()) > 999999999.0) {
                 			mOunceField.setText(weightScientificFormatter.format(weight.getOunce()));
                 		} else {
                 			mOunceField.setText(weightDecimalFormatter.format(weight.getOunce()));
                 		}
                 		
                 		if(Math.abs(weight.getKilogram()) < 0.000001 || Math.abs(weight.getKilogram()) > 999999999.0) {
                 			mKilogramField.setText(weightScientificFormatter.format(weight.getKilogram()));
                 		} else {
                 			mKilogramField.setText(weightDecimalFormatter.format(weight.getKilogram()));
                 		}
                 		
                 		if(Math.abs(weight.getStone()) < 0.000001 || Math.abs(weight.getStone()) > 999999999.0) {
                 			mStoneField.setText(weightScientificFormatter.format(weight.getStone()));
                 		} else {
                 			mStoneField.setText(weightDecimalFormatter.format(weight.getStone()));
                 		}
                 		
                 		if(Math.abs(weight.getShortTon()) < 0.000001 || Math.abs(weight.getShortTon()) > 999999999.0) {
                 			mShortTonField.setText(weightScientificFormatter.format(weight.getShortTon()));
                 		} else {
                 			mShortTonField.setText(weightDecimalFormatter.format(weight.getShortTon()));
                 		}
                 		
                 		if(Math.abs(weight.getMetricTon()) < 0.000001 || Math.abs(weight.getMetricTon()) > 999999999.0) {
                 			mMetricTonField.setText(weightScientificFormatter.format(weight.getMetricTon()));
                 		} else {
                 			mMetricTonField.setText(weightDecimalFormatter.format(weight.getMetricTon()));
                 		}
                 		
                 		if(Math.abs(weight.getLongTon()) < 0.000001 || Math.abs(weight.getLongTon()) > 999999999.0) {
                 			mLongTonField.setText(weightScientificFormatter.format(weight.getLongTon()));
                 		} else {
                 			mLongTonField.setText(weightDecimalFormatter.format(weight.getLongTon()));
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
        }); // end addTextChangedListener on mPoundField
        
        //Set addTextChangedListener on mKilogramField
        mKilogramField.addTextChangedListener(new TextWatcher() {
        	// update the other text fields with the converted values
        	public void onTextChanged(CharSequence s, int start, int before, int count) {
        		if(!suppressFieldChanges) {
            		if(!isNumeric(mKilogramField.getText().toString())) {
                 		// mKilogramField's text can't be parsed to a double,
                 		// set the other text fields to empty strings
            			suppressFieldChanges = true;
            				
            			mMilligramField.setText("");
            			mGramField.setText("");
            			mOunceField.setText("");
            			mPoundField.setText("");
            			mStoneField.setText("");
            			mShortTonField.setText("");
            			mMetricTonField.setText("");
            			mLongTonField.setText("");
                     	
                 		suppressFieldChanges = false;
                 	} else if (Double.parseDouble(mKilogramField.getText().toString()) == 0) {
                 		// conversions don't need to be performed on a kilogram value of 0
                 		// set the other text fields to empty strings
                 		suppressFieldChanges = true;
        				
                 		mMilligramField.setText("");
                 		mGramField.setText("");
                 		mOunceField.setText("");
                 		mPoundField.setText("");
            			mStoneField.setText("");
            			mShortTonField.setText("");
            			mMetricTonField.setText("");
            			mLongTonField.setText("");
                     	
                 		suppressFieldChanges = false;
                 	} else {
                 		weight.calculateFromKilogram(Double.parseDouble(mKilogramField.getText().toString()));
                     	
                 		// update text fields with converted values
                 		suppressFieldChanges = true;
                 		
                 		// these if statements determine if the values should be displayed
                 		// with scientific notation or decimal notation
                 		if(Math.abs(weight.getMilligram()) < 0.000001 || Math.abs(weight.getMilligram()) > 999999999.0) {
                 			mMilligramField.setText(weightScientificFormatter.format(weight.getMilligram()));
                 		} else {
                 			mMilligramField.setText(weightDecimalFormatter.format(weight.getMilligram()));
                 		}
             			
                 		if(Math.abs(weight.getGram()) < 0.000001 || Math.abs(weight.getGram()) > 999999999.0) {
                 			mGramField.setText(weightScientificFormatter.format(weight.getGram()));
                 		} else {
                 			mGramField.setText(weightDecimalFormatter.format(weight.getGram()));
                 		}
                 		
                 		if(Math.abs(weight.getOunce()) < 0.000001 || Math.abs(weight.getOunce()) > 999999999.0) {
                 			mOunceField.setText(weightScientificFormatter.format(weight.getOunce()));
                 		} else {
                 			mOunceField.setText(weightDecimalFormatter.format(weight.getOunce()));
                 		}
                 		
                 		if(Math.abs(weight.getPound()) < 0.000001 || Math.abs(weight.getPound()) > 999999999.0) {
                 			mPoundField.setText(weightScientificFormatter.format(weight.getPound()));
                 		} else {
                 			mPoundField.setText(weightDecimalFormatter.format(weight.getPound()));
                 		}
                 		
                 		if(Math.abs(weight.getStone()) < 0.000001 || Math.abs(weight.getStone()) > 999999999.0) {
                 			mStoneField.setText(weightScientificFormatter.format(weight.getStone()));
                 		} else {
                 			mStoneField.setText(weightDecimalFormatter.format(weight.getStone()));
                 		}
                 		
                 		if(Math.abs(weight.getShortTon()) < 0.000001 || Math.abs(weight.getShortTon()) > 999999999.0) {
                 			mShortTonField.setText(weightScientificFormatter.format(weight.getShortTon()));
                 		} else {
                 			mShortTonField.setText(weightDecimalFormatter.format(weight.getShortTon()));
                 		}
                 		
                 		if(Math.abs(weight.getMetricTon()) < 0.000001 || Math.abs(weight.getMetricTon()) > 999999999.0) {
                 			mMetricTonField.setText(weightScientificFormatter.format(weight.getMetricTon()));
                 		} else {
                 			mMetricTonField.setText(weightDecimalFormatter.format(weight.getMetricTon()));
                 		}
                 		
                 		if(Math.abs(weight.getLongTon()) < 0.000001 || Math.abs(weight.getLongTon()) > 999999999.0) {
                 			mLongTonField.setText(weightScientificFormatter.format(weight.getLongTon()));
                 		} else {
                 			mLongTonField.setText(weightDecimalFormatter.format(weight.getLongTon()));
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
        }); // end addTextChangedListener on mKilogramField
        
        //Set addTextChangedListener on mStoneField
        mStoneField.addTextChangedListener(new TextWatcher() {
        	// update the other text fields with the converted values
        	public void onTextChanged(CharSequence s, int start, int before, int count) {
        		if(!suppressFieldChanges) {
            		if(!isNumeric(mStoneField.getText().toString())) {
                 		// mStoneField's text can't be parsed to a double,
                 		// set the other text fields to empty strings
            			suppressFieldChanges = true;
            				
            			mMilligramField.setText("");
            			mGramField.setText("");
            			mOunceField.setText("");
            			mPoundField.setText("");
            			mKilogramField.setText("");
            			mShortTonField.setText("");
            			mMetricTonField.setText("");
            			mLongTonField.setText("");
                     	
                 		suppressFieldChanges = false;
                 	} else if (Double.parseDouble(mStoneField.getText().toString()) == 0) {
                 		// conversions don't need to be performed on a stone value of 0
                 		// set the other text fields to empty strings
                 		suppressFieldChanges = true;
        				
                 		mMilligramField.setText("");
                 		mGramField.setText("");
                 		mOunceField.setText("");
                 		mPoundField.setText("");
                 		mKilogramField.setText("");
            			mShortTonField.setText("");
            			mMetricTonField.setText("");
            			mLongTonField.setText("");
                     	
                 		suppressFieldChanges = false;
                 	} else {
                 		weight.calculateFromStone(Double.parseDouble(mStoneField.getText().toString()));
                     	
                 		// update text fields with converted values
                 		suppressFieldChanges = true;
                 		
                 		// these if statements determine if the values should be displayed
                 		// with scientific notation or decimal notation
                 		if(Math.abs(weight.getMilligram()) < 0.000001 || Math.abs(weight.getMilligram()) > 999999999.0) {
                 			mMilligramField.setText(weightScientificFormatter.format(weight.getMilligram()));
                 		} else {
                 			mMilligramField.setText(weightDecimalFormatter.format(weight.getMilligram()));
                 		}
             			
                 		if(Math.abs(weight.getGram()) < 0.000001 || Math.abs(weight.getGram()) > 999999999.0) {
                 			mGramField.setText(weightScientificFormatter.format(weight.getGram()));
                 		} else {
                 			mGramField.setText(weightDecimalFormatter.format(weight.getGram()));
                 		}
                 		
                 		if(Math.abs(weight.getOunce()) < 0.000001 || Math.abs(weight.getOunce()) > 999999999.0) {
                 			mOunceField.setText(weightScientificFormatter.format(weight.getOunce()));
                 		} else {
                 			mOunceField.setText(weightDecimalFormatter.format(weight.getOunce()));
                 		}
                 		
                 		if(Math.abs(weight.getPound()) < 0.000001 || Math.abs(weight.getPound()) > 999999999.0) {
                 			mPoundField.setText(weightScientificFormatter.format(weight.getPound()));
                 		} else {
                 			mPoundField.setText(weightDecimalFormatter.format(weight.getPound()));
                 		}
                 		
                 		if(Math.abs(weight.getKilogram()) < 0.000001 || Math.abs(weight.getKilogram()) > 999999999.0) {
                 			mKilogramField.setText(weightScientificFormatter.format(weight.getKilogram()));
                 		} else {
                 			mKilogramField.setText(weightDecimalFormatter.format(weight.getKilogram()));
                 		}
                 		
                 		if(Math.abs(weight.getShortTon()) < 0.000001 || Math.abs(weight.getShortTon()) > 999999999.0) {
                 			mShortTonField.setText(weightScientificFormatter.format(weight.getShortTon()));
                 		} else {
                 			mShortTonField.setText(weightDecimalFormatter.format(weight.getShortTon()));
                 		}
                 		
                 		if(Math.abs(weight.getMetricTon()) < 0.000001 || Math.abs(weight.getMetricTon()) > 999999999.0) {
                 			mMetricTonField.setText(weightScientificFormatter.format(weight.getMetricTon()));
                 		} else {
                 			mMetricTonField.setText(weightDecimalFormatter.format(weight.getMetricTon()));
                 		}
                 		
                 		if(Math.abs(weight.getLongTon()) < 0.000001 || Math.abs(weight.getLongTon()) > 999999999.0) {
                 			mLongTonField.setText(weightScientificFormatter.format(weight.getLongTon()));
                 		} else {
                 			mLongTonField.setText(weightDecimalFormatter.format(weight.getLongTon()));
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
        }); // end addTextChangedListener on mStoneField
        
        //Set addTextChangedListener on mShortTonField
        mShortTonField.addTextChangedListener(new TextWatcher() {
        	// update the other text fields with the converted values
        	public void onTextChanged(CharSequence s, int start, int before, int count) {
        		if(!suppressFieldChanges) {
            		if(!isNumeric(mShortTonField.getText().toString())) {
                 		// mShortTonField's text can't be parsed to a double,
                 		// set the other text fields to empty strings
            			suppressFieldChanges = true;
            				
            			mMilligramField.setText("");
            			mGramField.setText("");
            			mOunceField.setText("");
            			mPoundField.setText("");
            			mKilogramField.setText("");
            			mStoneField.setText("");
            			mMetricTonField.setText("");
            			mLongTonField.setText("");
                     	
                 		suppressFieldChanges = false;
                 	} else if (Double.parseDouble(mShortTonField.getText().toString()) == 0) {
                 		// conversions don't need to be performed on a shortTon value of 0
                 		// set the other text fields to empty strings
                 		suppressFieldChanges = true;
        				
                 		mMilligramField.setText("");
                 		mGramField.setText("");
                 		mOunceField.setText("");
                 		mPoundField.setText("");
                 		mKilogramField.setText("");
                 		mStoneField.setText("");
            			mMetricTonField.setText("");
            			mLongTonField.setText("");
                     	
                 		suppressFieldChanges = false;
                 	} else {
                 		weight.calculateFromShortTon(Double.parseDouble(mShortTonField.getText().toString()));
                     	
                 		// update text fields with converted values
                 		suppressFieldChanges = true;
                 		
                 		// these if statements determine if the values should be displayed
                 		// with scientific notation or decimal notation
                 		if(Math.abs(weight.getMilligram()) < 0.000001 || Math.abs(weight.getMilligram()) > 999999999.0) {
                 			mMilligramField.setText(weightScientificFormatter.format(weight.getMilligram()));
                 		} else {
                 			mMilligramField.setText(weightDecimalFormatter.format(weight.getMilligram()));
                 		}
             			
                 		if(Math.abs(weight.getGram()) < 0.000001 || Math.abs(weight.getGram()) > 999999999.0) {
                 			mGramField.setText(weightScientificFormatter.format(weight.getGram()));
                 		} else {
                 			mGramField.setText(weightDecimalFormatter.format(weight.getGram()));
                 		}
                 		
                 		if(Math.abs(weight.getOunce()) < 0.000001 || Math.abs(weight.getOunce()) > 999999999.0) {
                 			mOunceField.setText(weightScientificFormatter.format(weight.getOunce()));
                 		} else {
                 			mOunceField.setText(weightDecimalFormatter.format(weight.getOunce()));
                 		}
                 		
                 		if(Math.abs(weight.getPound()) < 0.000001 || Math.abs(weight.getPound()) > 999999999.0) {
                 			mPoundField.setText(weightScientificFormatter.format(weight.getPound()));
                 		} else {
                 			mPoundField.setText(weightDecimalFormatter.format(weight.getPound()));
                 		}
                 		
                 		if(Math.abs(weight.getKilogram()) < 0.000001 || Math.abs(weight.getKilogram()) > 999999999.0) {
                 			mKilogramField.setText(weightScientificFormatter.format(weight.getKilogram()));
                 		} else {
                 			mKilogramField.setText(weightDecimalFormatter.format(weight.getKilogram()));
                 		}
                 		
                 		if(Math.abs(weight.getStone()) < 0.000001 || Math.abs(weight.getStone()) > 999999999.0) {
                 			mStoneField.setText(weightScientificFormatter.format(weight.getStone()));
                 		} else {
                 			mStoneField.setText(weightDecimalFormatter.format(weight.getStone()));
                 		}
                 		
                 		if(Math.abs(weight.getMetricTon()) < 0.000001 || Math.abs(weight.getMetricTon()) > 999999999.0) {
                 			mMetricTonField.setText(weightScientificFormatter.format(weight.getMetricTon()));
                 		} else {
                 			mMetricTonField.setText(weightDecimalFormatter.format(weight.getMetricTon()));
                 		}
                 		
                 		if(Math.abs(weight.getLongTon()) < 0.000001 || Math.abs(weight.getLongTon()) > 999999999.0) {
                 			mLongTonField.setText(weightScientificFormatter.format(weight.getLongTon()));
                 		} else {
                 			mLongTonField.setText(weightDecimalFormatter.format(weight.getLongTon()));
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
        }); // end addTextChangedListener on mShortTonField
        
        //Set addTextChangedListener on mMetricTonField
        mMetricTonField.addTextChangedListener(new TextWatcher() {
        	// update the other text fields with the converted values
        	public void onTextChanged(CharSequence s, int start, int before, int count) {
        		if(!suppressFieldChanges) {
            		if(!isNumeric(mMetricTonField.getText().toString())) {
                 		// mMetricTonField's text can't be parsed to a double,
                 		// set the other text fields to empty strings
            			suppressFieldChanges = true;
            				
            			mMilligramField.setText("");
            			mGramField.setText("");
            			mOunceField.setText("");
            			mPoundField.setText("");
            			mKilogramField.setText("");
            			mStoneField.setText("");
            			mShortTonField.setText("");
            			mLongTonField.setText("");
                     	
                 		suppressFieldChanges = false;
                 	} else if (Double.parseDouble(mMetricTonField.getText().toString()) == 0) {
                 		// conversions don't need to be performed on a metricTon value of 0
                 		// set the other text fields to empty strings
                 		suppressFieldChanges = true;
        				
                 		mMilligramField.setText("");
                 		mGramField.setText("");
                 		mOunceField.setText("");
                 		mPoundField.setText("");
                 		mKilogramField.setText("");
                 		mStoneField.setText("");
                 		mShortTonField.setText("");
            			mLongTonField.setText("");
                     	
                 		suppressFieldChanges = false;
                 	} else {
                 		weight.calculateFromMetricTon(Double.parseDouble(mMetricTonField.getText().toString()));
                     	
                 		// update text fields with converted values
                 		suppressFieldChanges = true;
                 		
                 		// these if statements determine if the values should be displayed
                 		// with scientific notation or decimal notation
                 		if(Math.abs(weight.getMilligram()) < 0.000001 || Math.abs(weight.getMilligram()) > 999999999.0) {
                 			mMilligramField.setText(weightScientificFormatter.format(weight.getMilligram()));
                 		} else {
                 			mMilligramField.setText(weightDecimalFormatter.format(weight.getMilligram()));
                 		}
             			
                 		if(Math.abs(weight.getGram()) < 0.000001 || Math.abs(weight.getGram()) > 999999999.0) {
                 			mGramField.setText(weightScientificFormatter.format(weight.getGram()));
                 		} else {
                 			mGramField.setText(weightDecimalFormatter.format(weight.getGram()));
                 		}
                 		
                 		if(Math.abs(weight.getOunce()) < 0.000001 || Math.abs(weight.getOunce()) > 999999999.0) {
                 			mOunceField.setText(weightScientificFormatter.format(weight.getOunce()));
                 		} else {
                 			mOunceField.setText(weightDecimalFormatter.format(weight.getOunce()));
                 		}
                 		
                 		if(Math.abs(weight.getPound()) < 0.000001 || Math.abs(weight.getPound()) > 999999999.0) {
                 			mPoundField.setText(weightScientificFormatter.format(weight.getPound()));
                 		} else {
                 			mPoundField.setText(weightDecimalFormatter.format(weight.getPound()));
                 		}
                 		
                 		if(Math.abs(weight.getKilogram()) < 0.000001 || Math.abs(weight.getKilogram()) > 999999999.0) {
                 			mKilogramField.setText(weightScientificFormatter.format(weight.getKilogram()));
                 		} else {
                 			mKilogramField.setText(weightDecimalFormatter.format(weight.getKilogram()));
                 		}
                 		
                 		if(Math.abs(weight.getStone()) < 0.000001 || Math.abs(weight.getStone()) > 999999999.0) {
                 			mStoneField.setText(weightScientificFormatter.format(weight.getStone()));
                 		} else {
                 			mStoneField.setText(weightDecimalFormatter.format(weight.getStone()));
                 		}
                 		
                 		if(Math.abs(weight.getShortTon()) < 0.000001 || Math.abs(weight.getShortTon()) > 999999999.0) {
                 			mShortTonField.setText(weightScientificFormatter.format(weight.getShortTon()));
                 		} else {
                 			mShortTonField.setText(weightDecimalFormatter.format(weight.getShortTon()));
                 		}
                 		
                 		if(Math.abs(weight.getLongTon()) < 0.000001 || Math.abs(weight.getLongTon()) > 999999999.0) {
                 			mLongTonField.setText(weightScientificFormatter.format(weight.getLongTon()));
                 		} else {
                 			mLongTonField.setText(weightDecimalFormatter.format(weight.getLongTon()));
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
        }); // end addTextChangedListener on mMetricTonField
        
        //Set addTextChangedListener on mLongTonField
        mLongTonField.addTextChangedListener(new TextWatcher() {
        	// update the other text fields with the converted values
        	public void onTextChanged(CharSequence s, int start, int before, int count) {
        		if(!suppressFieldChanges) {
            		if(!isNumeric(mLongTonField.getText().toString())) {
                 		// mLongTonField's text can't be parsed to a double,
                 		// set the other text fields to empty strings
            			suppressFieldChanges = true;
            				
            			mMilligramField.setText("");
            			mGramField.setText("");
            			mOunceField.setText("");
            			mPoundField.setText("");
            			mKilogramField.setText("");
            			mStoneField.setText("");
            			mShortTonField.setText("");
            			mMetricTonField.setText("");
                     	
                 		suppressFieldChanges = false;
                 	} else if (Double.parseDouble(mLongTonField.getText().toString()) == 0) {
                 		// conversions don't need to be performed on a longTon value of 0
                 		// set the other text fields to empty strings
                 		suppressFieldChanges = true;
        				
                 		mMilligramField.setText("");
                 		mGramField.setText("");
                 		mOunceField.setText("");
                 		mPoundField.setText("");
                 		mKilogramField.setText("");
                 		mStoneField.setText("");
                 		mShortTonField.setText("");
                 		mMetricTonField.setText("");
                     	
                 		suppressFieldChanges = false;
                 	} else {
                 		weight.calculateFromLongTon(Double.parseDouble(mLongTonField.getText().toString()));
                     	
                 		// update text fields with converted values
                 		suppressFieldChanges = true;
                 		
                 		// these if statements determine if the values should be displayed
                 		// with scientific notation or decimal notation
                 		if(Math.abs(weight.getMilligram()) < 0.000001 || Math.abs(weight.getMilligram()) > 999999999.0) {
                 			mMilligramField.setText(weightScientificFormatter.format(weight.getMilligram()));
                 		} else {
                 			mMilligramField.setText(weightDecimalFormatter.format(weight.getMilligram()));
                 		}
             			
                 		if(Math.abs(weight.getGram()) < 0.000001 || Math.abs(weight.getGram()) > 999999999.0) {
                 			mGramField.setText(weightScientificFormatter.format(weight.getGram()));
                 		} else {
                 			mGramField.setText(weightDecimalFormatter.format(weight.getGram()));
                 		}
                 		
                 		if(Math.abs(weight.getOunce()) < 0.000001 || Math.abs(weight.getOunce()) > 999999999.0) {
                 			mOunceField.setText(weightScientificFormatter.format(weight.getOunce()));
                 		} else {
                 			mOunceField.setText(weightDecimalFormatter.format(weight.getOunce()));
                 		}
                 		
                 		if(Math.abs(weight.getPound()) < 0.000001 || Math.abs(weight.getPound()) > 999999999.0) {
                 			mPoundField.setText(weightScientificFormatter.format(weight.getPound()));
                 		} else {
                 			mPoundField.setText(weightDecimalFormatter.format(weight.getPound()));
                 		}
                 		
                 		if(Math.abs(weight.getKilogram()) < 0.000001 || Math.abs(weight.getKilogram()) > 999999999.0) {
                 			mKilogramField.setText(weightScientificFormatter.format(weight.getKilogram()));
                 		} else {
                 			mKilogramField.setText(weightDecimalFormatter.format(weight.getKilogram()));
                 		}
                 		
                 		if(Math.abs(weight.getStone()) < 0.000001 || Math.abs(weight.getStone()) > 999999999.0) {
                 			mStoneField.setText(weightScientificFormatter.format(weight.getStone()));
                 		} else {
                 			mStoneField.setText(weightDecimalFormatter.format(weight.getStone()));
                 		}
                 		
                 		if(Math.abs(weight.getShortTon()) < 0.000001 || Math.abs(weight.getShortTon()) > 999999999.0) {
                 			mShortTonField.setText(weightScientificFormatter.format(weight.getShortTon()));
                 		} else {
                 			mShortTonField.setText(weightDecimalFormatter.format(weight.getShortTon()));
                 		}
                 		
                 		if(Math.abs(weight.getMetricTon()) < 0.000001 || Math.abs(weight.getMetricTon()) > 999999999.0) {
                 			mMetricTonField.setText(weightScientificFormatter.format(weight.getMetricTon()));
                 		} else {
                 			mMetricTonField.setText(weightDecimalFormatter.format(weight.getMetricTon()));
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
        }); // end addTextChangedListener on mLongTonField
        
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
    	mMilligramField.setText("");
    	mGramField.setText("");
    	mOunceField.setText("");
    	mPoundField.setText("");
    	mKilogramField.setText("");
    	mStoneField.setText("");
    	mShortTonField.setText("");
    	mMetricTonField.setText("");
    	mLongTonField.setText("");
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
} // end class WeightFragment