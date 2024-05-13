
package acme.testing.developer.trainingSessions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class DeveloperTrainingSessionListTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/developer/training-session/list-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void test100Positive(final int trainingModuleIndex, final int sessionIndex, final String location, final String instructor, final String ini_date, final String final_date) {
		//Hay q poner todos los atributos?

		super.signIn("developer1", "developer1");

		super.clickOnMenu("Developer", "Training sessions");
		super.checkListingExists();

		super.clickOnListingRecord(trainingModuleIndex);
		super.clickOnButton("Training sessions");
		super.checkColumnHasValue(sessionIndex, 0, location);
		super.checkColumnHasValue(sessionIndex, 1, instructor);
		super.checkColumnHasValue(sessionIndex, 2, ini_date);
		super.checkColumnHasValue(sessionIndex, 3, final_date);

		super.signOut();
	}

	@Test
	public void test200Negative() {
		//no casos negativos solo listado
	}

	@Test
	public void test300Hacking() {
		super.checkLinkExists("Sign in");
		super.request("/developer/training-session/list");
		super.checkPanicExists();

		super.signIn("administrator1", "administrator1");
		super.request("/developer/training-session/list");
		super.checkPanicExists();
		super.signOut();

		super.signIn("manager1", "manager1");
		super.request("/developer/training-session/list");
		super.checkPanicExists();
		super.signOut();

		super.signIn("auditor1", "auditor1");
		super.request("/developer/training-session/list");
		super.checkPanicExists();
		super.signOut();

		super.signIn("client1", "client1");
		super.request("/developer/training-session/list");
		super.checkPanicExists();
		super.signOut();

		//el de ignacio?
		//super.signIn("auditor1", "auditor1");
		//super.request("/developer/training-session/list");
		//super.checkPanicExists();
		//super.signOut();
	}

}
