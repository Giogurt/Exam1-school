package mx.tec.web.lab.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import mx.tec.web.lab.service.vo.IntegrationFactor;
import mx.tec.web.lab.service.vo.UMA;

/**
 * Class to access the chambasoft service
 * 
 * @author giogurt
 *
 */
@Component
public class ChambaSoftService {

	/** Web Client */
	private WebClient webClient;

	/** Rest end point */
	private String endpoint;

	/**
	 * Chambasoft constructor
	 * @param endpoint the endpoint where the service is located
	 */
	public ChambaSoftService(@Value("${chambasoftEndpoint}") String endpoint) {
		this.endpoint = endpoint;
		this.webClient = WebClient.create(endpoint);
	}

	/**
	 * Retrieve UMA information of a year
	 * 
	 * @param año year of the uma
	 * @return the information of the uma
	 */
	public UMA getUMA(int año) {
		return webClient.get().uri("util/uma/" + año).accept(MediaType.APPLICATION_JSON).retrieve()
				.bodyToMono(UMA.class).block();
	}

	/**
	 * Retrieve the integration factor of a year
	 * 
	 * @param año year of the integration factor
	 * @return the information of the integration factor
	 */
	public IntegrationFactor getIntegrationFactor(int año) {
		return webClient.get().uri("util/factor-integracion/" + año).accept(MediaType.APPLICATION_JSON).retrieve()
				.bodyToMono(IntegrationFactor.class).block();
	}

	/**
	 * @return the end point
	 */
	public String getEndpoint() {
		return endpoint;
	}

	/**
	 * @param endpoint the end point to set
	 */
	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

}
