public class No
{
    static final int N = 5;
    private int[] vInfo;
    private No[] vLig;
    private int tl;

    public No()
    {
        this.vInfo = new int[N];
        this.vLig = new No[N+1];
        this.tl = 0;
    }

    public No(int info)
    {
        this();
        vInfo[0] = info;
        tl =1;
    }

    public int getvInfo(int i) {
        return vInfo[i];
    }

    public void setvInfo(int pos,int i) {
        vInfo[pos] = i;
    }

    public No getvLig(int i) {
        return vLig[i];
    }

    public void setvLig(int pos,No i) {
        vLig[pos] = i;
    }

    public int getTl() {
        return tl;
    }

    public void setTl(int tl) {
        this.tl = tl;
    }

    public int procuraPosicao(int info)
    {
        int pos = 0;
        while (pos<tl&&info>vInfo[pos])
            pos++;
        return pos;
    }

    public void remanejar(int pos)
    {
        vLig[tl+1] = vLig[tl];
        for(int i = tl;i>pos;i--)
        {
            vInfo[i] = vInfo[i-1];
            vLig[i] = vLig[i-1];
        }
    }

}
