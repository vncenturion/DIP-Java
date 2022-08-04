import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;

public class FiltroRegiao
{

    public static BufferedImage kernelMediana (BufferedImage img, int tamVizinhanca)
    {

        BufferedImage imgOut = Ferramentas.copiaImagem(img);
        int largura = img.getWidth();
        int altura = img.getHeight();
        int indexPixelInicial = ((tamVizinhanca+1)/2)-1;
        int deslocamento = (tamVizinhanca-1)/2;
        int tamanhoVetor = tamVizinhanca*tamVizinhanca;

        for(int linha=indexPixelInicial; linha < altura-deslocamento; linha++)
        {
            for(int coluna=indexPixelInicial; coluna <largura-deslocamento; coluna++)
            {
                int[] vetorAzul = buscaVetorBanda(img,linha,coluna, deslocamento, tamanhoVetor, 'b');
                int[] vetorVermelho = buscaVetorBanda(img,linha,coluna,deslocamento,tamanhoVetor, 'r');
                int[] vetorVerde = buscaVetorBanda(img,linha,coluna,deslocamento,tamanhoVetor, 'g');

                int blue=get_Mediana(vetorAzul);
                int red=get_Mediana(vetorVermelho);
                int green=get_Mediana(vetorVerde);

                Color corFiltro = new Color(red,green,blue);
                imgOut.setRGB(coluna,linha,corFiltro.getRGB());
            }

        } return imgOut;
    }

    public static BufferedImage kernelMedia (BufferedImage img, int tamVizinhanca)
    {
        BufferedImage imgOut = Ferramentas.copiaImagem(img);
        int largura = img.getWidth();
        int altura = img.getHeight();
        int indexPixelInicial = ((tamVizinhanca+1)/2)-1;
        int deslocamento = (tamVizinhanca-1)/2;
        int tamanhoVetor = tamVizinhanca*tamVizinhanca;


        for(int linha=indexPixelInicial; linha < altura-deslocamento; linha++)
        {
            for(int coluna=indexPixelInicial; coluna <largura-deslocamento; coluna++)
            {
                int[] vetorAzul = buscaVetorBanda(img,linha,coluna, deslocamento, tamanhoVetor, 'b');
                int[] vetorVermelho = buscaVetorBanda(img,linha,coluna,deslocamento,tamanhoVetor, 'r');
                int[] vetorVerde = buscaVetorBanda(img,linha,coluna,deslocamento,tamanhoVetor, 'g');

                int blue=get_Media(vetorAzul);
                int red=get_Media(vetorVermelho);
                int green=get_Media(vetorVerde);

                Color corFiltro = new Color(red,green,blue);
                imgOut.setRGB(coluna,linha,corFiltro.getRGB());
            }

        } return imgOut;
    }

    public static BufferedImage filtroGaussiano(BufferedImage img, int tamanhoKernel)
    {
        if (tamanhoKernel <= 3){
            tamanhoKernel=3;
        } else {
            tamanhoKernel=5;
        }
        BufferedImage imgOut = Ferramentas.copiaImagem(img);
        int largura = img.getWidth();
        int altura = img.getHeight();
        int indexPixelInicial = ((tamanhoKernel+1)/2)-1;
        int deslocamento = (tamanhoKernel-1)/2;
        int tamanhoVetor = tamanhoKernel*tamanhoKernel;

        for(int linha=indexPixelInicial; linha < altura-deslocamento; linha++)
        {
            for(int coluna=indexPixelInicial; coluna <largura-deslocamento; coluna++)
            {
                int[] vetorAzul = buscaVetorBanda(img,linha,coluna, deslocamento, tamanhoVetor, 'b');
                int[] vetorVermelho = buscaVetorBanda(img,linha,coluna,deslocamento,tamanhoVetor, 'r');
                int[] vetorVerde = buscaVetorBanda(img,linha,coluna,deslocamento,tamanhoVetor, 'g');

                int blue=get_gauss(vetorAzul,tamanhoKernel);
                int red=get_gauss(vetorVermelho,tamanhoKernel);
                int green=get_gauss(vetorVerde, tamanhoKernel);

                blue=Ferramentas.corrigeCor(blue);
                red=Ferramentas.corrigeCor(red);
                green=Ferramentas.corrigeCor(green);

                Color corFiltro = new Color(red,green,blue);
                imgOut.setRGB(coluna,linha,corFiltro.getRGB());
            }

        } return imgOut;
    }

