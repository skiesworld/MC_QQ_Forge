package com.github.theword.commands;

import com.github.theword.MCQQ;
import com.github.theword.commands.subCommands.HelpCommand;
import com.github.theword.commands.subCommands.ReconnectCommand;
import com.github.theword.commands.subCommands.ReloadCommand;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;

import static com.github.theword.commands.CommandManager.subCommandList;

@Mod.EventBusSubscriber(modid = MCQQ.MOD_ID)
public class CommandRegister {

    @SubscribeEvent
    public static void registerCommands(RegisterCommandsEvent event) {
        subCommandList.add(new HelpCommand(event.getDispatcher()));
        subCommandList.add(new ReloadCommand(event.getDispatcher()));
        subCommandList.add(new ReconnectCommand(event.getDispatcher()));
        ConfigCommand.register(event.getDispatcher());
    }

}