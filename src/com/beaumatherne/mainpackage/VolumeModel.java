package com.beaumatherne.mainpackage;

public class VolumeModel {
	private double mMilliliter;
	private double mTeaspoon;
	private double mCentiliter;
	private double mTablespoon;
	private double mOunce;
	private double mCup;
	private double mPint;
	private double mLiter;
	private double mGallon;
	private double mKiloliter;
	
	public VolumeModel() {
		// set initial values for member fields
		setMilliliter(0);
		setTeaspoon(0);
		setCentiliter(0);
		setTablespoon(0);
		setOunce(0);
		setCup(0);
		setPint(0);
		setLiter(0);
		setGallon(0);
		setKiloliter(0);
	} // end constructor VolumeModel()
	
	public double getMilliliter() {
		return mMilliliter;
	} // end method getMilliliter
	
	public void setMilliliter(double milliliter) {
		mMilliliter = milliliter;
	} // end method setMilliliter
	
	public double getTeaspoon() {
		return mTeaspoon;
	} // end method getTeaspoon
	
	public void setTeaspoon(double teaspoon) {
		mTeaspoon = teaspoon;
	} // end method setTeaspoon
	
	public double getCentiliter() {
		return mCentiliter;
	} // end method getCentiliter
	
	public void setCentiliter(double centiliter) {
		mCentiliter = centiliter;
	} // end method setCentiliter
	
	public double getTablespoon() {
		return mTablespoon;
	} // end method getTablespoon
	
	public void setTablespoon(double tablespoon) {
		mTablespoon = tablespoon;
	} // end method setTablespoon
	
	public double getOunce() {
		return mOunce;
	} // end method getOunce
	
	public void setOunce(double ounce) {
		mOunce = ounce;
	} // end method setOunce
	
	public double getCup() {
		return mCup;
	} // end method getCup
	
	public void setCup(double cup) {
		mCup = cup;
	} // end method setCup
	
	public double getPint() {
		return mPint;
	} // end method getPint
	
	public void setPint(double pint) {
		mPint = pint;
	} // end method setPint
	
	public double getLiter() {
		return mLiter;
	} // end method getLiter
	
	public void setLiter(double liter) {
		mLiter = liter;
	} // end method setLiter
	
	public double getGallon() {
		return mGallon;
	} // end method getGallon
	
	public void setGallon(double gallon) {
		mGallon = gallon;
	} // end method setGallon
	
	public double getKiloliter() {
		return mKiloliter;
	} // end method getKiloliter
	
	public void setKiloliter(double kiloliter) {
		mKiloliter = kiloliter;
	} // end method setKiloliter
	
	public void calculateFromMilliliter(double milliliter) {
		// convert milliliter to other volume values,
		// and the store the values in the member variables
		setMilliliter(milliliter);
		setTeaspoon(milliliter / 4.9289);
		setCentiliter(milliliter / 10.000);
		setTablespoon(milliliter / 14.787);
		setOunce(milliliter / 29.574);
		setCup(milliliter / 236.59);
		setPint(milliliter / 473.18);
		setLiter(milliliter / 1000.0);
		setGallon(milliliter / 3785.4);
		setKiloliter(milliliter / 1.0000e+6);
	} // end method calculateFromMilliliter
	
	public void calculateFromTeaspoon(double teaspoon) {
		// convert teaspoon to other volume values,
		// and the store the values in the member variables
		setMilliliter(teaspoon * 4.9289);
		setTeaspoon(teaspoon);
		setCentiliter(teaspoon / 2.0288);
		setTablespoon(teaspoon / 3.0000);
		setOunce(teaspoon / 6.0000);
		setCup(teaspoon / 48.000);
		setPint(teaspoon / 96.000);
		setLiter(teaspoon / 202.88);
		setGallon(teaspoon / 768.00);
		setKiloliter(teaspoon / 2.0288e+5);
	} // end method calculateFromTeaspoon
	
	public void calculateFromCentiliter(double centiliter) {
		// convert centiliter to other volume values,
		// and the store the values in the member variables
		setMilliliter(centiliter * 10.000);
		setTeaspoon(centiliter * 2.0288);
		setCentiliter(centiliter);
		setTablespoon(centiliter / 1.4787);
		setOunce(centiliter / 2.9574);
		setCup(centiliter / 23.659);
		setPint(centiliter / 47.318);
		setLiter(centiliter / 100.00);
		setGallon(centiliter / 378.54);
		setKiloliter(centiliter / 1.0000e+5);
	} // end method calculateFromCentiliter
	
