package Tokenizer;

import java.util.Stack;

public class MementoManager {
    private Stack<IMemento> mementosBefore;
    private Stack<IMemento> mementosAfter;
    int memorySize;

    public MementoManager(int memorySize) {
        mementosBefore = new Stack<>();
        mementosAfter = new Stack<>();
        this.memorySize = memorySize;
    }

    public void saveMementoBefore(IMemento memento){
        if(mementosBefore.size()>memorySize){
            mementosBefore.remove(mementosBefore.peek());
        }
        mementosBefore.push(memento);
    }

    public void saveMementoAfter(IMemento memento){
        if(mementosAfter.size()>memorySize){
            mementosAfter.remove(mementosAfter.peek());
        }
        mementosAfter.push(memento);
    }

    public IMemento undo(){
        if(mementosBefore.empty() == false){
            IMemento memento = mementosBefore.pop(); //sacar memento anterior
            saveMementoAfter(memento);
            return memento;
        }
        else {
            return null;
        }
    }

    public IMemento redo(){
        if(mementosAfter.empty() == false){
            IMemento memento = mementosAfter.pop(); //sacar memento anterior
            saveMementoBefore(memento);
            return memento;
        }
        else {
            return null;
        }
    }

}
