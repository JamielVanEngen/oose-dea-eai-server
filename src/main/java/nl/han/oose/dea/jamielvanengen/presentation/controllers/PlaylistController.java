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
import javax.ws.rs.*;
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
            int currentUserId = tokenService.getUserIdByTokenUuid(token);
            PlaylistOverview overview = getPlaylistOverview(currentUserId);

            return Response.status(HttpResponse.OK.getValue()).entity(overview).build();
        }
        else {
            return Response.status(HttpResponse.UNAUTHORIZED.getValue()).build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deletePlaylist(@PathParam("id") int id, @QueryParam("token") String token) {
        if (tokenService.doesTokenExist(token)) {
            int currentUserId = tokenService.getUserIdByTokenUuid(token);
            Playlist playlist = playlistService.getPlaylistById(id);
            if (playlist.getUserid() == currentUserId) {
                playlistService.deletePlaylistByid(id);
                PlaylistOverview overview = getPlaylistOverview(currentUserId);

                return Response.status(HttpResponse.OK.getValue()).entity(overview).build();
            }
        }
        return Response.status(HttpResponse.UNAUTHORIZED.getValue()).build();
    }

    private PlaylistOverview getPlaylistOverview(int currentUserId) {
        List<Playlist> playlists = playlistService.getAllPlaylists();
        int afspeelduur = trackService.getTotalTrackTime();
        List<PlaylistsOverviewItem> playlistsOverviewItems = playlistsOverviewItemBuilder
                .buildPlaylistOverviewsFromPlaylists(playlists, currentUserId);

        return new PlaylistOverview(playlistsOverviewItems, afspeelduur);
    }
}