    public static BufferedImage filtroAgucamento(BufferedImage img, int kernel)
    {
        int tamanhoKernel=3; //tamanho da vizinhança foi fixada em razao de apenas existir kernels 3x3 para os casos
        BufferedImage imgOut = Ferramentas.copiaImagem(img);
        int largura = img.getWidth();
        int altura = img.getHeight();
        int indexPixelInicial = ((tamanhoKernel+1)/2)-1;
        int deslocamento = (tamanhoKernel-1)/2;
        int tamanhoVetor = tamanhoKernel*tamanhoKernel;

        switch (kernel)
        {
            case 1: //kernel HORIZONTAL
                for(int linha=indexPixelInicial; linha < altura-deslocamento; linha++)
                {
                    for(int coluna=indexPixelInicial; coluna <largura-deslocamento; coluna++)
                    {
                        int[] vetorAzul = buscaVetorBanda(img,linha,coluna, deslocamento, tamanhoVetor, 'b');
                        int[] vetorVermelho = buscaVetorBanda(img,linha,coluna,deslocamento,tamanhoVetor, 'r');
                        int[] vetorVerde = buscaVetorBanda(img,linha,coluna,deslocamento,tamanhoVetor, 'g');

                        int blue=kernel_horizontal(vetorAzul,tamanhoKernel);
                        int red=kernel_horizontal(vetorVermelho,tamanhoKernel);
                        int green=kernel_horizontal(vetorVerde, tamanhoKernel);

                        blue=Ferramentas.corrigeCor(blue);
                        red=Ferramentas.corrigeCor(red);
                        green=Ferramentas.corrigeCor(green);

                        Color corFiltro = new Color(red,green,blue);
                        imgOut.setRGB(coluna,linha,corFiltro.getRGB());
                    }
                }
                break;
            case 2: //kernel VERTICAL
                for(int linha=indexPixelInicial; linha < altura-deslocamento; linha++)
                {
                    for(int coluna=indexPixelInicial; coluna <largura-deslocamento; coluna++)
                    {
                        int[] vetorAzul = buscaVetorBanda(img,linha,coluna, deslocamento, tamanhoVetor, 'b');
                        int[] vetorVermelho = buscaVetorBanda(img,linha,coluna,deslocamento,tamanhoVetor, 'r');
                        int[] vetorVerde = buscaVetorBanda(img,linha,coluna,deslocamento,tamanhoVetor, 'g');

                        int blue=kernel_vertical(vetorAzul,tamanhoKernel);
                        int red=kernel_vertical(vetorVermelho,tamanhoKernel);
                        int green=kernel_vertical(vetorVerde, tamanhoKernel);

                        blue=Ferramentas.corrigeCor(blue);
                        red=Ferramentas.corrigeCor(red);
                        green=Ferramentas.corrigeCor(green);

                        Color corFiltro = new Color(red,green,blue);
                        imgOut.setRGB(coluna,linha,corFiltro.getRGB());
                    }
                }
                break;
            case 3: //kernel LINHAS OESTE
                for(int linha=indexPixelInicial; linha < altura-deslocamento; linha++)
                {
                    for(int coluna=indexPixelInicial; coluna <largura-deslocamento; coluna++)
                    {
                        int[] vetorAzul = buscaVetorBanda(img,linha,coluna, deslocamento, tamanhoVetor, 'b');
                        int[] vetorVermelho = buscaVetorBanda(img,linha,coluna,deslocamento,tamanhoVetor, 'r');
                        int[] vetorVerde = buscaVetorBanda(img,linha,coluna,deslocamento,tamanhoVetor, 'g');

                        int blue=kernel_oeste(vetorAzul,tamanhoKernel);
                        int red=kernel_oeste(vetorVermelho,tamanhoKernel);
                        int green=kernel_oeste(vetorVerde, tamanhoKernel);

                        blue=Ferramentas.corrigeCor(blue);
                        red=Ferramentas.corrigeCor(red);
                        green=Ferramentas.corrigeCor(green);

                        Color corFiltro = new Color(red,green,blue);
                        imgOut.setRGB(coluna,linha,corFiltro.getRGB());
                    }
                }
                break;
            case 4: //kernel LAPLACIANO
                for(int linha=indexPixelInicial; linha < altura-deslocamento; linha++)
                {
                    for(int coluna=indexPixelInicial; coluna <largura-deslocamento; coluna++)
                    {
                        int[] vetorAzul = buscaVetorBanda(img,linha,coluna, deslocamento, tamanhoVetor, 'b');
                        int[] vetorVermelho = buscaVetorBanda(img,linha,coluna,deslocamento,tamanhoVetor, 'r');
                        int[] vetorVerde = buscaVetorBanda(img,linha,coluna,deslocamento,tamanhoVetor, 'g');

                        int blue=kernel_laplaciano(vetorAzul,tamanhoKernel);
                        int red=kernel_laplaciano(vetorVermelho,tamanhoKernel);
                        int green=kernel_laplaciano(vetorVerde, tamanhoKernel);

                        blue=Ferramentas.corrigeCor(blue);
                        red=Ferramentas.corrigeCor(red);
                        green=Ferramentas.corrigeCor(green);

                        Color corFiltro = new Color(red,green,blue);
                        imgOut.setRGB(coluna,linha,corFiltro.getRGB());
                    }
                }
                break;
            default:
                System.out.println("Opção de kernel inválida!");
                System.out.println("Passe os parametros:\n1-kernel horizontal\n2-kernel vertical\n3- kernel linhas " +
                        "oeste\n4-kernel laplaciano");
                break;
        }
        return imgOut;
    }

