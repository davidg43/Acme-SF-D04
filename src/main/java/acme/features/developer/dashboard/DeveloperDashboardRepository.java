
package acme.features.developer.dashboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.roles.Developer;

@Repository
public interface DeveloperDashboardRepository extends AbstractRepository {

	@Query("SELECT d FROM Developer d WHERE d.userAccount.id = :id")
	Developer findDeveloperById(int id);

	@Query("SELECT count(t) FROM TrainingModule t WHERE t.developer.userAccount.id = :id and t.updateMoment IS NOT NULL")
	int totalTrainingModulesWithUpdateMoment(int id);

	@Query("SELECT count(ts) FROM TrainingSession ts WHERE (ts.link IS NOT NULL and ts.link IS NOT '') and ts.trainingModule.developer.userAccount.id = :id")
	int totalTrainingSessionsWithLink(int id);

	@Query("SELECT avg(t.totalTime) FROM TrainingModule t WHERE t.developer.userAccount.id = :id")
	Double findAverageTrainingModuleTime(int id);

	@Query("SELECT stddev(t.totalTime) FROM TrainingModule t WHERE t.developer.userAccount.id = :id")
	Double findDeviationTrainingModuleTime(int id);

	@Query("SELECT max(t.totalTime) FROM TrainingModule t WHERE t.developer.userAccount.id = :id")
	Double findMaximumTrainingModuleTime(int id);

	@Query("SELECT min(t.totalTime) FROM TrainingModule t WHERE t.developer.userAccount.id = :id")
	Double findMinimumTrainingModuleTime(int id);

}
