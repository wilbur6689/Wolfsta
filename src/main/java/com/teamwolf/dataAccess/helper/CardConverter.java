package com.teamwolf.dataAccess.helper;

import com.teamwolf.enums.*;

import javax.persistence.*;

@Converter(autoApply = true)
public class CardConverter implements AttributeConverter<Card,Integer>
{
    @Override
    public Integer convertToDatabaseColumn(Card card)
    {
        return card.getId();
    }

    @Override
    public Card convertToEntityAttribute(Integer i)
    {
        return Card.getCardbyCardId(i);
    }
}
