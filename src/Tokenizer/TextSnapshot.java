package Tokenizer;

import Tokenizer.IState;
import Tokenizer.WordObject;

import java.util.ArrayList;

public class TextSnapshot implements IMemento {

    private ArrayList<ArrayList<WordObject>> words;

    public TextSnapshot(ArrayList<ArrayList<WordObject>> words) {
        this.words = words;
    }

    @Override
    public IMemento getState() {
        return this;
    }

    public ArrayList<ArrayList<WordObject>> getWords() {
        return words;
    }
}
