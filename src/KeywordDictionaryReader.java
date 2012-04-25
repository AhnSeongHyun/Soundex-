import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class KeywordDictionaryReader
{
	public List<String> ReadWordFromDictionary(String dicPath)
	{ 
		
		List<String> wordList = Collections.emptyList();
		
		BufferedReader brd = null;
		String rLine = null;

		try
		{
			brd = new BufferedReader(new InputStreamReader(new FileInputStream(
					dicPath), "UTF8"));
			
			wordList = new ArrayList<String>();
			

			for (;;)
			{
				if ((rLine = brd.readLine()) != null)
				{
					wordList.add(rLine);
				}
				else
				{
					break;
				}

			}

			brd.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		return wordList; 
	}
	
}
