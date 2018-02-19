package com.chess.Chess.server;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
public class Server {
    private final static Logger logger = Logger.getLogger(Server.class);

    @Autowired
    private RequestManager requestManager;

    private class ClientRunnable implements Runnable {
        private Socket clientSocket;

        ClientRunnable(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try (Socket socket = clientSocket) {
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

                OperationType operationType = (OperationType) in.readObject();
                logger.info("Handle request with operation type " + operationType.toString());
                requestManager.handleRequest(operationType).execute(in, out);
                out.flush();
            } catch (IOException | ClassNotFoundException e) {
                logger.error(e.getMessage(),e);
            }
        }
    }

    public void runServer() {
        try {
            ServerSocket listener = new ServerSocket(ApplicationProperties.getPort());
            int poolSize = ApplicationProperties.getPoolSize();

            BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<>(poolSize);

            ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                poolSize,
                poolSize,
                ApplicationProperties.getTimeToKeepAlive(),
                TimeUnit.SECONDS,
                blockingQueue
            );

            while (!listener.isClosed()) {
                Socket socket = listener.accept();
                threadPoolExecutor.execute(new ClientRunnable(socket));
            }

            threadPoolExecutor.shutdown();
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
        }
    }
}
