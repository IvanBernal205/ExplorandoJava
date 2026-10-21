package domain;

public class Utilidades {

    public static int randomNumberInARange(int min, int max){
        return (int) (Math.random() * (max - min + 1)) + min;
    }
}
