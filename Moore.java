import java.util.ArrayList;
import java.util.Vector;


/**
 * Classe permettant d'implémenter l'algorithme de Moore-Dijkstra
 * Hypothèse : la fonction coût est positive et les circuits sont autorisés.
 */
public class Moore {

	//attributs
	protected Graphe graphe ;
	protected String s ; //nom du noeud de départ
	protected String t; // nom du noeud d'arrivée
	protected Vector<Integer> pi ; //tableau des distances à l'origine s ; les indices du tableau correspondent au numéto du sommet (indice dans le graphe)
	protected Vector<ArrayList<String>> pred ;//tableau contenant les listes des prédecessurs pour chaque sommet ;  les indices du tableau correspondent au numéto du sommet (indice dans le graphe)
	protected ArrayList<String> aTraiter ; //liste des noeuds qui ne sont pas encore traités (notation "T" dans le cours)

	//constructeur
	/**
	 * 
	 * @param g : graphe d'entrée
	 * @param s : nom du noeud d'entrée
	 * @param t : nom du noeud de sortie
	 */
	public Moore(Graphe g , String s , String t){
		this.graphe=g;
		this.s=s;
		this.t=t;
		this.pi=new Vector<Integer>(this.graphe.getNbSommets());
		//initialisation des prédecesseurs
		this.pred = new Vector<ArrayList<String>>(this.graphe.getNbSommets());
		for (int i=0 ; i<this.graphe.getNbSommets() ; i++){
			pred.set(i, new ArrayList<String>());
		}
		this.aTraiter=new ArrayList<String>();
		
	}
	
	//méthodes
	/**
	 * 
	 * @return le graphe du plus court chemin
	 */
	public Graphe run(){
		
		//Initialisation
		
		//initialisation de la liste à traiter : tous les sommets sauf l'origine
		for (int i=0 ; i<graphe.getNbSommets() ; i++){
			if (! graphe.getListesommets()[i].equals(s)){	
				aTraiter.add(graphe.getListesommets()[i]);
			}
		}
		
		//initialisation de pi : 0 pour la source, distance au commet pour les successuers directs, +infini pour les autres
		Noeud noeudS = graphe.getNoeud(s);
		
		pi.set(graphe.indexOf(noeudS), 0); //on met la valeur de s à zéro
		//parcours de la liste des noeuds de aTraiter : s n'est pas dedans
		for (String nomNoeud : aTraiter){
			//si un arc existe entre s et le noeud i, on met la distance de cet arc dans pi[i] sinon on met +infini (max_value)
			
			Noeud noeud = graphe.getNoeud(nomNoeud); //noeud correspondant à nomNoeud
			
			int piIndex = graphe.indexOf(noeud);//indice de noeud dans le tableau pi
			
			if ( noeudS.successeurs.contains(noeud)){ //si le noeud est dans les successeurs de s...
				int succIndex = noeudS.successeurs.indexOf(noeud) ;//indice de noeud dans la liste des successeurs de s
				pi.set(piIndex, noeudS.getFlux(succIndex));
			}
			else{
				pi.set(piIndex, Integer.MAX_VALUE);
			}
				
		}
		
		//algorithme
		
		while (! aTraiter.isEmpty()){
			
			//trouver x0, l'index du sommet qui minimise pi
			int x0 =0 ;
			
			for (int i = 1 ; i<pi.size() ; i++){
				if (pi.get(x0)>pi.get(i)){
					x0=i;
				}
			}
			
			Noeud noeudX0 = graphe.getNoeud(x0); //noeud qui correspond à l'indice x0
			
			//oter le sommmet x0 de aTraiter
			aTraiter.remove(noeudX0); //les indices de pi sont les mêmes que les indices du graphe
			
			
			for (String nomNoeud : aTraiter){
				Noeud noeud = graphe.getNoeud(nomNoeud); //noeud de nom "nomNoeud"
				int noeudIndex = graphe.indexOf(noeud); //indice du noeud nomNoeud dans le graphe
				int succIndex = noeudX0.successeurs.indexOf(noeud) ;//index de nomNoeud parmi les successeurs de x0 @TODO : what if noeud n'est pas parmi les succ ??
				
				if (pi.get(noeudIndex)>(pi.get(x0)+))
			}
		}
		
		
	}
	
	
	
	
	
}
