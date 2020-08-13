package cit.vertx.eventbus;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;

/*
 *   C O N S U M I D O R
 * 
 */

public class EventBusPrincipal extends AbstractVerticle {

	public static void main(String[] args) {
		Vertx vertx = Vertx.vertx();
		vertx.deployVerticle(new EventBusPrincipal());
		vertx.deployVerticle(new EventBusExample());
	}

	@Override
	public void start() throws Exception {
		EventBus eb = vertx.eventBus();

		eb.consumer("canalDadosCachorro", dadosDoEventBus -> {

			try {
				System.out.println("Consumindo dados do Event Bus ---> " + dadosDoEventBus.body());
				
				ObjectMapper mapper = new ObjectMapper();
				Cachorro cachorro = mapper.readValue(dadosDoEventBus.body().toString(), Cachorro.class);
				
				System.out.println("Json convertido com sucesso ---> "  + cachorro + "\n");

			} catch (IOException e) {
				System.out.println("Erro ao converter JSON para Cachorro: " + e.getMessage() + "\n");
			}
		});
	}
}
