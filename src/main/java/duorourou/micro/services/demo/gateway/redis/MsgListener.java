package duorourou.micro.services.demo.gateway.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MsgListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(MsgListener.class);

    public void onMessage(String message) {
        LOGGER.info("received msg :  {} ", message );
    }
}
