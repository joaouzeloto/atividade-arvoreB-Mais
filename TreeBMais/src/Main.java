public class Main
{
    public static void main(String[] args) {
        TreeMais tree = new TreeMais();

        //insecao
        for(int i=1;i<25;i++)
            tree.inserir(i);

        //antes da exclusao
        System.out.println("\n----------------------In-Ordem---------------------------");
        tree.exibirTudo();
        System.out.println("\n----------------------Exibindo-Listas-------------------------");
        tree.exibir();

        //esclusao
        tree.excluirElemento(21);
        tree.excluirElemento(22);
        tree.excluirElemento(3);
        tree.excluirElemento(19);
        tree.excluirElemento(4);
        tree.excluirElemento(11);
        tree.excluirElemento(14);
        tree.excluirElemento(8);
        tree.excluirElemento(2);

        //depois da exclusao
        System.out.println("\n----------------------In-Ordem---------------------------");
        tree.exibirTudo();
        System.out.println("\n----------------------Exibindo-Listas-------------------------");
        tree.exibir();
    }
}
