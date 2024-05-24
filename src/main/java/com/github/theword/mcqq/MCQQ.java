package com.github.theword.mcqq;

import com.github.theword.mcqq.constant.WebsocketConstantMessage;
import com.github.theword.mcqq.handleMessage.HandleApiService;
import com.github.theword.mcqq.handleMessage.HandleCommandReturnMessageService;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.event.server.FMLServerStoppingEvent;

import static com.github.theword.mcqq.utils.Tool.initTool;
import static com.github.theword.mcqq.utils.Tool.websocketManager;

@Mod(MCQQ.MOD_ID)
public class MCQQ {
    public static final String MOD_ID = "mcqq";
    public static MinecraftServer minecraftServer;

    public MCQQ() {
        initTool(true, new HandleApiService(), new HandleCommandReturnMessageService());
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new EventProcessor());
    }

    @OnlyIn(Dist.DEDICATED_SERVER)
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        minecraftServer = event.getServer();
        websocketManager.startWebsocket(null);
    }

    @OnlyIn(Dist.DEDICATED_SERVER)
    @SubscribeEvent
    public void onServerStopping(FMLServerStoppingEvent event) {
        websocketManager.stopWebsocket(1000, WebsocketConstantMessage.Client.CLOSING, null);
    }
}
