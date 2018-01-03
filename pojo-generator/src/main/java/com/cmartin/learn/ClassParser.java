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
import java.util.stream.Collectors;

import static com.cmartin.learn.PathConstants.JAVA_PKG_PATH;
import static com.cmartin.learn.PathConstants.JAVA_SRC_PATH;
import static org.apache.commons.lang3.StringUtils.LF;
import static org.apache.commons.lang3.StringUtils.join;

public class ClassParser {
    private static final String CLASS_NAME = "VehicleServiceImpl";

    private static final String INTERFACE_PATHNAME =
            JAVA_SRC_PATH.value() + JAVA_PKG_PATH.value() + CLASS_NAME + ".java";

    private final List<ImmutablePair<String, String>> lines = new ArrayList<>();

    public static void main(String[] args) {
        ClassParser parser = new ClassParser();
        System.out.println(join("START: ", ClassParser.class, LF));
        parser.printInterface(INTERFACE_PATHNAME);
        System.out.println(join(LF, "STOP: ", ClassParser.class));
    }

    public void printInterface(final String pathname) {
        CompilationUnit compilationUnit = this.parseInterface(pathname);
        Optional<ClassOrInterfaceDeclaration> vehicleService = compilationUnit.getClassByName(CLASS_NAME);
        ClassOrInterfaceDeclaration vi = vehicleService.orElseThrow(() -> new RuntimeException("No class found"));

        this.addLine("class name", vi.getNameAsString());
        //processMembers(vi.getMembers());

        vi.getMembers().stream()
        .filter(BodyDeclaration::isFieldDeclaration).forEach(m -> System.out.println(m));
        vi.getMembers().stream()
        .filter(BodyDeclaration::isMethodDeclaration).forEach(m -> System.out.println(m));


        this.printUnit();

    }

    private CompilationUnit parseInterface(final String pathname) {
        File file = new File(pathname);

        if (file.exists() && file.canRead()) {
            try {
                return JavaParser.parse(file);
            } catch (FileNotFoundException e) {
                throw new RuntimeException("Error while trying to access file: " + pathname, e);
            }
        } else {
            throw new RuntimeException("File does not exist or has not permission to read: " + pathname);
        }
    }

    private void processMembers(final NodeList<BodyDeclaration<?>> members) {
        this.addLine("class has member count", String.valueOf(members.size()));
        members.forEach(decl -> this.processMember(decl));
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


}
