import java.awt.*;
import java.awt.image.BufferedImage;

public class FiltroYIQ {

    public static double[][][] rgb2YIQ (BufferedImage img)
    {
        int largura = img.getWidth();
        int altura = img.getHeight();
        double[][][] matrizYIQ = new double[altura][largura][3];
        for(int linha=0; linha < altura; linha++)
        {
            for(int coluna=0; coluna <largura; coluna++)
            {
                //recebe cor do pixel analisado
                int rgb = img.getRGB(coluna,linha);
                Color cor = new Color(rgb);

                //recupera as especf de cores
                int red = cor.getRed();
                int green = cor.getGreen();
                int blue = cor.getBlue();
                double y = 0.299*red+0.587*green+0.114*blue;
                double i = 0.596*red-0.274*green-0.322*blue;
                double q = 0.2115*red-0.523*green+0.312*blue;

                matrizYIQ[linha][coluna][0]=y;
                matrizYIQ[linha][coluna][1]=i;
                matrizYIQ[linha][coluna][2]=q;
                //System.out.println("pixel("+linha+","+coluna+") com Y:"+y+" I:"+i+" Q:"+q+);
            }
        }
        return matrizYIQ;
    }
    //aula 5 VINICIUS CENTURION
    public static BufferedImage yiq2RGB (BufferedImage img, double[][][] matrizYIQ)
    {
        int largura = img.getWidth();
        int altura = img.getHeight();
        BufferedImage imgOut = new BufferedImage(largura,altura,BufferedImage.TYPE_INT_RGB);
        for(int linha=0; linha < altura; linha++)
        {
            for(int coluna=0; coluna <largura; coluna++)
            {
                //recupera as especf de cores
                double Y= matrizYIQ[linha][coluna][0];
                double I= matrizYIQ[linha][coluna][1];
                double Q= matrizYIQ[linha][coluna][2];
                int red = (int) (Y + 0.956170685404*I + 0.621432566347*Q);
                int green = (int) (Y - 0.27268860233*I - 0.64681323702*Q);
                int blue = (int) (Y - 1.10374408218*I + 1.70062309468*Q);

                //limitação do resultado do tratamento de tonalidade para valor entre [0;255]
                if (red < 0) {
                    red = 0;
                } else if (red > 255) {
                    red = 255;
                }
                if (green < 0) {
                    green = 0;
                } else if (green > 255) {
                    green = 255;
                }
                if (blue < 0) {
                    blue = 0;
                } else if (blue > 255) {
                    blue = 255;
                }

                //aplica esquema de cor ao pixel
                Color corNova = new Color(red,green,blue);
                //System.out.println(red +" "+ green +" "+ blue);
                imgOut.setRGB(coluna,linha,corNova.getRGB());
            }
        }
        return imgOut;
    }
    //atividade VINICIUS CENTURION
    public static BufferedImage addBilhoYIQ (BufferedImage img, int valorBrilho)
    {
        /*
        metodo poderia ser feito a partir do metodo rgb2YIQ, contudo, haveria um
        gasto de processamento ao se percorrer 2 vezes a matriz de pixels,
        a primeira vez para gerar a matriz YIQ, e a segunda para adicionar o valor de brilho
        a variavel Y de cada linha da matriz. Dessa forma, optou-se pela realização da adição
        no momento da própria geração da matriz YIQ.
        */
        int largura = img.getWidth();
        int altura = img.getHeight();
        BufferedImage imgOut = new BufferedImage(largura,altura,BufferedImage.TYPE_INT_RGB);
        double[][][] matrizYIQBrilhoADD = new double[altura][largura][3];
        for(int linha=0; linha < altura; linha++)
        {
            for(int coluna=0; coluna <largura; coluna++)
            {
                //recebe cor do pixel analisado
                int rgb = img.getRGB(coluna,linha);
                Color cor = new Color(rgb);

                //recupera as especf de cores
                int red = cor.getRed();
                int green = cor.getGreen();
                int blue = cor.getBlue();
                double y = 0.299*red+0.587*green+0.114*blue;
                double i = 0.596*red-0.274*green-0.322*blue;
                double q = 0.211*red-0.523*green+0.312*blue;

                matrizYIQBrilhoADD[linha][coluna][0]=y+valorBrilho;
                matrizYIQBrilhoADD[linha][coluna][1]=i;
                matrizYIQBrilhoADD[linha][coluna][2]=q;
                //System.out.println("pixel("+linha+","+coluna+") com Y:"+y+" I:"+i+" Q:"+q+);
            }
        }
        imgOut=yiq2RGB (img,matrizYIQBrilhoADD);
        return imgOut;
    }
    //atividade VINICIUS CENTURION
    public static BufferedImage multBilhoYIQ (BufferedImage img, double valorBrilho)
    {
        /*
        metodo poderia ser feito a partir do metodo rgb2YIQ, contudo, haveria um
        gasto de processamento ao se percorrer 2 vezes a matriz de pixels,
        a primeira vez para gerar a matriz YIQ, e a segunda para multiplicar o valor de brilho
        a variavel Y de cada linha da matriz. Dessa forma, optou-se pela realização da multiplicação
        no momento da própria geração da matriz YIQ.
        */
        int largura = img.getWidth();
        int altura = img.getHeight();
        BufferedImage imgOut = new BufferedImage(largura,altura,BufferedImage.TYPE_INT_RGB);
        double[][][] matrizYIQBrilhoMULT = new double[altura][largura][3];
        for(int linha=0; linha < altura; linha++)
        {
            for(int coluna=0; coluna <largura; coluna++)
            {
                //recebe cor do pixel analisado
                int rgb = img.getRGB(coluna,linha);
                Color cor = new Color(rgb);

                //recupera as especf de cores
                int red = cor.getRed();
                int green = cor.getGreen();
                int blue = cor.getBlue();
                double y = 0.299*red+0.587*green+0.114*blue;
                double i = 0.596*red-0.274*green-0.322*blue;
                double q = 0.211*red-0.523*green+0.312*blue;

                matrizYIQBrilhoMULT[linha][coluna][0]=y*valorBrilho;
                matrizYIQBrilhoMULT[linha][coluna][1]=i;
                matrizYIQBrilhoMULT[linha][coluna][2]=q;
                //System.out.println("pixel("+linha+","+coluna+") com Y:"+y+" I:"+i+" Q:"+q+);
            }
        }
        imgOut=yiq2RGB (img,matrizYIQBrilhoMULT);
        return imgOut;
    }
    //atviidade VINICIUS CENTURION
    public static BufferedImage escalaNgtYIQ (BufferedImage img)
    {
        /*
        metodo poderia ser feito a partir do metodo rgb2YIQ, contudo, haveria um
        gasto de processamento ao se percorrer 2 vezes a matriz de pixels,
        a primeira vez para gerar a matriz YIQ, e a segunda para subtrair o valor da variavel Y
        de 255 (valor max) para cada linha da matriz. Dessa forma,
        optou-se pela realização da operação no momento da própria geração da matriz YIQ.
        */
        int largura = img.getWidth();
        int altura = img.getHeight();
        BufferedImage imgOut = new BufferedImage(largura,altura,BufferedImage.TYPE_INT_RGB);
        double[][][] matrizYIQNGT = new double[altura][largura][3];
        for(int linha=0; linha < altura; linha++)
        {
            for(int coluna=0; coluna <largura; coluna++)
            {
                //recebe cor do pixel analisado
                int rgb = img.getRGB(coluna,linha);
                Color cor = new Color(rgb);

                //recupera as especf de cores
                int red = cor.getRed();
                int green = cor.getGreen();
                int blue = cor.getBlue();
                double y = 0.299*red+0.587*green+0.114*blue;
                double i = 0.596*red-0.274*green-0.322*blue;
                double q = 0.211*red-0.523*green+0.312*blue;

                matrizYIQNGT[linha][coluna][0]=255-y;
                matrizYIQNGT[linha][coluna][1]=i;
                matrizYIQNGT[linha][coluna][2]=q;
                //System.out.println("pixel("+linha+","+coluna+") com Y:"+y+" I:"+i+" Q:"+q+);
            }
        }
        imgOut=yiq2RGB (img,matrizYIQNGT);
        return imgOut;
    }
}
