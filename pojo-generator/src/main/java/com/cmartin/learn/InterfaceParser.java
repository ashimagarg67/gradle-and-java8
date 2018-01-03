package com.cmartin.learn;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.comments.JavadocComment;
import com.github.javaparser.ast.type.ReferenceType;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.apache.commons.lang3.StringUtils.LF;
import static org.apache.commons.lang3.StringUtils.join;

public class InterfaceParser {

    private static final String JAVA_SRC_PATH = "src/main/java/";
    private static final String JAVA_PKG_PATH = "com/cmartin/learn/types/";

    private static final String INTERFACE_PATHNAME =
            JAVA_SRC_PATH + JAVA_PKG_PATH + "VehicleService.java";

    private final List<ImmutablePair<String, String>> lines = new ArrayList<>();

    public void parseInterface(final String pathname) throws FileNotFoundException {
        CompilationUnit compilationUnit = JavaParser.parse(new File(pathname));
        Optional<ClassOrInterfaceDeclaration> vehicleService = compilationUnit.getInterfaceByName("VehicleService");
        ClassOrInterfaceDeclaration vi = vehicleService.orElseThrow(() -> new RuntimeException("No interface found"));

        this.addLine("interface name", vi.getNameAsString());
        NodeList<BodyDeclaration<?>> members = vi.getMembers();
        processMembers(members);

        this.printUnit();

    }

    private void processMembers(final NodeList<BodyDeclaration<?>> members) {
        this.addLine("interface has member count", String.valueOf(members.size()));
        members.forEach(decl -> this.processMember(decl)
        );
    }

    private void processMember(final BodyDeclaration<?> decl) {
        MethodDeclaration md = decl.asMethodDeclaration();
        if (md.isMethodDeclaration()) {
            this.addLine(" ·method name", md.getNameAsString());
            this.addLine(" ·method type", md.getType().toString());

            md.getParameters().forEach(p -> this.processMemberParameter(p));

            md.getThrownExceptions().forEach(e -> this.processMemberException(e));

            this.addLine(" ·method comment",
                    decl.getComment()
                            .orElse(new JavadocComment(""))
                            .toString());
        }
    }

    private void processMemberException(final ReferenceType e) {
        this.addLine(" ··method exception", e.asReferenceType().asString());
    }

    private void processMemberParameter(final Parameter parameter) {
        this.addLine(" ··param type", parameter.getType().asString());
        this.addLine(" ··param name", parameter.getNameAsString());
    }


    private void printUnit() {
        this.lines.stream()
                .forEach(l -> System.out.println(join(l.left, ": ", l.right)));
    }

    private void addLine(final String description, final String objectRepresentation) {
        this.lines.add(new ImmutablePair<>(description, objectRepresentation));
    }


    public static void main(String[] args) throws FileNotFoundException {
        InterfaceParser parser = new InterfaceParser();
        System.out.println(join("START: ", InterfaceParser.class, LF));
        parser.parseInterface(INTERFACE_PATHNAME);
        System.out.println(join(LF, "STOP: ", InterfaceParser.class));
    }
}
