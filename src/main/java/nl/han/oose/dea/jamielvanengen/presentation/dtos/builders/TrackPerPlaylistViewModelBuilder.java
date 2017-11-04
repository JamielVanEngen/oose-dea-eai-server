package nl.han.oose.dea.jamielvanengen.presentation.dtos.builders;

import nl.han.oose.dea.jamielvanengen.domain.track.Track;
import nl.han.oose.dea.jamielvanengen.domain.track.TrackPerPlaylist;
import nl.han.oose.dea.jamielvanengen.domain.track.impl.Song;
import nl.han.oose.dea.jamielvanengen.domain.track.impl.Video;
import nl.han.oose.dea.jamielvanengen.presentation.dtos.PlaylistTrack;

import javax.enterprise.inject.Default;
import java.util.ArrayList;
import java.util.List;

@Default
public class TrackPerPlaylistViewModelBuilder {
    public List<PlaylistTrack> buildTrackPerPlaylistViewModelsFromTrackPerPlaylists(List<TrackPerPlaylist> trackPerPlaylists) {
        ArrayList<PlaylistTrack> playlistTracks = new ArrayList<>();

        for (TrackPerPlaylist trackPerPlaylist : trackPerPlaylists) {
            Track track = trackPerPlaylist.getTrack();
            if (track.getClass() == Video.class) {
                playlistTracks.add(getTrackPerPlaylistViewModelFromVideo((Video)track, trackPerPlaylist.isAvailableOffline()));
            }
            else {
                playlistTracks.add(getTrackPerPlaylistViewModelFromSong((Song)track, trackPerPlaylist.isAvailableOffline()));
            }
        }
        return playlistTracks;
    }

    private PlaylistTrack getTrackPerPlaylistViewModelFromVideo(Video video, boolean isAvailableOffline) {
        return new PlaylistTrack(
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

    private PlaylistTrack getTrackPerPlaylistViewModelFromSong(Song song, boolean isAvailableOffline) {
        return new PlaylistTrack(
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
