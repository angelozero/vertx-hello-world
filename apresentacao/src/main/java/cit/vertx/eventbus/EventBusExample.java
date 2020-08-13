package cit.vertx.eventbus;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;

/*
 *   P U B L I C A D O R
 * 
 */

public class EventBusExample extends AbstractVerticle {

	@Override
	public void start() throws Exception {

		EventBus eb = vertx.eventBus();

		Cachorro cachorro = new Cachorro("preto", "vira-lata", "grande", "Tobby");
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String jsonCachorro = ow.writeValueAsString(cachorro);
		

		System.out.println("Iniciando EventBus ...");

		vertx.setPeriodic(5000, v -> {
			System.out.println("Enviando um cachorro ...");

			eb.publish("canalDadosCachorro", jsonCachorro);

			System.out.println("Terminei ! Enviei o cachorro !\n");
		});
	}

}
