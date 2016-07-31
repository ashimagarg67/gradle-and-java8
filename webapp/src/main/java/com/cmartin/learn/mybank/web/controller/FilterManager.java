package com.cmartin.learn.mybank.web.controller;

import com.cmartin.learn.mybank.api.AccountFilter;
import com.cmartin.learn.mybank.api.UserFilter;
import org.springframework.stereotype.Component;

/**
 * Created by cmartin on 01/08/16.
 */
@Component
public class FilterManager {
    public UserFilter buildUserFilter() {
        return new UserFilter();
    }

    public AccountFilter buildAccoutFilter() {
        return new AccountFilter();
    }
}
