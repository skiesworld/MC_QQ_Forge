package com.github.theword.mcqq.commands.subCommands.client;

import com.github.theword.mcqq.commands.ForgeSubCommand;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.command.CommandSource;

import static com.github.theword.mcqq.utils.Tool.websocketManager;

public class ReconnectAllCommand extends ReconnectCommandAbstract implements ForgeSubCommand {

    @Override
    public int onCommand(CommandContext<CommandSource> context) {
        websocketManager.reconnectWebsocketClients(true, context);
        return Command.SINGLE_SUCCESS;
    }

}
