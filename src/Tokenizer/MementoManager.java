package Tokenizer;

import java.util.Stack;

public class MementoManager {
    private Stack<Memento> mementosBefore;
    private Stack<Memento> mementosAfter;
    int memorySize;

    public MementoManager(int memorySize) {
        mementosBefore = new Stack<>();
        mementosAfter = new Stack<>();
        this.memorySize = memorySize;
    }

    public void saveMementoBefore(Memento memento){
        if(mementosBefore.size()>memorySize){
            mementosBefore.remove(mementosBefore.peek());
        }
        mementosBefore.push(memento);
    }

    public void saveMementoAfter(Memento memento){
        if(mementosAfter.size()>memorySize){
            mementosAfter.remove(mementosAfter.peek());
        }
        mementosAfter.push(memento);
    }

    public Memento undo(){
        if(mementosBefore.empty() == false){
            Memento memento = mementosBefore.pop(); //sacar memento anterior
            saveMementoAfter(memento);
            return memento;
        }
        else {
            return null;
        }
    }

    public Memento redo(){
        if(mementosAfter.empty() == false){
            Memento memento = mementosAfter.pop(); //sacar memento anterior
            saveMementoBefore(memento);
            return memento;
        }
        else {
            return null;
        }
    }

}
