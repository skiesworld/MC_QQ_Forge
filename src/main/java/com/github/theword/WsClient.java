package com.github.theword;

import com.github.theword.constant.WebsocketConstantMessage;
import com.github.theword.utils.HandleWebsocketMessage;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.ConnectException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Timer;
import java.util.TimerTask;

import static com.github.theword.MCQQ.LOGGER;
import static com.github.theword.MCQQ.config;
import static com.github.theword.utils.Tool.unicodeEncode;

public class WsClient extends WebSocketClient {
    private int reconnectTimes = 1;
    private final Timer timer = new Timer();

    public WsClient(String websocketUrl) throws URISyntaxException {
        this(new URI(websocketUrl));
    }

    public WsClient(URI uri) {
        super(uri);
        addHeader("x-self-name", unicodeEncode(config.getServerName()));
    }

    /**
     * 连接打开时
     *
     * @param serverHandshake ServerHandshake
     */
    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        LOGGER.info(String.format(WebsocketConstantMessage.WEBSOCKET_ON_OPEN, getURI()));
        reconnectTimes = 1;
    }

    /**
     * 收到消息时触发
     * 向服务器游戏内公屏发送信息
     */
    @Override
    public void onMessage(String message) {
        if (config.isEnableMcQQ()) {
            try {
                new HandleWebsocketMessage().handleWebSocketJson(message);
            } catch (Exception e) {
                LOGGER.warn(String.format(WebsocketConstantMessage.WEBSOCKET_ERROR_ON_MESSAGE, getURI()));
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
            if (reconnectTimes == config.getReconnectMaxTimes()) {
                LOGGER.info(String.format(WebsocketConstantMessage.WEBSOCKET_RECONNECT_TIMES_REACH, getURI()));
            }
            reconnectWebsocket();
        }
    }

    public void reconnectWebsocket() {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                reconnect();
            }
        };
        timer.schedule(timerTask, config.getReconnectInterval() * 1000L);
    }

    @Override
    public void reconnect() {
        if (config.isEnableReconnectMessage()) {
            LOGGER.info(String.format(WebsocketConstantMessage.WEBSOCKET_RECONNECT_MESSAGE, getURI(), reconnectTimes));
        }
        reconnectTimes++;
        super.reconnect();
    }

    /**
     * 触发异常时
     *
     * @param exception 所有异常
     */
    @Override
    public void onError(Exception exception) {
        LOGGER.warn(String.format(WebsocketConstantMessage.WEBSOCKET_ON_ERROR, getURI(), exception.getMessage()));
        if (exception instanceof ConnectException && exception.getMessage().equals("Connection refused: connect") && reconnectTimes <= config.getReconnectMaxTimes()) {
            if (reconnectTimes == config.getReconnectMaxTimes()) {
                LOGGER.info(String.format(WebsocketConstantMessage.WEBSOCKET_RECONNECT_TIMES_REACH, getURI()));
            }
            reconnectWebsocket();
        }
    }

    public Timer getTimer() {
        return timer;
    }
}
