package com.shu.codec;

import io.netty.handler.codec.http.FullHttpRequest;

/**
 * @Author shu
 * @Version 1.0
 * @Date: 2022/03/07/ 21:02
 * @Description Http Json 请求
 **/
public class HttpJsonRequest {
    // 完整的Http请求
    private FullHttpRequest request;
    // 消息体
    private Object body;

    public HttpJsonRequest(FullHttpRequest request, Object body) {
        this.request = request;
        this.body = body;
    }

    /**
     * @return the request
     */
    public final FullHttpRequest getRequest() {
        return request;
    }

    /**
     * @param request the request to set
     */
    public final void setRequest(FullHttpRequest request) {
        this.request = request;
    }

    /**
     * @return the object
     */
    public final Object getBody() {
        return body;
    }

    /**
     * @param object the object to set
     */
    public final void setBody(Object body) {
        this.body = body;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "HttpJsonRequest [request=" + request + ", body =" + body + "]";
    }
}
