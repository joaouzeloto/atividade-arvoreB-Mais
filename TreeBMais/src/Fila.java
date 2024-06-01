public class Fila
{
    private NoFila inicio,fim;

    public Fila(){inicio=fim=null;}

    public void inserir(NoList no)
    {
        NoFila novo = new NoFila(no);
        if(inicio==null)
            inicio = fim = novo;
        else
        {
            novo.setAnt(fim);
            fim.setProximo(novo);
            fim = novo;
        }
    }

    public NoList retirar()
    {
        NoList aux= inicio.getNo();
        inicio = inicio.getProximo();
        return aux;
    }

    public boolean vazio(){return inicio==null;}


}
