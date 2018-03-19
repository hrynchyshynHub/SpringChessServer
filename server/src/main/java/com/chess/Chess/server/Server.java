package com.chess.Chess.server;

import com.chess.Chess.operation_handler.OperationHandlers;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
public class Server {
    private final static Logger logger = Logger.getLogger(Server.class);
    private final OperationHandlers operationHandlers;

    @Autowired
    public Server(OperationHandlers operationHandlers) {
        this.operationHandlers = operationHandlers;
    }


    public void runServer() {
        try {
            int poolSize = ApplicationProperties.getPoolSize();
            BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<>(poolSize);

            ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                    poolSize,
                    poolSize,
                    ApplicationProperties.getTimeToKeepAlive(),
                    TimeUnit.SECONDS,
                    blockingQueue
            );

            try (ServerSocket listener = new ServerSocket(ApplicationProperties.getPort())) {
                while (!listener.isClosed()) {
                    Socket socket = listener.accept();
                    threadPoolExecutor.execute(() -> operationHandlers.handleOperation(socket));
                }
            }


            threadPoolExecutor.shutdown();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
