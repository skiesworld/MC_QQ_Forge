package com.github.theword.mcqq;

import com.github.theword.mcqq.constant.BaseConstant;
import com.github.theword.mcqq.constant.WebsocketConstantMessage;
import com.github.theword.mcqq.handleMessage.HandleApiService;
import com.github.theword.mcqq.handleMessage.HandleCommandReturnMessageService;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppingEvent;

import static com.github.theword.mcqq.utils.Tool.initTool;
import static com.github.theword.mcqq.utils.Tool.websocketManager;

@Mod(modid = BaseConstant.COMMAND_HEADER)
public class MCQQ {
    public static MinecraftServer minecraftServer;

    public MCQQ() {
        initTool(true, new HandleApiService(), new HandleCommandReturnMessageService());
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new EventProcessor());
    }

    @EventHandler
    public void onServerStarting(FMLServerStartingEvent event) {
        minecraftServer = event.getServer();
        websocketManager.startWebsocket(null);
    }

    @EventHandler
    public void onServerStopping(FMLServerStoppingEvent event) {
        websocketManager.stopWebsocket(1000, WebsocketConstantMessage.Client.CLOSING, null);
    }
}
