package duorourou.micro.services.demo.gateway.card.repository;

import duorourou.micro.services.demo.gateway.card.entity.Card;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CardRepository extends MongoRepository<Card, String> {

    public List<Card> findByPositionLane(String lane);

}
