import app.App;

public class Engine {
    public static void main(String[] args) {
        try {
            new App();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}