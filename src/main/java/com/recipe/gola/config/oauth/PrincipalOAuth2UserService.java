package com.recipe.gola.config.oauth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.recipe.gola.config.auth.PrincipalDetails;
import com.recipe.gola.config.auth.UserAuth;
import com.recipe.gola.dto.UserDTO;
import com.recipe.gola.mapper.UserMapper;

@Service
public class PrincipalOAuth2UserService extends DefaultOAuth2UserService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		logger.info("구글로그인 : " + userRequest);
		OAuth2User oauth2User = super.loadUser(userRequest);
		
		String email = (String)oauth2User.getAttributes().get("email");
		
		UserDTO dto = new UserDTO();
		dto.setUserId(email);
		
		String rawPwd = "";
        String encPwd = bCryptPasswordEncoder.encode(rawPwd);
        dto.setUserPwd(encPwd);
        
		dto.setUserNickname((String)oauth2User.getAttributes().get("name"));
		dto.setUserEmail(email);
		dto.setUserAuth(UserAuth.USER);
		
		if(userMapper.findByUsername(email) == null) {
			userMapper.insertuser(dto);
		}
		
		return new PrincipalDetails(dto, oauth2User.getAttributes());
	}
}
