package duorourou.micro.services.demo.gateway.card.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardField {
    private String fieldName;
    private String label;
    private String key;
    private boolean isDefault;
    private boolean isInvisible;
    private boolean isFixed;
    private boolean isCustomized;
    private String fieldId;
    private String extendValue;
    private String value;
}
