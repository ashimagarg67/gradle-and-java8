package com.cmartin.learn.mybank.test;

import com.cmartin.learn.mybank.api.AccountDto;
import com.cmartin.learn.mybank.api.ContractDto;
import com.cmartin.learn.mybank.api.UserDto;
import org.apache.commons.lang3.RandomStringUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 *
 */
public class TestUtils {
    public static void createEntities(Integer count) {
    }

    public static UserDto newUserDto() {
        UserDto dto = new UserDto(UUID.randomUUID());

        return dto;
    }


    public static List<UserDto> createUsers(final Integer count) {
        return IntStream.rangeClosed(1, count)
                .mapToObj(i -> newUserDto())
                .collect(Collectors.toList());

    }

    public static List<ContractDto> createContracts(final Integer count) {
        return IntStream.rangeClosed(1, count)
                .mapToObj(i -> newContractDto(UUID.randomUUID(),
                        "alias-" + i))
                .collect(Collectors.toList());
    }

    private static ContractDto newContractDto(final UUID id, final String alias) {
        ContractDto dto = new ContractDto(id, alias);

        return dto;
    }

    private static ContractDto newContractDtoFull(final UUID id, final String alias) {
        ContractDto dto = new ContractDto(id, alias);

        return dto;
    }

    public static List<AccountDto> createAccounts(final Integer count) {
        return IntStream.rangeClosed(1, count)
                .mapToObj(i -> newAccountDto(UUID.randomUUID(),
                        "alias-" + i,
                        makePseudoIBANAccount(),
                        makeBigDecimal(0d)))
                .collect(Collectors.toList());
    }

    private static AccountDto newAccountDto(UUID id, String alias, String number, BigDecimal balance) {
        AccountDto dto = new AccountDto(id, alias, number, balance);

        return dto;
    }


    public static String makePseudoIBANAccount() {
        // country (2) + number (22) ES9<-22->1
        return RandomStringUtils.randomAlphabetic(2).toUpperCase() +
                RandomStringUtils.randomNumeric(22);

    }

    public static BigDecimal makeBigDecimal(final Double value) {
        return BigDecimal.valueOf(value).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

}


/*



package com.cmartin.learn.mybank.dto;

import org.apache.commons.lang3.RandomStringUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DomainFactory {
    public static AccountDTO newAccountDTO(final String number, final String alias, final BigDecimal balance) {
        final AccountDTO dto = new AccountDTO();
        dto.setNumber(number);
        dto.setAlias(alias);
        dto.setBalance(balance.setScale(2, BigDecimal.ROUND_HALF_UP));

        return dto;
    }

    public static List<AccountDTO> newAccountDTOList(final Integer number) {
        return IntStream.rangeClosed(1, number)
                .mapToObj(i -> newAccountDTO(makePseudoIBANAccount(),
                        "alias-" + i,
                        makeUpTo2Pow16Euro()))
                .collect(Collectors.toList());
    }

    public static List<AccountTransactionDTO> newAccountTransactionListDTO(final Integer number) {
        return IntStream.rangeClosed(1, number)
                .mapToObj(i -> newAccountTransactionDTO(makeBigDecimalRandomDecimal(1024d * i + 1),
                        "EUR",
                        "account transaction description-" + i))
                .collect(Collectors.toList());
    }

    public static AccountTransactionDTO newAccountTransactionDTO(BigDecimal value, Currency currency, String description) {
        Amount amount = new Amount(value.setScale(2, BigDecimal.ROUND_HALF_UP), currency);
        Date transactionDate = new Date();
        String id = UUID.randomUUID().toString();
        // TODO Java Time API
        Calendar calendar = Calendar.getInstance();
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        Date date = calendar.getTime();
        return new AccountTransactionDTO(id, amount, transactionDate, date, description);
    }

    public static AccountTransactionDTO newAccountTransactionDTO(BigDecimal value, String currencyCode, String description) {
        return newAccountTransactionDTO(value, Currency.getInstance(currencyCode), description);
    }

    private static AccountTransactionListDTO newAccountTransactionListDTO(
            final List<AccountTransactionDTO> dtos, final Boolean hasNextPage, final String paginationKey) {
        AccountTransactionListDTO dto = new AccountTransactionListDTO(dtos, paginationKey, hasNextPage);

        return dto;
    }

    public static AccountTransactionListDTO newAccountTransactionListDTO() {
        return newAccountTransactionListDTO(new ArrayList<AccountTransactionDTO>(), false, "");
    }

    public static AccountTransactionListDTO newAccountTransactionListDTO(final List<AccountTransactionDTO> dtos) {
        return newAccountTransactionListDTO(dtos, false, "");
    }

    public static AccountTransactionListDTO newAccountTransactionListDTO(
            final List<AccountTransactionDTO> dtos, final String paginationKey) {
        return newAccountTransactionListDTO(dtos, true, paginationKey);
    }

    public static BigDecimal makeBigDecimalRandomDecimal(final Double value) {
        return BigDecimal.valueOf(value + new Random().nextDouble()).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public static String makePseudoIBANAccount() {
        // country (2) + number (22) ES9<-22->1
        return RandomStringUtils.randomAlphabetic(2).toUpperCase() +
                RandomStringUtils.randomNumeric(22);

    }

    private static BigDecimal makeUpTo2Pow16Euro() {
        return new BigDecimal(new BigInteger(16, new Random()))
                .add(new BigDecimal(new Random().nextDouble()).setScale(2, BigDecimal.ROUND_HALF_UP));
    }
}



 */