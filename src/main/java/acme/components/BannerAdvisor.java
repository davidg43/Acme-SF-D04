
package acme.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import acme.entities.Banner;

@ControllerAdvice
public class BannerAdvisor {

	@Autowired
	protected BannerRepository repo;


	@ModelAttribute("banner")
	public Banner getRandomBanner() {
		Banner result;
		try {
			result = this.repo.findRandomBanner();
		} catch (final Throwable oops) {
			result = null;
		}
		return result;
	}

}
