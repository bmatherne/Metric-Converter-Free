package com.beaumatherne.mainpackage;

public class AreaModel {
	private double mSquareMillimeter;
	private double mSquareCentimeter;
	private double mSquareInch;
	private double mSquareFeet;
	private double mSquareYard;
	private double mSquareMeter;
	private double mAcre;
	private double mSquareKilometer;
	private double mSquareMile;
	
	public AreaModel() {
		setSquareMillimeter(0);
		setSquareCentimeter(0);
		setSquareInch(0);
		setSquareFeet(0);
		setSquareYard(0);
		setSquareMeter(0);
		setAcre(0);
		setSquareKilometer(0);
		setSquareMile(0);
	}
	
	public double getSquareMillimeter() {
		return mSquareMillimeter;
	} // end method getSquareMillimeter
	
	public void setSquareMillimeter(double squareMillimeter) {
		mSquareMillimeter = squareMillimeter;
	} // end method setSquareMillimeter
	
	public double getSquareCentimeter() {
		return mSquareCentimeter;
	} // end method getSquareCentimer
	
	public void setSquareCentimeter(double squareCentimeter) {
		mSquareCentimeter = squareCentimeter;
	} // end method setSquareCentimer
	
	public double getSquareInch() {
		return mSquareInch;
	} // end method getSquareInch
	
	public void setSquareInch(double squareInch) {
		mSquareInch = squareInch;
	} // end method setSquareInch
	
	public double getSquareFeet() {
		return mSquareFeet;
	} // end method getSquareFeet
	
	public void setSquareFeet(double squareFeet) {
		mSquareFeet = squareFeet;
	} // end method setSquareFeet
	
	public double getSquareYard() {
		return mSquareYard;
	} // end method getSquareYard
	
	public void setSquareYard(double squareYard) {
		mSquareYard = squareYard;
	} // end method setSquareYard
	
	public double getSquareMeter() {
		return mSquareMeter;
	} // end method getSquareMeter
	
	public void setSquareMeter(double squareMeter) {
		mSquareMeter = squareMeter;
	} // end method setSquareMeter
	
	public double getAcre() {
		return mAcre;
	} // end method getAcre
	
	public void setAcre(double acre) {
		mAcre = acre;
	} // end method setAcre
	
	public double getSquareKilometer() {
		return mSquareKilometer;
	} // end method getSquareKilometer
	
	public void setSquareKilometer(double squareKilometer) {
		mSquareKilometer = squareKilometer;
	} // end method setSquareKilometer
	
	public double getSquareMile() {
		return mSquareMile;
	} // end method getSquareMile
	
	public void setSquareMile(double squareMile) {
		mSquareMile = squareMile;
	} // end method setSquareMile
	
	public void calculateFromSquareMillimeter(double squareMillimeter) {
		// convert squareMillimeter to other area values,
		// and store the values in the member variables
		setSquareMillimeter(squareMillimeter);
		setSquareCentimeter(squareMillimeter / 100.00);
		setSquareInch(squareMillimeter / 645.16);
		setSquareFeet(squareMillimeter / 92903);
		setSquareYard(squareMillimeter / 8.3613e+5);
		setSquareMeter(squareMillimeter / 1.0000e+6);
		setAcre(squareMillimeter / 4.0469e+9);
		setSquareKilometer(squareMillimeter / 1.0000e+12);
		setSquareMile(squareMillimeter / 2.5900e+12);
	} // end method calculateFromSquareMillimeter
	
	public void calculateFromSquareCentimeter(double squareCentimeter) {
		// convert squareCentimeter to other area values,
		// and store the values in the member variables
		setSquareMillimeter(squareCentimeter * 100.00);
		setSquareCentimeter(squareCentimeter);
		setSquareInch(squareCentimeter / 6.4516);
		setSquareFeet(squareCentimeter / 929.03);
		setSquareYard(squareCentimeter / 8361.3);
		setSquareMeter(squareCentimeter / 10000);
		setAcre(squareCentimeter / 4.0469e+7);
		setSquareKilometer(squareCentimeter / 1.0000e+10);
		setSquareMile(squareCentimeter / 2.5900e+10);
	} // end method calculateFromSquareCentimeter
	
