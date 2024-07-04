package com.github.theword.mcqq.commands.subCommands;

import com.github.theword.mcqq.commands.CommandManager;
import com.github.theword.mcqq.commands.ForgeSubCommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import org.jetbrains.annotations.NotNull;

import static com.github.theword.mcqq.utils.Tool.handleCommandReturnMessage;

public class HelpCommand extends HelpCommandAbstract implements ForgeSubCommand {

    @Override
    public int onCommand(@NotNull MinecraftServer minecraftServer, @NotNull ICommandSender iCommandSender, String @NotNull [] strings) {
        handleCommandReturnMessage.handleCommandReturnMessage(iCommandSender, "-------------------");
        for (ForgeSubCommand forgeSubCommand : new CommandManager().getSubCommandList()) {
            handleCommandReturnMessage.handleCommandReturnMessage(iCommandSender, forgeSubCommand.getUsage() + "---" + forgeSubCommand.getDescription());
        }
        handleCommandReturnMessage.handleCommandReturnMessage(iCommandSender, "-------------------");
        return 1;

    }
}
