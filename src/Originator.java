import Tokenizer.IState;
import Tokenizer.Memento;

public class Originator {
    private IState state;

    public void setState(IState state) {
        this.state = state;
    }

    public IState getState() {
        return state;
    }

    public Memento createMemento(){
        return new Memento(state);
    }

    public void setMemento(Memento memento){
        state = memento.getState();
    }
}
