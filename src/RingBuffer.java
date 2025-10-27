//Tyler Sy and Logan Feistauer, CS3 K, 5th Period

import java.util.*;

public class RingBuffer
{
	double[] buffer; //array to hold all the elements
	int capacity; //max amount the buffer can hold
	int size; //how much stuff is currently in the buffer
	int first; //first item in the buffer to dequeue
	int last; //next available slot in the buffer that you can enqueue
	
	public RingBuffer(int capacity)
	{
		if (capacity < 1)
		{
			throw new IllegalStateException(); //if capacity is less than 1, give error
		}
		this.capacity = capacity;
		this.buffer = new double[capacity]; //we set ts to empty array with a given capacity
		this.size = 0;
		this.first = 0;
		this.last = 0;
	}
	
	public int size()
	{
		return size; //returns how many elements are in the buffer
	}
	
	public boolean isEmpty()
	{
		
		//if buffer is empty, return true; otherwise, return false
		if (size == 0)
		{
			return true;
		}
		return false;
	}
	
	public boolean isFull()
	{
		//same thing with isEmpty(), but if full or not
		if (size == capacity)
		{
			return true;
		}
		return false;
	}
	
	public void enqueue(double x) {
        if (isFull())
        {
            throw new IllegalStateException(); //if buffer full, give error
        }
        buffer[last] = x; //the last element will be enqueued
        last = (last + 1) % capacity; //then the position of last will be shifted accordingly
        //if it reaches the end, it should loop back around to the next available slot
        
        size++; //size increases because we added smth new
    }

	public double dequeue()
	{
        if (isEmpty())
        {
            throw new NoSuchElementException(); //if empty, give error
        }
        double item = buffer[first]; //we make a placeholder variable for the first item
        buffer[first] = 0.0; //the position where the first item was at becomes empty
        first = (first + 1) % capacity; //then first position shifts to next available element
        
        size--; //size decreases because we removed smth
        return item; //return the thing we removed
    }
	
	public double peek()
	{
		if (isEmpty())
		{
			throw new NoSuchElementException(); //if empty, give error
		}
		return buffer[first]; //look at what the first item is in buffer
	}
	
	public String toString()
	{
		if (isEmpty())
		{
			return "[]"; //if nothing inside, then return an empty array
		}
		String str = "["; //we will add the elements into our final str as we go along
		for (int i = 0; i < size; i++)
		{
			int index = (first + i) % capacity; //variable to find the next element to print out
			str = str + buffer[index]; //add that element to str
			if (i < (size - 1))
			{
				str = str + ", "; //add comma (unless it's at the end of the buffer)
			}
		}
		str = str + "]";
		return str; //we should then have an array with all the elements from first to last
	}
}



