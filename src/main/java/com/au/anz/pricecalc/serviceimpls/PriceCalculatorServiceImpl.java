/**
 * 
 */
package com.au.anz.pricecalc.serviceimpls;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.au.anz.pricecalc.constants.PriceCalculatorConstants;
import com.au.anz.pricecalc.services.PriceCalculatorService;

/**
 * @author Sarin
 *
 */
public class PriceCalculatorServiceImpl implements PriceCalculatorService {
	
	private static final Logger LOG = LoggerFactory.getLogger(PriceCalculatorServiceImpl.class);

	/**
	 * This method is used read from the console and populate the values
	 * 
	 * @return List<BigDecimal>
	 */
	public List<BigDecimal> populate() {
		LOG.info("populate - STARTS");
		int count = 0;
		String price = null;
		List<BigDecimal> priceList = new ArrayList<>();
		try(Scanner scanner = new Scanner(System.in)) { // Reading from System.in
			System.out.println("Enter the prices: ");
			while(scanner.hasNext()) {
				count++;
				price = scanner.next();
				if(null == price || price.isEmpty())
					break;
				try {
					priceList.add(new BigDecimal(price));
				} catch (NumberFormatException nfe) {
					LOG.error("Invalid price format %d day",count);
					System.out.println("Invalid price format at " + count + " day");
				}
			}
			LOG.info("populate - ENDS");
			return priceList;
		}
	}
	
	/**
	 * This method is used to calculate the profits and losses
	 * 
	 * @param priceList
	 * @return Map<String, List<BigDecimal>>
	 */
	public Map<String, List<BigDecimal>> calculate(List<BigDecimal> priceList) {
		LOG.info("calculate - STARTS");
		BigDecimal tempPrice = null;
		Map<String, List<BigDecimal>> resultMap = new HashMap<>();
		resultMap.put(PriceCalculatorConstants.PROFITS, new ArrayList<BigDecimal>());
		resultMap.put(PriceCalculatorConstants.LOSSES, new ArrayList<BigDecimal>());
		for (BigDecimal price : priceList) {
			if(null == tempPrice) {
				tempPrice = price;
			}
			if(tempPrice.compareTo(price) < 0) {
				resultMap.get(PriceCalculatorConstants.PROFITS).add(price.subtract(tempPrice).setScale(2, RoundingMode.CEILING));
			} else if(tempPrice.compareTo(price) > 0) {
				resultMap.get(PriceCalculatorConstants.LOSSES).add(price.subtract(tempPrice).setScale(2, RoundingMode.CEILING));
			}
			tempPrice = price;
		}
		LOG.info("calculate - ENDS");
		LOG.info("calculate - ENDS");
		return resultMap;
	}
	
	/**
	 * This method is find the total of profits and losses
	 * 
	 * @param resultMap
	 * @return Map<String, BigDecimal>
	 */
	public Map<String, BigDecimal> total(Map<String, List<BigDecimal>> resultMap) {
		LOG.info("total - STARTS");
		Map<String, BigDecimal> totalMap = new HashMap<>();
		for (Entry<String, List<BigDecimal>> entry : resultMap.entrySet()) {
			BigDecimal total = new BigDecimal(0);
			for (BigDecimal price : entry.getValue()) {
				total = total.add(price);
			}
			totalMap.put(entry.getKey(), total);
		}
		LOG.info("total - ENDS");
		return totalMap;
	}
	
	/**
	 * This method is used to display total profit and loss
	 * 
	 * @param totalMap
	 */
	public void display(Map<String, BigDecimal> totalMap) {
		LOG.info("display - STARTS");
		System.out.println("Total profits: " + totalMap.get(PriceCalculatorConstants.PROFITS));
		System.out.println("Total loss: " + totalMap.get(PriceCalculatorConstants.LOSSES));
		LOG.info("display - ENDS");
	}

}
