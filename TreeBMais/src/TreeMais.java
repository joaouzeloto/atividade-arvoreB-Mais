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


    private void split(No folha,No pai)
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
            if(raizI.getvLig(0)==null)
                System.out.print("folha: ");
            else
                System.out.print("nao folha:");
            for(int i=0;i<raizI.getTl();i++)
                    System.out.print(raizI.getvInfo(i)+" ");
            System.out.println();
            for(int i=0;i<=raizI.getTl();i++)
                exibirTudo(raizI.getvLig(i));
        }
    }

    private void listarFolhas(No raizI,Fila fila)
    {
        if(raizI!=null)
        {
            if(raizI.getvLig(0)==null)
                fila.inserir((NoList) raizI);
            for(int i=0;i<=raizI.getTl();i++)
                listarFolhas(raizI.getvLig(i),fila);
        }
    }

    public void exibirTudo()
    {
        exibirTudo(raiz);
    }

    private void conectarListas()
    {
        NoList ax1=null, ax2=null;
        Fila fila = new Fila();
        listarFolhas(raiz,fila);
        if(!fila.vazio())
            ax1 = fila.retirar();
        while(!fila.vazio())
        {
            ax2 = fila.retirar();
            ax1.setProx(ax2);
            ax2.setAnt(ax1);
            ax1 = ax2;
        }
    }


    public void excluirElemento(int ele)
    {
        NoList aux = (NoList) navegarAteFolha(ele);
        No pai;
        int pos = aux.procuraPosicao2(ele);
        if(aux.getvInfo(pos)==ele&&pos<No.N)
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

    private void redistribuirConcatenar(No folha,No pai)
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
                folha.setvInfo(0,irmaE.getvInfo(irmaE.getTl()-1));
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
                        for(int i= 0;i<folha.getTl()&&irmaE.getTl()<No.N;i++)
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
                                irmaD.setTl(irmaD.getTl()+1);
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
                            for(int i=0;i<folha.getTl()&&irmaE.getTl()<No.N;i++)
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
