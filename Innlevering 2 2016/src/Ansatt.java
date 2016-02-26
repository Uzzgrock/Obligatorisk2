import java.util.GregorianCalendar;
import java.util.Scanner;

public class Ansatt extends Kort implements Fast {

	StringBuilder fulltNavn = new StringBuilder(super.getNavn());
	int i = fulltNavn.indexOf(" ");
	String forNavn = fulltNavn.substring(0, i);
	String etterNavn = fulltNavn.substring(i);
	double timeLønn;
	int ansiennitet;

	public Ansatt(String navn, int pinKode, double timeLønn, int ansiennitet) {
		super(navn, pinKode);
		this.timeLønn = timeLønn;
		this.ansiennitet = ansiennitet;
	}

	public Kort Cloneable() {
	
		Kort kortClone = new Ansatt(this.getNavn(), this.getPinKode(), timeLønn, ansiennitet);
		System.out.println(this.getKortNummer());
	
		kortClone.setKortNummer(this.getKortNummer());
		return kortClone;
	}

	public String toString() {
		return super.toString() + "\nTimelønn: " + this.getTimeLønn() + ",-" + "\nAnsiennitet: " + this.getAnsiennitet()
				+ " år";
	}

	public double getTimeLønn() {
		return timeLønn;
	}

	public void setTimeLønn(double timeLønn) {
		this.timeLønn = timeLønn;
	}

	public int getAnsiennitet() {
		return ansiennitet;
	}

	public void setAnsiennitet(int ansiennitet) {
		this.ansiennitet = ansiennitet;
	}

	public double beregnKreditt() {
		double konstantSomBeregnerKredittAvTimelønn = 0.15;
		return this.getTimeLønn() * konstantSomBeregnerKredittAvTimelønn;
	}

	public double beregnBonus() {
		double konstantSomBeregnerBonusBasertPåAnsiennitet = 0.25;
		return this.getAnsiennitet() * konstantSomBeregnerBonusBasertPåAnsiennitet;
	}

	@Override
	public void settFulltNavn(String forNavn, String etterNavn) {
		fulltNavn.delete(0, fulltNavn.length());
		fulltNavn.append(forNavn + " " + etterNavn);
	}

	@Override
	public String hentFulltNavn() {
		return fulltNavn.toString();
	}

	@Override
	public void settEtterNavn(String etterNavn) {
		fulltNavn.replace(i + 1, fulltNavn.length(), etterNavn);
	}

	@Override
	public String hentEtterNavn() {
		i = fulltNavn.indexOf(" ");
		return fulltNavn.substring(i + 1);
	}

	@Override
	public void settFornavn(String forNavn) {
		fulltNavn.replace(0, i, forNavn);
	}

	@Override
	public String hentForNavn() {
		i = fulltNavn.indexOf(" ");
		return fulltNavn.substring(0, i);
	}

	@Override
	public boolean checkPIN(int pinKode) {

		if (super.getPinKode() == pinKode) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean IsTimeDuringWorkingHours() {

		GregorianCalendar timeNow = new GregorianCalendar();
		System.out.println(timeNow.get(GregorianCalendar.HOUR_OF_DAY));
		if ((timeNow.get(GregorianCalendar.DATE) == 6) || (timeNow.get(GregorianCalendar.DATE) == 7)) {
			return false;
		} else
			if ((timeNow.get(GregorianCalendar.HOUR_OF_DAY) < 7) || (timeNow.get(GregorianCalendar.HOUR_OF_DAY) > 17)) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public boolean methodToDetermineIfUserGetAccess() {

		if (super.isSperret() == true) {
			return false;
		}
	
		else if (IsTimeDuringWorkingHours() == true) {
			return true;
		}
	
		Scanner input = new Scanner(System.in);
		System.out.print("Please enter your PIN code: ");
		int enteredPinCode = input.nextInt();
		if (this.checkPIN(enteredPinCode) == true) {
			return true;
		}
	
		else {
			return false;
		}
	}
}