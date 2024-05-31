public class Main
{
    public static void main(String[] args) {
        TreeMais tree = new TreeMais();

        for(int i=0;i<25;i++)
            tree.inserir(i);
        System.out.println("\n-------------------------------------------------");
        tree.exibir();

    }
}
