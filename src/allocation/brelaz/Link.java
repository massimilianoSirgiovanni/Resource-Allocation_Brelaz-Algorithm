package allocation.brelaz;

public class Link {

	// This class represents the arcs of the graph

	// The two attributes represent the nodes that are connected through this arc

	private Device first;
	private Device second;

	public Link(Device first, Device second) {
		this.first = first;
		this.second = second;
	}

///////////////Getters/////////////////////////

	public Device getFirst() {
		return first;
	}

	public Device getSecond() {
		return second;
	}

/////////////////////////////////////////////////

	public void printLink() {
		// This method makes a graphical representation of the arcs

		System.out.println("The arch connects the following nodes: " + first.getName() + "->" + second.getName());
	}

}
