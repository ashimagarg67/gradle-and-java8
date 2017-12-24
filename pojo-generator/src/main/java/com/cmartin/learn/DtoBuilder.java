package com.cmartin.learn;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.comments.BlockComment;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.FieldAccessExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.ThisExpr;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.ExpressionStmt;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DtoBuilder {
    private final String packageName;
    private final String name;
    private final List<PropertyDef> properties = new ArrayList<>();

    public DtoBuilder(String packageName, String name) {
        this.packageName = packageName;
        this.name = name;
    }


    public CompilationUnit build() {
        CompilationUnit cu = new CompilationUnit(packageName);
        ClassOrInterfaceDeclaration dto = cu.addClass(name);
        dto.addImplementedType(Serializable.class);
        ConstructorDeclaration constructor = dto.addConstructor(Modifier.PUBLIC);
        BlockStmt blockStmt = new BlockStmt();
        constructor.setBody(blockStmt);

        for (PropertyDef pDef : this.properties) {
            constructor.addParameter(pDef.getType(), pDef.getName());
            blockStmt.addStatement(newConsAssignStmt(pDef.getName()));

            FieldDeclaration fieldDeclaration = dto.addPrivateField(pDef.getType(), pDef.getName());
            fieldDeclaration.setComment(new BlockComment(pDef.getComment()));
            fieldDeclaration.createGetter();
        }

        return cu;
    }

    public <T> DtoBuilder addProperty(String amountPropName, Class<T> amountPropType, String amountPropComment) {
        this.properties.add(new PropertyDef(amountPropName, amountPropType, amountPropComment));
        return this;
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

    private class PropertyDef<T> {

        private final String name;
        private final Class<T> type;
        private final String comment;

        public PropertyDef(String name, Class<T> type, String comment) {
            this.name = name;
            this.type = type;
            this.comment = comment;
        }

        public String getName() {
            return name;
        }

        public Class<T> getType() {
            return type;
        }

        public String getComment() {
            return comment;
        }
    }
}
