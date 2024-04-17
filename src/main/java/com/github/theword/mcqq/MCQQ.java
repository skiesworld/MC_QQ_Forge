package com.github.theword.mcqq;

import com.github.theword.mcqq.utils.HandleWebsocketMessageService;
import com.github.theword.mcqq.constant.WebsocketConstantMessage;
import com.github.theword.mcqq.utils.Config;
import com.github.theword.mcqq.websocket.WsClient;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.event.server.ServerStoppingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import static com.github.theword.mcqq.utils.Tool.*;

@Mod(MCQQ.MOD_ID)
public class MCQQ {

    public static final String MOD_ID = "mcqq";
    public static final String MOD_NAME = "MC_QQ";
    public static MinecraftServer minecraftServer;

    public MCQQ() {
        logger = LoggerFactory.getLogger(MOD_NAME);
        config = new Config(true);
        wsClientList = new ArrayList<>();
        handleWebsocketMessage = new HandleWebsocketMessageService();
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new EventProcessor());
    }

    @OnlyIn(Dist.DEDICATED_SERVER)
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        logger.info(WebsocketConstantMessage.WEBSOCKET_RUNNING);
        minecraftServer = event.getServer();
        config.getWebsocketUrlList().forEach(websocketUrl -> {
            try {
                WsClient wsClient = new WsClient(new URI(websocketUrl));
                wsClient.connect();
                wsClientList.add(wsClient);
            } catch (URISyntaxException e) {
                logger.warn(WebsocketConstantMessage.WEBSOCKET_ERROR_URI_SYNTAX_ERROR.formatted(websocketUrl));
            }
        });
    }

    @OnlyIn(Dist.DEDICATED_SERVER)
    @SubscribeEvent
    public void onServerStopping(ServerStoppingEvent event) {
        wsClientList.forEach(
                wsClient -> {
                    wsClient.stopWithoutReconnect(
                            1000,
                            WebsocketConstantMessage.WEBSOCKET_CLOSING.formatted(wsClient.getURI())
                    );
                }
        );
    }
}
