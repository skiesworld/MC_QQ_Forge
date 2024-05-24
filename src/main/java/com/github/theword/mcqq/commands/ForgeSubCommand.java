package com.github.theword.mcqq.commands;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.command.CommandSource;

public interface ForgeSubCommand {

    String getName();

    String getDescription();

    String getUsage();

    int onCommand(CommandContext<CommandSource> context);

}
