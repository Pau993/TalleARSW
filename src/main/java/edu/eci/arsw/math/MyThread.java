package edu.eci.arsw.math;

public class MyThread extends Thread {
    private int start;
    private int count;
    private int cantHilos;
    private byte[] result;

    public MyThread(int start, int count) {
        this.start = start;
        this.count = count;
        this.count = cantHilos;
    }

    @Override
    public void run() {
        // Calcular los dígitos usando PiDigits
        result = PiDigits.getDigits(start, count, cantHilos);
    }

    public byte[] getResult() {
        return result;
    }

    public static void main(String[] args){
        int start = 0;
        int count = 1000;
        int cantHilos = 4;

        byte[] piDigits = PiDigits.getDigits(start, count, cantHilos);

        String hexResult1 = MyThread.bytesToHex(piDigits);
        System.out.println("Primeros 100 caracteres de PI en hexadecimal:");
        System.out.println(hexResult1.substring(0, 100));

        //Primer estado, creación del hilo
        MyThread hilo = new MyThread(start, count);
        //Segundo estado, estado ejecutable
        hilo.start();
        
        //Tercer estado, bloquedo o not runnable
        try{
            hilo.sleep(1000);
        }catch(InterruptedException e){
            System.out.println("Error en el hilo 1" + e);
        }

        try{
            hilo.join();
        }catch(InterruptedException e){
                    System.out.println("Error en el hilo" + e);
        }

        // Obtener y convertir los resultados a hexadecimal
        byte[] result = hilo.getResult();
        String hexResult = bytesToHex(result);

        //Cuarto estado, muerte o finalizado
        hilo.stop();

        // Imprimir los primeros 100 caracteres del resultado como ejemplo
        System.out.println("Primeros 100 caracteres de PI en hexadecimal:");
        System.out.println(hexResult.substring(0, 100));
        
    }

    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();

    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        StringBuilder sb=new StringBuilder();
        for (int i=0;i<hexChars.length;i=i+2){
            //sb.append(hexChars[i]);
            sb.append(hexChars[i+1]);            
        }
        return sb.toString();
    }
}
