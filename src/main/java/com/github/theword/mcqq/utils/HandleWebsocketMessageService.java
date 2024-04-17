package com.github.theword.mcqq.utils;

import com.github.theword.mcqq.constant.WebsocketConstantMessage;
import com.github.theword.mcqq.returnBody.BaseReturnBody;
import com.github.theword.mcqq.returnBody.MessageReturnBody;
import com.github.theword.mcqq.utils.HandleWebsocketMessage;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerPlayer;

import static com.github.theword.mcqq.MCQQ.minecraftServer;
import static com.github.theword.mcqq.utils.Tool.logger;

public class HandleWebsocketMessageService implements HandleWebsocketMessage {

    private final ParseJsonToEvent parseJsonToEvent = new ParseJsonToEvent();

    public void handleWebSocketJson(String message) {
        Gson gson = new Gson();
        BaseReturnBody baseReturnBody = gson.fromJson(message, BaseReturnBody.class);
        JsonElement data = baseReturnBody.getData();
        switch (baseReturnBody.getApi()) {
            case "broadcast":
                MessageReturnBody messageList = gson.fromJson(data, MessageReturnBody.class);
                MutableComponent result = parseJsonToEvent.parseMessages(messageList.getMessageList());
                for (ServerPlayer serverPlayer : minecraftServer.getPlayerList().getPlayers()) {
                    serverPlayer.sendSystemMessage(result);
                }
                break;
            default:
                logger.warn(WebsocketConstantMessage.WEBSOCKET_UNKNOWN_API + baseReturnBody.getApi());
                break;
        }
    }

}
