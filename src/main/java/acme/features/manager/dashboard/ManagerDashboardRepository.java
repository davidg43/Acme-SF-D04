
package acme.features.manager.dashboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;

@Repository
public interface ManagerDashboardRepository extends AbstractRepository {

	@Query("select avg(us.estimatedCost) from UserStory us WHERE us.manager.id =:id")
	Double avgEstimatedCost(Integer id);

	@Query("select stddev(us.estimatedCost) from UserStory us WHERE us.manager.id =:id")
	Double deviationEstimatedCost(Integer id);

	@Query("select max(us.estimatedCost) from UserStory us WHERE us.manager.id =:id")
	Integer maxEstimatedCost(Integer id);

	@Query("select min(us.estimatedCost) from UserStory us WHERE us.manager.id =:id")
	Integer minEstimatedCost(Integer id);

	@Query("select count(us) from UserStory us where us.priority = '3' AND us.manager.id =:id")
	Integer countMust(Integer id);

	@Query("select count(us) from UserStory us where us.priority = '2' AND us.manager.id =:id")
	Integer countShould(Integer id);

	@Query("select count(us) from UserStory us where us.priority = '1' AND us.manager.id =:id")
	Integer countCould(Integer id);

	@Query("select count(us) from UserStory us where us.priority = '0' AND us.manager.id =:id")
	Integer countWont(Integer id);

	@Query("select avg(p.cost.amount) from Project p WHERE p.manager.id =:id AND p.cost.currency = 'EUR'")
	Double avgProjectCostEUR(Integer id);

	@Query("select stddev(p.cost.amount) from Project p WHERE p.manager.id =:id AND p.cost.currency = 'EUR'")
	Double deviationProjectCostEUR(Integer id);

	@Query("select max(p.cost.amount) from Project p WHERE p.manager.id =:id AND p.cost.currency = 'EUR'")
	Double maxProjectCostEUR(Integer id);

	@Query("select min(p.cost.amount) from Project p WHERE p.manager.id =:id AND p.cost.currency = 'EUR'")
	Double minProjectCostEUR(Integer id);

	@Query("select avg(p.cost.amount) from Project p WHERE p.manager.id =:id AND p.cost.currency = 'GBP'")
	Double avgProjectCostGBP(Integer id);

	@Query("select stddev(p.cost.amount) from Project p WHERE p.manager.id =:id AND p.cost.currency = 'GBP'")
	Double deviationProjectCostGBP(Integer id);

	@Query("select max(p.cost.amount) from Project p WHERE p.manager.id =:id AND p.cost.currency = 'GBP'")
	Double maxProjectCostGBP(Integer id);

	@Query("select min(p.cost.amount) from Project p WHERE p.manager.id =:id AND p.cost.currency = 'GBP'")
	Double minProjectCostGBP(Integer id);

	@Query("select avg(p.cost.amount) from Project p WHERE p.manager.id =:id AND p.cost.currency = 'USD'")
	Double avgProjectCostUSD(Integer id);

	@Query("select stddev(p.cost.amount) from Project p WHERE p.manager.id =:id AND p.cost.currency = 'USD'")
	Double deviationProjectCostUSD(Integer id);

	@Query("select max(p.cost.amount) from Project p WHERE p.manager.id =:id AND p.cost.currency = 'USD'")
	Double maxProjectCostUSD(Integer id);

	@Query("select min(p.cost.amount) from Project p WHERE p.manager.id =:id AND p.cost.currency = 'USD'")
	Double minProjectCostUSD(Integer id);

}
