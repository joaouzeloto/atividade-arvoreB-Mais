public class Main
{
    public static void main(String[] args) {
        TreeMais tree = new TreeMais();

        for(int i=0;i<26;i++)
            tree.inserir(i);
        System.out.println("\n-------------------------------------------------");
        tree.exibir();
        tree.excluirElemento(21);
        tree.excluirElemento(22);
        tree.excluirElemento(2);
        tree.excluirElemento(9);
        No aux = tree.navegarAteFolha(22);
        System.out.println();
        for(int i =0;i<aux.getTl();i++)
            System.out.print(aux.getvInfo(i)+" ");
        System.out.println("\n-------------------------------------------------");
        tree.exibir();
    }
}