	public void calculateFromTablespoon(double tablespoon) {
		// convert tablespoon to other volume values,
		// and the store the values in the member variables
		setMilliliter(tablespoon * 14.787);
		setTeaspoon(tablespoon * 3.0000);
		setCentiliter(tablespoon * 1.4787);
		setTablespoon(tablespoon);
		setOunce(tablespoon / 2.0000);
		setCup(tablespoon / 16.000);
		setPint(tablespoon / 32.000);
		setLiter(tablespoon / 67.628);
		setGallon(tablespoon / 256.00);
		setKiloliter(tablespoon / 67628);
	} // end method calculateFromTablespoon
	
	public void calculateFromOunce(double ounce) {
		// convert ounce to other volume values,
		// and the store the values in the member variables
		setMilliliter(ounce * 29.574);
		setTeaspoon(ounce * 6.0000);
		setCentiliter(ounce * 2.9574);
		setTablespoon(ounce * 2.0000);
		setOunce(ounce);
		setCup(ounce / 8.0000);
		setPint(ounce / 16.000);
		setLiter(ounce / 33.814);
		setGallon(ounce / 128.00);
		setKiloliter(ounce / 33814);
	} // end method calculateFromOunce
	
	public void calculateFromCup(double cup) {
		// convert cup to other volume values,
		// and the store the values in the member variables
		setMilliliter(cup * 236.59);
		setTeaspoon(cup * 48.000);
		setCentiliter(cup * 23.659);
		setTablespoon(cup * 16.000);
		setOunce(cup * 8.0000);
		setCup(cup);
		setPint(cup / 2.0000);
		setLiter(cup / 4.2268);
		setGallon(cup / 16.000);
		setKiloliter(cup / 4226.8);
	} // end method calculateFromCup
	
	public void calculateFromPint(double pint) {
		// convert pint to other volume values,
		// and the store the values in the member variables
		setMilliliter(pint * 473.18);
		setTeaspoon(pint * 96.000);
		setCentiliter(pint * 47.318);
		setTablespoon(pint * 32.000);
		setOunce(pint * 16.000);
		setCup(pint * 2.0000);
		setPint(pint);
		setLiter(pint / 2.1134);
		setGallon(pint / 8.0000);
		setKiloliter(pint / 2113.4);
	} // end method calculateFromPint
	
	public void calculateFromLiter(double liter) {
		// convert liter to other volume values,
		// and the store the values in the member variables
		setMilliliter(liter * 1000.0);
		setTeaspoon(liter * 202.88);
		setCentiliter(liter * 100.00);
		setTablespoon(liter * 67.628);
		setOunce(liter * 33.814);
		setCup(liter * 4.2268);
		setPint(liter * 2.1134);
		setLiter(liter);
		setGallon(liter / 3.7854);
		setKiloliter(liter / 1000.0);
	} // end method calculateFromLiter
	
	public void calculateFromGallon(double gallon) {
		// convert gallon to other volume values,
		// and the store the values in the member variables
		setMilliliter(gallon * 3785.4);
		setTeaspoon(gallon * 768.00);
		setCentiliter(gallon * 378.54);
		setTablespoon(gallon * 256.00);
		setOunce(gallon * 128.00);
		setCup(gallon * 16.000);
		setPint(gallon * 8.0000);
		setLiter(gallon * 3.7854);
		setGallon(gallon);
		setKiloliter(gallon / 264.17);
	} // end method calculateFromGallon
	
	public void calculateFromKiloliter(double kiloliter) {
		// convert kiloliter to other volume values,
		// and the store the values in the member variables
		setMilliliter(kiloliter * 1.0000e+6);
		setTeaspoon(kiloliter * 2.0288e+5);
		setCentiliter(kiloliter * 1.0000e+5);
		setTablespoon(kiloliter * 67628);
		setOunce(kiloliter * 33814);
		setCup(kiloliter * 4226.8);
		setPint(kiloliter * 2113.4);
		setLiter(kiloliter * 1000.0);
		setGallon(kiloliter * 264.17);
		setKiloliter(kiloliter);
	} // end method calculateFromKiloliter	
} // end class VolumeModel
