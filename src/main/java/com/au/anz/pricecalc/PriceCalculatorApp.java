/**
 * 
 */
package com.au.anz.pricecalc;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.au.anz.pricecalc.serviceimpls.PriceCalculatorServiceImpl;
import com.au.anz.pricecalc.services.PriceCalculatorService;

/**
 * @author Sarin
 *
 */
public class PriceCalculatorApp {
	
	private static final Logger LOG = LoggerFactory.getLogger(PriceCalculatorApp.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LOG.info("main - STARTS");
		PriceCalculatorService priceCalulatorService = new PriceCalculatorServiceImpl();
		List<BigDecimal> priceList = priceCalulatorService.populate();
		Map<String, List<BigDecimal>> resultMap = priceCalulatorService.calculate(priceList);
		Map<String, BigDecimal> totalMap = priceCalulatorService.total(resultMap);
		priceCalulatorService.display(totalMap);
		LOG.info("main - ENDS");

	}
	
	

}
