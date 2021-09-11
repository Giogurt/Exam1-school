package mx.tec.web.lab.vo;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * Calculation Value Object
 * 
 * @author giogurt
 */
public class CalculationVO {
	/** year of the calculation */
	@Pattern(regexp = "(19|20)[0-9][0-9]", message = "Please use a valid year in the format yyyy")
	@NotBlank(message = "year is Mandatory")
	private int year;
	/** month of the calculation */
	@Pattern(regexp = "(0[1-9]|1[012])", message = "Please use a valid month in the format MM")
	@NotBlank(message = "month is Mandatory")
	private String month;
	/** description of the calculation */
	private String description;
	/** value of the deduction */
	private double deduction;
	/** date the calculation was made */
	private String consultationDate;
	/** employee information */
	@Valid
	private EmployeeVO employee;
	/** number of days worked that month */
	private int daysWorked;
	/** value of integration factor */
	private double integrationFactor;

	/**
	 * Calculation Constructor
	 */
	public CalculationVO() {
	}

	/**
	 * Calculation Constructor with arguments
	 * 
	 * @param year              calculation year
	 * @param month             calculation month
	 * @param deduction         deduction value
	 * @param consultationDate  date the calculation was made
	 * @param employee          employee information
	 * @param daysWorked        number of days worked that month
	 * @param integrationFactor value of the integration factor
	 */
	public CalculationVO(int year, String month, double deduction, String consultationDate, EmployeeVO employee,
			int daysWorked, double integrationFactor) {
		this.year = year;
		this.month = month;
		this.description = "Calcula la deduccion del empleado relacionadas con la cesantia y vejez";
		this.deduction = deduction;
		this.consultationDate = consultationDate;
		this.employee = employee;
		this.daysWorked = daysWorked;
		this.integrationFactor = integrationFactor;
	}

	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * @return the month
	 */
	public String getMonth() {
		return month;
	}

	/**
	 * @param month the month to set
	 */
	public void setMonth(String month) {
		this.month = month;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the deduction
	 */
	public double getDeduction() {
		return deduction;
	}

	/**
	 * @param deduction the deduction to set
	 */
	public void setDeduction(double deduction) {
		this.deduction = deduction;
	}

	/**
	 * @return the consultationDate
	 */
	public String getConsultationDate() {
		return consultationDate;
	}

	/**
	 * @param consultationDate the consultationDate to set
	 */
	public void setConsultationDate(String consultationDate) {
		this.consultationDate = consultationDate;
	}

	/**
	 * @return the employee
	 */
	public EmployeeVO getEmployee() {
		return employee;
	}

	/**
	 * @param employee the employee to set
	 */
	public void setEmployee(EmployeeVO employee) {
		this.employee = employee;
	}

	/**
	 * @return the daysWorked
	 */
	public int getDaysWorked() {
		return daysWorked;
	}

	/**
	 * @param daysWorked the daysWorked to set
	 */
	public void setDaysWorked(int daysWorked) {
		this.daysWorked = daysWorked;
	}

	/**
	 * @return the integrationFactor
	 */
	public double getIntegrationFactor() {
		return integrationFactor;
	}

	/**
	 * @param integrationFactor the integrationFactor to set
	 */
	public void setIntegrationFactor(double integrationFactor) {
		this.integrationFactor = integrationFactor;
	}

	/**
	 * Convert object to string
	 */
	@Override
	public String toString() {
		return "CalculationVO [year=" + year + ", month=" + month + ", description=" + description + ", deduction="
				+ deduction + ", consultationDate=" + consultationDate + ", employee=" + employee + ", daysWorked="
				+ daysWorked + ", integrationFactor=" + integrationFactor + "]";
	}

}
