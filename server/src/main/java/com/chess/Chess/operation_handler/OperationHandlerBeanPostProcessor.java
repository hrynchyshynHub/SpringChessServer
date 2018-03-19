package com.chess.Chess.operation_handler;

import network.OperationType;
import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

@Component
public class OperationHandlerBeanPostProcessor implements BeanPostProcessor {

    private class Handler {
        private OperationHandler operationHandler;
        private Method method;
        private Object holder;

        public Handler(OperationHandler operationHandler, Method method, Object holder) {
            this.operationHandler = operationHandler;
            this.method = method;
            this.holder = holder;
        }

        void invoke(ObjectInputStream ois, ObjectOutputStream oos)
                throws InvocationTargetException, IllegalAccessException {
            method.invoke(holder, ois, oos);
        }

        boolean needCloseSocket() {
            return operationHandler.closeConnection();
        }
    }

    private final static Logger logger = Logger.getLogger(OperationHandlerBeanPostProcessor.class);
    private Map<OperationType, Handler> handlers = new HashMap<>();

    public void handleOperation(Socket socket) {
        boolean closeSocket = false;

        try {
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            OperationType operationType = (OperationType) in.readObject();
            logger.info("Handle request with operation type " + operationType.toString());

            Handler handler = handlers.get(operationType);

            if (handler == null) {
                throw new RuntimeException("No handler for operation type " + operationType.name());
            }

            closeSocket = handler.needCloseSocket();
            handler.invoke(in, out);
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

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Method[] methods = bean.getClass().getDeclaredMethods();

        for (Method method : methods) {
            OperationHandler operationHandler = method.getAnnotation(OperationHandler.class);

            if (operationHandler != null) {
                OperationType operationType = operationHandler.operationType();

                if (handlers.containsKey(operationType)) {
                    throw new RuntimeException("Two handlers for one operation type " + operationType.name());
                }

                handlers.put(operationType, new Handler(operationHandler, method, bean));
            }
        }

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
