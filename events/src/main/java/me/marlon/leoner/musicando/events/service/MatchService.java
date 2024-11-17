package me.marlon.leoner.musicando.events.service;

import lombok.RequiredArgsConstructor;
import me.marlon.leoner.musicando.events.domain.game.Match;
import me.marlon.leoner.musicando.events.domain.game.MatchStateEnum;
import me.marlon.leoner.musicando.events.repository.MatchRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MatchService {

    private final MatchRepository repository;

    public List<Match> getAllMatchesFromGame(String gameId) {
        return repository.findAllByGameId(gameId);
    }

    public Match getMatchOrNull(String gameId) {
        return findMatchById(gameId).orElse(null);
    }

    public Optional<Match> findMatchById(String matchId) {
        return repository.findById(matchId);
    }

    private Match save(Match match) {
        return repository.save(match);
    }

    private Match createAndSave(String gameId) {
        Match match = new Match(gameId);

        return save(match);
    }

    public Match onCreate(String gameId) {
        return createAndSave(gameId);
    }

    public Match onReconnect(String gameId, String matchId) {
        Optional<Match> match = findMatchById(matchId);
        return match.orElseGet(() -> createAndSave(gameId));
    }

    public void onDisconnect(Match match) {
        match.setState(MatchStateEnum.PAUSED);

        save(match);
    }

    public void onNumberOfRoundsChange(Match match, Integer numberOfRounds) {
        match.setNumberOfSongs(numberOfRounds);

        save(match);
    }

    public void onRoundDurationChange(Match match, Integer roundDuration) {
        match.setRoundDuration(roundDuration);

        save(match);
    }

    public void onPlaylistChange(Match match, String playlistId) {
        match.setPlaylistId(playlistId);

        save(match);
    }

    public void onStart(Match match, List<String> rounds) {
        match.setState(MatchStateEnum.LIVE);
        match.setRounds(rounds);
        match.setRoundNumber(0);
        match.setStartedAt(new Date());

        save(match);
    }

    public void onPreLive(Match match) {
        match.incrementRoundNumber();

        save(match);
    }

    public void onFinish(Match match) {
        match.setState(MatchStateEnum.FINISHED);
        match.setFinishedAt(new Date());

        save(match);
    }

    public Match onReset(Match match, String gameId) {
        match.setState(MatchStateEnum.PREPARING);

        save(match);

        return save(new Match(gameId));
    }
}
