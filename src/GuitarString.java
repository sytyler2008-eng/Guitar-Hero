//Tyler Sy and Logan Feistauer, CS3 K, 5th Period

public class GuitarString
{
	private RingBuffer ringBuffer;
	private int N;
	private int ticCount;
	
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
		this.ticCount = 0;
	}
	
	//second constructor
	public GuitarString(double[] init)
	{
		this.N = init.length;
		this.ringBuffer = new RingBuffer(N);
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
		double first = ringBuffer.peek(); //get the first sample
		ringBuffer.dequeue(); //get rid of the first sample in buffer (this allows us to also get second sample)
		double second = ringBuffer.peek(); //get the second sample
		double karplus = ((first + second) / 2.0) * 0.994; //find the average of the two
		ringBuffer.enqueue(karplus); //enqueue avg times decay factor at the end of the buffer
		ticCount++;
	}
	
	public double sample() {
        if (ringBuffer.isEmpty()) return 0.0;
        return ringBuffer.peek();
	}
	
	public int time()
	{
		return ticCount;
	}
}

