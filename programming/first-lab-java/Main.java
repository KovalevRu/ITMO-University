public class Main {

    static short[] z;
    static float[] x;
    static double[][] z1;
    

    public static void main (String[] args){
        z = new short[17];
        // Заполнение массива z числами от 1 до 17 включительно
        for (int i = 0; i<17;i++){
            z[i] = (short)(i + 1);
        }
        x = new float[20];
        // Диапазон для рандома
        final float MAX = 11.0f;
        final float MIN = -11.0f;
        // Цикл для заполнения массива рандомными числами
        for (int i = 0; i<20;i++){
            x[i] = (float) (Math.random() * (MAX-MIN)) + MIN;
        }

        calculateElement(z,x);
        out(z1);
    }
    

    public static void calculateElement(short[] z,float[] x) {
        z1 = new double[17][20];
        for (int i = 0; i < 17; i++){
            switch (z[i]){
                case 3: for (int j = 0; j < 20; j++){          // Исправил формулу ( Логарифм )
                            z1[i][j] = Math.asin(1/(Math.pow(Math.E, Math.abs(Math.cos(Math.log(Math.abs(x[j])))))));

                        }
                    break;

                case 1,2,4,5,8,9,10,17: for (int j = 0; j<20; j++){
                            z1[i][j] = Math.atan(Math.cos(Math.atan(Math.pow(Math.E, -1 * Math.abs(x[j])))));
                        }
                    break;
                    
                default: for (int j = 0; j < 20; j++){
                            z1[i][j] = Math.pow(Math.cos(Math.cos(Math.atan(x[j]/22))), 2);
                        }
                    


            }



             
            /*if (z[i] == 3) {
                for (int j = 0; j < 20; j++){
                    z1[i][j] = Math.asin(1/(Math.pow(Math.E, Math.abs(Math.cos(Math.exp(Math.abs(x[j])))))));

                }
            }else if (z[i] == 1 || z[i] == 2 || z[i] == 4 || z[i] == 5 || z[i] == 8 || z[i] == 9 || z[i] == 10 || z[i] == 17){
                for (int j = 0; j<20; j++){
                    z1[i][j] = Math.atan(Math.cos(Math.atan(Math.pow(Math.E, -1 * Math.abs(x[j])))));
                }
            }else{
                for (int j = 0; j < 20; j++){
                    z1[i][j] = Math.pow(Math.cos(Math.cos(Math.atan(x[j]/22))), 2);
                }
            }*/
        }
        


    }
    

    /*public static void out(double z1[][]){
        for (int i = 0; i < 17; i++) {
            for (int j = 0; j < 20; j++) {
                System.out.printf("%.5f", z1[i][j]);
                if (j != 19) {
                    System.out.print(" ");
                } else {
                    System.out.print("");
                }
            }
            System.out.print("\n");
        }


    }*/


    // Попросили переписать out что бы работало через формат
    public static void out(double z1[][]) {
        for (int i = 0; i < 17; i++) {
            for (int j = 0; j < 20; j++) {
                System.out.printf("%.5f%s", z1[i][j], j == 19 ? "\n" : " "); 
            }
        }
    }
}