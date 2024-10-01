
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

	@Query("select avg(c.budget.amount) from Contract c where c.client.id =:id and c.budget.currency = 'EUR'")
	Double averageBudgetOfContractsEUR(int id);

	@Query("select stddev(c.budget.amount) from Contract c where c.client.id =:id and c.budget.currency = 'EUR'")
	Double deviationBudgetOfContractsEUR(int id);

	@Query("select min(c.budget.amount) from Contract c where c.client.id =:id and c.budget.currency = 'EUR'")
	Double minimumBudgetOfContractsEUR(int id);

	@Query("select max(c.budget.amount) from Contract c where c.client.id =:id and c.budget.currency = 'EUR'")
	Double maximumBudgetOfContractsEUR(int id);

	@Query("select avg(c.budget.amount) from Contract c where c.client.id =:id and c.budget.currency = 'GBP'")
	Double averageBudgetOfContractsGBP(int id);

	@Query("select stddev(c.budget.amount) from Contract c where c.client.id =:id and c.budget.currency = 'GBP'")
	Double deviationBudgetOfContractsGBP(int id);

	@Query("select min(c.budget.amount) from Contract c where c.client.id =:id and c.budget.currency = 'GBP'")
	Double minimumBudgetOfContractsGBP(int id);

	@Query("select max(c.budget.amount) from Contract c where c.client.id =:id and c.budget.currency = 'GBP'")
	Double maximumBudgetOfContractsGBP(int id);

	@Query("select avg(c.budget.amount) from Contract c where c.client.id =:id and c.budget.currency = 'USD'")
	Double averageBudgetOfContractsUSD(int id);

	@Query("select stddev(c.budget.amount) from Contract c where c.client.id =:id and c.budget.currency = 'USD'")
	Double deviationBudgetOfContractsUSD(int id);

	@Query("select min(c.budget.amount) from Contract c where c.client.id =:id and c.budget.currency = 'USD'")
	Double minimumBudgetOfContractsUSD(int id);

	@Query("select max(c.budget.amount) from Contract c where c.client.id =:id and c.budget.currency = 'USD'")
	Double maximumBudgetOfContractsUSD(int id);

}
