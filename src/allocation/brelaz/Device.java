package allocation.brelaz;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.stream.Stream;

public class Device {

	private LinkedList<Channel> availableChannels = new LinkedList<Channel>();

	private LinkedList<Link> links = new LinkedList<Link>();

	private Channel assignedChan;

	private String name;

	public Device(String name) {
		this.name = name;
	}

	////////// Getters and Setters////////////

	public LinkedList<Channel> getAvailableChannels() {
		return availableChannels;
	}

	public Channel getAssignedChan() {
		return assignedChan;
	}

	public void setAssignedChan(Channel assignedChan) {
		this.assignedChan = assignedChan;
	}

	public LinkedList<Link> getLinks() {
		return links;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	////////////////////////////////////////

	/////////////// Add and Remove//////////////////
	// Subsequent methods are used to update the LinkedLists in attributes

	public void addLink(Link link) throws Exception {
		links.add(link);
	}

	public void removeLink(Link link) throws Exception {
		links.remove(link);
	}

	public void addChannel(Channel channel) throws Exception {
		availableChannels.add(channel);
	}

	public void removeChannel(Channel channel) throws Exception {
		availableChannels.remove(channel);
	}

	////////////////////////////////////////////////////////

	public int rank() {
		// Returns the rank of the node
		int n = 0;
		Stream<Link> stream = links.stream(); // A stream is used to iterate
		n = (int) stream.count(); // Through the stream count () function you get the number of arcs connected to
									// the node (the rank)
		return n;
	}

	public LinkedList<Link> updateAssistent() {
		LinkedList<Link> buffer = new LinkedList<Link>(); // creates a support buffer in which to store the arcs to be
															// deleted
		Iterator<Link> iterator = links.iterator();
		Link arc = null;
		while (iterator.hasNext()) {
			arc = iterator.next();
			if (arc.getSecond().getAssignedChan() != null && arc.getFirst().getAssignedChan() != null
					&& arc.getFirst().getAssignedChan().equals(arc.getSecond().getAssignedChan())) {
				buffer.add(arc);
			}
		}

		return buffer;
	}

	public void updateTopology() throws Exception {
		// updates the topology of the node, eliminating the nodes according to the
		// rules dictated by the algorithm
		if (getAssignedChan() != null && !links.isEmpty()) { // perform an initial check, checking if the node has an
																// assigned channel or if the list of arcs is empty. In
																// both cases, updating is not necessary
			LinkedList<Link> buffer = updateAssistent(); // Load the support buffer with the arcs to be deleted, using
															// the method updateAssistent()
			Iterator<Link> iterator = buffer.iterator(); // By using an iterator...
			while (iterator.hasNext()) { // ...it iterates over the whole list "buffer"...
				this.removeLink(iterator.next()); // ...and the arches to be removed are removed
			}
		}
	}

	public void printDevice() {
		// This method makes a graphical representation of the node...
		System.out.println("Node: " + name); // ...by printing the name of the node...
		System.out.print("Assigned Channel: ");// ...the assigned channel...
		if (assignedChan != null) { // ..if it is present...
			assignedChan.printChannels();
		} else {
			System.out.println("There is no assigned channel");
		}
		Iterator<Link> iterator = links.iterator(); // ...and all the arches that are connected to the node itself.
		while (iterator.hasNext()) {
			iterator.next().printLink();
		}
	}

	public boolean equals(Device node) {
		return this.getName().equals(node.getName()); // Check if two nodes are the same, check if they have the same
														// name
	}

}
