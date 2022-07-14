package com.example.proyectoIntegrador;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(
		classes = ProyectoIntegradorApplication.class,
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ImportAutoConfiguration
class ProyectoIntegradorApplicationTests {

	@Test
	void contextLoads() {
	}

}
