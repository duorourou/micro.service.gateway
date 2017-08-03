package duorourou.micro.services.demo.gateway.tree;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Branch {
    private String root;
    private List<Card> cards;

    private String currentCardId;

    public void addParent(Card parent) {
        this.cards.add(parent);
    }

    public boolean isMatched() {
        return cards.stream().filter(Card::isMatched).count() > 0;
    }
}
