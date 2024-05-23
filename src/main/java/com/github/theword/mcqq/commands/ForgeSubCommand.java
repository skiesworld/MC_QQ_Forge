package com.github.theword.mcqq.commands;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;

public interface ForgeSubCommand {

    String getName();

    String getDescription();

    String getUsage();

    int onCommand(CommandContext<CommandSourceStack> context);

}
