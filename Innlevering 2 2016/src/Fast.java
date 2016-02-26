
public interface Fast {

	void settFornavn(String fornavn);

	String hentForNavn();

	void settEtterNavn(String etterNavn);

	String hentEtterNavn();

	void settFulltNavn(String forNavn, String etterNavn);

	String hentFulltNavn();

	double beregnKreditt();

	double beregnBonus();
}
