package org.srinivas.siteworks.changeservice;
import java.util.Collection;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.srinivas.siteworks.calculate.Calculate;
import org.srinivas.siteworks.data.PropertiesReadWriter;
import org.srinivas.siteworks.denomination.Coin;
import com.google.inject.Inject;
import com.google.inject.name.Named;



public class ChangeServiceImpl implements ChangeService {
	

	@Inject
	PropertiesReadWriter propertiesReadWriter;
	

	@Inject @Named("Supply")
	Calculate supplyCalculator;
	

	@Inject @Named("Optimal")
	Calculate optimalCalculator;
	

	public static final Logger log = LoggerFactory.getLogger(ChangeServiceImpl.class);
	
	/* (non-Javadoc)
	 * @see org.srinivas.siteworks.changeservice.ChangeService#getOptimalChangeFor(int)
	 */
	@Override
	public Collection<Coin> getOptimalChangeFor(int pence)  {
		
		try {
			return optimalCalculator.calculate(pence,propertiesReadWriter.denominations());
		} catch (Exception e) {
			log.error("Optimal Calculation not Successful",e);
			return Collections.emptyList();
		}
	}

	/* (non-Javadoc)
	 * @see org.srinivas.siteworks.changeservice.ChangeService#getChangeFor(int)
	 */
	@Override
	public Collection<Coin> getChangeFor(int pence) {
		try {
			return supplyCalculator.calculate(pence,propertiesReadWriter.denominations());
		} catch (Exception e) {
			log.error("Supply Calculation not Successful",e);
			return Collections.emptyList();
		}
	}

}
