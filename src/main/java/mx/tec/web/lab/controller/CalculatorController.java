package mx.tec.web.lab.controller;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.tec.web.lab.manager.CalculatorManager;
import mx.tec.web.lab.vo.CalculationVO;
/**
 * Calculation Controller Class
 * @author giogurt
 *
 */
@RestController
@RequestMapping("/calc/api/v1/")
@Validated
public class CalculatorController {
	private static final Logger LOG = LoggerFactory.getLogger(CalculatorController.class);
	
	/**
	 * A reference to the Calculator Manager
	 */
	@Resource
	private CalculatorManager calculatorManager;

	/**
	 * The end point for GET {url}/deduction/old-age
	 * 
	 * @return a json list of all the calculations
	 */
	@GetMapping("/deduction/old")
	public ResponseEntity<List<CalculationVO>> getProducts() {
		LOG.info("Getting all the calculations");
		List<CalculationVO> products = calculatorManager.getCalculations();
		return new ResponseEntity<>(products, HttpStatus.OK);
	}
	
	/**
	 * The end point for POST {url}/deduction/old-age
	 * @param newCalculation JSON containing the info for the new calculation
	 * @return if the product is created succesfully then status 201 and the calculation info
	 */
	@PostMapping("/deduction/old-age")
	public ResponseEntity<CalculationVO> calculateEmployeeOldAgeDeduction(@RequestBody CalculationVO newCalculation) {
		LOG.info("doing the calculation {}", newCalculation);
		ResponseEntity<CalculationVO> responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		Optional<CalculationVO> calculation = calculatorManager.calculateEmployeeOldAgeDeduction(newCalculation);
		
		if (calculation.isPresent()) {
			responseEntity = new ResponseEntity<>(calculation.get(), HttpStatus.CREATED);
		} else {
			LOG.warn("The calculation wasnt made {}", newCalculation);
		}
		return responseEntity;
	}
}
