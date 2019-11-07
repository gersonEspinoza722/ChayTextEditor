package Tokenizer;

import Strategy.ColorStrategy;
import Strategy.IStrategy;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.util.ArrayList;


public class TextManager {
    private ArrayList<ArrayList<WordObject>> words; //IList?
    private boolean tokenized;
    private MementoManager history;

    public TextManager() {
        this.tokenized=false;
        this.words=new ArrayList<>();
        this.history = new MementoManager(20);
    }

    public Memento createMemento(){
        Memento newMemento = new Memento(new TextSnapshot(words)); //Factory de estados?
        history.saveMementoBefore(newMemento);
        return newMemento;
    }

    public void restore(Memento memento){
        //if dependiendo del tipo?
        TextSnapshot textSnapshot = (TextSnapshot) memento.getState();
        words = textSnapshot.getWords();
    }

    public void undo(){
        restore(history.undo());
    }

    public void redo(){
        restore(history.redo());
    }

    public ArrayList<ArrayList<WordObject>> getWords() {
        return words;
    }

    public void setWords(ArrayList<ArrayList<WordObject>> words) {
        this.words = words;
    }

    public boolean isTokenized() {
        return tokenized;
    }

    public void setTokenized(boolean tokenized) {
        this.tokenized = tokenized;
    }

    public void tokenize(JTextPane t){
        String [] renglonesCompletos = t.getText().split("\n");
        ArrayList<String[]> renglonesTokenizados=new ArrayList<>();
        for (int i = 0; i < renglonesCompletos.length; i++){
            renglonesTokenizados.add(renglonesCompletos[i].split(" "));
        }
        if(words.size()==0){
            for(int j =0; j<renglonesTokenizados.size();j++){
                ArrayList<WordObject> row = new ArrayList<>();
                for(int x = 0; x<renglonesTokenizados.get(j).length;x++){

                    //System.out.println(renglonesTokenizados.get(j)[x]);
                    IStrategy strategy = new ColorStrategy(Color.BLACK);
                    WordObject wordToken = new WordObject(renglonesTokenizados.get(j)[x],strategy);
                    //System.out.println(wordToken.getContent());
                    row.add(wordToken);
                    //System.out.println(row.size());
                }
                words.add(row);
                //System.out.println(words.size());
            }
            tokenized=true;
        }


    }
    public void processColor(JTextPane t) {
        int selectionPos = t.getSelectionStart();
        int wordCounter=0;

        t.setText("");
        //System.out.println("selección: "+selectionPos);
        for(int r=0;r<words.size();r++){
            for(int w=0;w<words.get(r).size();w++){
                //System.out.println("contador: "+wordCounter);
                if(wordCounter==selectionPos){
                    IStrategy strategy = new ColorStrategy(Color.MAGENTA);
                    words.get(r).get(w).setColor(strategy);
                }
                appendToPane(t,words.get(r).get(w).getContent(),(Color) words.get(r).get(w).getColor().doGetInfo());
                if(w!=words.get(r).size()-1){
                    appendToPane(t," ",Color.BLACK);
                }

                wordCounter+=words.get(r).get(w).getContent().length();
                if(words.get(r).size()>1 && w<words.get(r).size()-1){
                    wordCounter+=1;
                }

            }
            if(tokenized==true){
                wordCounter+=1;
            }

            appendToPane(t,"\n",Color.BLACK);
        }
    }
    private void appendToPane(JTextPane tp, String msg, Color c)
    {
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);

        aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Lucida Console");
        aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);

        int len = tp.getDocument().getLength();
        tp.setCaretPosition(len);
        tp.setCharacterAttributes(aset, false);
        tp.replaceSelection(msg);
    }

}
