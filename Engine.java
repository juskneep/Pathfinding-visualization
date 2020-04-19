import frame.Visualization;

public class Engine {
    public static void main(String[] args) {
        try {
            new Visualization();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}