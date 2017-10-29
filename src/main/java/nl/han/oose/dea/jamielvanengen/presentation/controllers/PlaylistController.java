package nl.han.oose.dea.jamielvanengen.presentation.controllers;

import nl.han.oose.dea.jamielvanengen.constants.HttpResponse;
import nl.han.oose.dea.jamielvanengen.domain.Playlist;
import nl.han.oose.dea.jamielvanengen.presentation.dtos.PlaylistOverview;
import nl.han.oose.dea.jamielvanengen.presentation.dtos.PlaylistsOverviewItem;
import nl.han.oose.dea.jamielvanengen.presentation.dtos.builders.PlaylistsOverviewItemBuilder;
import nl.han.oose.dea.jamielvanengen.services.PlaylistService;
import nl.han.oose.dea.jamielvanengen.services.TokenService;
import nl.han.oose.dea.jamielvanengen.services.TrackService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/playlists")
public class PlaylistController {

    @Inject
    PlaylistService playlistService;

    @Inject
    TokenService tokenService;

    @Inject
    TrackService trackService;

    @Inject
    PlaylistsOverviewItemBuilder playlistsOverviewItemBuilder;

    @GET
    @Path("/")
    public Response getAllPlaylists(@QueryParam("token") String token) {
        if (tokenService.doesTokenExist(token)) {
            List<Playlist> playlists = playlistService.getAllPlaylists();
            int currentUserId = tokenService.getUserIdByTokenUuid(token);
            int afspeelduur = trackService.getTotalTrackTime();

            PlaylistOverview overview = getPlaylistOverview(playlists, afspeelduur, currentUserId);

            return Response.status(HttpResponse.OK.getValue()).entity(overview).build();
        }
        else {
            return Response.status(HttpResponse.UNAUTHORIZED.getValue()).build();
        }
    }

    private PlaylistOverview getPlaylistOverview(List<Playlist> playlists, int afspeelduur, int currentUserId) {
        List<PlaylistsOverviewItem> playlistsOverviewItems = playlistsOverviewItemBuilder
                .buildPlaylistOverviewsFromPlaylists(playlists, currentUserId);

        return new PlaylistOverview(playlistsOverviewItems, afspeelduur);
    }
}
