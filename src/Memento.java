public class Memento {
    private IState state;

    public Memento(IState state){
        this.state = state;
    }

    public IState getState(){
        return state;
    }
}
