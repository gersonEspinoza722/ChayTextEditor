package Tokenizer;

import Prototype.IPrototype;

import java.util.ArrayList;

public class WordsLineArray implements WordsLineListing {

    private ArrayList<ArrayList<WordObject>> lines;

    public WordsLineArray() {
        lines = new ArrayList<>();
    }


    @Override
    public void addLine(ArrayList line) {
        lines.add(line);
    }

    @Override
    public Object getLine(int index) {
        return lines.get(index);
    }

    @Override
    public ArrayList<WordObject> removeLine(int index) {
        return lines.remove(index);
    }

    @Override
    public ArrayList<ArrayList<WordObject>> getWordsLineList() {
        return lines;
    }

    @Override
    public int getSize() {
        return lines.size();
    }

    @Override
    public IPrototype clone() {
        return null;
    }

    @Override
    public IPrototype deepClone() {
        ArrayList<ArrayList<WordObject>> newLines = new ArrayList<>();
        for (int i = 0; i<lines.size(); i++){
            ArrayList<WordObject> newLine = new ArrayList<>();
            for (int j = 0; j<lines.get(i).size(); j++){
                WordObject newWord = (WordObject) lines.get(i).get(j).deepClone();
                newLine.add(newWord);
            }
            newLines.add(newLine);
        }
        return (IPrototype) newLines;
    }
}
