public class ShoppingCart {
    private PaymentStrategy paymentMethod;

    public ShoppingCart(PaymentStrategy paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void checkout() {
        paymentMethod.pay();
    }

    // Main method for demonstration

}
