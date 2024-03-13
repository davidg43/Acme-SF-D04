
package acme.features.manager.project;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.entities.project.Project;
import acme.roles.Manager;

@Controller
public class ManagerProjectController extends AbstractController<Manager, Project> {

	/*
	 * @Autowired
	 * private ManagerProjectCreateService createService;
	 * 
	 * @Autowired
	 * private ManagerProjectUpdateService updateService;
	 * 
	 * @Autowired
	 * private ManagerProjectDeleteService deleteService;
	 */

	@Autowired
	private ManagerProjectShowService	showService;

	@Autowired
	private ManagerProjectListService	listService;


	@PostConstruct
	protected void initialise() {
		/*
		 * super.addBasicCommand("create", this.createService);
		 * super.addBasicCommand("update", this.updateService);
		 * super.addBasicCommand("delete", this.deleteService);
		 * 
		 */
		super.addBasicCommand("show", this.showService);
		super.addBasicCommand("list", this.listService);
	}
}
