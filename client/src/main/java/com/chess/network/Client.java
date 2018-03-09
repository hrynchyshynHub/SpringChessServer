package com.chess.network;

import com.sun.istack.internal.NotNull;
import com.chess.config.ApplicationProperties;
import com.chess.controller_elements.Util;
import network.OperationType;
import network.RequestCode;
import network.Response;

import java.io.*;
import java.net.Socket;

public class Client {
    private Socket socket = null;
    private ObjectOutputStream out = null;
    private ObjectInputStream in = null;

    private Client(String host, int port) {
        try {
            socket = new Socket(host, port);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    synchronized public static Client getInstance() {
        return new Client(ApplicationProperties.getHost(), ApplicationProperties.getPort());
    }

    synchronized public Response send(OperationType operationType, @NotNull Object... value) {
        try (Socket socket = this.socket) {
            out.writeObject(operationType);
            for (Object obj : value) {
                out.writeObject(obj);
            }
            out.flush();
            return (Response) in.readObject();
        } catch (IOException | ClassNotFoundException | NullPointerException e) {
            e.printStackTrace();
        }

        Util.showConnectionErrorNotice();
        return new Response(RequestCode.CONNECTION_ERROR);
    }
}