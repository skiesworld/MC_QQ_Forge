package com.github.theword.events;

import com.github.theword.CommandRegister;
import com.github.theword.MCQQ;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;

@Mod.EventBusSubscriber(modid = MCQQ.MOD_ID)
public class CommandEvents {

    @SubscribeEvent
    public static void registerCommands(RegisterCommandsEvent event) {
        new CommandRegister(event.getDispatcher());
        ConfigCommand.register(event.getDispatcher());
    }
}
