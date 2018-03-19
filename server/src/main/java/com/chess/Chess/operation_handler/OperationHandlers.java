package com.chess.Chess.operation_handler;

import com.chess.Chess.exceptions.HandlerNotFoundException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * Bean that contains all collected by OperationHandlerBeanPostProcessor Handlers.
 * Can invoke Handler by given by user path, if no path find will throw HandlerNotFoundException
 *
 * @see OperationHandlerBeanPostProcessor
 * @see Handler
 */
@Component
public class OperationHandlers {
    private final static Logger logger = Logger.getLogger(OperationHandlerBeanPostProcessor.class);
    private Map<String, Handler> handlers = new HashMap<>();

    public void handleOperation(Socket socket) {
        boolean closeSocket = true;

        try {
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            String path = (String) in.readObject();
            logger.info("Handle request with operation path " + path);

            Handler handler = handlers.get(path);

            if (handler == null) {
                throw new HandlerNotFoundException("No handler for operation path " + path);
            }

            closeSocket = handler.needCloseSocket();
            handler.invoke(socket, in, out);
            out.flush();
        } catch (IOException | ClassNotFoundException | IllegalAccessException | InvocationTargetException e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (closeSocket) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    void addOperationHandler(String path, Handler handler) {
        handlers.put(path, handler);
    }

    boolean containsOperationHandler(String path) {
        return handlers.containsKey(path);
    }
}
