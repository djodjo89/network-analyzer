package model.file.input;

public class LigneIncompleteException extends Exception {
    public LigneIncompleteException(int position) {
        super("La ligne n°" + position + " est incomplète");
    }
}
