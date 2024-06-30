public interface ILog {
    default void log(){
        System.out.println("Now I'm logging");
    }
}
