import java.awt.*;
import java.awt.image.BufferedImage;

public class FiltroRGB {

    public static BufferedImage bandRed (BufferedImage img)
    {
        int largura = img.getWidth();
        int altura = img.getHeight();
        BufferedImage imgOut = new BufferedImage(largura,altura,BufferedImage.TYPE_INT_RGB);

        for(int linha=0; linha < altura; linha++)
        {
            for(int coluna=0; coluna <largura; coluna++)
            {
                int rgb = img.getRGB(coluna,linha);
                Color cor = new Color(rgb);
                int red = cor.getRed();
                int blue = 0;
                int green = 0;
                Color vemelhoApenas = new Color(red,green,blue);
                //System.out.println(red +" "+ green +" "+ blue);
                imgOut.setRGB(coluna,linha,vemelhoApenas.getRGB());
            }
        }
        return imgOut;
    }
    //aula3 VINICIUS
    public static BufferedImage bandGreen (BufferedImage img)
    {
        int largura = img.getWidth();
        int altura = img.getHeight();
        BufferedImage imgOut = new BufferedImage(largura,altura,BufferedImage.TYPE_INT_RGB);
        for(int linha=0; linha < altura; linha++)
        {
            for(int coluna=0; coluna <largura; coluna++)
            {
                int rgb = img.getRGB(coluna,linha);
                Color cor = new Color(rgb);
                int red = 0;
                int green = cor.getGreen();
                int blue = 0;
                Color verdeApenas = new Color(red,green,blue);
                //System.out.println(red +" "+ green +" "+ blue);
                imgOut.setRGB(coluna,linha,verdeApenas.getRGB());
            }
        }
        return imgOut;
    }
    //aula3 VINICIUS
    public static BufferedImage bandBlue (BufferedImage img)
    {
        int largura = img.getWidth();
        int altura = img.getHeight();
        BufferedImage imgOut = new BufferedImage(largura,altura,BufferedImage.TYPE_INT_RGB);
        for(int linha=0; linha < altura; linha++)
        {
            for(int coluna=0; coluna <largura; coluna++)
            {
                int rgb = img.getRGB(coluna,linha);
                Color cor = new Color(rgb);
                int red = 0;
                int green = 0;
                int blue = cor.getBlue();
                Color azulApenas = new Color(red,green,blue);
                //System.out.println(red +" "+ green +" "+ blue);
                imgOut.setRGB(coluna,linha,azulApenas.getRGB());
            }
        }
        return imgOut;
    }

    public static BufferedImage bandSelec (BufferedImage img, char banda)
    {
        int largura = img.getWidth();
        int altura = img.getHeight();
        BufferedImage imgOut = new BufferedImage(largura,altura,BufferedImage.TYPE_INT_RGB);
        switch (banda) {
            case 'r':
            case 'R':
                imgOut = bandRed(img);
                break;
            case 'g':
            case 'G':
                imgOut = bandGreen(img);
                break;
            case 'b':
            case 'B':
                imgOut = bandBlue(img);
                break;
            default:
                System.out.println("Opção de banda inválida!");
                break;
        }
        return imgOut;
    }

    //aula3 VINICIUS
    public static BufferedImage tonalidadeValor (BufferedImage img, char banda, int valor)
    {
        int largura = img.getWidth();
        int altura = img.getHeight();
        BufferedImage imgOut = new BufferedImage(largura,altura,BufferedImage.TYPE_INT_RGB);
        for(int linha=0; linha < altura; linha++)
        {
            for(int coluna=0; coluna <largura; coluna++)
            {
                //recebe cor do pixel analisado
                int rgb = img.getRGB(coluna,linha);
                Color cor = new Color(rgb);
                //recupera as especf de cores
                int blue = cor.getBlue();
                int red = cor.getRed();
                int green = cor.getGreen();

                //seleciona a banda e aplica o aumento
                switch (banda) {
                    case 'r':
                    case 'R':
                        red = red + valor;
                        break;
                    case 'g':
                    case 'G':
                        green = green + valor;
                        break;
                    case 'b':
                    case 'B':
                        blue = blue + valor;
                        break;
                    default:
                        System.out.println("Opção de banda inválida!");
                        break;
                }

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
                Color corAumento = new Color(red,green,blue);
                //System.out.println(red +" "+ green +" "+ blue);
                imgOut.setRGB(coluna,linha,corAumento.getRGB());
            }
        }
        return imgOut;
    }

