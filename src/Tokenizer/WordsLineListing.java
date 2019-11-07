package Tokenizer;

import Prototype.IPrototype;

import java.util.ArrayList;

public interface WordsLineListing extends IPrototype<WordsLineListing> {
        void addLine(ArrayList line);
        Object getLine(int index);
        ArrayList<WordObject> removeLine(int index);
        ArrayList<ArrayList<WordObject>> getWordsLineList();
        int getSize();
}
