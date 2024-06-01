public class NoFila
{
    private NoList no;
    private NoFila proximo;

    public NoFila getAnt() {
        return ant;
    }

    public void setAnt(NoFila ant) {
        this.ant = ant;
    }

    private NoFila ant;

    public NoFila(NoList noI){no=noI;proximo=null;}

    public NoList getNo() {
        return no;
    }

    public void setNo(NoList no) {
        this.no = no;
    }

    public NoFila getProximo() {
        return proximo;
    }

    public void setProximo(NoFila proximo) {
        this.proximo = proximo;
    }
}
