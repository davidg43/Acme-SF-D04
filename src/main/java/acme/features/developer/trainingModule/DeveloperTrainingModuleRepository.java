
package acme.features.developer.trainingModule;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.project.Project;
import acme.entities.trainingModule.TrainingModule;
import acme.entities.trainingModule.TrainingSession;
import acme.roles.Developer;

@Repository
public interface DeveloperTrainingModuleRepository extends AbstractRepository {

	@Query("SELECT t FROM TrainingModule t WHERE t.id = :id")
	TrainingModule findOneTrainingModuleById(int id);

	@Query("SELECT t FROM TrainingModule t WHERE t.developer.id = :id")
	Collection<TrainingModule> findManyTrainingModuleByDeveloperId(int id);

	@Query("SELECT DISTINCT s.trainingModule FROM TrainingSession s WHERE s.trainingModule.developer.id = :id GROUP BY s.trainingModule HAVING COUNT(s) > 0")
	Collection<TrainingModule> findManyTrainingModuleByDeveloperIdWithSessions(int id);

	@Query("SELECT t FROM TrainingModule t")
	Collection<TrainingModule> findAllTrainingModules();

	@Query("SELECT d FROM Developer d WHERE d.id = :id")
	Developer findOneDeveloperById(int id);

	@Query("SELECT p FROM Project p WHERE p.id = :id")
	Project findOneProjectById(int id);

	@Query("SELECT p FROM Project p WHERE p.id = :id and p.isDraft = true")
	Project findOneProjectByIdPublished(int id);

	@Query("SELECT p FROM Project p")
	Collection<Project> findAllProjects();

	@Query("SELECT p FROM Project p WHERE p.isDraft = false")
	Collection<Project> findAllProjectsPublished();

	@Query("SELECT t.project FROM TrainingModule t where t.developer.id = :developerId")
	Collection<Project> findManyProjectsByDeveloperId(int developerId);

	@Query("SELECT s FROM TrainingSession s WHERE s.trainingModule.id = :id")
	Collection<TrainingSession> findManyTrainingSessionsByTrainingModuleId(int id);

	@Query("SELECT t FROM TrainingModule t WHERE t.code = :code")
	TrainingModule findOneTrainingModuleByCode(String code);

}
