// Author: Beau Matherne
// Date: January 20, 2014
// Description:	This class will hold temperature data, and it
//				will convert temperature data from one unit of
//				measurement to another.

package com.beaumatherne.mainpackage;

public class TemperatureModel {
	private double mCelsius;
	private double mFahrenheit;
	private double mKelvin;
	
	public TemperatureModel() {
		// set initial values for member fields
		setCelsius(0);
		setFahrenheit(0);
		setKelvin(0);
	} // end constructor TemperatureModel()
	
	public void setCelsius(double celsius) {
		mCelsius = celsius;
	} // end method setCelsius
	
	public double getCelsius() {
		return mCelsius;
	} // end method getCelsius
	
	public void setFahrenheit(double fahrenheit) {
		mFahrenheit = fahrenheit;
	} // end method setFahrenheit
	
	public double getFahrenheit() {
		return mFahrenheit;
	} // end method getFahrenheit
	
	public void setKelvin(double kelvin) {
		mKelvin = kelvin;
	} // end method setKelvin
	
	public double getKelvin() {
		return mKelvin;
	} // end method getKelvin
	
	public void calculateFromCelsius(double celsius) {
		// convert celsius to fahrenheit and kelvin,
		// and store the values in the member variables
		setCelsius(celsius);
		setFahrenheit(celsius * 1.8 + 32.00);
		setKelvin(celsius + 273.15);
	} // end method calculateFromCelsius
	
	public void calculateFromFahrenheit(double fahrenheit) {
		// convert fahrenheit to celsius and kelvin,
		// and store the values in the member variables
		setCelsius((fahrenheit - 32.0) / 1.8);
		setFahrenheit(fahrenheit);
		setKelvin((fahrenheit - 32.0) / 1.8 + 273.15);
	} // end method calculateFromFahrenheit
	
	public void calculateFromKelvin(double kelvin) {
		// convert kelvin to celsius and fahrenheit,
		// and store the values in the member variables
		setCelsius(kelvin - 273.15);
		setFahrenheit((kelvin - 273.15) * 1.8 + 32.0);
		setKelvin(kelvin);
	} // end method calculateFromKelvin
} // end class TemperatureModel
