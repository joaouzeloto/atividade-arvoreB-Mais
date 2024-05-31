public class NoList extends No
{
    private NoList ant, prox;

    public NoList()
    {
        super();
        ant = null;
        prox = null;
    }

    public NoList getAnt() {
        return ant;
    }

    public void setAnt(NoList ant) {
        this.ant = ant;
    }

    public NoList getProx() {
        return prox;
    }

    public void setProx(NoList prox) {
        this.prox = prox;
    }
}
