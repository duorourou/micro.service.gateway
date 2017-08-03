package duorourou.micro.services.demo.gateway.tree;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Card {
    private String id;
    private String title;
    private boolean matched;

}
