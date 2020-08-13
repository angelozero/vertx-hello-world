package cit.vertx.helloworld;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;

// E aqui começa tudo ---> "extends AbstractVerticle"
public class PrincipalVerticle extends AbstractVerticle {

	// Fazemos o deploy para iniciar o Verticle
	public static void main(String[] args) {
		System.out.println("Començando ...\n");
		Vertx vertx = Vertx.vertx();
		vertx.deployVerticle(new PrincipalVerticle());
	}
	
	
	// Metodo START que é chamado quando o verticle é "deeployado"
	@Override
	public void start() throws Exception {
		System.out.println("Método start() invocado ...\n");
		
		vertx.createHttpServer().requestHandler(req -> {
			
			System.out.println("Link http://localhost:8080/ foi chamado");
			
			req.response().putHeader("content-type", "application/json").end("Fala rapaziada 2 !");
		}).listen(8080);

		System.out.println("Podemos começar !");
		
		
		
	}

}
