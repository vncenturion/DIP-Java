import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        String path = "C:\\Users\\vinic\\IdeaProjects\\Filtro\\src\\estudo\\teste - restauração.png";
        File arquivo = new File(path);
        BufferedImage imagem = ImageIO.read(arquivo);
        int largura = imagem.getWidth();
        int altura = imagem.getHeight();


        BufferedImage filtroBW9 = FiltroRGB.escalaBnW(imagem,100);
        BufferedImage filtroCM = FiltroRGB.escalaCinzaMedia(imagem);
        BufferedImage filtroCG = FiltroRGB.escalaCinzaBanda(imagem, 'g');
        BufferedImage filtroNG1 = FiltroRGB.escalaNegativa(imagem);
        BufferedImage filtroNG2 = FiltroYIQ.escalaNgtYIQ(imagem);
        BufferedImage filtroMediana1 = FiltroRegiao.kernelMediana(imagem,7);
        BufferedImage filtroMediana2 = FiltroRegiao.kernelMediana(filtroMediana1,5);
        BufferedImage filtroMedia1 = FiltroRegiao.kernelMedia(imagem,5);
        BufferedImage filtroMedia2 = FiltroRegiao.kernelMedia(imagem,7);
        BufferedImage filtroGauss1 = FiltroRegiao.filtroGaussiano(imagem,3);
        BufferedImage filtroGauss2 = FiltroRegiao.filtroGaussiano(imagem,5);
        BufferedImage filtroHorizontal = FiltroRegiao.filtroAgucamento(filtroMediana2,1);
        BufferedImage filtroVertical = FiltroRegiao.filtroAgucamento(filtroMediana2,2);
        BufferedImage filtroOeste = FiltroRegiao.filtroAgucamento(filtroMediana2,3);
        BufferedImage filtroLaplace = FiltroRegiao.filtroAgucamento(filtroMediana2,4);

        exibeAmostra(imagem,largura,altura, "Original");
        exibeAmostra(filtroCM,largura,altura, "Cinza médio");
        exibeAmostra(filtroCG,largura,altura, "Cinza Verde");
        exibeAmostra(filtroNG1,largura,altura, "Negativo1");
        exibeAmostra(filtroNG2,largura,altura, "Negativo2");
        exibeAmostra(filtroMediana1,largura,altura, "Mediana1");
        exibeAmostra(filtroMedia1,largura,altura, "Media1");
        exibeAmostra(filtroMediana2,largura,altura, "Mediana2");
        exibeAmostra(filtroMedia2,largura,altura, "Media2");
        exibeAmostra(filtroGauss1,largura,altura, "Gausiano 3x3");
        exibeAmostra(filtroGauss2,largura,altura, "Gausiano 5x5");
        exibeAmostra(filtroHorizontal,largura,altura, "horizontal");
        exibeAmostra(filtroVertical,largura,altura, "vertical");
        exibeAmostra(filtroOeste,largura,altura, "oeste");
        exibeAmostra(filtroLaplace,largura,altura, "laplace");

    }

    public static void exibeAmostra(BufferedImage imgFiltro, int largura, int altura, String nomeFiltro){
        JLabel label1 = new JLabel(new ImageIcon(imgFiltro));
        JPanel panel1 = new JPanel();
        panel1.add(label1);
        JFrame fram1 = new JFrame(nomeFiltro);
        fram1.setSize(new Dimension(largura+20,altura+50));
        fram1.add(panel1);
        fram1.setVisible(true);
    }

}
