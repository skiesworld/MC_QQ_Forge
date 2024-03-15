package com.github.theword;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.ConnectException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Timer;
import java.util.TimerTask;

import static com.github.theword.MCQQ.*;
import static com.github.theword.Utils.parseWebSocketJson;

public class WsClient extends WebSocketClient {
    private int reconnectTimes = 1;
    private final Timer timer = new Timer();

    public WsClient(String websocketUrl) throws URISyntaxException {
        this(new URI(websocketUrl));
    }

    public WsClient(URI uri) {
        super(uri);
        this.addHeader("x-self-name", Utils.unicodeEncode(config.getServerName()));
    }

    /**
     * 连接打开时
     *
     * @param serverHandshake ServerHandshake
     */
    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        LOGGER.info("[MC_QQ] 已成功连接至 %s WebSocket 服务器。".formatted(getURI()));
        reconnectTimes = 1;
        LOGGER.debug("[MC_QQ] %s 的重连次数已重置".formatted(getURI()));
    }

    /**
     * 收到消息时触发
     * 向服务器游戏内公屏发送信息
     */
    @Override
    public void onMessage(String message) {
        if (config.isEnableMcQQ()) {
            try {
                parseWebSocketJson(message);
            } catch (Exception e) {
                LOGGER.error("解析消息时出现错误：" + message);
                e.printStackTrace();
            }
        }
    }

    /**
     * 关闭时
     *
     * @param code   关闭码
     * @param reason 关闭信息
     * @param remote 是否关闭
     */
    @Override
    public void onClose(int code, String reason, boolean remote) {
        if (remote && reconnectTimes <= config.getReconnectMaxTimes()) {
            reconnectWebsocket();
        }
    }

    public void reconnectWebsocket() {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if (config.isEnableReconnectMessage()) {
                    LOGGER.info("[MC_QQ] %s 的 WebSocket 连接已断开,正在第 ".formatted(getURI()) + reconnectTimes + " 次重新连接");
                }
                reconnectTimes++;
                reconnect();
            }
        };
        timer.schedule(timerTask, config.getReconnectInterval() * 1000L);
    }

    public Timer getTimer() {
        return this.timer;
    }

    /**
     * 触发异常时
     *
     * @param exception 所有异常
     */
    @Override
    public void onError(Exception exception) {
        LOGGER.error("[MC_QQ] %s WebSocket 连接出现异常：".formatted(getURI()) + exception.getMessage());
        if (exception instanceof ConnectException && exception.getMessage().equals("Connection refused: connect") && reconnectTimes <= config.getReconnectMaxTimes()) {
            reconnectWebsocket();
        }
    }

    /**
     * 发送消息
     *
     * @param message 消息
     */
    void sendMessage(String message) {
        if (this.isOpen()) {
            this.send(message);
        } else {
            LOGGER.info("[MC_QQ] 发送至 %s 的消息失败，没有连接到 WebSocket 服务器。".formatted(getURI()));
        }
    }
}
