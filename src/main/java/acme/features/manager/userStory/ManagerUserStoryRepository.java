
package acme.features.manager.userStory;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.project.Assignment;
import acme.entities.project.UserStory;
import acme.roles.Manager;

@Repository
public interface ManagerUserStoryRepository extends AbstractRepository {

	@Query("select us from UserStory us where us.id =:id")
	UserStory findUserStoryById(int id);

	@Query("select us.manager from UserStory us where us.id =:id")
	Manager findManagerByUserStoryId(int id);

	@Query("select us from UserStory us where us.manager.id =:id")
	Collection<UserStory> findUserStoriesOfAManagerById(int id);

	@Query("select distinct a from Assignment a where a.userStory.id =:id")
	Collection<Assignment> findAllAssignmentOfAnUserStoryById(int id);

}
