package allocation.brelaz;

import java.util.Iterator;
import java.util.LinkedList;

public class Graph {

	/*
	 * This class stores the nodes (Device) and links (Arc) that connects them. It
	 * has a list of Device without assigned channels to simplify the execution of
	 * the Brélaz algorithm.
	 */
	private LinkedList<Device> nodes = new LinkedList<Device>();

	private LinkedList<Device> unassignedNodes = new LinkedList<Device>();

	private LinkedList<Link> links = new LinkedList<Link>();

	private Network network;

	public Graph(Network network) {

		// A Network object is passed to the constructor of the graph, so that the graph
		// can initialize the availableChannels list of its Nodes equal to that of the
		// Network (as required by the algorithm)

		this.network = network;
	}

	/////////////////// Getters//////////////////////////////

	public LinkedList<Device> getNodes() {
		return nodes;
	}

	public LinkedList<Device> getUnassignedNodes() {
		return unassignedNodes;
	}

	public LinkedList<Link> getArch() {
		return links;
	}

	public Network getNetwork() {
		return network;
	}

	////////////////////////////////////////////////
	//////////////// Add and Remove////////////////

	public void addNode(Device node) throws Exception {
		nodes.add(node);
		if (node.getAssignedChan() == null) {
			unassignedNodes.add(node);
			uploadChannels(node); // initialize the availableChannels list of its Nodes equal to that of the
									// Network
		}
	}

	public void removeNode(Device node) throws Exception {
		nodes.remove(node);
	}

	public void assignedNode(Device node) throws Exception {
		// Method to be used when a channel is assigned to a node, consequently the
		// latter will be removed from the "unassignedNodes" list
		unassignedNodes.remove(node);
	}

	public void addLink(Link link) throws Exception {
		links.add(link);
		// When a link to the graph is added, it is also added to the lists of nodes
		// connected by that link
		Iterator<Device> iterator = nodes.iterator();
		Device node = null;
		while (iterator.hasNext()) {
			node = iterator.next();
			if (link.getFirst().equals(node) || link.getSecond().equals(node)) {
				node.addLink(link);
			}
		}
	}

	public void removeLink(Link link) throws Exception {
		links.remove(link);
	}
//////////////////////////////////////////////////

	public void update() throws Exception {
		// Updates the topology of the entire graph
		Iterator<Device> iterator = nodes.iterator();
		while (iterator.hasNext()) { // calls the updateTopology() method on all nodes in the graph
			iterator.next().updateTopology();
		}
	}

	public void printGraph() {
		// Print a graphical representation of the graph...
		Iterator<Device> iterator = nodes.iterator();
		while (iterator.hasNext()) { // ...calling on all nodes of the graph...
			iterator.next().printDevice(); // ...the printDevice() method (then printing them).
		}
	}

	public void uploadChannels(Device node) throws Exception {
		// initialize the availableChannels list of its Nodes equal to that of the
		// Network
		Iterator<Channel> iterator = network.getChannelList().iterator();
		while (iterator.hasNext()) {
			node.addChannel(iterator.next());
		}
	}

	public Device selectNode() {
		// Method that selects and return the node with the largest rank
		Iterator<Device> iterator = getUnassignedNodes().iterator();
		Device node = iterator.next();
		int max = node.rank(); // Gets the rank of the node
		Device temp = null;
		while (iterator.hasNext()) {
			temp = iterator.next(); // Inserts the next node into temp
			if (max < temp.rank()) { // checks whether the rank of the node stored in temp is greater
				node = temp; // If the previous condition were to be satisfied, then it would become temp the
				// node to be returned
				max = temp.rank();
			}
		}
		return node;
	}

}