    //app atividade VINICIUS
    public static BufferedImage tonalidadeValorRGB (BufferedImage img, int valorR, int valorG, int valorB)
    {
        int largura = img.getWidth();
        int altura = img.getHeight();
        BufferedImage imgOut = new BufferedImage(largura,altura,BufferedImage.TYPE_INT_RGB);
        for(int linha=0; linha < altura; linha++)
        {
            for(int coluna=0; coluna <largura; coluna++)
            {
                //recebe cor do pixel analisado
                int rgb = img.getRGB(coluna,linha);
                Color cor = new Color(rgb);
                //recupera as especf de cores
                int blue = cor.getBlue();
                int red = cor.getRed();
                int green = cor.getGreen();

                red = red + valorR;
                green = green + valorG;
                blue = blue + valorB;

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
                Color corAumento = new Color(red,green,blue);
                //System.out.println(red +" "+ green +" "+ blue);
                imgOut.setRGB(coluna,linha,corAumento.getRGB());
            }
        }
        return imgOut;
    }

    //app atividade VINICIUS CENTURION
    public static BufferedImage addBilhoRGB (BufferedImage img, int valorBrilhoAdd)
    {
        int largura = img.getWidth();
        int altura = img.getHeight();
        BufferedImage imgOut = new BufferedImage(largura,altura,BufferedImage.TYPE_INT_RGB);
        for(int linha=0; linha < altura; linha++)
        {
            for(int coluna=0; coluna <largura; coluna++)
            {
                //recebe cor do pixel analisado
                int rgb = img.getRGB(coluna,linha);
                Color cor = new Color(rgb);
                //recupera as especf de cores
                int blue = cor.getBlue();
                int red = cor.getRed();
                int green = cor.getGreen();

                red = red + valorBrilhoAdd;
                green = green + valorBrilhoAdd;
                blue = blue + valorBrilhoAdd;

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
                Color brilhoRgbADD = new Color(red,green,blue);
                //System.out.println(red +" "+ green +" "+ blue);
                imgOut.setRGB(coluna,linha,brilhoRgbADD.getRGB());
            }
        }
        return imgOut;
    }

    //app atividade
    public static BufferedImage multBilhoRGB (BufferedImage img, double valorBrilhoMult)
    {
        int largura = img.getWidth();
        int altura = img.getHeight();
        BufferedImage imgOut = new BufferedImage(largura,altura,BufferedImage.TYPE_INT_RGB);
        for(int linha=0; linha < altura; linha++)
        {
            for(int coluna=0; coluna <largura; coluna++)
            {
                //recebe cor do pixel analisado
                int rgb = img.getRGB(coluna,linha);
                Color cor = new Color(rgb);
                //recupera as especf de cores
                int blue = cor.getBlue();
                int red = cor.getRed();
                int green = cor.getGreen();

                red = (int) (red * valorBrilhoMult);
                green = (int) (green * valorBrilhoMult);
                blue = (int) (blue * valorBrilhoMult);

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
                Color brilhoRgbMult = new Color(red,green,blue);
                //System.out.println(red +" "+ green +" "+ blue);
                imgOut.setRGB(coluna,linha,brilhoRgbMult.getRGB());
            }
        }
        return imgOut;
    }

