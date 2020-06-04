package com.chess.exceptions;

public class InvalidMovementException extends Exception {
    /**
     * Throws invalid movement
     */
    public InvalidMovementException(){
        System.out.println("Invalid movement!");
    }
}
