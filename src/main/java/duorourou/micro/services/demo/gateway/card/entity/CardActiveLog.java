package duorourou.micro.services.demo.gateway.card.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CardActiveLog {
    private String operatorId;
    private Date operationTime;
    private String originValue;
    private String destValue;
}
