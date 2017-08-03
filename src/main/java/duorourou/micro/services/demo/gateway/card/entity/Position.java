package duorourou.micro.services.demo.gateway.card.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Position {
    private int position;
    private String board;
    private String lane;
    private String column;
    private Date lastModifiedDate;
}
