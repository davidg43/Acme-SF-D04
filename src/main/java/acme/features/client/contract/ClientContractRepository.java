
package acme.features.client.contract;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.contract.Contract;
import acme.entities.contract.ProgressLog;
import acme.entities.project.Project;
import acme.roles.Client;

@Repository
public interface ClientContractRepository extends AbstractRepository {

	@Query("select c from Contract c where c.client.id =:id")
	Collection<Contract> findContractsOfAClientById(int id);
	//
	//	@Query("select p from Project p where p.id =:id")
	//	Project findProjectById(int id);
	//
	//	@Query("select p.manager from Project p where p.id =:id")
	//	Manager findManagerByProjectId(int id);
	@Query("select c.client from Contract c where c.id =:id")
	Client findClientByContractId(int id);
	//
	//	@Query("select m from Manager m where m.id =:id")
	//	Manager findManagerByManagerId(int id);
	//
	//	@Query("Select Distinct a.userStory From Assignment a WHERE a.project.id =:id")
	//	Collection<UserStory> findAllUserStoriesOfAProjectById(int id);
	//
	//	@Query("Select Distinct a.userStory From Assignment a WHERE a.userStory.manager.id =:id")
	//	Collection<UserStory> findAllUserStoriesOfAManagerById(int id);
	//
	//	@Query("Select Distinct a From Assignment a WHERE a.project.id =:id")
	//	Collection<Assignment> findAllAssignmentsOfAProjectById(int id);
	//
	//	@Query("select p From Project p WHERE p.manager.id =:id")
	//	Collection<Project> findAllProjectsByManagerId(int id);
	//
	//	@Query("select a From Assignment a WHERE a.id =:id")
	//	Assignment findAssignmentById(int id);
	//
	//	@Query("select a.project.manager From Assignment a WHERE a.id =:id")
	//	Manager findProjectByAssignmentId(int id);
	//	//
	//	@Query("select ar From AuditRecord ar WHERE ar.codeAudit.project.id =:id")
	//	Collection<AuditRecord> findAllAuditRecordsOfAProjectById(int id);
	//
	//	@Query("select c From CodeAudit c WHERE c.project.id =:id")
	//	Collection<CodeAudit> findAllCodeAuditsOfAProjectById(int id);
	@Query("SELECT p FROM Project p")
	Collection<Project> findAllProjects();
	//	//
	@Query("select c From Contract c WHERE c.client.id =:id")
	Collection<Contract> findAllContractsOfAClientById(int id);
	@Query("select c From Contract c WHERE c.project.id =:projectId")
	Collection<Contract> findAllContractsOfAProjectById(int projectId);

	@Query("select c from Client c where c.id =:id")
	Client findClientByClientId(int id);

	@Query("select c from Contract c where c.id =:id")
	Contract findContractById(int id);
	//
	@Query("select p From ProgressLog p WHERE p.contract.id =:id")
	Collection<ProgressLog> findAllProgressLogsByContractId(int id);
	//	//
	//	@Query("select s From SponsorShip s WHERE s.project.id =:id")
	//	Collection<SponsorShip> findAllSponsorShipOfAProjectById(int id);
	//
	//	@Query("select t From TrainingModule t WHERE t.project.id =:id")
	//	Collection<TrainingModule> findAllTrainingModuleOfAProjectById(int id);
	//
	//	@Query("select t From TrainingSession t WHERE t.trainingModule.project.id =:id")
	//	Collection<TrainingSession> findAllTrainingSessionsOfAProjectById(int id);
	//	//
	//	@Query("select o From Objective o WHERE o.project.id =:id")
	//	Collection<Objective> findAllObjectivesOfAProjectById(int id);
	//	@Query("select p from Project p where p.manager.id =:id")
	//	Collection<Project> findProjectsOfAManagerById(int id);
	//
	//	@Query("select p from Project p where p.id =:id")
	//	Project findProjectById(int id);
	//
	//	@Query("select p.manager from Project p where p.id =:id")
	//	Manager findManagerByProjectId(int id);
	//
	//	@Query("select m from Manager m where m.id =:id")
	//	Manager findManagerByManagerId(int id);
	//
	//	@Query("Select Distinct a.userStory From Assignment a WHERE a.project.id =:id")
	//	Collection<UserStory> findAllUserStoriesOfAProjectById(int id);
	//
	//	@Query("Select Distinct a.userStory From Assignment a WHERE a.userStory.manager.id =:id")
	//	Collection<UserStory> findAllUserStoriesOfAManagerById(int id);
	//
	//	@Query("Select Distinct a From Assignment a WHERE a.project.id =:id")
	//	Collection<Assignment> findAllAssignmentsOfAProjectById(int id);
	//

	//
	//	@Query("select a From Assignment a WHERE a.id =:id")
	//	Assignment findAssignmentById(int id);
	//
	//	@Query("select a.project.manager From Assignment a WHERE a.id =:id")
	//	Manager findProjectByAssignmentId(int id);
	//	//
	//	@Query("select ar From AuditRecord ar WHERE ar.codeAudit.project.id =:id")
	//	Collection<AuditRecord> findAllAuditRecordsOfAProjectById(int id);
	//
	//	@Query("select c From CodeAudit c WHERE c.project.id =:id")
	//	Collection<CodeAudit> findAllCodeAuditsOfAProjectById(int id);
	//	//
	//	@Query("select c From Contract c WHERE c.project.id =:id")
	//	Collection<Contract> findAllContractOfAProjectById(int id);
	//
	//	@Query("select p From ProgressLog p WHERE p.contract.project.id =:id")
	//	Collection<ProgressLog> findAllProgressLogsByProjectId(int id);
	//	//
	//	@Query("select s From SponsorShip s WHERE s.project.id =:id")
	//	Collection<SponsorShip> findAllSponsorShipOfAProjectById(int id);
	//
	//	@Query("select t From TrainingModule t WHERE t.project.id =:id")
	//	Collection<TrainingModule> findAllTrainingModuleOfAProjectById(int id);
	//
	//	@Query("select t From TrainingSession t WHERE t.trainingModule.project.id =:id")
	//	Collection<TrainingSession> findAllTrainingSessionsOfAProjectById(int id);
	//	//
	//	@Query("select o From Objective o WHERE o.project.id =:id")
	//	Collection<Objective> findAllObjectivesOfAProjectById(int id);
	@Query("select c from Contract c")
	Collection<Contract> findAllContracts();

	@Query("select p from Project p where p.id =:projectId")
	Project findOneProjectById(int projectId);

	@Query("select c from Contract c where c.code =:code")
	Contract findOneContractByCode(String code);

}
