public class TreeMais
{
    private No raiz;

    public TreeMais(){raiz = null;}

    private No navegarAteFolha(int info)
    {
        int pos;
        No aux = raiz;
        while (aux.getvLig(0)!=null)
        {
            pos = aux.procuraPosicao(info);
            aux = aux.getvLig(pos);
        }
        return aux;
    }

    private No localizarPai(No folha, int info)
    {
        No no, pai;
        int pos;
        no = pai = raiz;
        while (no!=folha)
        {
            pai = no;
            pos = no.procuraPosicao(info);
            no = no.getvLig(pos);
        }
        return pai;
    }

    public No localizarSubE(No no,int info)
    {
        no = no.getvLig(info);
        while (no.getvLig(0)!=null)
            no = no.getvLig(no.getTl()-1);
        return no;
    }

    public No localizarSubD(No no,int info)
    {
        no = no.getvLig(info);
        while (no.getvLig(0)!=null)
            no = no.getvLig(0);
        return no;
    }

    public void split(No folha,No pai)
    {
        int posPai,conta;
        No irmaD=null,irmaE=null;

        if(folha.getvLig(0)==null)
        {
            NoList cx1,cx2;
            posPai =  pai.procuraPosicao(folha.getvInfo(1));
            if(posPai>0)
                irmaE = pai.getvLig(posPai-1);
            if(posPai+1<=pai.getTl())
                irmaD =pai.getvLig(posPai+1);
             cx1 = new NoList();
             cx2 = new NoList();
             conta = (int) Math.ceil((No.N-1)/2);

            for(int i=0;i<conta;i++)
            {
                cx1.setvInfo(i,folha.getvInfo(i));
                cx1.setTl(cx1.getTl()+1);
            }
            for (int i=conta,j=0;i<folha.getTl();i++,j++)
            {
                cx2.setvInfo(j,folha.getvInfo(i));
                cx2.setTl(cx2.getTl()+1);
            }
            cx1.setProx(cx2);
            cx1.setAnt((NoList) irmaE);
            cx2.setProx((NoList) irmaD);
            cx2.setAnt(cx1);
            if(irmaE!=null)
                ((NoList) irmaE).setProx(cx1);
            if(irmaD!=null)
                 ((NoList) irmaD).setAnt(cx2);
            if(folha==pai)
            {
                folha.setvInfo(0,cx2.getvInfo(0));
                folha.setTl(1);
                folha.setvLig(0,cx1);
                folha.setvLig(1,cx2);
            }
            else
            {
                int pos = pai.procuraPosicao(cx2.getvInfo(0));
                pai.remanejar(pos);
                pai.setvInfo(pos,cx2.getvInfo(0));
                pai.setTl(pai.getTl()+1);
                pai.setvLig(pos,cx1);
                pai.setvLig(pos+1,cx2);
                if(pai.getTl()>No.N-1)
                    split(pai,localizarPai(pai,pai.getvInfo(0)));
            }
        }
        else
        {
            No cx1, cx2;
            cx1 = new No();
            cx2 = new No();
            conta = (int) Math.ceil((No.N)/2-1);
            for(int i=0;i<conta;i++)
            {
                cx1.setvInfo(i,folha.getvInfo(i));
                cx1.setvLig(i,folha.getvLig(i));
                cx1.setTl(cx1.getTl()+1);
            }
            cx1.setvLig(conta,folha.getvLig(conta));
            for(int i=conta+1,j=0;i<folha.getTl();i++,j++)
            {
                cx2.setvInfo(j,folha.getvInfo(i));
                cx2.setvLig(j,folha.getvLig(i));
                cx2.setTl(cx2.getTl()+1);
            }
            cx2.setvLig(cx2.getTl(),folha.getvLig(folha.getTl()));

            if(folha==pai)
            {
                folha.setvInfo(0,folha.getvInfo(conta));
                folha.setvLig(0,cx1);
                folha.setvLig(1,cx2);
                folha.setTl(1);
            }
            else
            {
                int pos = pai.procuraPosicao(folha.getvInfo(conta));
                pai.remanejar(pos);
                pai.setvInfo(pos,folha.getvInfo(conta));
                pai.setvLig(pos,cx1);
                pai.setvLig(pos+1,cx2);
                pai.setTl(pai.getTl()+1);
                if(pai.getTl()>No.N-1)
                    split(pai,localizarPai(pai,pai.getvInfo(0)));
            }
        }
    }

    public void inserir(int info)
    {
        int pos;
        No pai, folha;

        if(raiz==null)
        {
            No novo = new No(info);
            raiz = novo;
        }
        else
        {
            folha = navegarAteFolha(info);
            pos = folha.procuraPosicao(info);
            folha.remanejar(pos);
            folha.setvInfo(pos,info);
            folha.setTl(folha.getTl()+1);
            if(folha.getTl()>No.N-1)
            {
                pai = localizarPai(folha,info);
                split(folha,pai);
            }
        }
    }

    public void exibir()
    {
        No aux = raiz, pai = null;
        int sinal = 0;
        NoList auxList = null;

        // Percorre até o nó mais à esquerda
        while (aux.getvLig(0) != null)
        {
            pai = aux;
            aux = aux.getvLig(0);
        }

        if (pai != null && pai.getvLig(0) instanceof NoList)
        {
            auxList = (NoList) pai.getvLig(0);
        }
        else
        {
            auxList = null;
            for (int i = 0; i < aux.getTl(); i++)
            {
                System.out.print(aux.getvInfo(i) + " ");
            }
        }

        while (auxList != null)
        {
            for (int i = 0; i < auxList.getTl(); i++)
            {
                System.out.print(auxList.getvInfo(i) + " ");
            }
            System.out.println();
            auxList = auxList.getProx();
        }
    }


    public void exibirTudo(No raizI)
    {
        if(raizI!=null)
        {
            for(int i=0;i<raizI.getTl();i++)
                System.out.print(raizI.getvInfo(i)+" ");
            System.out.println();
            for(int i=0;i<=raizI.getTl();i++)
                exibirTudo(raizI.getvLig(i));
        }
    }
    public void exibirTudo()
    {
        exibirTudo(raiz);
    }


}
