package com.orcunguducu.dynamic.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.orcunguducu.dynamic.domain.Address;
import com.orcunguducu.dynamic.domain.Parameter;
import com.orcunguducu.dynamic.domain.ParameterValue;
import com.orcunguducu.dynamic.domain.User;

public class DataGenerator {
	
	public static void main(String[] args) {
		createData(false);
		System.exit(0);
	}
	
	//Rollback is for test purpose
	public static void createData(boolean rollback) {
		String uniqueString = "";
		if(rollback)
			uniqueString = "uniqueString";
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("DynamicUserPage");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();

		Parameter parameterCountry = new Parameter();
		parameterCountry.setParameterName("PRM_COUNTRY_LIST");
		
		Parameter parameterCities = new Parameter();
		parameterCities.setParameterName("PRM_CITY_LIST");
		
		Parameter parameterCurrencies = new Parameter();
		parameterCurrencies.setParameterName("PRM_CURRENCY_LIST");
		
		User user = new User();
		user.setFirstName("Orcun");
		user.setLastName("Guducu");	
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode("dynamic");
		user.setPassword(hashedPassword);
		user.setUsername("orcunguducu@hotmail.com"+uniqueString);
		
		et.begin();
		em.persist(user);
		em.persist(parameterCountry);
		em.persist(parameterCities);
		em.persist(parameterCurrencies);
		
		Address address = new Address();
		address.setCountry("Turkey");
		address.setCity("Istanbul");
		address.setStreet("My Street");
		address.setZipCode("34755");
		address.setUserId(user.getId());
		
		ParameterValue countryParameter0 = new ParameterValue();
		countryParameter0.setParamKey(2);
		countryParameter0.setValue("Germany");
		countryParameter0.setParameterId(parameterCountry.getId());
		
		ParameterValue countryParameter1 = new ParameterValue();
		countryParameter1.setParamKey(1);
		countryParameter1.setValue("Turkey");
		countryParameter1.setParameterId(parameterCountry.getId());

		ParameterValue countryParameter3 = new ParameterValue();
		countryParameter3.setParamKey(3);
		countryParameter3.setValue("Netherlands");
		countryParameter3.setParameterId(parameterCountry.getId());
		
		em.persist(address);
		em.persist(countryParameter0);
		em.persist(countryParameter1);
		em.persist(countryParameter3);
		
		ParameterValue cityParameter1 = new ParameterValue();
		cityParameter1.setParamKey(1);
		cityParameter1.setFilterKey(countryParameter3.getParamKey());
		cityParameter1.setValue("Amsterdam");
		cityParameter1.setParameterId(parameterCities.getId());
		
		ParameterValue cityParameter2 = new ParameterValue();
		cityParameter2.setParamKey(2);
		cityParameter2.setFilterKey(countryParameter3.getParamKey());
		cityParameter2.setValue("Eindhoven");
		cityParameter2.setParameterId(parameterCities.getId());
		
		ParameterValue cityParameter3 = new ParameterValue();
		cityParameter3.setParamKey(3);
		cityParameter3.setFilterKey(countryParameter3.getParamKey());
		cityParameter3.setValue("Rotterdam");
		cityParameter3.setParameterId(parameterCities.getId());
		
		ParameterValue cityParameter4 = new ParameterValue();
		cityParameter4.setParamKey(4);
		cityParameter4.setFilterKey(countryParameter3.getParamKey());
		cityParameter4.setValue("Amhem");
		cityParameter4.setParameterId(parameterCities.getId());
		
		ParameterValue cityParameter5 = new ParameterValue();
		cityParameter5.setParamKey(5);
		cityParameter5.setFilterKey(countryParameter0.getParamKey());
		cityParameter5.setValue("Berlin");
		cityParameter5.setParameterId(parameterCities.getId());
		
		ParameterValue cityParameter6 = new ParameterValue();
		cityParameter6.setParamKey(6);
		cityParameter6.setFilterKey(countryParameter0.getParamKey());
		cityParameter6.setValue("Munich");
		cityParameter6.setParameterId(parameterCities.getId());
		
		ParameterValue cityParameter7 = new ParameterValue();
		cityParameter7.setParamKey(7);
		cityParameter7.setFilterKey(countryParameter0.getParamKey());
		cityParameter7.setValue("Hamburg");
		cityParameter7.setParameterId(parameterCities.getId());
		
		ParameterValue cityParameter8 = new ParameterValue();
		cityParameter8.setParamKey(8);
		cityParameter8.setFilterKey(countryParameter1.getParamKey());
		cityParameter8.setValue("Istanbul");
		cityParameter8.setParameterId(parameterCities.getId());
		
		ParameterValue cityParameter9 = new ParameterValue();
		cityParameter9.setParamKey(9);
		cityParameter9.setFilterKey(countryParameter1.getParamKey());
		cityParameter9.setValue("Ankara");
		cityParameter9.setParameterId(parameterCities.getId());
	
		ParameterValue currencyParameter1 = new ParameterValue();
		currencyParameter1.setParamKey(1);
		currencyParameter1.setValue("EUR");
		currencyParameter1.setParameterId(parameterCurrencies.getId());
		
		ParameterValue currencyParameter2 = new ParameterValue();
		currencyParameter2.setParamKey(2);
		currencyParameter2.setValue("USD");
		currencyParameter2.setParameterId(parameterCurrencies.getId());
		
		ParameterValue currencyParameter3 = new ParameterValue();
		currencyParameter3.setParamKey(3);
		currencyParameter3.setValue("GBP");
		currencyParameter3.setParameterId(parameterCurrencies.getId());
		
		ParameterValue currencyParameter4 = new ParameterValue();
		currencyParameter4.setParamKey(4);
		currencyParameter4.setValue("NZD");
		currencyParameter4.setParameterId(parameterCurrencies.getId());
		
		ParameterValue currencyParameter5 = new ParameterValue();
		currencyParameter5.setParamKey(5);
		currencyParameter5.setValue("AUD");
		currencyParameter5.setParameterId(parameterCurrencies.getId());
		
		ParameterValue currencyParameter6 = new ParameterValue();
		currencyParameter6.setParamKey(6);
		currencyParameter6.setValue("JPY");
		currencyParameter6.setParameterId(parameterCurrencies.getId());
		
		ParameterValue currencyParameter7 = new ParameterValue();
		currencyParameter7.setParamKey(7);
		currencyParameter7.setValue("HUF");
		currencyParameter7.setParameterId(parameterCurrencies.getId());
		
		em.persist(cityParameter1);
		em.persist(cityParameter2);
		em.persist(cityParameter3);
		em.persist(cityParameter4);
		em.persist(cityParameter5);
		em.persist(cityParameter6);
		em.persist(cityParameter7);
		em.persist(cityParameter8);
		em.persist(cityParameter9);
		em.persist(currencyParameter1);
		em.persist(currencyParameter2);
		em.persist(currencyParameter3);
		em.persist(currencyParameter4);
		em.persist(currencyParameter5);
		em.persist(currencyParameter6);
		em.persist(currencyParameter7);
		if(rollback)
			et.rollback();
		et.commit();
		em.close();
	}
}
