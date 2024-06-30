// The original code provided was trying to add new payment ways required to the CallShoppingCart method,
// which violates the idea of the open-closed principle. The Class should be able to be modified only through
// derivation, or through internal modification of either its interface or its function. Here is the original code
// tried to add more "else if" statements to add new payment methods, which violates the open-closed principle.
//
// In the revised code, the Strategy Pattern fixes the problem by encapsulating payment behaviours within separate
// classes that implement a common interface. It allows as many payment methods as the genius boss would like
// to add in the online E-Tailer by creating new classes (for the new payment method), instead of modifying the
// original code. It follows the idea of the open-closed principle. It also makes the code easy to maintain.

public class Main {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart(new PayByPayPal());
        cart.checkout();

        cart = new ShoppingCart(new PayByCC());
        cart.checkout();

        cart = new ShoppingCart(new PaybyApplePay());
        cart.checkout();

        cart = new ShoppingCart(new PayBySEPA());
        cart.checkout();

        cart = new ShoppingCart(new PayByBitcoin());
        cart.checkout();
    }
}