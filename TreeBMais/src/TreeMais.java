public class TreeMais
{
    private No raiz;

    public TreeMais(){raiz = null;}

    public No navegarAteFolha(int info)
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

    public No localizarPai(No folha, int info)
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
            posPai =  pai.procuraPosicao(folha.getvInfo(0));
            if(posPai>0)
                irmaE = pai.getvLig(posPai-1);
            if(posPai+1<=pai.getTl())
                irmaD =pai.getvLig(posPai+1);
             cx1 = new NoList();
             cx2 = new NoList();
             conta = (int) Math.ceil((No.N-1)/2.0);

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
            conta = (int) Math.ceil((No.N)/2.0-1);
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

    public NoList navegarAteFolhaPorListas(int ele)
    {
        No aux = raiz,pai=null;
        NoList auxList;
        if(raiz!=null)
        {
            while (aux.getvLig(0)!=null)
            {
                pai = aux;
                aux = aux.getvLig(0);
            }

                if (pai != null && pai.getvLig(0) instanceof NoList)
                {
                    auxList = (NoList) pai.getvLig(0);
                    while(auxList!=null)
                    {
                        if(ele>=auxList.getvInfo(0)&&ele<=auxList.getvInfo(aux.getTl()-1))
                            return auxList;
                        else
                            auxList = auxList.getProx();
                    }
                    return null;
                }
                return (NoList) aux;

        }
        return null;
    }

    public void conectarListas() {
        if (raiz == null || raiz.getvLig(0) == null) {
            return;
        }

        No aux = raiz;
        No esq, dir;
        No pai;

        for (int i = 0; i < aux.getTl(); i++) {
            esq = aux.getvLig(i);
            while (esq.getvLig(0) != null) {
                esq = esq.getvLig(esq.getTl());
            }

            NoList n1 = (NoList) esq;

            if (i + 1 < aux.getTl()) {
                dir = aux.getvLig(i + 1);
                while (dir.getvLig(0) != null) {
                    dir = dir.getvLig(0);
                }

                NoList n2 = (NoList) dir;

                n1.setProx(n2);
                n2.setAnt(n1);
            }
        }

        // Conectar o último nó da última folha
        esq = aux.getvLig(aux.getTl());
        while (esq.getvLig(0) != null) {
            esq = esq.getvLig(esq.getTl());
        }

        NoList n1 = (NoList) esq;
        if (n1.getProx() == null && aux.getvLig(aux.getTl() + 1) != null) {
            dir = aux.getvLig(aux.getTl() + 1);
            while (dir.getvLig(0) != null) {
                dir = dir.getvLig(0);
            }

            NoList n2 = (NoList) dir;

            n1.setProx(n2);
            n2.setAnt(n1);
        }
    }


    public void excluirElemento(int ele)
    {
        NoList aux = (NoList) navegarAteFolha(ele);
        No pai;
        int pos = aux.procuraPosicao2(ele);
        if(aux.getvInfo(pos)==ele)
        {
            aux.remanejarExclusao(pos);
            aux.setTl(aux.getTl()-1);
            pai = localizarPai(aux,aux.getvInfo(0));
            if(pai!=aux)
            {
                int posPai = pai.procuraPosicao(ele);
                if(posPai>0&&pos==0)
                    pai.setvInfo(posPai-1,aux.getvInfo(0));
                else
                    if(pos==0)
                      pai.setvInfo(0,aux.getvInfo(0));
                if(aux.getTl()<(No.N/2.0)-1)
                    redistribuirConcatenar(aux,pai);
            }
            conectarListas();
        }
    }

    public void redistribuirConcatenar(No folha,No pai)
    {
        int posPai;

        if(folha instanceof NoList)
        {
            NoList irmaE = null, irmaD = null;
            posPai = pai.procuraPosicao(folha.getvInfo(0));
            if(posPai>0)
                irmaE = (NoList) pai.getvLig(posPai-1);
            if(posPai+1<=pai.getTl())
                irmaD = (NoList) pai.getvLig(posPai+1);
            if(irmaE!=null&&irmaE.getTl()>Math.ceil(No.N/2.0)-1)
            {
                folha.remanejar(0);
                folha.setvInfo(0,irmaE.getvInfo(irmaE.getTl()));
                folha.setTl(folha.getTl()+1);
                irmaE.setTl(irmaE.getTl()-1);
                if(posPai>0)
                    pai.setvInfo(posPai-1,folha.getvInfo(0));
                else
                    pai.setvInfo(posPai,folha.getvInfo(0));
            }
            else
            {
                if(irmaD!=null&&irmaD.getTl()>Math.ceil(No.N/2.0)-1)
                {
                    folha.setvInfo(folha.getTl(),irmaD.getvInfo(0));
                    irmaD.remanejarExclusao(0);
                    irmaD.setTl(irmaD.getTl()-1);
                    folha.setTl(folha.getTl()+1);
                    pai.setvInfo(posPai, irmaD.getvInfo(0));
                }
                else
                {
                    if(irmaE!=null)
                    {
                        for(int i= 0;i<folha.getTl();i++)
                        {
                            irmaE.setvInfo(irmaE.getTl(),folha.getvInfo(i));
                            irmaE.setTl(irmaE.getTl()+1);
                        }
                        irmaE.setProx(irmaD);
                        if(irmaD!=null)
                            irmaD.setAnt(irmaE);
                        if(posPai>0)
                        {
                            pai.remanejarExclusao(posPai-1);
                            pai.setvLig(posPai-1,irmaE);
                            pai.setTl(pai.getTl()-1);
                        }
                        else
                        {
                            pai.remanejarExclusao(posPai);
                            pai.setvLig(posPai,irmaE);
                            pai.setTl(pai.getTl()-1);
                        }
                    }
                    else
                    {
                        if(irmaD!=null)
                        {
                            for(int i=folha.getTl()-1;i>=0;i--)
                            {
                                irmaD.remanejar(0);
                                irmaD.setvInfo(0,folha.getvInfo(i));
                            }
                            irmaD.setAnt(irmaE);
                            if(irmaE!=null)
                                irmaE.setProx(irmaD);
                            if(posPai>0)
                                pai.remanejarExclusao(posPai-1);
                            else
                                pai.remanejarExclusao(posPai);
                            pai.setTl(pai.getTl()-1);
                        }
                    }
                }
            }
            if(pai.getTl()<Math.ceil(No.N/2.0)-1)
                redistribuirConcatenar(pai,localizarPai(pai,pai.getvInfo(0)));
        }
        else
        {
            if(folha!=pai)
            {
                No irmaE = null, irmaD = null;
                posPai = pai.procuraPosicao2(folha.getvInfo(0));
                if(posPai>0)
                    irmaE =  pai.getvLig(posPai-1);
                if(posPai+1<=pai.getTl())
                    irmaD =  pai.getvLig(posPai+1);

                if(irmaE!=null&&irmaE.getTl()>Math.ceil(No.N/2.0)-1)
                {
                    folha.remanejar(0);
                    folha.setvInfo(0,pai.getvInfo(posPai));
                    folha.setvLig(0,irmaE.getvLig(irmaE.getTl()));
                    pai.setvInfo(posPai,irmaE.getvInfo(irmaE.getTl()-1));
                    irmaE.setTl(irmaE.getTl()-1);
                    folha.setTl(folha.getTl()+1);
                }
                else
                {
                    if(irmaD!=null&&irmaD.getTl()>Math.ceil(No.N/2.0)-1)
                    {
                        folha.setvInfo(folha.getTl(),pai.getvInfo(posPai));
                        folha.setTl(folha.getTl()+1);
                        pai.setvInfo(posPai,irmaD.getvInfo(0));
                        folha.setvLig(folha.getTl(),pai.getvLig(0));
                        irmaD.remanejarExclusao(0);
                        irmaD.setTl(irmaD.getTl()-1);
                    }
                    else
                    {
                        if(irmaE!=null)
                        {
                            irmaE.setvInfo(irmaE.getTl(),pai.getvInfo(posPai-1));
                            irmaE.setTl(irmaE.getTl()+1);

                            pai.remanejarExclusao(posPai-1);
                            pai.setTl(pai.getTl()-1);
                            pai.setvLig(posPai-1,irmaE);
                            for(int i=0;i<folha.getTl();i++)
                            {
                                irmaE.setvInfo(irmaE.getTl(),folha.getvInfo(i));
                                irmaE.setvLig(irmaE.getTl(),folha.getvLig(i));
                                irmaE.setTl(irmaE.getTl()+1);
                            }
                            irmaE.setvLig(irmaE.getTl(),folha.getvLig(folha.getTl()));
                        }
                        else
                        {
                            if(irmaD!=null)
                            {
                                irmaD.remanejar(0);
                                irmaD.setvInfo(0, pai.getvInfo(posPai));
                                irmaD.setTl(irmaD.getTl()+1);

                                pai.remanejarExclusao(posPai);
                                pai.setTl(pai.getTl()-1);
                                pai.setvLig(posPai,irmaD);

                                for(int i=folha.getTl()-1;i>=0;i--)
                                {
                                    irmaD.remanejar(0);
                                    irmaD.setvInfo(0,folha.getvInfo(i));
                                    irmaD.setvLig(1,folha.getvLig(i+1));
                                    irmaD.setTl(irmaD.getTl()+1);
                                }
                                irmaD.setvLig(0,folha.getvLig(0));
                            }
                            if(pai==raiz&&pai.getTl()==0)
                                if (irmaE!=null)
                                    raiz = irmaE;
                                else
                                    raiz = irmaD;
                        }
                    }
                }
                if(pai.getTl()<Math.ceil(No.N/2.0)-1)
                    redistribuirConcatenar(pai,localizarPai(pai,pai.getvInfo(0)));
            }
        }
    }
}
