import com.cmartin.learn.calculator.service.CalculatorServiceImpl
import io.vavr.control.Try
import spock.lang.Specification;

class CalculatorServiceSpecification extends Specification {

    BigDecimal FIVE = BigDecimal.valueOf(5)
    BigDecimal TWO = BigDecimal.valueOf(2)
    BigDecimal SIXTEEN = BigDecimal.valueOf(16)

    def "Add two numbers"() {
        when: "a new calculator is created"
        def calculator = new CalculatorServiceImpl()

        then: "2 plus 3 is 5"
        Try result = calculator.sum(BigDecimal.valueOf(2), BigDecimal.valueOf(3))

        result.isSuccess() == true
        result.get() == FIVE
    }

    def "Subtract two numbers"() {
        when: "a new calculator is created"
        def calculator = new CalculatorServiceImpl()

        then: "5 minus 3 is 2"
        Try result = calculator.subtract(BigDecimal.valueOf(5), BigDecimal.valueOf(3))

        result.isSuccess() == true
        result.get() == TWO
    }

    def "Multiply two numbers"() {
        when: "a new calculator is created"
        def calculator = new CalculatorServiceImpl()

        then: "4 multiply 4 is 16"
        Try result = calculator.multiply(BigDecimal.valueOf(4), BigDecimal.valueOf(4))

        result.isSuccess() == true
        result.get() == SIXTEEN
    }

    def "Divide two numbers"() {
        when: "a new calculator is created"
        def calculator = new CalculatorServiceImpl()

        then: "5 divide by 2 is 2.5"
        Try result = calculator.divide(BigDecimal.valueOf(5), BigDecimal.valueOf(2))

        result.isSuccess() == true
        result.get() == BigDecimal.valueOf(2.5)
    }

    def "Divide by zero"() {
        when: "a new calculator is created"
        def calculator = new CalculatorServiceImpl()

        then: "1 divide by 0 is an error"
        Try result = calculator.divide(BigDecimal.valueOf(1), BigDecimal.valueOf(0))

        result.isFailure() == true
        result.getCause().getClass() == ArithmeticException.class
        result.getCause().getMessage().contains("Division by zero")
    }
}