    //aula4
    public static BufferedImage escalaCinzaMedia (BufferedImage img)
    {
        int largura = img.getWidth();
        int altura = img.getHeight();
        BufferedImage imgOut = new BufferedImage(largura,altura,BufferedImage.TYPE_INT_RGB);
        for(int linha=0; linha < altura; linha++)
        {
            for(int coluna=0; coluna <largura; coluna++)
            {
                //recebe cor do pixel analisado
                int rgb = img.getRGB(coluna,linha);
                Color cor = new Color(rgb);
                //recupera as especf de cores
                int blue = cor.getBlue();
                int red = cor.getRed();
                int green = cor.getGreen();

                int media = ((blue+green+red)/3);

                //aplica esquema de cor ao pixel
                Color corCinza = new Color(media,media,media);
                //System.out.println(red +" "+ green +" "+ blue);
                imgOut.setRGB(coluna,linha,corCinza.getRGB());
            }
        }
        return imgOut;
    }

    //aula4 VINICIUS CENTURION
    public static BufferedImage escalaCinzaBanda (BufferedImage img, char banda)
    {
        int largura = img.getWidth();
        int altura = img.getHeight();
        BufferedImage imgOut = new BufferedImage(largura,altura,BufferedImage.TYPE_INT_RGB);
        for(int linha=0; linha < altura; linha++)
        {
            for(int coluna=0; coluna <largura; coluna++)
            {
                //recebe cor do pixel analisado
                int rgb = img.getRGB(coluna,linha);
                Color cor = new Color(rgb);
                //recupera as especf de cores
                int blue = cor.getBlue();
                int red = cor.getRed();
                int green = cor.getGreen();

                //seleciona a banda e aplica o aumento
                switch (banda) {
                    case 'r':
                    case 'R':
                        blue = red;
                        green = red;
                        break;
                    case 'g':
                    case 'G':
                        red = green;
                        blue = green;
                        break;
                    case 'b':
                    case 'B':
                        red = blue;
                        green = blue;
                        break;
                }

                //aplica esquema de cor ao pixel
                Color corCinza = new Color(red,green,blue);

                //System.out.println(red +" "+ green +" "+ blue);
                imgOut.setRGB(coluna,linha,corCinza.getRGB());
            }
        }
        return imgOut;
    }

    //aula4 VINICIUS CENTURION
    public static BufferedImage escalaNegativa (BufferedImage img)
    {
        int largura = img.getWidth();
        int altura = img.getHeight();
        BufferedImage imgOut = new BufferedImage(largura,altura,BufferedImage.TYPE_INT_RGB);
        for(int linha=0; linha < altura; linha++)
        {
            for(int coluna=0; coluna <largura; coluna++)
            {
                //recebe cor do pixel analisado
                int rgb = img.getRGB(coluna,linha);
                Color cor = new Color(rgb);
                //recupera as especf de cores
                int blue = cor.getBlue();
                int red = cor.getRed();
                int green = cor.getGreen();

                red = 255-red;
                blue = 255-blue;
                green = 255-green;

                //aplica esquema de cor ao pixel
                Color corNegativo = new Color(red,green,blue);

                //System.out.println(red +" "+ green +" "+ blue);
                imgOut.setRGB(coluna,linha,corNegativo.getRGB());
            }
        }
        return imgOut;
    }

    //aula5 VINICIUS
    public static BufferedImage escalaBnW (BufferedImage img, int limiar)
    {
        int largura = img.getWidth();
        int altura = img.getHeight();
        BufferedImage imgOut = new BufferedImage(largura,altura,BufferedImage.TYPE_INT_RGB);
        for(int linha=0; linha < altura; linha++)
        {
            for(int coluna=0; coluna <largura; coluna++)
            {
                //recebe cor do pixel analisado
                int rgb = img.getRGB(coluna,linha);
                Color cor = new Color(rgb);
                //recupera as especf de cores
                int blue = cor.getBlue();
                int red = cor.getRed();
                int green = cor.getGreen();
                int media = (blue+red+green)/3;
                //verifica a media da coloração em relação ao limiar passado
                if (media>limiar){
                    blue=255;
                    red=255;
                    green=255;
                } else {
                    blue=0;
                    red=0;
                    green=0;
                }
                //aplica esquema de cor ao pixel
                Color corBnW = new Color(red,green,blue);
                //System.out.println(red +" "+ green +" "+ blue);
                imgOut.setRGB(coluna,linha,corBnW.getRGB());
            }
        }
        return imgOut;
    }

}
