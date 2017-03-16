package com.cmartin.learn.mybank.test;

import com.cmartin.learn.mybank.api.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 *
 */
public class TestUtils {
    public static final int COLLECTION_SIZE_1 = 1;
    public static final int COLLECTION_SIZE_5 = 5;

    public static final String DECIMAL_NUMBER_PATTERN = "^[0-9]+.[0-9]{2}";
    public static final String IBAN_PATTERN = "^[A-Z]{2}[0-9]{22}";
    public static final String WORD_PATTERN = "\\p{Print}+";
    public static final String UUID_PATTERN = "^[a-z0-9]{8}-([a-z0-9]{4}-){3}[a-z0-9]{12}";


    public static void createEntities(Integer count) {
    }

    public static UserDto newUserDto() {
        return newUserDto(UUID.randomUUID());
    }

    public static UserDto newUserDto(final UUID userID) {
        UserDto dto = new UserDto(userID);

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

    public static AccountListDto createAccountList() {
        return new AccountListDto(null);
    }

    public static List<AccountDto> createAccounts(final Integer count) {
        return IntStream.rangeClosed(1, count)
                .mapToObj(i -> newAccountDto(UUID.randomUUID(),
                        "alias-" + i,
                        makePseudoIBANAccount(),
                        makeBigDecimal(0d)))
                .collect(Collectors.toList());
    }

    public static AccountDto newAccountDto(
            final UUID id, final String alias, final String number, final BigDecimal balance) {
        AccountDto dto = new AccountDto(id, alias, number, balance);

        return dto;
    }

    public static AccountTransactionDto newAccountTransactionDto(
            final UUID id, final BigDecimal amount, final LocalDateTime dateTime) {
        AccountTransactionDto dto = new AccountTransactionDto(id, amount, dateTime);

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

    public static List<AccountTransactionDto> createAccountTransactions(final Integer count) {
        return IntStream.rangeClosed(1, count)
                .mapToObj(i -> newAccountTransactionDto(UUID.randomUUID(),
                        makeBigDecimal(RandomUtils.nextDouble(0, 100)),
                        LocalDateTime.now()))
                .collect(Collectors.toList());
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