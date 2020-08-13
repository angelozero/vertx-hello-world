package cit.vertx.worker;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;

// E aqui começa tudo ---> "extends AbstractVerticle"
public class WorkerVerticle extends AbstractVerticle {

	@Override
	public void start() throws Exception {

		EventBus eb = vertx.eventBus();

		vertx.setPeriodic(7000, h -> {
			try {
				System.out.println("Verticle Worker dormindo zzz .... ");
				Thread.sleep(5000);
				eb.publish("worker", "Ola sou o Verticle Worker, um verticle bloqueante");
				System.out.println("Acordei !");
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

	}

}
