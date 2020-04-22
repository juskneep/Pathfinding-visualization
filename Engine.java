import frame.Frame;

public class Engine {
    public static void main(String[] args) {
        try {
            new Frame();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}