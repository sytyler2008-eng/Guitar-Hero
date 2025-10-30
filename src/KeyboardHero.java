//Tyler Sy and Logan Feistauer, CS3 K, 5th Period

public class KeyboardHero
{
	public static void main(String[] args)
    {
    	String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    	String pos="nn//SS/ ..,,mmn //..,,m //..,,m nn//SS/ ..,,mmn   (S = space)\r\n";
    	int count=0;
		GuitarString[] strings = new GuitarString[keyboard.length()];
        for (int i = 0; i < keyboard.length(); i++)
        {
            double frequency = 440.0 * Math.pow(1.05956, i - 24);
            strings[i] = new GuitarString(frequency);
        }


        final double TEXT_POS_X = 0.5;
        final double TEXT_POS_Y = 0.5;
        
        StdDraw.text(TEXT_POS_X, TEXT_POS_Y, "Type a keyboard character to play a note!");

        while (true) {
            //print next value from pos
        	StdDraw.text(TEXT_POS_X, TEXT_POS_Y,pos.substring(count));
        	count=count+1;
            // check if the user has typed a key, and, if so, process it
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int index = keyboard.indexOf(key);
                if (index != -1)
                {
                	strings[index].pluck();
                	StdDraw.clear();
                }
            }

            // compute the superposition of all the samples
            double sample = 0.0;
            for (GuitarString s: strings)
            {
            	sample += s.sample();
            }

            // send the result to standard audio
            StdAudio.play(sample);

            // advance the simulation of each string
            for (GuitarString s: strings)
            {
            	s.tic();
            }
        }
        
    }
}



