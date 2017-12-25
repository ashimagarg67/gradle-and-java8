package com.cmartin.learn;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.comments.BlockComment;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.FieldAccessExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.ThisExpr;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.ExpressionStmt;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class DtoGenerator {
    private static final String PACKAGE_NAME = "com.cmartin.learn.service";
    private static final String CLASS_NAME = "AccountTransactionDto";

    private static final Class<BigDecimal> AMOUNT_PROP_TYPE = BigDecimal.class;
    private static final String AMOUNT_PROP_NAME = "amount";
    private static final String AMOUNT_PROP_COMMENT = "ammount property comment";

    private static final Class<LocalDateTime> LDTIME_PROP_TYPE = LocalDateTime.class;
    private static final String LDTIME_PROP_NAME = "dateTime";
    private static final String LDTIME_PROP_COMMENT = "dateTime property comment";

    public static void main(String[] args) {
        System.out.println("START: DtoGenerator");

        CompilationUnit cu = new CompilationUnit();
        cu.setPackageDeclaration(PACKAGE_NAME);

        ClassOrInterfaceDeclaration dto = cu.addClass(CLASS_NAME);
        dto.addImplementedType(Serializable.class);
        FieldDeclaration amountField = dto.addPrivateField(BigDecimal.class, AMOUNT_PROP_NAME);
        amountField.setComment(new BlockComment(AMOUNT_PROP_COMMENT));

        FieldDeclaration dateTimeField = dto.addPrivateField(LocalDateTime.class, LDTIME_PROP_NAME);
        dateTimeField.setComment(new BlockComment(LDTIME_PROP_COMMENT));

        ConstructorDeclaration constructor = dto.addConstructor(Modifier.PUBLIC);
        constructor.addParameter(AMOUNT_PROP_TYPE, AMOUNT_PROP_NAME);
        constructor.addParameter(LDTIME_PROP_TYPE, LDTIME_PROP_NAME);


        MethodDeclaration md = new MethodDeclaration();
        ExpressionStmt statement = new ExpressionStmt();
        statement.setExpression(new AssignExpr(
                new FieldAccessExpr(new ThisExpr(), AMOUNT_PROP_NAME),
                new NameExpr(AMOUNT_PROP_NAME),
                AssignExpr.Operator.ASSIGN)
        );

        //statement.setExpression(new FieldAccessExpr(new ThisExpr(), "pepe"));

        AssignExpr expr = new AssignExpr();

        BlockStmt blockStmt = new BlockStmt();
        constructor.setBody(blockStmt);
        blockStmt.addStatement(newConsAssignStmt(AMOUNT_PROP_NAME));
        blockStmt.addStatement(newConsAssignStmt(LDTIME_PROP_NAME));


        amountField.createGetter();
        dateTimeField.createGetter();

        System.out.println(cu.toString());

        System.out.println("START: DtoBuilder");
        CompilationUnit cu2 = new DtoBuilder(PACKAGE_NAME, CLASS_NAME)
                .addProperty(AMOUNT_PROP_NAME, AMOUNT_PROP_TYPE, AMOUNT_PROP_COMMENT)
                .addProperty(LDTIME_PROP_NAME, LDTIME_PROP_TYPE, LDTIME_PROP_COMMENT)
                .build();
        System.out.println(cu2.toString());

        System.out.println("START: ServiceBuilder");
        CompilationUnit srvCu = new ServiceBuilder(PACKAGE_NAME,"BankService")
                .addOperation("operation", "ServiceRequest", "ServiceResponse")
                .build();
        System.out.println(srvCu);

        System.out.println("START: ServiceImplBuilder");
        CompilationUnit srvImplCu = new ServiceImplBuilder(srvCu)
                .build();
        System.out.println(srvImplCu);
    }

    private static ExpressionStmt newConsAssignStmt(final String propname) {
        return new ExpressionStmt(
                new AssignExpr(
                        new FieldAccessExpr(new ThisExpr(), propname),
                        new NameExpr(propname),
                        AssignExpr.Operator.ASSIGN
                )
        );
    }
}
