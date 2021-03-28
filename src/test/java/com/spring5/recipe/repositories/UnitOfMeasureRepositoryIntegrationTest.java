package com.spring5.recipe.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.spring5.recipe.domain.UnitOfMeasure;
import java.util.Optional;

@DataJpaTest
class UnitOfMeasureRepositoryIntegrationTest {

	@Autowired
	UnitOfMeasureRepository unitOfMeasureRepository;

	@BeforeEach
	public void setUp() throws Exception {

	}

	@Test
	// @DirtiesContext reloads context after done. Used when method changes data in
	// the context
	void testFindByDescription() {
		Optional<UnitOfMeasure> uomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
		assertEquals("Teaspoon", uomOptional.get().getDescription());
	}

}
