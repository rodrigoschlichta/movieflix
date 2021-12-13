package com.rschlichta.MovieFlix.web;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;



@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class GenreResourceIt {

	@Autowired
	private MockMvc mockMvc;

	@Value("${security.oauth2.client.client-id}")
	private String clientId;

	@Value("${security.oauth2.client.client-secret}")
	private String clientSecret;	
	
	private String visitorUsername;
	private String visitorPassword;
	private String memberUsername;
	private String memberPassword;
	
	@BeforeEach
	void setUp() throws Exception {
		
		visitorUsername = "bob@gmail.com";
		visitorPassword = "123456";
		memberUsername = "ana@gmail.com";
		memberPassword = "123456";
	}

	@Test
	public void findAllShouldReturnUnauthorizedWhenNotValidToken() throws Exception {

		ResultActions result =
				mockMvc.perform(get("/genres")
					.contentType(MediaType.APPLICATION_JSON));

		result.andExpect(status().isUnauthorized());
	}
	
	@Test
	public void findAllShouldReturnAllGenresWhenVisitorAuthenticated() throws Exception {

		String accessToken = obtainAccessToken(visitorUsername, visitorPassword);
				
		ResultActions result =
				mockMvc.perform(get("/genres")
					.header("Authorization", "Bearer " + accessToken)
					.contentType(MediaType.APPLICATION_JSON));

		result.andExpect(status().isOk());

	}
	
	@Test
	public void findAllShouldReturnAllGenresWhenMemberAuthenticated() throws Exception {

		String accessToken = obtainAccessToken(memberUsername, memberPassword);
	
		ResultActions result =
				mockMvc.perform(get("/genres")
					.header("Authorization", "Bearer " + accessToken)
					.contentType(MediaType.APPLICATION_JSON));

		result.andExpect(status().isOk());
	}

	private String obtainAccessToken(String username, String password) throws Exception {

		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "password");
		params.add("client_id", clientId);
		params.add("username", username);
		params.add("password", password);

		ResultActions result = mockMvc
				.perform(post("/oauth/token").params(params).with(httpBasic(clientId, clientSecret))
						.accept("application/json;charset=UTF-8"))
				.andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8"));

		String resultString = result.andReturn().getResponse().getContentAsString();

		JacksonJsonParser jsonParser = new JacksonJsonParser();
		return jsonParser.parseMap(resultString).get("access_token").toString();
	}	
}