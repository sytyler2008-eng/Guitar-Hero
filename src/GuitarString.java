//Tyler Sy and Logan Feistauer, CS3 K, 5th Period

public class GuitarString
{
	private RingBuffer ringBuffer;
	private int N;
	
	//first constructor
	public GuitarString(double frequency)
	{
		int samplingRate = 44100; //given sampling rate
		this.N = (int)(samplingRate / frequency); //capacity is sample rate divded by frequency
		this.ringBuffer = new RingBuffer(N); //make a new RingBuffer object with this capacity
		for (int i = 0; i < N; i++)
		{
			ringBuffer.enqueue(0.0);
		}
	}
	
	//second constructor
	public GuitarString(double[] init)
	{
		this.ringBuffer = new RingBuffer(init.length);
		for (int i = 0; i < init.length; i++)
		{
			ringBuffer.buffer[i] = init[i];
		}
	}
	
	public void pluck()
	{
		while (!ringBuffer.isEmpty())
		{
			ringBuffer.dequeue(); //empties out all the former elements until buffer is empty
		}
		for (int i = 0; i < N; i++)
		{
			ringBuffer.enqueue(Math.random() - 0.5); //fills up the buffer with the new things
		}
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



