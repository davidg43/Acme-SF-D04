
package acme.components;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.repository.Query;

import acme.client.helpers.MomentHelper;
import acme.client.helpers.RandomHelper;
import acme.client.repositories.AbstractRepository;
import acme.entities.Banner;

public interface BannerRepository extends AbstractRepository {

	@Query("SELECT count(b) FROM Banner b WHERE :currentDate BETWEEN b.periodInit AND b.periodEnd")
	int countBanners(Date currentDate);

	@Query("SELECT b from Banner b where :currentDate BETWEEN b.periodInit AND b.periodEnd")
	List<Banner> findManyBanners(PageRequest pageRequest, Date currentDate);

	default Banner findRandomBanner() {
		Banner result;
		int count, index;
		PageRequest page;
		List<Banner> list;

		Date now = MomentHelper.getCurrentMoment();
		count = this.countBanners(now);
		if (count == 0)
			result = null;
		else {
			index = RandomHelper.nextInt(0, count);

			page = PageRequest.of(index, 1, Sort.by(Direction.ASC, "id"));
			list = this.findManyBanners(page, now);
			//list = this.findAllActiveBanners(page);
			result = list.isEmpty() ? null : list.get(0);

		}
		return result;
	}

}
