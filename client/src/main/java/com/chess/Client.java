package com.chess;

import com.chess.config.ApplicationProperties;
import com.chess.controller_elements.Util;
import network.Response;
import network.StatusCode;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    private Socket socket = null;
    private ObjectOutputStream out = null;
    private ObjectInputStream in = null;

    private Client(String host, int port) {
        try {
            socket = new Socket(host, port);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream( socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    synchronized public static Client getInstance() {
        return new Client(ApplicationProperties.getHost(), ApplicationProperties.getPort());
        }

    synchronized public Response send(String  path, @NotNull Object... value) {
        try (Socket socket = this.socket) {
            out.writeObject(path);
            out.writeObject(value);
            out.flush();
            return (Response) in.readObject();
        } catch (IOException | ClassNotFoundException | NullPointerException e) {
            e.printStackTrace();
        }

        Util.showConnectionErrorNotice();
        return new Response(StatusCode.CONNECTION_ERROR);
    }
}