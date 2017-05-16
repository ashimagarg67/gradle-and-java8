package com.cmartin.learn;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.ReturnStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Stream;

import static org.apache.commons.lang3.StringUtils.join;
import static org.apache.commons.lang3.StringUtils.repeat;

/**
 * Created by cmartin on 12/05/2017.
 */
public class CUPrinter {

    public static final String SMC_PATH = "/Users/cmartin/projects/bbva-2017/stash/customerbudgets/src/main/java";
    private static final String JAVA_EXTENDSION = ".java";
    private static final String DTO_SELECTOR = "dto";
    private static final String TOKEN_SEPARATOR = " : ";

    public static void main(String[] args) throws IOException {
        System.out.println("-------------------> " + CUPrinter.class.getSimpleName());
        //readAllFiles(SMC_PATH);

        // show DTOs info
        //readAllDirectories(SMC_PATH).forEach(path -> printInfo(path.getPath()));
        //if (true) return;

        // creates an input stream for the file to be parsed
        FileInputStream in = new FileInputStream("/Users/cmartin/projects/bbva-2017/stash/customerbudgets/src/main/java/com/bbva/epcl/customerbudgets/business/dto/DTOIntCustomerBudgetCopy.java");

        // parse the file
        CompilationUnit cu = JavaParser.parse(in);

        // visit and print the methods names
        //new MethodVisitor().visit(cu, null);

        ToStringMethodGenerator generator = new ToStringMethodGenerator();
        generator.visit(cu, null);
        System.out.println("property count: " + generator.getPropertyCount());
        generator.generateToStringMethod();

        // prints the resulting compilation unit to default system output
        // System.out.println(cu.toString());
    }

    private static void printInfo(final String path) {
        try {
            FileInputStream in = new FileInputStream(path);
            CompilationUnit cu = JavaParser.parse(in);
            new ClassVisitor().visit(cu, null);
            new FieldDeclarationVisitor().visit(cu, null);
            ToStringFinder toStringFinder = new ToStringFinder();
            toStringFinder.visit(cu, null);
            System.out.println(join("hasToStringMethod: ", toStringFinder.hasToStringMethod()));
            //new VariableVisitor().visit(cu, null);
            System.out.println();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void printVariables(final String path) {
        FileInputStream in = null;
        try {
            in = new FileInputStream(path);
            CompilationUnit cu = JavaParser.parse(in);
            new ClassVisitor().visit(cu, null);
            new VariableVisitor().visit(cu, null);
            System.out.println();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static Stream<File> readAllDirectories(final String path) throws IOException {

        return Files.walk(Paths.get(path), FileVisitOption.FOLLOW_LINKS)
                .map(Path::toFile)
                .filter(File::isFile)
                .filter(f -> f.getPath().contains(DTO_SELECTOR))
                .filter(f -> f.getName().endsWith(JAVA_EXTENDSION));
        //.forEach(System.out::println);
    }


    private static class ToStringFinder extends VoidVisitorAdapter<Void> {
        private Boolean result = false;

        @Override
        public void visit(ClassOrInterfaceDeclaration c, Void arg) {
            //TODO refactor hasToStringMethod()
            result = c.getMethods()
                    .stream()
                    .map(method -> method.getNameAsString())
                    .anyMatch(name -> name.equals("toString"));
            //.filter(name -> name.equals("toString"));
            //.forEach(e -> System.out.println("method: " + e.toString() + " - " + e.getClass()));
            //
            super.visit(c, arg);
        }

        public Boolean hasToStringMethod() {
            return result;
        }
    }

    private static class ClassVisitor extends VoidVisitorAdapter<Void> {
        @Override
        public void visit(ClassOrInterfaceDeclaration c, Void arg) {
            System.out.println(c.getNameAsString());
            super.visit(c, arg);
        }
    }

    private static class FieldDeclarationVisitor extends VoidVisitorAdapter<Void> {
        @Override
        public void visit(FieldDeclaration fd, Void arg) {
            if (!fd.getModifiers().contains(Modifier.STATIC)) {
                System.out.println(join("field: ", fd.getVariable(0).getNameAsString(), ", type: ", fd.getElementType()));
            }
            super.visit(fd, arg);
        }
    }


    /**
     * Simple visitor implementation for visiting MethodDeclaration nodes.
     */
    private static class MethodVisitor extends VoidVisitorAdapter<Void> {
        @Override
        public void visit(MethodDeclaration n, Void arg) {
            /* here you can access the attributes of the method.
             this method will be called for all methods in this
             CompilationUnit, including inner class methods */
            System.out.println(n.getName());
            super.visit(n, arg);
        }
    }

    private static class VariableVisitor extends VoidVisitorAdapter<Void> {
        @Override
        public void visit(VariableDeclarator v, Void arg) {
            System.out.println(join(v.getName(), TOKEN_SEPARATOR, v.getType()));
            super.visit(v, arg);
        }
    }


    private static class ToStringMethodGenerator extends VoidVisitorAdapter<Void> {
        private MethodDeclaration md = new MethodDeclaration();
        private List<FieldDeclaration> fields = new ArrayList<>();

        @Override
        public void visit(FieldDeclaration fd, Void arg) {
            EnumSet<Modifier> modifiers = fd.getModifiers();

            if (!modifiers.contains(Modifier.STATIC) && (modifiers.contains(Modifier.PRIVATE))) {
                String nameAsString = fd.getVariable(0).getNameAsString();
                System.out.println(join("field: ", nameAsString, ", type: ", fd.getElementType()));
                this.fields.add(fd);
            }
            super.visit(fd, arg);
        }

        public Integer getPropertyCount() {
            return this.fields.size();
        }

        public String getToString() {
            return md.toString();
        }

        public void generateToStringMethod() {
            md.setPublic(true);
            md.setType(String.class.getSimpleName());
            md.setName("toString");
            md.addMarkerAnnotation("Override");

            StringBuilder sb = new StringBuilder("new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)");
            for (FieldDeclaration field : this.fields) {
                sb.append(createAppendStatement(field));
            }
            sb.append(join('\n', repeat(' ', 8), ".toString()"));

            BlockStmt blockStmt = new BlockStmt();
            md.setBody(blockStmt);
            blockStmt.addStatement(new ReturnStmt(sb.toString()));

            System.out.println(this.getToString());
        }

        private String createAppendStatement(final FieldDeclaration fieldDeclaration) {
            String typename = fieldDeclaration.getCommonType().toString();
            String fieldname = fieldDeclaration.getVariable(0).getNameAsString();
            String subStatement = join('\n', repeat(' ', 8), ".append(\"", fieldname, "\", ", fieldname);

            if (typename.startsWith("List")) {
                return join(subStatement, ".size())");
            } else {
                return join(subStatement, ")");
            }
        }
    }

}