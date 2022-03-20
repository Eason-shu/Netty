package com.shu.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;

import java.net.InetAddress;
import java.util.List;

/**
 * @Author shu
 * @Version 1.0
 * @Date: 2022/03/07/ 21:19
 * @Description Http Json 请求编码器
 **/
public class HttpJsonRequestEncoder extends AbstractHttpJsonEncoder<HttpJsonRequest> {
    /**
     * 把请求消息封装成Http报文->请求行（request line）、请求头部（header）、空行和请求数据
     * @param ctx
     * @param msg
     * @param out
     * @throws Exception
     */
    @Override
    protected void encode(ChannelHandlerContext ctx, HttpJsonRequest msg, List<Object> out) throws Exception {
        //(1)调用父类的encode0，将业务需要发送的对象转换为Json
        ByteBuf body = encode0(ctx, msg.getBody());
        //(2) 如果业务自定义了HTTP消息头，则使用业务的消息头，否则在这里构造HTTP消息头
        // 这里使用硬编码的方式来写消息头，实际中可以写入配置文件
        FullHttpRequest request = msg.getRequest();
        if (request == null) {
            request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1,
                    HttpMethod.GET, "/do", body);
            HttpHeaders headers = request.headers();
            headers.set(HttpHeaderNames.HOST, InetAddress.getLocalHost()
                    .getHostAddress());
            headers.set(HttpHeaderNames.CONNECTION, HttpHeaders.Values.CLOSE);
            headers.set(HttpHeaderNames.ACCEPT_ENCODING,
                    HttpHeaderValues.GZIP.toString() + ','
                            + HttpHeaderValues.DEFLATE.toString());
            headers.set(HttpHeaderNames.ACCEPT_CHARSET,
                    "ISO-8859-1,utf-8;q=0.7,*;q=0.7");
            headers.set(HttpHeaderNames.ACCEPT_LANGUAGE, "zh");
            headers.set(HttpHeaderNames.USER_AGENT,
                    "Netty json Http Client side");
            headers.set(HttpHeaderNames.ACCEPT,
                    "text/html,application/json;q=0.9,*/*;q=0.8");
        }
        HttpUtil.setContentLength(request, body.readableBytes());
        // (3) 编码后的对象
        out.add(request);
    }


}