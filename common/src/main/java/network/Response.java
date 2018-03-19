package network;

import java.io.Serializable;

public class Response implements Serializable {

    private Object data;
    private StatusCode statusCode;

    public Response() {
        statusCode = StatusCode.ERROR;
    }

    public Response(StatusCode statusCode) {
        this.statusCode = statusCode;
    }

    public Response(StatusCode statusCode, Object data) {
        this.data = data;
        this.statusCode = statusCode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public StatusCode getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(StatusCode statusCode) {
        this.statusCode = statusCode;
    }
}

