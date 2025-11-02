import java.util.Iterator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        AStar gs = new AStar();
        Iterator<AStar.State> it =
                gs.solve(new ArrayCfg(sc.nextLine()),
                        new ArrayCfg(sc.nextLine()));
        if (it==null){
            System.out.println("no solution found");
        }
        else {
            while(it.hasNext()) {
                AStar.State i = it.next();
                //System.out.println(i); //See full path from initial to solution
                if (!it.hasNext()){
                    System.out.println((int) i.getK());
                }
            }
        }
        sc.close();
    }
}
