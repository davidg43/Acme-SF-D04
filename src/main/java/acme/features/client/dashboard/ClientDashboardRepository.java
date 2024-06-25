
package acme.features.client.dashboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;

@Repository
public interface ClientDashboardRepository extends AbstractRepository {

	@Query("select count(pl) from ProgressLog pl where pl.completeness < 25 and pl.contract.client.id =:id")
	Integer totalNumberProgressLogsRateLess25(int id);

	@Query("select count(pl) from ProgressLog pl where pl.completeness between 25 and 50 and pl.contract.client.id =:id")
	Integer totalNumberProgressLogsRateBetween25And50(int id);

	@Query("select count(pl) from ProgressLog pl where pl.completeness between 50 and 75 and pl.contract.client.id =:id")
	Integer totalNumberProgressLogsRateBetween50And75(int id);

	@Query("select count(pl) from ProgressLog pl where pl.completeness > 75 and pl.contract.client.id =:id")
	Integer totalNumberProgressLogsRateMoreThan75(int id);

	@Query("select avg(c.budget.amount) from Contract c where c.client.id =:id")
	Double averageBudgetOfContracts(int id);

	@Query("select stddev(c.budget.amount) from Contract c where c.client.id =:id")
	Double deviationBudgetOfContracts(int id);

	@Query("select min(c.budget.amount) from Contract c where c.client.id =:id")
	Double minimumBudgetOfContracts(int id);

	@Query("select max(c.budget.amount) from Contract c where c.client.id =:id")
	Double maximumBudgetOfContracts(int id);

}
