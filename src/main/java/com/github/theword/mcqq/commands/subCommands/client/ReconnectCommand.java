package com.github.theword.mcqq.commands.subCommands.client;

import com.github.theword.mcqq.commands.ForgeSubCommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import org.jetbrains.annotations.NotNull;

import static com.github.theword.mcqq.utils.Tool.websocketManager;

public class ReconnectCommand extends ReconnectCommandAbstract implements ForgeSubCommand {

    @Override
    public int onCommand(@NotNull MinecraftServer minecraftServer, @NotNull ICommandSender iCommandSender, String @NotNull [] strings) {
        websocketManager.reconnectWebsocketClients(false, iCommandSender);
        return 1;
    }

}
