package com.cmartin.learn.mybank

import com.cmartin.learn.domain.Account
import com.cmartin.learn.mybank.test.DomainUtils
import spock.lang.Specification
import spock.lang.Subject

/**
 * Created by cmartin on 16/07/16.
 */
class AccountRepositoryTest extends Specification {

    def ACCOUNT_NUMBER = "11112222333344445555"
    def ACCOUNT_ALIAS = "account-alias"
    def ACCOUNT_BALANCE = 100.00

    def ACCOUNT_ONE = DomainUtils.newAccount(ACCOUNT_NUMBER, ACCOUNT_ALIAS, ACCOUNT_BALANCE)
    def ACCOUNT_TWO = DomainUtils.newAccount("9999888877776666", "another-account-alias", 50.0)

    @Subject
    private AccountRepository repository

    def setup() {
        repository = new AccountRepositoryImpl()
    }

    def "save an account"() {
        given: "an account"
        def previousCount = repository.count()

        when: "save the account"
        Optional<Account> some = repository.save(ACCOUNT_ONE)

        then: "repository sets an identifier to the account"
        some.isPresent() == true
        some.get().id != null
        some.get().alias == ACCOUNT_ALIAS
        some.get().balance == ACCOUNT_BALANCE
        some.get().number == ACCOUNT_NUMBER
        some.get().id.toString().isEmpty() == false
        repository.count() == previousCount + 1
    }

    def "get an account by id"() {
        given: "an stored account id"
        repository.save(ACCOUNT_ONE)

        when: "retrieve the account by id"
        def result = repository.findOne(ACCOUNT_ONE.id)

        then: "the account retrieved has the id supplied"
        result.isPresent() == true
    }

    def "get a missing account by id"() {
        given: "a missing account id"
        def id = DomainUtils.getAccountId()

        when: "retrieve the account by id"
        def result = repository.findOne(id)

        then: "the account retrieved has the id supplied"
        result.isPresent() == false
    }

    def "get all accounts"() {
        given: "an account collection"
        repository.save(ACCOUNT_ONE)
        repository.save(ACCOUNT_TWO)

        when: "retrieve all accounts"
        def result = repository.findAll()

        then: "size must be as expected"
        result.size() == 2
    }

    def "delete an account"() {
        given: "an stored account"
        repository.save(ACCOUNT_ONE)
        def previousCount = repository.count()

        when: "delete the account by id"
        repository.delete(ACCOUNT_ONE)

        then: "the account retrieved has the id supplied"
        repository.count() == previousCount - 1
    }

    def "account does not exists"() {
        given: "an empty repository"
        def id = DomainUtils.getAccountId()

        when: "get a missing account by id"
        def result = repository.exists(id)

        then: "the account retrieved has the id supplied"
        result == false
    }

    def "account exists"() {
        given: "an stored account id"
        repository.save(ACCOUNT_ONE)

        when: "retrieve the account by id"
        def result = repository.exists(ACCOUNT_ONE.id)

        then: "the account retrieved has the id supplied"
        result == true

    }
    // TODO update test

}
