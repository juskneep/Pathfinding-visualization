public interface AlgorithmFactory {
    void Run();
    void findPath();
    boolean isRunning();
    void addBorder(int xCoor, int yCoor);
    void removeBorder(int xCoor, int yCoor);
    boolean exist(int rowIndex, int colIndex);
    boolean isWall(int rowIndex, int colIndex);
    boolean isStartCell(int x, int y);
}