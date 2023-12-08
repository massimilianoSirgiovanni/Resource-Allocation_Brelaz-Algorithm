package allocation.brelaz;

import java.util.LinkedList;

public class Execution {

	// This class contains the main and is used to execute the algorithm on a
	// concrete graph

	public static void main(String[] args) throws Exception {

		// The graph that has been created is identical to the graph present on the
		// project text
///////////////Resource management//////////////////////////
		// Five channels have been created below, however any number of channels can be
		// applied to the algorithm (In this case, in order to see changes inside the
		// graph, it is advisable to apply only two channels to the algorithm).
		Channel C0 = new Channel("C0");
		Channel C1 = new Channel("C1");
		Channel C2 = new Channel("C2");
		Channel C3 = new Channel("C3");
		Channel C4 = new Channel("C4");
		// A list is created which will contain the newly created channels and which
		// will be used for the creation of a Network
		LinkedList<Channel> chan = new LinkedList<Channel>();
		chan.add(C0);
		chan.add(C1);
		chan.add(C2);
		chan.add(C3);
		chan.add(C4);
//The network is created, passing the newly created channel list to the constructor
		Network net = new Network(chan);
////////////Creation of nodes and arcs////////////////////////	
		// It would be appropriate for each node to have a different name, so that it
		// can be identified
		Device A = new Device("A");
		Device B = new Device("B");
		Device C = new Device("C");
		Device D = new Device("D");
		Device E = new Device("E");
		Device F = new Device("F");
		Device G = new Device("G");
		Device H = new Device("H");
		Device I = new Device("I");
		Device J = new Device("J");
		Device K = new Device("K");
		Device L = new Device("L");
		Device M = new Device("M");
		Device N = new Device("N");
		// The two nodes it connects are passed to the constructor of the Link
		Link AB = new Link(A, B);
		Link BC = new Link(B, C);
		Link BD = new Link(B, D);
		Link CD = new Link(C, D);
		Link ED = new Link(E, D);
		Link JD = new Link(J, D);
		Link FD = new Link(F, D);
		Link FG = new Link(G, F);
		Link FI = new Link(I, F);
		Link FH = new Link(F, H);
		Link GH = new Link(G, H);
		Link IK = new Link(I, K);
		Link IN = new Link(I, N);
		Link HN = new Link(H, N);
		Link HL = new Link(H, L);
		Link LM = new Link(L, M);
//////////////////////////////////////////////////////////////
/////////Graph Management////////////////////////////////////
		Graph Gr = new Graph(net); // The graph is created by passing the network
		// All nodes belonging to the graph are added
		Gr.addNode(A);
		Gr.addNode(B);
		Gr.addNode(C);
		Gr.addNode(D);
		Gr.addNode(E);
		Gr.addNode(F);
		Gr.addNode(G);
		Gr.addNode(H);
		Gr.addNode(I);
		Gr.addNode(J);
		Gr.addNode(K);
		Gr.addNode(L);
		Gr.addNode(M);
		Gr.addNode(N);
		// All the Links that are part of the graph are added
		Gr.addLink(AB);
		Gr.addLink(LM);
		Gr.addLink(HL);
		Gr.addLink(HN);
		Gr.addLink(IN);
		Gr.addLink(IK);
		Gr.addLink(GH);
		Gr.addLink(FH);
		Gr.addLink(FI);
		Gr.addLink(FG);
		Gr.addLink(FD);
		Gr.addLink(BC);
		Gr.addLink(BD);
		Gr.addLink(CD);
		Gr.addLink(ED);
		Gr.addLink(JD);
		Gr.printGraph(); // The graph is printed before the application of the algorithm, in order to
							// verify the differences
		BrelazAlgorithm Br = new BrelazAlgorithm(Gr); // The object containing the methods for applying the algorithm is
														// created
		Br.startAlgorithm(); // The algorithm applies
		Gr.printGraph(); // The graph is printed after applying the algorithm to it

	}
}
