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
 
public class VolumeFragment extends Fragment {
	private EditText mMilliliterField; // will reference milliliter_text_field
	private EditText mTeaspoonField; // will reference teaspoon_text_field
	private EditText mCentiliterField; // will reference centiliter_text_field
	private EditText mTablespoonField; // will reference tablespoon_text_field
	private EditText mOunceField; // will reference ounce_volume_text_field
	private EditText mCupField; // will reference cup_text_field
	private EditText mPintField; // will reference pint_text_field
	private EditText mLiterField; // will reference liter_text_field
	private EditText mGallonField; // will reference gallon_text_field
	private EditText mKiloliterField; // will reference kiloliter_text_field
	private Button mResetButton; // will reference volume_reset_button
	private VolumeModel volume; // model object to hold volume data
	private Boolean suppressFieldChanges; // use this boolean to prevent onTextChanged infinite loop
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	// inflate the fragment
        View rootView = inflater.inflate(R.layout.fragment_volume, container, false);
        
        // initialize volume
        volume = new VolumeModel();
        
        // initialize boolean variable
        suppressFieldChanges = false;
        
        // create decimal formatter with decimal notation
        final DecimalFormat weightDecimalFormatter = new DecimalFormat("0.0#####");
        weightDecimalFormatter.setRoundingMode(RoundingMode.HALF_UP);
        
        // create decimal formatter with scientific notation
        final DecimalFormat weightScientificFormatter = new DecimalFormat("0.0000E0");
        
        // set references to the text fields
        mMilliliterField = (EditText)rootView.findViewById(R.id.milliliter_text_field);
        mTeaspoonField = (EditText)rootView.findViewById(R.id.teaspoon_text_field);
        mCentiliterField = (EditText)rootView.findViewById(R.id.centiliter_text_field);
        mTablespoonField = (EditText)rootView.findViewById(R.id.tablespoon_text_field);
        mOunceField = (EditText)rootView.findViewById(R.id.ounce_volume_text_field);
        mCupField = (EditText)rootView.findViewById(R.id.cup_text_field);
        mPintField = (EditText)rootView.findViewById(R.id.pint_text_field);
        mLiterField = (EditText)rootView.findViewById(R.id.liter_text_field);
        mGallonField = (EditText)rootView.findViewById(R.id.gallon_text_field);
        mKiloliterField = (EditText)rootView.findViewById(R.id.kiloliter_text_field);
        mResetButton = (Button)rootView.findViewById(R.id.volume_reset_button);
        
