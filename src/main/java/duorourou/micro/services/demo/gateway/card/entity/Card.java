package duorourou.micro.services.demo.gateway.card.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Document(collection = "cards")
@Getter
@Setter
public class Card {
    @Id
    private String id;
    private String title;
    private String desc;
    private Double estimation;
    private String type;
    private String priority;
    private String release;
    private String iteration;
    private Date dueDate;
    private CardRisk risk;
    private Map<String, CardField> fields;
    private List<CardActiveLog> activeLogs;
    private List<String> owners;
    private Position position;
    private String creator;
    @LastModifiedDate
    private Date lastModifiedDate;
    @CreatedDate
    private Date createdDate;
//    private Status status = Status.ACTIVE;
}