    public static int[] buscaVetorBanda(BufferedImage imagem, int linha, int coluna, int deslocamento, int tamanhoVetor, char banda)
    {
        int contador=0;
        int[] vetor = new int[tamanhoVetor];
        for(int i=linha-deslocamento; i <= linha+deslocamento; i++) {
            for (int j= coluna-deslocamento; j <= coluna+deslocamento; j++) {
                //recebe cor do pixel analisado
                int rgb = imagem.getRGB(j, i);
                Color cor = new Color(rgb);
                //recupera as especf de cores
                switch (banda){
                    case 'r':
                    case 'R':
                        vetor[contador] = cor.getRed();
                        break;
                    case 'g':
                    case 'G':
                        vetor[contador] = cor.getGreen();
                        break;
                    case 'b':
                    case 'B':
                        vetor[contador] = cor.getBlue();
                        break;
                    default:
                        System.out.println("Opção de banda inválida!");
                        break;
                }
                contador++;
            }
        }
        return vetor;
    }

    public static int get_Mediana(int[] vetor)
    {
        Arrays.sort(vetor);
        int indexMediana = ((vetor.length+1)/2)-1;
        int mediana = vetor[indexMediana];
        return mediana;
    }

    public static int get_Media(int[] vetor)
    {
        int index = 0;
        int soma = 0;
        int nElementos = vetor.length;
        while (index<nElementos){
            soma += vetor[index];
            index++;
        }
        int media = (int)(soma/nElementos);
        return media;
    }

    public static int get_gauss(int[] vetor, int tamKernel)
    {
        int somaInteira=0;
        double soma=0;
        double [] kernel;
        if (tamKernel == 3){
            kernel = new double[]{0.0625, 0.125, 0.0625, 0.125, 0.25, 0.125, 0.0625, 0.125, 0.0625};
        } else {
            kernel = new double[]{(1.0/256), (4.0/256), (6.0/256), (4.0/256), (1.0/256), (4.0/256), (16.0/256),
                    (24.0/256), (16.0/256), (4.0/256), (6.0/256), (24.0/256), (36.0/256), (24.0/256), (6.0/256),
                    (4.0/256), (16.0/256), (24.0/256), (16.0/256), (4.0/256), (1.0/256), (4.0/256), (6.0/256),
                    (4.0/256), (1.0/256)};
        }
        for (int i = 0; i < (tamKernel*tamKernel); i++){
            soma+=vetor[i]*kernel[i];
        }
        somaInteira=(int)soma;
        return somaInteira;
    }

