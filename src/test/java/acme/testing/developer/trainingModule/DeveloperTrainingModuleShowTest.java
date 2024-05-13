
package acme.testing.developer.trainingModule;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class DeveloperTrainingModuleShowTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/developer/training-module/show-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void test100Positive(final int recordIndex, final String project, final String code, final String creationMoment, final String details, final String updateMoment, final String difficultyLevel, final String link, final String totalTime) {

		//todos los atributos string??
		super.signIn("developer1", "developer1");

		super.clickOnMenu("Developer", "Training modules");
		super.checkListingExists();
		super.clickOnListingRecord(recordIndex);

		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("creationMoment", creationMoment);
		super.checkInputBoxHasValue("details", details);
		super.checkInputBoxHasValue("updateMoment", updateMoment);
		super.checkInputBoxHasValue("difficultyLevel", difficultyLevel);
		super.checkInputBoxHasValue("link", link);
		super.checkInputBoxHasValue("totalTime", totalTime);

		super.signOut();
	}

	@Test
	public void test200Negative() {
		// no test negativos
	}

	@Test
	public void test300Hacking() {
		super.checkLinkExists("Sign in");
		super.request("/developer/training-module/show");
		super.checkPanicExists();

		super.signIn("administrator1", "administrator1");
		super.request("/developer/training-module/show");
		super.checkPanicExists();
		super.signOut();

		super.signIn("manager1", "manager1");
		super.request("/developer/training-module/show");
		super.checkPanicExists();
		super.signOut();

		super.signIn("auditor1", "auditor1");
		super.request("/developer/training-module/show");
		super.checkPanicExists();
		super.signOut();

		super.signIn("client1", "client1");
		super.request("/developer/training-module/show");
		super.checkPanicExists();
		super.signOut();

		//el de ignacio?
		//super.signIn("auditor1", "auditor1");
		//super.request("/developer/training-module/list");
		//super.checkPanicExists();
		//super.signOut();
	}

}
