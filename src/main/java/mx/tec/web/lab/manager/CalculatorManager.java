package mx.tec.web.lab.manager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import mx.tec.web.lab.service.ChambaSoftService;
import mx.tec.web.lab.service.vo.IntegrationFactor;
import mx.tec.web.lab.service.vo.UMA;
import mx.tec.web.lab.vo.CalculationVO;
import mx.tec.web.lab.vo.EmployeeVO;

/**
 * The Calculation Manager with all the available business operations for the
 * calculator
 * 
 * @author giogurt
 *
 */
@Service
public class CalculatorManager {

	private static final Logger LOG = LoggerFactory.getLogger(CalculatorManager.class);
	private SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * Reference to the chambasoft service
	 */
	@Resource
	ChambaSoftService chambasoftService;

	/**
	 * List of calculations made
	 */
	private List<CalculationVO> calculations;

	/**
	 * Calculator Manager constructor
	 */
	public CalculatorManager() {
		calculations = new ArrayList<>();
	}

	/**
	 * Retrieve all calculations
	 * 
	 * @return the calculations
	 */
	public List<CalculationVO> getCalculations() {
		LOG.debug("getting all the calculations {}", calculations);
		return calculations;
	}

	/**
	 * Makes a calculation based on the inputs received then saves it
	 * 
	 * @param newCalculation the calculation to be made
	 * @return The result of the calculation
	 */
	public Optional<CalculationVO> calculateEmployeeOldAgeDeduction(final CalculationVO newCalculation) {
		LOG.info("making the calculation requested");
		double deduction;
		int year = newCalculation.getYear();
		String month = newCalculation.getMonth();
		EmployeeVO employee = newCalculation.getEmployee();
		String incDate = employee.getIncorporationDate();
		float sd = newCalculation.getEmployee().getDailySalary();
		Optional<String> optionalTerDate = employee.getTerminationDate();
		String ssn = employee.getSsn();
		String name = employee.getName();
		String terDate = "";
		int intYear = 1;
		int workedDays = 0;
		double sbc = 0;
		UMA uma = chambasoftService.getUMA(year);
		LOG.debug("Retrieving the uma info from service {}", uma);
		if (optionalTerDate.isEmpty()) {
			terDate = String.valueOf(year) + "-" + month + "-" + "30";
		} else {
			terDate = optionalTerDate.get();
		}
		try {
			workedDays = calculateDaysWorked(terDate, incDate, TimeUnit.DAYS, year, month);
		} catch (ParseException e1) {
			LOG.warn("The worked days was parsed incorrectly {}", workedDays);
			e1.printStackTrace();
		}
		try {
			intYear = getYearDifference(terDate, incDate, TimeUnit.DAYS) + 1;
		} catch (ParseException e) {
			LOG.warn("The integration factor days was parsed incorrectly {}", intYear);
			e.printStackTrace();
		}
		IntegrationFactor intFactor = chambasoftService.getIntegrationFactor(intYear);
		double factor = intFactor.getFactor();
		LOG.debug("Retrieving the integration factor info from service {}", intFactor);
		sbc = calculateSBC(sd, uma.getValor(), factor);
		deduction = sbc * 0.01125 * workedDays;

		EmployeeVO resultEmployee = new EmployeeVO(incDate, Optional.of(terDate), ssn, name, sd);
		Date consultationDate = new Date();
		CalculationVO result = new CalculationVO(year, month, deduction, dateToString(consultationDate), resultEmployee,
				workedDays, factor);

		calculations.add(result);
		return Optional.of(result);
	}

	/**
	 * Calculates the SBC
	 * 
	 * @param sd        The daily salary
	 * @param umaValue  The value of a single uma
	 * @param intFactor The value of the integration factor
	 * @return The calculated sbc
	 */
	private double calculateSBC(float sd, float umaValue, double intFactor) {
		double sbc = sd * intFactor;
		LOG.debug("sbc por factor es {}", sbc);
		if (sbc > umaValue * 25) {
			return umaValue * 25;
		} else {
			return sbc;
		}
	}

	/**
	 * Converts dates to string
	 * 
	 * @param date date to be converted
	 * @return A string containing the converted date
	 */
	private String dateToString(Date date) {
		return dateFormatter.format(date);
	}

	/**
	 * Calculates the number of days worked in a month
	 * 
	 * @param dateA    First date to be compared
	 * @param dateB    Second date to be compared
	 * @param timeUnit The type of conversion to be made
	 * @param year     The year of the calculation
	 * @param month    The month of the calculation
	 * @return The number of days worked in a month
	 * @throws ParseException Date cannot be converted
	 */
	private int calculateDaysWorked(String dateA, String dateB, TimeUnit timeUnit, int year, String month)
			throws ParseException {

		int monthNum = Integer.parseInt(month);
		String[] sDate = dateA.split("-");
		String[] sDate2 = dateB.split("-");

		if (Integer.parseInt(sDate[0]) < year
				|| (Integer.parseInt(sDate[0]) == year && Integer.parseInt(sDate[1]) < monthNum)) {
			LOG.warn("The termination date was before the consultation year {}", dateA);
			return 0;
		}

		if (Integer.parseInt(sDate2[0]) > year
				|| (Integer.parseInt(sDate2[0]) == year && Integer.parseInt(sDate[1]) > monthNum)) {
			LOG.warn("The incorporation date was before the consultation year {}", dateB);
			return 0;
		}

		int[] daysOfMonth = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

		Date fDateA = dateFormatter.parse(dateA);
		Date fDateB = dateFormatter.parse(dateB);

		long diffInMillies = fDateA.getTime() - fDateB.getTime();
		long diff = timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
		if (diff + 1 > daysOfMonth[monthNum]) {
			return daysOfMonth[monthNum];
		} else {
			return (int) diff + 1;
		}
	}

	/**
	 * Get the year difference between two dates
	 * 
	 * @param dateA    First date to be compared
	 * @param dateB    Second date to be compared
	 * @param timeUnit The type of conversion to be made
	 * @return The year difference between both dates floored
	 * @throws ParseException The string date could not be parsed into a date
	 */
	private int getYearDifference(String dateA, String dateB, TimeUnit timeUnit) throws ParseException {

		Date fDateA = dateFormatter.parse(dateA);
		Date fDateB = dateFormatter.parse(dateB);

		long diffInMillies = fDateA.getTime() - fDateB.getTime();
		long diff = timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);

		double yearDifference = (double)diff / 365;
		LOG.debug("anios servicio {}", yearDifference);

		return (int) yearDifference;

	}
}
