public class Originator {
    private IState state;

    public void setState(IState state) {
        this.state = state;
    }

    public IState getState() {
        return state;
    }

    public Memento saveStateToMemento(){
        return new Memento(state);
    }

    public void getStateFromMemento(Memento memento){
        state = memento.getState();
    }
}
