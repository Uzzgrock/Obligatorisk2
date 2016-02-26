import java.util.GregorianCalendar;
import java.util.Scanner;

public class Gjest extends Kort {

	public Gjest(String navn, int pinKode) {
		super(navn, pinKode);
	}

	@Override
	public boolean checkPIN(int pinKode) {

		if (pinKode != 9999) {
			return false;
		} else {
			return true;
		}
	}

	public Kort Cloneable() {

		Kort kortClone = new Gjest(this.getNavn(), this.getPinKode());
		System.out.println(this.getKortNummer());

		kortClone.setKortNummer(this.getKortNummer());
		return kortClone;
	}

	public boolean isGuestCardExpired() {

		GregorianCalendar expirationDateForCard = new GregorianCalendar();
		expirationDateForCard.setTime(getDateCreated());
		expirationDateForCard.add(GregorianCalendar.HOUR, 168);
		if (super.currentTime().before(expirationDateForCard.getTime())) {
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
		
		else if (isGuestCardExpired() == true) {
			return false;
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