import com.cmartin.learn.calculator.service.CalculatorServiceImpl
import io.vavr.control.Try
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll;

class CalculatorServiceSpecification extends Specification {

    BigDecimal FIVE = BigDecimal.valueOf(5)
    BigDecimal TWO = BigDecimal.valueOf(2)
    BigDecimal SIXTEEN = BigDecimal.valueOf(16)

    @Subject
            calculator

    def setup() {
        calculator = new CalculatorServiceImpl()
    }

    @Unroll
    def "Add two numbers matrix"(BigDecimal a, BigDecimal b, BigDecimal r) {
        given: "a new calculator is created"

        expect:
        Try result = calculator.sum(BigDecimal.valueOf(a), BigDecimal.valueOf(b))
        result.isSuccess()
        result.get() == r

        where:
        a  | b | r
        2  | 3 | 5
        1  | 0 | 1
        -2 | 3 | 1
    }

    def "Add two numbers"() {
        given: "a new calculator is created"

        when: "2 plus 3 is 5"
        Try result = calculator.sum(BigDecimal.valueOf(2), BigDecimal.valueOf(3))

        then:
        with(result) {
            isSuccess()
            get() == FIVE
        }
    }

    def "Subtract two numbers"() {
        given: "a new calculator is created"

        when: "5 minus 3 is 2"
        Try result = calculator.subtract(BigDecimal.valueOf(5), BigDecimal.valueOf(3))

        then:
        with(result) {
            isSuccess()
            get() == TWO
        }
    }

    def "Multiply two numbers"() {
        given: "a new calculator is created"

        when: "4 multiply 4 is 16"
        Try result = calculator.multiply(BigDecimal.valueOf(4), BigDecimal.valueOf(4))

        then:
        with(result) {
            isSuccess()
            get() == SIXTEEN
        }
    }

    def "Divide two numbers"() {
        given: "a new calculator is created"

        when: "5 divide by 2 is 2.5"
        Try result = calculator.divide(BigDecimal.valueOf(5), BigDecimal.valueOf(2))

        then:
        with(result) {
            isSuccess()
            get() == BigDecimal.valueOf(2.5)
        }
    }

    def "Divide by zero"() {
        given: "a new calculator is created"

        when: "1 divide by 0 is an error"
        Try result = calculator.divide(BigDecimal.valueOf(1), BigDecimal.valueOf(0))

        then:
        with(result) {
            isFailure()
            getCause() instanceof ArithmeticException
            getCause().getMessage().contains("Division by zero")
        }
    }
}