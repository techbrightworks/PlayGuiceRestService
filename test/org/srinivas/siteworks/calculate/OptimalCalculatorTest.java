package org.srinivas.siteworks.calculate;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.srinivas.siteworks.data.PropertiesReadWriter;
import org.srinivas.siteworks.denomination.Coin;
import org.srinivas.siteworks.module.Module;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.name.Names;

@RunWith(MockitoJUnitRunner.class)
public class OptimalCalculatorTest {
	Injector testInjector;
	private Calculate optimalCalculator;
	PropertiesReadWriter propertiesReadWriter;


	@Before
	public void setUp() throws Exception {
		testInjector = Guice.createInjector(new Module());
		MockitoAnnotations.initMocks(this);	
		optimalCalculator =	testInjector.getInstance(Key.get(Calculate.class, Names.named("Optimal")));
		propertiesReadWriter = testInjector.getInstance(PropertiesReadWriter.class);
	}

	@After
	public void tearDown() throws Exception {
		propertiesReadWriter = null;
		optimalCalculator = null;
	}

	@Test
	public void testCalculate() throws Exception {
		
		propertiesReadWriter.readInventoryData();
		List<Coin>coins = optimalCalculator.calculate(576, propertiesReadWriter.denominations());
		assertTrue(coins.size() == 5);
		assertEquals(5,filterByValue(coins,100).getCount().intValue());
		assertEquals(1,filterByValue(coins,50).getCount().intValue());
		assertEquals(1,filterByValue(coins,20).getCount().intValue());
		assertEquals(1,filterByValue(coins,5).getCount().intValue());
		assertEquals(1,filterByValue(coins,1).getCount().intValue());
	}
	
	@Test
	public void testErrorScenario() throws Exception {
		propertiesReadWriter.readInventoryData();
		List<Coin>coins = optimalCalculator.calculate(576, null);
		assertTrue(coins.size() == 0);
	}

	
	private Coin filterByValue(List<Coin> coins, Integer value) {
		return coins.stream().filter(coin -> coin.getValue() == value).findFirst().get();
	}

}
