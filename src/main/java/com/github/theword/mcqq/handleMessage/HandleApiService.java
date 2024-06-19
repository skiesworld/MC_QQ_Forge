package com.github.theword.mcqq.handleMessage;

import com.github.theword.mcqq.returnBody.returnModle.MyBaseComponent;
import com.github.theword.mcqq.returnBody.returnModle.MyTextComponent;
import com.github.theword.mcqq.returnBody.returnModle.SendTitle;
import com.github.theword.mcqq.utils.ParseJsonToEvent;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundSetActionBarTextPacket;
import net.minecraft.network.protocol.game.ClientboundSetSubtitleTextPacket;
import net.minecraft.network.protocol.game.ClientboundSetTitleTextPacket;
import net.minecraft.network.protocol.game.ClientboundSetTitlesAnimationPacket;
import net.minecraft.server.level.ServerPlayer;
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
        MutableComponent result = parseJsonToEvent.parseMessages(messageList);
        for (ServerPlayer serverPlayer : minecraftServer.getPlayerList().getPlayers()) {
            serverPlayer.sendMessage(result, UUID.randomUUID());
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
        sendPacket(new ClientboundSetTitleTextPacket(parseJsonToEvent.parseMessages(sendTitle.getTitle())));
        if (sendTitle.getSubtitle() != null)
            sendPacket(new ClientboundSetSubtitleTextPacket(parseJsonToEvent.parseMessages(sendTitle.getSubtitle())));
        sendPacket(new ClientboundSetTitlesAnimationPacket(sendTitle.getFadein(), sendTitle.getStay(), sendTitle.getFadeout()));
    }

    /**
     * 广播 Action Bar 消息
     *
     * @param webSocket   websocket
     * @param messageList Action Bar 消息体
     */
    @Override
    public void handleActionBarMessage(WebSocket webSocket, List<MyBaseComponent> messageList) {
        sendPacket(new ClientboundSetActionBarTextPacket(parseJsonToEvent.parseMessages(messageList)));
    }

    private void sendPacket(Packet<?> packet) {
        for (ServerPlayer serverPlayer : minecraftServer.getPlayerList().getPlayers()) {
            serverPlayer.connection.send(packet);
        }
    }
}
