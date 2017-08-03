package duorourou.micro.services.demo.gateway.card.service;


import duorourou.micro.services.demo.gateway.card.entity.Card;
import duorourou.micro.services.demo.gateway.card.entity.CardActiveLog;
import duorourou.micro.services.demo.gateway.card.entity.CardField;
import duorourou.micro.services.demo.gateway.card.repository.CardRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.stream.Collectors.toMap;

@Service
public class CreateService {

    String[] fieldIds = new String[]{
            "5832881560ad2bad9a46ae46",
            "583289e260ad2baf3c9938fc",
            "58328f1b60ad2bb3c3c3ee4e",
            "58328f7060ad2bb3c3c3ee51",
            "58328f7060ad2bb3c3c3ee52",
            "58328f7060ad2bb3c3c3ee53",
            "58328f7060ad2bb3c3c3ee54",
            "58328f7060ad2bb3c3c3ee55",
            "58328f7060ad2bb3c3c3ee56",
            "58328f7060ad2bb3c3c3ee57",
            "58328f7060ad2bb3c3c3ee58",
            "58328f7060ad2bb3c3c3ee59",
            "58328f7060ad2bb3c3c3ee5a",
            "58328f7060ad2bb3c3c3ee5b",
            "58328f7060ad2bb3c3c3ee5c"
    };

    @Autowired
    CardRepository cardRepository;
    @Autowired
    PositionService positionService;

    public Card createCard(Card card, String board, String lane) {
        card.setActiveLogs(createLogs());
        card.setFields(defaultFields());
        card.setCreator(ObjectId.get().toHexString());
        card.setOwners(newArrayList(ObjectId.get().toHexString()));
        card.setCreatedDate(new Date());
        card.setPosition(positionService.defaultPosition(board, lane));
        return cardRepository.save(card);
    }

    private List<CardActiveLog> createLogs() {
        CardActiveLog log = new CardActiveLog();
        log.setDestValue("AAAAAAAAAAAAAAAAAAAAAA");
        log.setOriginValue("BBBBBBBBBBBBBBBBBBBBBB");
        log.setOperationTime(new Date());
        log.setOperatorId(ObjectId.get().toHexString());
        return newArrayList(log);
    }

    private Map<String, CardField> defaultFields() {
        return Arrays.stream(fieldIds).collect(toMap(id -> id, this::getField));
    }

    private CardField getField(String id) {
        CardField field = new CardField();
        field.setFieldId(id);
        field.setFieldName(id);
        field.setLabel(id);
        return field;
    }
}


