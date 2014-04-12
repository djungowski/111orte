package de.djungowski.orte;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomNumberGenerator
{
	private final Integer NUMBER_OF_SIGHTS = 110;
	private final Integer NUMBER_OF_SIGHTS_OFFSET = 1;
	private Random random;
	private List<Integer> alreadyGenerated;
	
	/**
	 * Create new RandomNumberGenerator instance
	 * 
	 * @param Random random
	 */
	public RandomNumberGenerator(Random random)
	{
		this.random = random;
		alreadyGenerated = new ArrayList<Integer>();
	}
	
	/*
	 * Get the next available random number
	 * 
	 * @return Integer
	 */
	public Integer getNext()
	{
		Integer randomInteger;
		
		randomInteger = getRandomInteger();
		if (hasAlreadyGenerated(randomInteger)) {
			randomInteger = getRandomInteger();
		}
		saveGeneratedInteger(randomInteger);
		return randomInteger;
	}
	
	/*
	 * Get a random number based on the available number of sights
	 * 
	 * @return Integer
	 */
	private Integer getRandomInteger()
	{
		return this.random.nextInt(NUMBER_OF_SIGHTS) + NUMBER_OF_SIGHTS_OFFSET;
	}
	
	/*
	 * Check if a number has already been generated
	 * 
	 * @param Integer generatedInteger
	 * @return Boolean
	 */
	private Boolean hasAlreadyGenerated(Integer generatedInteger)
	{
		return alreadyGenerated.contains(generatedInteger);
	}
	
	/*
	 * Save a number that has been generated for later checking
	 * 
	 * @param Integer generatedInteger
	 */
	private void saveGeneratedInteger(Integer generatedInteger)
	{
		alreadyGenerated.add(generatedInteger);
	}
}
