import ToolStore.Tests.StoreTest;

public class Main
{
    public static void main(String[] args)
    {
        StoreTest tests = new StoreTest();

        tests.checkoutExceptionTest();

        try {
            tests.checkoutTest();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}