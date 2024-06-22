package com.github.theword.mcqq.handleMessage;

import com.github.theword.mcqq.returnBody.returnModle.MyBaseComponent;
import com.github.theword.mcqq.returnBody.returnModle.MyTextComponent;
import com.github.theword.mcqq.returnBody.returnModle.SendTitle;
import com.github.theword.mcqq.utils.ParseJsonToEvent;
import com.github.theword.mcqq.utils.Tool;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SChatPacket;
import net.minecraft.network.play.server.STitlePacket;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.StringTextComponent;
import org.java_websocket.WebSocket;

import java.util.List;
import java.util.UUID;

import static com.github.theword.mcqq.MCQQ.minecraftServer;

public class HandleApiService implements HandleApi {
    private final ParseJsonToEvent parseJsonToEvent = new ParseJsonToEvent();

    /**
     * 广播消息
     *
     * @param webSocket   websocket
     * @param messageList 消息体
     */
    @Override
    public void handleBroadcastMessage(WebSocket webSocket, List<MyTextComponent> messageList) {
        StringTextComponent textComponent = parseJsonToEvent.parsePerMessageToMultiText(Tool.getPrefixComponent());
        textComponent.append(parseJsonToEvent.parseMessages(messageList));
        UUID uuid = UUID.randomUUID();
        for (ServerPlayerEntity serverPlayer : minecraftServer.getPlayerList().getPlayers()) {
            serverPlayer.sendMessage(textComponent, uuid);
        }
    }

    /**
     * 广播 Send Title 消息
     *
     * @param webSocket websocket
     * @param sendTitle Send Title 消息体
     */
    @Override
    public void handleSendTitleMessage(WebSocket webSocket, SendTitle sendTitle) {
        sendPacket(new STitlePacket(STitlePacket.Type.TITLE, parseJsonToEvent.parseMessages(sendTitle.getTitle())));
        if (sendTitle.getSubtitle() != null)
            sendPacket(new STitlePacket(STitlePacket.Type.SUBTITLE, parseJsonToEvent.parseMessages(sendTitle.getSubtitle())));
        sendPacket(new STitlePacket(sendTitle.getFadein(), sendTitle.getStay(), sendTitle.getFadeout()));
    }

    /**
     * 广播 Action Bar 消息
     *
     * @param webSocket   websocket
     * @param messageList Action Bar 消息体
     */
    @Override
    public void handleActionBarMessage(WebSocket webSocket, List<MyBaseComponent> messageList) {
        sendPacket(new SChatPacket(parseJsonToEvent.parseMessages(messageList), ChatType.GAME_INFO, UUID.randomUUID()));
    }

    private void sendPacket(IPacket<?> packet) {
        for (ServerPlayerEntity serverPlayer : minecraftServer.getPlayerList().getPlayers()) {
            serverPlayer.connection.send(packet);
        }
    }
}
