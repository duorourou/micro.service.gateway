package duorourou.micro.services.demo.gateway.cache;

import com.mongodb.Cursor;
import com.mongodb.DBObject;
import duorourou.micro.services.demo.gateway.card.entity.Card;
import duorourou.micro.services.demo.gateway.card.repository.CardRepository;
import duorourou.micro.services.demo.gateway.card.service.CreateService;
import duorourou.micro.services.demo.gateway.mq.RabbitMqConfig;
import duorourou.micro.services.demo.gateway.redis.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping(path = "/caches")
public class Controller {

    @Autowired
    RabbitMqConfig mqConfig;
    @Autowired
    Publisher publisher;
    @Autowired
    private CreateService createService;
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    MongoTemplate mongoTemplate;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<String> listAllCaches() {
        String key = UUID.randomUUID().toString();
        System.out.println("key : " + key);
        mqConfig.send("tree", key);
        return ResponseEntity.ok(key);
    }

    @RequestMapping(value = "/kafka-sync/{board}/{lane}", method = RequestMethod.GET)
    public ResponseEntity<String> listAllKafkaCaches(@PathVariable String board,
                                                     @PathVariable String lane) throws InterruptedException {
        String key = UUID.randomUUID().toString();
        System.out.println("key : " + key);
        publisher.publish("kkk", key);
        return ResponseEntity.ok(key);
    }

    @RequestMapping(value = "/cards", method = RequestMethod.POST)
    public ResponseEntity<String> postCard(@RequestBody Card card) {


        return ResponseEntity.ok("ok");
    }

    @RequestMapping(value = "/cards/lane/{laneId}", method = RequestMethod.GET)
    public ResponseEntity<String> listCards(@PathVariable String laneId) {

        long start = System.currentTimeMillis();
        List<Card> cardList = cardRepository.findByPositionLane(laneId);
        long end = System.currentTimeMillis();
        List<Map> res = mongoTemplate.find(Query.query(Criteria.where("position.lane").is(laneId)), Map.class, "cards");
        toCards(res);
        long end2 = System.currentTimeMillis();
        Cursor cursor = mongoTemplate.getCollection("cards").find(Query.query(Criteria.where("position.lane").is(laneId)).getQueryObject());
        toCards(cursor);
        long end3 = System.currentTimeMillis();
        System.out.println("findby : " + (end - start) + " , template : " + (end2 - end) + " , cursor : " + (end3 - end2));
        return ResponseEntity.ok("ok");
    }


    private List<Card> toCards(List<Map> maps) {
        return maps.stream()
                .map(map -> {
                    Card card = new Card();
                    card.setTitle((String) map.get("title"));
                    card.setDueDate((Date) map.get("dueDate"));
                    card.setId((String) map.get("id"));
                    card.setDesc((String) map.get("desc"));
                    card.setEstimation((Double) map.get("estimation"));
                    card.setType((String) map.get("type"));
                    card.setRelease((String) map.get("release"));
                    return card;
                })
                .collect(toList());
    }

    private List<Card> toCards(Cursor cursor) {
        List<Card> cards = new ArrayList(1000);
        try {
            while (cursor.hasNext()) {
                DBObject obj = cursor.next();
                Card card = new Card();
                card.setTitle((String) obj.get("title"));
                card.setDueDate((Date) obj.get("dueDate"));
                card.setId((String) obj.get("id"));
                card.setDesc((String) obj.get("desc"));
                card.setEstimation((Double) obj.get("estimation"));
                card.setType((String) obj.get("type"));
                card.setRelease((String) obj.get("release"));
                cards.add(card);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }
        return cards;
    }
}
