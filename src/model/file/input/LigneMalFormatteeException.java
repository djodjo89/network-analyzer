package model.file.input;

public class LigneMalFormatteeException extends Exception {
    public LigneMalFormatteeException(int position) {
        super("La ligne n°" + position + " est incomplète");
    }
}
