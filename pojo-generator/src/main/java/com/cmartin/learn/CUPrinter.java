package com.cmartin.learn;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.apache.commons.lang3.StringUtils.join;

/**
 * Created by cmartin on 12/05/2017.
 */
public class CUPrinter {

    public static final String SMC_PATH = "/Users/cmartin/projects/bbva-2017/stash/customerbudgets/src/main/java";
    private static final String JAVA_EXTENDSION = ".java";
    private static final String DTO_SELECTOR = "dto";
    private static final String TOKEN_SEPARATOR = " : ";

    public static void main(String[] args) throws IOException {
        //readAllFiles(SMC_PATH);

        readAllDirectories(SMC_PATH).forEach(path ->
                printInfo(path.getPath()));

        if (true) return;
        // creates an input stream for the file to be parsed
        FileInputStream in = new FileInputStream("/tmp/Money.java");

        // parse the file
        CompilationUnit cu = JavaParser.parse(in);

        // visit and print the methods names
        new MethodVisitor().visit(cu, null);

        new VariableVisitor().visit(cu, null);

        // prints the resulting compilation unit to default system output
        // System.out.println(cu.toString());
    }

    private static void printInfo(final String path) {
        try {
            FileInputStream in = new FileInputStream(path);
            CompilationUnit cu = JavaParser.parse(in);
            new ClassVisitor().visit(cu, null);
            ToStringFinder toStringFinder = new ToStringFinder();
            toStringFinder.visit(cu, null);
            System.out.println(join("hasToStringMethod: ", toStringFinder.hasToStringMethod()));
            new VariableVisitor().visit(cu, null);
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


    private static void readAllFiles(final String path) throws IOException {
        Files.list(Paths.get(path))
                .filter(Files::isRegularFile)
                //.filter(f -> f.getFileName().endsWith(".java"))
                .forEach(System.out::println);
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

}
