//Tyler Sy and Logan Feistauer, CS3 K, 5th Period

public class GuitarString
{
	private RingBuffer ringBuffer;
	
	public GuitarString(double frequency)
	{
		int samplingRate = 44100; //given sampling rate
		int N = (int)(samplingRate / frequency); //capacity is sample rate divded by frequency
		this.ringBuffer = new RingBuffer(N); //make a new RingBuffer object with this capacity
	}
	
	public GuitarString(double[] init)
	{
		
	}
	
	public void pluck()
	{
		
	}
	
	public void tic()
	{
		
	}
	
	public double sample()
	{
		return 0.0;
	}
	
	public int time()
	{
		return 0;
	}
}



