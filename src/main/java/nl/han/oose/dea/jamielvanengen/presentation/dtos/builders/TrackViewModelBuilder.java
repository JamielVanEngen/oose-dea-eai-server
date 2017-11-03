package nl.han.oose.dea.jamielvanengen.presentation.dtos.builders;

import nl.han.oose.dea.jamielvanengen.domain.track.Track;
import nl.han.oose.dea.jamielvanengen.domain.track.impl.Song;
import nl.han.oose.dea.jamielvanengen.domain.track.impl.Video;
import nl.han.oose.dea.jamielvanengen.presentation.dtos.TrackViewModel;

import javax.enterprise.inject.Default;
import java.util.ArrayList;
import java.util.List;

@Default
public class TrackViewModelBuilder {
    public List<TrackViewModel> buildTrackViewModelsFromTracks(List<Track> tracks) {
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
                video.getPublicatiedatum(),
                video.getBeschrijving(),
                video.isAvailableOffline()
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
                null,
                song.isAvailableOffline()
        );
    }
}
