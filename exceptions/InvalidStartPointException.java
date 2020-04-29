package exceptions;

import algorithms.Node;

public class InvalidStartPointException extends Exception {
    private static final long serialVersionUID = 1L;    
    private final static String message = "Cannot start the app with start node equals %s and goal node equals %s!";

    public InvalidStartPointException(Node startNode, Node goalNode) {
        super(String.format(message, startNode, goalNode));
    }
}