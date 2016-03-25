package grok.citysearch.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	private UserRepository userRepository;
	
	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public User get(String username) {
		return userRepository.findOneByUsername(username);
	}
	
	public Integer getUserRank(String username) {
		User user = userRepository.findOneByUsername(username);
		return user.getRank();
	}
	
	public void update(User user){
		userRepository.save(user);
	}
}
