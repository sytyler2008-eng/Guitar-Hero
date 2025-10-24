//Tyler Sy and Logan Feistauer, CS3 K, 5th Period

import java.io.*;

public class RingBuffer
{
	double[] buffer;
	int size;
	int rear;
	int capacity;
	
	public RingBuffer(int capacity)
	{
		this.capacity = capacity;
		this.buffer = new double[capacity];
		this.size = 0;
		this.rear = 0;
	}
	
	public int size() //Tyler Sy
	{
		for (int i = 0; i < buffer.length; i++)
		{
			if (buffer[i] != 0.0)
			{
				size++;
			}
		}
		return size;
	}
	
	public boolean isEmpty() //Tyler Sy
	{
		if (size == 0)
		{
			return true;
		}
		return false;
	}
	
	public boolean isFull() //Tyler Sy
	{
		if (size == buffer.length)
		{
			return true;
		}
		return false;
	}
	
	public void enqueue(double x) {
        if (isFull()) {
            throw new IllegalStateException("Buffer is full");
        }
        buffer[rear] = x;
        rear = (rear + 1) % capacity; // wrap around if needed
        size++;
    }

	public double dequeue()
	{
		
	}
	
	public double peek() //Tyler Sy
	{
		double x = 0;
		for (int i = 0; i < buffer.length; i++)
		{
			if (buffer[i] != 0.0)
			{
				x = buffer[i];
			}
		}
		return x;
	}
	
	public String toString()
	{
		
	}
}