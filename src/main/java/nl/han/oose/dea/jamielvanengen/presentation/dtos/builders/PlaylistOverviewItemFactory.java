package nl.han.oose.dea.jamielvanengen.presentation.dtos.builders;

import nl.han.oose.dea.jamielvanengen.domain.Playlist;
import nl.han.oose.dea.jamielvanengen.presentation.dtos.PlaylistOverviewItem;

import javax.enterprise.inject.Default;
import java.util.ArrayList;
import java.util.List;

@Default
public class PlaylistOverviewItemFactory {
    public List<PlaylistOverviewItem> getPlaylistOverviewsFromPlaylists(List<Playlist> playlists, int currentUserId) {
        List<PlaylistOverviewItem> playlistOverviewItems = new ArrayList<>();

        playlists.stream().forEach(playlist -> playlistOverviewItems.add(new PlaylistOverviewItem(
                playlist.getId(),
                playlist.getName(),
                playlist.getUserid() == currentUserId,
                new ArrayList<>()
        )));
        return playlistOverviewItems;
    }
}
