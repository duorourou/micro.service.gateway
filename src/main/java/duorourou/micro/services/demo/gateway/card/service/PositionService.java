package duorourou.micro.services.demo.gateway.card.service;

import duorourou.micro.services.demo.gateway.card.entity.Position;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PositionService {

    AtomicInteger integer = new AtomicInteger();

    public Position defaultPosition(String boardId, String laneId) {
        Position position = new Position();

        position.setBoard(boardId);
        position.setLane(laneId);
        position.setColumn("7832881560ad2bad9a46ae46");
        position.setPosition(integer.getAndDecrement());
        position.setLastModifiedDate(new Date());
        return position;
    }
}
