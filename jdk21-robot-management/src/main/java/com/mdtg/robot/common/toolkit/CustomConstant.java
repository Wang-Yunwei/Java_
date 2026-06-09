package com.mdtg.robot.common.toolkit;

/**
 * @author WangYunwei [2026-03-18]
 */
public record CustomConstant(int code, String message) {

    public static final CustomConstant SC_CONTINUE = new CustomConstant(100, "客户端应继续发送请求的剩余部分");
    public static final CustomConstant SC_SWITCHING_PROTOCOLS  = new CustomConstant(101, "服务器根据客户端的请求切换协议（如升级到 WebSocket）");

    public static final CustomConstant SC_OK  = new CustomConstant(200, "最常见的状态码。请求成功，服务器返回了请求的数据（GET/POST 成功）");
    public static final CustomConstant SC_CREATED  = new CustomConstant(201, "请求成功并且服务器创建了新的资源（通常用于 POST 创建成功）");
    public static final CustomConstant SC_NO_CONTENT  = new CustomConstant(204, "请求成功，但服务器不需要返回任何响应体（通常用于 DELETE 成功）");
    public static final CustomConstant SC_RESET_CONTENT  = new CustomConstant(205, "类似于 204，但要求客户端重置文档视图（很少用）");

    public static final CustomConstant SC_MULTIPLE_CHOICES  = new CustomConstant(300, "资源已被永久移动到新位置。以后应使用新地址");
    public static final CustomConstant SC_MOVED_PERMANENTLY  = new CustomConstant(301, "资源临时从不同的 URI 响应请求");
    public static final CustomConstant SC_MOVED_TEMPORARILY  = new CustomConstant(302, "资源临时从不同的 URI 响应请求。注意： SC_MOVED_TEMPORARILY 和 SC_FOUND 是同一个值，后者是更规范的名称");
    public static final CustomConstant SC_FOUND  = new CustomConstant(302, "对应当前请求的响应可以在另一个 URI 上找到");
    public static final CustomConstant SC_SEE_OTHER  = new CustomConstant(303, "客户端应继续发送请求的剩余部分");
    public static final CustomConstant SC_NOT_MODIFIED  = new CustomConstant(304, "缓存相关。资源未修改，客户端可以使用缓存的版本");
    public static final CustomConstant SC_USE_PROXY  = new CustomConstant(305, "客户端应继续发送请求的剩余部分");
    public static final CustomConstant SC_TEMPORARY_REDIRECT  = new CustomConstant(307, "客户端应继续发送请求的剩余部分");

    public static final CustomConstant SC_BAD_REQUEST  = new CustomConstant(400, "客户端应继续发送请求的剩余部分");
    public static final CustomConstant SC_UNAUTHORIZED  = new CustomConstant(401, "客户端应继续发送请求的剩余部分");
    public static final CustomConstant SC_PAYMENT_REQUIRED  = new CustomConstant(402, "客户端应继续发送请求的剩余部分");
    public static final CustomConstant SC_FORBIDDEN  = new CustomConstant(403, "客户端应继续发送请求的剩余部分");
    public static final CustomConstant SC_NOT_FOUND  = new CustomConstant(404, "客户端应继续发送请求的剩余部分");
    public static final CustomConstant SC_METHOD_NOT_ALLOWED  = new CustomConstant(405, "客户端应继续发送请求的剩余部分");
    public static final CustomConstant SC_NOT_ACCEPTABLE  = new CustomConstant(406, "客户端应继续发送请求的剩余部分");
    public static final CustomConstant SC_PROXY_AUTHENTICATION_REQUIRED  = new CustomConstant(407, "客户端应继续发送请求的剩余部分");
    public static final CustomConstant SC_REQUEST_TIMEOUT  = new CustomConstant(408, "客户端应继续发送请求的剩余部分");
    public static final CustomConstant SC_CONFLICT  = new CustomConstant(409, "客户端应继续发送请求的剩余部分");
    public static final CustomConstant SC_GONE  = new CustomConstant(410, "客户端应继续发送请求的剩余部分");
    public static final CustomConstant SC_LENGTH_REQUIRED  = new CustomConstant(411, "客户端应继续发送请求的剩余部分");
    public static final CustomConstant SC_PRECONDITION_FAILED  = new CustomConstant(412, "客户端应继续发送请求的剩余部分");
    public static final CustomConstant SC_REQUEST_ENTITY_TOO_LARGE  = new CustomConstant(413, "客户端应继续发送请求的剩余部分");
    public static final CustomConstant SC_REQUEST_URI_TOO_LONG  = new CustomConstant(414, "客户端应继续发送请求的剩余部分");
    public static final CustomConstant SC_UNSUPPORTED_MEDIA_TYPE  = new CustomConstant(415, "客户端应继续发送请求的剩余部分");
    public static final CustomConstant SC_REQUESTED_RANGE_NOT_SATISFIABLE  = new CustomConstant(416, "客户端应继续发送请求的剩余部分");
    public static final CustomConstant SC_EXPECTATION_FAILED  = new CustomConstant(417, "客户端应继续发送请求的剩余部分");

    public static final CustomConstant SC_INTERNAL_SERVER_ERROR  = new CustomConstant(500, "客户端应继续发送请求的剩余部分");
    public static final CustomConstant SC_NOT_IMPLEMENTED  = new CustomConstant(501, "客户端应继续发送请求的剩余部分");
    public static final CustomConstant SC_BAD_GATEWAY  = new CustomConstant(502, "客户端应继续发送请求的剩余部分");
    public static final CustomConstant SC_SERVICE_UNAVAILABLE  = new CustomConstant(503, "客户端应继续发送请求的剩余部分");
    public static final CustomConstant SC_GATEWAY_TIMEOUT  = new CustomConstant(504, "客户端应继续发送请求的剩余部分");
    public static final CustomConstant SC_HTTP_VERSION_NOT_SUPPORTED  = new CustomConstant(505, "客户端应继续发送请求的剩余部分");

}
