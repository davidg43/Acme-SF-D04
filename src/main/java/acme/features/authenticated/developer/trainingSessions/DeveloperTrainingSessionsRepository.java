
package acme.features.authenticated.developer.trainingSessions;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.trainingModule.TrainingModule;
import acme.entities.trainingModule.TrainingSession;
import acme.roles.Developer;

@Repository
public interface DeveloperTrainingSessionsRepository extends AbstractRepository {

	@Query("SELECT t FROM TrainingSession t WHERE t.id = :id")
	TrainingSession findOneTrainingSessionById(int id);

	@Query("SELECT s FROM TrainingSession s WHERE s.trainingModule.id = :id")
	Collection<TrainingSession> findManyTrainingSessionsByTrainingModuleId(int id);

	@Query("SELECT d FROM Developer d WHERE d.id = :id")
	Developer findOneDeveloperById(int id);

	@Query("SELECT t FROM TrainingModule t WHERE t.id = :id")
	TrainingModule findOneTrainingModuleById(int id);

	@Query("SELECT t FROM TrainingModule t")
	Collection<TrainingModule> findAllTrainingModules();

	@Query("SELECT t FROM TrainingSession t")
	Collection<TrainingSession> findAllTrainingSessions();

}
