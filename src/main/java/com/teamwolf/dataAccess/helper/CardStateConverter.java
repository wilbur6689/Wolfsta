package com.teamwolf.dataAccess.helper;

import com.teamwolf.enums.*;

import javax.persistence.*;

@Converter(autoApply = true)
public class CardStateConverter implements AttributeConverter<CardState, Integer>
{
    @Override
    public Integer convertToDatabaseColumn(CardState state)
    {
        return state.getState();
    }

    @Override
    public CardState convertToEntityAttribute(Integer id)
    {
        for(CardState s : CardState.values()){
            if(s.getState() == id) return s;
        }
        return null;
    }
}
