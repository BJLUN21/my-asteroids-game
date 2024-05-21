package dk.sdu.mmmi.cbse.scoringsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class ScoringSystem {

	private int totalScore = 0;

	public static void main(String[] args) {
		System.out.println("Booting server!");
		SpringApplication.run(ScoringSystem.class, args);
	}

	@GetMapping("/score/add")
	public int addToScore(@RequestParam(value = "points") int points) {
		totalScore += points;
		return totalScore;
	}

	@PutMapping("/score/set/{score}")
	public int setScore(@PathVariable(value = "score") int score) {
		totalScore = score;
		return totalScore;
	}

	@GetMapping("/score/get")
	public int getScore() {
		return totalScore;
	}

}