    public static int kernel_horizontal(int[] vetor, int tamKernel)
    {
        int somaInteira=0;
        int soma=0;
        int [] kernel;
        kernel = new int[]{-1, -2, -1, 0, 0, 0, 1,2, 1};
        for (int i = 0; i < (tamKernel*tamKernel); i++){
            soma+=vetor[i]*kernel[i];
        }
        somaInteira=(int)soma;
        return somaInteira;
    }

    public static int kernel_vertical(int[] vetor, int tamKernel)
    {
        int somaInteira=0;
        int soma=0;
        int [] kernel;
        kernel = new int[]{-1, 0, 1, -2, 0, 2, -1, 0, 1};
        for (int i = 0; i < (tamKernel*tamKernel); i++){
            soma+=vetor[i]*kernel[i];
        }
        somaInteira=(int)soma;
        return somaInteira;
    }

    public static int kernel_oeste(int[] vetor, int tamKernel)
    {
        int somaInteira=0;
        int soma=0;
        int [] kernel;
        kernel = new int[]{1, 1, -1, 1, -2, -1, 1, 1, -1};
        for (int i = 0; i < (tamKernel*tamKernel); i++){
            soma+=vetor[i]*kernel[i];
        }
        somaInteira=(int)soma;
        return somaInteira;
    }

    public static int kernel_laplaciano(int[] vetor, int tamKernel)
    {
        int somaInteira=0;
        int soma=0;
        int [] kernel;
        kernel = new int[]{0, -1, 0, -1, 4, -1, 0, -1, 0};
        for (int i = 0; i < (tamKernel*tamKernel); i++){
            soma+=vetor[i]*kernel[i];
        }
        somaInteira=(int)soma;
        return somaInteira;
    }


    public static int[] buscaVetorBlue(BufferedImage imagem, int linha, int coluna, int deslocamento, int tamanhoVetor)
    {
        int contador=0;
        int[] vetor = new int[tamanhoVetor];
        for(int i=linha-deslocamento; i <= linha+deslocamento; i++) {
            for (int j= coluna-deslocamento; j <= coluna+deslocamento; j++) {
                //recebe cor do pixel analisado
                int rgb = imagem.getRGB(j, i);
                Color cor = new Color(rgb);
                //recupera as especf de cores
                vetor[contador] = cor.getBlue();
                contador++;
            }
        }
        return vetor;
    }

    public static int[] buscaVetorRed(BufferedImage imagem, int linha, int coluna, int deslocamento, int tamanhoVetor)
    {
        int contador=0;
        int[] vetor = new int[tamanhoVetor];
        for(int i=linha-deslocamento; i <= linha+deslocamento; i++) {
            for (int j= coluna-deslocamento; j <= coluna+deslocamento; j++) {
                //recebe cor do pixel analisado
                int rgb = imagem.getRGB(j, i);
                Color cor = new Color(rgb);
                //recupera as especf de cores
                vetor[contador] = cor.getRed();
                contador++;
            }
        }
        return vetor;
    }

    public static int[] buscaVetorGreen(BufferedImage imagem, int linha, int coluna, int deslocamento, int tamanhoVetor)
    {
        int contador=0;
        int[] vetor = new int[tamanhoVetor];
        for(int i=linha-deslocamento; i <= linha+deslocamento; i++) {
            for (int j= coluna-deslocamento; j <= coluna+deslocamento; j++) {
                //recebe cor do pixel analisado
                int rgb = imagem.getRGB(j, i);
                Color cor = new Color(rgb);
                //recupera as especf de cores
                vetor[contador] = cor.getGreen();
                contador++;
            }
        }
        return vetor;
    }


}
