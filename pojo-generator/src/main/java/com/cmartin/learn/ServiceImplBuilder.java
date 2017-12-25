package com.cmartin.learn;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.stmt.BlockStmt;

import java.util.EnumSet;

public class ServiceImplBuilder {
    private final CompilationUnit interfaceUnit;

    public ServiceImplBuilder(CompilationUnit compilationUnit) {
        this.interfaceUnit = compilationUnit;
    }


    public CompilationUnit build() {
        CompilationUnit cu = new CompilationUnit();
        cu.setPackageDeclaration(this.interfaceUnit.getPackageDeclaration().get().getName() + ".impl");
        if (interfaceUnit.getTypes().isNonEmpty()) {
            TypeDeclaration<?> interfaceType = interfaceUnit.getType(0);
            String interfaceName = interfaceType.getNameAsString();
            ClassOrInterfaceDeclaration classDeclaration = cu.addClass( interfaceName+ "Impl");
            classDeclaration.addImplementedType(interfaceName);
            MethodDeclaration methodDecl = interfaceType.getMethods().get(0); //TODO
            methodDecl.setModifiers(EnumSet.of(Modifier.PUBLIC));
            methodDecl.getParameter(0).setModifiers(EnumSet.of(Modifier.FINAL));
            methodDecl.setBody(new BlockStmt());
            classDeclaration.addMember(methodDecl);

            System.out.println(interfaceType.getMethods().get(0));
        }


        return cu;
    }
}
