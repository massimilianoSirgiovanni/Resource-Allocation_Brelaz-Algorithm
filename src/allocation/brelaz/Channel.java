package allocation.brelaz;

public class Channel {

	/*
	 * The channel is the resource to be assigned to the Devices of the Network. In
	 * this case it is simply defined by its name.
	 */

	private String name;

	public Channel(String name) {
		this.name = name;
	}

///////////Getters and Setters///////////////
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

/////////////////////////////////////////////

	public void printChannels() {
		// This method makes a graphical representation of the channels
		System.out.println(name); // The graphical representation consists in printing the name of the channel
	}

	public boolean equals(Channel C) {

		return name.equals(C.getName()); // Check if two channels are the same, check if they have the same name
	}
}
