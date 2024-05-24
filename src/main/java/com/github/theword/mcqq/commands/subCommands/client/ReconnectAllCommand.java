package com.github.theword.mcqq.commands.subCommands.client;

import com.github.theword.mcqq.commands.ForgeSubCommand;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;

import static com.github.theword.mcqq.utils.Tool.websocketManager;

public class ReconnectAllCommand extends ReconnectCommandAbstract implements ForgeSubCommand {

    @Override
    public int onCommand(CommandContext<CommandSourceStack> context) {
        websocketManager.reconnectWebsocketClients(true, context);
        return Command.SINGLE_SUCCESS;
    }

}
