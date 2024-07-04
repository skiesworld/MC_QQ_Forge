package com.github.theword.mcqq.handleMessage;

import com.github.theword.mcqq.returnBody.returnModle.MyBaseComponent;
import com.github.theword.mcqq.returnBody.returnModle.MyTextComponent;
import com.github.theword.mcqq.returnBody.returnModle.SendTitle;
import com.github.theword.mcqq.utils.ParseJsonToEvent;
import com.github.theword.mcqq.utils.Tool;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketChat;
import net.minecraft.network.play.server.SPacketTitle;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.TextComponentString;
import org.java_websocket.WebSocket;

import java.util.List;

import static com.github.theword.mcqq.MCQQ.minecraftServer;

public class HandleApiService implements HandleApi {
    private final ParseJsonToEvent parseJsonToEvent = new ParseJsonToEvent();

    /**
     * 广播消息
     *
     * @param webSocket   websocket
     * @param messageList 消息
     */
    @Override
    public void handleBroadcastMessage(WebSocket webSocket, List<MyTextComponent> messageList) {
        TextComponentString textComponentString = parseJsonToEvent.parsePerMessageToMultiText(Tool.getPrefixComponent());
        textComponentString.appendSibling(parseJsonToEvent.parseMessages(messageList));
        sendPacket(new SPacketChat(textComponentString, ChatType.GAME_INFO));
    }

    /**
     * 广播 Send Title 消息
     *
     * @param webSocket websocket
     * @param sendTitle Send Title 消息
     */
    @Override
    public void handleSendTitleMessage(WebSocket webSocket, SendTitle sendTitle) {
        sendPacket(new SPacketTitle(SPacketTitle.Type.TITLE, parseJsonToEvent.parseMessages(sendTitle.getTitle()), sendTitle.getFadein(), sendTitle.getStay(), sendTitle.getFadeout()));
        if (sendTitle.getSubtitle() != null)
            sendPacket(new SPacketTitle(SPacketTitle.Type.SUBTITLE, parseJsonToEvent.parseMessages(sendTitle.getSubtitle()), sendTitle.getFadein(), sendTitle.getStay(), sendTitle.getFadeout()));
    }

    /**
     * 广播 Action Bar 消息
     *
     * @param webSocket   websocket
     * @param messageList Action Bar 消息
     */
    @Override
    public void handleActionBarMessage(WebSocket webSocket, List<MyBaseComponent> messageList) {
        sendPacket(new SPacketTitle(SPacketTitle.Type.ACTIONBAR, parseJsonToEvent.parseMessages(messageList)));
    }

    private void sendPacket(Packet<?> packet) {
        for (EntityPlayerMP serverPlayer : minecraftServer.getPlayerList().getPlayers()) {
            serverPlayer.connection.sendPacket(packet);
        }
    }
}
