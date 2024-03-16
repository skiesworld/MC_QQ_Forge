package com.github.theword.constant;

public class WebsocketConstantMessage extends BaseConstant {
    private static final String CONNECT_TO = PREFIX + "连接至：%s 的 ";
    public static final String WEBSOCKET_RUNNING = PREFIX + "WebSocket Client 正在启动...";
    public static final String WEBSOCKET_CLOSING = CONNECT_TO + "WebSocket Client 正在关闭。";
    public static final String WEBSOCKET_RECONNECT_MESSAGE = CONNECT_TO + "WebSocket 连接已断开，尝试第 %d 次重连...";

    public static final String WEBSOCKET_ON_OPEN = PREFIX + "已成功连接至 %s 的 WebSocket 服务器！";
    public static final String WEBSOCKET_ERROR_ON_MESSAGE = PREFIX + "解析来自 %s 的 WebSocket 消息时出现异常";
    public static final String WEBSOCKET_ON_ERROR = CONNECT_TO + "WebSocket 连接出现异常：%s";

    public static final String WEBSOCKET_IS_NOT_OPEN = CONNECT_TO + "WebSocket 连接未打开";
    public static final String WEBSOCKET_IS_NOT_OPEN_WHEN_SEND_MESSAGE = WEBSOCKET_IS_NOT_OPEN + "，发送消息失败";


    public static final String WEBSOCKET_ERROR_URI_SYNTAX_ERROR = CONNECT_TO + "WebSocket URL 格式错误，无法连接！";

    public static final String WEBSOCKET_RECONNECT_TIMES_REACH = CONNECT_TO + "重连次数达到最大值，已停止重连！";

    public static final String WEBSOCKET_UNKNOWN_API = PREFIX + "未知的API：";
}
