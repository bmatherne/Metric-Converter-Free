package com.beaumatherne.mainpackage;

public class LengthModel {
	private double mMillimeter;
	private double mCentimeter;
	private double mInch;
	private double mDecimeter;
	private double mFeet;
	private double mYard;
	private double mMeter;
	private double mKilometer;
	private double mMile;
	
	public LengthModel() {
		// set initial values for member fields
		setMillimeter(0);
		setCentimeter(0);
		setInch(0);
		setDecimeter(0);
		setFeet(0);
		setYard(0);
		setMeter(0);
		setKilometer(0);
		setMile(0);
	} // end constructor LengthModel()
	
	public double getMillimeter() {
		return mMillimeter;
	} // end method getMillimeter
	
	public void setMillimeter(double millimeter) {
		mMillimeter = millimeter;
	} // end method setMillimeter
	
	public double getCentimeter() {
		return mCentimeter;
	} // end method getCentimeter
	
	public void setCentimeter(double centimeter) {
		mCentimeter = centimeter;
	} // end method setCentimeter
	
	public double getInch() {
		return mInch;
	} // end method getInch
	
	public void setInch(double inch) {
		mInch = inch;
	} // end method setInch
	
	public double getDecimeter() {
		return mDecimeter;
	} // end method getDecimeter
	
	public void setDecimeter(double decimeter) {
		mDecimeter = decimeter;
	} // end method setDecimeter
	
	public double getFeet() {
		return mFeet;
	} // end method getFeet
	
	public void setFeet(double feet) {
		mFeet = feet;
	} // end method setFeet
	
	public double getYard() {
		return mYard;
	} // end method getYard
	
	public void setYard(double yard) {
		mYard = yard;
	} // end method setYard
	
	public double getMeter() {
		return mMeter;
	} // end method getMeter
	
	public void setMeter(double meter) {
		mMeter = meter;
	} // end method setMeter
	
	public double getKilometer() {
		return mKilometer;
	} // end method getKilometer
	
	public void setKilometer(double kilometer) {
		mKilometer = kilometer;
	} // end method setKilometer
	
	public double getMile() {
		return mMile;
	} // end method getMile
	
	public void setMile(double mile) {
		mMile = mile;
	} // end method setMiles
	
	public void calculateFromMillimeter(double millimeter) {
		// convert millimeter to other length values,
		// and store the values in the member variables
		setMillimeter(millimeter);
		setCentimeter(millimeter / 10.0);
		setInch(millimeter / 25.400);
		setDecimeter(millimeter / 100.00);
		setFeet(millimeter / 304.80);
		setYard(millimeter / 914.40);
		setMeter(millimeter / 1000.0);
		setKilometer(millimeter / 1.0000e+6);
		setMile(millimeter / 1.6093e+6);
	} // end method calculateFromMillimeter
	
	public void calculateFromCentimeter(double centimeter) {
		// convert centimeter to other length values,
		// and store the values in the member variables
		setMillimeter(centimeter * 10.000);
		setCentimeter(centimeter);
		setInch(centimeter / 2.5400);
		setDecimeter(centimeter / 10.000);
		setFeet(centimeter / 30.480);
		setYard(centimeter / 91.440);
		setMeter(centimeter / 100.00);
		setKilometer(centimeter / 1.0000e+5);
		setMile(centimeter / 1.6093e+5);
	} // end method calculateFromCentimeter
	
	public void calculateFromInch(double inch) {
		// convert inch to other length values,
		// and store the values in the member variables
		setMillimeter(inch * 25.400);
		setCentimeter(inch * 2.5400);
		setInch(inch);
		setDecimeter(inch / 3.9370);
		setFeet(inch / 12.000);
		setYard(inch / 36.000);
		setMeter(inch / 39.370);
		setKilometer(inch / 39370);
		setMile(inch / 63360);
	} // end method calculateFromInch
	
	public void calculateFromDecimeter(double decimeter) {
		// convert decimeter to other length values,
		// and store the values in the member variables
		setMillimeter(decimeter * 100.00);
		setCentimeter(decimeter * 10.000);
		setInch(decimeter * 3.9370);
		setDecimeter(decimeter);
		setFeet(decimeter / 3.0480);
		setYard(decimeter / 9.1440);
		setMeter(decimeter / 10.000);
		setKilometer(decimeter / 10000);
		setMile(decimeter / 16093);
	} // end method calculateFromDecimeter
	
	public void calculateFromFeet(double feet) {
		// convert feet to other length values,
		// and store the values in the member variables
		setMillimeter(feet * 304.80);
		setCentimeter(feet * 30.480);
		setInch(feet * 12.000);
		setDecimeter(feet * 3.0480);
		setFeet(feet);
		setYard(feet / 3.0000);
		setMeter(feet / 3.2808);
		setKilometer(feet / 3280.8);
		setMile(feet / 5280.0);
	} // end method calculateFromFeet
	
	public void calculateFromYard(double yard) {
		// convert yard to other length values,
		// and store the values in the member variables
		setMillimeter(yard * 914.40);
		setCentimeter(yard * 91.440);
		setInch(yard * 36.000);
		setDecimeter(yard * 9.1440);
		setFeet(yard * 3.0000);
		setYard(yard);
		setMeter(yard / 1.0936);
		setKilometer(yard / 1093.6);
		setMile(yard / 1760.0);
	} // end method calculateFromYard
	
	public void calculateFromMeter(double meter) {
		// convert meter to other length values,
		// and store the values in the member variables
		setMillimeter(meter * 1000.0);
		setCentimeter(meter * 100.00);
		setInch(meter * 39.370);
		setDecimeter(meter * 10.000);
		setFeet(meter * 3.2808);
		setYard(meter * 1.0936);
		setMeter(meter);
		setKilometer(meter / 1000.0);
		setMile(meter / 1609.3);
	} // end method calculateFromMeter
	
	public void calculateFromKilometer(double kilometer) {
		// convert kilometer to other length values,
		// and store the values in the member variables
		setMillimeter(kilometer * 1.0000e+6);
		setCentimeter(kilometer * 1.0000e+5);
		setInch(kilometer * 39370);
		setDecimeter(kilometer * 10000);
		setFeet(kilometer * 3280.8);
		setYard(kilometer * 1093.6);
		setMeter(kilometer * 1000.0);
		setKilometer(kilometer);
		setMile(kilometer / 1.6093);
	} // end method calculateFromKilometer
	
	public void calculateFromMile(double mile) {
		// convert mile to other length values,
		// and store the values in the member variables
		setMillimeter(mile * 1.6093e+6);
		setCentimeter(mile * 1.6093e+5);
		setInch(mile * 63360);
		setDecimeter(mile * 16093);
		setFeet(mile * 5280.0);
		setYard(mile * 1760.0);
		setMeter(mile * 1609.3);
		setKilometer(mile * 1.6093);
		setMile(mile);
	} // end method calculateFromMile
} // end class LengthModel
