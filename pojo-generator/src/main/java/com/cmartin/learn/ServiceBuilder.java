package com.cmartin.learn;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.javadoc.Javadoc;
import com.github.javaparser.javadoc.JavadocBlockTag;
import com.github.javaparser.javadoc.description.JavadocDescription;
import org.apache.commons.lang3.text.WordUtils;

import java.util.ArrayList;
import java.util.List;

public class ServiceBuilder {
    private final String packageName;
    private final String name;
    private final List<MethodDeclaration> methods = new ArrayList<>();

    public ServiceBuilder(String packageName, String name) {
        this.packageName = packageName;
        this.name = name;
    }


    public ServiceBuilder addOperation(String name, String serviceRequest, String serviceResponse) {
        MethodDeclaration md = new MethodDeclaration();
        md.setName(name);
        md.setType(serviceResponse);
        md.addParameter(serviceRequest, WordUtils.uncapitalize(serviceRequest));
        md.removeBody();

        Javadoc doc = new Javadoc(JavadocDescription.parseText("bla, bla, bla"))
                .addBlockTag(new JavadocBlockTag(JavadocBlockTag.Type.PARAM, serviceRequest))
                .addBlockTag(new JavadocBlockTag(JavadocBlockTag.Type.RETURN, serviceResponse));
        md.setJavadocComment(doc.toText());

        this.methods.add(md);
        return this;
    }

    public CompilationUnit build() {
        CompilationUnit cu = new CompilationUnit(packageName);
        ClassOrInterfaceDeclaration srv = cu.addInterface(name);
        methods.forEach(m -> srv.addMember(m));
        return cu;
    }
}
