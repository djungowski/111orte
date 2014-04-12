package de.djungowski.orte;
import java.util.Random;

import junit.framework.TestCase;
import static org.mockito.Mockito.*;

public class RandomNumberGeneratorTest extends TestCase
{
	private RandomNumberGenerator generator;
	private Random randomMock;
	
	public void setUp()
	{
		randomMock = mock(Random.class);
		generator = new RandomNumberGenerator(randomMock);
	}
	
	public void testCreation()
	{
		assertTrue(generator instanceof RandomNumberGenerator);
	}
	
	public void testGetNext()
	{
		final Integer expected = 50;
		when(randomMock.nextInt(anyInt())).thenReturn(49);
		final Integer actual = generator.getNext();
		assertEquals(expected, actual);
	}
	
	public void testGetNextIfRandomReturns0()
	{
		when(randomMock.nextInt(anyInt())).thenReturn(0);
		final Integer expected = 1;
		final Integer actual = generator.getNext();
		assertEquals(expected, actual);
	}
	
	public void testGetNextIfNumberAlreadyDrawn()
	{
		stub(randomMock.nextInt(anyInt()))
			.toReturn(5)
			.toReturn(5)
			.toReturn(11);
		// Get random first time
		generator.getNext();
		// Get random second time
		final Integer actual = generator.getNext();
		final Integer expected = 12;
		assertEquals(expected, actual);
	}
}
