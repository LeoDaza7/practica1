import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Map;

public class Practica1 {

    enum State {
        S1, S2, S3, S4;
    }
    enum Action {
        a1, a2, a3, a4;
    }

    private static Map<State, Map<Action, Integer> > rewards;
    private static Map<State, Map<Action, State> > transitions;
    private static Map<State, Double> V;
    private static ArrayList<Double> Q;

    private static void initTables( ) {
        rewards = new EnumMap<>( State.class );
        transitions = new EnumMap<>( State.class );
        V = new EnumMap<>( State.class );
        for ( State s: State.values() ) {
            V.put( s, 0.0 );
            rewards.put( s, new EnumMap<>( Action.class ) );
            transitions.put( s, new EnumMap<>( Action.class ) );
        }
        rewards.get( State.S1 ).put( Action.a1, -1 );
        rewards.get( State.S1 ).put( Action.a2, -2 );
        rewards.get( State.S1 ).put( Action.a3,  0 );
        rewards.get( State.S1 ).put( Action.a4,  2 );
        rewards.get( State.S2 ).put( Action.a1,  1 );
        rewards.get( State.S2 ).put( Action.a2,  2 );
        rewards.get( State.S2 ).put( Action.a3, -3 );
        rewards.get( State.S2 ).put( Action.a4, -1 );
        rewards.get( State.S3 ).put( Action.a1,  1 );
        rewards.get( State.S3 ).put( Action.a2,  2 );
        rewards.get( State.S3 ).put( Action.a3, -2 );
        rewards.get( State.S3 ).put( Action.a4,  4 );
        rewards.get( State.S4 ).put( Action.a1,  2 );
        rewards.get( State.S4 ).put( Action.a2, -2 );
        rewards.get( State.S4 ).put( Action.a3,  3 );
        rewards.get( State.S4 ).put( Action.a4,  5 );

        transitions.get( State.S1 ).put( Action.a1, State.S2 );
        transitions.get( State.S1 ).put( Action.a2, State.S1 );
        transitions.get( State.S1 ).put( Action.a3, State.S2 );
        transitions.get( State.S1 ).put( Action.a4, State.S2 );
        transitions.get( State.S2 ).put( Action.a1, State.S4 );
        transitions.get( State.S2 ).put( Action.a2, State.S3 );
        transitions.get( State.S2 ).put( Action.a3, State.S1 );
        transitions.get( State.S2 ).put( Action.a4, State.S4 );
        transitions.get( State.S3 ).put( Action.a1, State.S1 );
        transitions.get( State.S3 ).put( Action.a2, State.S4 );
        transitions.get( State.S3 ).put( Action.a3, State.S4 );
        transitions.get( State.S3 ).put( Action.a4, State.S1 );
        transitions.get( State.S4 ).put( Action.a1, State.S1 );
        transitions.get( State.S4 ).put( Action.a2, State.S2 );
        transitions.get( State.S4 ).put( Action.a3, State.S2 );
        transitions.get( State.S4 ).put( Action.a4, State.S4 );
    }

    private static Integer eval_R( State s, Action a ) {
	    return rewards.get( s ).get( a );
    }

    private static State eval_T( State s, Action a ) {
	    return transitions.get( s ).get( a );
    }

    private static void print_V() {
	    for ( State s: State.values() ) {
		    System.out.print( V.get( s ) );
		    System.out.print( ", " );
	    }
	    System.out.println();
    }

    public static void main( String[] args ) {
        initTables();
	// cambio minimo para considerar que la convergencia sigue en proceso
	double epsilon = 0.001;

	// TODO:
	// leer gamma del teclado
	// implementar el aprendizaje según el algoritmo del enunciado
	// imprimir los valores de V luego de cada iteración sobre todos los estados
	// recordar (de algun modo) los valores del vector V de la anterior iteración
	// terminar el proceso cuando el cambio de los números es menor a epsilon

    }


}
