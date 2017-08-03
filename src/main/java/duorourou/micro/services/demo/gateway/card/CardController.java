package duorourou.micro.services.demo.gateway.card;

import duorourou.micro.services.demo.gateway.card.entity.Card;
import duorourou.micro.services.demo.gateway.card.entity.CardActiveLog;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@RestController
@RequestMapping(path = "/cards")
public class CardController {

    @RequestMapping(path = "/{cardId}", method = RequestMethod.GET)
    public Card getById(@PathVariable String cardId) {
        Card card = new Card();
        card.setId(cardId);
        card.setActiveLogs(newArrayList(new CardActiveLog()));
        return card;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Card> list() {
        Card card = new Card();
        card.setId("123");
        card.setActiveLogs(newArrayList(new CardActiveLog()));
        return newArrayList(card);
    }
}
