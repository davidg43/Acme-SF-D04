
package acme.features.manager.project;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.entities.project.Project;
import acme.roles.Manager;

@Controller
public class ManagerProjectController extends AbstractController<Manager, Project> {

	@Autowired
	private ManagerProjectDeleteService		deleteService;

	@Autowired
	private ManagerProjectCreateService		createService;

	@Autowired
	private ManagerProjectUpdateService		updateService;

	@Autowired
	private ManagerProjectShowService		showService;

	@Autowired
	private ManagerProjectListService		listService;

	@Autowired
	private ManagerProjectPublishService	publishService;


	@PostConstruct
	protected void initialise() {

		super.addBasicCommand("create", this.createService);
		super.addBasicCommand("update", this.updateService);
		super.addBasicCommand("show", this.showService);
		super.addBasicCommand("list", this.listService);
		super.addBasicCommand("delete", this.deleteService);

		super.addCustomCommand("publish", "update", this.publishService);
	}
}
