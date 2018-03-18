import java.util.*;

public class Practica1 {

    enum State {

        S1, S2, S3, S4

    }

    enum Action {

        a1, a2, a3, a4

    }

    static int [] [] rewardsMatrix = {{-1,1,1,2},
                                    {-2,2,2,-2},
                                    {0,-3,-2,3},
                                    {2,-1,4,5}};

    static State [] [] transitionsMatrix = {{State.S2,State.S4,State.S1,State.S1},
                                            {State.S1,State.S3,State.S4,State.S2},
                                            {State.S2,State.S1,State.S4,State.S2},
                                            {State.S2,State.S4,State.S1,State.S4}};

    private static Map<State, Map<Action, Integer>> rewards;
    private static Map<State, Map<Action, State>> transitions;
    private static Map<State, Double> V;
    private static ArrayList<Double> Q;
    private static Map<State, Boolean> C;

    private static void initTables( ) {

        rewards = new EnumMap<>( State.class );
        transitions = new EnumMap<>( State.class );
        V = new EnumMap<>( State.class );
        C = new EnumMap<>(State.class);
        int i = 0, j = 0;

        for ( State s: State.values() ) {

            V.put(s, 0.0);
            C.put(s,false);
            rewards.put( s, new EnumMap<>( Action.class ) );
            transitions.put( s, new EnumMap<>( Action.class ) );

        }

        for (Action a : Action.values()){

            for (State s : State.values()) {

                rewards.get(s).put(a,rewardsMatrix[i][j]);
                transitions.get(s).put(a,transitionsMatrix[i][j]);
                j++;

            }

            i++;
            j = 0;

        }

    }

    private static void learn(double gamma) {

        Q = new ArrayList<>();
        double newV, lastV, epsilon=0.001;
        int count = 1;

        while (verify_C()) {

            System.out.println("iteration: " + count);

            for (State s : State.values()) {

                lastV = eval_V(s);

                for (Action a : Action.values()) {

                    Q.add(eval_R(s, a) + gamma * eval_V(eval_T(s, a)));

                }

                V.put(s, Collections.max(Q));
                newV = eval_V(s);

                if (Math.abs(newV-lastV)<epsilon){

                    C.put(s,true);

                }

                print_V();
                Q.clear();

            }

            count++;

        }

    }

    private static Integer eval_R( State s, Action a ) {

        return rewards.get(s).get(a);

    }

    private static State eval_T( State s, Action a ) {

        return transitions.get(s).get(a);

    }

    private static Double eval_V(State s){

        return V.get(s);

    }

    private static Boolean verify_C(){

        boolean b = true;

        for(State s: State.values()){

            b &= C.get(s);

        }

        return !b;

    }

    private static void print_V() {

        for (State s: State.values()) {

            System.out.print(V.get(s));
            System.out.print(" ");

        }

        System.out.println();

    }

    public static void main (String[] args) {

        System.out.print("gamma value(double):");
        Scanner scanner = new Scanner(System.in);
        double gamma = scanner.nextDouble();
        scanner.close();
        initTables();
        learn(gamma);

    }

}
