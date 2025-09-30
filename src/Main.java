import java.util.Iterator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        GSolver gs = new GSolver();
        Iterator<GSolver.State> it =
                gs.solve(new ArrayCfg(sc.nextLine()),
                        new ArrayCfg(sc.nextLine()));
        if (it==null){
            System.out.println("no solution found");
        }
        else {
            while(it.hasNext()) {
                GSolver.State i = it.next();
                System.out.println(i);
                if (!it.hasNext()){
                    System.out.println(i.getK());
                }
            }
        }
        sc.close();
    }
}
