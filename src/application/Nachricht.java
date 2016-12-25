package application;
public class Nachricht implements Comparable<Nachricht>
{
	long timestamp;
	String inhalt;
	String theme;
	int length;
	public Nachricht(long pTime,String pInhalt, String pTheme, int pLength)
	{
		timestamp = pTime;
		inhalt = pInhalt;
		theme = pTheme;
		length = pLength;
	}
	public String getTheme()
	{
		return theme;
	}
	public String getInhalt()
	{
		return inhalt;
	}
	public long getTimestamp()
	{
		return timestamp;
	}
	public void show()
	{
		System.out.println("TIME : " + timestamp + " THEME: " + theme + " INHALT: " + inhalt + " LENGTH: " + length);
	}
	public int getLength()
	{
		return length;
	}
	@Override
	public int compareTo(Nachricht arg0) {
		return (int)(this.getTimestamp() - arg0.getTimestamp());
	}
}