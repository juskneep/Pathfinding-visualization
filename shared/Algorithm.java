package shared;

import java.util.Collection;

import algorithms.Node;
import exceptions.InvalidStartPointException;

public interface Algorithm {

	Collection<Node> getBorders();

    Collection<Node> getOpenList();

    Collection<Node> getClosedList();

    Collection<Node> getPathToGoalCollection();

	void addEndPoint(Node goalNode);

	void addStartPoint(Node startNode);

	void addBorder(Node node);

	void removeBorder(int xPosition, int yPosition);

	boolean isRunning();

	void run();

	void stop();

	void changeDiagonalPref(boolean pref);

	void findPath() throws InvalidStartPointException;

}