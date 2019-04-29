package br.com.collegesmaster.generics.contracts;

import java.io.Serializable;

public interface Localization extends Serializable {

	void setCity(String city);

	String getCity();

	void setState(String state);

	String getState();

	void setCountry(String country);

	String getCountry();

}
