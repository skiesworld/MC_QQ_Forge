package com.github.theword.mcqq.commands;

import com.github.theword.mcqq.commands.subCommands.HelpCommand;
import com.github.theword.mcqq.commands.subCommands.ReconnectCommand;
import com.github.theword.mcqq.commands.subCommands.ReloadCommand;
import com.github.theword.mcqq.MCQQ;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;

@Mod.EventBusSubscriber(modid = MCQQ.MOD_ID)
public class CommandRegister {

    @SubscribeEvent
    public static void registerCommands(RegisterCommandsEvent event) {
        CommandManager.subCommandList.add(new HelpCommand(event.getDispatcher()));
        CommandManager.subCommandList.add(new ReloadCommand(event.getDispatcher()));
        CommandManager.subCommandList.add(new ReconnectCommand(event.getDispatcher()));
        ConfigCommand.register(event.getDispatcher());
    }

}