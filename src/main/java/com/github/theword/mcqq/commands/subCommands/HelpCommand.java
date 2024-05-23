package com.github.theword.mcqq.commands.subCommands;

import com.github.theword.mcqq.commands.CommandManager;
import com.github.theword.mcqq.commands.ForgeSubCommand;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;

import static com.github.theword.mcqq.utils.Tool.handleCommandReturnMessage;

public class HelpCommand extends HelpCommandAbstract implements ForgeSubCommand {

    @Override
    public int onCommand(CommandContext<CommandSourceStack> context) {
        handleCommandReturnMessage.handleCommandReturnMessage(context, "-------------------");
        for (ForgeSubCommand forgeSubCommand : new CommandManager().getSubCommandList()) {
            handleCommandReturnMessage.handleCommandReturnMessage(context, forgeSubCommand.getUsage() + "---" + forgeSubCommand.getDescription());
        }
        handleCommandReturnMessage.handleCommandReturnMessage(context, "-------------------");
        return Command.SINGLE_SUCCESS;

    }
}
