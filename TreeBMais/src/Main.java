public class Main
{
    public static void main(String[] args) {
        TreeMais tree = new TreeMais();
        for(int i=1;i<25;i++)
            tree.inserir(i);
        tree.excluirElemento(21);
        tree.excluirElemento(22);
        tree.excluirElemento(3);
        tree.excluirElemento(19);
        tree.excluirElemento(4);
        tree.excluirElemento(11);
        tree.excluirElemento(14);
        System.out.println("\n----------------------In-Ordem---------------------------");
        tree.exibirTudo();
        System.out.println("\n----------------------Exibindo-Listas-------------------------");
        tree.exibir();
    }
}
