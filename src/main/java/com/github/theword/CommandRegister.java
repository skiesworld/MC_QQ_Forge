package com.github.theword;


import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.LiteralContents;

import static com.github.theword.MCQQ.*;
import static com.github.theword.Utils.connectWebsocket;

public class CommandRegister {

    public CommandRegister(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("mcqq").requires(source -> source.hasPermission(2)).then(Commands.literal("reload").executes(context -> {
            config = new Config(true);
            sendResultComponent(context, "[MC_QQ] 配置文件已重载");
            wsClientList.forEach(wsClient -> {
                if (!wsClient.isClosed() && !wsClient.isClosing()) {
                    wsClient.close();
                }
                wsClient.getTimer().cancel();
            });
            wsClientList.clear();
            sendResultComponent(context, "[MC_QQ] 旧链接清理完成");
            config.getWebsocketUrlList().forEach(websocketUrl -> {
                WsClient wsClient = connectWebsocket(websocketUrl);
                if (wsClient == null) {
                    sendResultComponent(context, "[MC_QQ] %s 的连接为空，无法连接".formatted(websocketUrl));
                } else {
                    wsClientList.add(wsClient);
                }
            });
            return sendResultComponent(context, "[MC_QQ] 重载完成");
        })));

        dispatcher.register(Commands.literal("mcqq").requires(source -> source.hasPermission(2)).then(Commands.literal("reconnect").executes(context -> {
            wsClientList.forEach(wsClient -> {
                if (!wsClient.isOpen()) {
                    wsClient.reconnectWebsocket();
                    sendResultComponent(context, "[MC_QQ] %s 的连接已重连".formatted(wsClient.getURI()));
                }
            });
            return sendResultComponent(context, "[MC_QQ] 已将未连接的 Websocket Client 重新连接");
        }).then(Commands.literal("all").executes(context -> {
            wsClientList.forEach(WsClient::reconnectWebsocket);
            return sendResultComponent(context, "[MC_QQ] 已将所有 Websocket Client 重新连接");
        }))));

    }

    int sendResultComponent(CommandContext<CommandSourceStack> context, String text) {
        context.getSource().sendSystemMessage(MutableComponent.create(new LiteralContents(text)));
        return Command.SINGLE_SUCCESS;
    }
}
