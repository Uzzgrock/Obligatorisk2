import java.util.Date;
import java.util.GregorianCalendar;

public abstract class Kort implements Cloneable, Comparable<Kort> {

	private String navn;
	private int pinKode;
	private static int unikIdForKortNummer = 1000;
	private boolean sperretKort;
	private int kortNummer;
	private final GregorianCalendar dateCreated = new GregorianCalendar();

	public Kort() {
		unikIdForKortNummer++;
		kortNummer = unikIdForKortNummer;
		sperretKort = false;
	}

	public int compareTo(Kort k) {
		
		int i = this.getNavn().indexOf(" "), j = k.getNavn().indexOf(" ");
		if ((this.getNavn().substring(i, this.getNavn().length()))
				.compareToIgnoreCase(k.getNavn().substring(j, k.getNavn().length())) > 0) {
			return 1;
		} else if ((this.getNavn().substring(i, this.getNavn().length()))
				.compareToIgnoreCase(k.getNavn().substring(j, k.getNavn().length())) < 0) {
			return -1;
		} else {
			if (this.getNavn().substring(0, i).compareToIgnoreCase(k.getNavn().substring(0, j)) > 0) {
				return 1;
			} else if (this.getNavn().substring(0, i).compareToIgnoreCase(k.getNavn().substring(0, j)) < 0) {
				return -1;
			} else {
				return 0;
			}
		}
	}

	public Kort(String navn, int pinKode) {
		unikIdForKortNummer++;
		this.navn = navn;
		this.pinKode = pinKode;
		kortNummer = unikIdForKortNummer;
		sperretKort = false;
	}

	public int getKortNummer() {
		return kortNummer;
	}

	public String getNavn() {
		return navn;
	}

	public boolean isSperret() {
		return sperretKort;
	}

	public void setPinKode(int pinKode) {
		this.pinKode = pinKode;
	}

	public int getPinKode() {
		return pinKode;
	}

	public void setKortNummer(int kortNummer) {
		this.kortNummer = kortNummer;
	}

	public Date getDateCreated() {
		return dateCreated.getTime();
	}

	@Override
	public String toString() {
		return "\nNavn: " + navn + "\nPIN-kode: " + pinKode + "\nKortnummer: " + kortNummer + "\nKortet sperret? "
				+ sperretKort;
	}

	public abstract boolean checkPIN(int pinKode);

	public abstract boolean methodToDetermineIfUserGetAccess();

	public Date currentTime() {
		GregorianCalendar timeRightNow = new GregorianCalendar();
		return timeRightNow.getTime();
	}
}