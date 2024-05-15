
package acme.testing.developer.trainingModule;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.developer.TestHarness;

public class DeveloperTrainingModuleListTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/developer/training-module/list-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void test100Positive(final int recordIndex, final String code, final String details, final String project_code) {

		super.signIn("developer1", "developer1");

		super.clickOnMenu("Developer", "Training modules");
		super.checkListingExists();

		super.checkColumnHasValue(recordIndex, 0, code);
		super.checkColumnHasValue(recordIndex, 1, details);
		super.checkColumnHasValue(recordIndex, 2, project_code);

		super.signOut();
	}

	@Test
	public void test200Negative() {
		//No casos negativos ya que solo es listar
	}

	@Test
	public void test300Hacking() {
		super.checkLinkExists("Sign in");
		super.request("/developer/training-module/list");
		super.checkPanicExists();

		super.signIn("administrator1", "administrator1");
		super.request("/developer/training-module/list");
		super.checkPanicExists();
		super.signOut();

		super.signIn("manager1", "manager1");
		super.request("/developer/training-module/list");
		super.checkPanicExists();
		super.signOut();

		super.signIn("auditor1", "auditor1");
		super.request("/developer/training-module/list");
		super.checkPanicExists();
		super.signOut();

		super.signIn("client1", "client1");
		super.request("/developer/training-module/list");
		super.checkPanicExists();
		super.signOut();

		//el de ignacio?
		//super.signIn("auditor1", "auditor1");
		//super.request("/developer/training-module/list");
		//super.checkPanicExists();
		//super.signOut();
	}

}
