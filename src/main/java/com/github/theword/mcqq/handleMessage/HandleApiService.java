package com.github.theword.mcqq.handleMessage;

import com.github.theword.mcqq.MCQQ;
import com.github.theword.mcqq.returnBody.ActionbarReturnBody;
import com.github.theword.mcqq.returnBody.MessageReturnBody;
import com.github.theword.mcqq.returnBody.SendTitleReturnBody;
import com.github.theword.mcqq.utils.ParseJsonToEvent;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.StringTextComponent;

import java.util.UUID;

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
        StringTextComponent result = parseJsonToEvent.parseMessages(messageReturnBody.getMessageList());
        for (ServerPlayerEntity serverPlayer : MCQQ.minecraftServer.getPlayerList().getPlayers()) {
            serverPlayer.sendMessage(result, ChatType.SYSTEM, UUID.randomUUID());
        }
    }

    /**
     * 广播 Send Title 消息
     *
     * @param sendTitleReturnBody Send Title 消息体
     */
    @Override
    public void handleSendTitleMessage(SendTitleReturnBody sendTitleReturnBody) {
        // TODO Send Title Message
        logger.warn("暂未实现 Send Title Message");
    }

    /**
     * 广播 Action Bar 消息
     *
     * @param actionbarReturnBody Action Bar 消息体
     */
    @Override
    public void handleActionBarMessage(ActionbarReturnBody actionbarReturnBody) {
        // TODO Action Bar Message
        logger.warn("暂未实现 Action Bar Message");
    }
}
