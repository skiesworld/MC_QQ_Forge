package com.github.theword;

import net.minecraft.server.MinecraftServer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.event.server.ServerStoppingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


@Mod(MCQQ.MOD_ID)
public class MCQQ {

    public static final String MOD_ID = "mcqq";
    public static final String MOD_NAME = "MC_QQ";
    public static final Logger LOGGER = LogManager.getLogger(MOD_NAME);
    public static Config config = new Config(true);
    static MinecraftServer minecraftServer;
    static List<WsClient> wsClientList = new ArrayList<>();

    public MCQQ() {
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new EventProcessor());
    }

    @OnlyIn(Dist.DEDICATED_SERVER)
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("WebSocket Client 正在启动...");
        minecraftServer = event.getServer();
        config.getWebsocketUrlList().forEach(url -> {
            try {
                WsClient wsClient = new WsClient(url);
                wsClient.connect();
                wsClientList.add(wsClient);
            } catch (URISyntaxException e) {
                LOGGER.warn("[MC_QQ]|连接至：%s WebSocket URL 配置错误，无法连接！".formatted(url));
            }
        });
    }

    @OnlyIn(Dist.DEDICATED_SERVER)
    @SubscribeEvent
    public void onServerStopping(ServerStoppingEvent event) {
        wsClientList.forEach(
                wsClient -> {
                    wsClient.close(
                            1000,
                            "[MC_QQ]|连接至：%s 的 WebSocket Client 正在关闭".formatted(wsClient.getURI())
                    );
                    wsClient.getTimer().cancel();
                }
        );
    }
}
