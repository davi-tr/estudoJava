package model;

public class ExpressaoTernaria {
    public static void main(String[] args) {
        /*Sintaxe:
        (condição) ? valor_se_verdadeiro : valor_se_falso
        utilizamos uma ternaria para inviar uma informação para uma variavel por exemplo
        assim temos uma forma mais facil de atribuir um valor com base em uma condição
         */

        //Exemplos
        int x = 4;
        int y = 3;
        String resposta = (x>y) ? "Verdadeiro"  : "Falso" ;
        System.out.println(resposta);
    }
}
