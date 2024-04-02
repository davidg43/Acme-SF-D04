
package acme.features.manager.userStory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.project.UserStory;
import acme.roles.Manager;

@Service
public class ManagerUserStoryShowService extends AbstractService<Manager, UserStory> {

	@Autowired
	private ManagerUserStoryRepository uSRepository;


	@Override
	public void authorise() {
		boolean status;
		UserStory userStory;
		int masterId;
		Manager manager;

		masterId = super.getRequest().getData("id", int.class);
		userStory = this.uSRepository.findUserStoryById(masterId);
		manager = this.uSRepository.findManagerByUserStoryId(masterId);

		status = super.getRequest().getPrincipal().hasRole(manager) || userStory != null;

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		int id;

		id = super.getRequest().getData("id", int.class);
		UserStory userStory = this.uSRepository.findUserStoryById(id);

		super.getBuffer().addData(userStory);
	}

	@Override
	public void unbind(final UserStory userStory) {
		assert userStory != null;

		Dataset dataset;

		dataset = super.unbind(userStory, "title", "description", "estimatedCost", "priority", "acceptanceCriteria", "link", "isDraft");

		super.getResponse().addData(dataset);
	}

}
