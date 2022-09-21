package org.flab.deliveryplatform.order.query.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import javax.persistence.AttributeConverter;
import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.order.query.domain.MyOrder.MyOrderLineItem;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OrderLineItemsConverter implements AttributeConverter<List<MyOrderLineItem>, String> {

    private final ObjectMapper objectMapper;

    @Override
    public String convertToDatabaseColumn(List<MyOrderLineItem> myOrderLineItems) {
        String json;
        try {
            json = objectMapper.writeValueAsString(myOrderLineItems);
        } catch (final JsonProcessingException e) {
            throw new IllegalStateException("Json processing Error");
        }

        return json;
    }

    @Override
    public List<MyOrderLineItem> convertToEntityAttribute(String json) {
        List<MyOrderLineItem> myOrderLineItems;
        try {
            myOrderLineItems = objectMapper.readValue(json, new TypeReference<List<MyOrderLineItem>>() {
            });
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Json processing Error");
        }

        return myOrderLineItems;
    }
}
