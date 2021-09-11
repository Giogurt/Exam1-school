/**
 * 
 */
package mx.tec.web.lab.vo;

import java.util.Optional;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * Employee Value Object
 * 
 * @author giogurt
 *
 */
public class EmployeeVO {
	/** Date of the employee incorporation */
	@NotBlank(message = "incorporationDate is Mandatory")
	@Pattern(regexp = "(19|20)[0-9][0-9]-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])", message = "Please use a valid date in the format yyyy-MM-dd")
	private String incorporationDate;
	/** Date of the employee termination */
	@Pattern(regexp = "(19|20)[0-9][0-9]-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])", message = "Please use a valid date in the format yyyy-MM-dd")
	private Optional<String> terminationDate;
	/** social security number of the employee */
	@Pattern(regexp = "[0-9]{11,11}", message = "Please enter a valid SSN with format XXXXXXXXXX")
	@NotBlank(message = "ssn is Mandatory")
	private String ssn;
	/** name of the employee */
	@NotBlank(message = "name is Mandatory")
	private String name;
	/** daily salary of the employee */
	@Min(value = 1, message = "Please insert a salary of 1 or more")
	@NotBlank(message = "daily salary is Mandatory")
	private float dailySalary;

	/**
	 * Employee constructor
	 */
	public EmployeeVO() {
	}

	/**
	 * Constructor termination date
	 * 
	 * @param incorporationDate Date when the employee joined the company
	 * @param terminationDate   Date when the employee left the company
	 * @param ssn               Social security number of the employee
	 * @param name              Complete name of the employee
	 * @param dailySalary       Daily salary of the employee
	 */
	public EmployeeVO(String incorporationDate, Optional<String> terminationDate, String ssn, String name,
			float dailySalary) {
		this.incorporationDate = incorporationDate;
		this.terminationDate = terminationDate;
		this.ssn = ssn;
		this.name = name;
		this.dailySalary = dailySalary;
	}

	/**
	 * Constructor without termination date
	 * 
	 * @param incorporationDate Date when the employee joined the company
	 * @param ssn               Social security number of the employee
	 * @param name              Complete name of the employee
	 * @param dailySalary       Daily salary of the employee
	 */
	public EmployeeVO(String incorporationDate, String ssn, String name, float dailySalary) {
		this.incorporationDate = incorporationDate;
		this.terminationDate = Optional.empty();
		this.ssn = ssn;
		this.name = name;
		this.dailySalary = dailySalary;
	}

	/**
	 * @return the incorporationDate
	 */
	public String getIncorporationDate() {
		return incorporationDate;
	}

	/**
	 * @param incorporationDate the incorporationDate to set
	 */
	public void setIncorporationDate(String incorporationDate) {
		this.incorporationDate = incorporationDate;
	}

	/**
	 * @return the terminationDate
	 */
	public Optional<String> getTerminationDate() {
		if (terminationDate == null) {
			return Optional.empty();
		} else {
			return terminationDate;
		}
	}

	/**
	 * @param terminationDate the terminationDate to set
	 */
	public void setTerminationDate(Optional<String> terminationDate) {
		this.terminationDate = terminationDate;
	}

	/**
	 * @return the ssn
	 */
	public String getSsn() {
		return ssn;
	}

	/**
	 * @param ssn the ssn to set
	 */
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the dailySalary
	 */
	public float getDailySalary() {
		return dailySalary;
	}

	/**
	 * @param dailySalary the dailySalary to set
	 */
	public void setDailySalary(float dailySalary) {
		this.dailySalary = dailySalary;
	}

	/**
	 * Convert object to string
	 */
	@Override
	public String toString() {
		return "EmployeeVO [incorporationDate=" + incorporationDate + ", terminationDate=" + terminationDate + ", ssn="
				+ ssn + ", name=" + name + ", dailySalary=" + dailySalary + "]";
	}

}
