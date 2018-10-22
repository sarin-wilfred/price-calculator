/**
 * 
 */
package com.au.anz.pricecalc;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.au.anz.pricecalc.constants.PriceCalculatorConstants;
import com.au.anz.pricecalc.serviceimpls.PriceCalculatorServiceImpl;

/**
 * @author Sarin
 *
 */
public class PriceCalculatorServiceImplTest {
	
    @Test
	public void populateTest1() {
        String input = "5.5";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
    	PriceCalculatorServiceImpl PriceCalculatorServiceImpl = new PriceCalculatorServiceImpl();
    	Assert.assertEquals(Arrays.asList(new BigDecimal(input)), PriceCalculatorServiceImpl.populate());
	}
    
    @Test
	public void populateTest2() {
        String input = "1.1\n2.2\n3.3";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
    	PriceCalculatorServiceImpl PriceCalculatorServiceImpl = new PriceCalculatorServiceImpl();
    	Assert.assertEquals(Arrays.asList(new BigDecimal("1.1"), new BigDecimal("2.2"), new BigDecimal("3.3")), PriceCalculatorServiceImpl.populate());
	}
    
    @Test
	public void populateTest3() {
        String input = "7.4\n3.1";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
    	PriceCalculatorServiceImpl PriceCalculatorServiceImpl = new PriceCalculatorServiceImpl();
    	Assert.assertEquals(Arrays.asList(new BigDecimal("7.4"), new BigDecimal("3.1")), PriceCalculatorServiceImpl.populate());
	}
	
    @Test
	public void calculateTest1() {
    	List<BigDecimal> priceList = Arrays.asList(new BigDecimal("1.1"), new BigDecimal("6.1"), new BigDecimal("3.1"));
    	Map<String, List<BigDecimal>> expectedResultMap = new HashMap<>();
    	expectedResultMap.put(PriceCalculatorConstants.PROFITS, Arrays.asList(new BigDecimal("5.00")));
    	expectedResultMap.put(PriceCalculatorConstants.LOSSES, Arrays.asList(new BigDecimal("-3.00")));
		PriceCalculatorServiceImpl PriceCalculatorServiceImpl = new PriceCalculatorServiceImpl();
		Map<String, List<BigDecimal>> actualResultMap = PriceCalculatorServiceImpl.calculate(priceList);
		Assert.assertEquals(expectedResultMap.get(PriceCalculatorConstants.PROFITS), actualResultMap.get(PriceCalculatorConstants.PROFITS));
		Assert.assertEquals(expectedResultMap.get(PriceCalculatorConstants.LOSSES), actualResultMap.get(PriceCalculatorConstants.LOSSES));
	}
    
    @Test
	public void calculateTest2() {
    	List<BigDecimal> priceList = Arrays.asList(new BigDecimal("10.1"), new BigDecimal("2.1"), new BigDecimal("5.1"), new BigDecimal("4.1"));
    	Map<String, List<BigDecimal>> expectedResultMap = new HashMap<>();
    	expectedResultMap.put(PriceCalculatorConstants.PROFITS, Arrays.asList(new BigDecimal("3.00")));
    	expectedResultMap.put(PriceCalculatorConstants.LOSSES, Arrays.asList(new BigDecimal("-8.00"), new BigDecimal("-1.00")));
		PriceCalculatorServiceImpl PriceCalculatorServiceImpl = new PriceCalculatorServiceImpl();
		Map<String, List<BigDecimal>> actualResultMap = PriceCalculatorServiceImpl.calculate(priceList);
		Assert.assertEquals(expectedResultMap.get(PriceCalculatorConstants.PROFITS), actualResultMap.get(PriceCalculatorConstants.PROFITS));
		Assert.assertEquals(expectedResultMap.get(PriceCalculatorConstants.LOSSES), actualResultMap.get(PriceCalculatorConstants.LOSSES));
	}
    
    @Test
	public void calculateTest3() {
    	List<BigDecimal> priceList = Arrays.asList(new BigDecimal("5.1"), new BigDecimal("10.1"), new BigDecimal("5.1"), new BigDecimal("9.1"));
    	Map<String, List<BigDecimal>> expectedResultMap = new HashMap<>();
    	expectedResultMap.put(PriceCalculatorConstants.PROFITS, Arrays.asList(new BigDecimal("5.00"), new BigDecimal("4.00")));
    	expectedResultMap.put(PriceCalculatorConstants.LOSSES, Arrays.asList(new BigDecimal("-5.00")));
		PriceCalculatorServiceImpl PriceCalculatorServiceImpl = new PriceCalculatorServiceImpl();
		Map<String, List<BigDecimal>> actualResultMap = PriceCalculatorServiceImpl.calculate(priceList);
		Assert.assertEquals(expectedResultMap.get(PriceCalculatorConstants.PROFITS), actualResultMap.get(PriceCalculatorConstants.PROFITS));
		Assert.assertEquals(expectedResultMap.get(PriceCalculatorConstants.LOSSES), actualResultMap.get(PriceCalculatorConstants.LOSSES));
	}
	
