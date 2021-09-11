/**
 * 
 */
package mx.tec.web.lab.service.vo;

/**
 * Integration Factor class
 * 
 * @author giogurt
 *
 */
public class IntegrationFactor {
	/** number of years of service */
	private int añosServicio;
	/** value of the aguinaldo */
	private int aguinaldo;
	/** value of the vacations */
	private int vacaciones;
	/** value of the prima vacacional */
	private int primaVacacional;
	/** factor of integration value */
	private double factor;

	/**
	 * Constructor no args
	 */
	public IntegrationFactor() {
	}

	/**
	 * Constructor with args
	 * 
	 * @param añosServicio    number of years of service
	 * @param aguinaldo       value of the aguinaldo
	 * @param vacaciones      value of vacation days
	 * @param primaVacacional value of the prima vacacional
	 * @param factor          factor of integration value
	 */
	public IntegrationFactor(int añosServicio, int aguinaldo, int vacaciones, int primaVacacional, double factor) {
		this.añosServicio = añosServicio;
		this.aguinaldo = aguinaldo;
		this.vacaciones = vacaciones;
		this.primaVacacional = primaVacacional;
		this.factor = factor;
	}

	/**
	 * @return the añosServicio
	 */
	public int getAñosServicio() {
		return añosServicio;
	}

	/**
	 * @param añosServicio the añosServicio to set
	 */
	public void setAñosServicio(int añosServicio) {
		this.añosServicio = añosServicio;
	}

	/**
	 * @return the aguinaldo
	 */
	public int getAguinaldo() {
		return aguinaldo;
	}

	/**
	 * @param aguinaldo the aguinaldo to set
	 */
	public void setAguinaldo(int aguinaldo) {
		this.aguinaldo = aguinaldo;
	}

	/**
	 * @return the vacaciones
	 */
	public int getVacaciones() {
		return vacaciones;
	}

	/**
	 * @param vacaciones the vacaciones to set
	 */
	public void setVacaciones(int vacaciones) {
		this.vacaciones = vacaciones;
	}

	/**
	 * @return the primaVacacional
	 */
	public int getPrimaVacacional() {
		return primaVacacional;
	}

	/**
	 * @param primaVacacional the primaVacacional to set
	 */
	public void setPrimaVacacional(int primaVacacional) {
		this.primaVacacional = primaVacacional;
	}

	/**
	 * @return the factor
	 */
	public double getFactor() {
		return factor;
	}

	/**
	 * @param factor the factor to set
	 */
	public void setFactor(double factor) {
		this.factor = factor;
	}

	/**
	 * Converts the object to string
	 */
	@Override
	public String toString() {
		return "IntegrationFactor [añosServicio=" + añosServicio + ", factor=" + factor + "]";
	}

}
