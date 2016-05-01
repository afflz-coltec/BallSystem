

import java.util.Scanner;
import java.util.Random;

public class TestaDesenho {

    public static void main(String args[]) { // Definindo a escala a ser empregada .
        double xmin = -1.0;
        double xmax = 2.0;
        double ymin = -1.0;
        double ymax = 2.0;
        double x, y; // Construindo os eixos da área para desenhar figuras .

        StdDraw.setXscale(xmin, xmax);
        StdDraw.setYscale(ymin, ymax);
        // Criando um vetor que pode armazenar
        // objetos da classe Desenho e das suas
        // subclasses . 
        Desenho vm[];
        // Perguntar ao usuário o número de desenhos a serem geradas .
        Scanner in = new Scanner(System.in);
        System.out.println("Gerar quantos desenhos ?");
        int n = in.nextInt();
        // Alocação dinâmica de memória .
        vm = new Desenho[n];
        // Criando aleatoriamente de desenhos .
        Random r = new Random();
        int opcao, cor;
        double tam;
        // Laco para construcao aleatoria de objetos .
        for (int i = 0; i < vm.length; i++) {
            // Gerando valores inteiros 0 ou 1 para
            // escolher o tipo de objeto de desenho .
            opcao = r.nextInt(2);
            // Gerando aleatoriamente o tamanho
            // do ponto : valor no intervalo [0.01 ,0.10].
            tam = r.nextDouble() * 0.09 + 0.01;
            // Gerando aleatoriamente as coordenadas
            // x e y da imagem .
            x = r.nextDouble() * (xmax - xmin) + xmin;
            y = r.nextDouble() * (ymax - ymin) + ymin;
            // Definindo aleatoriamente a cor de cada desenho . 

            cor = r.nextInt(5);

            System.out.println(" Desenho : " + (i + 1) + " Opcao = " + opcao + " tam = " + tam + " x = " + x + " y = " + y);

            // Criando um objeto do tipo ponto .
            if (opcao == 0) {
                vm[i] = new Ponto(x, y, cor, tam);
            } // Criando um objeto do tipo quadrado .
            else {
                vm[i] = new Quadrado(x, y, cor, tam);
            }
        }

        // Laco para mostrar os objetos armazenados em vm.
        for (int i = 0; i < vm.length; i++) {
            // Usando um metodo comum a todos os objetos ,
            vm[i].desenhar();
        }
    }
}
