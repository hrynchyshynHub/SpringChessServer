package com.chess.Chess;

import com.chess.Chess.server.Server;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class ChessApplication {

	public static void main(String[] args) {

		new SpringApplicationBuilder(ChessApplication.class)
			.web(false)
			.run(args)
			.getBean(Server.class)
			.runServer();
	}
}
