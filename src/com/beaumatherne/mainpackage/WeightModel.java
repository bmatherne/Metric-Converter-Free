package com.beaumatherne.mainpackage;

public class WeightModel {
	private double mMilligram;
	private double mGram;
	private double mOunce;
	private double mPound;
	private double mKilogram;
	private double mStone;
	private double mShortTon;
	private double mMetricTon;
	private double mLongTon;
	
	public WeightModel() {
		// set initial values for member fields
		setMilligram(0);
		setGram(0);
		setOunce(0);
		setPound(0);
		setKilogram(0);
		setStone(0);
		setShortTon(0);
		setMetricTon(0);
		setLongTon(0);
	} // end constructor WeightModel()
	
	public double getMilligram() {
		return mMilligram;
	} // end method getMilligram
	
	public void setMilligram(double milligram) {
		mMilligram = milligram;
	} // end method setMilligram
	
	public double getGram() {
		return mGram;
	} // end method getGram
	
	public void setGram(double gram) {
		mGram = gram;
	} // end method setGram
	
	public double getOunce() {
		return mOunce;
	} // end method getOunce
	
	public void setOunce(double ounce) {
		mOunce = ounce;
	} // end method setOunce
	
	public double getPound() {
		return mPound;
	} // end method getPound
	
	public void setPound(double pound) {
		mPound = pound;
	} // end method setPound
	
	public double getKilogram() {
		return mKilogram;
	} // end method getKilogram
	
	public void setKilogram(double kilogram) {
		mKilogram = kilogram;
	} // end method setKilogram
	
	public double getStone() {
		return mStone;
	} // end method getStone
	
	public void setStone(double stone) {
		mStone = stone;
	} // end method setStone
	
	public double getShortTon() {
		return mShortTon;
	} // end method getShortTon
	
	public void setShortTon(double shortTon) {
		mShortTon = shortTon;
	} // end method setShortTon
	
	public double getMetricTon() {
		return mMetricTon;
	} // end method getMetricTon
	
	public void setMetricTon(double metricTon) {
		mMetricTon = metricTon;
	} // end method setMetricTon
	
	public double getLongTon() {
		return mLongTon;
	} // end method getLongTon
	
	public void setLongTon(double longTon) {
		mLongTon = longTon;
	} // end method setLongTon
	
	public void calculateFromMilligram(double milligram) {
		// convert milligram to other weight values,
		// and the store the values in the member variables
		setMilligram(milligram);
		setGram(milligram / 1000.0);
		setOunce(milligram / 28350.0);
		setPound(milligram / 4.5359e+5);
		setKilogram(milligram / 1.0000e+6);
		setStone(milligram / 6.3503e+6);
		setShortTon(milligram / 9.0718e+8);
		setMetricTon(milligram / 1.0000e+9);
		setLongTon(milligram / 1.0160e+9);
	} // end method calculateFromMilligram
	
	public void calculateFromGram(double gram) {
		// convert gram to other weight values,
		// and store the values in the member variable
		setMilligram(gram * 1000.0);
		setGram(gram);
		setOunce(gram / 28.350);
		setPound(gram / 453.59);
		setKilogram(gram / 1000.0);
		setStone(gram / 6350.3);
		setShortTon(gram / 9.0718e+5);
		setMetricTon(gram / 1.0000e+6);
		setLongTon(gram / 1.0160e+6);
	} // end method calculateFromGram
	
	public void calculateFromOunce(double ounce) {
		// convert ounce to other weight values,
		// and store the values in the member variables
		setMilligram(ounce * 28350.0);
		setGram(ounce * 28.350);
		setOunce(ounce);
		setPound(ounce / 16.000);
		setKilogram(ounce / 35.274);
		setStone(ounce / 224.00);
		setShortTon(ounce / 32000.0);
		setMetricTon(ounce / 35274.0);
		setLongTon(ounce / 35840.0);
	} // end method calculateFromOunce
	
	public void calculateFromPound(double pound) {
		// convert pound to other weight values,
		// and store the values in the member variables
		setMilligram(pound * 4.5359e+5);
		setGram(pound * 453.59);
		setOunce(pound * 16.000);
		setPound(pound);
		setKilogram(pound / 2.2046);
		setStone(pound / 14.000);
		setShortTon(pound / 2000.0);
		setMetricTon(pound / 2204.6);
		setLongTon(pound / 2240.0);
	} // end method calculateFromPound
	
	public void calculateFromKilogram(double kilogram) {
		// convert kilogram to other weight values,
		// and store the values in the member variables
		setMilligram(kilogram * 1.0000e+6);
		setGram(kilogram * 1000.0);
		setOunce(kilogram * 35.274);
		setPound(kilogram * 2.2046);
		setKilogram(kilogram);
		setStone(kilogram / 6.3503);
		setShortTon(kilogram / 907.18);
		setMetricTon(kilogram / 1000.0);
		setLongTon(kilogram / 1016.0);
	} // end method calculateFromKilogram
	
	public void calculateFromStone(double stone) {
		// convert stone to other weight values,
		// and store the values in the member variables
		setMilligram(stone * 6.3503e+6);
		setGram(stone * 6350.3);
		setOunce(stone * 224.00);
		setPound(stone * 14.000);
		setKilogram(stone * 6.3503);
		setStone(stone);
		setShortTon(stone / 142.86);
		setMetricTon(stone / 157.47);
		setLongTon(stone / 160.00);
	} // end method calculateFromStone
	
	public void calculateFromShortTon(double shortTon) {
		// convert shortTon to other weight values,
		// and store the values in the member variables
		setMilligram(shortTon * 9.0718e+8);
		setGram(shortTon * 9.0718e+5);
		setOunce(shortTon * 32000.0);
		setPound(shortTon * 2000.0);
		setKilogram(shortTon * 907.18);
		setStone(shortTon * 142.86);
		setShortTon(shortTon);
		setMetricTon(shortTon / 1.1023);
		setLongTon(shortTon / 1.1200);
	} // end method calculateFromShortTon
	
	public void calculateFromMetricTon(double metricTon) {
		// convert metricTon to other weight values,
		// and store the values in the member variables
		setMilligram(metricTon * 1.0000e+9);
		setGram(metricTon * 1.0000e+6);
		setOunce(metricTon * 35274.0);
		setPound(metricTon * 2204.6);
		setKilogram(metricTon * 1000.0);
		setStone(metricTon * 157.47);
		setShortTon(metricTon * 1.1023);
		setMetricTon(metricTon);
		setLongTon(metricTon / 1.0160);
	} // end method calculateFromMetricTon
	
	public void calculateFromLongTon(double longTon) {
		// convert longTon to other weight values,
		// and store the values in the member variables
		setMilligram(longTon * 1.0160e+9);
		setGram(longTon * 1.0160e+6);
		setOunce(longTon * 35840.0);
		setPound(longTon * 2240.0);
		setKilogram(longTon * 1016.0);
		setStone(longTon * 160.00);
		setShortTon(longTon * 1.1200);
		setMetricTon(longTon * 1.0160);
		setLongTon(longTon);
	} // end method calculateFromLongTon
} // end class WeightModel
