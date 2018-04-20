package com.cmartin.learn.mybank

import com.cmartin.learn.domain.Account
import spock.lang.Specification
import spock.lang.Subject

/**
 * Created by cmartin on 16/07/16.
 */
class AccountRepositoryTest extends Specification {

    @Subject
    private AccountRepository repository

    def setup() {
        repository = new AccountRepositoryImpl()
    }

    def "save an account"() {
        given: "an account"
        def account = createAccount()
        def previousCount = repository.count()

        when: "save the account"
        Optional<Account> some = repository.save(account)

        then: "repository sets an identifier to the account"
        some.isPresent() == true
        some.get().id != null
        some.get().id.toString().isEmpty() == false
        repository.count() == previousCount + 1
    }

    def "get an account by id"() {
        given: "an stored account id"
        def account = createAccount()
        repository.save(account)
        def id = account.id

        when: "retrieve the account by id"
        def result = repository.findOne(id)

        then: "the account retrieved has the id supplied"
        result.isPresent() == true
    }

    def "delete an account"() {
        given: "an stored account"
        def account = createAccount()
        repository.save(account)
        def previousCount = repository.count()

        when: "delete the account by id"
        repository.delete(account)

        then: "the account retrieved has the id supplied"
        repository.count() == previousCount - 1
    }

    // TODO update test

    def createAccount() {
        def account = new Account();
        account.alias = "account-alias"
        account.balance = 100.00
        account.number = "11112222333344445555"

        return account
    }
}
