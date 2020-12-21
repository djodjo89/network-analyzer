package model.file.output;

import model.network.champ.IChamp;

public class IJSONChamp {
    private IChamp champ;

    public IJSONChamp(IChamp champ) {
        this.champ = champ;
    }

    public String getEtiquette() {
        return champ.getEtiquette();
    }

    public String getValeur() {
        return champ.getValeur();
    }
}
