package allocation.brelaz;

import java.util.Iterator;

public class BrelazAlgorithm {

	/*
	 * The core of the Brélaz algorithm is written in this class. The class stores
	 * the graph to which the algorithm has to be applied.
	 */

	private Graph graph;

	public BrelazAlgorithm(Graph graph) {
		// The graph is passed to the algorithm via constructor
		this.graph = graph;
	}

	// Getters and Setters are not necessary

	public void assignChannel() throws Exception {
		/*
		 * Assign a channel to a compatible device. If the device has any available
		 * channel the one assigned is the first of the list otherwise a random channel
		 * is assigned.
		 */
		Device node = graph.selectNode(); // The node with the highest rank is stored. This node is obtained through
											// the graph method "selectNode()"
		Channel channel = null;
		if (!node.getAvailableChannels().isEmpty()) { // Check if the device has any available channel
			channel = node.getAvailableChannels().pop(); // If the condition is met the one assigned is the
															// first of the list
			node.setAssignedChan(channel);
		} else { // In the event that the previous condition is not met...
			channel = graph.getNetwork().getRandomChannel();
			node.setAssignedChan(channel); // ...a channel randomly taken from the Network
											// is assigned
		}
		removeChannel(node, channel); // In this case the channel is removed from all lists of adjacent nodes
		graph.assignedNode(node); // Of course the node inside the graph must be stored as "assigned"
		graph.update(); // Every time a channel is assigned to a node, an update of the topography of
						// the graph is carried out
	}

	public void removeChannel(Device node, Channel channel) throws Exception {
		/*
		 * Remove a channel from the list of available channels of all adjacent devices.
		 */
		Iterator<Link> iterator = node.getLinks().iterator(); // Iterates over the list containing the arches of the
																// node
		while (iterator.hasNext()) {
			Link link = iterator.next(); // For each arc, the input channel is eliminated from the two nodes
			link.getFirst().removeChannel(channel);
			link.getSecond().removeChannel(channel);
		}
	}

	public boolean endAlgorithm() {
		/*
		 * The list "unassignedNodes" is empty when every node has a channel assigned,
		 * so the algorithm may end.
		 */
		return graph.getUnassignedNodes().isEmpty();
	}

	public void startAlgorithm() throws Exception {
		// Proceeds to start the algorithm
		while (!endAlgorithm()) { // Keep assigning channels until there is no node left without an assigned
									// channel
			assignChannel(); // Calls the method for channel assignment
		}
	}
}
