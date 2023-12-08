package allocation.brelaz;

import java.util.LinkedList;

public class Network {

	/*
	 * The Network contains the List that stores the Channels that are to be
	 * assigned to the Devices.
	 */

	public LinkedList<Channel> channelList;

	public Network(LinkedList<Channel> channelList) {
		// A list of channels previously created is passed to the manufacturer
		this.channelList = channelList;
	}

	//////////////// Getter//////////////////////////

	public LinkedList<Channel> getChannelList() {
		return channelList;
	}
//////////////////////////////////////////////////////

	public Channel getRandomChannel() {
		/*
		 * This method allows the selection of a random Channel to be assigned to a
		 * Device that hasn't any available. It simply returns the Channel which index
		 * is a random number between zero and N-1.
		 */
		return channelList.get((int) (Math.random() * channelList.size()));
	}

}
