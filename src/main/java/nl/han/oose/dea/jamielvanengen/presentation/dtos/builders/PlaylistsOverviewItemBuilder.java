package nl.han.oose.dea.jamielvanengen.presentation.dtos.builders;

import nl.han.oose.dea.jamielvanengen.domain.Playlist;
import nl.han.oose.dea.jamielvanengen.presentation.dtos.PlaylistsOverviewItem;

import javax.enterprise.inject.Default;
import java.util.ArrayList;
import java.util.List;

@Default
public class PlaylistsOverviewItemBuilder {
    public List<PlaylistsOverviewItem> buildPlaylistOverviewsFromPlaylists(List<Playlist> playlists, int currentUserId) {
        List<PlaylistsOverviewItem> playlistsOverviewItems = new ArrayList<>();

        playlists.stream().forEach(playlist -> playlistsOverviewItems.add(new PlaylistsOverviewItem(
                playlist.getId(),
                playlist.getName(),
                playlist.getUserid() == currentUserId,
                new ArrayList<>()
        )));
        return playlistsOverviewItems;
    }
}
