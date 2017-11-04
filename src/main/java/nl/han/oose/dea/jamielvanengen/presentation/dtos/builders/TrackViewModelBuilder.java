package nl.han.oose.dea.jamielvanengen.presentation.dtos.builders;

import nl.han.oose.dea.jamielvanengen.domain.track.Track;
import nl.han.oose.dea.jamielvanengen.domain.track.TrackPerPlaylist;
import nl.han.oose.dea.jamielvanengen.domain.track.impl.Song;
import nl.han.oose.dea.jamielvanengen.domain.track.impl.Video;
import nl.han.oose.dea.jamielvanengen.presentation.dtos.TrackViewModel;

import javax.enterprise.inject.Default;
import java.util.ArrayList;
import java.util.List;

@Default
public class TrackViewModelBuilder {
    public List<TrackViewModel> buildTrackViewModelsFromTrackPerPlaylists(List<TrackPerPlaylist> trackPerPlaylists) {
        ArrayList<TrackViewModel> trackViewModels = new ArrayList<>();

        for (TrackPerPlaylist trackPerPlaylist : trackPerPlaylists) {
            Track track = trackPerPlaylist.getTrack();
            if (track.getClass() == Video.class) {
                trackViewModels.add(getTrackViewModelFromVideo((Video)track, trackPerPlaylist.isAvailableOffline()));
            }
            else {
                trackViewModels.add(getTrackViewModelFromSong((Song)track, trackPerPlaylist.isAvailableOffline()));
            }
        }
        return trackViewModels;
    }

    private TrackViewModel getTrackViewModelFromVideo(Video video, boolean isAvailableOffline) {
        return new TrackViewModel(
                video.getId(),
                video.getTitel(),
                video.getPerformer(),
                video.getAfspeelduur(),
                null,
                video.getPlaycount(),
                video.getPublicatiedatum(),
                video.getBeschrijving(),
                isAvailableOffline
        );
    }

    private TrackViewModel getTrackViewModelFromSong(Song song, boolean isAvailableOffline) {
        return new TrackViewModel(
                song.getId(),
                song.getTitel(),
                song.getPerformer(),
                song.getAfspeelduur(),
                song.getAlbum(),
                song.getPlaycount(),
                null,
                null,
                isAvailableOffline
        );
    }
}
