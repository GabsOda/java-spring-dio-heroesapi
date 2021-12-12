package com.example.heroesapi;

import com.example.heroesapi.repository.HeroesRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import static com.example.heroesapi.constans.HeroesConstant.HEROES_ENDPOINT_LOCAL;

@RunWith(SpringRunner.class)
@DirtiesContext
@AutoConfigureWebTestClient
@SpringBootTest
class HeroesapiApplicationTests {

	@Autowired
	private WebTestClient webTestClient;

	@Autowired
	private HeroesRepository heroesRepository;

	@Test
	public void getOneHeroById() {
		webTestClient.get().uri(HEROES_ENDPOINT_LOCAL.concat("/{id}"), "1")
				.exchange()
				.expectStatus().isOk()
				.expectBody();
	}

	@Test
	public void notGetOneHeroById() {
		webTestClient.get().uri(HEROES_ENDPOINT_LOCAL.concat("/{id}"), "10")
				.exchange()
				.expectStatus().isNotFound();
	}

	@Test
	public void notDeleteHero() {
		webTestClient.delete().uri(HEROES_ENDPOINT_LOCAL.concat("/{id}"), "10")
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isNotFound()
				.expectBody(Void.class);
	}

}
