package com.fon.footballfantasy.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fon.footballfantasy.domain.Club;
import com.fon.footballfantasy.parser.ClubPageHtmlParser;
import com.fon.footballfantasy.repository.BaseRepositoryTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class ClubServiceTest extends BaseRepositoryTest {

	@Autowired
	ClubService clubService;

	@Autowired
	ClubPageHtmlParser parser;

	@Test
	void testSave() throws IOException {

		log.info("Saving single club with players");

		Club club = parser.parse("/69eacba4/TSC-Backa-Top");
		club = clubService.save(club);

		assertNotNull(club);
		assertNotNull(club.getPlayers());
		assertEquals(29, club.getPlayers().size());
		
	}

	@Test
	void testParseSeasonClubs() {

		log.info("Saving season clubs");

		clubService.parseSeasonClubs();
		List<Club> clubs = clubService.findAll();

		assertEquals(16, clubs.size());

	}

}
