/**
 * 
 */
package com.au.anz.pricecalc.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author Sarin
 *
 */
public interface PriceCalculatorService {
	
	/**
	 * This method is used read from the console and populate the values
	 * 
	 * @return List<BigDecimal>
	 */
	List<BigDecimal> populate();
	
	/**
	 * This method is used to calculate the profits and losses
	 * 
	 * @param priceList
	 * @return Map<String, List<BigDecimal>>
	 */
	Map<String, List<BigDecimal>> calculate(List<BigDecimal> priceList);
	
	/**
	 * This method is find the total of profits and losses
	 * 
	 * @param resultMap
	 * @return Map<String, BigDecimal>
	 */
	Map<String, BigDecimal> total(Map<String, List<BigDecimal>> resultMap);
	
	/**
	 * This method is used to display total profit and loss
	 * 
	 * @param totalMap
	 */
	void display(Map<String, BigDecimal> totalMap);

}
