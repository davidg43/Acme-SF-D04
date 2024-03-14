
package acme.features.manager.assigment;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.entities.project.Assigment;
import acme.roles.Manager;

@Controller
public class ManagerAssigmentController extends AbstractController<Manager, Assigment> {

	@Autowired
	private ManagerAssigmentShowService	assigmentShow;

	@Autowired
	private ManagerAssigmentListService	assigmentList;


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand("show", this.assigmentShow);
		super.addBasicCommand("list", this.assigmentList);

	}

}