	@Test
	public void totalTest1() {
		Map<String, List<BigDecimal>> inputResultMap = new HashMap<>();
		inputResultMap.put(PriceCalculatorConstants.PROFITS, Arrays.asList(new BigDecimal("1.00"), new BigDecimal("5.00")));
		inputResultMap.put(PriceCalculatorConstants.LOSSES, Arrays.asList(new BigDecimal("-6.00")));
		Map<String, BigDecimal> expectedResultMap = new HashMap<>();
    	expectedResultMap.put(PriceCalculatorConstants.PROFITS, new BigDecimal("6.00"));
    	expectedResultMap.put(PriceCalculatorConstants.LOSSES, new BigDecimal("-6.00"));
		PriceCalculatorServiceImpl PriceCalculatorServiceImpl = new PriceCalculatorServiceImpl();
		Map<String, BigDecimal> actualResultMap = PriceCalculatorServiceImpl.total(inputResultMap);
		Assert.assertEquals(expectedResultMap.get(PriceCalculatorConstants.PROFITS), actualResultMap.get(PriceCalculatorConstants.PROFITS));
		Assert.assertEquals(expectedResultMap.get(PriceCalculatorConstants.LOSSES), actualResultMap.get(PriceCalculatorConstants.LOSSES));
	}
	
	@Test
	public void totalTest2() {
		Map<String, List<BigDecimal>> inputResultMap = new HashMap<>();
		inputResultMap.put(PriceCalculatorConstants.PROFITS, Arrays.asList(new BigDecimal("3.00")));
		inputResultMap.put(PriceCalculatorConstants.LOSSES, Arrays.asList(new BigDecimal("-2.00"), new BigDecimal("-7.70")));
		Map<String, BigDecimal> expectedResultMap = new HashMap<>();
    	expectedResultMap.put(PriceCalculatorConstants.PROFITS, new BigDecimal("3.00"));
    	expectedResultMap.put(PriceCalculatorConstants.LOSSES, new BigDecimal("-9.70"));
		PriceCalculatorServiceImpl PriceCalculatorServiceImpl = new PriceCalculatorServiceImpl();
		Map<String, BigDecimal> actualResultMap = PriceCalculatorServiceImpl.total(inputResultMap);
		Assert.assertEquals(expectedResultMap.get(PriceCalculatorConstants.PROFITS), actualResultMap.get(PriceCalculatorConstants.PROFITS));
		Assert.assertEquals(expectedResultMap.get(PriceCalculatorConstants.LOSSES), actualResultMap.get(PriceCalculatorConstants.LOSSES));
	}
	
	@Test
	public void totalTest3() {
		Map<String, List<BigDecimal>> inputResultMap = new HashMap<>();
		inputResultMap.put(PriceCalculatorConstants.PROFITS, Arrays.asList(new BigDecimal("4.22"), new BigDecimal("6.33")));
		inputResultMap.put(PriceCalculatorConstants.LOSSES, Arrays.asList(new BigDecimal("-2.50"), new BigDecimal("-5.60")));
		Map<String, BigDecimal> expectedResultMap = new HashMap<>();
    	expectedResultMap.put(PriceCalculatorConstants.PROFITS, new BigDecimal("10.55"));
    	expectedResultMap.put(PriceCalculatorConstants.LOSSES, new BigDecimal("-8.10"));
		PriceCalculatorServiceImpl PriceCalculatorServiceImpl = new PriceCalculatorServiceImpl();
		Map<String, BigDecimal> actualResultMap = PriceCalculatorServiceImpl.total(inputResultMap);
		Assert.assertEquals(expectedResultMap.get(PriceCalculatorConstants.PROFITS), actualResultMap.get(PriceCalculatorConstants.PROFITS));
		Assert.assertEquals(expectedResultMap.get(PriceCalculatorConstants.LOSSES), actualResultMap.get(PriceCalculatorConstants.LOSSES));
	}
	

}
