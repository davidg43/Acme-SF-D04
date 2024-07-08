
package acme.features.authenticated.developer;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.data.accounts.UserAccount;
import acme.client.repositories.AbstractRepository;
import acme.roles.Developer;

@Repository
public interface AuthenticatedDeveloperRepository extends AbstractRepository {

	@Query("SELECT u FROM UserAccount u WHERE u.id = :id")
	UserAccount findOneUserAccountById(int id);

	@Query("SELECT d FROM Developer d WHERE d.userAccount.id = :id")
	Developer findOneDeveloperByUserAccountId(int id);
}