	public void calculateFromSquareInch(double squareInch) {
		// convert squareInch to other area values,
		// and store the values in the member variables
		setSquareMillimeter(squareInch * 645.16);
		setSquareCentimeter(squareInch * 6.4516);
		setSquareInch(squareInch);
		setSquareFeet(squareInch / 144.00);
		setSquareYard(squareInch / 1296.0);
		setSquareMeter(squareInch / 1550.0);
		setAcre(squareInch / 6.2726e+6);
		setSquareKilometer(squareInch / 1.5500e+9);
		setSquareMile(squareInch / 4.0145e+9);
	} // end method calculateFromSquareInch
	
	public void calculateFromSquareFeet(double squareFeet) {
		// convert squareFeet to other area values,
		// and store the values in the member variables
		setSquareMillimeter(squareFeet * 92903);
		setSquareCentimeter(squareFeet * 929.03);
		setSquareInch(squareFeet * 144.00);
		setSquareFeet(squareFeet);
		setSquareYard(squareFeet / 9.0000);
		setSquareMeter(squareFeet / 10.764);
		setAcre(squareFeet / 43560);
		setSquareKilometer(squareFeet / 1.0764e+7);
		setSquareMile(squareFeet / 2.7878e+7);
	} // end method calculateFromSquareFeet
	
	public void calculateFromSquareYard(double squareYard) {
		// convert squareYard to other area values,
		// and store the values in the member variables
		setSquareMillimeter(squareYard * 8.3613e+5);
		setSquareCentimeter(squareYard * 8361.3);
		setSquareInch(squareYard * 1296.0);
		setSquareFeet(squareYard * 9.0000);
		setSquareYard(squareYard);
		setSquareMeter(squareYard / 1.1960);
		setAcre(squareYard / 4840.0);
		setSquareKilometer(squareYard / 1.1960e+6);
		setSquareMile(squareYard / 3.0976e+6);
	} // end method calculateFromSquareYard
	
	public void calculateFromSquareMeter(double squareMeter) {
		// convert squareMeter to other area values,
		// and store the values in the member variables
		setSquareMillimeter(squareMeter * 1.0000e+6);
		setSquareCentimeter(squareMeter * 10000);
		setSquareInch(squareMeter * 1550.0);
		setSquareFeet(squareMeter * 10.764);
		setSquareYard(squareMeter * 1.1960);
		setSquareMeter(squareMeter);
		setAcre(squareMeter / 4046.9);
		setSquareKilometer(squareMeter / 1.0000e+6);
		setSquareMile(squareMeter / 2.5900e+6);
	} // end method calculateFromSquareMeter
	
	public void calculateFromAcre(double acre) {
		// convert acre to other area values,
		// and store the values in the member variables
		setSquareMillimeter(acre * 4.0469e+9);
		setSquareCentimeter(acre * 4.0469e+7);
		setSquareInch(acre * 6.2726e+6);
		setSquareFeet(acre * 43560);
		setSquareYard(acre * 4840.0);
		setSquareMeter(acre * 4046.9);
		setAcre(acre);
		setSquareKilometer(acre / 247.11);
		setSquareMile(acre / 640.00);
	} // end method calculateFromAcre
	
	public void calculateFromSquareKilometer(double squareKilometer) {
		// convert squareKilometer to other area values,
		// and store the values in the member variables
		setSquareMillimeter(squareKilometer * 1.0000e+12);
		setSquareCentimeter(squareKilometer * 1.0000e+10);
		setSquareInch(squareKilometer * 1.5500e+9);
		setSquareFeet(squareKilometer * 1.0764e+7);
		setSquareYard(squareKilometer * 1.1960e+6);
		setSquareMeter(squareKilometer * 1.0000e+6);
		setAcre(squareKilometer * 247.11);
		setSquareKilometer(squareKilometer);
		setSquareMile(squareKilometer / 2.5900);
	} // end method calculateFromSquareKilometer
	
	public void calculateFromSquareMile(double squareMile) {
		// convert squareMile to other area values,
		// and store the values in the member variables
		setSquareMillimeter(squareMile * 2.5900e+12);
		setSquareCentimeter(squareMile * 2.5900e+10);
		setSquareInch(squareMile * 4.0145e+9);
		setSquareFeet(squareMile * 2.7878e+7);
		setSquareYard(squareMile * 3.0976e+6);
		setSquareMeter(squareMile * 2.5900e+6);
		setAcre(squareMile * 640.00);
		setSquareKilometer(squareMile * 2.5900);
		setSquareMile(squareMile);
	} // end method calculateFromSquareMile
} // end class AreaModel
