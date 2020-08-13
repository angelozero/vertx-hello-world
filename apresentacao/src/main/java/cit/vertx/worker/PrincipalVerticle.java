package cit.vertx.worker;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;

public class PrincipalVerticle extends AbstractVerticle {

	public static void main(String[] args) {
		Vertx vertx = Vertx.vertx();
		vertx.deployVerticle(new PrincipalVerticle());
	}

	@Override
	public void start(Future<Void> startFuture) throws Exception {
		System.out.println("START EventLoop ... \n");

		EventBus eb = vertx.eventBus();

		// Verticle Bloqueante
		vertx.deployVerticle("cit.vertx.worker.WorkerVerticle", new DeploymentOptions().setWorker(true));
		
		eb.consumer("worker", retorno -> {
			System.out.println("Retorno do worker bloqueante ---> " + retorno.body().toString());
		});

		// Verticle 1
		vertx.deployVerticle("cit.vertx.worker.Verticle1", resposta -> {
			if (resposta.succeeded()) {
				System.out.println("[PrincipalVerticle] Sucesso ao iniciar Verticle1\n");
			} else {
				startFuture.fail("[PrincipalVerticle] Falha ao iniciar Verticle1");
				System.out.println(
						"[PrincipalVerticle] Sucesso ao iniciar Verticle1\n" + startFuture.cause().getMessage());
			}
		});

		// Verticle 2
		vertx.deployVerticle("cit.vertx.worker.Verticle2", resposta -> {
			if (resposta.succeeded()) {
				System.out.println("[PrincipalVerticle] Sucesso ao iniciar Verticle2\n");
			} else {
				startFuture.fail("[PrincipalVerticle] Falha ao iniciar Verticle2");
				System.out.println(
						"[PrincipalVerticle] Sucesso ao iniciar Verticle2\n" + startFuture.cause().getMessage());
			}
		});

		// Verticle 3
		vertx.deployVerticle("cit.vertx.worker.Verticle3", resposta -> {
			if (resposta.succeeded()) {
				System.out.println("[PrincipalVerticle] Sucesso ao iniciar Verticle3\n");
			} else {
				startFuture.fail("[PrincipalVerticle] Falha ao iniciar Verticle3");
				System.out.println(
						"[PrincipalVerticle] Sucesso ao iniciar Verticle3\n" + startFuture.cause().getMessage());
			}
		});

		System.out.println("[PrincipalVerticle] Podemos começar !");

	}
}
