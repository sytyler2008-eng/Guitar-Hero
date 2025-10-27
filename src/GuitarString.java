// Tyler Sy and Logan Feistauer, CS3 K, 5th Period

<<<<<<< HEAD
public class GuitarString {
    private static final int SAMPLING_RATE = 44_100;
    private static final double ENERGY_DECAY = 0.996;
=======
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
			ringBuffer.dequeue();
		}
		for (int i = 0; i < N; i++)
		{
			ringBuffer.enqueue(Math.random() - 0.5);
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
>>>>>>> 3c4291bfde7116679b16884737218f70f2c5341b

    // public for the autograder – do NOT make it private!
    public RingBuffer ringBuffer;

    private int ticCount = 0;

    /* --------------------------------------------------------------
       Constructor 1 – a string at rest with a given frequency
       -------------------------------------------------------------- */
    public GuitarString(double frequency) {
        if (frequency <= 0) {
            throw new IllegalArgumentException("frequency must be > 0");
        }
        // N = ceil(samplingRate / frequency)
        int N = (int) Math.ceil(SAMPLING_RATE / frequency);
        ringBuffer = new RingBuffer(N);

        // fill with zeros (string at rest)
        for (int i = 0; i < N; i++) {
            ringBuffer.enqueue(0.0);
        }
    }

    /* --------------------------------------------------------------
       Constructor 2 – initialise buffer from an array (used for testing)
       -------------------------------------------------------------- */
    public GuitarString(double[] init) {
        if (init == null || init.length == 0) {
            throw new IllegalArgumentException("init array must be non-empty");
        }
        ringBuffer = new RingBuffer(init.length);
        for (double v : init) {
            ringBuffer.enqueue(v);
        }
    }

    /* --------------------------------------------------------------
       pluck – replace every item with white noise in [-0.5, 0.5)
       -------------------------------------------------------------- */
    public void pluck() {
        // empty the buffer first
        while (!ringBuffer.isEmpty()) {
            ringBuffer.dequeue();
        }

     
        for (int i = 0; i < N; i++) {
            double random = Math.random() - 0.5;          // [-0.5, 0.5)
            ringBuffer.enqueue(random);
        }
    }

    /* --------------------------------------------------------------
       tic – advance the simulation one step (Karplus-Strong update)
       -------------------------------------------------------------- */
    public void tic() {
        if (ringBuffer.isEmpty()) return;

        double first  = ringBuffer.dequeue();   // remove front
        double second = ringBuffer.peek();      // look at new front
        double avg    = (first + second) / 2.0;
        double newVal = avg * ENERGY_DECAY;

        ringBuffer.enqueue(newVal);
        ticCount++;
    }

    /* --------------------------------------------------------------
       sample – return the front value without removing it
       -------------------------------------------------------------- */
    public double sample() {
        if (ringBuffer.isEmpty()) return 0.0;
        return ringBuffer.peek();
    }

    /* --------------------------------------------------------------
       time – number of times tic() has been called
       -------------------------------------------------------------- */
    public int time() {
        return ticCount;
    }
}