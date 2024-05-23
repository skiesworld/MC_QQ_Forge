package com.github.theword.mcqq.commands.subCommands;

import com.github.theword.mcqq.commands.ForgeSubCommand;
import com.github.theword.mcqq.commands.subCommands.client.ReconnectCommand;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;

import java.util.ArrayList;
import java.util.List;

import static com.github.theword.mcqq.utils.Tool.handleCommandReturnMessage;

public class HelpCommand extends HelpCommandAbstract implements ForgeSubCommand {
    private final List<ForgeSubCommand> subCommandList = new ArrayList<>();

    public HelpCommand() {
        subCommandList.add(new HelpCommand());
        subCommandList.add(new ReloadCommand());
        subCommandList.add(new ReconnectCommand());
    }

    @Override
    public int onCommand(CommandContext<CommandSourceStack> context) {
        handleCommandReturnMessage.handleCommandReturnMessage(context, "-------------------");
        for (ForgeSubCommand forgeSubCommand : subCommandList) {
            handleCommandReturnMessage.handleCommandReturnMessage(context, forgeSubCommand.getUsage() + "---" + forgeSubCommand.getDescription());
        }
        handleCommandReturnMessage.handleCommandReturnMessage(context, "-------------------");
        return Command.SINGLE_SUCCESS;

    }
}
