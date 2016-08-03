package com.cmartin.learn.mybank.api;

import java.util.UUID;

/**
 * Created by cmartin on 16/07/16.
 */
public class CardDto extends ContractDto {
    public CardDto(UUID id, String alias) {
        super(id, alias);
    }
}
