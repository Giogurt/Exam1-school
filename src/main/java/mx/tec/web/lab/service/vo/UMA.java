package mx.tec.web.lab.service.vo;

/**
 * UMA class
 * 
 * @author giogurt
 *
 */
public class UMA {
	/** year of the uma */
	private int año;
	/** value of the uma */
	private float valor;

	/**
	 * Constructor no args
	 */
	public UMA() {
	}

	/**
	 * Uma constructor with arguments
	 * 
	 * @param año   año del valor del uma
	 * @param valor valor del uma
	 */
	public UMA(int año, float valor) {
		this.año = año;
		this.valor = valor;
	}

	/**
	 * @return the año
	 */
	public int getAño() {
		return año;
	}

	/**
	 * @param año the año to set
	 */
	public void setAño(int año) {
		this.año = año;
	}

	/**
	 * @return the valor
	 */
	public float getValor() {
		return valor;
	}

	/**
	 * @param valor the valor to set
	 */
	public void setValor(float valor) {
		this.valor = valor;
	}

	/**
	 * Converts the object to string
	 */
	@Override
	public String toString() {
		return "UMA [año=" + año + ", valor=" + valor + "]";
	}

}
