package se.iths.entity;

public class InvalidIdException extends Exception{

    public InvalidIdException(){
        super("Object with this id dont exist, try another Id ");
    }
}
