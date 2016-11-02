package com.hlee.scratch;

public class EnumMethodEx {

    public static void main(String[] args) {
        MyEnum e = MyEnum.SOME_ENUM_CONSTANT;
        testEnumMethod(e);
        e = MyEnum.ANOTHER_ENUM_CONSTANT;
        testEnumMethod(e);
    }

    enum MyEnum {
        SOME_ENUM_CONSTANT {
            @Override
            public void method() {
                System.out.println("first enum constant behavior!");
            }
        },
        ANOTHER_ENUM_CONSTANT {
            @Override
            public void method() {
                System.out.println("second enum constant behavior!");
            }
        };

        public abstract void method(); // could also be in an interface that
                                       // MyEnum implements
    }

    static void testEnumMethod(final MyEnum e) {
        // doSomeStuff();
        e.method(); // here is where the switch would be, now it's one line of
                    // code!
        // doSomeOtherStuff();
    }
}
