package com.cmartin.learn.mybank.web;

import com.cmartin.learn.mybank.api.BankApi;
import com.cmartin.learn.mybank.api.OperationOutputDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by cmartin on 10/07/16.
 */
@Controller
@RequestMapping(value = "/")
public class BankController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BankApi bankApi;

    @RequestMapping(value = "accounts",
            method = RequestMethod.GET,
            produces = "application/json")
    @ResponseBody
    public ResponseEntity<OperationOutputDto> getAccounts(
            @RequestParam(required = false, defaultValue = "0") final Integer pageSize) {

        this.logger.debug("retrieving bank accounts");

        OperationOutputDto dto = new OperationOutputDto("property value");

        return new ResponseEntity<OperationOutputDto>(dto, HttpStatus.OK);
    }

}
