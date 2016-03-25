package grok.citysearch.loaders;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import grok.citysearch.commodity.Commodity;
import grok.citysearch.commodity.CommodityService;
import grok.citysearch.security.ApplicationUser;
import grok.citysearch.security.ApplicationUserService;
import grok.citysearch.user.User;
import grok.citysearch.user.UserService;

@Component
@Transactional
public class UserLoaderImpl implements Loader, UserLoader{

	@Autowired
	private UserService userService;
	@Autowired
	private ApplicationUserService applicationUserService;
	@Autowired
	private CommodityService commodityService;
	
	private int numberOfCommodities;
	private int maxRank;
	
	private UserLoaderImpl(){
		
	}
	public UserLoaderImpl(int numberOfCommodities, int maxRank){
		this.numberOfCommodities = numberOfCommodities;
		this.maxRank = maxRank;
	}
	@Override
	public void populate() {
		List<Commodity> commodities = commodityService.find();
		Random random = new Random();
		for(ApplicationUser applicationUser:applicationUserService.find()){
			User user = userService.get(applicationUser.getUsername());
			user.setRank(random.nextInt(maxRank));
			for(int i = 0; i < numberOfCommodities; i++){
				Commodity commodity = commodities.get(random.nextInt(commodities.size()));
				user.receive(commodity);	
				System.out.println("gave user: commodity: "+ commodity.getName() );
				userService.update(user);
			}
		}
	}
}