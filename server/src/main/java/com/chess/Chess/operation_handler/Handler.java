package com.chess.Chess.operation_handler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class that contains information about methods that annotated with OperationHandler annotation.
 * Use for handling client request
 *
 * @see OperationHandler
 */
class Handler {
    private OperationHandler operationHandler;
    private Method method;
    private Object holder;

    Handler(OperationHandler operationHandler, Method method, Object holder) {
        this.operationHandler = operationHandler;
        this.method = method;
        this.holder = holder;
    }

    void invoke(Socket socket, ObjectInputStream ois, ObjectOutputStream oos)
            throws InvocationTargetException, IllegalAccessException, IOException, ClassNotFoundException {
        Object[] socketArgs = (Object[]) ois.readObject();
        List<Object> socketArgsList = new ArrayList<>(Arrays.asList(socketArgs));

        Object[] args = Arrays.stream(method.getParameters()).map(parameter -> {
            Class<?> parameterType = parameter.getType();

            if (parameterType.equals(Socket.class)) {
                return socket;
            } else if (parameterType.equals(ObjectInputStream.class)) {
                return ois;
            } else if (parameterType.equals(ObjectOutputStream.class)) {
                return oos;
            }

            return socketArgsList.remove(0);
        }).toArray();


        Object response = method.invoke(holder, args);

        if (!method.getReturnType().equals(Void.TYPE)) {
            oos.writeObject(response);
        }
    }

    boolean needCloseSocket() {
        return operationHandler.closeConnection();
    }
}
