import app.App;

public class Engine {
    public static void main(String[] args) {
        try {
            new App();
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}