        // Set addTextChangedListener on mMilliliterField
        mMilliliterField.addTextChangedListener(new TextWatcher() {
        	// update the other text fields with the converted values
        	public void onTextChanged(CharSequence s, int start, int before, int count) {
        		if(!suppressFieldChanges) {
            		if(!isNumeric(mMilliliterField.getText().toString())) {
                 		// mMilliliterField's text can't be parsed to a double,
                 		// set the other text fields to empty strings
            			suppressFieldChanges = true;
            				
            			mTeaspoonField.setText("");
            			mCentiliterField.setText("");
            			mTablespoonField.setText("");
            			mOunceField.setText("");
            			mCupField.setText("");
            			mPintField.setText("");
            			mLiterField.setText("");
            			mGallonField.setText("");
            			mKiloliterField.setText("");
                     	
                 		suppressFieldChanges = false;
                 	} else if (Double.parseDouble(mMilliliterField.getText().toString()) == 0) {
                 		// conversions don't need to be performed on a milliliter value of 0
                 		// set the other text fields to empty strings
                 		suppressFieldChanges = true;
        				
                 		mTeaspoonField.setText("");
            			mCentiliterField.setText("");
            			mTablespoonField.setText("");
            			mOunceField.setText("");
            			mCupField.setText("");
            			mPintField.setText("");
            			mLiterField.setText("");
            			mGallonField.setText("");
            			mKiloliterField.setText("");
                     	
                 		suppressFieldChanges = false;
                 	} else {
                 		volume.calculateFromMilliliter(Double.parseDouble(mMilliliterField.getText().toString()));

                 		// update text fields with converted values
                 		suppressFieldChanges = true;
                 		
                 		// these if statements determine if the values should be displayed
                 		// with scientific notation or decimal notation
                 		if(Math.abs(volume.getTeaspoon()) < 0.000001 || Math.abs(volume.getTeaspoon()) > 999999999.0) {
                 			mTeaspoonField.setText(weightScientificFormatter.format(volume.getTeaspoon()));
                 		} else {
                 			mTeaspoonField.setText(weightDecimalFormatter.format(volume.getTeaspoon()));
                 		}
             			
                 		if(Math.abs(volume.getCentiliter()) < 0.000001 || Math.abs(volume.getCentiliter()) > 999999999.0) {
                 			mCentiliterField.setText(weightScientificFormatter.format(volume.getCentiliter()));
                 		} else {
                 			mCentiliterField.setText(weightDecimalFormatter.format(volume.getCentiliter()));
                 		}
                 		
                 		if(Math.abs(volume.getTablespoon()) < 0.000001 || Math.abs(volume.getTablespoon()) > 999999999.0) {
                 			mTablespoonField.setText(weightScientificFormatter.format(volume.getTablespoon()));
                 		} else {
                 			mTablespoonField.setText(weightDecimalFormatter.format(volume.getTablespoon()));
                 		}
                 		
                 		if(Math.abs(volume.getOunce()) < 0.000001 || Math.abs(volume.getOunce()) > 999999999.0) {
                 			mOunceField.setText(weightScientificFormatter.format(volume.getOunce()));
                 		} else {
                 			mOunceField.setText(weightDecimalFormatter.format(volume.getOunce()));
                 		}
                 		
                 		if(Math.abs(volume.getCup()) < 0.000001 || Math.abs(volume.getCup()) > 999999999.0) {
                 			mCupField.setText(weightScientificFormatter.format(volume.getCup()));
                 		} else {
                 			mCupField.setText(weightDecimalFormatter.format(volume.getCup()));
                 		}
                 		
                 		if(Math.abs(volume.getPint()) < 0.000001 || Math.abs(volume.getPint()) > 999999999.0) {
                 			mPintField.setText(weightScientificFormatter.format(volume.getPint()));
                 		} else {
                 			mPintField.setText(weightDecimalFormatter.format(volume.getPint()));
                 		}
                 		
                 		if(Math.abs(volume.getLiter()) < 0.000001 || Math.abs(volume.getLiter()) > 999999999.0) {
                 			mLiterField.setText(weightScientificFormatter.format(volume.getLiter()));
                 		} else {
                 			mLiterField.setText(weightDecimalFormatter.format(volume.getLiter()));
                 		}
                 		
                 		if(Math.abs(volume.getGallon()) < 0.000001 || Math.abs(volume.getGallon()) > 999999999.0) {
                 			mGallonField.setText(weightScientificFormatter.format(volume.getGallon()));
                 		} else {
                 			mGallonField.setText(weightDecimalFormatter.format(volume.getGallon()));
                 		}
                 		
                 		if(Math.abs(volume.getKiloliter()) < 0.000001 || Math.abs(volume.getKiloliter()) > 999999999.0) {
                 			mKiloliterField.setText(weightScientificFormatter.format(volume.getKiloliter()));
                 		} else {
                 			mKiloliterField.setText(weightDecimalFormatter.format(volume.getKiloliter()));
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
        }); // end addTextChangedListener on mMilliliterField
        
        // Set addTextChangedListener on mTeaspoonField
        mTeaspoonField.addTextChangedListener(new TextWatcher() {
        	// update the other text fields with the converted values
        	public void onTextChanged(CharSequence s, int start, int before, int count) {
        		if(!suppressFieldChanges) {
            		if(!isNumeric(mTeaspoonField.getText().toString())) {
                 		// mTeaspoonField's text can't be parsed to a double,
                 		// set the other text fields to empty strings
            			suppressFieldChanges = true;
            				
            			mMilliliterField.setText("");
            			mCentiliterField.setText("");
            			mTablespoonField.setText("");
            			mOunceField.setText("");
            			mCupField.setText("");
            			mPintField.setText("");
            			mLiterField.setText("");
            			mGallonField.setText("");
            			mKiloliterField.setText("");
                     	
                 		suppressFieldChanges = false;
                 	} else if (Double.parseDouble(mTeaspoonField.getText().toString()) == 0) {
                 		// conversions don't need to be performed on a teaspoon value of 0
                 		// set the other text fields to empty strings
                 		suppressFieldChanges = true;
        				
                 		mMilliliterField.setText("");
            			mCentiliterField.setText("");
            			mTablespoonField.setText("");
            			mOunceField.setText("");
            			mCupField.setText("");
            			mPintField.setText("");
            			mLiterField.setText("");
            			mGallonField.setText("");
            			mKiloliterField.setText("");
                     	
                 		suppressFieldChanges = false;
                 	} else {
                 		volume.calculateFromTeaspoon(Double.parseDouble(mTeaspoonField.getText().toString()));

                 		// update text fields with converted values
                 		suppressFieldChanges = true;
                 		
                 		// these if statements determine if the values should be displayed
                 		// with scientific notation or decimal notation
                 		if(Math.abs(volume.getMilliliter()) < 0.000001 || Math.abs(volume.getMilliliter()) > 999999999.0) {
                 			mMilliliterField.setText(weightScientificFormatter.format(volume.getMilliliter()));
                 		} else {
                 			mMilliliterField.setText(weightDecimalFormatter.format(volume.getMilliliter()));
                 		}
             			
                 		if(Math.abs(volume.getCentiliter()) < 0.000001 || Math.abs(volume.getCentiliter()) > 999999999.0) {
                 			mCentiliterField.setText(weightScientificFormatter.format(volume.getCentiliter()));
                 		} else {
                 			mCentiliterField.setText(weightDecimalFormatter.format(volume.getCentiliter()));
                 		}
                 		
                 		if(Math.abs(volume.getTablespoon()) < 0.000001 || Math.abs(volume.getTablespoon()) > 999999999.0) {
                 			mTablespoonField.setText(weightScientificFormatter.format(volume.getTablespoon()));
                 		} else {
                 			mTablespoonField.setText(weightDecimalFormatter.format(volume.getTablespoon()));
                 		}
                 		
                 		if(Math.abs(volume.getOunce()) < 0.000001 || Math.abs(volume.getOunce()) > 999999999.0) {
                 			mOunceField.setText(weightScientificFormatter.format(volume.getOunce()));
                 		} else {
                 			mOunceField.setText(weightDecimalFormatter.format(volume.getOunce()));
                 		}
                 		
                 		if(Math.abs(volume.getCup()) < 0.000001 || Math.abs(volume.getCup()) > 999999999.0) {
                 			mCupField.setText(weightScientificFormatter.format(volume.getCup()));
                 		} else {
                 			mCupField.setText(weightDecimalFormatter.format(volume.getCup()));
                 		}
                 		
                 		if(Math.abs(volume.getPint()) < 0.000001 || Math.abs(volume.getPint()) > 999999999.0) {
                 			mPintField.setText(weightScientificFormatter.format(volume.getPint()));
                 		} else {
                 			mPintField.setText(weightDecimalFormatter.format(volume.getPint()));
                 		}
                 		
                 		if(Math.abs(volume.getLiter()) < 0.000001 || Math.abs(volume.getLiter()) > 999999999.0) {
                 			mLiterField.setText(weightScientificFormatter.format(volume.getLiter()));
                 		} else {
                 			mLiterField.setText(weightDecimalFormatter.format(volume.getLiter()));
                 		}
                 		
                 		if(Math.abs(volume.getGallon()) < 0.000001 || Math.abs(volume.getGallon()) > 999999999.0) {
                 			mGallonField.setText(weightScientificFormatter.format(volume.getGallon()));
                 		} else {
                 			mGallonField.setText(weightDecimalFormatter.format(volume.getGallon()));
                 		}
                 		
                 		if(Math.abs(volume.getKiloliter()) < 0.000001 || Math.abs(volume.getKiloliter()) > 999999999.0) {
                 			mKiloliterField.setText(weightScientificFormatter.format(volume.getKiloliter()));
                 		} else {
                 			mKiloliterField.setText(weightDecimalFormatter.format(volume.getKiloliter()));
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
        }); // end addTextChangedListener on mTeaspoonField
        
        // Set addTextChangedListener on mCentiliterField
        mCentiliterField.addTextChangedListener(new TextWatcher() {
        	// update the other text fields with the converted values
        	public void onTextChanged(CharSequence s, int start, int before, int count) {
        		if(!suppressFieldChanges) {
            		if(!isNumeric(mCentiliterField.getText().toString())) {
                 		// mCentiliterField text can't be parsed to a double,
                 		// set the other text fields to empty strings
            			suppressFieldChanges = true;
            				
            			mMilliliterField.setText("");
            			mTeaspoonField.setText("");
            			mTablespoonField.setText("");
            			mOunceField.setText("");
            			mCupField.setText("");
            			mPintField.setText("");
            			mLiterField.setText("");
            			mGallonField.setText("");
            			mKiloliterField.setText("");
                     	
                 		suppressFieldChanges = false;
                 	} else if (Double.parseDouble(mCentiliterField.getText().toString()) == 0) {
                 		// conversions don't need to be performed on a centiliter value of 0
                 		// set the other text fields to empty strings
                 		suppressFieldChanges = true;
        				
                 		mMilliliterField.setText("");
                 		mTeaspoonField.setText("");
            			mTablespoonField.setText("");
            			mOunceField.setText("");
            			mCupField.setText("");
            			mPintField.setText("");
            			mLiterField.setText("");
            			mGallonField.setText("");
            			mKiloliterField.setText("");
                     	
                 		suppressFieldChanges = false;
                 	} else {
                 		volume.calculateFromCentiliter(Double.parseDouble(mCentiliterField.getText().toString()));

                 		// update text fields with converted values
                 		suppressFieldChanges = true;
                 		
                 		// these if statements determine if the values should be displayed
                 		// with scientific notation or decimal notation
                 		if(Math.abs(volume.getMilliliter()) < 0.000001 || Math.abs(volume.getMilliliter()) > 999999999.0) {
                 			mMilliliterField.setText(weightScientificFormatter.format(volume.getMilliliter()));
                 		} else {
                 			mMilliliterField.setText(weightDecimalFormatter.format(volume.getMilliliter()));
                 		}
             			
                 		if(Math.abs(volume.getTeaspoon()) < 0.000001 || Math.abs(volume.getTeaspoon()) > 999999999.0) {
                 			mTeaspoonField.setText(weightScientificFormatter.format(volume.getTeaspoon()));
                 		} else {
                 			mTeaspoonField.setText(weightDecimalFormatter.format(volume.getTeaspoon()));
                 		}
                 		
                 		if(Math.abs(volume.getTablespoon()) < 0.000001 || Math.abs(volume.getTablespoon()) > 999999999.0) {
                 			mTablespoonField.setText(weightScientificFormatter.format(volume.getTablespoon()));
                 		} else {
                 			mTablespoonField.setText(weightDecimalFormatter.format(volume.getTablespoon()));
                 		}
                 		
                 		if(Math.abs(volume.getOunce()) < 0.000001 || Math.abs(volume.getOunce()) > 999999999.0) {
                 			mOunceField.setText(weightScientificFormatter.format(volume.getOunce()));
                 		} else {
                 			mOunceField.setText(weightDecimalFormatter.format(volume.getOunce()));
                 		}
                 		
                 		if(Math.abs(volume.getCup()) < 0.000001 || Math.abs(volume.getCup()) > 999999999.0) {
                 			mCupField.setText(weightScientificFormatter.format(volume.getCup()));
                 		} else {
                 			mCupField.setText(weightDecimalFormatter.format(volume.getCup()));
                 		}
                 		
                 		if(Math.abs(volume.getPint()) < 0.000001 || Math.abs(volume.getPint()) > 999999999.0) {
                 			mPintField.setText(weightScientificFormatter.format(volume.getPint()));
                 		} else {
                 			mPintField.setText(weightDecimalFormatter.format(volume.getPint()));
                 		}
                 		
                 		if(Math.abs(volume.getLiter()) < 0.000001 || Math.abs(volume.getLiter()) > 999999999.0) {
                 			mLiterField.setText(weightScientificFormatter.format(volume.getLiter()));
                 		} else {
                 			mLiterField.setText(weightDecimalFormatter.format(volume.getLiter()));
                 		}
                 		
                 		if(Math.abs(volume.getGallon()) < 0.000001 || Math.abs(volume.getGallon()) > 999999999.0) {
                 			mGallonField.setText(weightScientificFormatter.format(volume.getGallon()));
                 		} else {
                 			mGallonField.setText(weightDecimalFormatter.format(volume.getGallon()));
                 		}
                 		
                 		if(Math.abs(volume.getKiloliter()) < 0.000001 || Math.abs(volume.getKiloliter()) > 999999999.0) {
                 			mKiloliterField.setText(weightScientificFormatter.format(volume.getKiloliter()));
                 		} else {
                 			mKiloliterField.setText(weightDecimalFormatter.format(volume.getKiloliter()));
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
        }); // end addTextChangedListener on mCentiliterField
        
        // Set addTextChangedListener on mTablespoonField
        mTablespoonField.addTextChangedListener(new TextWatcher() {
        	// update the other text fields with the converted values
        	public void onTextChanged(CharSequence s, int start, int before, int count) {
        		if(!suppressFieldChanges) {
            		if(!isNumeric(mTablespoonField.getText().toString())) {
                 		// mTablespoonField's text can't be parsed to a double,
                 		// set the other text fields to empty strings
            			suppressFieldChanges = true;
            				
            			mMilliliterField.setText("");
            			mTeaspoonField.setText("");
            			mCentiliterField.setText("");
            			mOunceField.setText("");
            			mCupField.setText("");
            			mPintField.setText("");
            			mLiterField.setText("");
            			mGallonField.setText("");
            			mKiloliterField.setText("");
                     	
                 		suppressFieldChanges = false;
                 	} else if (Double.parseDouble(mTablespoonField.getText().toString()) == 0) {
                 		// conversions don't need to be performed on a tablespoon value of 0
                 		// set the other text fields to empty strings
                 		suppressFieldChanges = true;
        				
                 		mMilliliterField.setText("");
                 		mTeaspoonField.setText("");
                 		mCentiliterField.setText("");
            			mOunceField.setText("");
            			mCupField.setText("");
            			mPintField.setText("");
            			mLiterField.setText("");
            			mGallonField.setText("");
            			mKiloliterField.setText("");
                     	
                 		suppressFieldChanges = false;
                 	} else {
                 		volume.calculateFromTablespoon(Double.parseDouble(mTablespoonField.getText().toString()));

                 		// update text fields with converted values
                 		suppressFieldChanges = true;
                 		
                 		// these if statements determine if the values should be displayed
                 		// with scientific notation or decimal notation
                 		if(Math.abs(volume.getMilliliter()) < 0.000001 || Math.abs(volume.getMilliliter()) > 999999999.0) {
                 			mMilliliterField.setText(weightScientificFormatter.format(volume.getMilliliter()));
                 		} else {
                 			mMilliliterField.setText(weightDecimalFormatter.format(volume.getMilliliter()));
                 		}
             			
                 		if(Math.abs(volume.getTeaspoon()) < 0.000001 || Math.abs(volume.getTeaspoon()) > 999999999.0) {
                 			mTeaspoonField.setText(weightScientificFormatter.format(volume.getTeaspoon()));
                 		} else {
                 			mTeaspoonField.setText(weightDecimalFormatter.format(volume.getTeaspoon()));
                 		}
                 		
                 		if(Math.abs(volume.getCentiliter()) < 0.000001 || Math.abs(volume.getCentiliter()) > 999999999.0) {
                 			mCentiliterField.setText(weightScientificFormatter.format(volume.getCentiliter()));
                 		} else {
                 			mCentiliterField.setText(weightDecimalFormatter.format(volume.getCentiliter()));
                 		}
                 		
                 		if(Math.abs(volume.getOunce()) < 0.000001 || Math.abs(volume.getOunce()) > 999999999.0) {
                 			mOunceField.setText(weightScientificFormatter.format(volume.getOunce()));
                 		} else {
                 			mOunceField.setText(weightDecimalFormatter.format(volume.getOunce()));
                 		}
                 		
                 		if(Math.abs(volume.getCup()) < 0.000001 || Math.abs(volume.getCup()) > 999999999.0) {
                 			mCupField.setText(weightScientificFormatter.format(volume.getCup()));
                 		} else {
                 			mCupField.setText(weightDecimalFormatter.format(volume.getCup()));
                 		}
                 		
                 		if(Math.abs(volume.getPint()) < 0.000001 || Math.abs(volume.getPint()) > 999999999.0) {
                 			mPintField.setText(weightScientificFormatter.format(volume.getPint()));
                 		} else {
                 			mPintField.setText(weightDecimalFormatter.format(volume.getPint()));
                 		}
                 		
                 		if(Math.abs(volume.getLiter()) < 0.000001 || Math.abs(volume.getLiter()) > 999999999.0) {
                 			mLiterField.setText(weightScientificFormatter.format(volume.getLiter()));
                 		} else {
                 			mLiterField.setText(weightDecimalFormatter.format(volume.getLiter()));
                 		}
                 		
                 		if(Math.abs(volume.getGallon()) < 0.000001 || Math.abs(volume.getGallon()) > 999999999.0) {
                 			mGallonField.setText(weightScientificFormatter.format(volume.getGallon()));
                 		} else {
                 			mGallonField.setText(weightDecimalFormatter.format(volume.getGallon()));
                 		}
                 		
                 		if(Math.abs(volume.getKiloliter()) < 0.000001 || Math.abs(volume.getKiloliter()) > 999999999.0) {
                 			mKiloliterField.setText(weightScientificFormatter.format(volume.getKiloliter()));
                 		} else {
                 			mKiloliterField.setText(weightDecimalFormatter.format(volume.getKiloliter()));
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
        }); // end addTextChangedListener on mTablespoonField
        
        // Set addTextChangedListener on mOunceField
        mOunceField.addTextChangedListener(new TextWatcher() {
        	// update the other text fields with the converted values
        	public void onTextChanged(CharSequence s, int start, int before, int count) {
        		if(!suppressFieldChanges) {
            		if(!isNumeric(mOunceField.getText().toString())) {
                 		// mOunceField's text can't be parsed to a double,
                 		// set the other text fields to empty strings
            			suppressFieldChanges = true;
            				
            			mMilliliterField.setText("");
            			mTeaspoonField.setText("");
            			mCentiliterField.setText("");
            			mTablespoonField.setText("");
            			mCupField.setText("");
            			mPintField.setText("");
            			mLiterField.setText("");
            			mGallonField.setText("");
            			mKiloliterField.setText("");
                     	
                 		suppressFieldChanges = false;
                 	} else if (Double.parseDouble(mOunceField.getText().toString()) == 0) {
                 		// conversions don't need to be performed on a ounce value of 0
                 		// set the other text fields to empty strings
                 		suppressFieldChanges = true;
        				
                 		mMilliliterField.setText("");
                 		mTeaspoonField.setText("");
                 		mCentiliterField.setText("");
                 		mTablespoonField.setText("");
            			mCupField.setText("");
            			mPintField.setText("");
            			mLiterField.setText("");
            			mGallonField.setText("");
            			mKiloliterField.setText("");
                     	
                 		suppressFieldChanges = false;
                 	} else {
                 		volume.calculateFromOunce(Double.parseDouble(mOunceField.getText().toString()));

                 		// update text fields with converted values
                 		suppressFieldChanges = true;
                 		
                 		// these if statements determine if the values should be displayed
                 		// with scientific notation or decimal notation
                 		if(Math.abs(volume.getMilliliter()) < 0.000001 || Math.abs(volume.getMilliliter()) > 999999999.0) {
                 			mMilliliterField.setText(weightScientificFormatter.format(volume.getMilliliter()));
                 		} else {
                 			mMilliliterField.setText(weightDecimalFormatter.format(volume.getMilliliter()));
                 		}
             			
                 		if(Math.abs(volume.getTeaspoon()) < 0.000001 || Math.abs(volume.getTeaspoon()) > 999999999.0) {
                 			mTeaspoonField.setText(weightScientificFormatter.format(volume.getTeaspoon()));
                 		} else {
                 			mTeaspoonField.setText(weightDecimalFormatter.format(volume.getTeaspoon()));
                 		}
                 		
                 		if(Math.abs(volume.getCentiliter()) < 0.000001 || Math.abs(volume.getCentiliter()) > 999999999.0) {
                 			mCentiliterField.setText(weightScientificFormatter.format(volume.getCentiliter()));
                 		} else {
                 			mCentiliterField.setText(weightDecimalFormatter.format(volume.getCentiliter()));
                 		}
                 		
                 		if(Math.abs(volume.getTablespoon()) < 0.000001 || Math.abs(volume.getTablespoon()) > 999999999.0) {
                 			mTablespoonField.setText(weightScientificFormatter.format(volume.getTablespoon()));
                 		} else {
                 			mTablespoonField.setText(weightDecimalFormatter.format(volume.getTablespoon()));
                 		}
                 		
                 		if(Math.abs(volume.getCup()) < 0.000001 || Math.abs(volume.getCup()) > 999999999.0) {
                 			mCupField.setText(weightScientificFormatter.format(volume.getCup()));
                 		} else {
                 			mCupField.setText(weightDecimalFormatter.format(volume.getCup()));
                 		}
                 		
                 		if(Math.abs(volume.getPint()) < 0.000001 || Math.abs(volume.getPint()) > 999999999.0) {
                 			mPintField.setText(weightScientificFormatter.format(volume.getPint()));
                 		} else {
                 			mPintField.setText(weightDecimalFormatter.format(volume.getPint()));
                 		}
                 		
                 		if(Math.abs(volume.getLiter()) < 0.000001 || Math.abs(volume.getLiter()) > 999999999.0) {
                 			mLiterField.setText(weightScientificFormatter.format(volume.getLiter()));
                 		} else {
                 			mLiterField.setText(weightDecimalFormatter.format(volume.getLiter()));
                 		}
                 		
                 		if(Math.abs(volume.getGallon()) < 0.000001 || Math.abs(volume.getGallon()) > 999999999.0) {
                 			mGallonField.setText(weightScientificFormatter.format(volume.getGallon()));
                 		} else {
                 			mGallonField.setText(weightDecimalFormatter.format(volume.getGallon()));
                 		}
                 		
                 		if(Math.abs(volume.getKiloliter()) < 0.000001 || Math.abs(volume.getKiloliter()) > 999999999.0) {
                 			mKiloliterField.setText(weightScientificFormatter.format(volume.getKiloliter()));
                 		} else {
                 			mKiloliterField.setText(weightDecimalFormatter.format(volume.getKiloliter()));
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
        
        // Set addTextChangedListener on mCupField
        mCupField.addTextChangedListener(new TextWatcher() {
        	// update the other text fields with the converted values
        	public void onTextChanged(CharSequence s, int start, int before, int count) {
        		if(!suppressFieldChanges) {
            		if(!isNumeric(mCupField.getText().toString())) {
                 		// mCupField's text can't be parsed to a double,
                 		// set the other text fields to empty strings
            			suppressFieldChanges = true;
            				
            			mMilliliterField.setText("");
            			mTeaspoonField.setText("");
            			mCentiliterField.setText("");
            			mTablespoonField.setText("");
            			mOunceField.setText("");
            			mPintField.setText("");
            			mLiterField.setText("");
            			mGallonField.setText("");
            			mKiloliterField.setText("");
                     	
                 		suppressFieldChanges = false;
                 	} else if (Double.parseDouble(mCupField.getText().toString()) == 0) {
                 		// conversions don't need to be performed on a cup value of 0
                 		// set the other text fields to empty strings
                 		suppressFieldChanges = true;
        				
                 		mMilliliterField.setText("");
                 		mTeaspoonField.setText("");
                 		mCentiliterField.setText("");
                 		mTablespoonField.setText("");
                 		mOunceField.setText("");
            			mPintField.setText("");
            			mLiterField.setText("");
            			mGallonField.setText("");
            			mKiloliterField.setText("");
                     	
                 		suppressFieldChanges = false;
                 	} else {
                 		volume.calculateFromCup(Double.parseDouble(mCupField.getText().toString()));

                 		// update text fields with converted values
                 		suppressFieldChanges = true;
                 		
                 		// these if statements determine if the values should be displayed
                 		// with scientific notation or decimal notation
                 		if(Math.abs(volume.getMilliliter()) < 0.000001 || Math.abs(volume.getMilliliter()) > 999999999.0) {
                 			mMilliliterField.setText(weightScientificFormatter.format(volume.getMilliliter()));
                 		} else {
                 			mMilliliterField.setText(weightDecimalFormatter.format(volume.getMilliliter()));
                 		}
             			
                 		if(Math.abs(volume.getTeaspoon()) < 0.000001 || Math.abs(volume.getTeaspoon()) > 999999999.0) {
                 			mTeaspoonField.setText(weightScientificFormatter.format(volume.getTeaspoon()));
                 		} else {
                 			mTeaspoonField.setText(weightDecimalFormatter.format(volume.getTeaspoon()));
                 		}
                 		
                 		if(Math.abs(volume.getCentiliter()) < 0.000001 || Math.abs(volume.getCentiliter()) > 999999999.0) {
                 			mCentiliterField.setText(weightScientificFormatter.format(volume.getCentiliter()));
                 		} else {
                 			mCentiliterField.setText(weightDecimalFormatter.format(volume.getCentiliter()));
                 		}
                 		
                 		if(Math.abs(volume.getTablespoon()) < 0.000001 || Math.abs(volume.getTablespoon()) > 999999999.0) {
                 			mTablespoonField.setText(weightScientificFormatter.format(volume.getTablespoon()));
                 		} else {
                 			mTablespoonField.setText(weightDecimalFormatter.format(volume.getTablespoon()));
                 		}
                 		
                 		if(Math.abs(volume.getOunce()) < 0.000001 || Math.abs(volume.getOunce()) > 999999999.0) {
                 			mOunceField.setText(weightScientificFormatter.format(volume.getOunce()));
                 		} else {
                 			mOunceField.setText(weightDecimalFormatter.format(volume.getOunce()));
                 		}
                 		
                 		if(Math.abs(volume.getPint()) < 0.000001 || Math.abs(volume.getPint()) > 999999999.0) {
                 			mPintField.setText(weightScientificFormatter.format(volume.getPint()));
                 		} else {
                 			mPintField.setText(weightDecimalFormatter.format(volume.getPint()));
                 		}
                 		
                 		if(Math.abs(volume.getLiter()) < 0.000001 || Math.abs(volume.getLiter()) > 999999999.0) {
                 			mLiterField.setText(weightScientificFormatter.format(volume.getLiter()));
                 		} else {
                 			mLiterField.setText(weightDecimalFormatter.format(volume.getLiter()));
                 		}
                 		
                 		if(Math.abs(volume.getGallon()) < 0.000001 || Math.abs(volume.getGallon()) > 999999999.0) {
                 			mGallonField.setText(weightScientificFormatter.format(volume.getGallon()));
                 		} else {
                 			mGallonField.setText(weightDecimalFormatter.format(volume.getGallon()));
                 		}
                 		
                 		if(Math.abs(volume.getKiloliter()) < 0.000001 || Math.abs(volume.getKiloliter()) > 999999999.0) {
                 			mKiloliterField.setText(weightScientificFormatter.format(volume.getKiloliter()));
                 		} else {
                 			mKiloliterField.setText(weightDecimalFormatter.format(volume.getKiloliter()));
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
        }); // end addTextChangedListener on mCupField
        
        // Set addTextChangedListener on mPintField
        mPintField.addTextChangedListener(new TextWatcher() {
        	// update the other text fields with the converted values
        	public void onTextChanged(CharSequence s, int start, int before, int count) {
        		if(!suppressFieldChanges) {
            		if(!isNumeric(mPintField.getText().toString())) {
                 		// mPintField's text can't be parsed to a double,
                 		// set the other text fields to empty strings
            			suppressFieldChanges = true;
            				
            			mMilliliterField.setText("");
            			mTeaspoonField.setText("");
            			mCentiliterField.setText("");
            			mTablespoonField.setText("");
            			mOunceField.setText("");
            			mCupField.setText("");
            			mLiterField.setText("");
            			mGallonField.setText("");
            			mKiloliterField.setText("");
                     	
                 		suppressFieldChanges = false;
                 	} else if (Double.parseDouble(mPintField.getText().toString()) == 0) {
                 		// conversions don't need to be performed on a pint value of 0
                 		// set the other text fields to empty strings
                 		suppressFieldChanges = true;
        				
                 		mMilliliterField.setText("");
                 		mTeaspoonField.setText("");
                 		mCentiliterField.setText("");
                 		mTablespoonField.setText("");
                 		mOunceField.setText("");
                 		mCupField.setText("");
            			mLiterField.setText("");
            			mGallonField.setText("");
            			mKiloliterField.setText("");
                     	
                 		suppressFieldChanges = false;
                 	} else {
                 		volume.calculateFromPint(Double.parseDouble(mPintField.getText().toString()));

                 		// update text fields with converted values
                 		suppressFieldChanges = true;
                 		
                 		// these if statements determine if the values should be displayed
                 		// with scientific notation or decimal notation
                 		if(Math.abs(volume.getMilliliter()) < 0.000001 || Math.abs(volume.getMilliliter()) > 999999999.0) {
                 			mMilliliterField.setText(weightScientificFormatter.format(volume.getMilliliter()));
                 		} else {
                 			mMilliliterField.setText(weightDecimalFormatter.format(volume.getMilliliter()));
                 		}
             			
                 		if(Math.abs(volume.getTeaspoon()) < 0.000001 || Math.abs(volume.getTeaspoon()) > 999999999.0) {
                 			mTeaspoonField.setText(weightScientificFormatter.format(volume.getTeaspoon()));
                 		} else {
                 			mTeaspoonField.setText(weightDecimalFormatter.format(volume.getTeaspoon()));
                 		}
                 		
                 		if(Math.abs(volume.getCentiliter()) < 0.000001 || Math.abs(volume.getCentiliter()) > 999999999.0) {
                 			mCentiliterField.setText(weightScientificFormatter.format(volume.getCentiliter()));
                 		} else {
                 			mCentiliterField.setText(weightDecimalFormatter.format(volume.getCentiliter()));
                 		}
                 		
                 		if(Math.abs(volume.getTablespoon()) < 0.000001 || Math.abs(volume.getTablespoon()) > 999999999.0) {
                 			mTablespoonField.setText(weightScientificFormatter.format(volume.getTablespoon()));
                 		} else {
                 			mTablespoonField.setText(weightDecimalFormatter.format(volume.getTablespoon()));
                 		}
                 		
                 		if(Math.abs(volume.getOunce()) < 0.000001 || Math.abs(volume.getOunce()) > 999999999.0) {
                 			mOunceField.setText(weightScientificFormatter.format(volume.getOunce()));
                 		} else {
                 			mOunceField.setText(weightDecimalFormatter.format(volume.getOunce()));
                 		}
                 		
                 		if(Math.abs(volume.getCup()) < 0.000001 || Math.abs(volume.getCup()) > 999999999.0) {
                 			mCupField.setText(weightScientificFormatter.format(volume.getCup()));
                 		} else {
                 			mCupField.setText(weightDecimalFormatter.format(volume.getCup()));
                 		}
                 		
                 		if(Math.abs(volume.getLiter()) < 0.000001 || Math.abs(volume.getLiter()) > 999999999.0) {
                 			mLiterField.setText(weightScientificFormatter.format(volume.getLiter()));
                 		} else {
                 			mLiterField.setText(weightDecimalFormatter.format(volume.getLiter()));
                 		}
                 		
                 		if(Math.abs(volume.getGallon()) < 0.000001 || Math.abs(volume.getGallon()) > 999999999.0) {
                 			mGallonField.setText(weightScientificFormatter.format(volume.getGallon()));
                 		} else {
                 			mGallonField.setText(weightDecimalFormatter.format(volume.getGallon()));
                 		}
                 		
                 		if(Math.abs(volume.getKiloliter()) < 0.000001 || Math.abs(volume.getKiloliter()) > 999999999.0) {
                 			mKiloliterField.setText(weightScientificFormatter.format(volume.getKiloliter()));
                 		} else {
                 			mKiloliterField.setText(weightDecimalFormatter.format(volume.getKiloliter()));
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
        }); // end addTextChangedListener on mPintField
        
        // Set addTextChangedListener on mLiterField
        mLiterField.addTextChangedListener(new TextWatcher() {
        	// update the other text fields with the converted values
        	public void onTextChanged(CharSequence s, int start, int before, int count) {
        		if(!suppressFieldChanges) {
            		if(!isNumeric(mLiterField.getText().toString())) {
                 		// mLiterField's text can't be parsed to a double,
                 		// set the other text fields to empty strings
            			suppressFieldChanges = true;
            				
            			mMilliliterField.setText("");
            			mTeaspoonField.setText("");
            			mCentiliterField.setText("");
            			mTablespoonField.setText("");
            			mOunceField.setText("");
            			mCupField.setText("");
            			mPintField.setText("");
            			mGallonField.setText("");
            			mKiloliterField.setText("");
                     	
                 		suppressFieldChanges = false;
                 	} else if (Double.parseDouble(mLiterField.getText().toString()) == 0) {
                 		// conversions don't need to be performed on a liter value of 0
                 		// set the other text fields to empty strings
                 		suppressFieldChanges = true;
        				
                 		mMilliliterField.setText("");
                 		mTeaspoonField.setText("");
                 		mCentiliterField.setText("");
                 		mTablespoonField.setText("");
                 		mOunceField.setText("");
                 		mCupField.setText("");
                 		mPintField.setText("");
            			mGallonField.setText("");
            			mKiloliterField.setText("");
                     	
                 		suppressFieldChanges = false;
                 	} else {
                 		volume.calculateFromLiter(Double.parseDouble(mLiterField.getText().toString()));

                 		// update text fields with converted values
                 		suppressFieldChanges = true;
                 		
                 		// these if statements determine if the values should be displayed
                 		// with scientific notation or decimal notation
                 		if(Math.abs(volume.getMilliliter()) < 0.000001 || Math.abs(volume.getMilliliter()) > 999999999.0) {
                 			mMilliliterField.setText(weightScientificFormatter.format(volume.getMilliliter()));
                 		} else {
                 			mMilliliterField.setText(weightDecimalFormatter.format(volume.getMilliliter()));
                 		}
             			
                 		if(Math.abs(volume.getTeaspoon()) < 0.000001 || Math.abs(volume.getTeaspoon()) > 999999999.0) {
                 			mTeaspoonField.setText(weightScientificFormatter.format(volume.getTeaspoon()));
                 		} else {
                 			mTeaspoonField.setText(weightDecimalFormatter.format(volume.getTeaspoon()));
                 		}
                 		
                 		if(Math.abs(volume.getCentiliter()) < 0.000001 || Math.abs(volume.getCentiliter()) > 999999999.0) {
                 			mCentiliterField.setText(weightScientificFormatter.format(volume.getCentiliter()));
                 		} else {
                 			mCentiliterField.setText(weightDecimalFormatter.format(volume.getCentiliter()));
                 		}
                 		
                 		if(Math.abs(volume.getTablespoon()) < 0.000001 || Math.abs(volume.getTablespoon()) > 999999999.0) {
                 			mTablespoonField.setText(weightScientificFormatter.format(volume.getTablespoon()));
                 		} else {
                 			mTablespoonField.setText(weightDecimalFormatter.format(volume.getTablespoon()));
                 		}
                 		
                 		if(Math.abs(volume.getOunce()) < 0.000001 || Math.abs(volume.getOunce()) > 999999999.0) {
                 			mOunceField.setText(weightScientificFormatter.format(volume.getOunce()));
                 		} else {
                 			mOunceField.setText(weightDecimalFormatter.format(volume.getOunce()));
                 		}
                 		
                 		if(Math.abs(volume.getCup()) < 0.000001 || Math.abs(volume.getCup()) > 999999999.0) {
                 			mCupField.setText(weightScientificFormatter.format(volume.getCup()));
                 		} else {
                 			mCupField.setText(weightDecimalFormatter.format(volume.getCup()));
                 		}
                 		
                 		if(Math.abs(volume.getPint()) < 0.000001 || Math.abs(volume.getPint()) > 999999999.0) {
                 			mPintField.setText(weightScientificFormatter.format(volume.getPint()));
                 		} else {
                 			mPintField.setText(weightDecimalFormatter.format(volume.getPint()));
                 		}
                 		
                 		if(Math.abs(volume.getGallon()) < 0.000001 || Math.abs(volume.getGallon()) > 999999999.0) {
                 			mGallonField.setText(weightScientificFormatter.format(volume.getGallon()));
                 		} else {
                 			mGallonField.setText(weightDecimalFormatter.format(volume.getGallon()));
                 		}
                 		
                 		if(Math.abs(volume.getKiloliter()) < 0.000001 || Math.abs(volume.getKiloliter()) > 999999999.0) {
                 			mKiloliterField.setText(weightScientificFormatter.format(volume.getKiloliter()));
                 		} else {
                 			mKiloliterField.setText(weightDecimalFormatter.format(volume.getKiloliter()));
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
        }); // end addTextChangedListener on mLiterField
        
        // Set addTextChangedListener on mGallonField
        mGallonField.addTextChangedListener(new TextWatcher() {
        	// update the other text fields with the converted values
        	public void onTextChanged(CharSequence s, int start, int before, int count) {
        		if(!suppressFieldChanges) {
            		if(!isNumeric(mGallonField.getText().toString())) {
                 		// mGallonField's text can't be parsed to a double,
                 		// set the other text fields to empty strings
            			suppressFieldChanges = true;
            				
            			mMilliliterField.setText("");
            			mTeaspoonField.setText("");
            			mCentiliterField.setText("");
            			mTablespoonField.setText("");
            			mOunceField.setText("");
            			mCupField.setText("");
            			mPintField.setText("");
            			mLiterField.setText("");
            			mKiloliterField.setText("");
                     	
                 		suppressFieldChanges = false;
                 	} else if (Double.parseDouble(mGallonField.getText().toString()) == 0) {
                 		// conversions don't need to be performed on a gallon value of 0
                 		// set the other text fields to empty strings
                 		suppressFieldChanges = true;
        				
                 		mMilliliterField.setText("");
                 		mTeaspoonField.setText("");
                 		mCentiliterField.setText("");
                 		mTablespoonField.setText("");
                 		mOunceField.setText("");
                 		mCupField.setText("");
                 		mPintField.setText("");
                 		mLiterField.setText("");
            			mKiloliterField.setText("");
                     	
                 		suppressFieldChanges = false;
                 	} else {
                 		volume.calculateFromGallon(Double.parseDouble(mGallonField.getText().toString()));

                 		// update text fields with converted values
                 		suppressFieldChanges = true;
                 		
                 		// these if statements determine if the values should be displayed
                 		// with scientific notation or decimal notation
                 		if(Math.abs(volume.getMilliliter()) < 0.000001 || Math.abs(volume.getMilliliter()) > 999999999.0) {
                 			mMilliliterField.setText(weightScientificFormatter.format(volume.getMilliliter()));
                 		} else {
                 			mMilliliterField.setText(weightDecimalFormatter.format(volume.getMilliliter()));
                 		}
             			
                 		if(Math.abs(volume.getTeaspoon()) < 0.000001 || Math.abs(volume.getTeaspoon()) > 999999999.0) {
                 			mTeaspoonField.setText(weightScientificFormatter.format(volume.getTeaspoon()));
                 		} else {
                 			mTeaspoonField.setText(weightDecimalFormatter.format(volume.getTeaspoon()));
                 		}
                 		
                 		if(Math.abs(volume.getCentiliter()) < 0.000001 || Math.abs(volume.getCentiliter()) > 999999999.0) {
                 			mCentiliterField.setText(weightScientificFormatter.format(volume.getCentiliter()));
                 		} else {
                 			mCentiliterField.setText(weightDecimalFormatter.format(volume.getCentiliter()));
                 		}
                 		
                 		if(Math.abs(volume.getTablespoon()) < 0.000001 || Math.abs(volume.getTablespoon()) > 999999999.0) {
                 			mTablespoonField.setText(weightScientificFormatter.format(volume.getTablespoon()));
                 		} else {
                 			mTablespoonField.setText(weightDecimalFormatter.format(volume.getTablespoon()));
                 		}
                 		
                 		if(Math.abs(volume.getOunce()) < 0.000001 || Math.abs(volume.getOunce()) > 999999999.0) {
                 			mOunceField.setText(weightScientificFormatter.format(volume.getOunce()));
                 		} else {
                 			mOunceField.setText(weightDecimalFormatter.format(volume.getOunce()));
                 		}
                 		
                 		if(Math.abs(volume.getCup()) < 0.000001 || Math.abs(volume.getCup()) > 999999999.0) {
                 			mCupField.setText(weightScientificFormatter.format(volume.getCup()));
                 		} else {
                 			mCupField.setText(weightDecimalFormatter.format(volume.getCup()));
                 		}
                 		
                 		if(Math.abs(volume.getPint()) < 0.000001 || Math.abs(volume.getPint()) > 999999999.0) {
                 			mPintField.setText(weightScientificFormatter.format(volume.getPint()));
                 		} else {
                 			mPintField.setText(weightDecimalFormatter.format(volume.getPint()));
                 		}
                 		
                 		if(Math.abs(volume.getLiter()) < 0.000001 || Math.abs(volume.getLiter()) > 999999999.0) {
                 			mLiterField.setText(weightScientificFormatter.format(volume.getLiter()));
                 		} else {
                 			mLiterField.setText(weightDecimalFormatter.format(volume.getLiter()));
                 		}
                 		
                 		if(Math.abs(volume.getKiloliter()) < 0.000001 || Math.abs(volume.getKiloliter()) > 999999999.0) {
                 			mKiloliterField.setText(weightScientificFormatter.format(volume.getKiloliter()));
                 		} else {
                 			mKiloliterField.setText(weightDecimalFormatter.format(volume.getKiloliter()));
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
        }); // end addTextChangedListener on mGallonField
        
        // Set addTextChangedListener on mKiloliterField
        mKiloliterField.addTextChangedListener(new TextWatcher() {
        	// update the other text fields with the converted values
        	public void onTextChanged(CharSequence s, int start, int before, int count) {
        		if(!suppressFieldChanges) {
            		if(!isNumeric(mKiloliterField.getText().toString())) {
                 		// mKiloliterField's text can't be parsed to a double,
                 		// set the other text fields to empty strings
            			suppressFieldChanges = true;
            				
            			mMilliliterField.setText("");
            			mTeaspoonField.setText("");
            			mCentiliterField.setText("");
            			mTablespoonField.setText("");
            			mOunceField.setText("");
            			mCupField.setText("");
            			mPintField.setText("");
            			mLiterField.setText("");
            			mGallonField.setText("");
                     	
                 		suppressFieldChanges = false;
                 	} else if (Double.parseDouble(mKiloliterField.getText().toString()) == 0) {
                 		// conversions don't need to be performed on a kiloliter value of 0
                 		// set the other text fields to empty strings
                 		suppressFieldChanges = true;
        				
                 		mMilliliterField.setText("");
                 		mTeaspoonField.setText("");
                 		mCentiliterField.setText("");
                 		mTablespoonField.setText("");
                 		mOunceField.setText("");
                 		mCupField.setText("");
                 		mPintField.setText("");
                 		mLiterField.setText("");
                 		mGallonField.setText("");
                     	
                 		suppressFieldChanges = false;
                 	} else {
                 		volume.calculateFromKiloliter(Double.parseDouble(mKiloliterField.getText().toString()));

                 		// update text fields with converted values
                 		suppressFieldChanges = true;
                 		
                 		// these if statements determine if the values should be displayed
                 		// with scientific notation or decimal notation
                 		if(Math.abs(volume.getMilliliter()) < 0.000001 || Math.abs(volume.getMilliliter()) > 999999999.0) {
                 			mMilliliterField.setText(weightScientificFormatter.format(volume.getMilliliter()));
                 		} else {
                 			mMilliliterField.setText(weightDecimalFormatter.format(volume.getMilliliter()));
                 		}
             			
                 		if(Math.abs(volume.getTeaspoon()) < 0.000001 || Math.abs(volume.getTeaspoon()) > 999999999.0) {
                 			mTeaspoonField.setText(weightScientificFormatter.format(volume.getTeaspoon()));
                 		} else {
                 			mTeaspoonField.setText(weightDecimalFormatter.format(volume.getTeaspoon()));
                 		}
                 		
                 		if(Math.abs(volume.getCentiliter()) < 0.000001 || Math.abs(volume.getCentiliter()) > 999999999.0) {
                 			mCentiliterField.setText(weightScientificFormatter.format(volume.getCentiliter()));
                 		} else {
                 			mCentiliterField.setText(weightDecimalFormatter.format(volume.getCentiliter()));
                 		}
                 		
                 		if(Math.abs(volume.getTablespoon()) < 0.000001 || Math.abs(volume.getTablespoon()) > 999999999.0) {
                 			mTablespoonField.setText(weightScientificFormatter.format(volume.getTablespoon()));
                 		} else {
                 			mTablespoonField.setText(weightDecimalFormatter.format(volume.getTablespoon()));
                 		}
                 		
                 		if(Math.abs(volume.getOunce()) < 0.000001 || Math.abs(volume.getOunce()) > 999999999.0) {
                 			mOunceField.setText(weightScientificFormatter.format(volume.getOunce()));
                 		} else {
                 			mOunceField.setText(weightDecimalFormatter.format(volume.getOunce()));
                 		}
                 		
                 		if(Math.abs(volume.getCup()) < 0.000001 || Math.abs(volume.getCup()) > 999999999.0) {
                 			mCupField.setText(weightScientificFormatter.format(volume.getCup()));
                 		} else {
                 			mCupField.setText(weightDecimalFormatter.format(volume.getCup()));
                 		}
                 		
                 		if(Math.abs(volume.getPint()) < 0.000001 || Math.abs(volume.getPint()) > 999999999.0) {
                 			mPintField.setText(weightScientificFormatter.format(volume.getPint()));
                 		} else {
                 			mPintField.setText(weightDecimalFormatter.format(volume.getPint()));
                 		}
                 		
                 		if(Math.abs(volume.getLiter()) < 0.000001 || Math.abs(volume.getLiter()) > 999999999.0) {
                 			mLiterField.setText(weightScientificFormatter.format(volume.getLiter()));
                 		} else {
                 			mLiterField.setText(weightDecimalFormatter.format(volume.getLiter()));
                 		}
                 		
                 		if(Math.abs(volume.getGallon()) < 0.000001 || Math.abs(volume.getGallon()) > 999999999.0) {
                 			mGallonField.setText(weightScientificFormatter.format(volume.getGallon()));
                 		} else {
                 			mGallonField.setText(weightDecimalFormatter.format(volume.getGallon()));
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
        }); // end addTextChangedListener on mKiloliterField
        
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
    	mMilliliterField.setText("");
    	mTeaspoonField.setText("");
    	mCentiliterField.setText("");
    	mTablespoonField.setText("");
    	mOunceField.setText("");
    	mCupField.setText("");
    	mPintField.setText("");
    	mLiterField.setText("");
    	mGallonField.setText("");
    	mKiloliterField.setText("");
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
} // end class VolumeFragment