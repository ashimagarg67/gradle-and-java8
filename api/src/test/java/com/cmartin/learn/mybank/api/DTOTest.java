package com.cmartin.learn.mybank.api;


import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.PojoClassFilter;
import com.openpojo.reflection.impl.PojoClassFactory;
import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.rule.impl.GetterMustExistRule;
import com.openpojo.validation.test.impl.GetterTester;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class DTOTest {

    private Validator validator;
    private List<PojoClass> pojoClasses;
    private final ClassNameFilter classNameFilter = new ClassNameFilter();

    @Before
    public void setUp() {
        this.validator = ValidatorBuilder.create()
                //.with(new SetterMustExistRule())
                //.with(new SetterTester())
                .with(new GetterMustExistRule())
                .with(new GetterTester())
                .build();
    }

    @Test
    public void testFacadePojos() {
        this.pojoClasses = PojoClassFactory.getPojoClasses(this.getClass().getPackage().getName(), classNameFilter);
        this.validator.validate(this.pojoClasses);
    }


    private static class ClassNameFilter implements PojoClassFilter {

        @Override
        public boolean include(PojoClass pojoClass) {
            return pojoClass.getClazz().getSimpleName().endsWith("Dto");
        }
    }

    /*


    public static List<PojoClass> getOpenPojoClasses(Class clazz) {
        return getOpenPojoClasses(clazz.getPackage().getName());
    }

    public static List<PojoClass> getOpenPojoClasses(String packageName) {
        return PojoClassFactory.getPojoClassesRecursively(packageName, new FilterPackageInfo());
    }


    public static Validator buildSimplePojoValidator() {
        return ValidatorBuilder.create()
                .with(new GetterMustExistRule())
                .with(new SetterMustExistRule())
                .with(new SetterTester())
                .with(new GetterTester())
                .build();
    }




     */
}
