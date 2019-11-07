package Tokenizer;

public class Memento implements IMemento{
    private IState state;

    public Memento(IState state){
        this.state = state;
    }

    public IMemento getState(){
        return this;
    }
}
