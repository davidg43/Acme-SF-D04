
package acme.features.client.progresslog;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.contract.Contract;
import acme.entities.contract.ProgressLog;
import acme.roles.Client;

@Repository
public interface ClientProgressLogRepository extends AbstractRepository {

	@Query("select pl from ProgressLog pl where pl.id = :id")
	ProgressLog findOneProgressLogById(int id);

	@Query("select c from Contract c where c.id = :id")
	Contract findOneContractById(int id);

	@Query("select pl.contract from ProgressLog pl where pl.id = :id")
	Contract findOneContractByProgressLogId(int id);

	@Query("select pl from ProgressLog pl where pl.contract.id = :contractId")
	Collection<ProgressLog> findManyProgressLogByContractId(int contractId);

	@Query("select pl from ProgressLog pl where pl.recordId = :code")
	ProgressLog findOneProgressLogByCode(String code);

	@Query("select count(pl) from ProgressLog pl where pl.recordId = :code")
	int findNumberProgressLogsByCode(String code);

	@Query("SELECT SUM(p.completeness) FROM ProgressLog p WHERE p.contract.id = :id AND p.id <> :ownId")
	Double findTotalCompletenessByContractIdExceptSelf(int id, int ownId);

	@Query("SELECT SUM(p.completeness) FROM ProgressLog p WHERE p.contract.code =:code")
	Double findTotalCompletenessByContractCode(String code);

	@Query("select pl from ProgressLog pl where pl.contract.client.id =:masterId")
	Collection<ProgressLog> findAllProgressOfClient(int masterId);
	@Query("select p from ProgressLog p WHERE p.contract.id =:id")
	Collection<ProgressLog> findAllProgressLogsByContractId(int id);

	@Query("select p.contract.client from ProgressLog p where p.id =:id")
	Client findClientByProgressLogId(int id);

	@Query("select p from ProgressLog p")
	Collection<ProgressLog> findAllProgressLogs();

	@Query("select c from Contract c")

	Collection<Contract> findAllContracts();

	@Query("select c from Contract c where c.client.id =:activeRoleId")
	Collection<Contract> findAllContractsByClientId(int activeRoleId);

	@Query("select c from Contract c where c.code =:code")
	Contract findOneContractByCode(String code);

	@Query("SELECT p FROM ProgressLog p WHERE p.contract.id =:id ORDER BY p.registrationMoment DESC ")
	List<ProgressLog> findBefore(int id);

}
