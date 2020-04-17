public class Test {

    public static void main(String[] args) {

        String str = "qwe\\qwe\\qwe\\qwe";

        System.out.println("str = " + str);

        str = str.replace('\\', '/');

        System.out.println("str = " + str);

    }

}
