public class Cartridge implements ICartridge{
    private String model_no;
    private int num_pages;
    public Cartridge(String model_no, int num_pages) {
        this.model_no = model_no;
        this.num_pages = num_pages;
        System.out.println("Cartridge is "+ model_no);
    }

    @Override
    public void useCartridge() {
        System.out.println("The printer is using Cartridge");
    }
}
