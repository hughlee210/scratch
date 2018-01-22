package com.hlee.scratch.corejava;

public class EnumMethodEx {

    public static void main(String[] args) {
        MyEnum e1 = MyEnum.SOME_ENUM_CONSTANT;
        testEnumMethod(e1);
        MyEnum e2 = MyEnum.ANOTHER_ENUM_CONSTANT;
        testEnumMethod(e2);
    }

    enum MyEnum {
        SOME_ENUM_CONSTANT {
            @Override
            public void method() {
                System.out.println("Some enum constant behavior!");
            }
        },
        ANOTHER_ENUM_CONSTANT {
            @Override
            public void method() {
                System.out.println("Another enum constant behavior!");
            }
        };

        public abstract void method(); // could also be in an interface that MyEnum implements
    }

    static void testEnumMethod(final MyEnum e) {
        // doSomeStuff();
        e.method(); // here is where the switch would be, now it's one line of code!
        // doSomeOtherStuff();
    }
}
