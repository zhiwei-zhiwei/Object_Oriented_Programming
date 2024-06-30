public class Printer implements IPrinter{
    private Cartridge cartridge;

    public Printer(Cartridge cartridge) {
        this.cartridge = cartridge;
    }

    public void prepare() {
        System.out.println("Printer is preparing to print");
        cartridge.useCartridge();
    }

    @Override
    public void print() {
        System.out.println("Printer is printing");
    }
}
