package com.cmartin.learn;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import org.apache.commons.lang3.text.WordUtils;

public class ServiceBuilder {
    private final String packageName;
    private final String name;
    //private final List<DtoBuilder.PropertyDef> properties = new ArrayList<>();

    public ServiceBuilder(String packageName, String name) {
        this.packageName = packageName;
        this.name = name;
    }

    MethodDeclaration md = new MethodDeclaration();

    public ServiceBuilder addOperation(String name, String serviceRequest, String serviceResponse) {
        md.setName(name);
        md.setType(serviceResponse);
        md.addParameter(serviceRequest, WordUtils.uncapitalize(serviceRequest));
        return this;
    }

    public CompilationUnit build() {
        CompilationUnit cu = new CompilationUnit(packageName);
        ClassOrInterfaceDeclaration srv = cu.addInterface(name);
        srv.addMember(md);
        return cu;
    }
}
