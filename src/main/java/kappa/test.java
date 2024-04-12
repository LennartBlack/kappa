package kappa;


public class test {
    public static void main(String[] args) {
        String env = System.getenv("INFLUX_TOKEN");
        System.out.println("env is: " + env);
    }
}
