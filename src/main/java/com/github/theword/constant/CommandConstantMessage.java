package com.github.theword.constant;

public class CommandConstantMessage extends BaseConstant {
    public static final String RELOAD_CONFIG = PREFIX + "配置文件重载完成";
    public static final String RELOAD_CLOSE_WEBSOCKET_CLIENT = PREFIX + "正在关闭连接至：%s 的 Websocket Client";
    public static final String RELOAD_CLEAR_WEBSOCKET_CLIENT_LIST = PREFIX + "旧连接清理完毕";
    public static final String RELOADED = PREFIX + "重载完成";


    public static final String RECONNECT_MESSAGE = PREFIX + "正在尝试重连至：%s 的 WebSocket 服务器...";
    public static final String RECONNECT_NOT_OPEN_CLIENT = PREFIX + "即将重新连接未处于开启状态的 Websocket Client...";
    public static final String RECONNECT_ALL_CLIENT = PREFIX + "即将重新连接所有的 Websocket Client...";
    public static final String RECONNECT_NO_CLIENT_NEED_RECONNECT = PREFIX + "没有需要重连的 Websocket Client";
    public static final String RECONNECTED = PREFIX + "重连完成";
}
