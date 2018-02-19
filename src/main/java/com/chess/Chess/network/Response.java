package com.chess.Chess.network;

import java.io.Serializable;

public class Response implements Serializable {
    private Object data;
    private RequestCode requestCode;

    public Response() {
        requestCode = RequestCode.ERROR;
    }

    public Response(RequestCode requestCode) {
        this.requestCode = requestCode;
    }

    public Response(RequestCode requestCode, Object data) {
        this.data = data;
        this.requestCode = requestCode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public RequestCode getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(RequestCode requestCode) {
        this.requestCode = requestCode;
    }
}

