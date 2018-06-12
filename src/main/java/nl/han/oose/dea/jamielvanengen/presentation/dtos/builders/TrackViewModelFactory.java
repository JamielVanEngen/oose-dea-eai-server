package nl.han.oose.dea.jamielvanengen.presentation.dtos.builders;

import nl.han.oose.dea.jamielvanengen.domain.track.Track;
import nl.han.oose.dea.jamielvanengen.domain.track.Track;
import nl.han.oose.dea.jamielvanengen.domain.track.impl.Song;
import nl.han.oose.dea.jamielvanengen.domain.track.impl.Video;
import nl.han.oose.dea.jamielvanengen.presentation.dtos.TrackViewModel;
import nl.han.oose.dea.jamielvanengen.presentation.dtos.TrackViewModel;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TrackViewModelFactory {
    public List<TrackViewModel> getTrackViewModelsFromTracks(List<Track> tracks) {
        ArrayList<TrackViewModel> trackViewModels = new ArrayList<>();

        for (Track track : tracks) {
            if (track.getClass() == Video.class) {
                trackViewModels.add(getTrackViewModelFromVideo((Video)track));
            }
            else {
                trackViewModels.add(getTrackViewModelFromSong((Song)track));
            }
        }
        return trackViewModels;
    }

    private TrackViewModel getTrackViewModelFromVideo(Video video) {
        return new TrackViewModel(
                video.getId(),
                video.getTitel(),
                video.getPerformer(),
                video.getAfspeelduur(),
                null,
                video.getPlaycount(),
                video.getPublicatiedatum().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                video.getBeschrijving()
        );
    }

    private TrackViewModel getTrackViewModelFromSong(Song song) {
        return new TrackViewModel(
                song.getId(),
                song.getTitel(),
                song.getPerformer(),
                song.getAfspeelduur(),
                song.getAlbum(),
                song.getPlaycount(),
                null,
                null
        );
    }
}
