package com.github.theword.mcqq.handleMessage;

import com.github.theword.mcqq.returnBody.ActionbarReturnBody;
import com.github.theword.mcqq.returnBody.MessageReturnBody;
import com.github.theword.mcqq.returnBody.SendTitleReturnBody;
import com.github.theword.mcqq.returnBody.returnModle.MyBaseComponent;
import com.github.theword.mcqq.returnBody.returnModle.SendTitle;
import com.github.theword.mcqq.utils.ParseJsonToEvent;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.LiteralContents;
import net.minecraft.network.protocol.game.ClientboundSetActionBarTextPacket;
import net.minecraft.network.protocol.game.ClientboundSetSubtitleTextPacket;
import net.minecraft.network.protocol.game.ClientboundSetTitleTextPacket;
import net.minecraft.network.protocol.game.ClientboundSetTitlesAnimationPacket;
import net.minecraft.server.level.ServerPlayer;

import java.util.List;

import static com.github.theword.mcqq.MCQQ.minecraftServer;
import static com.github.theword.mcqq.utils.Tool.logger;

public class HandleApiService implements HandleApi {
    private final ParseJsonToEvent parseJsonToEvent = new ParseJsonToEvent();

    /**
     * 广播消息
     *
     * @param messageReturnBody 消息体
     */
    @Override
    public void handleBroadcastMessage(MessageReturnBody messageReturnBody) {
        MutableComponent result = parseJsonToEvent.parseMessages(messageReturnBody.getMessageList());
        for (ServerPlayer serverPlayer : minecraftServer.getPlayerList().getPlayers()) {
            serverPlayer.sendSystemMessage(result);
        }
    }

    /**
     * 广播 Send Title 消息
     *
     * @param sendTitleReturnBody Send Title 消息体
     */
    @Override
    public void handleSendTitleMessage(SendTitleReturnBody sendTitleReturnBody) {
        SendTitle sendTitle = sendTitleReturnBody.getSendTitle();
        for (ServerPlayer serverPlayer : minecraftServer.getPlayerList().getPlayers()) {
            serverPlayer.connection.send(new ClientboundSetTitleTextPacket(MutableComponent.create(new LiteralContents(sendTitle.getTitle()))));
            if (sendTitle.getSubtitle() != null)
                serverPlayer.connection.send(new ClientboundSetSubtitleTextPacket(MutableComponent.create(new LiteralContents(sendTitle.getSubtitle()))));
            serverPlayer.connection.send(new ClientboundSetTitlesAnimationPacket(sendTitle.getFadein(), sendTitle.getStay(), sendTitle.getFadeout()));
        }
    }

    /**
     * 广播 Action Bar 消息
     *
     * @param actionbarReturnBody Action Bar 消息体
     */
    @Override
    public void handleActionBarMessage(ActionbarReturnBody actionbarReturnBody) {
        List<MyBaseComponent> messageList = actionbarReturnBody.getMessageList();
        MutableComponent mutableComponent = parseJsonToEvent.parseMessages(messageList);
        for (ServerPlayer serverPlayer : minecraftServer.getPlayerList().getPlayers()) {
            serverPlayer.connection.send(new ClientboundSetActionBarTextPacket(mutableComponent));
        }
    }
}
