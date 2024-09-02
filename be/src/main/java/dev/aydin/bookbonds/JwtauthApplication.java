package dev.aydin.bookbonds;

import dev.aydin.bookbonds.entities.*;
import dev.aydin.bookbonds.repositories.BookRepository;
import dev.aydin.bookbonds.repositories.ClubRepository;
import dev.aydin.bookbonds.repositories.JourneyRepository;
import dev.aydin.bookbonds.repositories.UserRepository;
import dev.aydin.bookbonds.services.BookService;
import dev.aydin.bookbonds.services.ClubMemberService;
import dev.aydin.bookbonds.services.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.util.*;

@SpringBootApplication
public class JwtauthApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BookRepository bookRepository;
@Autowired
	private BookService bookService;

@Autowired
private JourneyRepository journeyRepository;

	@Autowired
	private ClubRepository clubRepository;

	@Autowired
	private ClubService clubService;
	@Autowired
	private ClubMemberService clubMemberService;

	public static void main(String[] args) {
		SpringApplication.run(JwtauthApplication.class, args);
	}


	public void run(String... args){
		User adminAccount = userRepository.findByRole(Role.ADMIN);
		if(null == adminAccount){
			User user = new User();

			user.setUsername("admin");
			user.setFirstname("Max");
			user.setLastname("Mustermann");
			user.setEmail("mustermann@gmx.de");
			user.setRole(Role.ADMIN);
			user.setPassword(new BCryptPasswordEncoder().encode("admin"));
			userRepository.save(user);

			// Normaler Benutzer erstellen
			User user2 = new User();
			user2.setUsername("memo");
			user2.setFirstname("Mehmet");
			user2.setLastname("Demir");
			user2.setEmail("memo99@gmx.de");
			user2.setRole(Role.USER);
			user2.setPassword(new BCryptPasswordEncoder().encode("memo"));
			userRepository.save(user2);

			// Bücher erstellen
			Book book = new Book();
			book.setTitle("Das Buch");
			book.setAuthor("Aydin, Tarik");
			book.setNumberOfChapters(10);
			book.setNumberOfPages(10);
			book.setPublishDate(LocalDate.now());
			book.setIsbn10("1234");
			book.setIsbn13("1234");
			bookRepository.save(book);

			Book book1 = new Book();
			book1.setTitle("Das wandelnde Schloss");
			book1.setAuthor("Aydin, Tarik");
			book1.setNumberOfChapters(10);
			book1.setNumberOfPages(10);
			book1.setPublishDate(LocalDate.now());
			book1.setIsbn10("6778");
			book1.setIsbn13("5678");
			bookRepository.save(book1);

			Book book2 = new Book();
			book2.setTitle("Elden Ring");
			book2.setAuthor("Aydin, TarikMemo, Can");
			book2.setNumberOfChapters(10);
			book2.setNumberOfPages(10);
			book2.setPublishDate(LocalDate.now());
			book2.setIsbn10("5424");
			book2.setIsbn13("0101");
			bookRepository.save(book2);

			// Clubs erstellen
			Club club = new Club();
			club.setTitle("Sci-Fi Hub");
			club.setDescription("Willkommen in der Welt der Science-Fiction! Hier diskutieren wir die aufregendsten und visionärsten Geschichten aus der Welt von morgen und darüber hinaus.");
			club.setTheme("royal");
			ClubMember member = new ClubMember();
			member.setUser(user);
			member.setRole(ClubRole.HOST);
			member.setClub(club);
			club.getMembers().add(member);

			clubRepository.save(club);

			Club club2 = new Club();
			club2.setTitle("Literaturfreunde");
			club2.setDescription("Ein Club für alle, die sich für klassische und moderne Literatur begeistern. Hier tauschen wir uns über unsere Lieblingsbücher aus und entdecken neue Schätze der Literaturgeschichte.");
			club2.setTheme("classics");
			List<ClubMember> clubMembers = new ArrayList<>();

			ClubMember cm = new ClubMember();
			cm.setUser(user2);
			cm.setRole(ClubRole.HOST);
			cm.setClub(club2);
			clubMembers.add(cm);

			club2.getMembers().addAll(clubMembers);
			clubRepository.save(club2);



			// Journey 1 erstellen
			Journey journey1 = new Journey();
			journey1.setTitle("Die Zukunft im Blick");
			journey1.setDescription("Eine spannende Reise durch die größten Science-Fiction-Klassiker, die die Zukunft der Menschheit voraussagen.");
			journey1.setBook(book);
			journey1.setNumberOfChapters(book.getNumberOfChapters());


			// Kapitel für Journey 1 erstellen und hinzufügen
			for (int i = 1; i <= journey1.getNumberOfChapters(); i++) {
				Chapter chapter = new Chapter();
				chapter.setTitle("Chapter " + i);
				chapter.setChapterNumber(i);
				chapter.setClubId(club.getClubId());
				journey1.getChapters().add(chapter);
			}
			journeyRepository.save(journey1); // Journey und zugehörige Kapitel speichern
			club.getJourneys().add(journey1);


			// Journey 2 erstellen
			Journey journey2 = new Journey();
			journey2.setTitle("Reise durch die Welt der Klassiker");
			journey2.setDescription("In dieser Journey lesen wir uns durch die bedeutendsten Werke der Weltliteratur.");
			journey2.setBook(book1);
			journey2.setNumberOfChapters(book1.getNumberOfChapters());

			// Kapitel für Journey 2 erstellen und hinzufügen
			for (int i = 1; i <= journey2.getNumberOfChapters(); i++) {
				Chapter chapter = new Chapter();
				chapter.setTitle("Chapter " + i);
				chapter.setChapterNumber(i);
				chapter.setClubId(club2.getClubId());
				journey2.getChapters().add(chapter);
			}
			journeyRepository.save(journey2); // Journey und zugehörige Kapitel speichern
			club2.getJourneys().add(journey2);
			clubService.saveClub(club2);


			Journey journey3 = new Journey();
			journey3.setTitle("Dystopie des 20. Jahrhunderts");
			journey3.setDescription("In dieser Journey lesen wir uns durch einen dystopischen Roman des 20. Jahrhunderts, '1984' von George Orwell,. Wir analysieren die Vision des Autors und die dargestellt Gesellschaftsstrukturen.");
			journey3.setBook(book);
			journey3.setNumberOfChapters(book.getNumberOfChapters());


			// Kapitel für Journey 1 erstellen und hinzufügen
			for (int i = 1; i <= journey3.getNumberOfChapters(); i++) {
				Chapter chapter = new Chapter();
				chapter.setTitle("Chapter " + i);
				chapter.setChapterNumber(i);
				chapter.setClubId(club.getClubId());
				journey3.getChapters().add(chapter);
			}
			journeyRepository.save(journey3); // Journey und zugehörige Kapitel speichern
			club.getJourneys().add(journey3);
			clubRepository.save(club);

		}
	}
}
